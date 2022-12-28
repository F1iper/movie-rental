package org.movierental.branch.service;

import lombok.RequiredArgsConstructor;
import org.movierental.branch.entity.Branch;
import org.movierental.branch.repository.BranchRepository;

@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public void add(Branch branch) {
        branchRepository.insert(branch);
    }

    @Override
    public void findById(Long id) {
        branchRepository.findById(id);
    }

    @Override
    public void findAllStaffByBranchId(Long id) {
        branchRepository.findAllStaffByBranchId(id);
    }

    @Override
    public void findAllBranches() {
        branchRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
        branchRepository.removeById(id);
    }
}
