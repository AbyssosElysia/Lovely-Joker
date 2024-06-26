package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.repo.CompanyRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    @Override
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
    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        companyRepository.delete(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Page<Company> getCompaniesByPage(int page, int size) {
        return companyRepository.findAll(PageRequest.of(page, size));
    }
}