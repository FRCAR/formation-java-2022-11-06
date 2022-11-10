package com.bigcorp.companies.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bigcorp.companies.rest.bean.CompanyRestBean;
import com.bigcorp.companies.service.CompanyService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class CompanyServiceTest {
	
	@Autowired 
	private CompanyService companyService;
	
	@Test
	public void  testFindById() {
		CompanyRestBean companyRestBean = new CompanyRestBean();
		String name = "superCompany";
		companyRestBean.setName(name);
		CompanyRestBean companyFromDB = this.companyService.save(companyRestBean);
		Assertions.assertNotNull(companyFromDB.getId());
		Assertions.assertEquals(name, companyFromDB.getName());
	}
	
}
