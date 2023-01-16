package org.movierental.branch.repository;

import org.movierental.branch.entity.Branch;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {

    private static final String BRANCH = "branch";


    /**
     * Add a branch to the database
     *
     * @param branch the address to add
     * @return true if the branch was successfully added, false otherwise
     */
    @Override
    public boolean insert(Branch branch) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement("INSERT INTO " + BRANCH)) {
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Find a branch by ID
     *
     * @param id the ID of the branch to search for
     * @return the branch that matches the given ID, or throw RuntimeException
     */
    @Override
    public Branch findById(Long id) {
        return executeFindById("SELECT * FROM " + BRANCH + " WHERE branch_id = " + id);
    }

    /**
     * Execute a query for finding the branch by id
     *
     * @param sql sql query to be executed
     * @return branch object corresponding to the given id
     */
    private Branch executeFindById(String sql) {
        Branch branch = new Branch();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                branch = createBranch(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return branch;
    }

    /**
     * This method is used to execute a given SQL query and retrieve a list of Branch
     * objects from the result set. The method makes use of a QueryExecutor,
     * a Connection, a Statement, and aResultSet to execute the query and extract the data.
     *
     * @param sql The SQL query to be executed
     * @return A list of Branch objects retrieved from the result set
     */
    private List<Branch> execute(String sql) {
        List<Branch> branches = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                branches.add(createBranch(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return branches;
    }

    /**
     * Find all the branches available in the system
     *
     * @return List of all branches
     */
    @Override
    public List<Branch> findAll() {
        return execute("SELECT * FROM " + BRANCH);
    }

    /**
     * Remove a branch by ID
     *
     * @param id the ID of the branch to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return executeRemove("DELETE FROM " + BRANCH + " WHERE branch_id = " + id);
    }

    /**
     * Create branch object from the query result
     *
     * @param rs query result
     * @return branch object
     */
    private Branch createBranch(ResultSet rs) throws SQLException {
        Long id = rs.getLong("branch_id");
        String name = rs.getString("name");

        return new Branch(id, name);
    }

    /**
     * Execute a query for removing the branch from the system
     *
     * @param sql sql query to be executed
     * @return true if the removal was successful, false otherwise
     */
    private boolean executeRemove(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
