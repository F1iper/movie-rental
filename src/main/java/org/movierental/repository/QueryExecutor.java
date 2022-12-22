package org.movierental.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.address.entity.Address;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryExecutor {

    public static void insertCompany(String companyName) {
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

    public static void insertAddress(Address address) {
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

    public static void searchByCompanyName(String companyName) {
        try {
            String sql = "SELECT * FROM company WHERE NAME LIKE '" + companyName + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);

            while (rs.next()) {
                System.out.print(rs.getString("company_id"));
                System.out.print(" - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAll() {
        try {
            String sql = "SELECT * FROM company";
            ResultSet rs = QueryExecutor.executeSelect(sql);

            while (rs.next()) {
                System.out.print("[");
                System.out.print(rs.getString("company_id"));
                System.out.print("] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void removeCompanyById(Long id) {
        String sql = "DELETE FROM company WHERE company_id = " + id;
        executeQuery(sql);
    }

    public static void insertStaff(Staff staff) {
        try {
            var connection = DatabaseConnection.connect();

            String sql = "INSERT INTO staff (firstname, lastname, salary, position_id) VALUES (?, ?, ?, ?)";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getFirstname());
            statement.setString(2, staff.getLastname());
            statement.setDouble(3, staff.getSalary());
            statement.setLong(4, staff.getPosition_id());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Added: " + staff);
            }

        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void getPositions() {
        try {
            String sql = "SELECT * FROM position";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                System.out.print("[");
                System.out.print(rs.getLong("position_id"));
                System.out.print("] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
