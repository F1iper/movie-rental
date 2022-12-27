package org.movierental.company.controller;

import org.movierental.company.entity.Company;
import org.movierental.company.service.CompanyServiceImpl;

public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController() {
        this.companyServiceImpl = new CompanyServiceImpl();
    }

    public Company addCompany(Company company) {
        return companyServiceImpl.add(company);
    }

    public void findCompanyByName(String companyName) {
        companyServiceImpl.findByName(companyName);
    }

    public void removeCompany(Long id) {
        companyServiceImpl.removeById(id);
    }

    public void findCompanyById(Long id) {
        companyServiceImpl.findById(id);
    }

    public void findCompaniesAsList() {
        companyServiceImpl.findAll();
    }

    public void updateCompany(long id, String name) {
        companyServiceImpl.update(id, name);
    }
}
