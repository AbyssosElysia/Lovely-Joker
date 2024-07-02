package com.elysiaptr.cemenghuiweb.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.repo.CompanyRepository;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyServiceImpl;

    @Test
    void testDeleteCompanySuccess() {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        companyServiceImpl.deleteCompany(companyId);

        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, times(1)).delete(company);
    }

    @Test
    void testDeleteCompanyNotFound() {
        Long companyId = 2L;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            companyServiceImpl.deleteCompany(companyId);
        });

        String expectedMessage = "Company not found for this id :: " + companyId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, times(0)).delete(any(Company.class));
    }
}
