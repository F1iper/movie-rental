package org.movierental.address.service.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movierental.address.entity.Address;
import org.movierental.address.repository.AddressRepository;
import org.movierental.address.service.AddressServiceImpl;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
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
    @DisplayName("Add address")
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
    @DisplayName("Find by id")
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
    @DisplayName("Find by street")
    void shouldFindAllAddressesByStreet() {
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

    @Test
    @DisplayName("Find by city")
    void shouldFindAllAddressesByCity() {
        //given
        String city = "Warsaw";

        List<Address> addresses = singletonList(address);

        when(addressRepository.findByCity(city)).thenReturn(addresses);

        //when
        List<Address> resultAddresses = addressService.findByCity(city);

        //then
        assertEquals(addresses, resultAddresses);
        assertEquals(1, resultAddresses.size());
        verify(addressRepository).findByCity(city);
    }

    @Test
    @DisplayName("Find by state")
    void shouldFindAllAddressesByState() {
        //given
        String state = "Mazowieckie";
        List<Address> addresses = singletonList(address);

        when(addressRepository.findByState(state)).thenReturn(addresses);

        //when
        List<Address> resultAddresses = addressService.findByState(state);

        //then
        assertEquals(addresses, resultAddresses);
        verify(addressRepository).findByState(state);
    }

    @Test
    @DisplayName("Find by zip code")
    void shouldFindAllAddressesByZipCode() {
        //given
        String zipCode = "05-100";
        List<Address> addresses = singletonList(address);

        when(addressRepository.findByZipCode(zipCode)).thenReturn(addresses);

        //when
        List<Address> resultAddresses = addressService.findByZipCode(zipCode);

        //then
        assertEquals(addresses, resultAddresses);
        verify(addressRepository).findByZipCode(zipCode);
    }

    @Test
    @DisplayName("Find all")
    void shouldFindAllAddresses() {
        //given
        List<Address> addresses = Arrays.asList(address, address2);

        when(addressRepository.findAll()).thenReturn(addresses);

        //when
        List<Address> resultAddresses = addressService.findAll();

        //then
        assertEquals(addresses, resultAddresses);
        verify(addressRepository).findAll();
    }

    @Test
    @DisplayName("Remove by id")
    void shouldRemoveById() {
        //given
        Long id = 1L;

        when(addressRepository.removeById(id)).thenReturn(true);

        //when
        boolean result = addressService.removeById(id);

        //then
        assertTrue(result);
        verify(addressRepository).removeById(id);
    }
}