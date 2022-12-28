package org.movierental.branch.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.branch.entity.Branch;
import org.movierental.movie.entity.Movie;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BranchRepositoryImpl implements BranchRepository {

    private static final String BRANCH = "branch";

    @Override
    public void insert(Branch branch) {
        try (var queryExecution = new QueryExecutor(); var connection = queryExecution.getConnection(); var statement = connection.prepareStatement("INSERT INTO " + BRANCH)) {
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("New branch has been inserted successfully.\nID: [" + branch.getBranchId() + "]");
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM " + BRANCH + " WHERE branch_id = " + id);
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

    private void print(ResultSet rs) throws SQLException {
        Long branchId = rs.getLong("branch_id");
        String name = rs.getString("name");
        Branch branch = new Branch(branchId, name);
        System.out.println(branch);
    }

//    private List<Movie> getMoviesForBranch(Long branchId) {
//        List<Movie> movies = new ArrayList<>();
//        try (var queryExecution = new QueryExecutor();
//             var connection = queryExecution.getConnection();
//             var statement = connection.createStatement();
//             var rs = statement.executeQuery("SELECT * FROM movies WHERE branch_id = " + branchId)) {
//            while (rs.next()) {
//                Long movieId = rs.getLong("movie_id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int releaseYear = rs.getInt("release_year");
//                int length = rs.getInt("length");
//                Long languageId = rs.getLong("language_id");
//                Long categoryId = rs.getLong("category_id");
//                double cost = rs.getDouble("cost");
//                Long statusId = rs.getLong("status_id");
//                double rentalRate = rs.getDouble("rental_rate");
//                Movie movie = new Movie(movieId, title, description, releaseYear, length, languageId, categoryId, cost, statusId, rentalRate);
//                movies.add(movie);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return movies;
//    }

    @Override
    public void findAllStaffByBranchId(Long id) {
    }

    @Override
    public void findAll() {

    }

    @Override
    public void removeById(Long id) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + BRANCH + " WHERE branch_id = " + id);
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }
}
