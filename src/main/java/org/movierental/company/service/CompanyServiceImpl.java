package org.movierental.company.service;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.repository.CompanyRepository;

import java.util.List;

@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * Adds a new company to the repository.
     *
     * @param company the company to add
     * @return true if the company was successfully added, false otherwise
     */
    @Override
    public boolean add(Company company) {
        return companyRepository.insert(company);
    }

    /**
     * Updates the name of a company in the repository.
     *
     * @param id the id of the company to update
     * @param newName the new name for the company
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean update(long id, String newName) {
        return companyRepository.update(id, newName);
    }

    /**
     * Finds a company by its id in the repository.
     *
     * @param id the id of the company to find
     * @return the company with the specified id, or throw RuntimeException
     */
    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id);
    }

    /**
     * Finds all companies in the repository with a given name.
     *
     * @param name the name to search for
     * @return a list of companies with the specified name
     */
    @Override
    public List<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    /**
     * Finds all companies in the repository.
     *
     * @return a list of all companies in the repository
     */
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    /**
     * Removes a company from the repository by its id.
     *
     * @param id the id of the company to remove
     * @return true if the company was successfully removed, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return companyRepository.removeById(id);
    }
}
