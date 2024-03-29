package org.movierental.staff.service;

import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.util.List;

public interface StaffService {

    boolean add(Staff staff);

    List<Position> getPositions();

    Staff findById(Long id);

    List<Staff> findByFirstname(String firstname);

    List<Staff> findByLastname(String lastname);

    List<Staff> findBySalaryRange(double min, double max);

    List<Staff> findByPositionId(Long positionId);

    List<Staff> findAll();

    boolean removeById(Long id);

    List<Staff> findAllStaffByBranchId(Long branchId);
    // TODO: 1/16/2023 make controller use return value
}
