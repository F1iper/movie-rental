package org.movierental.movie.repository;

import org.movierental.movie.entity.Movie;

import java.util.List;

public interface MovieRepository {

    boolean add(Movie movie);

    List<Movie> findByName(String name);

    Movie findById(Long id);

    List<Movie> findByCategoryId(Long categoryId);

    List<Movie> findByReleaseYear(int year);

    void updateName(Long id, String name);

    void updateDescription(Long id, String description);

    boolean removeById(Long id);

    void findStatuses();

    void findMovieTypes();

    void findLanguages();

    List<Movie> findByCostRange(int min, int max);

    List<Movie> findAll();
}
