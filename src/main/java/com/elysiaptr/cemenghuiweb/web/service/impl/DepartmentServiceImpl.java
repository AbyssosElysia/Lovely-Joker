package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.DepartmentDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.Department;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.DepartmentRepository;
import com.elysiaptr.cemenghuiweb.web.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    @Override
    public Department updateDepartment(Long id, DepartmentDto departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
        if (departmentDetails.getName() != null) {
            department.setName(departmentDetails.getName());
        }
        if (departmentDetails.getLeader()!=null){
            department.setManager(departmentDetails.getLeader());
        }
       if (departmentDetails.getStatus()!=null){
           department.setStatus(departmentDetails.getStatus());
       }
        if (departmentDetails.getFatherDeptId() != null && departmentRepository.existsById(departmentDetails.getFatherDeptId())) {
            departmentRepository.findById(departmentDetails.getFatherDeptId())
                    .ifPresent(fatherDept -> department.setFatherDept(fatherDept));
        }
       if (departmentDetails.getEmail()!=null){
           department.setEmail(departmentDetails.getEmail());
       }
       if (departmentDetails.getMobile()!=null){
           department.setMobile(departmentDetails.getMobile());
       }
       if (departmentDetails.getId()!=null){
           department.setId(departmentDetails.getId());
       }

        return departmentRepository.save(department);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
        departmentRepository.delete(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Page<Department> getDepartmentsByPage(int page, int size) {
        return departmentRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public List<Department> searchDepartmentByStatus(List<Department> departmentList, Byte status) {
        return departmentList.stream()
                .filter(department -> Objects.equals(department.getStatus(), status))
                .collect(Collectors.toList());
    }
    @Override
    public List<Department> searchDepartmentByName(List<Department> departmentList, String name) {
        return departmentList.stream()
                .filter(department -> department.getName().contains(name))
                .collect(Collectors.toList());
    }
/*
    @Override
    public List<DepartmentDto> findByFatherDeptId(Long id){
        List<DepartmentDto> departmentList =null;
        return departmentList.stream()
                .filter(department -> department.getParentDepartment().getId().equals(id))
                .collect(Collectors.toList());
    };
*/

    @Override
    public Map<String, Object> DFS(Department department, Stack<DepartmentDto> inputDepartmentDtoStack){
        Map<String, Object> result = new HashMap<>();
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setTime(department.getTime());
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        dto.setDepartments(departmentDtoList);
        inputDepartmentDtoStack.push(dto);
        if (!department.getSonDepartments().isEmpty()){
            for(Department dept: department.getSonDepartments()){
                Map<String, Object> recentResult = DFS(dept, inputDepartmentDtoStack);
                DepartmentDto recentDto = (DepartmentDto) recentResult.get("department");
                Stack<DepartmentDto> recentDtoStack = (Stack<DepartmentDto>) recentResult.get("stack");
                DepartmentDto nowDto = recentDtoStack.pop();
                nowDto.getDepartments().add(recentDto);
                recentDtoStack.push(nowDto);
            }
        }
        inputDepartmentDtoStack.pop();
        result.put("department", dto);
        result.put("stack", inputDepartmentDtoStack);
        return result;
    }
    @Override
    public List<Department> searchDepartmentByCompany(Company company){
        List<Department> departmentList = company.getDepartments();
        System.out.println(company.getId());
        //System.out.println();
        return departmentList.stream()
                .filter(department -> department.getCompany().getId().equals(company.getId()))
                .collect(Collectors.toList());

    }
}