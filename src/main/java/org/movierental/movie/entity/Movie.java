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
}
