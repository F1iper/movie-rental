package org.movierental.staff.controller;

import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.StaffService;
import org.movierental.staff.service.StaffServiceImpl;

public class StaffController {

    private final StaffService staffService;

    public StaffController() {
        this.staffService = new StaffServiceImpl();
    }

    public void add(Staff staff) {
        staffService.add(staff);
    }

    public void removeById(Long id) {
        staffService.removeById(id);
    }

    public void findAll() {
        staffService.findAll();
    }

    public void getPositions() {
        staffService.getPositions();
    }

    public void findStaffById(long id) {
        staffService.findById(id);
    }

    public void findByFirstname(String firstname) {
        staffService.findByFirstname(firstname);
    }

    public void findByLastname(String lastname) {
        staffService.findByLastname(lastname);
    }

    public void findBySalaryRange(int min, int max) {
        staffService.findBySalaryRange(min, max);
    }

    public void findByPositionId(long positionId) {
        staffService.findByPositionId(positionId);
    }
}
