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
@Table(name= "LOCAL")
public class Local {
		
	@Id
	@Column(name = "LOC_COD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loc_cod;
	
	@Column(name = "LOC_NOME")
	private String loc_nome;
	
	@Column(name = "LOC_DES")
	private String loc_descricao;
	
	@Column(name = "LOC_ENDERECO")
	private String loc_endereco;
	
	@ManyToOne
	@JoinColumn(name = "LOC_CIDADE", referencedColumnName="CID_COD")
	private Cidade loc_cidade;

	public Integer getLoc_cod() {
		return loc_cod;
	}

	public void setLoc_cod(Integer loc_cod) {
		this.loc_cod = loc_cod;
	}

	public String getLoc_descricao() {
		return loc_descricao;
	}

	public void setLoc_descricao(String loc_descricao) {
		this.loc_descricao = loc_descricao;
	}

	public String getLoc_endereco() {
		return loc_endereco;
	}

	public void setLoc_endereco(String loc_endereco) {
		this.loc_endereco = loc_endereco;
	}

	public Cidade getLoc_cidade() {
		return loc_cidade;
	}

	public void setLoc_cidade(Cidade loc_cidade) {
		this.loc_cidade = loc_cidade;
	}

	public String getLoc_nome() {
		return loc_nome;
	}

	public void setLoc_nome(String loc_nome) {
		this.loc_nome = loc_nome;
	}
}
