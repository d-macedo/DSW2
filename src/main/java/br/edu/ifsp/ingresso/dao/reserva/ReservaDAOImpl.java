package br.edu.ifsp.ingresso.dao.reserva;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.dao.pagamento.PagamentoDAOImpl;
import br.edu.ifsp.ingresso.models.Evento;
import br.edu.ifsp.ingresso.models.Pagamento;
import br.edu.ifsp.ingresso.models.PagamentoStatus;
import br.edu.ifsp.ingresso.models.Reserva;
import br.edu.ifsp.ingresso.models.ReservaStatus;

public class ReservaDAOImpl implements ReservaDAO{
	
	private EntityManager manager;
	
	private PagamentoDAOImpl daoPagamento;

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
	
	public BigDecimal getReservasInteirasByEvento(Evento evento) {
		Query query = manager.createNativeQuery("SELECT coalesce(SUM(RES_QTD_INTEIRA), 0) AS INTEIRAS_RESERVADAS FROM RESERVA WHERE RES_STATUS IN(1,2) AND RES_EVENTO = :evento_id").setParameter("evento_id", evento.getEve_cod());
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		return resultado;
	}
	
	public BigDecimal getReservasMeiasByEvento(Evento evento) {
		Query query = manager.createNativeQuery("SELECT coalesce(SUM(RES_QTD_MEIA), 0) AS MEIAS_RESERVADAS FROM RESERVA WHERE RES_STATUS IN(1,2) AND RES_EVENTO = :evento_id").setParameter("evento_id", evento.getEve_cod());
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		return resultado;
	}
	
	public void  efeutarReserva(Reserva reserva) {
		persist(reserva);
		pagamentoEfetuado(reserva);
	}
	
	public void pagamentoEfetuado(Reserva reserva) {
		
		Query query = manager.createQuery("SELECT r FROM ReservaStatus r WHERE RST_COD = 2");
		ReservaStatus status = (ReservaStatus) query.getSingleResult();
		reserva.setRes_status(status);
		
		//Gerar pagamento ------------------------------------------------------------------------------
		daoPagamento = new PagamentoDAOImpl();
		
		Query query2 = manager.createQuery("SELECT p FROM PagamentoStatus p WHERE PST_COD = 3");
		PagamentoStatus status2 = (PagamentoStatus) query2.getSingleResult();
		
		Pagamento pagamento = new Pagamento();
		
		Date date = new Date();
		
		pagamento.setPag_reserva(reserva);
		pagamento.setPag_status(status2);
		pagamento.setPag_data_ics(date);
		pagamento.setPag_data_status(date);
		pagamento.setPag_metodo("PROJETO DO AUGUSTO");
		
		daoPagamento.persist(pagamento);
		//----------------------------------------------------------------------------------------------
		
		update(reserva);
	}
	
	public void expirarReserva(Reserva reserva) {
		Query query = manager.createQuery("SELECT r FROM ReservaStatus r WHERE RST_COD = 3");
		ReservaStatus status = (ReservaStatus) query.getSingleResult();
		reserva.setRes_status(status);
		update(reserva);
	}
	
	public void cancelarReserva(Reserva reserva) {
		Query query = manager.createQuery("SELECT r FROM ReservaStatus r WHERE RST_COD = 4");
		ReservaStatus status = (ReservaStatus) query.getSingleResult();
		reserva.setRes_status(status);
		update(reserva);
	}
	
	public ReservaStatus getStatus(String status_id) {
		Query query = manager.createQuery("SELECT r FROM ReservaStatus r WHERE RST_COD = :status_id").setParameter("status_id", status_id);
		ReservaStatus status = (ReservaStatus) query.getSingleResult();
		return status;
	}
	
}
