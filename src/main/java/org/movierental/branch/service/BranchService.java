package org.movierental.branch.service;

import org.movierental.branch.entity.Branch;

import java.util.List;

public interface BranchService {

    boolean add(Branch branch);

    Branch findById(Long id);

    List<Branch> findAllBranches();

    boolean removeById(Long id);

    List<Branch> findByName(String branchName);

    boolean updateName(long id, String newName);
}
