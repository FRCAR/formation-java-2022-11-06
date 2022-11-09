package com.bigcorp.companies.persistence.dao;

import java.time.LocalDate;
import java.util.List;

import com.bigcorp.companies.persistence.model.Child;
import com.bigcorp.companies.persistence.model.Company;
import com.bigcorp.companies.persistence.model.Employee;
import com.bigcorp.companies.persistence.model.Service;

public class DaoMain {

	public static void main(String[] args) {
		Company bigCorp = new Company();
		bigCorp.setName("bigCorp");
		CompanyDao companyDao = new CompanyDao();
		bigCorp = companyDao.merge(bigCorp);

		Service service1 = new Service();
		service1.setName("Service 1");
		service1.setCompany(bigCorp);
		ServiceDao serviceDao = new ServiceDao();
		service1 = serviceDao.merge(service1);

		Employee jean = new Employee();
		jean.setFirstName("Jean");
		jean.setLastName("Dupont");
		jean.setServiceAsEmployee(service1);
		EmployeeDao employeeDao = new EmployeeDao();
		jean = employeeDao.merge(jean);

		Employee roger = new Employee();
		roger.setFirstName("Roger");
		roger.setLastName("PtitChef");
		roger.setServiceAsEmployee(service1);
		roger.setServiceAsManager(service1);
		roger = employeeDao.merge(roger);

		serviceDao.merge(service1);

		Child paulo = new Child();
		paulo.setBirthDate(LocalDate.of(2012, 12, 31));
		paulo.setFirstName("Paulo");
		paulo.setLastName("Dupont");
		paulo.setParent(jean);
		ChildDao childDao = new ChildDao();
		paulo = childDao.merge(paulo);

		Child inès = new Child();
		inès.setBirthDate(LocalDate.of(2012, 12, 31));
		inès.setFirstName("Inès");
		inès.setLastName("Dupont");
		inès.setParent(jean);
		inès = childDao.merge(inès);

		List<Child> bigCorpEmployeesChildren = childDao.findFromCompanyName("bigCorp");
		for (Child child : bigCorpEmployeesChildren) {
			System.out.println("Le prénom de l'enfant rattaché à BigCorp est : " + child.getFirstName());
		}

		List<Employee> employesDePtitChef = employeeDao.findFromManagerLastName("PtitChef");
		for (Employee employee : employesDePtitChef) {
			System.out.println("Le prénom de l'employé rattaché à Roger est : " + employee.getFirstName());
		}

	}

}
