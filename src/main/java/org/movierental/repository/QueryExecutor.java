package org.movierental.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.address.entity.Address;
import org.movierental.company.entity.Company;
import org.movierental.staff.entity.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery) {
        try {
            var connection = DatabaseConnection.connect();
            var statement = connection.createStatement();
            statement.setFetchSize(Integer.MIN_VALUE);
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

    public static Company insertCompany(Company company) {
        try {
            Connection connection = DatabaseConnection.connect();
            String sql = "INSERT INTO company (name) values (?)";

            var statement = connection.prepareStatement(sql);
            statement.setString(1, company.getName());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New company [" + company.getName() + "] has been inserted successfully."));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return company;
    }

    public static void updateCompany(long id, String newName) {
        try(var connection = DatabaseConnection.connect()) {
            var statement = connection.createStatement();
            String sql = "UPDATE company SET name = '" + newName + "' WHERE company_id = " + id;
            statement.executeUpdate(sql);
            System.out.println("Company with id [" + id +"] name was updated to: " + newName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findCompanyById(Long id) {
        try {
            String sql = "SELECT * FROM company WHERE company_id = " + id;
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printCompanies(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchByCompanyName(String companyName) {
        try {
            String sql = "SELECT * FROM company WHERE NAME LIKE '" + companyName + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);

            while (rs.next()) {
                printCompanies(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAllCompanies() {
        try {
            String sql = "SELECT * FROM company";
            ResultSet rs = QueryExecutor.executeSelect(sql);

            while (rs.next()) {
                printCompanies(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void removeCompanyById(Long id) {
        String sql = "DELETE FROM company WHERE company_id = " + id;
        executeQuery(sql);
    }

    public static void getPositions() {
        try {
            String sql = "SELECT * FROM position";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                System.out.print("[" + rs.getLong("position_id") + "] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
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

    public static void searchAddressById(Long id) {
        try {
            String sql = "SELECT * FROM address WHERE address_id = " + id;
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAddressByStreet(String street) {
        try {
            String sql = "SELECT * FROM address WHERE street LIKE '" + street + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAddressByCity(String city) {
        try {
            String sql = "SELECT * FROM address WHERE city LIKE '" + city + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAddressByState(String state) {
        try {
            String sql = "SELECT * FROM address WHERE state LIKE '" + state + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAddressByZipCode(String zipCode) {
        try {
            String sql = "SELECT * FROM address WHERE zip_code LIKE '" + zipCode + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void searchAllAddresses() {
        try {
            String sql = "SELECT * FROM address";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
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

    public static void findStaffById(Long id) {
        try {
            String sql = "SELECT * FROM staff WHERE staff_id = " + id;
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void findStaffByFirstname(String firstname) {
        try {
            String sql = "SELECT * FROM staff WHERE firstname like '" + firstname + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void findStaffByLastname(String lastname) {
        try {
            String sql = "SELECT * FROM staff WHERE lastname like '" + lastname + "'";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void findStaffByPositionId(Long positionId) {
        try {
            String sql = "SELECT * FROM staff WHERE position_id = " + positionId;
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void findStaffBySalaryRange(int min, int max) {
        try {
            String sql = "SELECT * FROM staff WHERE salary BETWEEN " + min + " AND " + max;
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void findAllStaff() {
        try {
            String sql = "SELECT * FROM staff";
            ResultSet rs = QueryExecutor.executeSelect(sql);
            while (rs.next()) {
                printStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void removeStaffById(Long id) {
        String sql = "DELETE * FROM STAFF WHERE staff_id = " + id;
        executeQuery(sql);
    }

    private static void printCompanies(ResultSet rs) throws SQLException {
        System.out.print(rs.getString("company_id"));
        System.out.print(" - ");
        System.out.println(rs.getString("name"));
    }

    private static void printStaff(ResultSet rs) throws SQLException {
        System.out.print("[" + rs.getString("staff_id") + "] ");
        System.out.print(rs.getString("firstname"));
        System.out.print(", " + rs.getString("lastname"));
        System.out.print(", " + rs.getDouble("salary"));
        System.out.println(", " + rs.getLong("position_id"));
    }

    private static void printAddress(ResultSet rs) throws SQLException {
        System.out.print("[" + rs.getString("address_id") + "] ");
        System.out.print(rs.getString("street"));
        System.out.print(", " + rs.getString("city"));
        System.out.print(", " + rs.getString("state"));
        System.out.print(", " + rs.getString("zip_code"));
        System.out.println(", " + rs.getString("phone"));
    }
}
