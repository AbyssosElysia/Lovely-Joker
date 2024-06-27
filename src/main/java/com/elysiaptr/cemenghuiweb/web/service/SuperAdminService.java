package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.po.SuperAdmin;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SuperAdminService {

    SuperAdmin saveSuperAdmin(SuperAdmin superAdmin);

    SuperAdmin updateSuperAdmin(Long id, SuperAdmin superAdminDetails);

    void deleteSuperAdmin(Long id);

    SuperAdmin getSuperAdminById(Long id);

    List<SuperAdmin> getAllSuperAdmins();

    Page<SuperAdmin> getSuperAdminsByPage(int page, int size);
}