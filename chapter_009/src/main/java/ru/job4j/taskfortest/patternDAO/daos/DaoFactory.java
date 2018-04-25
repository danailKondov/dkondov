package ru.job4j.taskfortest.patternDAO.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.Pool;
import ru.job4j.crud.model.UserStore;
import ru.job4j.taskfortest.patternDAO.EntityDao;
import ru.job4j.taskfortest.patternDAO.TestTaskPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class is factory for daos. Initialises DB with default schema and data;
 *
 * @since 20/01/2018
 * @version 1
 */
public class DaoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DaoFactory.class);

    static {
        initialize();
    }

    /**
     * Фабричный метод, обеспечивающий инкапсуляцию создания объектов доступа к БД.
     * @param daoName имя сущности, доступ к которой обеспечивает DAO
     * @return объект DAO
     */
    public static EntityDao getDaoByName(String daoName) {

        switch (daoName.toLowerCase()) {
            case "user": return new UserDao();
            case "role": return RoleDao.getInstance();
            case "musictype": return new MusicTypeDao();
            case "address": return AddressDao.getInstance();
            default: return null;
        }
    }

    public static Connection getConnection() throws SQLException {
        return TestTaskPool.getDataSource().getConnection();
    }

    private static void initialize() {
        try (final Connection connection = getConnection();
             final Statement statement = connection.createStatement()) {
            addSchema(statement);
            addDefaultRoles(statement);
            addDefaultMusicTypes(statement);
        } catch (SQLException e) {
            LOG.error("Connection to DB failed to be created and DB is not (probably) initialized", e);
        }
    }

    private static void addDefaultMusicTypes(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO music_types (music_type_name) VALUES ('RAP')");
        statement.executeUpdate("INSERT INTO music_types (music_type_name) VALUES ('ROCK')");
        statement.executeUpdate("INSERT INTO music_types (music_type_name) VALUES ('JAZZ')");
        statement.executeUpdate("INSERT INTO music_types (music_type_name) VALUES ('CLASSIC')");
    }

    private static void addSchema(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS roles");
        statement.executeUpdate("DROP TABLE IF EXISTS addreses");
        statement.executeUpdate("DROP TABLE IF EXISTS users");
        statement.executeUpdate("DROP TABLE IF EXISTS music_types");
        statement.executeUpdate("DROP TABLE IF EXISTS users_music");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS roles (" +
                "id serial PRIMARY KEY," +
                "role_name CHARACTER VARYING (30))");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS addresses (" +
                "id serial PRIMARY KEY," +
                "address_name CHARACTER VARYING (50))");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id serial PRIMARY KEY," +
                "user_name CHARACTER VARYING (30)," +
                "address_id INTEGER REFERENCES addreses (id)" +
                "ON UPDATE CASCADE , " +
                "roles_id INTEGER REFERENCES roles (id) " +
                "ON UPDATE CASCADE )");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS music_types (" +
                "id serial PRIMARY KEY," +
                "music_type_name CHARACTER VARYING (30) NOT NULL )");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users_music (" +
                "user_id INTEGER REFERENCES users(id) " +
                "ON UPDATE CASCADE " +
                "ON DELETE CASCADE ," +
                "music_id INTEGER REFERENCES music_types(id) " +
                "ON UPDATE CASCADE " +
                "ON DELETE CASCADE )");
    }

    private static void addDefaultRoles(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO roles (role_name) VALUES ('USER')");
        statement.executeUpdate("INSERT INTO roles (role_name) VALUES ('MANDATOR')");
        statement.executeUpdate("INSERT INTO roles (role_name) VALUES ('ADMIN')");
    }
}
