package org.movierental.staff.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.StaffService;

import java.util.List;

@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    public boolean add(Staff staff) {
        return staffService.add(staff);
    }

    public boolean removeById(Long id) {
        return staffService.removeById(id);
    }

    public List<Staff> findAll() {
        return staffService.findAll();
    }

    public List<Position> getPositions() {
        return staffService.getPositions();
    }

    public Staff findStaffById(long id) {
        return staffService.findById(id);
    }

    public List<Staff> findByFirstname(String firstname) {
        return staffService.findByFirstname(firstname);
    }

    public List<Staff> findByLastname(String lastname) {
        return staffService.findByLastname(lastname);
    }

    public List<Staff> findBySalaryRange(int min, int max) {
        return staffService.findBySalaryRange(min, max);
    }

    public List<Staff> findByPositionId(long positionId) {
        return staffService.findByPositionId(positionId);
    }
}
