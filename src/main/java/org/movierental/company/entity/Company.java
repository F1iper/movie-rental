package org.movierental.company.entity;

import lombok.Data;

@Data
public class Company {
    private Long companyId;
    private String name;

    public Company(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Company() {
    }
}
