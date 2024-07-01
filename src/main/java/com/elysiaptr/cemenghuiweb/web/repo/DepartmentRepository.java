package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
