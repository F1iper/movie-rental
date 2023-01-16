package org.movierental.address.service;


import org.movierental.address.entity.Address;

import java.util.List;

public interface AddressService {

    boolean add(Address address);

    Address findById(Long id);

    List<Address> findByStreet(String street);

    List<Address> findByCity(String city);

    List<Address> findByState(String state);

    List<Address> findByZipCode(String zipCode);

    List<Address> findAll();

    boolean removeById(Long id);
}
