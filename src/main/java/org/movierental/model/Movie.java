package org.movierental.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Movie {

    private UUID movieId;
    private String ISAN;
    private String catalogNumber;
    private String title;
    private Category category;
//    private String dailyRental?;
    private BigDecimal rentCost;
    private List<Actor> actors;
    private List<Director> directors;
    private RentStatus status;

}
