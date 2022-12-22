package org.movierental.address.service;

import org.movierental.repository.QueryExecutor;

public class SearchAddressService {

    public void searchAddressById(Long id) {
        QueryExecutor.searchAddressById(id);
    }

    public void searchAddressByStreet(String street) {
        QueryExecutor.searchAddressByStreet(street);
    }

    public void searchAddressByCity(String city) {
        QueryExecutor.searchAddressByCity(city);
    }

    public void searchAddressByState(String state) {
        QueryExecutor.searchAddressByState(state);
    }

    public void searchAddressByZipCode(String zipCode) {
        QueryExecutor.searchAddressByZipCode(zipCode);
    }

    public void searchAllAddresses() {
        QueryExecutor.searchAllAddresses();
    }
}
