package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;

public class GetStaffAsListService {

    public void getAll() {
        QueryExecutor.getStaffList();
    }
}
