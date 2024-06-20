package com.elysiaptr.cemenghuiweb.service;

import com.elysiaptr.cemenghuiweb.po.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    Company updateCompany(Long id, Company companyDetails);

    void deleteCompany(Long id);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Page<Company> getCompaniesByPage(int page, int size);
}