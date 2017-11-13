package br.edu.ifsp.ingresso.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.dao.evento.EventoDAOImpl;
import br.edu.ifsp.ingresso.models.Evento;

@Controller
public class EventoController {
	private EventoDAOImpl dao;

	@Inject
	private Validator validator;

	@Inject
	private Result result;

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
		if (evento != null) {
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
				validator.add(new SimpleMessage("maxIntEvento", "Favor adicionar um máximo de entradas inteiras."));
			}
			if (evento.getEve_max_meia() == null) {
				validator.add(new SimpleMessage("maxMeiaEvento", "Favor adicionar um máximo de entradas inteiras."));
			}
			if (evento.getEve_taxa() == null) {
				validator.add(new SimpleMessage("taxaEvento", "Favor adicionar uma taxa."));
			}
			if (evento.getEve_taxa() == null) {
				validator.add(new SimpleMessage("cidadeEvento", "Favor adicionar uma cidade."));
			}

			validator.onErrorUsePageOf(EventoController.class).cadastroEvento();
			
			evento.setEve_status(1);
			evento.setEve_taxa((float) 0.0);
			
			dao.persist(evento);
			result.redirectTo(EventoController.class).cadastroEvento();
		} else {
			validator.add(new SimpleMessage("form", "Favor adicionar dados corretamente."));
		}
	}
	
	@Get("/evento")
	public void evento() {
		
	}

	/*@Get("/evento/{evento_id}")
	public void buscarEvento(Long evento_id) {
		Evento evento = dao.findById(evento_id);

		System.out.println(evento.getEve_titulo());
	}*/
}
