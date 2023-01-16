package org.movierental.branch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Branch {
    private Long branchId;
    private String name;

    public Branch() {
    }
}