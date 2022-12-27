package org.movierental.address.controller;

import org.movierental.address.entity.Address;
import org.movierental.address.service.AddressService;
import org.movierental.address.service.AddressServiceImpl;

public class AddressController {

    private final AddressService addressService;

    public AddressController() {
        this.addressService = new AddressServiceImpl();
    }

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
