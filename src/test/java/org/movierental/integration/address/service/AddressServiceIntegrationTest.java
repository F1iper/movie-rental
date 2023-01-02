package org.movierental.integration.address.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;


@ExtendWith(MockitoExtension.class)
public class AddressServiceIntegrationTest {

    @BeforeEach
    void setUp() {
        String url = "jdbc:h2:mem:test";
        try(var connection = DriverManager.getConnection(url)) {
            System.out.println("connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsert() throws SQLException {
        //given


    }
}