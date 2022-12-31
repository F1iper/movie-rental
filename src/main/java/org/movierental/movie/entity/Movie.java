package org.movierental.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
