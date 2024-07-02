package com.elysiaptr.cemenghuiweb.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Department;
import com.elysiaptr.cemenghuiweb.web.repo.DepartmentRepository;
import com.elysiaptr.cemenghuiweb.web.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void testDeleteDepartmentSuccess() {
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        departmentServiceImpl.deleteDepartment(departmentId);

        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(1)).delete(department);
    }

    @Test
    void testDeleteDepartmentNotFound() {
        Long departmentId = 4L;

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            departmentServiceImpl.deleteDepartment(departmentId);
        });

        String expectedMessage = "Department not found for this id :: " + departmentId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(0)).delete(any(Department.class));
    }
}

