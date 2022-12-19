package org.movierental.model;

import org.movierental.model.personel.Person;

import java.util.List;
import java.util.UUID;

public class Branch {

    private UUID branchId;
    private Address address;
    private List<Person> personnel;
    private List<Movie> movieCopies;


}
