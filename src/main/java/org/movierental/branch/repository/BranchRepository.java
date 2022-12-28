package org.movierental.branch.repository;

import org.movierental.branch.entity.Branch;

public interface BranchRepository {

    void insert(Branch branch);

    void findById(Long id);

    void findAllStaffByBranchId(Long id);

    void findAll();

    void removeById(Long id);
}
