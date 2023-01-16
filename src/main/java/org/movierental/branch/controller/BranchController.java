package org.movierental.branch.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.branch.entity.Branch;
import org.movierental.branch.service.BranchService;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.StaffService;

import java.util.List;

/**
 * The BranchController class is responsible for handling all actions related to Branch objects.
 * It uses a BranchService object to interact with the underlying data storage.
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;
    private final StaffService staffService;

    /**
     * Adds a new Branch object to the data storage.
     *
     * @param branch The Branch object to be added.
     */
    public void add(Branch branch) {
        branchService.add(branch);
    }

    /**
     * Finds a Branch object by its ID in the data storage.
     *
     * @param id The ID of the Branch to be found.
     */
    public Branch findById(Long id) {
        return branchService.findById(id);
    }

    /**
     * Finds all staff members associated with a specific Branch in the data storage.
     *
     * @param id The ID of the Branch for which to find staff members.
     * @return List of all staff assigned to branch by given branch id
     */
    public List<Staff> findAllStaffByBranchId(Long id) {
        return staffService.findAllStaffByBranchId(id);
    }

    /**
     * Find all Branches available in the system
     *
     * @return List of all branches
     */
    public List<Branch> findAll() {
        return branchService.findAllBranches();
    }

    /**
     * This method is used to find a list of Branches by their name.
     *
     * @param branchName The name of the Branches to be found.
     * @return List of Branches with the specified name.
     */
    public List<Branch> findByName(String branchName) {
        return branchService.findByName(branchName);
    }


    /**
     * This method is used to update the name of a branch in the database by its ID.
     *
     * @param id      ID of the branch to be updated.
     * @param newName The new name of the Branch.
     * @return true if the Branch name is successfully updated, false otherwise.
     */
    public boolean updateName(long id, String newName) {
        return branchService.updateName(id, newName);
    }
}
