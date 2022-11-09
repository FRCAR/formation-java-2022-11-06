package com.bigcorp.companies.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bigcorp.companies.persistence.PersistenceFactory;
import com.bigcorp.companies.persistence.model.Company;
import com.bigcorp.companies.persistence.model.Employee;
import com.bigcorp.companies.persistence.model.Employee.Contract;

public class CompanyDao {

	/**
	 * Récupère la Company ayant l'id : id . Peut renvoyer null...
	 * 
	 * @param id
	 * @return la company ayant l'id, ou null si aucune n'est trouvée.
	 */
	public Company find(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Company savedCompany = em.find(Company.class, id);
		em.close();
		return savedCompany;
	}

	public List<Company> findByName(String name) {
		if (name == null) {
			name = "";
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Company> companies = em
				.createQuery("select distinct c from Company c where upper(c.name) like :queryName order by c.name ",
						Company.class)
				.setParameter("queryName", '%' + name.toUpperCase() + '%').getResultList();
		em.close();
		return companies;
	}

	public List<Company> findWithEmployees(String name) {
		if (name == null) {
			name = "";
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Company> companies = em
				.createQuery("select distinct c " + " from Company c " 
						+ " left outer join fetch c.employees "
						+ " where upper(c.name) like :queryName ", Company.class)
				.setParameter("queryName", '%' + name.toUpperCase() + '%').getResultList();
		em.close();
		return companies;
	}

	public Company merge(Company company) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Company savedCompany = em.merge(company);
		tx.commit();
		em.close();
		return savedCompany;
	}

	/**
	 * Supprime la {@link Company} company
	 * 
	 * @param company
	 */
	public void remove(Company company) {
		if (company == null) {
			return;
		}
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Company companyToRemove = em.find(company.getClass(), company.getId());
		if (companyToRemove != null) {
			em.remove(companyToRemove);
		}
		tx.commit();
		em.close();
	}

	/**
	 * Supprime la {@link Company} ayant l'id id.
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
		Company companyToRemove = em.find(Company.class, id);
		if (companyToRemove != null) {
			em.remove(companyToRemove);
		}
		tx.commit();
		em.close();
	}

	public static void main(String[] args) {
		Company company = new Company();
		company.setName("bigCorp2");
		CompanyDao dao = new CompanyDao();
		Company savedCompany = dao.merge(company);

		Company company2 = new Company();
		company2.setName("bigCorp4");
		dao.merge(company2);

		System.out.println(savedCompany.getId());
		System.out.println(savedCompany.getName());
		System.out.println("id vaut : " + savedCompany.getId());

		Company companyId45 = dao.find(45l);
		if (companyId45 == null) {
			System.out.println("La company est null");
		}

//		dao.remove(savedCompany);
//		savedCompany = dao.merge(savedCompany);
//		System.out.println(savedCompany);

//		List<Company> results = dao.findByName("iGc");
//		for (Company result : results) {
//			System.out.println("L'entreprise bigCorp est : " + result);
//		}
//
//		Employee employee1 = new Employee();
//		employee1.setFirstName("Jean");
//		employee1.setLastName("Dupont");
//		employee1.setActive(Boolean.FALSE);
//		employee1.setArrivalDate(LocalDateTime.now().minusDays(1));
//		employee1.setContract(Contract.CDI);
//		employee1.setPhone("0836656565");
//		employee1.setCompany(savedCompany);
//		savedCompany.getEmployees().add(employee1);
//
//		EmployeeDao employeeDao = new EmployeeDao();
//		employeeDao.merge(employee1);
//
//		Employee employee2 = new Employee();
//		employee2.setFirstName("Jacques");
//		employee2.setLastName("Durant");
//		employee2.setActive(Boolean.FALSE);
//		employee2.setArrivalDate(LocalDateTime.now().minusDays(1));
//		employee2.setContract(Contract.CDI);
//		employee2.setPhone("0836656565");
//		employee2.setCompany(savedCompany);
//		savedCompany.getEmployees().add(employee2);
//		employeeDao.merge(employee2);
//
//		List<Company> companies = dao.findWithEmployees("bigCorp2");
//		for (Company companyFromDataBase : companies) {
//			for (Employee employeeFromDataBase : companyFromDataBase.getEmployees()) {
//				System.out.println(
//						employeeFromDataBase.getFirstName() + " travaille chez " + companyFromDataBase.getName());
//			}
//		}

	}

}
