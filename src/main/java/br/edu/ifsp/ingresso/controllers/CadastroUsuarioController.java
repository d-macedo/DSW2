package br.edu.ifsp.ingresso.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.edu.ifsp.ingresso.dao.usuario.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Usuario;

@Controller
public class CadastroUsuarioController {
	
	private UsuarioDAOImpl dao;
	
	public CadastroUsuarioController(UsuarioDAOImpl dao) {
		super();
		this.dao = new UsuarioDAOImpl();
	}
	
	
	@Path("/cadastro_usuario")
	public void cadastro_usuario() {
		
	}
	
	@Post
	@Path("/cadastro_usuario/cadastrar")
	public void cadastrar (Usuario user) {
		Usuario carregado = new Usuario();
		
		carregado.setUsu_email(user.getUsu_email());
		carregado.setUsu_nome(user.getUsu_nome());
		carregado.setUsu_senha(user.getUsu_senha());
		carregado.setUsu_tipo(user.getUsu_tipo());
		
		dao.persist(carregado);
	}

}
