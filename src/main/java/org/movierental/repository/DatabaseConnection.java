package org.movierental.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;


public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

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
                logger.info("Database connected");
            } catch (Exception e) {
                logger.info("Error: " + e.getMessage());
            }
            return connection;
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        return null;
    }
}
