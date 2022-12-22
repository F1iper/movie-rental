package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;

public class RemoveStaffService {

    public void remove(Long id) {
        QueryExecutor.removeStaffById(id);
    }
}
