package org.movierental.address.repository;

import org.movierental.address.entity.Address;

public interface AddressRepository {

    void insert(Address address);

    void findById(Long id);

    void findByStreet(String street);

    void findByCity(String city);

    void findByState(String state);

    void findByZipCode(String zipCode);

    void findAll();

    void removeById(Long id);
}
