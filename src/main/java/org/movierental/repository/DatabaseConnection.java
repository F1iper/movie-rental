package org.movierental.repository;

import lombok.extern.slf4j.Slf4j;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Slf4j
public class DatabaseConnection {

    public static Connection connect() {
        try (InputStream input = new FileInputStream("src/resources/dbconfig.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);
                log.warn("Database connected");
            } catch (Exception e) {
                log.warn("Error: " + e.getMessage());
            }
            return connection;
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return null;
    }
}
