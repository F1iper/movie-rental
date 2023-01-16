package org.movierental.address.service;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.repository.AddressRepository;

import java.util.List;

@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public boolean add(Address address) {
        return addressRepository.insert(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        return addressRepository.findByZipCode(zipCode);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public boolean removeById(Long id) {
        return addressRepository.removeById(id);
    }
}
