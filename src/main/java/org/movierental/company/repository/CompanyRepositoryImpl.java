package org.movierental.company.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class CompanyRepositoryImpl implements CompanyRepository {

    private final static String COMPANY = "company";

    @Override
    public Company insert(Company company) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement("INSERT INTO " + COMPANY + " (name) VALUES (?)")) {
            statement.setString(1, company.getName());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New company [" + company.getName() + "] has been inserted successfully."));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return company;
    }

    @Override
    public void update(long id, String newName) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE " + COMPANY + " SET name = '" + newName + "' WHERE company_id = " + id + ";");
            execute("SELECT * FROM company WHERE company_id = " + id + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM " + COMPANY + " WHERE company_id = " + id + ";");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM " + COMPANY + " ;");
    }

    @Override
    public void findByName(String companyName) {
        execute("SELECT * FROM " + COMPANY + " WHERE NAME LIKE '%" + companyName + "%'");
    }

    @Override
    public void removeById(Long id) {
        try (var queryExecution = new QueryExecutor()) {
            queryExecution.executeQuery("DELETE FROM " + COMPANY + " WHERE company_id = " + id);
        }
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
    public void print(ResultSet rs) {
        try {
            System.out.print(rs.getString("company_id"));
            System.out.print(" - ");
            System.out.println(rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
