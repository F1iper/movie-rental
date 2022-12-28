package org.movierental.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Customer {
    private Long customerId;
    private boolean active;
    private Timestamp createdAt;
    private String email;
    private String firstname;
    private String lastname;
}
