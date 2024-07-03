package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.CompanyDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    Company updateCompany(Long id, CompanyDto companyDetails);

    void deleteCompany(Long id);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Page<Company> getCompaniesByPage(int page, int size);

    List<Company> searchByCompanyId(Long id,List<Company> companyList);
    List<Company> searchByCompanyName(String name,List<Company> companyList);
    List<Company> searchByCompanyContact(String contact,List<Company> companyList);
    List<Company> searchByCompanyMobile(Long mobile,List<Company> companyList);

    List<Company> getCompaniesByIds(List<Long> companyIds);
}