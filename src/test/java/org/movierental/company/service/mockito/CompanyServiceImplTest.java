package org.movierental.company.service.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movierental.company.entity.Company;
import org.movierental.company.repository.CompanyRepository;
import org.movierental.company.service.CompanyServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    private Company company1;
    private Company company2;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        company1 = new Company(
                1L,
                "SAMSUNG"
        );
        company2 = new Company(
                2L,
                "SONY"
        );
    }

    @Test
    @DisplayName("Add company")
    void addCompany_returnTrue() {
        //given
        when(companyRepository.insert(any(Company.class))).thenReturn(true);

        //when
        boolean result = companyService.add(company1);

        //then
        assertTrue(result);
        verify(companyRepository).insert(company1);
    }

    @Test
    @DisplayName("Update company name when given ID")
    void shouldUpdateCompanyNameOfGivenId() {
        //given
        String newName = "ROLEX";
        when(companyRepository.update(company1.getCompanyId(), newName)).thenReturn(true);
        company1.setName(newName);

        //when
        boolean result = companyService.update(company1.getCompanyId(), newName);

        //then
        assertTrue(result);
        assertEquals(newName, company1.getName());
    }

    @Test
    @DisplayName("Find by id")
    void shouldReturnCompanyById() {
        //given
        long id = 1;
        when(companyRepository.findById(id)).thenReturn(company1);

        //when
        Company result1 = companyService.findById(id);

        //then
        assertEquals(id, result1.getCompanyId());
        verify(companyRepository).findById(any());
    }

    @Test
    @DisplayName("Find by name")
    void shouldFindByName() {
        //given
        String name = "SAMSUNG";
        List<Company> companies = Collections.singletonList(company1);

        when(companyRepository.findByName(name)).thenReturn(companies);

        //when
        List<Company> resultCompanies = companyService.findByName(name);

        //then
        assertEquals(companies, resultCompanies);
        verify(companyRepository).findByName(name);
    }

    @Test
    @DisplayName("Find all")
    void shouldFindAll() {
        //given
        List<Company> companies = Arrays.asList(company1, company2);

        when(companyRepository.findAll()).thenReturn(companies);

        //when
        List<Company> resultAddresses = companyService.findAll();

        //then
        assertEquals(companies, resultAddresses);
        verify(companyRepository).findAll();
    }

    @Test
    @DisplayName("Remove by id")
    void shouldRemoveById() {
        //given
        Long id = 1L;

        when(companyRepository.removeById(id)).thenReturn(true);

        //when
        boolean result = companyService.removeById(id);

        //then
        assertTrue(result);
        verify(companyRepository).removeById(id);
    }
}