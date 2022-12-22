package org.movierental.staff.service;

import org.movierental.staff.entity.Staff;
import org.movierental.repository.QueryExecutor;

public class AddStaffService {

    public void add(Staff staff) {
        QueryExecutor.insertStaff(staff);
    }
}
