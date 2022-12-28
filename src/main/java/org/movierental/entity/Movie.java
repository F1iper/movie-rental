package org.movierental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private Long movieId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
    private Long languageId;
    private Long categoryId;
    private double cost;
    private Long statusId;
    private double rentalRate;
}
