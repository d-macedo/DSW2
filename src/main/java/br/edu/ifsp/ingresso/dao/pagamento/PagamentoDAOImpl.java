package br.edu.ifsp.ingresso.dao.pagamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.ingresso.component.TurtleMail;
import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.dao.reserva.ReservaDAOImpl;
import br.edu.ifsp.ingresso.models.Pagamento;
import br.edu.ifsp.ingresso.models.PagamentoStatus;

public class PagamentoDAOImpl implements PagamentoDAO{
	
	private EntityManager manager;
	
	private ReservaDAOImpl daoReserva;
	
	public PagamentoDAOImpl() {
		super();
		this.manager = FactoryEntityManager.getEntityManager();
		daoReserva = new ReservaDAOImpl();
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
	
	public void mudarPagamentoParaReembolsado(Pagamento pagamento) {
		Query query = manager.createQuery("SELECT p FROM PagamentoStatus p WHERE PST_COD = 5");
		PagamentoStatus status = (PagamentoStatus) query.getSingleResult();
		pagamento.setPag_status(status);
		update(pagamento);
	}
	
	public void mudarPagamentoParaCancelado(Pagamento pagamento) {
		Query query = manager.createQuery("SELECT p FROM PagamentoStatus p WHERE PST_COD = 4");
		PagamentoStatus status = (PagamentoStatus) query.getSingleResult();
		pagamento.setPag_status(status);
		update(pagamento);
	}
	
	@SuppressWarnings("unchecked")
	public void reembolsarPagamentos(long id) {
		Query query = manager.createQuery
				("SELECT p FROM Pagamento p , "
						+ "Reserva r WHERE p.pag_reserva = RES_COD and RES_EVENTO = :id"
						+ " AND p.pag_status in (1,2,3)").setParameter("id", id);
		
		List<Pagamento> pagamentos = query.getResultList();
		
		for (Pagamento pagamento : pagamentos) {
			if (pagamento.getPag_status().getPst_cod() == 1) {
				System.out.println("AGUARDANDO APROVACAO");
				daoReserva.cancelarReserva(pagamento.getPag_reserva());
				mudarPagamentoParaCancelado(pagamento);
				
				TurtleMail mail = new TurtleMail(pagamento.getPag_reserva().getRes_comprador().getUsu_email());
				mail.enviaEmail("EVENTOS AGUARDANDO APROVACAO", "Ola filha da puta, \n acessa essa merda \n www.xvideos.com.br");
				
			}
			else if(pagamento.getPag_status().getPst_cod() == 2) {
				System.out.println("NAO APROVADO");
				daoReserva.cancelarReserva(pagamento.getPag_reserva());
			}
			else if(pagamento.getPag_status().getPst_cod() == 3) {
				System.out.println("REALIZADO");
				TurtleMail mail = new TurtleMail("user 3");
				
				TurtleMail mail1 = new TurtleMail(pagamento.getPag_reserva().getRes_comprador().getUsu_email());
				mail1.enviaEmail("EVENTOS REALIZADO", "Ola filha da puta, \n acessa essa merda \n www.pudim.com.br");
				
			}
		}
		
		
	}
}
