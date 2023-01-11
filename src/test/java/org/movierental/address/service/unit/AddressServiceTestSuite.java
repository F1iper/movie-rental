package org.movierental.address.service.unit;

import org.junit.jupiter.api.*;
import org.movierental.address.entity.Address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressServiceTestSuite {
    private static final String URL = "jdbc:h2:~/temp/test2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private final Connection connection;

    public AddressServiceTestSuite() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @BeforeAll
    static void setup() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS address\n" +
                    "(\n" +
                    "    address_id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    city       VARCHAR(255) NOT NULL,\n" +
                    "    phone      VARCHAR(255) NOT NULL,\n" +
                    "    state      VARCHAR(255) NOT NULL,\n" +
                    "    street     VARCHAR(255) NOT NULL,\n" +
                    "    zip_code   VARCHAR(255) NOT NULL\n" +
                    ");\n");
        }
    }

    @AfterAll
    static void cleanUp() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE address");
        }
    }

    @Test
    @DisplayName("Address table should be empty")
    @Order(1)
    void shouldReturnEmptyAddressTable() throws SQLException {
        //given
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM address")) {
            if (rs.next()) {
                addAddressToList(addresses, rs);
            }
        }

        //then
        assertTrue(addresses.isEmpty());
        assertEquals(0, addresses.size());
    }

    @Test
    @DisplayName("Insert test data")
    @Order(2)
    void testInsertData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "insert into address (city, phone, state, street, zip_code) values " +
                        "('Poznan', '500 300 500', 'Greater Poland', 'Poznanska', '99-100'), " +
                        "('Warsaw', '300 500', 'Greater Poland', 'Mokotowska', '02-508')," +
                        "('Warsaw', '+48 500 600 600', 'Masovian', 'Mokotowska', '05-120')," +
                        "('Krakow', '+48 200 300 600', 'Greater Poland', 'Krakowska', '99-100')")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(4, rows);
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    void shouldFindAll() throws SQLException {
        //given
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM address")) {

            while (rs.next()) {
               addAddressToList(addresses, rs);
            }
        }

        //then
        assertAll(
                () -> assertEquals(4, addresses.size()),
                () -> assertEquals("Poznan", addresses.get(0).getCity()),
                () -> assertEquals("02-508", addresses.get(1).getZip_code()),
                () -> assertEquals("+48 500 600 600", addresses.get(2).getPhone()),
                () -> assertEquals("Krakowska", addresses.get(3).getStreet())
        );
    }

    @Test
    @DisplayName("Remove by id")
    @Order(4)
    void shouldRemoveById() throws SQLException {
        //given
        long id = 1;
        int initialCount = 0;
        int finalCount = 0;

        //when
        try (var statement = connection.prepareStatement(
                "SELECT COUNT(address_id) FROM address");
             var rs = statement.executeQuery()) {
            if (rs.next()) {
                initialCount = rs.getInt(1);
            }
        }
        try (var statement = connection.createStatement()) {
            statement.execute("DELETE FROM address WHERE address_id = " + id);
        }

        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT COUNT(address_id) FROM address")) {
            if (rs.next()) {
                finalCount = rs.getInt(1);
            }
        }

        //then
        assertNotEquals(initialCount, finalCount);
    }

    @Test
    @DisplayName("Find all by city")
    @Order(5)
    void shouldFindAllByCity() throws SQLException {
        //given
        String providedCity = "Warsaw";
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery(
                     "SELECT * FROM address WHERE city like '" + providedCity + "';")) {
            while (rs.next()) {
                addAddressToList(addresses, rs);
            }
        }

        //then
        assertAll(
                () -> assertNotNull(addresses),
                () -> assertEquals(2, addresses.size()),
                () -> assertEquals(providedCity, addresses.get(0).getCity()),
                () -> assertEquals(providedCity, addresses.get(1).getCity())
        );
    }

    @Test
    @DisplayName("Find all by street")
    @Order(6)
    void shouldFindAllByStreet() throws SQLException {
        //given
        String providedStreet = "Mokotowska";
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery(
                     "SELECT * FROM address WHERE street like '" + providedStreet + "';")) {
            while (rs.next()) {
                addAddressToList(addresses, rs);
            }
        }

        //then
        assertAll(
                () -> assertNotNull(addresses),
                () -> assertEquals(2, addresses.size()),
                () -> assertEquals(providedStreet, addresses.get(0).getStreet()),
                () -> assertEquals(providedStreet, addresses.get(1).getStreet())
        );
    }

    @Test
    @DisplayName("Find all by zip code")
    @Order(7)
    void shouldFindAllByZipCode() throws SQLException {
        //given
        String providedZipCode = "99-100";
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery(
                     "SELECT * FROM address WHERE zip_code like '" + providedZipCode + "';")) {
            while (rs.next()) {
                addAddressToList(addresses, rs);
            }
        }

        //then
        assertAll(
                () -> assertNotNull(addresses),
                () -> assertEquals(1, addresses.size()),
                () -> assertEquals(providedZipCode, addresses.get(0).getZip_code())
        );
    }

    @Test
    @DisplayName("Find all by state")
    @Order(8)
    void shouldFindAllByState() throws SQLException {
        //given
        String providedState = "Greater Poland";
        List<Address> addresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery(
                     "SELECT * FROM address WHERE state like '" + providedState + "';")) {
            while (rs.next()) {
                addAddressToList(addresses, rs);
            }
        }

        //then
        assertAll(
                () -> assertNotNull(addresses),
                () -> assertEquals(2, addresses.size()),
                () -> assertEquals(providedState, addresses.get(0).getState()),
                () -> assertEquals(providedState, addresses.get(1).getState())
        );
    }

    private void addAddressToList(List<Address> addresses, ResultSet rs) throws SQLException {
        long id = rs.getLong("address_id");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String zipCode = rs.getString("zip_code");
        String phone = rs.getString("phone");

        addresses.add(new Address(id, street, city, state, zipCode, phone));
    }
}