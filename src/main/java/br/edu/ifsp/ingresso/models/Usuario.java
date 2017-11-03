package br.edu.ifsp.ingresso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USU_COD")
	private long usu_cod;
	
	@Column(name = "USU_NOME")
	@NotNull
	private String usu_nome;
	
	@Column(name = "USU_EMAIL")
	@NotNull
	private String usu_email;
	
	@Column(name = "USU_SENHA")
	@NotNull
	private String usu_senha;
	
	@Column(name = "USU_TIPO")
	@NotNull
	private char usu_tipo;
	
	@Column(name = "USU_IMG_PATH")
	private String usu_img_path;
	
	
	
	public Usuario (){
	}
	
	public Usuario(String usu_nome, String usu_email, String usu_senha, char usu_tipo){
		this.usu_nome = usu_nome;
		this.usu_email = usu_email;
		this.usu_senha = usu_senha;
		this.usu_tipo = usu_tipo;
	}
	
	public Usuario(String usu_nome, String usu_email, String usu_senha, char usu_tipo, String usu_img_path){
		this.usu_nome = usu_nome;
		this.usu_email = usu_email;
		this.usu_senha = usu_senha;
		this.usu_tipo = usu_tipo;
		this.usu_img_path = usu_img_path;
	}
	
	
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

	@Override
	public String toString() {
		return "Usuario [usu_cod=" + usu_cod + ", usu_nome=" + usu_nome + ", usu_email=" + usu_email + ", usu_senha="
				+ usu_senha + ", usu_tipo=" + usu_tipo + ", usu_img_path=" + usu_img_path + "]";
	}
	
	
	
	
	
}
