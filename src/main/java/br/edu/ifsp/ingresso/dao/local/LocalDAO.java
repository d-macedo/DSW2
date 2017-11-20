package br.edu.ifsp.ingresso.dao.local;

import java.util.List;

import br.edu.ifsp.ingresso.models.Local;

public interface LocalDAO {
	
	public void persist(Local entity);
	public void update(Local entity);	
    public Local findById(long id);
    public void delete(Local entity);	     
    public List<Local> findAll();

}
