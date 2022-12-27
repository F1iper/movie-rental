package org.movierental.company.service;

import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

public class CompanyServiceImpl implements CompanyService {

    public Company add(Company company) {
        return QueryExecutor.insertCompany(company);
    }

    public void update(long id, String newName) {
        QueryExecutor.updateCompany(id, newName);
    }

    public void findById(Long id) {
        QueryExecutor.findCompanyById(id);
    }

    public void findByName(String companyName) {
        QueryExecutor.searchByCompanyName(companyName);
    }

    public void findAll() {
        QueryExecutor.searchAllCompanies();
    }

    public void removeById(Long id) {
        QueryExecutor.removeCompanyById(id);
    }
}
