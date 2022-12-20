package org.movierental.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Branch {

    private Long branchId;
    private Company company;
    private Address address;
    private List<Movie> movies;
    private Set<Staff> staff = new HashSet<>();
}
