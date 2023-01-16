package org.movierental.company.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.company.entity.Company;
import org.movierental.company.service.CompanyService;

import java.util.List;

/**
 * The CompanyController class is responsible for handling
 * requests and delegating them to the CompanyService class
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * This method is used to add a new Company to the database.
     *
     * @param company The Company object to be added to the database.
     * @return true if the Company is successfully added, false otherwise.
     */
    public boolean add(Company company) {
        return companyService.add(company);
    }

    /**
     * This method is used to find a list of Companies by their name.
     *
     * @param companyName The name of the Companies to be found.
     * @return List of Companies with the specified name.
     */
    public List<Company> findByName(String companyName) {
        return companyService.findByName(companyName);
    }

    /**
     * This method is used to remove a Company from the database by its ID.
     *
     * @param id ID of the Company to be removed.
     * @return true if the Company is successfully removed, false otherwise.
     */
    public boolean removeById(Long id) {
        return companyService.removeById(id);
    }

    /**
     * This method is used to find a Company by its ID.
     *
     * @param id ID of the Company to be found.
     * @return The Company with the specified ID.
     */
    public Company findById(Long id) {
        return companyService.findById(id);
    }

    /**
     * This method is used to find all Companies in the database.
     *
     * @return List of all Companies in the database.
     */
    public List<Company> findAll() {
        return companyService.findAll();
    }

    /**
     * This method is used to update the name of a Company in the database by its ID.
     *
     * @param id   ID of the Company to be updated.
     * @param name The new name of the Company.
     * @return true if the Company name is successfully updated, false otherwise.
     */
    public boolean updateName(Long id, String name) {
        return companyService.update(id, name);
    }
}
