package org.movierental.address.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.service.AddressService;

import java.util.List;

@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    public boolean add(Address address) {
        return addressService.add(address);
    }

    public Address findById(Long id) {
        return addressService.findById(id);
    }

    public List<Address> findByStreet(String street) {
        return addressService.findByStreet(street);
    }

    public List<Address> findByCity(String city) {
        return addressService.findByCity(city);
    }

    public List<Address> findByState(String state) {
        return addressService.findByState(state);
    }

    public List<Address> findByZipCode(String zipCode) {
        return addressService.findByZipCode(zipCode);
    }

    public List<Address> findAll() {
        return addressService.findAll();
    }

    public boolean removeById(Long id) {
        return addressService.removeById(id);
    }
}
