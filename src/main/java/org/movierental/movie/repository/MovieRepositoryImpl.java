package org.movierental.movie.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.movie.entity.Movie;
import org.movierental.repository.QueryExecutor;

import java.sql.SQLException;

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {

    private final static String MOVIES = "movies";
    private final static String MOVIE_TYPE = "movie_type";
    private final static String STATUS = "status";
    private final static String LANGUAGE = "language";

    @Override
    public Movie add(Movie movie) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement("INSERT INTO " + MOVIES +
                     " (title, description, release_year, length, language_id, cost, status_id, rental_rate, movie_type_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());
            statement.setInt(3, movie.getReleaseYear());
            statement.setInt(4, movie.getLength());
            statement.setLong(5, movie.getLanguageId());
            statement.setDouble(6, movie.getCost());
            statement.setLong(7, movie.getStatusId());
            statement.setDouble(8, movie.getRentalRate());
            statement.setLong(9, movie.getMovieTypeId());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New Movie [" + movie.getTitle() + "] has been inserted successfully."));
                System.out.println(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return movie;
    }

    @Override
    public void findByName(String name) {
        execute("SELECT * FROM " + MOVIES + " WHERE name LIKE '%" + name + "%'");
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    @Override
    public void findByCategoryId(Long categoryId) {

    }

    @Override
    public void findByReleaseYearBetween(int start, int end) {

    }

    @Override
    public void updateName(Long id, String name) {

    }

    @Override
    public void updateDescription(Long id, String description) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void findStatuses() {
       printStatuses("SELECT * FROM " + STATUS);
    }

    @Override
    public void findMovieTypes() {
        printMovieTypes("SELECT * FROM " + MOVIE_TYPE);
    }

    @Override
    public void findLanguages() {
        printLanguages("SELECT * FROM " + LANGUAGE);
    }

    @Override
    public void findByCostRange(int min, int max) {

    }

    @Override
    public void findAll() {
        execute("SELECT * FROM " + MOVIES);
    }

    private void execute(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("[" + rs.getString("movie_id") + "] - ");
                System.out.print(rs.getString("title"));
                System.out.print(rs.getString("description"));
                System.out.print(rs.getInt("release_year"));
                System.out.println(rs.getInt("length"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    private void printMovieTypes(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("[" + rs.getString("movie_type_id") + "] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    private void printStatuses(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("[" + rs.getString("status_id") + "] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    private void printLanguages(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("[" + rs.getString("language_id") + "] - ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }
}
