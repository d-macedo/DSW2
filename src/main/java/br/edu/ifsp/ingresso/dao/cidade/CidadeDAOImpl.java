package br.edu.ifsp.ingresso.dao.cidade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Cidade;
import br.edu.ifsp.ingresso.models.Local;

public class CidadeDAOImpl implements CidadeDAO {
	
	private EntityManager manager;
		
	public CidadeDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Cidade entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();	
	}

	@Override
	public void update(Cidade entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();	
		
	}
	
	@Override
	public void delete(Cidade entity) {
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();		
	}

	@Override
	public Cidade findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Cidade u WHERE CID_COD = :id").setParameter("id", id);
		try {
			Cidade cidade = (Cidade) query.getSingleResult();
			return cidade;
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> findAll() {
		Query query = manager.createQuery("SELECT u FROM Cidade u");
		try {
			List<Cidade> cidades = query.getResultList();
			return cidades;
		} catch (Exception e) {
			return null;
		}
	}
}
