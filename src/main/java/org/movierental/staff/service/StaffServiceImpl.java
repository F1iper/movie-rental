package org.movierental.staff.service;

import lombok.RequiredArgsConstructor;
import org.movierental.address.service.AddressService;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.repository.StaffRepository;

import java.util.List;

/**
 * StaffServiceImpl class implements the StaffService interface and
 * provides the implementation for its methods.
 * It uses StaffRepository to perform the CRUD operations on Staff objects.
 *
 * @author Filip Timofiejew
 *
 */
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    /**
     * This method is used to add a new Staff object to the system.
     *
     * @param staff Staff object to be added.
     * @return true if the Staff object is successfully added, false otherwise.
     */
    @Override
    public boolean add(Staff staff) {
        return staffRepository.add(staff);
    }

    /**
     * This method is used to return a list of all Position objects in the system.
     *
     * @return List of all Position objects.
     */
    @Override
    public List<Position> getPositions() {
        return staffRepository.getPositions();
    }

    /**
     * This method is used to return a Staff object by its ID.
     *
     * @param id ID of the Staff object.
     * @return Staff object with the given ID.
     */
    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id);
    }

    /**
     * This method is used to return a list of Staff objects with the given first name.
     *
     * @param firstname First name of the Staff objects.
     * @return List of Staff objects with the given first name.
     */
    @Override
    public List<Staff> findByFirstname(String firstname) {
        return staffRepository.findByFirstname(firstname);
    }

    /**
     * This method is used to return a list of Staff objects with the given last name.
     *
     * @param lastname Last name of the Staff objects.
     * @return List of Staff objects with the given last name.
     */
    @Override
    public List<Staff> findByLastname(String lastname) {
        return staffRepository.findByLastname(lastname);
    }

    /**
     * This method is used to return a list of Staff objects with salary within the given range.
     *
     * @param min Minimum salary of the Staff objects.
     * @param max Maximum salary of the Staff objects.
     * @return List of Staff objects with salary within the given range.
     */
    @Override
    public List<Staff> findBySalaryRange(double min, double max) {
        return staffRepository.findBySalaryRange(min, max);
    }

    /**
     * This method is used to return a list of Staff objects with the given position ID.
     *
     * @param positionId ID of the Position of the Staff objects.
     * @return List of Staff objects with the given position ID.
     */
    @Override
    public List<Staff> findByPositionId(Long positionId) {
        return staffRepository.findByPositionId(positionId);
    }

    /**
     * This method is used to return a list of all Staff objects in the system.
     *
     * @return List of all Staff objects.
     */
    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    /**
     * This method is used to remove a Staff object from the system by its ID.
     *
     * @param id ID of the Staff object to be removed.
     * @return true if the Staff object is successfully removed, false otherwise.
     */
    @Override
    public boolean removeById(Long id) {
        return staffRepository.removeById(id);
    }
}
