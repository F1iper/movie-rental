package org.movierental.address.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.service.AddressService;

import java.util.List;

/**
 * The AddressController class is responsible for handling
 * requests and delegating them to the AddressService class
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * This method is used to add an address.
     *
     * @param address The address object that needs to be added.
     * @return true if address is added successfully, false otherwise
     */
    public boolean add(Address address) {
        return addressService.add(address);
    }

    /**
     * This method is used to find an address by id.
     *
     * @param id The id of the address
     * @return The address with the given id
     */
    public Address findById(Long id) {
        return addressService.findById(id);
    }

    /**
     * This method is used to find addresses by given street.
     *
     * @param street The street of the address
     * @return The list of addresses with given street
     */
    public List<Address> findByStreet(String street) {
        return addressService.findByStreet(street);
    }

    /**
     * This method is used to find addresses by given city.
     *
     * @param city The city of the address
     * @return The list of addresses with given city
     */
    public List<Address> findByCity(String city) {
        return addressService.findByCity(city);
    }

    /**
     * This method is used to find addresses by given state.
     *
     * @param state The state of the address
     * @return The list of addresses with given state
     */
    public List<Address> findByState(String state) {
        return addressService.findByState(state);
    }

    /**
     * This method is used to find addresses by given zip code.
     *
     * @param zipCode The zip code of the address
     * @return The list of addresses with given zip code
     */
    public List<Address> findByZipCode(String zipCode) {
        return addressService.findByZipCode(zipCode);
    }

    /**
     * This method is used to find all addresses.
     *
     * @return The list of all addresses
     */
    public List<Address> findAll() {
        return addressService.findAll();
    }

    /**
     * This method is used to remove an address by id.
     *
     * @param id The id of the address to remove
     * @return true if removal is successful, false otherwise
     */
    public boolean removeById(Long id) {
        return addressService.removeById(id);
    }
}
