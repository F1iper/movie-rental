package org.movierental.address.repository;

import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {

    private final static String ADDRESS = "address";

    /**
     * Add a address to the database
     *
     * @param address the address to add
     * @return true if the address was successfully added, false otherwise
     */
    @Override
    public boolean insert(Address address) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var preparedStatement = connection.prepareStatement("INSERT INTO " + ADDRESS + " (street, city, state, zip_code, phone) " +
                     "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getState());
            preparedStatement.setString(4, address.getZip_code());
            preparedStatement.setString(5, address.getPhone());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Find a address by ID
     *
     * @param id the ID of the address to search for
     * @return the address that matches the given ID, or null if no match is found
     */
    @Override
    public Address findById(Long id) {
        return executeFindById("SELECT * FROM " + ADDRESS + " WHERE address_id = " + id + ";");
    }

    /**
     * Find addresses by street
     *
     * @param street the street of the address to search for
     * @return a list of addresses that match the given street
     */
    @Override
    public List<Address> findByStreet(String street) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE street LIKE '%" + street + "%'");
    }

    /**
     * Find addresses by city
     *
     * @param city the city of the address to search for
     * @return a list of addresses that match the given city
     */
    @Override
    public List<Address> findByCity(String city) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE city LIKE '%" + city + "%'");
    }

    /**
     * Find addresses by state
     *
     * @param state the state of the address to search for
     * @return a list of addresses that match the given state
     */
    @Override
    public List<Address> findByState(String state) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE state LIKE '%" + state + "%'");
    }

    /**
     * Find addresses by zip code
     *
     * @param zipCode the zip code of the address to search for
     * @return a list of addresses that match the given zip code
     */
    @Override
    public List<Address> findByZipCode(String zipCode) {
        return execute("SELECT * FROM " + ADDRESS + " WHERE zip_code LIKE '%" + zipCode + "%'");
    }

    /**
     * Find all the addresses available in the system
     *
     * @return List of all addresses
     */
    @Override
    public List<Address> findAll() {
        return execute("SELECT * FROM " + ADDRESS);
    }

    /**
     * Remove an address by ID
     *
     * @param id the ID of the address to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return executeRemove("DELETE FROM " + ADDRESS + " WHERE address_id = " + id);
    }

    /**
     * This method is used to execute a given SQL query and retrieve a list of Address
     * objects from the result set. The method makes use of a QueryExecutor,
     * a Connection, a Statement, and aResultSet to execute the query and extract the data.
     *
     * @param sql The SQL query to be executed
     * @return A list of Address objects retrieved from the result set
     */
    private List<Address> execute(String sql) {
        List<Address> addresses = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                addresses.add(createAddress(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return addresses;
    }

    /**
     * Execute a query for finding the address by id
     *
     * @param sql sql query to be executed
     * @return address object corresponding to the given id
     */
    private Address executeFindById(String sql) {
        Address address = new Address();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                address = createAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return address;
    }

    /**
     * Create address object from the query result
     *
     * @param rs query result
     * @return address object
     */
    private Address createAddress(ResultSet rs) throws SQLException {
        Long id = rs.getLong("address_id");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String zipCode = rs.getString("zip_code");
        String phone = rs.getString("phone");

        return new Address(id, street, city, state, zipCode, phone);
    }

    /**
     * Execute a query for removing the address from the system
     *
     * @param sql sql query to be executed
     * @return true if the removal was successful, false otherwise
     */
    private boolean executeRemove(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
