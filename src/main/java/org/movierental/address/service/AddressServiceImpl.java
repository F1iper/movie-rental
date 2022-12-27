package org.movierental.address.service;

import org.movierental.address.entity.Address;
import org.movierental.repository.QueryExecutor;

public class AddressServiceImpl implements AddressService {

    @Override
    public void add(Address address) {
        QueryExecutor.insertAddress(address);
    }

    @Override
    public void findById(Long id) {
        QueryExecutor.searchAddressById(id);
    }

    @Override
    public void findByStreet(String street) {
        QueryExecutor.searchAddressByStreet(street);
    }

    @Override
    public void findByCity(String city) {
        QueryExecutor.searchAddressByCity(city);
    }

    @Override
    public void findByState(String state) {
        QueryExecutor.searchAddressByState(state);
    }

    @Override
    public void findByZipCode(String zipCode) {
        QueryExecutor.searchAddressByZipCode(zipCode);
    }

    @Override
    public void findAll() {
        QueryExecutor.searchAllAddresses();
    }

    @Override
    public void removeById(Long id) {
        QueryExecutor.removeAddressById(id);
    }
}
