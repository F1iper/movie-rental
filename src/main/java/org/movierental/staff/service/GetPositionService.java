package org.movierental.staff.service;

import org.movierental.repository.QueryExecutor;

public class GetPositionService {

    public void get() {
        QueryExecutor.getPositions();
    }
}
