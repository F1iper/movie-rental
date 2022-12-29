package org.movierental.movie.repository;

import org.movierental.movie.entity.Movie;

public interface MovieRepository {

    Movie add(Movie movie);

    void findByName(String name);

    void findById(Long id);

    void findByCategoryId(Long categoryId);

    void findByReleaseYear(int year);

    void updateName(Long id, String name);

    void updateDescription(Long id, String description);

    void removeById(Long id);

    void findStatuses();

    void findMovieTypes();

    void findLanguages();

    void findByCostRange(int min, int max);

    void findAll();
}
