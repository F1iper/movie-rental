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
    public void findById(Long id) {
        branchService.findById(id);
    }

    /**
     * Finds all staff members associated with a specific Branch in the data storage.
     *
     * @param id The ID of the Branch for which to find staff members.
     */
    public List<Staff> findAllStaffByBranchId(Long id) {
        return staffService.findAllStaffByBranchId(id);
        // TODO: 1/16/2023 implement find all staff in staff UI
    }

    /**
     * Find all Branches available in the system
     *
     * @return List of all branches
     */
    public List<Branch> findAll() {
        return branchService.findAllBranches();
    }
}
