package org.movierental.address.entity;

import lombok.Data;

@Data
public class Address {

    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip_code;
    private String phone;

    public Address(Long addressId, String street, String city, String state, String zip_code, String phone) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.phone = phone;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "\nid: " + addressId + ", street: " + street + ", city: " + city + ", state: " + state +
                ", zip code: " + zip_code + ", phone: " + phone;
    }
}
