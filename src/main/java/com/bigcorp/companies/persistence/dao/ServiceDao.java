package com.bigcorp.companies.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bigcorp.companies.persistence.PersistenceFactory;
import com.bigcorp.companies.persistence.model.Service;

public class ServiceDao {

	public Service find(Long id) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		Service savedService = em.find(Service.class, id);
		em.close();
		return savedService;
	}

	public List<Service> findByName(String name) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		List<Service> services = em.createQuery(
				"select distinct c from Service s where upper(s.name) like upper(:queryName)",
				Service.class)
				.setParameter("queryName", "%" + name + "%").getResultList();
		em.close();
		return services;
	}

	public Service merge(Service service) {
		EntityManager em = PersistenceFactory.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Service savedService = em.merge(service);
		tx.commit();
		em.close();
		return savedService;
	}

}
