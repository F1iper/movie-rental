package org.movierental.entity;

import java.time.LocalDateTime;

public class Customer {

    private Long customerId;
    private String firstname;
    private String lastname;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;
    private Address address;
}
