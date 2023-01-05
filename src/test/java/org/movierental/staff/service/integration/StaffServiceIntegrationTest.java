package org.movierental.staff.service.integration;

import org.junit.jupiter.api.*;
import org.movierental.address.entity.Address;
import org.movierental.address.entity.StaffAddress;
import org.movierental.branch.entity.Branch;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaffServiceIntegrationTest {
    private static final String URL = "jdbc:h2:~/temp/test2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private final Connection connection;

    public StaffServiceIntegrationTest() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @BeforeAll
    static void setup() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS branch\n" +
                            "(\n" +
                            "    branch_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    name      varchar(255)\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS positions\n" +
                            "(\n" +
                            "    position_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    name        VARCHAR(50)\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS address\n" +
                            "(\n" +
                            "    address_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    city       VARCHAR(255)       NOT NULL,\n" +
                            "    phone      VARCHAR(255)       NOT NULL,\n" +
                            "    state      VARCHAR(255)       NOT NULL,\n" +
                            "    street     VARCHAR(255)       NOT NULL,\n" +
                            "    zip_code   VARCHAR(255)       NOT NULL\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS staff\n" +
                            "(\n" +
                            "    staff_id    BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    firstname   VARCHAR(255)       NOT NULL,\n" +
                            "    lastname    VARCHAR(255)       NOT NULL,\n" +
                            "    salary      DOUBLE             NOT NULL,\n" +
                            "    branch_id   BIGINT             NOT NULL,\n" +
                            "    position_id BIGINT             NOT NULL,\n" +
                            "    FOREIGN KEY (branch_id) REFERENCES branch (branch_id),\n" +
                            "    FOREIGN KEY (position_id) REFERENCES positions (position_id)\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS staff_address\n" +
                            "(\n" +
                            "    staff_id   BIGINT NOT NULL,\n" +
                            "    address_id BIGINT NOT NULL,\n" +
                            "    FOREIGN KEY (staff_id) REFERENCES staff (staff_id),\n" +
                            "    FOREIGN KEY (address_id) REFERENCES address (address_id),\n" +
                            "    UNIQUE (staff_id, address_id)\n" +
                            ");");
        }
    }

    @AfterAll
    static void cleanUp() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE staff_address");
            statement.executeUpdate("DROP TABLE staff");
            statement.executeUpdate("DROP TABLE address");
            statement.executeUpdate("DROP TABLE positions");
            statement.executeUpdate("DROP TABLE branch");
        }
    }

    @Test
    @DisplayName("Branch table is empty")
    @Order(1)
    void shouldReturnEmptyBranchTable() throws SQLException {
        //given
        List<Branch> branches = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM branch")) {
            if (rs.next()) {
                branches.add(new Branch(rs.getLong("branch_id"), rs.getString("name")));
            }
        }
        //then
        assertTrue(branches.isEmpty());
        assertEquals(0, branches.size());
    }

    @Test
    @DisplayName("Position table is empty")
    @Order(2)
    void shouldReturnEmptyPositionTable() throws SQLException {
        //given
        List<Position> positions = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM positions")) {
            if (rs.next()) {
                positions.add(new Position(rs.getLong("position_id"), rs.getString("name")));
            }
        }
        //then
        assertTrue(positions.isEmpty());
        assertEquals(0, positions.size());
    }

    @Test
    @DisplayName("Address table is empty")
    @Order(3)
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
    @DisplayName("Staff table is empty")
    @Order(4)
    void shouldReturnEmptyStaffTable() throws SQLException {
        //given
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff")) {
            if (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        //then
        assertTrue(staffList.isEmpty());
        assertEquals(0, staffList.size());
    }

    @Test
    @DisplayName("Staff_address table is empty")
    @Order(5)
    void shouldReturnEmptyStaff_AddressTable() throws SQLException {
        //given
        List<StaffAddress> staffAddresses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff_address")) {
            if (rs.next()) {
                staffAddresses.add(new StaffAddress(rs.getLong("staff_id"), rs.getLong("address_id")));
            }
        }

        //then
        assertTrue(staffAddresses.isEmpty());
        assertEquals(0, staffAddresses.size());
    }

    @Test
    @DisplayName("Insert branch test data")
    @Order(6)
    void testInsertBranchData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO branch (name) " +
                        "VALUES ('FIRST'), ('SECOND'), ('THIRD');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(3, rows);
    }

    @Test
    @DisplayName("Insert position test data")
    @Order(7)
    void testInsertPositionData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO positions (name) " +
                        "VALUES ('BOSS'), ('LEADER'), ('JUNIOR');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(3, rows);
    }

    @Test
    @DisplayName("Insert address test data")
    @Order(8)
    void testInsertAddressData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "insert into address (city, phone, state, street, zip_code) values " +
                        "('Warsaw', '55314315', 'Mazowieckie', 'Mokotowska', '30-032'), " +
                        "('Warsaw', '64275362', 'Mazowieckie', 'Krakowska', '02-120'), " +
                        "('Krakow', '64275362', 'Malopolskie', 'Krakowska', '30-032');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(3, rows);
    }

    @Test
    @DisplayName("Insert staff test data")
    @Order(9)
    void testInsertStaffData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "insert into staff (firstname, lastname, salary, branch_id, position_id) values" +
                        "('Justyna', 'Kowalczyk', 5600, 1, 3), " +
                        "('Kamil', 'Bro', 2600, 1, 2), " +
                        "('Jakub', 'Bro', 5500, 2, 3), " +
                        "('Maria', 'Tutek', 5000, 2, 1);")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(4, rows);
    }

    @Test
    @DisplayName("Insert staff_address test data")
    @Order(10)
    void testInsertStaff_AddressData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "insert into staff_address (staff_id, address_id) values " +
                        "(1, 1), " +
                        "(2, 1), " +
                        "(3, 2), " +
                        "(3, 3);")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(4, rows);
    }

    @Test
    @DisplayName("Find all staff")
    @Order(11)
    void shouldFindAllStaff() throws SQLException {
        //given
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff")) {

            while (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        //then
        assertAll(
                () -> assertEquals(4, staffList.size()),
                () -> assertEquals("Justyna", staffList.get(0).getFirstname()),
                () -> assertEquals("Tutek", staffList.get(3).getLastname()),
                () -> assertEquals(5500, staffList.get(2).getSalary())
        );
    }

    @Test
    @DisplayName("Find staff by ID")
    @Order(12)
    void shouldFindStaffById() throws SQLException {
        //given
        Staff staff = null;
        long id = 1L;

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff WHERE staff_id = " + id)) {

            if (rs.next()) {
                staff = new Staff(rs.getLong("staff_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDouble("salary"),
                        rs.getLong("position_id"),
                        rs.getLong("branch_id"));
            }
        }

        //then
        assertEquals(1, staff.getStaffId());
        assertEquals("Justyna", staff.getFirstname());
        assertEquals("Kowalczyk", staff.getLastname());
    }

    @Test
    @DisplayName("Find staff by firstname")
    @Order(13)
    void shouldFindAllStaffByFirstname() throws SQLException {
        //given
        String firstname = "Justyna";
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff WHERE firstname like '" + firstname + "'")) {
            while (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        //then
        assertNotNull(staffList);
        assertEquals(1, staffList.size());
        assertEquals(firstname, staffList.get(0).getFirstname());
    }

    @Test
    @DisplayName("Find staff by lastname")
    @Order(14)
    void shouldFindAllStaffByLastname() throws SQLException {
        //given
        String lastname = "Tutek";
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff WHERE lastname like '" + lastname + "'")) {
            while (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        //then
        assertNotNull(staffList);
        assertEquals(1, staffList.size());
        assertEquals(lastname, staffList.get(0).getLastname());
    }

    @Test
    @DisplayName("Find staff by salary range")
    @Order(15)
    void shouldFindStaffBySalaryRange() throws SQLException {
        //given
        double min = 2600;
        double max = 5500;
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM staff WHERE salary BETWEEN " + min + " AND " + max)) {
            while (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        System.out.println(staffList);
        //then
        assertNotNull(staffList);
        assertEquals(3, staffList.size());
    }

    @Test
    @DisplayName("Find staff by position ID")
    @Order(16)
    void shouldFindStaffByPositionId() throws SQLException {
        //given
        long positionId = 3;
        List<Staff> staffList = new ArrayList<>();

        //when
        try (var statement = connection.prepareStatement(
                "SELECT s.* FROM staff s INNER JOIN positions p ON s.position_id = p.position_id WHERE p.position_id = " + positionId)) {
            var rs = statement.executeQuery();
            while (rs.next()) {
                addStaffToList(staffList, rs);
            }
        }

        //then
        assertNotNull(staffList);
        assertEquals(2, staffList.size());
        assertEquals(positionId, staffList.get(1).getPositionId());
    }

    private void addStaffToList(List<Staff> staffList, ResultSet rs) throws SQLException {
        Long id = rs.getLong("staff_id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Double salary = rs.getDouble("salary");
        Long positionId = rs.getLong("position_id");
        Long branchId = rs.getLong("branch_id");

        staffList.add(new Staff(id, firstname, lastname, salary, positionId, branchId));
    }


    private void addAddressToList(List<Address> addresses, ResultSet rs) throws SQLException {
        Long id = rs.getLong("address_id");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String zipCode = rs.getString("zip_code");
        String phone = rs.getString("phone");

        addresses.add(new Address(id, street, city, state, zipCode, phone));
    }
}
