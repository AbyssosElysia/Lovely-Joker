package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.SuperAdmin;
import com.elysiaptr.cemenghuiweb.repo.SuperAdminRepository;
import com.elysiaptr.cemenghuiweb.service.SuperAdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Transactional
    @Override
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    @Transactional
    @Override
    public SuperAdmin updateSuperAdmin(Long id, SuperAdmin superAdminDetails) {
        SuperAdmin superAdmin = superAdminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin not found for this id :: " + id));

        superAdmin.setUsername(superAdminDetails.getUsername());
        superAdmin.setPassword(superAdminDetails.getPassword());

        return superAdminRepository.save(superAdmin);
    }

    @Transactional
    @Override
    public void deleteSuperAdmin(Long id) {
        SuperAdmin superAdmin = superAdminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin not found for this id :: " + id));
        superAdminRepository.delete(superAdmin);
    }

    @Override
    public SuperAdmin getSuperAdminById(Long id) {
        return superAdminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin not found for this id :: " + id));
    }

    @Override
    public List<SuperAdmin> getAllSuperAdmins() {
        return superAdminRepository.findAll();
    }

    @Override
    public Page<SuperAdmin> getSuperAdminsByPage(int page, int size) {
        return superAdminRepository.findAll(PageRequest.of(page, size));
    }
}