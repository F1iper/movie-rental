package org.movierental.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DatabaseConnectionTest {

    public Connection connect() {
        try (InputStream input = new FileInputStream("src/test/resources/dbconfig.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.warn("Error connecting to the database: " + e.getMessage());
            return null;
        } catch (IOException e) {
            log.warn("Error reading database configuration: " + e.getMessage());
            return null;
        }
    }
}

