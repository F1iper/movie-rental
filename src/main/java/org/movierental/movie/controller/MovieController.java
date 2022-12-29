package org.movierental.movie.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.service.MovieService;

@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public Movie add(Movie movie) {
        return movieService.add(movie);
    }

    public void findByTitle(String name) {
        movieService.findByName(name);
    }

    public void findById(Long id) {
        movieService.findById(id);
    }

    public void updateName(Long id, String name) {
        movieService.updateName(id, name);
    }

    public void updateDescription(Long id, String description) {
        movieService.updateDescription(id, description);
    }

    public void findByCategoryId(Long categoryId) {
        movieService.findByCategoryId(categoryId);
    }

    public void removeById(Long id) {
        movieService.removeById(id);
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

    public void findByCostRange(int min, int max) {
        movieService.findByCostRange(min, max);
    }

    public void findByReleaseYear(int year) {
        movieService.findByReleaseYear(year);
    }

    public void findAll() {
        movieService.findAll();
    }
}
