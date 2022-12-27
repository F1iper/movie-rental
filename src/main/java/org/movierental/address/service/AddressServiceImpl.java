package org.movierental.address.service;

import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

public class AddressServiceImpl implements AddressService {

    public void add(Address address) {
        QueryExecutor.insertAddress(address);
    }

    public void findById(Long id) {
        QueryExecutor.searchAddressById(id);
    }

    public void findByStreet(String street) {
        QueryExecutor.searchAddressByStreet(street);
    }

    public void findByCity(String city) {
        QueryExecutor.searchAddressByCity(city);
    }

    public void findByState(String state) {
        QueryExecutor.searchAddressByState(state);
    }

    public void findByZipCode(String zipCode) {
        QueryExecutor.searchAddressByZipCode(zipCode);
    }

    public void findAll() {
        QueryExecutor.searchAllAddresses();
    }
}
