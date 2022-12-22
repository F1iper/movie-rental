package org.movierental.service;

import org.movierental.repository.QueryExecutor;

public class SearchCompanyService {

    public void searchByName(String companyName) {
        QueryExecutor.searchByCompanyName(companyName);
    }

    public void searchAll() {
        QueryExecutor.searchAll();
    }

    public void searchById(Long id) {
        QueryExecutor.removeCompanyById(id);
    }
}
