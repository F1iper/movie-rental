package org.movierental.branch.repository;

import org.movierental.branch.entity.Branch;

import java.util.List;

public interface BranchRepository {

    boolean insert(Branch branch);

    Branch findById(Long id);

    List<Branch> findAll();

    boolean removeById(Long id);

    List<Branch> findByName(String branchName);

    boolean updateName(long id, String newName);
}
