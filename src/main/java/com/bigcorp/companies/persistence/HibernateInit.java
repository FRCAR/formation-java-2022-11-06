package com.bigcorp.companies.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bigcorp.companies.persistence.model.Company;

public class HibernateInit {

	public static void main(String[] args) {
		HibernateInit init = new HibernateInit();
		init.saveAndLoad();
		init.load();
	}

	private void load() {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Company savedCompany = em.find(Company.class, 1l);
		em.close();
	}

	private void saveAndLoad() {
		Company company = new Company();
		company.setName("Big Corp");
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(company);
		Company savedCompany = em.find(Company.class, company.getId());
		System.out.println(savedCompany.getId());
		System.out.println(savedCompany.getName());
		tx.commit();
		em.close();

		EntityManager em2 = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();
		savedCompany.setName("New Big Corp");
		em2.merge(savedCompany);
		tx2.commit();
		em2.close();
	}

}
