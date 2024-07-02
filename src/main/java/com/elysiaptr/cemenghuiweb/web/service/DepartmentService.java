package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.DepartmentDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department updateDepartment(Long id, DepartmentDto departmentDetails);

    void deleteDepartment(Long id);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartments();

    Page<Department> getDepartmentsByPage(int page, int size);

    List<Department> searchDepartmentByStatus(byte status);

    List<Department> searchDepartmentByName(String name);
    List<Department> searchDepartmentByCompany(Company company);

 //  Page<Department> getDepartmentsByCompany(Company company, Pageable pageable);

   // List<DepartmentDto> findByFatherDeptId(Long id);
    DepartmentDto DFS(Department department);
}