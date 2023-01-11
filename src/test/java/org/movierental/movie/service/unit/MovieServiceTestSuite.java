package org.movierental.movie.service.unit;

import org.junit.jupiter.api.*;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieServiceTestSuite {
    private static final String URL = "jdbc:h2:~/temp/test2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private final Connection connection;

    public MovieServiceTestSuite() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @BeforeAll
    static void setup() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS language\n" +
                            "(\n" +
                            "    language_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    name        VARCHAR(255)       NOT NULL\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS status\n" +
                            "(\n" +
                            "    status_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    name      VARCHAR(255)       NOT NULL\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS movie_type\n" +
                            "(\n" +
                            "    movie_type_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            "    name          VARCHAR(50)\n" +
                            ");");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS movies\n" +
                            "(\n" +
                            "    movie_id      BIGINT           NOT NULL AUTO_INCREMENT,\n" +
                            "    title         VARCHAR(255)     NOT NULL,\n" +
                            "    description   TEXT,\n" +
                            "    release_year  INT              NOT NULL,\n" +
                            "    length        INT              NOT NULL,\n" +
                            "    language_id   BIGINT           NOT NULL,\n" +
                            "    movie_type_id BIGINT           NOT NULL,\n" +
                            "    cost          DOUBLE PRECISION NOT NULL,\n" +
                            "    status_id     BIGINT           NOT NULL,\n" +
                            "    rental_rate   DOUBLE PRECISION NOT NULL,\n" +
                            "    PRIMARY KEY (movie_id),\n" +
                            "    FOREIGN KEY (language_id) REFERENCES language (language_id),\n" +
                            "    FOREIGN KEY (movie_type_id) REFERENCES movie_type (movie_type_id),\n" +
                            "    FOREIGN KEY (status_id) REFERENCES status (status_id)\n" +
                            ");");
        }
    }

    @AfterAll
    static void cleanUp() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE movies");
            statement.executeUpdate("DROP TABLE movie_type");
            statement.executeUpdate("DROP TABLE status");
            statement.executeUpdate("DROP TABLE language");
        }
    }

    @Test
    @DisplayName("Movie table is empty")
    @Order(1)
    void shouldReturnEmptyMovieTable() throws SQLException {
        //given
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies")) {
            if (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }
        //then
        assertTrue(movies.isEmpty());
        assertEquals(0, movies.size());
    }

    @Test
    @DisplayName("Language table is empty")
    @Order(2)
    void shouldReturnEmptyLanguageTable() throws SQLException {
        //given
        List<Language> languages = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM language")) {
            if (rs.next()) {
                Long id = rs.getLong("language_id");
                String name = rs.getString("name");
                languages.add(new Language(id, name));
            }
        }

        //then
        assertTrue(languages.isEmpty());
        assertEquals(0, languages.size());
    }

    @Test
    @DisplayName("Status table is empty")
    @Order(3)
    void shouldReturnEmptyStatusTable() throws SQLException {
        //given
        List<Status> statuses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM status")) {
            if (rs.next()) {
                Long id = rs.getLong("status_id");
                String name = rs.getString("name");
                statuses.add(new Status(id, name));
            }
        }

        //then
        assertTrue(statuses.isEmpty());
        assertEquals(0, statuses.size());
    }


    @Test
    @DisplayName("Movie_type table is empty")
    @Order(4)
    void shouldReturnEmptyMovieTypeTable() throws SQLException {
        //given
        List<MovieType> movieTypes = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movie_type")) {
            if (rs.next()) {
                Long id = rs.getLong("movie_type_id");
                String name = rs.getString("name");
                movieTypes.add(new MovieType(id, name));
            }
        }

        //then
        assertTrue(movieTypes.isEmpty());
        assertEquals(0, movieTypes.size());
    }

    @Test
    @DisplayName("Insert language test data")
    @Order(5)
    void testInsertLanguagesData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO language (name) " +
                        "VALUES ('POLISH'), ('ENGLISH'), ('FRENCH'), ('SPANISH');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(4, rows);
    }

    @Test
    @DisplayName("Insert status test data")
    @Order(6)
    void testInsertStatusesData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO status (name) " +
                        "VALUES ('IN STOCK'), ('OUT OF STOCK'), ('IN REPAIR');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(3, rows);
    }

    @Test
    @DisplayName("Insert movie type test data")
    @Order(7)
    void testInsertMovieTypeData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO movie_type (name) " +
                        "VALUES ('DRAMA'), ('FUR KIDS'), ('ANIME'), ('HORROR'), ('COMEDY');")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(5, rows);
    }

    @Test
    @DisplayName("Insert movie test data")
    @Order(8)
    void testInsertMovieData() throws SQLException {
        //given + when
        int rows;
        try (var statement = connection.prepareStatement(
                "INSERT INTO movies " +
                        "(title, description, release_year, length, language_id, cost, status_id, rental_rate, movie_type_id) " +
                        "VALUES \n" +
                        "('John Wick', 'Film about John', 1990, 120, 1, 19.99, 1, 0, 2),\n" +
                        "('Jumanji', 'Film about jungle', 2015, 150, 1, 9.99, 1, 0, 2),\n" +
                        "('Titanic', 'Ship and sea', 1990, 124, 1, 20, 1, 0, 3);")) {

            rows = statement.executeUpdate();
        }

        //then
        assertEquals(3, rows);
    }

    @Test
    @DisplayName("Find all movies")
    @Order(9)
    void shouldFindAllMovies() throws SQLException {
        //given
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies")) {

            while (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }

        //then
        assertAll(
                () -> assertEquals(3, movies.size()),
                () -> assertEquals("John Wick", movies.get(0).getTitle()),
                () -> assertEquals(2015, movies.get(1).getReleaseYear()),
                () -> assertEquals(3L, movies.get(2).getMovieId())
        );
    }

    @Test
    @DisplayName("Find movie by ID")
    @Order(10)
    void shouldFindMovieById() throws SQLException {
        //given
        long id = 2;
        Movie movie = null;

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies WHERE movie_id = " + id)) {
            if (rs.next()) {
                movie = new Movie(rs.getLong("movie_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getInt("length"),
                        rs.getLong("language_id"),
                        rs.getDouble("cost"),
                        rs.getLong("status_id"),
                        rs.getDouble("rental_rate"),
                        rs.getLong("movie_type_id"));
            }
        }

        //then
        assertNotNull(movie);
        assertEquals(id, movie.getMovieId());
    }

    @Test
    @DisplayName("Find movies by title")
    @Order(11)
    void shouldFindMovieByTitle() throws SQLException {
        //given
        String title = "John Wick";
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies WHERE title like '" + title + "'")) {
            while (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }

        //then
        assertNotNull(movies);
        assertEquals(1, movies.size());
    }

    @Test
    @DisplayName("Find movies by movie_type")
    @Order(11)
    void shouldFindMovieByMovieType() throws SQLException {
        //given
        long movieTypeId = 2;
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.prepareStatement(
                "SELECT m.* FROM movies m INNER JOIN movie_type mt ON m.movie_type_id = mt.movie_type_id WHERE mt.movie_type_id = " + movieTypeId)) {
            var rs = statement.executeQuery();
            while (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }

        //then
        assertNotNull(movies);
        assertEquals(2, movies.size());
        assertEquals(movieTypeId, movies.get(0).getMovieTypeId());
        assertEquals(movieTypeId, movies.get(1).getMovieTypeId());
    }

    @Test
    @DisplayName("Find movies by release year")
    @Order(12)
    void shouldFindMovieByReleaseYear() throws SQLException {
        //given
        int releaseYear = 1990;
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies WHERE release_year = " + releaseYear)) {
            while (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }

        //then
        assertNotNull(movies);
        assertEquals(2, movies.size());
        assertEquals(releaseYear, movies.get(1).getReleaseYear());
    }

    @Test
    @DisplayName("Find movies by cost range")
    @Order(13)
    void shouldFindMoviesByCostRange() throws SQLException {
        //given
        double min = 10;
        double max = 20;
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movies WHERE cost BETWEEN " + min + " AND " + max)) {
            while (rs.next()) {
                addMoviesToList(movies, rs);
            }
        }

        //then
        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    @DisplayName("Update title")
    @Order(14)
    void shouldUpdateMovieTitle() throws SQLException {
        //given
        long movieId = 1;
        String newTitle = "Tactical new title";
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.prepareStatement(
                "UPDATE movies\n" +
                        " SET title = '" + newTitle + "' " +
                        " WHERE movie_id = " + movieId)) {
            statement.executeUpdate();
        }
        var statement = connection.prepareStatement(
                "SELECT * FROM movies WHERE movie_id = ?");
        statement.setLong(1, movieId);

        var rs = statement.executeQuery();
        if (rs.next()) {
            addMoviesToList(movies, rs);
        }

        //then
        assertEquals(1, movies.size());
        assertEquals(newTitle, movies.get(0).getTitle());
    }

    @Test
    @DisplayName("Update description")
    @Order(15)
    void shouldUpdateMovieDescription() throws SQLException {
        //given
        long movieId = 1;
        String newDescription = "Random new description";
        List<Movie> movies = new ArrayList<>();

        //when
        try (var statement = connection.prepareStatement(
                "UPDATE movies\n" +
                        " SET description = '" + newDescription + "' " +
                        " WHERE movie_id = " + movieId)) {
            statement.executeUpdate();
        }
        var statement = connection.prepareStatement(
                "SELECT * FROM movies WHERE movie_id = ?");
        statement.setLong(1, movieId);

        var rs = statement.executeQuery();
        if (rs.next()) {
            addMoviesToList(movies, rs);
        }

        //then
        assertEquals(1, movies.size());
        assertEquals(newDescription, movies.get(0).getDescription());
    }

    @Test
    @DisplayName("Remove movie by ID")
    @Order(16)
    void shouldRemoveMovieById() throws SQLException {
        //given
        long id = 1;
        int initialCount = 0;
        int finalCount = 0;

        //when
        try (var statement = connection.prepareStatement(
                "SELECT COUNT(movie_id) FROM movies");
             var rs = statement.executeQuery()) {
            if (rs.next()) {
                initialCount = rs.getInt(1);
            }
        }
        try (var statement = connection.createStatement()) {
            statement.execute("DELETE FROM movies WHERE movie_id = " + id);
        }

        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT COUNT(movie_id) FROM movies")) {
            if (rs.next()) {
                finalCount = rs.getInt(1);
            }
        }

        //then
        assertNotEquals(initialCount, finalCount);
    }

    @Test
    @DisplayName("Find languages")
    @Order(17)
    void shouldFindLanguages() throws SQLException {
        //given
        List<Language> languages = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM language")) {

            while (rs.next()) {
                languages.add(new Language(rs.getLong("language_id"),
                        rs.getString("name")));
            }
        }

        //then
        assertAll(
                () -> assertEquals(4, languages.size()),
                () -> assertEquals("POLISH", languages.get(0).getName()),
                () -> assertEquals("SPANISH", languages.get(3).getName())
        );
    }

    @Test
    @DisplayName("Find statuses")
    @Order(18)
    void shouldFindStatuses() throws SQLException {
        //given
        List<Status> statuses = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM status")) {

            while (rs.next()) {
                statuses.add(new Status(rs.getLong("status_id"),
                        rs.getString("name")));
            }
        }

        //then
        assertAll(
                () -> assertEquals(3, statuses.size()),
                () -> assertEquals("IN STOCK", statuses.get(0).getName()),
                () -> assertEquals("IN REPAIR", statuses.get(2).getName())
        );
    }

    @Test
    @DisplayName("Find movie types")
    @Order(19)
    void shouldFindMovieTypes() throws SQLException {
        //given
        List<MovieType> movieTypes = new ArrayList<>();

        //when
        try (var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT * FROM movie_type")) {

            while (rs.next()) {
                movieTypes.add(new MovieType(rs.getLong("movie_type_id"),
                        rs.getString("name")));
            }
        }

        //then
        assertAll(
                () -> assertEquals(5, movieTypes.size()),
                () -> assertEquals("DRAMA", movieTypes.get(0).getName()),
                () -> assertEquals("COMEDY", movieTypes.get(4).getName())
        );
    }

    private void addMoviesToList(List<Movie> movies, ResultSet rs) throws SQLException {
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
}