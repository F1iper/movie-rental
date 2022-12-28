package org.movierental.address.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AddressRepositoryImpl implements AddressRepository {

    private final static String ADDRESS = "address";

    @Override
    public void insert(Address address) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement("INSERT INTO " + ADDRESS + " (street, city, state, zip_code, phone) " +
                     "VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getState());
            statement.setString(4, address.getZip_code());
            statement.setString(5, address.getPhone());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New address has been inserted successfully.\n" + address));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM " + ADDRESS + " WHERE address_id = " + id + ";");
    }

    @Override
    public void findByStreet(String street) {
        execute("SELECT * FROM " + ADDRESS + " WHERE street LIKE '%" + street + "%'");
    }

    @Override
    public void findByCity(String city) {
        execute("SELECT * FROM " + ADDRESS + " WHERE city LIKE '%" + city + "%'");
    }

    @Override
    public void findByState(String state) {
        execute("SELECT * FROM " + ADDRESS + " WHERE state LIKE '%" + state + "%'");
    }

    @Override
    public void findByZipCode(String zipCode) {
        execute("SELECT * FROM " + ADDRESS + " WHERE zip_code LIKE '%" + zipCode + "%'");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM " + ADDRESS);
    }

    private void execute(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                print(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeById(Long id) {
        try (var queryExecution = new QueryExecutor()) {
            queryExecution.executeQuery("DELETE FROM " + ADDRESS + " WHERE address_id = " + id);
        }
    }

    private static void print(ResultSet rs) throws SQLException {
        System.out.print("[" + rs.getString("address_id") + "] ");
        System.out.print(rs.getString("street"));
        System.out.print(", " + rs.getString("city"));
        System.out.print(", " + rs.getString("state"));
        System.out.print(", " + rs.getString("zip_code"));
        System.out.println(", " + rs.getString("phone"));
    }
}
