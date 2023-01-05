package org.movierental.movie.service;

import lombok.RequiredArgsConstructor;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
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
    public List<Movie> findByTitle(String name) {
        return movieRepository.findByTitle(name);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findByMovieTypeId(Long categoryId) {
        return movieRepository.findByMovieTypeId(categoryId);
    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }

    @Override
    public boolean updateTitle(Long id, String name) {
        return movieRepository.updateTitle(id, name);
    }

    @Override
    public boolean updateDescription(Long id, String description) {
        return movieRepository.updateDescription(id, description);
    }

    @Override
    public boolean removeById(Long id) {
        return movieRepository.removeById(id);
    }

    @Override
    public List<Status> findStatuses() {
        return movieRepository.findStatuses();
    }

    @Override
    public List<MovieType> findMovieTypes() {
        return movieRepository.findMovieTypes();
    }

    @Override
    public List<Language> findLanguages() {
        return movieRepository.findLanguages();
    }

    @Override
    public List<Movie> findByCostRange(double min, double max) {
        return movieRepository.findByCostRange(min, max);
    }

    @Override
    public List<Movie> findAll() {
        return  movieRepository.findAll();
    }
}
