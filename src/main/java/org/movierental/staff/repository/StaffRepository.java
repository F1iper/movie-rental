package org.movierental.staff.repository;

import org.movierental.staff.entity.Staff;

public interface StaffRepository {

    void insert(Staff staff);

    void findById(Long id);

    void findByFirstname(String firstname);

    void findByLastname(String lastname);

    void findByPositionId(Long positionId);

    void findBySalaryRange(int min, int max);

    void findAll();

    void getPositions();

    void removeById(Long id);
}
