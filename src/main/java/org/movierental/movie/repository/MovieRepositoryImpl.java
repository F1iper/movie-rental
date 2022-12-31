package org.movierental.movie.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.movie.entity.Movie;
import org.movierental.repository.QueryExecutor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {

    private final static String MOVIES = "movies";
    private final static String MOVIE_TYPE = "movie_type";
    private final static String STATUS = "status";
    private final static String LANGUAGE = "language";

    @Override
    public boolean add(Movie movie) {
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
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Movie> findByName(String name) {
        return execute("SELECT * FROM " + MOVIES + " WHERE name LIKE '%" + name + "%'");

        // TODO: 12/31/2022 execute return list
    }

    @Override
    public Movie findById(Long id) {
        return executeFindById("SELECT * FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    @Override
    public List<Movie> findByCategoryId(Long categoryId) {
        return execute("SELECT * FROM " + MOVIES + " WHERE category_id = " + categoryId);

    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return execute("SELECT * FROM " + MOVIES + " WHERE release_year = " + year);
    }

    @Override
    public void updateName(Long id, String name) {

    }

    @Override
    public void updateDescription(Long id, String description) {

    }

    @Override
    public boolean removeById(Long id) {
        try (var queryExecution = new QueryExecutor()) {
            queryExecution.executeQuery("DELETE FROM " + MOVIES + " WHERE movie_id = " + id);
        }
        return true;
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
    public List<Movie> findByCostRange(int min, int max) {
        return execute("SELECT * FROM " + MOVIES + " BETWEEN " + min + " AND " + max);
    }

    @Override
    public List<Movie> findAll() {
        return execute("SELECT * FROM " + MOVIES);
    }

    private List<Movie> execute(String sql) {
        List<Movie> movies = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("movie_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int releaseYear = rs.getInt("release_year");
                int length = rs.getInt("length");
                Long languageId = rs.getLong("language_id");
                double cost = rs.getDouble("cost");
                Long statusId = rs.getLong("status_id");
                double rentalRate = rs.getLong("rental_rate");
                Long movieTypeId = rs.getLong("movie_type_id");

                movies.add(new Movie(id, title, description, releaseYear, length, languageId, cost, statusId, rentalRate, movieTypeId));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return movies;
    }

    private Movie executeFindById(String sql) {
        Movie movie = new Movie();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("movie_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int releaseYear = rs.getInt("release_year");
                int length = rs.getInt("length");
                Long languageId = rs.getLong("language_id");
                double cost = rs.getDouble("cost");
                Long statusId = rs.getLong("status_id");
                double rentalRate = rs.getLong("rental_rate");
                Long movieTypeId = rs.getLong("movie_type_id");

                movie = new Movie(id, title, description, releaseYear, length, languageId, cost, statusId, rentalRate, movieTypeId);
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return movie;
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
