package org.movierental.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"addressId", "branch", "customer", "staff"})
public class Address {

    private Long addressId;
    private Branch branch;
    private Customer customer;
    private Staff staff;
    private String street;
    private String city;
    private String state;
    private String zip_code;
    private String phone;
}
