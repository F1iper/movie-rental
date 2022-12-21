package org.movierental.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class QueryExecutor {

    public static void executeInsertCompany(String companyName) {
        try {
            var connection = DatabaseConnection.connect();
            String sql = "INSERT INTO company (name) values (?)";

            var statement = connection.prepareStatement(sql);
            statement.setString(1, companyName);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                log.info("New company [" + companyName + "] has been inserted successfully.");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void executeInsertAddress(Address address) {
        try {
            var connection = DatabaseConnection.connect();
            String sql = "INSERT INTO address (street, city, state, zip_code, phone) " +
                    "VALUES (?, ?, ?, ?, ?)";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getState());
            statement.setString(4, address.getZip_code());
            statement.setString(5, address.getPhone());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Added: " + address);
            }

        } catch (SQLException e) {
            log.warn(e.getMessage());
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
