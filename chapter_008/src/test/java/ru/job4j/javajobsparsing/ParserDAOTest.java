package ru.job4j.javajobsparsing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

/**
 * Class for data access test.
 *
 * @since 06/12/2017
 * @version 1
 */
public class ParserDAOTest {

    private Properties properties;

    /**
     * Initiates property obj.
     */
    @Before
    public void init() {
        properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/javajobs_db");
        properties.setProperty("password", "pass");
    }

    /**
     * Tests adding and getting vacancies from DB.
     */
    @Test
    public void addAndGetAllVacanciesTest() {
        ParserDAO dao = new ParserDAO(properties);
        BlockingQueue<Vacancy> vacancies = new ArrayBlockingQueue<Vacancy>(10);
        Vacancy vacancy1 = new Vacancy("name1", "great work 1", "6-12-2017", "7-12-2017", "link1");
        Vacancy vacancy2 = new Vacancy("name2", "great work 2", "6-12-2017", "7-12-2017", "link2");
        Vacancy vacancy3 = new Vacancy("name3", "great work 3", "7-12-2017", "8-12-2017", "link3");
        try {
            vacancies.put(vacancy1);
            vacancies.put(vacancy2);
            vacancies.put(vacancy3);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dao.addVacanciesToDB(vacancies);
                }
            });
            thread.start();
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Vacancy> newVacancies = new ArrayList<>();
        newVacancies = dao.getAllVacancys();
        assertTrue(newVacancies.contains(vacancy1));
        assertTrue(newVacancies.contains(vacancy2));
        assertTrue(newVacancies.contains(vacancy3));
    }

    /**
     * Cleans DB after test.
     */
    @After
    public void cleanDB() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javajobs_db", "postgres", "pass");
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM vacancies");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}