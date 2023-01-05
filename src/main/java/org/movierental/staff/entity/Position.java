package org.movierental.staff.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Position {

    private Long positionId;
    private String name;

    public Position(Long positionId, String name) {
        this.positionId = positionId;
        this.name = name;
    }
}
