package com.bigcorp.companies.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bigcorp.companies.service.CompanyService;
import com.bigcorp.companies.service.EmployeeService;

public class SpringApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext appContext 
				= new AnnotationConfigApplicationContext()) {
			appContext.scan("com.bigcorp");
			appContext.refresh();

			CompanyService companyService = (CompanyService) appContext.getBean("companyService");
			System.out.println(companyService.findById(43l));
			
		}
	}

}
