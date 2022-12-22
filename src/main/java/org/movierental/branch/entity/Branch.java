package org.movierental.branch.entity;

import org.movierental.address.entity.Address;
import org.movierental.company.entity.Company;
import org.movierental.entity.Movie;
import org.movierental.entity.Staff;

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
