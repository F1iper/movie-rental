package org.movierental.entity;

import org.movierental.address.entity.Address;
import org.movierental.branch.entity.Branch;

public class Staff {

    private Long staffId;
    private String firstname;
    private String lastname;
    private Position position;
    private double salary;
    private Branch branch;
    private Address address;
}