package org.movierental.company.repository;

import org.movierental.company.entity.Company;

import java.sql.ResultSet;

public interface CompanyRepository {

    Company insert(Company company);

    void update(long id, String newName);

    void findById(Long id);

    void findByName(String companyName);

    void findAll();

    void removeById(Long id);

    void print(ResultSet rs);
}
