package org.movierental.movie.repository;

import lombok.extern.slf4j.Slf4j;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
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
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return execute("SELECT * FROM " + MOVIES + " WHERE title LIKE '%" + title + "%'");
    }

    @Override
    public Movie findById(Long id) {
        return executeFindById("SELECT * FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    @Override
    public List<Movie> findByMovieTypeId(Long movieTypeId) {
        return execute("SELECT * FROM " + MOVIES + " WHERE movie_type_id = " + movieTypeId);
    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return execute("SELECT * FROM " + MOVIES + " WHERE release_year = " + year);
    }

    @Override
    public boolean updateTitle(Long id, String title) {
        return executeUpdate("UPDATE " + MOVIES + " SET title = '" + title + "' WHERE movie_id = " + id);
    }

    @Override
    public boolean updateDescription(Long id, String description) {
        return executeUpdate("UPDATE " + MOVIES + " SET description = '" + description + "' WHERE movie_id = " + id);
    }

    @Override
    public boolean removeById(Long id) {
        return executeRemove("DELETE FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    @Override
    public List<Status> findStatuses() {
        return statuses("SELECT * FROM " + STATUS);
    }

    @Override
    public List<MovieType> findMovieTypes() {
        return movieTypes("SELECT * FROM " + MOVIE_TYPE);
    }

    @Override
    public List<Language> findLanguages() {
        return languages("SELECT * FROM " + LANGUAGE);
    }

    @Override
    public List<Movie> findByCostRange(double min, double max) {
        return execute("SELECT * FROM " + MOVIES + " WHERE cost BETWEEN " + min + " AND " + max);
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
                movies.add(movieCreator(rs));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return movies;
    }

    private boolean executeUpdate(String sql) {
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.prepareStatement(sql)) {
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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

    private Movie executeFindById(String sql) {
        Movie movie = new Movie();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                movie = movieCreator(rs);
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return movie;
    }

    private Movie movieCreator(ResultSet rs) throws SQLException {
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

        return new Movie(id, title, description, releaseYear, length, languageId, cost, statusId, rentalRate, movieTypeId);
    }


    private List<MovieType> movieTypes(String sql) {
        List<MovieType> movieTypes = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("movie_type_id");
                String name = rs.getString("name");

                movieTypes.add(new MovieType(id, name));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return movieTypes;
    }

    private List<Status> statuses(String sql) {
        List<Status> statuses = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("status_id");
                String name = rs.getString("name");

                statuses.add(new Status(id, name));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return statuses;
    }

    private List<Language> languages(String sql) {
        List<Language> languages = new ArrayList<>();
        try (var queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("language_id");
                String name = rs.getString("name");

                languages.add(new Language(id, name));
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return languages;
    }
}
