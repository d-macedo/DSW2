package br.edu.ifsp.ingresso.categoria;

import java.util.List;

import br.edu.ifsp.ingresso.models.Categoria;

public interface CategoriaDAO {
	
	public void persist(Categoria entity);
	public void update(Categoria entity);	
    public Categoria findById(long id);
    public void delete(Categoria entity);	     
    public List<Categoria> findAll();

}
