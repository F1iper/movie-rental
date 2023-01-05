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

    public Staff(Long staffId, String firstname, String lastname, Double salary, Long positionId, Long branchId) {
        this.staffId = staffId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.positionId = positionId;
        this.branchId = branchId;
    }

    public Staff() {
    }
}