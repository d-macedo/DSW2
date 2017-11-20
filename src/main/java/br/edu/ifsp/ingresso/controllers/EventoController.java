package br.edu.ifsp.ingresso.controllers;

import java.util.Date;
import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.component.UsuarioSession;
import br.edu.ifsp.ingresso.dao.evento.EventoDAOImpl;
import br.edu.ifsp.ingresso.models.Evento;

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

	}

	@Post
	@Path("/cadastrar/evento")
	public void cadastrar(Evento evento) {
		evento.setEve_executor((int) usuarioSession.getId());
		System.out.println(evento.getEve_data());
		if (evento.getEve_titulo() == null) {
			validator.add(new SimpleMessage("tituloEvento", "Favor adicionar um título."));
		}
		if (evento.getEve_descricao() == null) {
			validator.add(new SimpleMessage("descricaoEvento", "Favor adicionar uma descrição."));
		}
		if (evento.getEve_data() == null) {
			validator.add(new SimpleMessage("dataEvento", "Favor adicionar uma data."));
		}
		if (evento.getEve_max_inteira() == null) {
			validator.add(new SimpleMessage("maxIntEvento", "Favor adicionar um máximo de entradas do tipo inteira."));
		}
		if (evento.getEve_max_meia() == null) {
			validator.add(new SimpleMessage("maxMeiaEvento", "Favor adicionar um máximo entradas do tipo meia."));
		}

		validator.onErrorUsePageOf(EventoController.class).cadastroEvento();
		Date date = new Date();
		evento.setEve_data_ics(date);
		evento.setEve_categoria(null);
		evento.setEve_local(null);
		evento.setEve_status(null);
		evento.setEve_taxa((float) 0.0);
		
		dao.persist(evento);
		result.redirectTo(EventoController.class).cadastroEvento();
	}
	
	@Get("/evento/{evento_id}")
	public void evento(Long evento_id) {
		Evento evento = dao.findById(evento_id);
		result.include(evento);
	}
	
	
	public void cancelarEvento(boolean bol, Integer id){
		if (bol) {
			Evento evento = dao.findById(id);
			dao.reembolsarEvento(evento);
		}else {
			System.out.println("cancelado");
		}
	}
	
	public void validateValues(Evento evento) {
		
	}
	
}
