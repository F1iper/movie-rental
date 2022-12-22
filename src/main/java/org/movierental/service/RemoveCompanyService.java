package org.movierental.service;

import org.movierental.repository.QueryExecutor;

public class RemoveCompanyService {

    public void removeById(Long id) {
        QueryExecutor.removeCompanyById(id);
    }
}
