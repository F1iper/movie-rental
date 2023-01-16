package org.movierental.address.service;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.repository.AddressRepository;

import java.util.List;

/**
 * Implementation of the {@link AddressService} interface
 *
 * @author Filip Timofiejew
 */
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    /**
     * This method is used to add a address to the address repository.
     *
     * @param address The address object that needs to be added.
     * @return true if address is added successfully, false otherwise
     */
    @Override
    public boolean add(Address address) {
        return addressRepository.insert(address);
    }

    /**
     * This method is used to find a address by id
     *
     * @param id The id of the address
     * @return The address with the given id
     */
    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    /**
     * This method is used to find addresses by street
     *
     * @param street The street of the address
     * @return The list of addresses with the given street
     */
    @Override
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    /**
     * This method is used to find addresses by city
     *
     * @param city The city of the address
     * @return The list of addresses with the given city
     */
    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    /**
     * This method is used to find addresses by state
     *
     * @param state The state of the address
     * @return The list of addresses with the given state
     */
    @Override
    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    /**
     * This method is used to find movies by title
     *
     * @param zipCode The zip code of the address
     * @return The list of addresses with the given zip code
     */
    @Override
    public List<Address> findByZipCode(String zipCode) {
        return addressRepository.findByZipCode(zipCode);
    }

    /**
     * This method is used to find all addresses
     *
     * @return The list of all addresses
     */
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    /**
     * This method is used to remove an address by id
     *
     * @param id The id of the address to remove
     * @return true if removal is successful, false otherwise
     */
    @Override
    public boolean removeById(Long id) {
        return addressRepository.removeById(id);
    }
}
