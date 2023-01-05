package org.movierental.staff.repository;

import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.util.List;

public interface StaffRepository {

    boolean insert(Staff staff);

    Staff findById(Long id);

    List<Staff> findByFirstname(String firstname);

    List<Staff> findByLastname(String lastname);

    List<Staff> findByPositionId(Long positionId);

    List<Staff> findBySalaryRange(int min, int max);

    List<Staff> findAll();

    List<Position> getPositions();

    boolean removeById(Long id);
}
