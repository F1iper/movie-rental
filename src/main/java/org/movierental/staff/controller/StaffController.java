package org.movierental.staff.controller;

import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.*;

public class StaffController {

    private final AddStaffService addStaffService;
    private final GetPositionService getPositionService;
    private final RemoveStaffService removeStaffService;
    private final SearchStaffService searchStaffService;

    public StaffController() {
        this.addStaffService = new AddStaffService();
        this.getPositionService = new GetPositionService();
        this.removeStaffService = new RemoveStaffService();
        this.searchStaffService = new SearchStaffService();
    }

    public void addStaff(Staff staff) {
        addStaffService.add(staff);
    }

    public void removeStaffById(Long id) {
        removeStaffService.remove(id);
    }

    public void findAllStaff() {
        searchStaffService.findAllStaff();
    }

    public void getPositions() {
        getPositionService.getPositions();
    }

    public void findStaffById(long id) {
        searchStaffService.findStaffById(id);
    }

    public void findStaffByFirstname(String firstname) {
        searchStaffService.findStaffByFirstname(firstname);
    }

    public void findStaffByLastname(String lastname) {
        searchStaffService.findStaffByLastname(lastname);
    }

    public void findStaffBySalaryRange(int min, int max) {
        searchStaffService.findStaffBySalaryRange(min, max);
    }

    public void findStaffByPositionId(long positionId) {
        searchStaffService.findStaffByPositionId(positionId);
    }
}
