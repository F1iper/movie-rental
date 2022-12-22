package org.movierental.address.controller;

import org.movierental.address.entity.Address;
import org.movierental.address.service.AddAddressService;
import org.movierental.address.service.SearchAddressService;

public class AddressController {

    private final AddAddressService addAddressService;
    private final SearchAddressService searchAddressService;

    public AddressController() {
        this.addAddressService = new AddAddressService();
        this.searchAddressService = new SearchAddressService();
    }

    public void addAddress(Address address) {
        addAddressService.add(address);
    }

    public void searchAddressById(Long id) {
        searchAddressService.searchAddressById(id);
    }

    public void searchAddressByStreet(String street) {
        searchAddressService.searchAddressByStreet(street);
    }

    public void searchAddressByCity(String city) {
        searchAddressService.searchAddressByCity(city);
    }

    public void searchAddressByState(String state) {
        searchAddressService.searchAddressByState(state);
    }

    public void searchAddressByZipCode(String zipCode) {
        searchAddressService.searchAddressByZipCode(zipCode);
    }

    public void searchAllAddresses() {
        searchAddressService.searchAllAddresses();
    }
}
