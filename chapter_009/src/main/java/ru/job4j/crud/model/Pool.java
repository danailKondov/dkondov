package ru.job4j.crud.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for c3pO connection pool.
 *
 * @since 20/01/2018
 * @version 1
 */
public class Pool {

    private static final Logger LOG = LoggerFactory.getLogger(Pool.class);
    private static final Pool INSTANCE = new Pool();
    private final ComboPooledDataSource source;


    private Pool() {
        source = new ComboPooledDataSource();

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");

            source.setJdbcUrl(url);
            source.setUser(username);
            source.setPassword(password);
            source.setDriverClass(driver);

            source.setMinPoolSize(10);
            source.setAcquireIncrement(5);
            source.setMaxPoolSize(100);
        } catch (Exception e) {
            LOG.error("Properties for DB not found", e);
        }
    }

    public static DataSource getDataSource() {
        return INSTANCE.source;
    }
}
