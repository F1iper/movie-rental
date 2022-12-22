package org.movierental.address.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip_code;
    private String phone;
}
