package org.movierental.company.entity;

import org.movierental.branch.entity.Branch;

import java.util.Set;

public class Company {

    private Long companyId;
    private String name;
    private Set<Branch> branches;
}
