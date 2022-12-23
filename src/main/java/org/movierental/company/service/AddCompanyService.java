package org.movierental.company.service;

import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

public class AddCompanyService {

    public Company add(Company company) {
        return QueryExecutor.insertCompany(company);
    }
}
