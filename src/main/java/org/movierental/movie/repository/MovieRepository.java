package org.movierental.movie.repository;

import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;

import java.util.List;

public interface MovieRepository {

    boolean add(Movie movie);

    List<Movie> findByTitle(String name);

    Movie findById(Long id);

    List<Movie> findByMovieTypeId(Long categoryId);

    List<Movie> findByReleaseYear(int year);

    boolean updateTitle(Long id, String name);

    boolean updateDescription(Long id, String description);

    boolean removeById(Long id);

    List<Status> findStatuses();

    List<MovieType> findMovieTypes();

    List<Language> findLanguages();

    List<Movie> findByCostRange(double min, double max);

    List<Movie> findAll();
}