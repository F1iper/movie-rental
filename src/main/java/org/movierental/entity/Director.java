package org.movierental.entity;

import lombok.Data;

@Data
public class Director {
    private Long directorId;
    private String firstname;
    private String lastname;
}