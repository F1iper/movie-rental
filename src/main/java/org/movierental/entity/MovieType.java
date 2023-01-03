package org.movierental.entity;

import lombok.Data;

@Data
public class MovieType {
    private Long movieTypeId;
    private String name;
    private Long movieId;

    public MovieType(Long movieTypeId, String name) {
        this.movieTypeId = movieTypeId;
        this.name = name;
    }
}