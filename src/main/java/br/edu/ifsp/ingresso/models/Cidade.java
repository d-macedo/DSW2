package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "CIDADE")
public class Cidade {
		
	@Id
	@Column(name = "CID_COD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid_cod;
	
	@Column(name = "CID_NOME")
	private String cid_nome;
	
	@Column(name = "CID_ABREV")
	private String cid_abrev;
	
	@ManyToOne
	@JoinColumn(name = "CID_ESTADO", referencedColumnName="EST_COD")
	private Estado cid_estado;

	public Integer getCid_cod() {
		return cid_cod;
	}

	public void setCid_cod(Integer cid_cod) {
		this.cid_cod = cid_cod;
	}

	public String getCid_nome() {
		return cid_nome;
	}

	public void setCid_nome(String cid_nome) {
		this.cid_nome = cid_nome;
	}

	public String getCid_abrev() {
		return cid_abrev;
	}

	public void setCid_abrev(String cid_abrev) {
		this.cid_abrev = cid_abrev;
	}

	public Estado getCid_estado() {
		return cid_estado;
	}

	public void setCid_estado(Estado cid_estado) {
		this.cid_estado = cid_estado;
	}
	
}
