package br.edu.ifsp.ingresso.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.categoria.CategoriaDAOImpl;
import br.edu.ifsp.ingresso.component.UsuarioSession;
import br.edu.ifsp.ingresso.dao.cidade.CidadeDAOImpl;
import br.edu.ifsp.ingresso.dao.evento.EventoDAOImpl;
import br.edu.ifsp.ingresso.dao.local.LocalDAOImpl;
import br.edu.ifsp.ingresso.dao.reserva.ReservaDAOImpl;
import br.edu.ifsp.ingresso.dao.usuario.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Categoria;
import br.edu.ifsp.ingresso.models.Cidade;
import br.edu.ifsp.ingresso.models.Evento;
import br.edu.ifsp.ingresso.models.EventoStatus;
import br.edu.ifsp.ingresso.models.Local;
import br.edu.ifsp.ingresso.models.Reserva;
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class EventoController {
	private EventoDAOImpl dao;

	@Inject
	private Validator validator;

	@Inject
	private Result result;
	
	@Inject
	private UsuarioSession usuarioSession;

	public EventoController() {
		this.dao = new EventoDAOImpl();
	}

	@Get
	@Path("/cadastrar/evento")
	public void cadastroEvento() {
		
		CategoriaDAOImpl catDAO = new CategoriaDAOImpl();
		List<Categoria> catLista = catDAO.findAll();
		result.include("categorias",catLista);
		
		CidadeDAOImpl cidDAO = new CidadeDAOImpl();
		List<Cidade> cidLista = cidDAO.findAll();
		result.include("cidades",cidLista);
		
		LocalDAOImpl locDAO = new LocalDAOImpl();
		List<Local> locLista = locDAO.findAll();
		result.include("locais",locLista);
	}

	@Post
	@Path("/cadastrar/evento")
	public void cadastrar(Evento evento, Integer local_id,Local local, Integer cidade_id, Integer categoria_id) {
		evento.setEve_executor((int) usuarioSession.getId());
		
		if (evento.getEve_titulo() == null) {
			validator.add(new SimpleMessage("tituloEvento", "Favor adicionar um título."));
		}
		if (evento.getEve_descricao() == null) {
			validator.add(new SimpleMessage("descricaoEvento", "Favor adicionar uma descrição."));
		}
		if (evento.getEve_data() == null) {
			validator.add(new SimpleMessage("dataEvento", "Favor adicionar uma data."));
		}
		if (evento.getEve_max_inteira() == 0) {
			validator.add(new SimpleMessage("maxIntEvento", "Favor adicionar um máximo de entradas do tipo inteira."));
		}
		if (evento.getEve_valor_inteira() == 0) {
			validator.add(new SimpleMessage("valIntEvento", "Favor informar o valor de entradas do tipo inteira."));
		}
		if ((evento.getEve_valor_meia() == 0) && (evento.getEve_max_meia() != 0)) {
			validator.add(new SimpleMessage("valMeiaEvento", "Favor informar o valor de entradas do tipo meia."));
		}
		if (local_id == 0) {			
			if(local.getLoc_nome() == null && local.getLoc_endereco() == null && cidade_id == 0) {
				validator.add(new SimpleMessage("localEvento", "Favor informar o local do evento."));
			}
			
			else if(local.getLoc_nome() == null || local.getLoc_endereco() == null || cidade_id == 0) {
				validator.add(new SimpleMessage("localEvento", "Favor completar o cadastro do local."));
			}
		}
		if (categoria_id == 0) {
			validator.add(new SimpleMessage("categoriaEvento", "Favor informar a categoria do evento."));
		}

		validator.onErrorForwardTo(EventoController.class).cadastroEvento();
		
		Date date = new Date();
		evento.setEve_data_ics(date);
		
		LocalDAOImpl locDao = new LocalDAOImpl();
		if (local_id != 0) {			
			Local locTemp = locDao.findById(local_id);			
			evento.setEve_local(locTemp);
		}
		else {
			CidadeDAOImpl cidDao = new CidadeDAOImpl();
			Cidade cidTemp = cidDao.findById(cidade_id);
			
			local.setLoc_cidade(cidTemp);
			
			locDao.persist(local);
			
			evento.setEve_local(local);			
		}
		
		CategoriaDAOImpl catDao = new CategoriaDAOImpl();			
		Categoria catTemp = catDao.findById(categoria_id);
		evento.setEve_categoria(catTemp);
		evento.setEve_taxa(catTemp.getCat_taxa());		
		
		EventoStatus staTemp = dao.statusById(1);
		evento.setEve_status(staTemp);
		
		dao.persist(evento);
		
		result.redirectTo(EventoController.class).evento(evento.getEve_cod());
	}
	
	@Get("/evento/{evento_id}")
	public void evento(Long evento_id) {
		Evento evento = dao.findById(evento_id);
		ArrayList<Evento> pendentes = dao.findPendentes();
		
		result.include("numeroPendentes", pendentes.size());
		result.include(evento);
		
	}
	
	@Get("/evento/meuseventos")
	public void meusEventos() {
		if(usuarioSession.isLogado()) {
			ArrayList<Evento> meusEventos = dao.findByExecutor((long) usuarioSession.getId());
			result.include("meusEventos", meusEventos);
		}	
	}
	
	@Get("/evento/balanco")
	public void balanco() {
		ArrayList<Evento> balanco = dao.balancoGeral();
		ArrayList<Evento> pendentes = dao.findPendentes();
		
		result.include("balancos", balanco);
		result.include("numeroPendentes", pendentes.size());
	}
	
	@Get("/evento/pendentes")
	public void pendentesAprovacao() {
		ArrayList<Evento> pendentes = dao.findPendentes();
		
		result.include("numeroPendentes", pendentes.size());
		result.include("pendentes", pendentes);
	}
	
	
	public void cancelarEvento(boolean bol, Integer id){
		if (bol) {
			Evento evento = dao.findById(id);
			dao.reembolsarEvento(evento);
		}else {
			System.out.println("cancelado");
		}
	}
	
	@Post
	@Path("/evento/altera/situacao")
	public void mudarSituacaoEvento(Evento evento, String decisao) {
		
		evento = dao.findById(evento.getEve_cod());
		
		if(decisao.equals("A")) {
			System.out.println("Evento para aprovacao");
			dao.aprovarEvento(evento);
			
		}else if(decisao.equals("C")){
			System.out.println("Evento para reprovacao");
			dao.reprovarEvento(evento);
			
		}else if(decisao.equals("CE")){
			dao.reembolsarEvento(evento);
		}
		
		result.redirectTo(EventoController.class).evento(evento.getEve_cod());
	}
	
	@Post
	@Path("/evento/{evento_id}/comprar")
	public void comprarIngresso(Long evento_id, Integer qtdIngresso, Integer qtdIngressoMeia) {
		
		if ((qtdIngresso == null || qtdIngresso < 1) && (qtdIngressoMeia == null || qtdIngressoMeia < 1)) {
			validator.add(new SimpleMessage("quantidadeIngressos", "Preencher os campos de quantidade de ingressos corretamente."));
		}else if(qtdIngresso == null || qtdIngresso < 1) {
			qtdIngresso = 0;
		}else if(qtdIngressoMeia == null || qtdIngressoMeia < 1) {
			qtdIngressoMeia = 0;
		}
		
		validator.onErrorForwardTo(EventoController.class).evento(evento_id);
		
		ReservaDAOImpl reservaDao = new ReservaDAOImpl();
		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		
		Usuario usuarioComprador = new Usuario();
		usuarioComprador = usuarioDao.findById(usuarioSession.getId());
		
		Evento evento = dao.findById(evento_id);
		
		// Verifica disponibilidade dos ingressos ------------------------------------------------------------
		boolean inteiraDisponivel = false;
		
		if(qtdIngresso != null) {
			BigDecimal qtdInteirasReservadas = reservaDao.getReservasInteirasByEvento(evento);
			if((qtdInteirasReservadas.intValue() + qtdIngresso) < evento.getEve_max_inteira()) {
				inteiraDisponivel = true;
			}

		}
		
		boolean meiaDisponivel = false;
		
		if(qtdIngressoMeia != null) {
			BigDecimal qtdMeiasReservadas = reservaDao.getReservasMeiasByEvento(evento);
			if((qtdMeiasReservadas.intValue() + qtdIngressoMeia) < evento.getEve_max_meia()) {
				meiaDisponivel = true;
			}
		}
		// ---------------------------------------------------------------------------------------------------
		
		if(inteiraDisponivel && meiaDisponivel) {
			reservaDao.efeutarReserva(gerarReserva(usuarioComprador, evento, qtdIngresso, qtdIngressoMeia));
			result.redirectTo(IndexController.class).index();
		}else {
			String esgotados = "";
			if(inteiraDisponivel) {
				esgotados += " Os ingressos de entrada inteira estão esgotados.";
			}
			
			if(meiaDisponivel) {
				esgotados += "Os ingressos de meia entrada estão esgotados.";
			}
			validator.add(new SimpleMessage("ingresso nao disponivel.", esgotados));
		}
		validator.onErrorForwardTo(EventoController.class).evento(evento_id);
		
	}
	
	public void validateValues(Evento evento) {
		
	}
	
	private Reserva gerarReserva(Usuario usuarioComprador, Evento evento, Integer qtdIngresso, Integer qtdIngressoMeia) {
		ReservaDAOImpl reservaDao = new ReservaDAOImpl();
		
		Reserva novaReserva = new Reserva();
		
		novaReserva.setRes_comprador(usuarioComprador);
		novaReserva.setRes_evento(evento);
		novaReserva.setRes_data_ics(new Date());
		
		// ------ Pegando dia depois de hoje ----
		Date dataExp = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dataExp); 
		c.add(Calendar.DATE, 1);
		
		novaReserva.setRes_data_exp(c.getTime());
		// ----------------------------------------
		
		novaReserva.setRes_qtd_inteira(qtdIngresso);
		novaReserva.setRes_qtd_meia(qtdIngressoMeia);
		
		novaReserva.setRes_valor_total((qtdIngresso * evento.getEve_valor_inteira()) + (qtdIngressoMeia * evento.getEve_valor_meia()));
		novaReserva.setRes_status(reservaDao.getStatus("1"));
		
		return novaReserva;
	}
	
}
