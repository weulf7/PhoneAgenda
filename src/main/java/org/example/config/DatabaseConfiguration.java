package org.example.config;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    private static Connection connection;

    public static Connection getConnection() {


        if (connection == null) {
            InputStream inputStream = DatabaseConfiguration.class.getClassLoader()
                    .getResourceAsStream("application.properties");


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Properties properties = new Properties();
                properties.load(inputStream);

                connection = DriverManager.getConnection(
                        properties.getProperty("datasource.url"),
                        properties.getProperty("datasource.username"),
                        properties.getProperty("datasource.password"));

            } catch (IOException | SQLException |ClassNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Failed to close input stream.");
                }
            }

        }
        return connection;
    }
}
