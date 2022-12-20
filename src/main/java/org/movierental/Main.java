package org.movierental;

import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {


        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM ADDRESS");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("city"));
            System.out.println(resultSet.getString("phone"));
            System.out.println(resultSet.getString("zip_code"));
        }
    }
}