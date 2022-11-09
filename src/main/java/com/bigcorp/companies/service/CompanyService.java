package com.bigcorp.companies.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigcorp.companies.persistence.dao.CompanyDao;
import com.bigcorp.companies.persistence.model.Company;
import com.bigcorp.companies.rest.bean.CompanyRestBean;

@Component
public class CompanyService {

	private Map<Long, CompanyRestBean> companies = new HashMap<>();

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyDao companyDao;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public Company getCompany(Long id) {
		return this.companyDao.find(id);
	}

	public CompanyService() {
		this.companies.put(1l, new CompanyRestBean(1l, "VMWARE"));
		this.companies.put(2l, new CompanyRestBean(2l, "ORACLE"));
		this.companies.put(3l, new CompanyRestBean(3l, "MICROSOFT"));
	}

	public void removeById(Long id) {
		this.companyDao.remove(id);
	}

	public CompanyRestBean save(CompanyRestBean companyRestBean) {
		Company company = CompanyRestBean.toCompany(companyRestBean);
		Company savedCompany = this.companyDao.merge(company);
		return CompanyRestBean.toCompanyRestBean(savedCompany);
	}

	public Collection<CompanyRestBean> findAll() {
		Collection<CompanyRestBean> restBeans = new ArrayList<>();
		for(Company company : this.companyDao.findAll()) {
			restBeans.add(CompanyRestBean.toCompanyRestBean(company));
		}
		return restBeans;
	}

	public CompanyRestBean findById(Long companyId) {
		Company company = this.companyDao.find(companyId);
		return CompanyRestBean.toCompanyRestBean(company);
	}

}
