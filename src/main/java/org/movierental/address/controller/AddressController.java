package org.movierental.address.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.service.AddressService;

@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    public void add(Address address) {
        addressService.add(address);
    }

    public void findById(Long id) {
        addressService.findById(id);
    }

    public void findByStreet(String street) {
        addressService.findByStreet(street);
    }

    public void findByCity(String city) {
        addressService.findByCity(city);
    }

    public void findByState(String state) {
        addressService.findByState(state);
    }

    public void findByZipCode(String zipCode) {
        addressService.findByZipCode(zipCode);
    }

    public void findAll() {
        addressService.findAll();
    }

    public void removeById(Long id) {
        addressService.removeById(id);
    }
}
