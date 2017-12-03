package br.edu.ifsp.ingresso.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.dao.cidade.CidadeDAOImpl;
import br.edu.ifsp.ingresso.dao.evento.EventoDAOImpl;
import br.edu.ifsp.ingresso.models.Cidade;
import java.util.Date ;
import br.edu.ifsp.ingresso.models.Evento;

@Controller
public class IndexController {
	
	
	private EventoDAOImpl dao;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	public IndexController() {
		this.dao = new EventoDAOImpl();
	}
	
	@Get
	@Path("/")
	public void index() {
		ArrayList<Evento> pendentes = dao.findPendentes();
		result.include("numeroPendentes", pendentes.size());
	
		CidadeDAOImpl cidadeDao = new CidadeDAOImpl();
		List<Cidade> minhalista = cidadeDao.findAll();
		result.include("cidades",minhalista);	

	}
	
	
	@Post
	@Path("/")
	public void index(String evento, Integer cidade, Date data) throws ParseException{
		
		
		validator.onErrorForwardTo(IndexController.class).index();
		
		result.include("testa", 1);
		ArrayList<Evento> pendentes = dao.findPendentes();
		result.include("numeroPendentes", pendentes.size());

		CidadeDAOImpl cidadeDao = new CidadeDAOImpl();
		List<Cidade> minhalista = cidadeDao.findAll();
		result.include("cidades",minhalista);		
		
		List<Evento> eventos = dao.pesquisarEventosIndex(evento, cidade, data);
		result.include("eventos", eventos);
	}

}
