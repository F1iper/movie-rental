package org.movierental.company.service;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.repository.CompanyRepository;

@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company add(Company company) {
        return companyRepository.insert(company);
    }

    @Override
    public void update(long id, String newName) {
        companyRepository.update(id, newName);
    }

    @Override
    public void findById(Long id) {
        companyRepository.findById(id);
    }

    @Override
    public void findByName(String name) {
        companyRepository.findByName(name);
    }

    @Override
    public void findAll() {
        companyRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
        companyRepository.removeById(id);
    }
}
