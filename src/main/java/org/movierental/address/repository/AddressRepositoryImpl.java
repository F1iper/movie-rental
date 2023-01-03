package org.movierental.address.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AddressRepositoryImpl implements AddressRepository {

    private final static String ADDRESS = "address";

    @Override
    public boolean insert(Address address) {
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
            return true;
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Address findById(Long id) {
        return executeFindById("SELECT * FROM " + ADDRESS + " WHERE address_id = " + id + ";");
    }

    @Override
    public List<Address> findByStreet(String street) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE street LIKE '%" + street + "%'");
    }

    @Override
    public List<Address> findByCity(String city) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE city LIKE '%" + city + "%'");
    }

    @Override
    public List<Address> findByState(String state) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE state LIKE '%" + state + "%'");
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE zip_code LIKE '%" + zipCode + "%'");
    }

    @Override
    public List<Address> findAll() {
        return execute("SELECT * FROM " + ADDRESS);
    }

    private List<Address> execute(String sql) {
        List<Address> addresses = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("address_id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zip_code");
                String phone = rs.getString("phone");

                addresses.add(new Address(id, street, city, state, zipCode, phone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return addresses;
    }

    private Address executeFindById(String sql) {
        Address address = new Address();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("address_id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zip_code");
                String phone = rs.getString("phone");

                address = new Address(id, street, city, state, zipCode, phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return address;
    }

    @Override
    public boolean removeById(Long id) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM " + ADDRESS + " WHERE address_id = " + id)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
