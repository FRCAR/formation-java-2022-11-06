package com.bigcorp.companies.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bigcorp.companies.rest.bean.CompanyRestBean;
import com.bigcorp.companies.service.CompanyService;

@RestController
public class CompanyRestService {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/companies/{companyId}")
	public CompanyRestBean getByCompanyId(@PathVariable("companyId") Long companyId) {
		CompanyRestBean bean = this.companyService.findById(companyId);
		if (bean == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No company found");
		}
		return bean;
	}

	@GetMapping("/companies")
	public Collection<CompanyRestBean> getAll2() {
		return this.companyService.findAll();
	}

	@DeleteMapping("/companies/{companyId}")
	public void deleteByCompanyUserId(@PathVariable("companyId") Long id) {
		this.companyService.removeById(id);
	}

	@PostMapping("/companies")
	public CompanyRestBean postCompany(@RequestBody CompanyRestBean companyRestBean) {
		return this.companyService.save(companyRestBean);
	}
	

}