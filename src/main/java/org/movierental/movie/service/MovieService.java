package org.movierental.movie.service;

import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;

import java.util.List;

/**

 The MovieService interface provides methods for performing CRUD operations on Movie objects.
 @author Filip Timofiejew
 */
public interface MovieService {

    /**
     * Add a movie to the system
     *
     * @param movie the movie to add
     * @return true if the movie was successfully added, false otherwise
     */
    boolean add(Movie movie);

    /**
     * Find movies by title
     *
     * @param name the title of the movie to search for
     * @return a list of movies that match the given title
     */
    List<Movie> findByTitle(String name);

    /**
     * Find a movie by ID
     *
     * @param id the ID of the movie to search for
     * @return the movie that matches the given ID, or null if no match is found
     */
    Movie findById(Long id);

    /**
     * Find movies by movie type ID
     *
     * @param categoryId the ID of the movie type to search for
     * @return a list of movies that match the given movie type ID
     */
    List<Movie> findByMovieTypeId(Long categoryId);

    /**
     * Find movies by release year
     *
     * @param year the release year of the movies to search for
     * @return a list of movies that were released in the given year
     */
    List<Movie> findByReleaseYear(int year);

    /**
     * Update the title of a movie
     *
     * @param id the ID of the movie to update
     * @param name the new title for the movie
     * @return true if the update was successful, false otherwise
     */
    boolean updateTitle(Long id, String name);

    /**
     * Update the description of a movie
     *
     * @param id the ID of the movie to update
     * @param description the new description for the movie
     * @return true if the update was successful, false otherwise
     */
    boolean updateDescription(Long id, String description);

    /**
     * Remove a movie by ID
     *
     * @param id the ID of the movie to remove
     * @return true if the removal was successful, false otherwise
     */
    boolean removeById(Long id);

    /**
     * Find all the statuses of the movie
     *
     * @return List of statuses available
     */
    List<Status> findStatuses();

    /**
     * Find all the movie types available
     *
     * @return List of movie types
     */
    List<MovieType> findMovieTypes();

    /**
     * Find all the languages available
     *
     * @return List of languages
     */
    List<Language> findLanguages();

    /**
     * Find all the movies by cost range
     *
     * @param min the minimum cost of the movie
     * @param max the maximum cost of the movie
     * @return List of movies with in the cost range
     */
    List<Movie> findByCostRange(double min, double max);

    /**
     * Find all the movies available in the system
     *
     * @return List of all movies
     */
    List<Movie> findAll();
}
