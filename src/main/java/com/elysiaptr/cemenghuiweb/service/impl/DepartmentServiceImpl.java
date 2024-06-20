package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.Department;
import com.elysiaptr.cemenghuiweb.repo.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));

        department.setName(departmentDetails.getName());
        department.setMobile(departmentDetails.getMobile());
        department.setEmail(departmentDetails.getEmail());
        department.setStatus(departmentDetails.getStatus());
        department.setFatherDept(departmentDetails.getFatherDept());
        department.setTime(departmentDetails.getTime());
        department.setCompany(departmentDetails.getCompany());
        department.setUsers(departmentDetails.getUsers());
        department.setPosts(departmentDetails.getPosts());

        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
        departmentRepository.delete(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Page<Department> getDepartmentsByPage(int page, int size) {
        return departmentRepository.findAll(PageRequest.of(page, size));
    }
}