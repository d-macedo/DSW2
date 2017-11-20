package br.edu.ifsp.ingresso.dao.evento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.dao.pagamento.PagamentoDAOImpl;
import br.edu.ifsp.ingresso.models.Evento;
import br.edu.ifsp.ingresso.models.EventoStatus;


public class EventoDAOImpl implements EventoDAO {
	
	private EntityManager manager; 
	
	private PagamentoDAOImpl daoPagamento;
	
	public EventoDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
		daoPagamento = new PagamentoDAOImpl();
	}

	@Override
	public void persist(Evento evento) {
		manager.getTransaction().begin();
		manager.persist(evento);
		manager.getTransaction().commit();
	}
	
	@Override
	public void update(Evento evento) {
		manager.getTransaction().begin();
		manager.merge(evento);
		manager.getTransaction().commit();
	}
	
	@Override
	public void delete (Evento evento) {
		manager.getTransaction().begin();
		manager.remove(evento);
		manager.getTransaction().commit();
	}
	
	@Override
	public Evento findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Evento u WHERE EVE_COD = :id").setParameter("id", id);
		try {
			Evento evento = (Evento) query.getSingleResult();
			return evento;
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> findAll(){
		Query query = manager.createQuery("SELECT * FROM Evento");
		try {
			List<Evento> eventos = query.getResultList();
			return eventos;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void cancelarEvento(Evento evento) {
		Query query = manager.createQuery("SELECT e FROM EventoStatus e WHERE EST_COD = 4");
		EventoStatus status = (EventoStatus) query.getSingleResult();
		evento.setEve_status(status);
		update(evento);
	}
	
	public void reembolsarEvento(Evento entity) {
		daoPagamento.reembolsarPagamentos(entity.getEve_cod());
		cancelarEvento(entity);
	}
	
	

}
