package org.movierental.address.service;

import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

public class AddAddressService {

    public void add(Address address) {
        QueryExecutor.insertAddress(address);
    }
}
