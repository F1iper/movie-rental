package org.movierental.branch.service;

import lombok.RequiredArgsConstructor;
import org.movierental.branch.entity.Branch;
import org.movierental.branch.repository.BranchRepository;

import java.util.List;

/**
 * Implementation of the {@link BranchService} interface
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    /**
     * This method is used to add a branch to the branch repository.
     *
     * @param branch The branch object that needs to be added.
     * @return true if branch is added successfully, false otherwise
     */
    @Override
    public boolean add(Branch branch) {
        return branchRepository.insert(branch);
    }

    /**
     * This method is used to find a branch by id
     *
     * @param id The id of the branch
     * @return The branch with the given id
     */
    @Override
    public Branch findById(Long id) {
        return branchRepository.findById(id);
    }

    /**
     * This method is used to find all branches
     *
     * @return The list of all branches
     */
    @Override
    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    /**
     * This method is used to update the name of a Branch in the database by its ID.
     *
     * @param id      ID of the Branch to be updated.
     * @param newName The new name of the Branch.
     * @return true if the Branch name is successfully updated, false otherwise.
     */
    @Override
    public boolean updateName(long id, String newName) {
        return branchRepository.updateName(id, newName);
    }

    /**
     * This method is used to find branches by branch name
     *
     * @param branchName The name of the branch to find
     * @return List of branches with given branchName
     */
    @Override
    public List<Branch> findByName(String branchName) {
        return branchRepository.findByName(branchName);
    }

    /**
     * This method is used to remove a branch by id
     *
     * @param id The id of the branch to remove
     * @return true if removal is successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return branchRepository.removeById(id);
    }
}
