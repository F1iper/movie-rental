package org.movierental.address.service;

import lombok.RequiredArgsConstructor;
import org.movierental.address.entity.Address;
import org.movierental.address.repository.AddressRepository;

@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void add(Address address) {
        addressRepository.insert(address);
    }

    @Override
    public void findById(Long id) {
        addressRepository.findById(id);
    }

    @Override
    public void findByStreet(String street) {
        addressRepository.findByStreet(street);
    }

    @Override
    public void findByCity(String city) {
        addressRepository.findByCity(city);
    }

    @Override
    public void findByState(String state) {
        addressRepository.findByState(state);
    }

    @Override
    public void findByZipCode(String zipCode) {
        addressRepository.findByZipCode(zipCode);
    }

    @Override
    public void findAll() {
        addressRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
        addressRepository.removeById(id);
    }
}
