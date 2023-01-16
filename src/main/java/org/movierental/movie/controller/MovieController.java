package org.movierental.movie.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.service.MovieService;

import java.util.List;

/**
 * The MovieController class is responsible for handling
 * requests and delegating them to the MovieService class
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * This method is used to add a movie.
     *
     * @param movie The movie object that needs to be added.
     * @return true if movie is added successfully, false otherwise
     */
    public boolean add(Movie movie) {
        return movieService.add(movie);
    }

    /**
     * This method is used to find movies by title.
     *
     * @param name The title of the movie
     * @return The list of movies with the given title
     */
    public List<Movie> findByTitle(String name) {
        return movieService.findByTitle(name);
    }

    /**
     * This method is used to find a movie by id.
     *
     * @param id The id of the movie
     * @return The movie with the given id
     */
    public Movie findById(Long id) {
        return movieService.findById(id);
    }

    /**
     * This method is used to update the title of a movie.
     *
     * @param id   The id of the movie to update
     * @param name The new title of the movie
     * @return true if update is successful, false otherwise
     */
    public boolean updateTitle(Long id, String name) {
        return movieService.updateTitle(id, name);
    }

    /**
     * This method is used to update the description of a movie.
     *
     * @param id          The id of the movie to update
     * @param description The new description of the movie
     * @return true if update is successful, false otherwise
     */
    public boolean updateDescription(Long id, String description) {
        return movieService.updateDescription(id, description);
    }

    /**
     * This method is used to find movies by movie type id.
     *
     * @param categoryId The id of the movie type
     * @return The list of movies with the given movie type id
     */
    public List<Movie> findByMovieTypeId(Long categoryId) {
        return movieService.findByMovieTypeId(categoryId);
    }

    /**
     * This method is used to remove a movie by id.
     *
     * @param id The id of the movie to remove
     * @return true if removal is successful, false otherwise
     */
    public boolean removeById(Long id) {
        return movieService.removeById(id);
    }

    /**
     * This method is used to find all statuses of movies.
     *
     * @return The list of statuses
     */
    public List<Status> getStatuses() {
        return movieService.findStatuses();
    }

    /**
     * This method is used to find all movie types.
     *
     * @return The list of movie types
     */
    public List<MovieType> getMovieTypes() {
        return movieService.findMovieTypes();
    }

    /**
     * This method is used to find all languages.
     *
     * @return The list languages
     */
    public List<Language> getLanguages() {
        return movieService.findLanguages();
    }

    /**
     * This method is used to find all movies by cost range.
     *
     * @param min The minimum cost of movie
     * @param max The maximum cost of movie
     * @return The list of movies between min and max values
     */
    public List<Movie> findByCostRange(double min, double max) {
        return movieService.findByCostRange(min, max);
    }

    /**
     * This method is used to find all movies by release year.
     *
     * @param year The release year of movie
     * @return The list of movies by release year
     */
    public List<Movie> findByReleaseYear(int year) {
        return movieService.findByReleaseYear(year);
    }

    /**
     * This method is used to find all movies.
     *
     * @return The list of all movies
     */
    public List<Movie> findAll() {
        return movieService.findAll();
    }
}
