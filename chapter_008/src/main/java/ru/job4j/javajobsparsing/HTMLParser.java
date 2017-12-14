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
    private List<Vacancy> vacancies = new ArrayList<>();
    private String dateOfParsing;
    private boolean firstIteration = true;
    private SimpleDateFormat parsingDateFormat;

    public HTMLParser(ParserDAO database, Properties properties) {
        this.database = database;
        this.properties = properties;
        endMarkVacancyName = loadEndMarkVacancyName();
        parsingDateFormat = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
        dateOfParsing = parsingDateFormat.format(new Date());
    }

    /**
     * Only for test purposes.
     */
    public HTMLParser() {
    }

    public void parseSite() {
        Document doc;
        String firstURL = "http://www.sql.ru/forum/job-offers";
        try {
            doc = Jsoup.connect(firstURL).get();
            Element element = doc.body().getElementsByClass("sort_options").last();
            Element lastLink = element.select("a[href]").last();
            int numOfPages = Integer.parseInt(lastLink.text());

            // парсим до последней страницы или пока метод parsePage не вернет false
            int numOfParsedPage = 2;
            boolean pageParsingIsNormal = true;
            while(pageParsingIsNormal && numOfParsedPage <= numOfPages) {
                pageParsingIsNormal = parsePage(doc);
                String nextPageURL = firstURL + "/" + numOfParsedPage;
                doc = Jsoup.connect(nextPageURL).get();
                numOfParsedPage++;
            }

            database.addVacanciesToDB(vacancies);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public boolean parsePage(Document doc) {
        // в случае сбоя в исполнения кода в блоке try исполнение метода прекратится и он
        // вернет false, что приведет к прекращению работы parseSite()
        boolean result = true;
        try {
            Elements elements = doc.body().getElementsByClass("postslisttopic");
            Elements links = elements.select("a[href]");
            for (Element link : links) {
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
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = false;
            System.err.println("The program is stopped because of an error during parsing process");
        }
        return result;
    }

    public boolean checkForJavaIn(String s){
        Pattern java = Pattern.compile(".*\\b(java)\\b.*", Pattern.CASE_INSENSITIVE);
        Pattern javaScript = Pattern.compile(".*\\b(java)?( |-|)?(script)\\b.*", Pattern.CASE_INSENSITIVE);
        Matcher m = java.matcher(s);
        Matcher m2 = javaScript.matcher(s);
        return m.matches() && !(m2.matches());
    }

    private void parseJavaVacancy(String url, String nameOfVacancy) {
        Document doc;
        Vacancy result = null;

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
            vacancies.add(vacancy);

            // если это первая вакансия, то сохраняем её имя -
            // в следующий раз парсим до неё
            if (firstIteration) {
                saveEndMarkVacancyName(nameOfVacancy);
                firstIteration = false;
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

    private String loadEndMarkVacancyName() {
        return properties.getProperty("endmarkvacancyname");
    }

    private void saveEndMarkVacancyName(String value) {
        properties.setProperty("endmarkvacancyname", value);
        try (OutputStream outputStream = Files.newOutputStream(
                Paths.get("chapter_008\\src\\main\\resources\\jobparser.properties"));
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            properties.store(writer, null);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
