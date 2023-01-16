package org.movierental.staff.repository;

import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StaffRepositoryImpl class provides the implementation for StaffRepository interface.
 * It uses the QueryExecutor class to perform the CRUD operations on Staff objects in the database.
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
public class StaffRepositoryImpl implements StaffRepository {

    /**
     * Constants for the table names in the database
     */
    private final static String STAFF = "staff";
    private final static String POSITIONS = "positions";

    /**
     * This method is used to add a new Staff object to the database.
     *
     * @param staff Staff object to be added.
     * @return true if the Staff object is successfully added, false otherwise.
     */
    @Override
    public boolean add(Staff staff) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + STAFF + " (firstname, lastname, salary, position_id) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, staff.getFirstname());
            preparedStatement.setString(2, staff.getLastname());
            preparedStatement.setDouble(3, staff.getSalary());
            preparedStatement.setLong(4, staff.getPositionId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * This method is used to return a Staff object
     * from the database by its ID.
     *
     * @param id ID of the Staff object to be retrieved.
     * @return Staff object with the given ID.
     */
    @Override
    public Staff findById(Long id) {
        return executeFindById("SELECT * FROM " + STAFF + " WHERE staff_id = " + id);
    }

    /**
     * This method is used to return a list of Staff objects from the database with the given first name.
     *
     * @param firstname First name of the Staff objects to be retrieved.
     * @return List of Staff objects with the given first name.
     */
    @Override
    public List<Staff> findByFirstname(String firstname) {
        return execute("SELECT * FROM " + STAFF + " WHERE firstname like '%" + firstname + "%'");
    }

    /**
     * This method is used to return a list of Staff objects from the database with the given last name.
     *
     * @param lastname Last name of the Staff objects to be retrieved.
     * @return List of Staff objects with the given last name.
     */
    @Override
    public List<Staff> findByLastname(String lastname) {
        return execute("SELECT * FROM " + STAFF + "  WHERE lastname like '%" + lastname + "%'");
    }

    /**
     * This method is used to return a list of Staff objects from the database with the given position ID.
     *
     * @param positionId ID of the Position of the Staff objects to be retrieved.
     * @return List of Staff objects with the given position ID.
     */
    @Override
    public List<Staff> findByPositionId(Long positionId) {
        return execute("SELECT * FROM " + STAFF + " WHERE position_id = " + positionId);
    }

    /**
     * This method is used to return a list of Staff objects from the database with salary within the given range.
     *
     * @param min Minimum salary of the Staff objects to be retrieved.
     * @param max Maximum salary of the Staff objects to be retrieved.
     * @return List of Staff objects with salary within the given range.
     */
    @Override
    public List<Staff> findBySalaryRange(double min, double max) {
        return execute("SELECT * FROM " + STAFF + " WHERE salary BETWEEN " + min + " AND " + max);
    }

    /**
     * This method is used to return a list of all Staff objects from the database.
     *
     * @return List of all Staff objects.
     */
    @Override
    public List<Staff> findAll() {
        return execute("SELECT * FROM " + STAFF);
    }

    /**
     * This method is used to return a list of all Position objects from the database.
     *
     * @return List of all Position objects.
     */
    @Override
    public List<Position> getPositions() {
        return fetchPositions("SELECT * FROM " + POSITIONS);

    }

    /**
     * Internal helper method to execute a query and return a list of Staff objects.
     *
     * @param sql SQL query to be executed.
     * @return List of Staff objects returned by the query.
     */
    private List<Staff> execute(String sql) {
        List<Staff> staffList = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement(sql);
             var rs = statement.executeQuery()) {
            while (rs.next()) {
                staffList.add(createStaff(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return staffList;
    }

    /**
     * Internal helper method to execute a query and return a single Staff object by ID.
     *
     * @param sql SQL query to be executed.
     * @return Staff object returned by the query.
     */
    private Staff executeFindById(String sql) {
        Staff staff = new Staff();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement(sql);
             var rs = statement.executeQuery()) {
            if (rs.next()) {
                staff = createStaff(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return staff;
    }

    /**
     * This method is used to return a list of Staff objects from the database with the given branch ID.
     *
     * @param branchId ID of the branch that the Staff objects are assigned to.
     * @return List of Staff objects that are assigned to given branch ID.
     */
    @Override
    public List<Staff> findByBranchId(Long branchId) {
        return execute("SELECT * FROM " + STAFF + " WHERE branch_id = " + branchId);
    }

    /**
     * This is helper method to remove a Staff object from the database by its ID.
     *
     * @param id ID of the Staff object to be removed.
     * @return true if the Staff object is successfully removed, false otherwise.
     */
    @Override
    public boolean removeById(Long id) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM " + STAFF + "  WHERE staff_id = " + id)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to create a Staff object from a ResultSet object.
     *
     * @param rs ResultSet object containing the data for the Staff object.
     * @return Staff object created from the data in the ResultSet.
     * @throws SQLException If an error occurs while retrieving data from the ResultSet.
     */
    private Staff createStaff(ResultSet rs) throws SQLException {
        Long id = rs.getLong("staff_id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Double salary = rs.getDouble("salary");
        Long positionId = rs.getLong("position_id");
        Long branchId = rs.getLong("branch_id");

        return new Staff(id, firstname, lastname, salary, positionId, branchId);
    }

    /**
     * This method is used to create a list of Position objects from a query.
     *
     * @param sql SQL query to be executed.
     * @return List of Position objects returned by the query.
     */
    private List<Position> fetchPositions(String sql) {
        List<Position> positions = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("position_id");
                String name = rs.getString("name");

                positions.add(new Position(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }
}
