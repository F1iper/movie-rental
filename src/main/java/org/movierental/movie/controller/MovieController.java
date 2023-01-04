package org.movierental.movie.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public boolean add(Movie movie) {
        return movieService.add(movie);
    }

    public List<Movie> findByTitle(String name) {
        return movieService.findByTitle(name);
    }

    public Movie findById(Long id) {
        return movieService.findById(id);
    }

    public boolean updateName(Long id, String name) {
        return movieService.updateTitle(id, name);
    }

    public boolean updateDescription(Long id, String description) {
        return movieService.updateDescription(id, description);
    }

    public List<Movie> findByMovieTypeId(Long categoryId) {
        return movieService.findByMovieTypeId(categoryId);
    }

    public boolean removeById(Long id) {
        return movieService.removeById(id);
    }

    public void getStatuses() {
        movieService.findStatuses();
    }

    public void getMovieTypes() {
        movieService.findMovieTypes();
    }

    public void getLanguages() {
        movieService.findLanguages();
    }

    public List<Movie> findByCostRange(double min, double max) {
        return movieService.findByCostRange(min, max);
    }

    public List<Movie> findByReleaseYear(int year) {
        return movieService.findByReleaseYear(year);
    }

    public List<Movie> findAll() {
        return movieService.findAll();
    }
}
