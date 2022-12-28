package org.movierental.company.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.service.CompanyService;

@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    public Company addCompany(Company company) {
        return companyService.add(company);
    }

    public void findByName(String companyName) {
        companyService.findByName(companyName);
    }

    public void removeById(Long id) {
        companyService.removeById(id);
    }

    public void findById(Long id) {
        companyService.findById(id);
    }

    public void findAll() {
        companyService.findAll();
    }

    public void updateName(Long id, String name) {
        companyService.update(id, name);
    }
}
