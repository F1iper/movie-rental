package org.movierental.address.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.service.AddressService;

@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    public void addAddress(Address address) {
        addressService.add(address);
    }

    public void searchAddressById(Long id) {
        addressService.findById(id);
    }

    public void searchAddressByStreet(String street) {
        addressService.findByStreet(street);
    }

    public void searchAddressByCity(String city) {
        addressService.findByCity(city);
    }

    public void searchAddressByState(String state) {
        addressService.findByState(state);
    }

    public void searchAddressByZipCode(String zipCode) {
        addressService.findByZipCode(zipCode);
    }

    public void searchAllAddresses() {
        addressService.findAll();
    }
}
