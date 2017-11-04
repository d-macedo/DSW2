package br.edu.ifsp.ingresso.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.dao.usuario.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class CadastroUsuarioController {
	
	private UsuarioDAOImpl dao;
	
	@Inject
	private Validator validator;
	
	@Inject
	private Result result;
	
	public CadastroUsuarioController() {
		this.dao = new UsuarioDAOImpl();
	}
	
	@Get
	@Path("/cadastrar/usuario")
	public void cadastroUsuario() {
		
	}
	
	@Post
	@Path("/cadastrar/usuario")
	public void cadastrar (Usuario usuario) {
		
		if (usuario != null) {
			if(usuario.getUsu_email() == null) {
				validator.add(new SimpleMessage("email","Favor adicionar o email."));
			}
			if (usuario.getUsu_senha() == null) {
				validator.add(new SimpleMessage("senha","Favor adicionar a senha."));
			}
			if(usuario.getUsu_nome() == null) {
				validator.add(new SimpleMessage("nome", "Favor adicionar o nome."));
			}
		}else {
			validator.add(new SimpleMessage("form", "Favor adicionar dados corretamente."));
		}

		validator.onErrorUsePageOf(CadastroUsuarioController.class).cadastroUsuario();
			
		dao.persist(usuario);
		result.redirectTo(IndexController.class).index();
		
	}
}