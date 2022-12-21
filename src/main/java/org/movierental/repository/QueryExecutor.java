package org.movierental.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {

    public static void executeInsertCompany(String companyName) {
        try {
            var connection = DatabaseConnection.connect();
            String sql = "INSERT INTO company (name) values (?)";

            var statement = connection.prepareStatement(sql);
            statement.setString(1, companyName);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("New company [" + companyName + "] has been inserted successfully.");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

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

    public static void executeSearchCompanyByCompanyName(String companyName) {
        try {
            var connection = DatabaseConnection.connect();
            String sql = "SELECT * FROM company WHERE NAME LIKE '" + companyName + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
