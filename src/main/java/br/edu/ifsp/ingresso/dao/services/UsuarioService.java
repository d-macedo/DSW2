package br.edu.ifsp.ingresso.dao.services;

import java.util.List;

import br.edu.ifsp.ingresso.dao.implementations.UsuarioDAOImpl;
import br.edu.ifsp.ingresso.models.Usuario;

public class UsuarioService {
	
	private static UsuarioDAOImpl usuarioDAO;
	
	public UsuarioService() {
		UsuarioService.usuarioDAO = new UsuarioDAOImpl();
	}
	
	public void insertUser(Usuario user) {
		usuarioDAO.openCurrentSessionWithTransaction();
		usuarioDAO.persist(user);
		usuarioDAO.closeCurrentSessionWithTransaction();
	}
	
	
	public void update (Usuario user) {
		usuarioDAO.openCurrentSessionWithTransaction();
		usuarioDAO.update(user);
		usuarioDAO.closeCurrentSessionWithTransaction();
	}
	
	public Usuario findById(long id) {
		usuarioDAO.openCurrentSession();
		Usuario user = usuarioDAO.findById(id);
		usuarioDAO.closeCurrentSession();
		return user;
	}
	
	public void delete(long id) {
		usuarioDAO.openCurrentSessionWithTransaction();
		Usuario user = usuarioDAO.findById(id);
		usuarioDAO.delete(user);
		usuarioDAO.closeCurrentSessionWithTransaction();
	}
	
	public List<Usuario> findAll(){
		usuarioDAO.openCurrentSession();
		List<Usuario> users = usuarioDAO.findAll();
		usuarioDAO.closeCurrentSession();
		return users;
	}
	
	public void deleteAll() {
		usuarioDAO.openCurrentSessionWithTransaction();
		usuarioDAO.deleteAll();
		usuarioDAO.closeCurrentSessionWithTransaction();
	}
	
}
