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
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class CadastroEventoController {
	private EventoDAOImpl dao;
	
	@Inject
	private Validator validator;
	
	@Inject
	private Result result;
	
	public CadastroEventoController() {
		this.dao = new EventoDAOImpl();
	}
	
	@Get
	@Path("/cadastrar/evento")
	public void cadastroEvento() {
		
	}
	
	@Get("/evento/{evento_id}")
	public void buscarEvento(Long evento_id) {
		Evento evento = dao.findById(evento_id);
		
		System.out.println(evento.getEve_titulo());
	}
}
