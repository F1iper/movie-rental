package org.movierental.company.service;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.repository.CompanyRepository;

import java.util.List;

@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public boolean add(Company company) {
        return companyRepository.insert(company);
    }

    @Override
    public void update(long id, String newName) {
        companyRepository.update(id, newName);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> findByName(String name) {
        companyRepository.findByName(name);
        return null;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public boolean removeById(Long id) {
        companyRepository.removeById(id);
        return false;
    }
}
