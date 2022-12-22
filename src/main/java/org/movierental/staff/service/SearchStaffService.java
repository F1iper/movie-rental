package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;

public class SearchStaffService {

    public void findStaffById(Long id) {
        QueryExecutor.findStaffById(id);
    }

    public void findStaffByFirstname(String firstname) {
        QueryExecutor.findStaffByFirstname(firstname);
    }

    public void findStaffByLastname(String lastname) {
        QueryExecutor.findStaffByLastname(lastname);
    }

    public void findStaffBySalaryRange(int min, int max) {
        QueryExecutor.findStaffBySalaryRange(min, max);
    }

    public void findStaffByPositionId(Long positionId) {
        QueryExecutor.findStaffByPositionId(positionId);
    }

    public void findAllStaff() {
        QueryExecutor.findAllStaff();
    }
}
