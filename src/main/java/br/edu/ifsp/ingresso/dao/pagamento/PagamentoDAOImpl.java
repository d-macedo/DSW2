package br.edu.ifsp.ingresso.dao.pagamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Pagamento;

public class PagamentoDAOImpl implements PagamentoDAO{
	
	private EntityManager manager;
	
	public PagamentoDAOImpl() {
		super();
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Pagamento entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
	}

	@Override
	public void update(Pagamento entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
	}
	
	@Override
	public void delete(Pagamento entity) {
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();
	}

	@Override
	public Pagamento findById(long id) {
		Query query = manager.createQuery("SELECT p FROM Pagamento p WHERE PAG_COD = :id")
				.setParameter("id", id);;
		try {
			Pagamento pag = (Pagamento) query.getSingleResult();
			return pag;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagamento> findAll() {
		Query query = manager.createQuery("SELECT * FROM Pagamento");
		try {
			List<Pagamento> pags = query.getResultList();
			return pags;
		} catch (Exception e) {
			return null;
		}
	}
	
}
