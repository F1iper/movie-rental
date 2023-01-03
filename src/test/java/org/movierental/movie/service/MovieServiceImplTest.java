package org.movierental.movie.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.entity.Movie;
import org.movierental.movie.repository.MovieRepository;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    private Movie movie1;
    private Movie movie2;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        movie1 = new Movie(
                1L,
                "Apocalypto",
                "Film about apocalipse",
                2000,
                120,
                1L,
                25.99,
                1L,
                0,
                1L);

        movie2 = new Movie(
                2L,
                "Jumanji",
                "Film about jumanji",
                2000,
                100,
                1L,
                20.99,
                1L,
                0,
                1L
        );
    }

    @Test
    @DisplayName("Add movie")
    void addMovie_returnTrue() {
        //given
        when(movieRepository.add(any(Movie.class))).thenReturn(true);

        //when
        boolean result = movieService.add(movie1);

        //then
        assertTrue(result);
        verify(movieRepository).add(movie1);
    }

    @Test
    @DisplayName("Find by id")
    void shouldReturnMovieById() {
        //given
        Long id = 1L;
        when(movieRepository.findById(id)).thenReturn(movie1);

        //when
        Movie resultMovie = movieService.findById(id);

        //then
        assertEquals(id, resultMovie.getMovieId());
        verify(movieRepository).findById(id);
    }

    @Test
    @DisplayName("Find by title")
    void shouldFindAllMoviesByTitle() {
        //given
        String title = "Apocalypto";
        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieRepository.findByTitle(title)).thenReturn(movies);

        //when
        List<Movie> resultMovies = movieService.findByTitle(title);

        //then
        assertEquals(movies, resultMovies);
        verify(movieRepository).findByTitle(title);
    }

    @Test
    @DisplayName("Find by movie type ID")
    void shouldFindByMovieTypeId() {
        //given
        Long movieTypeId = 1L;

        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieRepository.findByMovieTypeId(movieTypeId)).thenReturn(movies);

        //when
        List<Movie> resultMovies = movieService.findByMovieTypeId(movieTypeId);

        //then
        assertEquals(movies, resultMovies);
        assertEquals(2, resultMovies.size());
        verify(movieRepository).findByMovieTypeId(movieTypeId);
    }

    @Test
    @DisplayName("Find by release year")
    void shouldFindByReleaseYear() {
        //given
        int releaseYear = 2000;

        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieRepository.findByReleaseYear(releaseYear)).thenReturn(movies);

        //when
        List<Movie> resultMovies = movieService.findByReleaseYear(releaseYear);

        //then
        assertEquals(movies, resultMovies);
        assertEquals(2, resultMovies.size());
        verify(movieRepository).findByReleaseYear(releaseYear);
    }

    @Test
    @DisplayName("Remove by ID")
    void shouldRemoveById() {
        //given
        Long id = 1L;

        when(movieRepository.removeById(id)).thenReturn(true);

        //when
        boolean result = movieService.removeById(id);

        //then
        assertTrue(result);
        verify(movieRepository).removeById(id);
    }

    @Test
    @DisplayName("Update title by given ID")
    void shouldUpdateTitle() {
        //given
        String newTitle = "Forrest Gump";
        when(movieRepository.updateTitle(movie1.getMovieId(), newTitle)).thenReturn(true);
        movie1.setTitle(newTitle);

        //when
        boolean result = movieService.updateTitle(movie1.getMovieId(), newTitle);

        //then
        assertTrue(result);
        assertEquals(newTitle, movie1.getTitle());
        verify(movieRepository).updateTitle(movie1.getMovieId(), newTitle);
    }

    @Test
    @DisplayName("Update description by given ID")
    void shouldUpdateDescription() {
        //given
        String newDescription = "Updated description";
        when(movieRepository.updateDescription(movie1.getMovieId(), newDescription)).thenReturn(true);
        movie1.setDescription(newDescription);

        //when
        boolean result = movieService.updateDescription(movie1.getMovieId(), newDescription);

        //then
        assertTrue(result);
        assertEquals(newDescription, movie1.getDescription());
        verify(movieRepository).updateDescription(movie1.getMovieId(), newDescription);
    }

    @Test
    @DisplayName("Find by cost range")
    void shouldFindByCostRange() {
        //given
        List<Movie> movies = singletonList(movie1);
        double min = 21.99;
        double max = 26;

        when(movieRepository.findByCostRange(min, max)).thenReturn(movies);

        //when
        List<Movie> moviesByCostRange = movieService.findByCostRange(min, max);

        //then
        assertNotNull(moviesByCostRange);
        assertEquals(1, moviesByCostRange.size());
        assertEquals(movies, moviesByCostRange);
    }

    @Test
    @DisplayName("Find statuses")
    void shouldFindStatuses() {
        //given
        List<Status> statuses = Arrays.asList(new Status(1L, "IN STOCK"),
                new Status(2L, "IN REPAIR"));

        when(movieRepository.findStatuses()).thenReturn(statuses);

        //when
        List<Status> resultStatuses = movieService.findStatuses();

        //then
        assertNotNull(resultStatuses);
        assertEquals(2, resultStatuses.size());
        assertEquals(statuses, resultStatuses);
    }

    @Test
    @DisplayName("Find movie types")
    void shouldFindMovieTypes() {
        //given
        List<MovieType> movieTypes = Arrays.asList(new MovieType(1L, "HORROR"),
                new MovieType(2L, "THRILLER"));

        when(movieRepository.findMovieTypes()).thenReturn(movieTypes);

        //when
        List<MovieType> resultMovieTypes = movieService.findMovieTypes();

        //then
        assertNotNull(resultMovieTypes);
        assertEquals(2, resultMovieTypes.size());
        assertEquals(movieTypes, resultMovieTypes);
    }

    @Test
    @DisplayName("Find languages")
    void shouldFindLanguages() {
        //given
        List<Language> languages = Arrays.asList(new Language(1L, "ENGLISH"),
                new Language(2L, "POLISH"));

        when(movieRepository.findLanguages()).thenReturn(languages);

        //when
        List<Language> resultLanguages = movieService.findLanguages();

        //then
        assertNotNull(resultLanguages);
        assertEquals(2, resultLanguages.size());
        assertEquals(languages, resultLanguages);
    }
}
