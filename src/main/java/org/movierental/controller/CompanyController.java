package org.movierental.controller;

import org.movierental.service.AddCompanyService;
import org.movierental.service.SearchCompanyService;

public class CompanyController {

    private final AddCompanyService addCompanyService;
    private final SearchCompanyService searchCompanyService;

    public CompanyController() {
        this.addCompanyService = new AddCompanyService();
        this.searchCompanyService = new SearchCompanyService();
    }

    public void addCompany(String companyName) {
        addCompanyService.add(companyName);
    }

    public void searchCompany(String companyName) {
        searchCompanyService.search(companyName);
    }
}
