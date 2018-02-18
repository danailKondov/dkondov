package ru.job4j.crud.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for database access (DAO).
 *
 * @since 17/01/2018
 * @version 1
 */

public class UserStore {

    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private UserStore() {
        initialize();
    }

    /**
     * Inner static class for singlton realisation.
     */
    private static class UserStoreHelper {
        private static final UserStore INSTANCE = new UserStore();
    }

    public static UserStore getInstance() {
        return UserStoreHelper.INSTANCE;
    }

    /**
     * Initializes DB: creates table in case if not exists.
     */
    private void initialize() {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id serial PRIMARY KEY," +
                    "user_name CHARACTER VARYING (30)," +
                    "user_login CHARACTER VARYING (30)," +
                    "user_email CHARACTER VARYING (50)," +
                    "user_password CHARACTER VARYING (50)," +
                    "user_role CHARACTER VARYING (50)," +
                    "user_city CHARACTER VARYING (50)," +
                    "user_country CHARACTER VARYING (50)," +
                    "create_date TIMESTAMP)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS roles (" +
                    "id serial PRIMARY KEY," +
                    "role_name CHARACTER VARYING (30))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cities (" +
                    "id serial PRIMARY KEY," +
                    "city_name CHARACTER VARYING (30)," +
                    "country_name CHARACTER VARYING (30))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS countries (" +
                    "id serial PRIMARY KEY," +
                    "country_name CHARACTER VARYING (30))");

