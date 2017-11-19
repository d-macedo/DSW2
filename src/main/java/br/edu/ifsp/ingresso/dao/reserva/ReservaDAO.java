package br.edu.ifsp.ingresso.dao.reserva;

import java.util.List;

import br.edu.ifsp.ingresso.models.Reserva;

public interface ReservaDAO {
	
	public void persist(Reserva entity);
	public void update(Reserva entity);	
    public Reserva findById(long id);
    public void delete(Reserva entity);	     
    public List<Reserva> findAll();

}
