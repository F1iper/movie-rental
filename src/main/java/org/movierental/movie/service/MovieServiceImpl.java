package org.movierental.movie.service;

import lombok.RequiredArgsConstructor;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.repository.MovieRepository;

import java.util.List;

@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public boolean add(Movie movie) {
        return movieRepository.add(movie);
    }

    @Override
    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findByCategoryId(Long categoryId) {
        return movieRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }

    @Override
    public void updateName(Long id, String name) {

    }

    @Override
    public void updateDescription(Long id, String description) {

    }

    @Override
    public boolean removeById(Long id) {
        return movieRepository.removeById(id);
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
    public List<Movie> findByCostRange(int min, int max) {
        return movieRepository.findByCostRange(min, max);
    }

    @Override
    public List<Movie> findAll() {
        return  movieRepository.findAll();
    }
}
