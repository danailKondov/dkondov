package ru.job4j.crud.model;

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

    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    private UserStore() {
        initialize();
    }
    /**
     * Inner static class for singlet realisation.
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
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id serial PRIMARY KEY," +
                    "user_name CHARACTER VARYING (30)," +
                    "user_login CHARACTER VARYING (30)," +
                    "user_email CHARACTER VARYING (50)," +
                    "create_date TIMESTAMP)");
        } catch (SQLException e) {
            log.error("Connection to DB failed to be created and DB is not (probably) initialized", e);
        }
    }

    /**
     * Adds user to DB.
     * @param user to add
     */
    public void add (User user) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                     "(user_name, user_login, user_email, create_date) " +
                     "VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.executeUpdate();
            // получаем сгенерированный id и записываем его юзеру:
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
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
                     "id, user_name, user_login, user_email, create_date " +
                     "FROM users " +
                     "WHERE user_login = ?")) {
            statement.setString(1, login);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String email = rs.getString(4);
                    Timestamp createDate = rs.getTimestamp(5);
                    result = new User (id, name, login, email, createDate);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
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
                         "SET user_name = ?, user_login = ?, user_email = ?, create_date = ? " +
                         "WHERE user_login = ?")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getEmail());
                statement.setTimestamp(4, user.getCreateDate());
                statement.setString(5, login);
                // проверить ниже, раньше не работало:
                int res = statement.executeUpdate();
                if (res > 0) result = true;
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Deletes user from DB by login
     * @param login of user to delete
     * @return true if
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
                log.error(e.getMessage(), e);
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
             final PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
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
                     "id, user_name, user_login, user_email, create_date " +
                     "FROM users ")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String login = rs.getString(3);
                    String email = rs.getString(4);
                    Timestamp createDate = rs.getTimestamp(5);
                    result.add(new User (id, name, login, email, createDate));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
