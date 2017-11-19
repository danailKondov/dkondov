package ru.job4j.firststepjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

/**
 * Class for JDBC and SQL first steps.
 *
 * @since 02/11/2017
 * @version 1
 */
public class SQLStorage {
    private static final Logger Log = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/cars";
        String username = "postgres";
        String password = "pass";

        // альтернатива с Properties:

//        Properties props = new Properties();
//        props.setProperty("user","fred");
//        props.setProperty("password","secret");
//        props.setProperty("ssl","true");
//        Connection conn = DriverManager.getConnection(url, props);

        // или так:

//        String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
//        Connection conn = DriverManager.getConnection(url);


        Connection conn = null;
//        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, username, password);

            // выводим в консоль все таблицы из БД:
            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getTables(null, null, null, null);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }

//            st = conn.createStatement();
//            rs = st.executeQuery("SELECT * FROM cars_list");
//            while (rs.next())
//            {
//                System.out.println(rs.getString("car_name")); // по названию колонки
//            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
//                st.close();
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }


    }
}
