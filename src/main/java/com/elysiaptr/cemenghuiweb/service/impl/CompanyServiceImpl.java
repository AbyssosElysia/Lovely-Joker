package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.Company;
import com.elysiaptr.cemenghuiweb.repo.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl {
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));

        company.setContact(companyDetails.getContact());
        company.setLogo(companyDetails.getLogo());
        company.setName(companyDetails.getName());
        company.setMobile(companyDetails.getMobile());
        company.setTime(companyDetails.getTime());
        company.setRemark(companyDetails.getRemark());
        company.setClassCS(companyDetails.getClassCS());
        company.setNews(companyDetails.getNews());
        company.setUsers(companyDetails.getUsers());
        company.setDepartments(companyDetails.getDepartments());

        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        companyRepository.delete(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Page<Company> getCompaniesByPage(int page, int size) {
        return companyRepository.findAll(PageRequest.of(page, size));
    }
}