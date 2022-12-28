package org.movierental.movie.service;

import lombok.RequiredArgsConstructor;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.repository.MovieRepository;

@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie add(Movie movie) {
        return movieRepository.add(movie);
    }

    @Override
    public void findByName(String name) {

    }

    @Override
    public void findById(Long id) {

    }

    @Override
    public void findByCategoryId(Long categoryId) {

    }

    @Override
    public void findByReleaseYear(int start) {

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
        movieRepository.findStatuses();
    }

    @Override
    public void findMovieTypes() {
        movieRepository.findMovieTypes();
    }

    @Override
    public void findLanguages() {
        movieRepository.findLanguages();
    }

    @Override
    public void findByCostRange(int min, int max) {
        movieRepository.findByCostRange(min, max);
    }

    @Override
    public void findAll() {
        movieRepository.findAll();
    }
}
