package org.movierental.branch.service;

import org.movierental.branch.entity.Branch;

public interface BranchService {

    void add(Branch branch);

    void findById(Long id);

    void findAllStaffByBranchId(Long id);

    void findAllBranches();

    void removeById(Long id);

}
