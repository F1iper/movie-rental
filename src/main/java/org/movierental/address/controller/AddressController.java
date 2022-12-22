package org.movierental.address.controller;

import org.movierental.address.entity.Address;
import org.movierental.address.service.AddAddressService;

public class AddressController {

    private final AddAddressService addAddressService;

    public AddressController() {
        this.addAddressService = new AddAddressService();
    }

    public void addAddress(Address address) {
        addAddressService.execute(address);
    }
}
