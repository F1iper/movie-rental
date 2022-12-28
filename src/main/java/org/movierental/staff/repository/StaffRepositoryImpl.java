package org.movierental.staff.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class StaffRepositoryImpl implements StaffRepository {

    private final static String STAFF = "staff";
    private final static String POSITIONS = "positions";

    @Override
    public void insert(Staff staff) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement(
                     "INSERT INTO " + STAFF + " (firstname, lastname, salary, position_id) VALUES (?, ?, ?, ?)")) {

            statement.setString(1, staff.getFirstname());
            statement.setString(2, staff.getLastname());
            statement.setDouble(3, staff.getSalary());
            statement.setLong(4, staff.getPositionId());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Added: " + staff);
            }

        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM " + STAFF + " WHERE staff_id = " + id + ";");
    }

    @Override
    public void findByFirstname(String firstname) {
        execute("SELECT * FROM " + STAFF + " WHERE firstname like '%" + firstname + "%';");
    }

    @Override
    public void findByLastname(String lastname) {
        execute("SELECT * FROM " + STAFF + "  WHERE lastname like '%" + lastname + "%';");
    }

    @Override
    public void findByPositionId(Long positionId) {
        execute("SELECT * FROM " + STAFF + " WHERE position_id = " + positionId + ";");
    }

    @Override
    public void findBySalaryRange(int min, int max) {
        execute("SELECT * FROM " + STAFF + " WHERE salary BETWEEN " + min + " AND " + max + ";");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM " + STAFF);
    }

    @Override
    public void getPositions() {
        printPositions("SELECT * FROM " + POSITIONS);

    }

    private void execute(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement(sql);
             var rs = statement.executeQuery()) {
            while (rs.next()) {
                print(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeById(Long id) {
        try (var queryExecution = new QueryExecutor()) {
            queryExecution.executeQuery("DELETE FROM " + STAFF + "  WHERE staff_id = " + id);
        }
    }

    private static void print(ResultSet rs) throws SQLException {
        System.out.print("[" + rs.getString("staff_id") + "] ");
        System.out.print(rs.getString("firstname"));
        System.out.print(", " + rs.getString("lastname"));
        System.out.print(", " + rs.getDouble("salary"));
        System.out.println(", " + rs.getLong("position_id"));
    }

    private static void printPositions(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("[" + rs.getString("position_id") + "] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }
}
