package org.movierental.staff.service;

import lombok.RequiredArgsConstructor;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.repository.StaffRepository;

import java.util.List;

@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public boolean add(Staff staff) {
        return staffRepository.insert(staff);
    }

    @Override
    public List<Position> getPositions() {
        return staffRepository.getPositions();
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public List<Staff> findByFirstname(String firstname) {
        return staffRepository.findByFirstname(firstname);
    }

    @Override
    public List<Staff> findByLastname(String lastname) {
        return staffRepository.findByLastname(lastname);
    }

    @Override
    public List<Staff> findBySalaryRange(int min, int max) {
        return staffRepository.findBySalaryRange(min, max);
    }

    @Override
    public List<Staff> findByPositionId(Long positionId) {
        return staffRepository.findByPositionId(positionId);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public boolean removeById(Long id) {
        return staffRepository.removeById(id);
    }
}
