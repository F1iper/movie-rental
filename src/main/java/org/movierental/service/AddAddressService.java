package org.movierental.service;

import org.movierental.entity.Address;
import org.movierental.repository.QueryExecutor;

public class AddAddressService {

    public void execute(Address address) {
        QueryExecutor.insertAddress(address);
    }
}
