package com.bigcorp.companies.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;

import com.bigcorp.companies.persistence.PersistenceFactory;
import com.bigcorp.companies.persistence.model.Child;

@Component
public class ChildDao {

	public Child find(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Child savedChild = em.find(Child.class, id);
		em.close();
		return savedChild;
	}

	public List<Child> findFromCompanyName(String companyName) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Child> childs = em.createQuery(
				"select distinct child from Child child "
						+ " left outer join fetch child.parent p"
						+ " left outer join fetch p.serviceAsEmployee s "
						+ " left outer join fetch s.company company "
						+ " where company.name = :companyName",
				Child.class)
				.setParameter("companyName", companyName).getResultList();
		em.close();
		return childs;
	}

	public Child merge(Child child) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Child savedChild = em.merge(child);
		tx.commit();
		em.close();
		return savedChild;
	}

	public boolean delete(Long id) {
		boolean deleted = false;
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Child savedChild = em.find(Child.class, id);
		if (savedChild != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(savedChild);
			deleted = true;
			tx.commit();
		}
		em.close();
		return deleted;
	}

	public List<Child> findAll() {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Child> childs = em.createQuery(
				"select distinct child from Child child ",
				Child.class).getResultList();
		em.close();
		return childs;
	}

}
