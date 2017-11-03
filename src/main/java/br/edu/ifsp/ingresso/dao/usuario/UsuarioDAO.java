package br.edu.ifsp.ingresso.dao.usuario;

import java.util.List;

import br.edu.ifsp.ingresso.models.Usuario;

public interface UsuarioDAO {
	
	public void persist(Usuario entity);
	public void update(Usuario entity);	
    public Usuario findById(long id);
    public void delete(Usuario entity);	     
    public List<Usuario> findAll();
	public void deleteAll();

}
