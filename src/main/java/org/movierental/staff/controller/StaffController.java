package org.movierental.staff.controller;

import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.AddStaffService;
import org.movierental.staff.service.GetPositionService;
import org.movierental.staff.service.GetStaffAsListService;
import org.movierental.staff.service.RemoveStaffService;

public class StaffController {

    private final AddStaffService addStaffService;
    private final GetPositionService getPositionService;
    private final RemoveStaffService removeStaffService;
    private final GetStaffAsListService getStaffAsListService;

    public StaffController() {
        this.addStaffService = new AddStaffService();
        this.getPositionService = new GetPositionService();
        this.removeStaffService = new RemoveStaffService();
        this.getStaffAsListService = new GetStaffAsListService();
    }

    public void addStaff(Staff staff) {
        addStaffService.add(staff);
    }

    public void removeStaff(Long id) {
        removeStaffService.remove(id);
    }

    public void getAllStaff() {
        getStaffAsListService.getAll();
    }

    public void getPositions() {
        getPositionService.get();
    }
}
