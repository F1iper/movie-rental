package org.movierental.address.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final QueryExecutor queryExecutor;

    @Override
    public void insert(Address address) {
        try (var connection = queryExecutor.getConnection();
             var statement = connection.prepareStatement("INSERT INTO address (street, city, state, zip_code, phone) " +
                     "VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getState());
            statement.setString(4, address.getZip_code());
            statement.setString(5, address.getPhone());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New address [" + address + "] has been inserted successfully."));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM address WHERE address_id = " + id + ";");
    }

    @Override
    public void findByStreet(String street) {
        execute("SELECT * FROM address WHERE street LIKE '" + street + "';");
    }

    @Override
    public void findByCity(String city) {
        execute("SELECT * FROM address WHERE city LIKE '" + city + "';");
    }

    @Override
    public void findByState(String state) {
        execute("SELECT * FROM address WHERE state LIKE '" + state + "';");
    }

    @Override
    public void findByZipCode(String zipCode) {
        execute("SELECT * FROM address WHERE zip_code LIKE '" + zipCode + "';");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM address;");
    }

    private void execute(String sql) {
        try (QueryExecutor queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                print(rs);
            }
            queryExecutor.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(long id, String newName) {
        try (var connection = queryExecutor.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE address SET name = '" + newName + "' WHERE company_id = " + id);
            System.out.println("Company with id [" + id + "] name was updated to: " + newName);
        } catch (
                SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void removeById(Long id) {
        queryExecutor.executeQuery("DELETE * FROM address WHERE address_id = " + id);
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
