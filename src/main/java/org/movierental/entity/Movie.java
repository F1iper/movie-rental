package org.movierental.entity;

import java.util.List;
import java.util.Set;

public class Movie {
    private Long movieId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
    private Language language;
    private Category category;
    private double cost;
    private Status status;
    private double rentalRate;
    private Set<Actor> actors;
    private List<Branch> branches;

}
