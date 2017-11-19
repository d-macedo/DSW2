package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PAGAMENTO_STATUS")
public class PagamentoStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PST_COD")
	private int pst_cod;
	
	
	@Column(name = "PST_DESCRICAO")
	@NotNull
	private String pst_descricao;
	
	
	public int getPst_cod() {
		return pst_cod;
	}


	public void setPst_cod(int pst_cod) {
		this.pst_cod = pst_cod;
	}


	public String getPst_descricao() {
		return pst_descricao;
	}


	public void setPst_descricao(String pst_descricao) {
		this.pst_descricao = pst_descricao;
	}
	
}
