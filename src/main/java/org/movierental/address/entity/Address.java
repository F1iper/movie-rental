package org.movierental.address.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip_code;
    private String phone;

    @Override
    public String toString() {
        return "Street: " + street + ",\ncity: " + city + ",\nstate: " + state +
                ",\nzip code: " + zip_code + ",\nphone: " + phone;
    }
}
