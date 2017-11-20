package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EVENTO_STATUS")
public class EventoStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EST_COD")
	private Integer est_cod;
	
	@Column(name = "EST_DESCRICAO")
	@NotNull
	private String est_descricao;

	public Integer getEst_cod() {
		return est_cod;
	}

	public void setEst_cod(Integer est_cod) {
		this.est_cod = est_cod;
	}

	public String getEst_descricao() {
		return est_descricao;
	}

	public void setEst_descricao(String est_descricao) {
		this.est_descricao = est_descricao;
	}
}
