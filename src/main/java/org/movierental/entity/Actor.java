package org.movierental.entity;

import lombok.Data;

@Data
public class Actor {
    private Long actorId;
    private String firstName;
    private String lastName;
}