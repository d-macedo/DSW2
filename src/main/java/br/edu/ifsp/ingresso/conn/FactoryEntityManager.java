package br.edu.ifsp.ingresso.conn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryEntityManager {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ingresso");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
