package br.edu.ifsp.ingresso.dao.evento;

import java.util.List;

import br.edu.ifsp.ingresso.models.Evento;

public interface EventoDAO {

	public void persist(Evento entity);
	public void update(Evento entity);	
    public Evento findById(long id);
    public void delete(Evento entity);	 
    public List<Evento> findPendentes();
    public List<Evento> findByExecutor(long id);
    public List<Evento> findAll(); 

}
