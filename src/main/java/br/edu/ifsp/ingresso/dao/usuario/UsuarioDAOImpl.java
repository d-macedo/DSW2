package br.edu.ifsp.ingresso.dao.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.edu.ifsp.ingresso.conn.FactoryEntityManager;
import br.edu.ifsp.ingresso.models.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {
	
	private EntityManager manager; 
	
	public UsuarioDAOImpl() {
		this.manager = FactoryEntityManager.getEntityManager();
	}

	@Override
	public void persist(Usuario user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public void update(Usuario user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public void delete (Usuario user) {
		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();
	}
	
	@Override
	public Usuario findById(long id) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE USU_COD = :id").setParameter("id", id);
		Usuario user = (Usuario) query.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll(){
		Query query = manager.createQuery("SELECT * FROM Usuario");
		List<Usuario> users = query.getResultList();
		
		if(users.isEmpty()) {
			return null;
		}else {
			return users;
		}	
	}
	
	@Override
	public Usuario findByEmail(String email) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE USU_EMAIL = :email").setParameter("email", email);
		Usuario user = (Usuario) query.getSingleResult(); 
		
		if(user != null) {
			return user;
		}else {
			return null;
		}
		
	}

}
