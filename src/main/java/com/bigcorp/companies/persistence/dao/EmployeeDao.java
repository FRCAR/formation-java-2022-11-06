package com.bigcorp.companies.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bigcorp.companies.persistence.PersistenceFactory;
import com.bigcorp.companies.persistence.model.Company;
import com.bigcorp.companies.persistence.model.Employee;
import com.bigcorp.companies.persistence.model.Employee.Contract;
import com.bigcorp.companies.service.CompanyService;

public class EmployeeDao {

	public Employee find(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Employee savedEmployee = em.find(Employee.class, id);
		em.close();
		return savedEmployee;
	}
	

	public Employee findWithLazy(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Employee savedEmployee = em.find(Employee.class, id);
		em.close();
		return savedEmployee;
	}
	


	public List<Employee> findFromManagerLastName(String lastName) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Employee> employees = em.createQuery(
				"select distinct employee from Employee employee " 
						+ " left outer join fetch employee.serviceAsEmployee s "
						+ " left outer join fetch s.managers managers " 
						+ " where managers.lastName = :lastName",
				Employee.class).setParameter("lastName", lastName).getResultList();
		em.close();
		return employees;
		
	}

	public Employee findWithCompany(Long id) {
		if(id == null) {
			return null;
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Employee> employees = em.createQuery(
				"select distinct e from Employee e "
				+ " left outer join fetch e.company "
				+ " where e.id = :id ", Employee.class)
				.setParameter("id", id)
				.getResultList();
		em.close();
		if(employees.isEmpty()) {
			return null;
		}
		return employees.get(0);
	}

	public Employee merge(Employee employee) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee savedEmployee = em.merge(employee);
		tx.commit();
		em.close();
		return savedEmployee;
	}
	


	/**
	 * Supprime la {@link Company} company
	 * 
	 * @param company
	 */
	public void remove(Employee employee) {
		if (employee == null) {
			return;
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee employeeToRemove = em.find(Employee.class, employee.getId());
		if (employeeToRemove != null) {
			em.remove(employee);
		}
		tx.commit();
		em.close();
	}

	/**
	 * Supprime l'{@link Employee} ayant l'id id.
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		if (id == null) {
			return;
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee employeeToRemove = em.find(Employee.class, id);
		if (employeeToRemove != null) {
			em.remove(employeeToRemove);
		}
		tx.commit();
		em.close();
	}


	
	/**
	 * Renvoie tous les {@link Employee} dont le nom contient
	 * name, indépendamment de la casse.
	 * @param name
	 * @return
	 */
	public List<Employee> findByName(String name) {
		if(name == null) {
			name = "";
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Employee> employees = em.createQuery(
				"select distinct c from Company c where upper(c.name) "
				+ " like :queryName order by c.name ", Employee.class)
				.setParameter("queryName", '%' + name.toUpperCase() + '%')
				.getResultList();
		em.close();
		return employees;
	}

	public static void main(String[] args) {

//		Company company = new Company();
//		company.setName("Legrand");
//		CompanyDao companyDao = new CompanyDao();
//		company = companyDao.merge(company);
//		
//		Employee employee = new Employee();
//		employee.setFirstName("Jean");
//		employee.setLastName("Dupont");
//		employee.setActive(Boolean.FALSE);
//		employee.setArrivalDate(LocalDateTime.now().minusDays(1));
//		employee.setContract(Contract.CDI);
//		employee.setPhone("0836656565");
//		employee.setCompany(company);
//		
//		EmployeeDao employeeDao = new EmployeeDao();
//		Employee savedEmployee = employeeDao.merge(employee);
//		System.out.println(savedEmployee);
//		
////		Employee employeeFromDataBase = employeeDao.findWithCompany(savedEmployee.getId());
////		System.out.println("Le nom de l'entreprise de l'employé est : " + employeeFromDataBase.getCompany().getName());
//		
//		employeeDao.findWithLazy(savedEmployee.getId());
		
		
	}

}
