package org.movierental.company.service;

import org.movierental.company.entity.Company;

public interface CompanyService {

    Company add(Company company);

    void update(long id, String newName);

    void findById(Long id);

    void findByName(String companyName);

    void findAll();

    void removeById(Long id);

}
