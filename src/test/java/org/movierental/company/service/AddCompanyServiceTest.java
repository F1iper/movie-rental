package org.movierental.company.service;

import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCompanyServiceTest {

    @Rule
    public MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @Test
    public void getConnectionTest() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(), mySQLContainer.getPassword());

        //when
        boolean valid = connection.isValid(1000);

        //then
        assertTrue(valid);
    }
}