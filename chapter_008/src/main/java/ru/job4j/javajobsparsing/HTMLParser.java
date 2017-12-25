package ru.job4j.javajobsparsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing on www.sql.ru.
 *
 * @since 21/12/2017
 * @version 3
 */
public class HTMLParser {

    private Properties properties;
    private Date controlDate;
    private final Logger log = LoggerFactory.getLogger(HTMLParser.class);
    private ParserDAO database;
    private final int QUEUE_CAPACITY = 100;
    private BlockingQueue<Vacancy> vacancies;
    private TreeSet<Date> vacancyDates;
    private String dateOfParsing;
    private SimpleDateFormat parsingDateFormat;
    private SimpleDateFormat controlDateFormat;
    private ExecutorService executorService;
    private ExecutorService executorServiceForDAO;
    private boolean parserIsNotStopped = true;

    public HTMLParser(ParserDAO database, Properties properties) {
        this.database = database;
        this.properties = properties;
        parsingDateFormat = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
        controlDateFormat = new SimpleDateFormat("dd MMM yy, HH:mm");
        dateOfParsing = parsingDateFormat.format(new Date());
        controlDate = loadControlDate();
        vacancies = new ArrayBlockingQueue<Vacancy>(QUEUE_CAPACITY);
        vacancyDates = new TreeSet<>();
        int CPU_NUM = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(CPU_NUM * 2);
        executorServiceForDAO = Executors.newSingleThreadExecutor();
    }

    /**
     * Only for test purposes.
     */
    public HTMLParser() {
    }


    /**
     * Loads from property file date of the latest vacancy from last parsing.
     * @return Date
     */
    private Date loadControlDate() {
        Date result = null;
        try {
            String date = properties.getProperty("endmarkvacancydate");
            result = controlDateFormat.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            // если не получается загрузить дату граничной вакансии, то полностью
            // парсим сайт - устанавливаем дату на 20 лет назад
            result = new Date(System.currentTimeMillis() - 1000*60*60*24*365*20);
        }
        return result;
    }

