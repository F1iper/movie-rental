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

    public void findCompanyByName(String companyName) {
        companyService.findByName(companyName);
    }

    public void removeCompanyById(Long id) {
        companyService.removeById(id);
    }

    public void findCompanyById(Long id) {
        companyService.findById(id);
    }

    public void findCompaniesAsList() {
        companyService.findAll();
    }

    public void updateCompanyNameById(long id, String name) {
        companyService.update(id, name);
    }
}
