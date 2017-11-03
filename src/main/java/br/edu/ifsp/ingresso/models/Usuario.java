package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
	@Id
	private long usu_cod;
	
	@Column
	@NotNull
	private String usu_nome;
	
	@Column
	@NotNull
	private String usu_email;
	
	@Column
	@NotNull
	private String usu_senha;
	
	@Column
	@NotNull
	private char usu_tipo;
	
	@Column
	private String usu_img_path;

	public long getUsu_cod() {
		return usu_cod;
	}

	public void setUsu_cod(long usu_cod) {
		this.usu_cod = usu_cod;
	}

	public String getUsu_nome() {
		return usu_nome;
	}

	public void setUsu_nome(String usu_nome) {
		this.usu_nome = usu_nome;
	}

	public String getUsu_email() {
		return usu_email;
	}

	public void setUsu_email(String usu_email) {
		this.usu_email = usu_email;
	}

	public String getUsu_senha() {
		return usu_senha;
	}

	public void setUsu_senha(String usu_senha) {
		this.usu_senha = usu_senha;
	}

	public char getUsu_tipo() {
		return usu_tipo;
	}

	public void setUsu_tipo(char usu_tipo) {
		this.usu_tipo = usu_tipo;
	}

	public String getUsu_img_path() {
		return usu_img_path;
	}

	public void setUsu_img_path(String usu_img_path) {
		this.usu_img_path = usu_img_path;
	}
	
	
}
