package org.movierental.service;

import org.movierental.repository.QueryExecutor;

public class SearchCompanyService {

    public void search(String companyName) {
        QueryExecutor.executeSearchCompanyByCompanyName(companyName);
    }

}
