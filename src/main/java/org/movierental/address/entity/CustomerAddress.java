package org.movierental.address.entity;

import lombok.Data;

@Data
public class CustomerAddress {
    private Long customerId;
    private Long addressId;
}