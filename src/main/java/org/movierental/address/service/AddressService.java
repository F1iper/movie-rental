package org.movierental.address.service;


import org.movierental.address.entity.Address;

public interface AddressService {

    void add(Address address);

    void findById(Long id);

    void findByStreet(String street);

    void findByCity(String city);

    void findByState(String state);

    void findByZipCode(String zipCode);

    void findAll();
}
