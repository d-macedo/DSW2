package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ESTADO")
public class Estado {
		
	@Id
	@Column(name = "EST_COD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer est_cod;
	
	@Column(name = "EST_NOME")
	private String est_nome;
	
	@Column(name = "EST_SIGLA")
	private String est_sigla;

	public Integer getEst_cod() {
		return est_cod;
	}

	public void setEst_cod(Integer est_cod) {
		this.est_cod = est_cod;
	}

	public String getEst_nome() {
		return est_nome;
	}

	public void setEst_nome(String est_nome) {
		this.est_nome = est_nome;
	}

	public String getEst_sigla() {
		return est_sigla;
	}

	public void setEst_sigla(String est_abrev) {
		this.est_sigla = est_abrev;
	}

	
}
