package org.movierental.branch.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.branch.entity.Branch;
import org.movierental.branch.service.BranchService;

@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    public void add(Branch branch) {
        branchService.add(branch);
    }

    public void findById(Long id) {
        branchService.findById(id);
    }

    public void findAllStaffByBranchId(Long id) {
        branchService.findAllStaffByBranchId(id);
    }
}
