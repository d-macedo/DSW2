package br.edu.ifsp.ingresso.dao.pagamento;

import java.util.List;

import br.edu.ifsp.ingresso.models.Pagamento;

public interface PagamentoDAO {
	
	public void persist(Pagamento entity);
	public void update(Pagamento entity);	
    public Pagamento findById(long id);
    public void delete(Pagamento entity);	     
    public List<Pagamento> findAll();

}
