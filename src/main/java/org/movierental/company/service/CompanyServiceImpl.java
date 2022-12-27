package org.movierental.company.service;

import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

public class CompanyServiceImpl implements CompanyService {

    @Override
    public Company add(Company company) {
        return QueryExecutor.insertCompany(company);
    }

    @Override
    public void update(long id, String newName) {
        QueryExecutor.updateCompany(id, newName);
    }

    @Override
    public void findById(Long id) {
        QueryExecutor.findCompanyById(id);
    }

    @Override
    public void findByName(String companyName) {
        QueryExecutor.searchByCompanyName(companyName);
    }

    @Override
    public void findAll() {
        QueryExecutor.searchAllCompanies();
    }

    @Override
    public void removeById(Long id) {
        QueryExecutor.removeCompanyById(id);
    }
}
