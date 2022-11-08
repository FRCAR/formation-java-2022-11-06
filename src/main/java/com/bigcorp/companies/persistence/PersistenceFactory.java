package com.bigcorp.companies.persistence;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceFactory {

	private static final String PERSISTENCE_UNIT_NAME = "PersisterPU";
	public static PersistenceFactory INSTANCE = new PersistenceFactory();
	private AtomicBoolean init = new AtomicBoolean();
	private EntityManagerFactory emf;

	private PersistenceFactory() {
	}

	public EntityManager getEntityManager() {
		if (this.init.compareAndSet(false, true)) {
			this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return this.emf.createEntityManager();
	}

}