package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;
import org.movierental.staff.entity.Staff;

public class StaffServiceImpl implements StaffService {

    @Override
    public void add(Staff staff) {
        QueryExecutor.insertStaff(staff);
    }

    @Override
    public void getPositions() {
        QueryExecutor.getPositions();
    }

    @Override
    public void findById(Long id) {
        QueryExecutor.findStaffById(id);
    }

    @Override
    public void findByFirstname(String firstname) {
        QueryExecutor.findStaffByFirstname(firstname);
    }

    @Override
    public void findByLastname(String lastname) {
        QueryExecutor.findStaffByLastname(lastname);
    }

    @Override
    public void findBySalaryRange(int min, int max) {
        QueryExecutor.findStaffBySalaryRange(min, max);
    }

    @Override
    public void findByPositionId(Long positionId) {
        QueryExecutor.findStaffByPositionId(positionId);
    }

    @Override
    public void findAll() {
        QueryExecutor.findAllStaff();
    }

    @Override
    public void removeById(Long id) {
        QueryExecutor.removeStaffById(id);
    }
}
