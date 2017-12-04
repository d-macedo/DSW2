package br.edu.ifsp.ingresso.dao.evento;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
		Query query = manager.createQuery("SELECT e FROM Evento e");
		try {
			List<Evento> eventos = query.getResultList();
			return eventos;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ArrayList<Evento> findPendentes() {
		Query query = manager.createQuery("SELECT u FROM Evento u WHERE EVE_STATUS = 1");
		try {
			ArrayList<Evento> eventos = (ArrayList<Evento>) query.getResultList();
			return eventos;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ArrayList<Evento> findByExecutor(long id) {
		Query query = manager.createQuery("SELECT u FROM Evento u WHERE EVE_EXECUTOR = :id").setParameter("id", id);
		try {
			ArrayList<Evento> eventos = (ArrayList<Evento>) query.getResultList();
			return eventos;
		} catch (Exception e) {
			return null;
		}
	}
	
	public EventoStatus statusById(int id) {
		Query query = manager.createQuery("SELECT e FROM EventoStatus e WHERE EST_COD = "+id);
		EventoStatus status = (EventoStatus) query.getSingleResult();
		return status;
	}
	
	public void cancelarEvento(Evento evento) {
		Query query = manager.createQuery("SELECT e FROM EventoStatus e WHERE EST_COD = 4");
		EventoStatus status = (EventoStatus) query.getSingleResult();
		evento.setEve_status(status);
		update(evento);
	}
	
	public void aprovarEvento(Evento evento) {
		Query query = manager.createQuery("SELECT e FROM EventoStatus e WHERE EST_COD = 3");
		EventoStatus status = (EventoStatus) query.getSingleResult();
		evento.setEve_status(status);
		update(evento);
	}
	
	public void reprovarEvento(Evento evento) {
		Query query = manager.createQuery("SELECT e FROM EventoStatus e WHERE EST_COD = 2");
		EventoStatus status = (EventoStatus) query.getSingleResult();
		evento.setEve_status(status);
		update(evento);
	}
	
	public void finalizarEvento(Evento evento) {
		EventoStatus status = statusById(5);
		evento.setEve_status(status);
		update(evento);
	}
	
	public void reembolsarEvento(Evento entity) {
		daoPagamento.reembolsarPagamentos(entity.getEve_cod());
		cancelarEvento(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> pesquisarEventosIndex(String evento, Integer cidade, Date data) throws ParseException{
		
		String queryStatement = "SELECT u FROM Evento u, Local l WHERE u.eve_local = l.loc_cod AND u.eve_status = 3 ";
			
		if (evento != null) {
			queryStatement += "AND u.eve_titulo LIKE :evento ";
		}
		if (cidade != null && cidade != 0) {
			queryStatement += "AND l.loc_cidade.cid_cod = :cidade ";
		}
		if (data != null) {
			queryStatement += "AND date(eve_data) = str_to_date(:data, '%d/%m/%Y')";
		}
		
		Query query = manager.createQuery(queryStatement);
		
		if (evento != null) {
			query.setParameter("evento", "%"+evento+"%");
		}
		if (cidade != null && cidade != 0) {
			query.setParameter("cidade", cidade);
		}
		if (data != null) {
			br.edu.ifsp.ingresso.Util.LocalDateConverter converter = new br.edu.ifsp.ingresso.Util.LocalDateConverter();
			String dataPesquisa = converter.convertToString(data);
			query.setParameter("data", dataPesquisa);
			
		}
	
		List<Evento> eventos = query.getResultList();
		
		return eventos;
	}
	
	@Override
	public ArrayList<Evento> balancoGeral(){
		Query query = manager.createNativeQuery("SELECT eve_cod, eve_titulo, eve_max_inteira, eve_max_meia, sum(res_qtd_inteira) as TOTAL_INTEIRAS, sum(res_qtd_meia) as TOTAL_MEIAS FROM Evento e INNER JOIN reserva r ON r.res_evento = e.eve_cod WHERE res_status = 2 GROUP BY eve_titulo, EVE_MAX_INTEIRA, eve_max_meia");
		
		try {
			ArrayList<Evento> eventos = new ArrayList<Evento>();
			
			ArrayList<Object> eventosSQL = (ArrayList<Object>) query.getResultList();
			for(Object eventoSQL: eventosSQL) {
				Object[] object = (Object[]) eventoSQL;

				Evento novoEvento = new Evento();
				novoEvento.setEve_cod(new Long((Integer) object[0]));
				novoEvento.setEve_titulo((String) object[1]);
				novoEvento.setEve_max_inteira((Integer) object[2]);
				novoEvento.setEve_max_meia((Integer) object[3]);
				novoEvento.setTotal_inteiras((BigDecimal) object[4]);
				novoEvento.setTotal_meia((BigDecimal) object[5]);
				
				eventos.add(novoEvento);
			}

			return eventos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
