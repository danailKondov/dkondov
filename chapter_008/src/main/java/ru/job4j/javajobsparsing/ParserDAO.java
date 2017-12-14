package ru.job4j.javajobsparsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class provides access to DB.
 *
 * @since 29/11/2017
 * @version 1
 */
public class ParserDAO {

    /**
     * Connection to DB.
     */
    private Connection connection;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ParserDAO.class);

    public ParserDAO(Properties properties) {
        String url = properties.getProperty("url");
        String username = properties.getProperty("user");
        String password = properties.getProperty("password");
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            initiateDB();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Initiates DB: create table if not exist.
     */
    private void initiateDB() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS vacancies (" +
                    "id serial PRIMARY KEY, " +
                    "vacancy_name TEXT, " +
                    "vacancy TEXT, " +
                    "date_of_publishing CHARACTER VARYING (30), " +
                    "date_of_parsing CHARACTER VARYING (30), " +
                    "link TEXT)");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Adds vacancies to DB.
     * @param list of vacancies
     */
    public void addVacanciesToDB(List<Vacancy> list) {
        String stmt = "INSERT INTO vacancies (vacancy_name, vacancy, date_of_publishing, date_of_parsing, link) " +
                      "VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(stmt)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : list) {
                statement.setString(1, vacancy.getVacancyName());
                statement.setString(2, vacancy.getVacancyText());
                statement.setString(3, vacancy.getDatePublishing());
                statement.setString(4, vacancy.getDateParsing());
                statement.setString(5, vacancy.getLink());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error(e.getMessage(), e);
            }
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets all vacancies from DB.
     * @return list of vacancies.
     */
    public List<Vacancy> getAllVacancys() {
        ArrayList<Vacancy> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, vacancy_name, vacancy, date_of_publishing, date_of_parsing, link " +
                    "FROM vacancies " +
                    "ORDER BY id");
            while(rs.next()) {
                String vacancyName = rs.getString(2);
                String vacancyText = rs.getString(3);
                String datePublishing = rs.getString(4);
                String dateParsing = rs.getString(5);
                String link = rs.getString(6);
                Vacancy vacancy = new Vacancy(vacancyName, vacancyText, datePublishing, dateParsing, link);
                result.add(vacancy);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
