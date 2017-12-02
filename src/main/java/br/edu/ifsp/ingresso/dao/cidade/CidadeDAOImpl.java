package br.edu.ifsp.ingresso.dao.cidade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Cidade;

public class CidadeDAOImpl implements CidadeDAO {
	
	private EntityManager manager;
		
	public CidadeDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
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
