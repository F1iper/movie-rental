package org.movierental.entity;

import lombok.Data;

@Data
public class Status {

    private Long statusId;
    private String name;

    public Status(Long statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }
}
