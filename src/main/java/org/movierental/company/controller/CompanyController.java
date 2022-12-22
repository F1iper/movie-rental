package org.movierental.company.controller;

import org.movierental.company.service.AddCompanyService;
import org.movierental.company.service.RemoveCompanyService;
import org.movierental.company.service.SearchCompanyService;

public class CompanyController {

    private final AddCompanyService addCompanyService;
    private final SearchCompanyService searchCompanyService;
    private final RemoveCompanyService removeCompanyService;

    public CompanyController() {
        this.addCompanyService = new AddCompanyService();
        this.searchCompanyService = new SearchCompanyService();
        this.removeCompanyService = new RemoveCompanyService();
    }

    public void addCompany(String companyName) {
        addCompanyService.add(companyName);
    }

    public void searchCompanyByName(String companyName) {
        searchCompanyService.searchByName(companyName);
    }

    public void removeCompany(Long id) {
        removeCompanyService.removeById(id);
    }

    public void searchCompanyById(Long id) {
        searchCompanyService.searchById(id);
    }

    public void searchAll() {
        searchCompanyService.searchAll();
    }
}
