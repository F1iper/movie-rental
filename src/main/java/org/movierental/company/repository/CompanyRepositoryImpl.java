package org.movierental.company.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompanyRepositoryImpl implements CompanyRepository {

    private final static String COMPANY = "company";

    @Override
    public boolean insert(Company company) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement("INSERT INTO " + COMPANY + " (name) VALUES (?)")) {
            statement.setString(1, company.getName());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New company [" + company.getName() + "] has been inserted successfully."));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO: 12/30/2022 Provide specific exception + VALIDATION
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void update(long id, String newName) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE " + COMPANY + " SET name = '" + newName + "' WHERE company_id = " + id + ";");
            execute("SELECT * FROM " + COMPANY + " WHERE company_id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Company> findAll() {
        return execute("SELECT * FROM " + COMPANY);
    }

    @Override
    public List<Company> findByName(String companyName) {
        return execute("SELECT * FROM " + COMPANY + " WHERE NAME LIKE '%" + companyName + "%'");
    }

    @Override
    public boolean removeById(Long id) {
        try (var queryExecution = new QueryExecutor()) {

            queryExecution.executeQuery("DELETE FROM " + COMPANY + " WHERE company_id = " + id);
        }
        return true;
    }

    @Override
    public Company findById(Long id) {
        return executeFindById("SELECT * FROM " + COMPANY + " WHERE company_id = " + id);
    }

    private List<Company> execute(String sql) {
        List<Company> companies = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long companyId = rs.getLong("company_id");
                String name = rs.getString("name");

                companies.add(new Company(companyId, name));
            }
            return companies;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Company executeFindById(String sql) {
        Company company = null;
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                Long companyId = rs.getLong("company_id");
                String name = rs.getString("name");

                company = new Company(companyId, name);
            }
            return company;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
