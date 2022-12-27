package org.movierental.company.controller;

import org.movierental.company.entity.Company;
import org.movierental.company.service.CompanyService;
import org.movierental.company.service.CompanyServiceImpl;

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController() {
        this.companyService = new CompanyServiceImpl();
    }

    public Company addCompany(Company company) {
        return companyService.add(company);
    }

    public void findCompanyByName(String companyName) {
        companyService.findByName(companyName);
    }

    public void removeCompany(Long id) {
        companyService.removeById(id);
    }

    public void findCompanyById(Long id) {
        companyService.findById(id);
    }

    public void findCompaniesAsList() {
        companyService.findAll();
    }

    public void updateCompany(long id, String name) {
        companyService.update(id, name);
    }
}
