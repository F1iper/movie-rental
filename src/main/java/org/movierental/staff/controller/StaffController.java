package org.movierental.staff.controller;

import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.AddStaffService;
import org.movierental.staff.service.GetPositionService;

public class StaffController {

    private final AddStaffService addStaffService;
    private final GetPositionService getPositionService;

    public StaffController() {
        this.addStaffService = new AddStaffService();
        this.getPositionService = new GetPositionService();
    }

    public void addStaff(Staff staff) {
        addStaffService.add(staff);
    }

    public void getPositions() {
        getPositionService.get();
    }
}
