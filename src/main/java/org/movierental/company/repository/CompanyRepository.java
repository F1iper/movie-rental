package org.movierental.company.repository;

import org.movierental.company.entity.Company;

import java.util.List;

public interface CompanyRepository {

    boolean insert(Company company);

    void update(long id, String newName);

    Company findById(Long id);

    List<Company> findByName(String companyName);

    List<Company> findAll();

    boolean removeById(Long id);
}
