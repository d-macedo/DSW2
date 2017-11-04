package br.edu.ifsp.ingresso.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.ifsp.ingresso.dao.usuario.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class CadastroUsuarioController {
	
	private UsuarioDAOImpl dao;
	
	@Inject
	private Validator validator;
	
	public CadastroUsuarioController() {
		this.dao = new UsuarioDAOImpl();
	}
	
	
	@Path("/cadastrar/usuario")
	public void cadastro_usuario() {
		
	}
	
	@Post
	@Path("/cadastro_usuario/cadastrar")
	public void cadastrar (Usuario user) {
		Usuario carregado = new Usuario();
		
		if (user.getUsu_email().equals(null)) {
			validator.add(new SimpleMessage("email","Favor adicionar o email"));
		}
		else if (user.getUsu_senha().equals(null)) {
			validator.add(new SimpleMessage("senha","Favor adicionar a senha"));
		}
		else if(user.getUsu_nome().equals(null)) {
			validator.add(new SimpleMessage("nome", "Favor adicionar o nome"));
		}
		
		
		carregado.setUsu_email(user.getUsu_email());
		carregado.setUsu_nome(user.getUsu_nome());
		carregado.setUsu_senha(user.getUsu_senha());
		carregado.setUsu_tipo(user.getUsu_tipo());
		
		dao.persist(carregado);
	}

}
