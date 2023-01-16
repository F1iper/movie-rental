package org.movierental.company.repository;

import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private final static String COMPANY = "company";

    /**
     * This method is used to insert a company into the database
     *
     * @param company The company to be inserted
     * @return true if insertion is successful, false otherwise
     */
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
            // TODO: 1/16/2023 Provide specific exception + VALIDATION
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * This method is used to update the name of a company
     *
     * @param id      The id of the company
     * @param newName The new name of the company
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean update(long id, String newName) {
        boolean updated = false;
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate("UPDATE " + COMPANY + " SET name = '" + newName + "' WHERE company_id = " + id + ";");

            if (rowsAffected > 0) {
                updated = true;
            }
            execute("SELECT * FROM " + COMPANY + " WHERE company_id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return updated;
    }

    /**
     * This method is used to find all companies in the repository.
     *
     * @return A list of all companies in the repository
     */
    @Override
    public List<Company> findAll() {
        return execute("SELECT * FROM " + COMPANY);
    }

    /**
     * This method is used to find a companies by name
     *
     * @param name The name of the company to find
     * @return A list of companies with the specified name
     */
    @Override
    public List<Company> findByName(String name) {
        return execute("SELECT * FROM " + COMPANY + " WHERE NAME LIKE '%" + name + "%'");
    }
    /**

     This method is used to remove a company from the repository by its id
     @param id The id of the company to remove
     @return true if the removal is successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM " + COMPANY + " WHERE company_id = " + id)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    /**

     This method is used to find a company by its id
     @param id The id of the company to find
     @return The company object with the specified id, or null if not found
     */
    @Override
    public Company findById(Long id) {
        return executeFindById("SELECT * FROM " + COMPANY + " WHERE company_id = " + id);
    }

    /**
     * This method is used to execute a given SQL query and retrieve a list of Companies
     * objects from the result set. The method makes use of a QueryExecutor,
     * a Connection, a Statement, and aResultSet to execute the query and extract the data.
     *
     * @param sql The SQL query to be executed
     * @return A list of Company objects retrieved from the result set
     */
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

    /**
     * Execute a query for finding the company by id
     *
     * @param sql sql query to be executed
     * @return company object corresponding to the given id
     */
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
