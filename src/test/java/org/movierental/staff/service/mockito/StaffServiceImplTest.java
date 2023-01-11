package org.movierental.staff.service.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;
import org.movierental.staff.repository.StaffRepository;
import org.movierental.staff.service.StaffServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StaffServiceImplTest {

    private Staff staff1;
    private Staff staff2;

    @InjectMocks
    private StaffServiceImpl staffService;

    @Mock
    private StaffRepository staffRepository;

    @BeforeEach
    public void setup() {
        staff1 = new Staff(
                1L,
                "Jack",
                "Sparrow",
                2500.50,
                1L,
                1L
        );

        staff2 = new Staff(
                2L,
                "John",
                "Wick",
                5500.50,
                2L,
                1L
        );
    }

    @Test
    @DisplayName("Add staff")
    void addStaff_returnTrue() {
        //given
        when(staffRepository.add(any(Staff.class))).thenReturn(true);

        //when
        boolean result = staffService.add(staff1);

        //then
        assertTrue(result);
        verify(staffRepository).add(staff1);
    }

    @Test
    @DisplayName("Find by id")
    void shouldReturnStaffById() {
        //given
        Long id = 1L;
        when(staffRepository.findById(id)).thenReturn(staff1);

        //when
        Staff resultStaff = staffService.findById(id);

        //then
        assertEquals(id, resultStaff.getStaffId());
        verify(staffRepository).findById(id);
    }

    @Test
    @DisplayName("Find by firstname")
    void shouldFindStaffByFirstname() {
        //given
        String firstname = "John";
        List<Staff> staffList = Arrays.asList(staff1, staff2);

        when(staffRepository.findByFirstname(firstname)).thenReturn(staffList);

        //when
        List<Staff> resultStaff = staffService.findByFirstname(firstname);

        //then
        assertEquals(staffList.size(), resultStaff.size());
        assertEquals(staffList.get(0).getFirstname(), resultStaff.get(0).getFirstname());
        verify(staffRepository).findByFirstname(firstname);
    }

    @Test
    @DisplayName("Find by lastname")
    void shouldFindStaffByLastname() {
        //given
        String lastname = "Wick";
        List<Staff> staffList = Arrays.asList(staff1, staff2);

        when(staffRepository.findByLastname(lastname)).thenReturn(staffList);

        //when
        List<Staff> resultStaff = staffService.findByLastname(lastname);

        //then
        assertEquals(staffList.size(), resultStaff.size());
        assertEquals(staffList.get(0).getLastname(), resultStaff.get(0).getLastname());
        verify(staffRepository).findByLastname(lastname);
    }

    @Test
    @DisplayName("Find by salary range")
    void shouldFindStaffBySalaryRange() {
        //given
        List<Staff> staffList = Arrays.asList(staff1, staff2);
        double min = 1500;
        double max = 25000;

        when(staffRepository.findBySalaryRange(min, max)).thenReturn(staffList);

        //when
        List<Staff> resultStaff = staffService.findBySalaryRange(min, max);

        //then
        assertEquals(staffList.size(), resultStaff.size());
        assertEquals(staffList.get(0).getSalary(), resultStaff.get(0).getSalary());
        verify(staffRepository).findBySalaryRange(min, max);
    }

    @Test
    @DisplayName("Find all")
    void shouldFindAllStaff() {
        //given
        List<Staff> staffList = Arrays.asList(staff1, staff2);

        when(staffRepository.findAll()).thenReturn(staffList);

        //when
        List<Staff> resultStaff = staffService.findAll();

        //then
        assertEquals(staffList.size(), resultStaff.size());
        assertEquals(staffList.get(0).getStaffId(), resultStaff.get(0).getStaffId());
        verify(staffRepository).findAll();
    }

    @Test
    @DisplayName("Remove by ID")
    void shouldRemoveStaffById() {
        //given
        Long id = 1L;

        when(staffRepository.removeById(id)).thenReturn(true);

        //when
        boolean result = staffService.removeById(id);

        //then
        assertTrue(result);
        verify(staffRepository).removeById(id);
    }

    @Test
    @DisplayName("Find by position ID")
    void shouldFindByPositionId() {
        //given
        Long positionId = 2L;

        when(staffRepository.findByPositionId(positionId)).thenReturn(Collections.singletonList(staff2));

        //when
        List<Staff> resultList = staffService.findByPositionId(positionId);

        //then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        verify(staffRepository).findByPositionId(positionId);
    }

    @Test
    @DisplayName("Find all positions")
    void shouldFindPositions() {
        //given
        List<Position> positions = Arrays.asList(new Position(1L, "BOSS")
                , new Position(2L, "LEADER"));

        when(staffRepository.getPositions()).thenReturn(positions);

        //when
        List<Position> positionList = staffService.getPositions();

        //then
        assertNotNull(positionList);
        assertEquals(2, positionList.size());
        verify(staffRepository).getPositions();
    }
}
