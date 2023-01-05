package org.movierental.staff.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StaffRepositoryImpl implements StaffRepository {

    private final static String STAFF = "staff";
    private final static String POSITIONS = "positions";

    @Override
    public boolean insert(Staff staff) {
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
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Staff findById(Long id) {
        return executeFindById("SELECT * FROM " + STAFF + " WHERE staff_id = " + id);
    }

    @Override
    public List<Staff> findByFirstname(String firstname) {
        return execute("SELECT * FROM " + STAFF + " WHERE firstname like '%" + firstname + "%'");
    }

    @Override
    public List<Staff> findByLastname(String lastname) {
        return execute("SELECT * FROM " + STAFF + "  WHERE lastname like '%" + lastname + "%'");
    }

    @Override
    public List<Staff> findByPositionId(Long positionId) {
        return execute("SELECT * FROM " + STAFF + " WHERE position_id = " + positionId);
    }

    @Override
    public List<Staff> findBySalaryRange(int min, int max) {
        return execute("SELECT * FROM " + STAFF + " WHERE salary BETWEEN " + min + " AND " + max);
    }

    @Override
    public List<Staff> findAll() {
        return execute("SELECT * FROM " + STAFF);
    }

    @Override
    public List<Position> getPositions() {
        return fetchPositions("SELECT * FROM " + POSITIONS);

    }

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

    private Staff createStaff(ResultSet rs) throws SQLException {
        Long id = rs.getLong("staff_id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Double salary = rs.getDouble("salary");
        Long positionId = rs.getLong("position_id");
        Long branchId = rs.getLong("branch_id");

        return new Staff(id, firstname, lastname, salary, positionId, branchId);
    }

    private static List<Position> fetchPositions(String sql) {
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
            log.warn(e.getMessage());
        }
        return positions;
    }
}
