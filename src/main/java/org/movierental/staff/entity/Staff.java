package org.movierental.staff.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"staffId", "branchId"})
public class Staff {

    private Long staffId;
    private String firstname;
    private String lastname;
    private Double salary;
    private Long positionId;
    private Long branchId;

}