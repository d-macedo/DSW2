package br.edu.ifsp.ingresso.controllers;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.edu.ifsp.ingresso.dao.evento.EventoDAOImpl;
import br.edu.ifsp.ingresso.models.Evento;

@Controller
public class IndexController {
	private EventoDAOImpl dao;
	
	@Inject
	private Result result;
	
	public IndexController() {
		this.dao = new EventoDAOImpl();
	}
	
	@Path("/")
	public void index() {
		ArrayList<Evento> pendentes = dao.findPendentes();
		
		result.include("numeroPendentes", pendentes.size());
		
	}
}
