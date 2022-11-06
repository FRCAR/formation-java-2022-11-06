package com.bigcorp.companies.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bigcorp.companies.persistence.PersistenceFactory;
import com.bigcorp.companies.persistence.model.Company;

public class CompanyDao {

	public Company find(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Company savedCompany = em.find(Company.class, id);
		em.close();
		return savedCompany;
	}

	public List<Company> findByName(String name) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Company> companies = em.createQuery(
				"select distinct c from Company c where upper(c.name) like upper(:queryName)",
				Company.class)
				.setParameter("queryName", "%" + name + "%").getResultList();
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

	public static void main(String[] args) {
		Company company = new Company();
		company.setName("bigCorp");
		CompanyDao dao = new CompanyDao();
		Company savedCompany = dao.merge(company);
		System.out.println(savedCompany.getId());
		savedCompany = dao.findByName("igCO").get(0);
		System.out.println(savedCompany.getName());

	}

}
