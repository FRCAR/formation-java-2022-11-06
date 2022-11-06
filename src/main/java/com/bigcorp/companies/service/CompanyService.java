package com.bigcorp.companies.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bigcorp.companies.rest.bean.CompanyRestBean;

@Component
public class CompanyService {

	private Map<Long, CompanyRestBean> companies = new HashMap<>();

	public CompanyService() {
		this.companies.put(1l, new CompanyRestBean(1l, "VMWARE"));
		this.companies.put(2l, new CompanyRestBean(2l, "ORACLE"));
		this.companies.put(3l, new CompanyRestBean(3l, "MICROSOFT"));
	}

	public void removeById(Long id) {
		this.companies.remove(id);

	}

	public CompanyRestBean save(CompanyRestBean companyRestBean) {
		if (companyRestBean == null
				|| companyRestBean.getId() == null) {
			return null;
		}
		return this.companies.put(companyRestBean.getId(), companyRestBean);
	}

	public Collection<CompanyRestBean> findAll() {
		return this.companies.values();
	}

	public CompanyRestBean findById(Long companyId) {
		return this.companies.get(companyId);
	}

}
