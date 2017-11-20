package br.edu.ifsp.ingresso.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Categoria;
import br.edu.ifsp.ingresso.models.Evento;

public class CategoriaDAOImpl implements CategoriaDAO {
	
	private EntityManager manager;
	
	public CategoriaDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Categoria entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		
	}

	@Override
	public void update(Categoria entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
		
	}

	@Override
	public Categoria findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Categoria u WHERE CAT_COD = :id").setParameter("id", id);
		try {
			Categoria categoria = (Categoria) query.getSingleResult();
			return categoria;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(Categoria entity) {
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findAll() {
		Query query = manager.createQuery("SELECT c FROM Categoria c");
		try {
			List<Categoria> categorias = query.getResultList();
			return categorias;
		} catch (Exception e) {
			return null;
		}
	}
	

}
