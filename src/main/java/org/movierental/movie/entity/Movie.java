package org.movierental.movie.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"movieId", "languageId", "statusId", "movieTypeId", "rentalRate"})
public class Movie {
    private Long movieId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
    private Long languageId;
    private double cost;
    private Long statusId;
    private double rentalRate;
    private Long movieTypeId;

    public Movie(Long movieId, String title, String description, int releaseYear, int length, Long languageId, double cost, Long statusId, double rentalRate, Long movieTypeId) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.length = length;
        this.languageId = languageId;
        this.cost = cost;
        this.statusId = statusId;
        this.rentalRate = rentalRate;
        this.movieTypeId = movieTypeId;
    }

    public Movie() {
    }
}
