package org.movierental.staff.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.movierental.address.entity.Address;
import org.movierental.branch.entity.Branch;

@Getter
@Setter
@ToString
public class Staff {

    private Long staffId;
    private String firstname;
    private String lastname;
    private Double salary;
    private Long position_id;
    private Long branch_id;

}