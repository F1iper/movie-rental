package org.movierental.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MovieType {
    private Long movieTypeId;
    private String name;

    public MovieType(Long movieTypeId, String name) {
        this.movieTypeId = movieTypeId;
        this.name = name;
    }
}