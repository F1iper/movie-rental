package org.movierental.staff.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class StaffRepositoryImpl implements StaffRepository {

    private final static String STAFF_TABLE = "staff";
    private final static String POSITION = "position";

    private final QueryExecutor queryExecutor;

    @Override
    public void insert(Staff staff) {
        String sql = "INSERT INTO staff (firstname, lastname, salary, position_id) VALUES (?, ?, ?, ?)";
        try (var connection = queryExecutor.getConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, staff.getFirstname());
            statement.setString(2, staff.getLastname());
            statement.setDouble(3, staff.getSalary());
            statement.setLong(4, staff.getPosition_id());
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
        execute("SELECT * FROM staff WHERE staff_id = " + id + ";");
    }

    @Override
    public void findByFirstname(String firstname) {
        execute("SELECT * FROM " + STAFF_TABLE + " WHERE firstname like '%" + firstname + "%';");
    }

    @Override
    public void findByLastname(String lastname) {
        execute("SELECT * FROM " + STAFF_TABLE + "  WHERE lastname like '%" + lastname + "%';");
    }

    @Override
    public void findByPositionId(Long positionId) {
        execute("SELECT * FROM " + STAFF_TABLE + " WHERE position_id = " + positionId + ";");
    }

    @Override
    public void findBySalaryRange(int min, int max) {
        execute("SELECT * FROM " + STAFF_TABLE + " WHERE salary BETWEEN " + min + " AND " + max + ";");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM " + STAFF_TABLE);
    }

    @Override
    public void getPositions() {
        execute("SELECT * FROM " + POSITION);
    }

    private void execute(String sql) {
        try (QueryExecutor queryExecution = new QueryExecutor();
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
        String sql = "DELETE * FROM STAFF WHERE staff_id = " + id;
        queryExecutor.executeQuery(sql);
    }

    private static void print(ResultSet rs) throws SQLException {
        System.out.print("[" + rs.getString("staff_id") + "] ");
        System.out.print(rs.getString("firstname"));
        System.out.print(", " + rs.getString("lastname"));
        System.out.print(", " + rs.getDouble("salary"));
        System.out.println(", " + rs.getLong("position_id"));
    }
}
