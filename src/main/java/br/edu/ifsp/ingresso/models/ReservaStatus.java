package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "RESERVA_STATUS")
public class ReservaStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RST_COD")
	private int rst_cod;
	
	@Column (name = "RST_DESCRICAO")
	@NotNull
	private String rst_descricao;
	
	
	public int getRst_cod() {
		return rst_cod;
	}

	public void setRst_cod(int rst_cod) {
		this.rst_cod = rst_cod;
	}

	public String getRst_descricao() {
		return rst_descricao;
	}
	
	public void setRst_descricao(String rst_descricao) {

		this.rst_descricao = rst_descricao;
	}
	
}
