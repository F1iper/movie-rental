package org.movierental.movie.repository;

import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MovieRepositoryImpl is a concrete implementation of the MovieRepository interface.
 * It provides methods for performing CRUD operations on Movie objects in the database.
 *
 * @author Filip Timofiejew
 */
public class MovieRepositoryImpl implements MovieRepository {

    private final static String MOVIES = "movies";
    private final static String MOVIE_TYPE = "movie_type";
    private final static String STATUS = "status";
    private final static String LANGUAGE = "language";

    /**
     * Add a movie to the database
     *
     * @param movie the movie to add
     * @return true if the movie was successfully added, false otherwise
     */
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

    /**
     * Find movies by title
     *
     * @param title the title of the movie to search for
     * @return a list of movies that match the given title
     */
    @Override
    public List<Movie> findByTitle(String title) {
        return execute("SELECT * FROM " + MOVIES + " WHERE title LIKE '%" + title + "%'");
    }

    /**
     * Find a movie by ID
     *
     * @param id the ID of the movie to search for
     * @return the movie that matches the given ID, or null if no match is found
     */
    @Override
    public Movie findById(Long id) {
        return executeFindById("SELECT * FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    /**
     * Find movies by movie type ID
     *
     * @param movieTypeId the ID of the movie type to search for
     * @return a list of movies that match the given movie type ID
     */
    @Override
    public List<Movie> findByMovieTypeId(Long movieTypeId) {
        return execute("SELECT * FROM " + MOVIES + " WHERE movie_type_id = " + movieTypeId);
    }

    /**
     * Find movies by release year
     *
     * @param year the release year of the movies to search for
     * @return a list of movies that were released in the given year
     */
    @Override
    public List<Movie> findByReleaseYear(int year) {
        return execute("SELECT * FROM " + MOVIES + " WHERE release_year = " + year);
    }

    /**
     * Update the title of a movie
     *
     * @param id    the ID of the movie to update
     * @param title the new title for the movie
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateTitle(Long id, String title) {
        return executeUpdate("UPDATE " + MOVIES + " SET title = '" + title + "' WHERE movie_id = " + id);
    }

    /**
     * Update the description of a movie
     *
     * @param id          the ID of the movie to update
     * @param description the new description for the movie
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateDescription(Long id, String description) {
        return executeUpdate("UPDATE " + MOVIES + " SET description = '" + description + "' WHERE movie_id = " + id);
    }

    /**
     * Remove a movie by ID
     *
     * @param id the ID of the movie to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return executeRemove("DELETE FROM " + MOVIES + " WHERE movie_id = " + id);
    }

    /**
     * Find all the statuses of the movie
     *
     * @return List of statuses available
     */
    @Override
    public List<Status> findStatuses() {
        return statuses("SELECT * FROM " + STATUS);
    }

    /**
     * Find all the movie types available
     *
     * @return List of movie types
     */
    @Override
    public List<MovieType> findMovieTypes() {
        return movieTypes("SELECT * FROM " + MOVIE_TYPE);
    }

    /**
     * Find all the languages available
     *
     * @return List of languages
     */
    @Override
    public List<Language> findLanguages() {
        return languages("SELECT * FROM " + LANGUAGE);
    }

    /**
     * Find all the movies by cost range
     *
     * @param min the minimum cost of the movie
     * @param max the maximum cost of the movie
     * @return List of movies with in the cost range
     */
    @Override
    public List<Movie> findByCostRange(double min, double max) {
        return execute("SELECT * FROM " + MOVIES + " WHERE cost BETWEEN " + min + " AND " + max);
    }

    /**
     * Find all the movies available in the system
     *
     * @return List of all movies
     */
    @Override
    public List<Movie> findAll() {
        return execute("SELECT * FROM " + MOVIES);
    }

    /**
     * This method is used to execute a given SQL query and retrieve a list of Movie
     * objects from the result set. The method makes use of a QueryExecutor,
     * a Connection, a Statement, and aResultSet to execute the query and extract the data.
     *
     * @param sql The SQL query to be executed
     * @return A list of Movie objects retrieved from the result set
     */
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
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Execute a query for updating the movie details
     *
     * @param sql sql query to be executed
     * @return true if the update was successful, false otherwise
     */
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

    /**
     * Execute a query for removing the movie from the system
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

    /**
     * Execute a query for finding the movie by id
     *
     * @param sql sql query to be executed
     * @return movie object corresponding to the given id
     */
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
        }
        return movie;
    }

    /**
     * Create movie object from the query result
     *
     * @param rs query result
     * @return movie object
     */
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

    /**
     * Execute a query for finding the movie types
     *
     * @param sql sql query to be executed
     * @return list of movie types
     */
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
        }
        return movieTypes;
    }

    /**
     * Execute a query for finding the statuses of the movies
     *
     * @param sql sql query to be executed
     * @return list of statuses
     */
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
        }
        return statuses;
    }

    /**
     * Execute a query for finding the languages of the movies
     *
     * @param sql sql query to be executed
     * @return list of languages
     */
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
        }
        return languages;
    }
}