            // добавляем роль "admin", которая обязательна по условию
            // и добавляем дефолтного админа, без которого невозможно что-либо делать с БД в начале работы
            // так же добавляем города и страны, если ничего нет
            addAdminRoleIfNotExists();
            addDefaultAdminIfNotExists();
            addDefaultCountriesAndCitiesIfNotExists(statement);
        } catch (SQLException e) {
            LOG.error("Connection to DB failed to be created and DB is not (probably) initialized", e);
        }
    }

    private void addDefaultCountriesAndCitiesIfNotExists(Statement statement) throws SQLException {
        if (getAllCountries().isEmpty() && getAllCities().isEmpty()) {
            statement.executeUpdate("INSERT INTO countries (country_name) VALUES ('Russia')");
            statement.executeUpdate("INSERT INTO countries (country_name) VALUES ('Germany')");
            statement.executeUpdate("INSERT INTO countries (country_name) VALUES ('France')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Paris', 'France')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Toulouse', 'France')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Lion', 'France')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Moscow', 'Russia')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('SPb', 'Russia')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Vladivistok', 'Russia')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Berlin', 'Germany')");
            statement.executeUpdate("INSERT INTO cities (city_name, country_name) VALUES ('Frankfurt', 'Germany')");
        }
    }

    private void addDefaultAdminIfNotExists() {
        boolean adminInUsersTab = false;
        for (User user : getAllUsers()) {
            if (user.getRole().equals("admin")) adminInUsersTab = true;
        }
        if (!adminInUsersTab) add(new User(
                "defaultAdmin",
                "log",
                "pass",
                "admin",
                "email@mail.com",
                "Moscow",
                "Russia"
        ));
    }

    /**
     * Adds "admin" to DB.
     */
    private void addAdminRoleIfNotExists() {
        boolean adminInDB = false;
        for (Role role : getAllRoles()) {
            if (role.getRole().equals("admin")) adminInDB = true;
        }
        if (!adminInDB) addRole(new Role("admin")); // id будет добалена при внесении в БД
    }

    /**
     * Adds user to DB.
     * @param user to add
     */
    public void add (User user) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                     "(user_name, user_login, user_email, user_password, user_role, user_city, user_country, create_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getCity());
            statement.setString(7, user.getCountry());
            statement.setTimestamp(8, user.getCreateDate());
            statement.executeUpdate();
            // получаем сгенерированный id и записываем его юзеру:
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Gets user from DB.
     * @param login is criteria of search
     * @return null if no such user in DB
     */
    public User getUser (String login) {
        User result = null;
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT " +
                     "id, user_name, user_login, user_email, user_password, user_role, user_city, user_country, create_date " +
                     "FROM users " +
                     "WHERE user_login = ?")) {
            statement.setString(1, login);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String role = rs.getString(6);
                    String city = rs.getString(7);
                    String country = rs.getString(8);
                    Timestamp createDate = rs.getTimestamp(9);
                    result = new User (id, name, login, email, password, role, city, country, createDate);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Updates user data by login.
     * @param login is old login of user to update
     * @param user with new data
     * @return true if update was successful
     */
    public boolean update (String login, User user) {
        boolean result = false;
        if (login != null && user != null) {
            try (final Connection connection = Pool.getDataSource().getConnection();
                 final PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                         "SET user_name = ?, " +
                         "user_login = ?, " +
                         "user_email = ?, " +
                         "user_password =?, " +
                         "user_role = ?, " +
                         "user_city = ?, " +
                         "user_country = ?, " +
                         "create_date = ? " +
                         "WHERE user_login = ?")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getRole());
                statement.setString(6, user.getCity());
                statement.setString(7, user.getCountry());
                statement.setTimestamp(8, user.getCreateDate());
                statement.setString(9, login);
                int res = statement.executeUpdate();
                if (res > 0) result = true;
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Deletes user from DB by login
     * @param login of user to delete
     * @return true if delete was successful
     */
    public boolean delete (String login) {
        boolean result = false;
        if (login != null) {
            try (final Connection connection = Pool.getDataSource().getConnection();
                 final PreparedStatement statement = connection.prepareStatement("DELETE FROM users " +
                         "WHERE user_login = ?")) {
                statement.setString(1, login);
                int res = statement.executeUpdate();
                if (res > 0) {
                    result = true;
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Deletes all users and cleans DB.
     * For test purposes.
     */
    public void deleteAll() {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
//            statement.executeUpdate("DELETE FROM roles");
//            statement.executeUpdate("DELETE FROM cities");
//            statement.executeUpdate("DELETE FROM countries");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Gets all users from DB.
     * @return list of users
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT " +
                     "id, user_name, user_login, user_email, user_password, user_role, user_city, user_country, create_date " +
                     "FROM users ")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String login = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String role = rs.getString(6);
                    String city = rs.getString(7);
                    String country = rs.getString(8);
                    Timestamp createDate = rs.getTimestamp(9);
                    result.add(new User (id, name, login, email, password, role, city, country, createDate));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Tests if login/password are registered.
     * @param login to test
     * @param password to test
     * @return true if login and password are valid
     */
    public boolean loginAndPasswordIsValid(String login, String password) {
        boolean result = false;
        if (login != null & password != null) {
            User user = getUser(login);
            if (user != null && user.getPassword().equals(password)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Adds role to DB.
     * @param role to add
     */
    public void addRole (Role role) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT INTO roles " +
                             "(role_name) " +
                             "VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getRole());
            statement.executeUpdate();
            // получаем сгенерированный id и записываем его юзеру:
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    role.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes role from DB by name.
     * @param roleName to delete
     */
    public void deleteRole(String roleName) {
        if (roleName != null) {
            try (final Connection connection = Pool.getDataSource().getConnection();
                 final PreparedStatement statement = connection.prepareStatement("DELETE FROM roles " +
                         "WHERE role_name = ?")) {
                statement.setString(1, roleName);
                statement.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Gets all users from DB.
     * @return list of users
     */
    public List<Role> getAllRoles() {
        List<Role> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT " +
                     "id, role_name " +
                     "FROM roles ")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    result.add(new Role (id, name));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets all cities from DB.
     * @return list of cities
     */
    public List<City> getAllCities() {
        List<City> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT " +
                     "id, city_name, country_name " +
                     "FROM cities ")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String city = rs.getString(2);
                    String country = rs.getString(3);
                    result.add(new City (id, city, country));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets all countries from DB.
     * @return list of countries
     */
    public List<Country> getAllCountries() {
        List<Country> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT " +
                     "id, country_name " +
                     "FROM countries ")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    result.add(new Country (id, name));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Closes connection pool.
     */
    public void close() {
        ((ComboPooledDataSource) Pool.getDataSource()).close();
    }
}
