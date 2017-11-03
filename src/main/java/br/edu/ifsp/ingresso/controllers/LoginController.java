package br.edu.ifsp.ingresso.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.component.UsuarioSession;
import br.edu.ifsp.ingresso.dao.usuario.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class LoginController {
	
	@Inject
	private UsuarioSession usuarioSession;
	
	private UsuarioDAOImpl dao;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	public LoginController() {
		this.dao = new UsuarioDAOImpl();
	}

	@Path("/login")
	public void login() {

	}

	@Post
	@Path("/login/autenticar")
	public void autenticar(Usuario usuario) {
		Usuario carregado = dao.findByEmail(usuario.getUsu_email());
		
		if (carregado == null) {
			validator.add(new SimpleMessage("Login e/ou senha inválidos", "usuario.login"));
		}
		
		validator.onErrorUsePageOf(LoginController.class).login();
		
		usuarioSession.login(carregado);
		result.redirectTo(IndexController.class).index();
	}

}
