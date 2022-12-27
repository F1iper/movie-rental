package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Staff;

public class StaffServiceImpl implements StaffService {

    public void add(Staff staff) {
        QueryExecutor.insertStaff(staff);
    }

    public void getPositions() {
        QueryExecutor.getPositions();
    }

    public void findById(Long id) {
        QueryExecutor.findStaffById(id);
    }

    public void findByFirstname(String firstname) {
        QueryExecutor.findStaffByFirstname(firstname);
    }

    public void findByLastname(String lastname) {
        QueryExecutor.findStaffByLastname(lastname);
    }

    public void findBySalaryRange(int min, int max) {
        QueryExecutor.findStaffBySalaryRange(min, max);
    }

    public void findByPositionId(Long positionId) {
        QueryExecutor.findStaffByPositionId(positionId);
    }

    public void findAll() {
        QueryExecutor.findAllStaff();
    }

    public void removeById(Long id) {
        QueryExecutor.removeStaffById(id);
    }
}
