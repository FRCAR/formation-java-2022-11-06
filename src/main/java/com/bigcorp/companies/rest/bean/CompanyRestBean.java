package com.bigcorp.companies.rest.bean;

import com.bigcorp.companies.persistence.model.Company;

public class CompanyRestBean {

	private Long id;

	private String name;

	public CompanyRestBean() {
		super();
	}

	public CompanyRestBean(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Company toCompany(CompanyRestBean companyRestBean) {
		if(companyRestBean == null) {
			return null;
		}
		Company company = new Company();
		company.setId(companyRestBean.getId());
		company.setName(companyRestBean.getName());
		return company;
	}
	
	public static CompanyRestBean toCompanyRestBean(Company company) {
		CompanyRestBean companyRestBean = new CompanyRestBean();
		companyRestBean.setId(company.getId());
		companyRestBean.setName(company.getName());
		return companyRestBean;
	}


}
