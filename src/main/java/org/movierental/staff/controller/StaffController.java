package org.movierental.staff.controller;

import lombok.RequiredArgsConstructor;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.service.StaffService;

import java.util.List;

/**
 * StaffController class handles all requests related to Staff objects.
 * It uses StaffService to perform the CRUD operations on Staff objects.
 *
 * @author Filip Timofiejew
 * @version 1.0
 */
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    /**
     * This method is used to add a new Staff object to the system.
     *
     * @param staff Staff object to be added.
     * @return true if the Staff object is successfully added, false otherwise.
     */
    public boolean add(Staff staff) {
        return staffService.add(staff);
    }

    /**
     * Removes a Staff object from the system by its ID.
     *
     * @param id ID of the Staff object to be removed.
     * @return true if the Staff object is successfully removed, false otherwise.
     */
    public boolean removeById(Long id) {
        return staffService.removeById(id);
    }

    /**
     * This method is used to return a list of all Staff objects in the system.
     *
     * @return List of all Staff objects.
     */
    public List<Staff> findAll() {
        return staffService.findAll();
    }

    /**
     * This method is used to return a list of all Position objects in the system.
     *
     * @return List of all Position objects.
     */
    public List<Position> getPositions() {
        return staffService.getPositions();
    }

    /**
     * This method is used to return a Staff object by its ID.
     *
     * @param id ID of the Staff object.
     * @return Staff object with the given ID.
     */
    public Staff findStaffById(long id) {
        return staffService.findById(id);
    }

    /**
     * This method is used to return a list of Staff objects with the given first name.
     *
     * @param firstname First name of the Staff objects.
     * @return List of Staff objects with the given first name.
     */
    public List<Staff> findByFirstname(String firstname) {
        return staffService.findByFirstname(firstname);
    }

    /**
     * This method is used to return a list of Staff objects with the given last name.
     *
     * @param lastname Last name of the Staff objects.
     * @return List of Staff objects with the given last name.
     */
    public List<Staff> findByLastname(String lastname) {
        return staffService.findByLastname(lastname);
    }

    /**
     * This method is used to return a list of Staff objects with salary within the given range.
     *
     * @param min Minimum salary of the Staff objects.
     * @param max Maximum salary of the Staff objects.
     * @return List of Staff objects with salary within the given range.
     */
    public List<Staff> findBySalaryRange(int min, int max) {
        return staffService.findBySalaryRange(min, max);
    }

    /**
     * This method is used to return
     * a list of Staff objects with the given position ID.
     *
     * @param positionId ID of the Position of the Staff objects.
     * @return List of Staff objects with the given position ID.
     */
    public List<Staff> findByPositionId(long positionId) {
        return staffService.findByPositionId(positionId);
    }
}
