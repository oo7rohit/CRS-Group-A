package com.flipkart.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    public static Connection con = null;

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
            properties.load(inputStream);
            String driver = properties.getProperty("driver");
            String url = "jdbc:mysql://localhost:3306/crs";
            String username = "root";
            String password = "Blue_175976";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

