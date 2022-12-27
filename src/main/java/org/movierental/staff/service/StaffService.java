package org.movierental.staff.service;

import org.movierental.staff.entity.Staff;

public interface StaffService {

    void add(Staff staff);

    void getPositions();

    void findById(Long id);

    void findByFirstname(String firstname);

    void findByLastname(String lastname);

    void findBySalaryRange(int min, int max);

    void findByPositionId(Long positionId);

    void findAll();

    void removeById(Long id);
}
