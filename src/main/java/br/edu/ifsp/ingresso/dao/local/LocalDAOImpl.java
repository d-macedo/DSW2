package br.edu.ifsp.ingresso.dao.local;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Local;

public class LocalDAOImpl implements LocalDAO{
	
	private EntityManager manager;
	
	public LocalDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Local entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();		
	}

	@Override
	public void update(Local entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();		
	}

	@Override
	public Local findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Local u WHERE LOC_COD = :id").setParameter("id", id);
		try {
			Local local = (Local) query.getSingleResult();
			return local;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(Local entity) {
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Local> findAll() {
		Query query = manager.createQuery("SELECT c FROM Local c");
		try {
			List<Local> locais = query.getResultList();
			return locais;
		} catch (Exception e) {
			return null;
		}
	}

}
