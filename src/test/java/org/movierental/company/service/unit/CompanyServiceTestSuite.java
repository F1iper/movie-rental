package org.movierental.company.service.unit;

import org.junit.jupiter.api.*;
import org.movierental.company.entity.Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyServiceTestSuite {

    private static final String URL = "jdbc:h2:~/temp/test2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private final Connection connection;

    public CompanyServiceTestSuite() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @BeforeAll
    static void setup() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); var statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS company\n" + "(\n" + "    company_id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" + "    name       VARCHAR(255) NULL DEFAULT NULL\n" + ");");
        }
    }

    @AfterAll
    static void cleanUp() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); var statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE company");
        }
    }

    @Test
    @DisplayName("Table should be ampty")
    @Order(1)
    void shouldReturnEmptyTable() throws SQLException {
        //given
        List<Company> companies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement(); var rs = statement.executeQuery("SELECT * FROM company")) {
            if (rs.next()) {
                Long id = rs.getLong("company_id");
                String name = rs.getString("name");

                companies.add(new Company(id, name));
            }
        }

        //then
        assertTrue(companies.isEmpty());
        assertEquals(0, companies.size());
    }

    @Test
    @DisplayName("Insert test data")
    @Order(2)
    void testInsertData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement("insert into company (name) values " + "('LENOVO'), " + "('HP'), " + "('BOSCH'), " + "('NINTENDO');")) {

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
        List<Company> companies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement(); var rs = statement.executeQuery("SELECT * FROM company")) {

            while (rs.next()) {
                Long id = rs.getLong("company_id");
                String name = rs.getString("name");

                companies.add(new Company(id, name));
            }
        }
        //then
        assertAll(() -> assertEquals(4, companies.size()), () -> assertEquals("LENOVO", companies.get(0).getName()), () -> assertEquals("NINTENDO", companies.get(3).getName()));
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
        try (var statement = connection.prepareStatement("SELECT COUNT(company_id) FROM company"); var rs = statement.executeQuery()) {
            if (rs.next()) {
                initialCount = rs.getInt(1);
            }
        }
        try (var statement = connection.createStatement()) {
            statement.execute("DELETE FROM company WHERE company_id = " + id);
        }

        try (var statement = connection.createStatement(); var rs = statement.executeQuery("SELECT COUNT(company_id) FROM company")) {
            if (rs.next()) {
                finalCount = rs.getInt(1);
            }
        }

        //then
        assertNotEquals(initialCount, finalCount);
    }

    @Test
    @DisplayName("Find all by name")
    @Order(5)
    void shouldFindAllByName() throws SQLException {
        //given
        String providedName = "BOSCH";
        List<Company> companies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement(); var rs = statement.executeQuery("SELECT * FROM company WHERE name like '" + providedName + "';")) {
            while (rs.next()) {
                Long id = rs.getLong("company_id");
                String name = rs.getString("name");

                companies.add(new Company(id, name));
            }
        }

        //then
        assertAll(() -> assertNotNull(companies),
                () -> assertEquals(1, companies.size()),
                () -> assertEquals(providedName, companies.get(0).getName())
        );
    }
}