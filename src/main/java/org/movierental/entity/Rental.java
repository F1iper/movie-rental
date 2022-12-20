package org.movierental.entity;

import java.time.LocalDateTime;

public class Rental {

    private Long rentalId;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private Staff staff;
    private Movie movie;
    private Customer customer;
}
