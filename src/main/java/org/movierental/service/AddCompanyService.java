package org.movierental.service;

import org.movierental.repository.QueryExecutor;

public class AddCompanyService {

    public void add(String companyName) {
        QueryExecutor.insertCompany(companyName);
    }
}
