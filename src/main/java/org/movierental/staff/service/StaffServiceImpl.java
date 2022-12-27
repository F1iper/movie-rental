package org.movierental.staff.service;

import lombok.RequiredArgsConstructor;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.repository.StaffRepository;

@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void add(Staff staff) {
        staffRepository.insert(staff);
    }

    @Override
    public void getPositions() {
        staffRepository.getPositions();
    }

    @Override
    public void findById(Long id) {
        staffRepository.findById(id);
    }

    @Override
    public void findByFirstname(String firstname) {
        staffRepository.findByFirstname(firstname);
    }

    @Override
    public void findByLastname(String lastname) {
        staffRepository.findByLastname(lastname);
    }

    @Override
    public void findBySalaryRange(int min, int max) {
        staffRepository.findBySalaryRange(min, max);
    }

    @Override
    public void findByPositionId(Long positionId) {
        staffRepository.findByPositionId(positionId);
    }

    @Override
    public void findAll() {
        staffRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
        staffRepository.removeById(id);
    }
}
