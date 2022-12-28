package org.movierental.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Rental {
    private Long rentalId;
    private Timestamp rentDate;
    private Timestamp returnDate;
    private Long customerId;
    private Long movieId;
    private Long staffId;
}