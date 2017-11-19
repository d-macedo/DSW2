package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "CATEGORIA")
public class Categoria {
		
	@Id
	@Column(name = "CAT_COD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cat_cod;
	
	@Column(name = "CAT_DESCRICAO")
	private String cat_descricao;
	
	@Column(name = "CAT_TAXA")
	private Float cat_taxa;

	public Integer getCat_cod() {
		return cat_cod;
	}

	public void setCat_cod(Integer cat_cod) {
		this.cat_cod = cat_cod;
	}

	public String getCat_descricao() {
		return cat_descricao;
	}

	public void setCat_descricao(String cat_descricao) {
		this.cat_descricao = cat_descricao;
	}

	public Float getCat_taxa() {
		return cat_taxa;
	}

	public void setCat_taxa(Float cat_taxa) {
		this.cat_taxa = cat_taxa;
	}
	
}
