package org.movierental.controller;

import org.movierental.entity.Address;
import org.movierental.service.AddAddressService;

public class AddressController {

    private final AddAddressService addAddressService;

    public AddressController() {
        this.addAddressService = new AddAddressService();
    }

    public void addAddress(Address address) {
        addAddressService.execute(address);
    }
}
