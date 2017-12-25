package ru.job4j.javajobsparsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Class for starting java jobs parsing on www.sql.ru.
 *
 * @since 21/12/2017
 * @version 3
 */
public class StartUI {

    private final Logger log;
    private Properties properties;

    /**
     * Frequency you want program to startToParse.
     */
    private int frequency;

    /**
     * How often program was already started today.
     */
    private int startedToday;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
    private String lastSessionTime;
    private String actualDate;

    public static void main(String[] args) {
        new StartUI();
    }

    public StartUI() {
        actualDate = dateFormat.format(new Date());
        log = LoggerFactory.getLogger(StartUI.class);
        properties = new Properties();
        loadProperties();
        checkStartFrequencyAndStart();
    }

    /**
     * Initiates fields from property file.
     */
    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jobparser.properties");
             InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
            properties.load(reader);
            lastSessionTime = properties.getProperty("lastsessiontime");
            String searchFrequency = properties.getProperty("searchfreqency");
            String alreadyStarted = properties.getProperty("startedtoday");
            frequency = Integer.parseInt(searchFrequency);
            startedToday = Integer.parseInt(alreadyStarted);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Checks start count and starts site parsing process or sets timer.
     */
    private void checkStartFrequencyAndStart() {
        // если сегодня еще не запускали
        if (!actualDate.equals(lastSessionTime)) {
            startedToday = 1;
            System.out.println("First start for today");
            startToParse();
        } else {
            // если запускали, то проверяем количество запусков и их
            // необходимую частоту
            if (startedToday < frequency) {
                ++startedToday;
                System.out.println("Program was started today " + startedToday + " time(s)");
                startToParse();
            } else {
                // если количество запусков равно или больше требуемой
                // частоты, то запускаем таймер, который включит парсер
                // в начале следующих суток (в случае, если программа все
                // еще будеи работать)
                System.out.println("Limit for starting program for today was exhausted");
                setTimer();
            }
        }
    }

    /**
     * Sets timer to start parsing in the beginning of the next day.
     */
    private void setTimer() {
        // вычисляем время до конца дня
        LocalTime current = LocalTime.now();
        long millis = ChronoUnit.MILLIS.between(current, LocalTime.MAX);
        millis += 1000*60;
        // запускаем таймер, который включит парсер в начале следующих суток
        long hours = millis/1000/60/60;
        long minutes = (millis - hours*60*60*1000)/1000/60;
        System.out.println("Timer is set \nNext time program wil stat in " + hours + " hours " + minutes + " min");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startedToday = 1;
                startToParse();
            }
        };
        timer.schedule(task, millis);
    }

    /**
     * Starts parsing process.
     */
    private void startToParse() {
        saveTimeAndFrequencyProperties();
        ParserDAO dao = new ParserDAO(properties);
        HTMLParser parser = new HTMLParser(dao, properties);
        parser.parseSite();
    }

    /**
     * Saves to property file date and frequency of parsing.
     */
    private void saveTimeAndFrequencyProperties() {
        properties.setProperty("lastsessiontime", dateFormat.format(new Date()));
        properties.setProperty("startedtoday", String.valueOf(startedToday));
        try (OutputStream outputStream = Files.newOutputStream(
                Paths.get("chapter_008\\src\\main\\resources\\jobparser.properties"));
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            properties.store(writer, null);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
