package br.edu.ifsp.ingresso.dao.evento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Evento;


public class EventoDAOImpl implements EventoDAO {
	
	private EntityManager manager; 
	
	public EventoDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Evento user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public void update(Evento user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public void delete (Evento user) {
		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public Evento findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Evento u WHERE EVE_COD = :id").setParameter("id", id);
		try {
			Evento user = (Evento) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> findAll(){
		Query query = manager.createQuery("SELECT * FROM Evento");
		try {
			List<Evento> users = query.getResultList();
			return users;
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
