package org.movierental.address.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movierental.address.entity.Address;
import org.movierental.address.repository.AddressRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private Address address;
    private Address address2;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void setup() {
        address = new Address(
                1L,
                "Mokotowska",
                "Warsaw",
                "Mazowieckie",
                "05-100",
                "+48 500 600 200");
        address2 = new Address(
                5L,
                "Mokotowska",
                "Krakow",
                "Malopolskie",
                "15-999",
                "+48 500 399 588");

    }

    @Test
    void addAddress_returnTrue() {
        //given
        when(addressRepository.insert(any(Address.class))).thenReturn(true);

        //when
        boolean result = addressService.add(address);

        //then
        assertTrue(result);
        verify(addressRepository).insert(address);
    }

    @Test
    void shouldReturnAddressById() {
        //given
        Long id = 1L;
        when(addressRepository.findById(id)).thenReturn(address);

        //when
        Address resultAddress = addressService.findById(id);

        //then
        assertEquals(id, resultAddress.getAddressId());
        verify(addressRepository).findById(id);
    }

    @Test
    void shouldReturnListOfAddressesWithProvidedStreet() {
        //given
        String street = "Mokotowska";
        List<Address> addresses = Arrays.asList(address, address2);

        when(addressRepository.findByStreet(street)).thenReturn(addresses);

        //when
        List<Address> resultAddresses = addressService.findByStreet(street);

        //then
        assertEquals(addresses, resultAddresses);
        verify(addressRepository).findByStreet(street);
    }
}