package org.movierental.company.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.movierental.branch.entity.Branch;

import java.util.Set;

@Getter
@Setter
@ToString
public class Company {

    private Long companyId;
    private String name;
    private Set<Branch> branches;
}
