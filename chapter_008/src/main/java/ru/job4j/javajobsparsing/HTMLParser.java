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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing on www.sql.ru.
 *
 * @since 29/11/2017
 * @version 1
 */
public class HTMLParser {

    private Properties properties;
    private String endMarkVacancyName;
    private final Logger log = LoggerFactory.getLogger(HTMLParser.class);
    private ParserDAO database;
    private int QUEUE_CAPACITY;
    private BlockingQueue<Vacancy> vacancies;
    private String dateOfParsing;
    private volatile boolean firstIteration = true;
    private SimpleDateFormat parsingDateFormat;
    private ExecutorService executorService;
    private boolean parserIsNotStopped = true;

    public HTMLParser(ParserDAO database, Properties properties) {
        this.database = database;
        this.properties = properties;
        endMarkVacancyName = loadEndMarkVacancyName();
        parsingDateFormat = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
        dateOfParsing = parsingDateFormat.format(new Date());
        QUEUE_CAPACITY = 100;
        vacancies = new ArrayBlockingQueue<Vacancy>(QUEUE_CAPACITY);
        int CPU_NUM = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(CPU_NUM * 2);
    }

    /**
     * Only for test purposes.
     */
    public HTMLParser() {
    }

    private String loadEndMarkVacancyName() {
        return properties.getProperty("endmarkvacancyname");
    }

    /**
     * Top method for parsing site.
     */
    public void parseSite() {
        Document doc;
        String firstURL = "http://www.sql.ru/forum/job-offers";
        try {
            doc = Jsoup.connect(firstURL).get();
            Element element = doc.body().getElementsByClass("sort_options").last();
            Element lastLink = element.select("a[href]").last();
            int numOfPages = Integer.parseInt(lastLink.text());

            DAORunnable daoRunnable = new DAORunnable(database);
            executorService.submit(daoRunnable);

            int numOfParsedPage = 2;
            String nextPageURL = firstURL;
            while(parserIsNotStopped && numOfParsedPage <= numOfPages) {
                executorService.submit(new PageParser(nextPageURL));
                nextPageURL = firstURL + "/" + numOfParsedPage;
                numOfParsedPage++;
            }

            // Проблема: с помощью метода ниже программа должна прерывать потоки ПОСЛЕ окончания работы
            // цикла WHILE, но получается, что потоки прерываются сразу и программа ничего не парсит. Но без прерыания
            // потоков shutdownNow() невозможно нормально выйти из программы.
            stopParsing();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Saves to property file name of first vacancy - the limit for future parsing.
     * @param value is name of first vacancy
     */
    private synchronized void saveEndMarkVacancyName(String value) {
        properties.setProperty("endmarkvacancyname", value);
        try (OutputStream outputStream = Files.newOutputStream(
                Paths.get("chapter_008\\src\\main\\resources\\jobparser.properties"));
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            properties.store(writer, null);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Stops parsing and adding vacancies to DB.
     */
    private synchronized void stopParsing() {
        parserIsNotStopped = false;
        executorService.shutdownNow();
    }

    /**
     * Class parses html pages by URL.
     */
    private class PageParser implements Runnable {

        private String url;

        public PageParser(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                Document doc = Jsoup.connect(url).get();
                boolean parsingIsOk = parsePage(doc);
                if (!parsingIsOk) {
                    stopParsing();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                stopParsing();
            }
        }

        /**
         * Parsing page.
         * @param doc is Jsoup representation of html page
         * @return true if parsing was successful
         */
        public boolean parsePage(Document doc) {
            // в случае сбоя в исполнения кода в блоке try исполнение метода прекратится и он
            // вернет false, что приведет к прекращению работы parseSite()
            boolean result = true;
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

                            // проверка на совпадение с именем первой вакансии из прошлого сеанса работы
                            // парсера, если совпадение есть - прекращаем работу парсера (выходим из цикла
                            // и возвращаем false)
                            if (endMarkVacancyName.equals(nameOfVacancy)) {
                                result = false;
                                break;
                            }
                            parseJavaVacancy(linkToVacancy, nameOfVacancy);
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                result = false;
                System.err.println("The program is stopped because of an error during parsing process");
            }
            return result;
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
        private void parseJavaVacancy(String url, String nameOfVacancy) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Element element = doc.body().getElementsByClass("msgBody").first().nextElementSibling();
                Element time = doc.body().getElementsByClass("msgFooter").first();

                String vacancyText = element.text();
                String timeOfPublishing = getDateOfPublishing(time.text());

                Vacancy vacancy = new Vacancy();
                vacancy.setVacancyName(nameOfVacancy);
                vacancy.setDatePublishing(timeOfPublishing);
                vacancy.setDateParsing(dateOfParsing);
                vacancy.setVacancyText(vacancyText);
                vacancy.setLink(url);
                vacancies.put(vacancy);

                // если это первая вакансия, то сохраняем её имя -
                // в следующий раз парсим до неё - как от этого избавиться?
                if (firstIteration) {
                    firstIteration = false;
                    saveEndMarkVacancyName(nameOfVacancy);
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        private String getDateOfPublishing(String source) {
            int i = source.indexOf("[");
            String result = source.substring(0, i - 1);
            // заменяем "сегодня" и "вчера" на дату
            if (result.contains("сегодня")) {
                String newDate = parsingDateFormat.format(new Date());
                String s = result.substring(7, result.length());
                result = newDate + s;
            } else if (result.contains("вчера")) {
                String newDate = parsingDateFormat.format(new Date(System.currentTimeMillis() - 1000*60*60*24));
                String s = result.substring(5, result.length());
                result = newDate + s;
            }
            return result;
        }
    }

    /**
     * Class is task for executor service, which starts 'addVacanciesToDB' method.
     */
    private class DAORunnable implements Runnable {

        private ParserDAO dao;

        public DAORunnable(ParserDAO dao) {
            this.dao = dao;
        }

        @Override
        public void run() {
            dao.addVacanciesToDB(vacancies);
        }
    }
}