    /**
     * Top method for parsing site.
     */
    public void parseSite() {
        Document doc;
        String firstURL = "http://www.sql.ru/forum/job-offers";
        try {
            doc = Jsoup.connect(firstURL).timeout(10*1000).get();
            int numOfPages = getNumberOfHTMLPages(doc);
            startDatabaseThread();
            startHTMLParsingThreads(numOfPages);
            stopParsing();
            saveControlDate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets number of HTML pages with vacancies.
     * @param doc is Jsoup Document representing first HTML page
     * @return number of pages
     */
    private int getNumberOfHTMLPages(Document doc) {
        Element element = doc.body().getElementsByClass("sort_options").last();
        Element lastLink = element.select("a[href]").last();
        return Integer.parseInt(lastLink.text());
    }

    /**
     * Starts thread adding vacancies to DB.
     */
    private void startDatabaseThread() {
        executorServiceForDAO.submit(new DAORunnable(database, vacancies));
    }

    /**
     * Starts multithreading parsing of HTML pages.
     * @param numOfPages is number of pages on site
     */
    private void startHTMLParsingThreads(int numOfPages) {
        String firstURL = "http://www.sql.ru/forum/job-offers";
        int numOfParsedPage = 2;
        String nextPageURL = firstURL;
        while(parserIsNotStopped && numOfParsedPage <= numOfPages) {
            executorService.submit(new PageParser(nextPageURL));
            nextPageURL = firstURL + "/" + numOfParsedPage;
            numOfParsedPage++;
        }
    }

    /**
     * Saves to property date of first vacancy - the limit for future parsing.
     */
    private synchronized void saveControlDate() {
        if (vacancyDates.size() > 0) {
            Date controlDate = vacancyDates.last();
            String date = controlDateFormat.format(controlDate);
            properties.setProperty("endmarkvacancydate", date);
            try (OutputStream outputStream = Files.newOutputStream(
                    Paths.get("chapter_008\\src\\main\\resources\\jobparser.properties"));
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
                properties.store(writer, null);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Stops parsing and adding vacancies to DB.
     */
    private synchronized void stopParsing() {
        try {
            parserIsNotStopped = false;
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.MINUTES);
            if (!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
            executorServiceForDAO.shutdownNow();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Class parses html pages by URL and adds resulting vacancies to queue.
     */
    private class PageParser implements Runnable {

        private String url;

        public PageParser(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                Document doc = Jsoup.connect(url).timeout(20*1000).get();
                parsePage(doc);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        /**
         * Parsing page.
         * @param doc is Jsoup representation of html page
         */
        public void parsePage(Document doc) {
            try {
                Elements elements = doc.body().getElementsByClass("postslisttopic");
                Elements links = elements.select("a[href]");
                for (Element link : links) {

                    // проверка на прерывание треда
                    if (!Thread.currentThread().isInterrupted()) {
                        String linkToVacancy = link.attr("href");
                        String nameOfVacancy = link.text();

                        // проверка на наличие "java" в названии вакансии
                        if (checkForJavaIn(nameOfVacancy)) {
                            parseJavaVacancy(linkToVacancy, nameOfVacancy);
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        /**
         * Checks name of vacancy for 'java' and 'javascript'.
         * @param s is name of vacancy
         * @return true if name have 'java' and not 'javascript'
         */
        public boolean checkForJavaIn(String s){
            Pattern java = Pattern.compile(".*\\b(java)\\b.*", Pattern.CASE_INSENSITIVE);
            Pattern javaScript = Pattern.compile(".*\\b(java)?( |-|)?(script)\\b.*", Pattern.CASE_INSENSITIVE);
            Matcher m = java.matcher(s);
            Matcher m2 = javaScript.matcher(s);
            return m.matches() && !(m2.matches());
        }

        /**
         * Parses java vacancy html page to Vacancy obj and adds it to queue.
         * @param url of html page with vacancy
         * @param nameOfVacancy is name of vacancy
         */
        private void parseJavaVacancy(String url, String nameOfVacancy) throws Exception {
            Document doc;

            doc = Jsoup.connect(url).timeout(20*1000).get();
            Element element = doc.body().getElementsByClass("msgBody").first().nextElementSibling();
            Element time = doc.body().getElementsByClass("msgFooter").first();

            String vacancyText = element.text();
            String timeOfPublishing = getDateOfPublishing(time.text());
            Date dateOfPublishing = getDateFromString(timeOfPublishing);

            // сравниваем дату публикации с контрольной датой и если публикация
            // имело место позже контрольной, то добавляем в БД
            if (dateOfPublishing != null && dateOfPublishing.after(controlDate)) {
                // добавляем в список дат
                vacancyDates.add(dateOfPublishing);

                Vacancy vacancy = new Vacancy();
                vacancy.setVacancyName(nameOfVacancy);
                vacancy.setDatePublishing(timeOfPublishing);
                vacancy.setDateParsing(dateOfParsing);
                vacancy.setVacancyText(vacancyText);
                vacancy.setLink(url);

                // ставим в очередь на добавление в БД
                vacancies.put(vacancy);
            }
        }

        /**
         * Parses string representation of date from site.
         * @param source row date from site
         * @return normal string representation of date
         */
        private String getDateOfPublishing(String source) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
            int i = source.indexOf("[");
            String result = source.substring(0, i - 1);
            // заменяем "сегодня" и "вчера" на дату
            if (result.contains("сегодня")) {
                String newDate = dateFormat.format(new Date());
                String s = result.substring(7, result.length());
                result = newDate + s;
            } else if (result.contains("вчера")) {
                String newDate = dateFormat.format(new Date(System.currentTimeMillis() - 1000*60*60*24));
                String s = result.substring(5, result.length());
                result = newDate + s;
            }
            return result;
        }

        /**
         * Returns Date object from string date representation.
         * @param date is string date
         * @return Date obj
         */
        private Date getDateFromString(String date) {
            Date result = null;
            try {
                // ВНИМАНЕ! SimpleDateFormat не является потокобезопасным, поэтому на каждый тред
                // создаем свой экземпляр
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm");
                result = format.parse(date);
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
            }
            return result;
        }
    }
}
