package org.movierental.company.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.service.CompanyService;

import java.util.List;

@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    public boolean addCompany(Company company) {
        return companyService.add(company);
    }

    public List<Company> findByName(String companyName) {
        return companyService.findByName(companyName);
    }

    public boolean removeById(Long id) {
        return companyService.removeById(id);
    }

    public Company findById(Long id) {
        return companyService.findById(id);
    }

    public List<Company> findAll() {
        return companyService.findAll();
    }

    public boolean updateName(Long id, String name) {
        return companyService.update(id, name);
    }
}
