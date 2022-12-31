package org.movierental.repository;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryExecutorTest implements AutoCloseable {
    private final Connection connection;

    public QueryExecutorTest() {
        connection = new DatabaseConnectionTest().connect();
    }

    public Connection getConnection() {
        return connection;
    }

    public void executeQuery(String query) {
        try (var statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}