package org.movierental.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery) {
        try {
            var connection = DatabaseConnection.connect();
            var statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void executeQuery(String query) {
        try {
            var connection = DatabaseConnection.connect();
            var statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
