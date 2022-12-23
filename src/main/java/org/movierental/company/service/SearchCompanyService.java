package org.movierental.company.service;

import org.movierental.repository.QueryExecutor;

public class SearchCompanyService {

    public void searchByName(String companyName) {
        QueryExecutor.searchByCompanyName(companyName);
    }

    public void searchAll() {
        QueryExecutor.searchAllCompanies();
    }

    public void removeCompanyById(Long id) {
        QueryExecutor.removeCompanyById(id);
    }

    public void findById(Long id) {
        QueryExecutor.findCompanyById(id);
    }
}
