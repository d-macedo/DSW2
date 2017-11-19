package br.edu.ifsp.ingresso.dao.reserva;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Reserva;

public class ReservaDAOImpl implements ReservaDAO{
	
	private EntityManager manager;

	public ReservaDAOImpl() {
		super();
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Reserva entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		
	}

	@Override
	public void update(Reserva entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
	}
	
	@Override
	public void delete(Reserva entity) {
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();
	}

	@Override
	public Reserva findById(long id) {
		Query query = manager.createQuery("SELECT r FROM Reserva r WHERE RES_COD = :id")
				.setParameter("id", id);
		try {
			Reserva reserva = (Reserva) query.getSingleResult();
			return reserva;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> findAll() {
		Query query = manager.createQuery("SELECT * FROM Reserva");
		try {
			List<Reserva> reservas = query.getResultList();
			return reservas;
		} catch (Exception e) {
			return null;
		}
	}
	
}
