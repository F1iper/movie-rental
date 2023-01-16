package org.movierental.movie.service;

import lombok.RequiredArgsConstructor;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.repository.MovieRepository;

import java.util.List;

/**
 * Implementation of the {@link MovieService} interface
 *
 * @author Filip Timofiejew
 */
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    /**
     * This method is used to add a movie to the movie repository.
     *
     * @param movie The movie object that needs to be added.
     * @return true if movie is added successfully, false otherwise
     */
    @Override
    public boolean add(Movie movie) {
        return movieRepository.add(movie);
    }

    /**
     * This method is used to find movies by title
     *
     * @param name The title of the movie
     * @return The list of movies with the given title
     */
    @Override
    public List<Movie> findByTitle(String name) {
        return movieRepository.findByTitle(name);
    }

    /**
     * This method is used to find a movie by id
     *
     * @param id The id of the movie
     * @return The movie with the given id
     */
    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    /**
     * This method is used to find movies by movie type id
     *
     * @param categoryId The id of the movie type
     * @return The list of movies with the given movie type id
     */
    @Override
    public List<Movie> findByMovieTypeId(Long categoryId) {
        return movieRepository.findByMovieTypeId(categoryId);
    }

    /**
     * This method is used to find movies by release year
     *
     * @param year The release year of the movie
     * @return The list of movies with the given release year
     */
    @Override
    public List<Movie> findByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }

    /**
     * This method is used to update the title of a movie
     *
     * @param id    The id of the movie to update
     * @param title The new title of the movie
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateTitle(Long id, String title) {
        return movieRepository.updateTitle(id, title);
    }

    /**
     * This method is used to update the description of a movie
     *
     * @param id          The id of the movie to update
     * @param description The new description of the movie
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateDescription(Long id, String description) {
        return movieRepository.updateDescription(id, description);
    }

    /**
     * This method is used to remove a movie by id
     *
     * @param id The id of the movie to remove
     * @return true if removal is successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return movieRepository.removeById(id);
    }

    /**
     * This method is used to find all statuses of movies
     *
     * @return The list of statuses
     */
    @Override
    public List<Status> findStatuses() {
        return movieRepository.findStatuses();
    }

    /**
     * This method is used to find all movie types
     *
     * @return The list of movie types
     */
    @Override
    public List<MovieType> findMovieTypes() {
        return movieRepository.findMovieTypes();
    }

    /**
     * This method is used to find all languages
     *
     * @return The list of languages
     */
    @Override
    public List<Language> findLanguages() {
        return movieRepository.findLanguages();
    }

    /**
     * This method is used to find movies by cost range
     *
     * @param min The minimum cost of the movie
     * @param max The maximum cost of the movie
     * @return The list of movies with the given cost range
     */
    @Override
    public List<Movie> findByCostRange(double min, double max) {
        return movieRepository.findByCostRange(min, max);
    }

    /**
     * This method is used to find all movies
     *
     * @return The list of all movies
     */
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
