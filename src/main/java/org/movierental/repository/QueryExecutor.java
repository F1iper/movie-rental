package org.movierental.repository;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryExecutor implements AutoCloseable {
    private final DatabaseConnection databaseConnection;

    public QueryExecutor() {
        databaseConnection = new DatabaseConnection();
    }

    public Connection getConnection() {
        return databaseConnection.connect();
    }

    public void executeQuery(String query) {
        try (var statement = databaseConnection.connect().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            databaseConnection.connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}