package org.movierental.address.entity;

import lombok.Data;

@Data
public class StaffAddress {
    private Long staffId;
    private Long addressId;

    public StaffAddress(Long staffId, Long addressId) {
        this.staffId = staffId;
        this.addressId = addressId;
    }
}
