package br.edu.ifsp.ingresso.component;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.edu.ifsp.ingresso.models.Usuario;

@SessionScoped
public class UsuarioSession implements Serializable{
	private static final long serialVersionUID = -2883031389844933915L;
	
	private Usuario logado;
	
	public void login(Usuario usuario) {
		this.logado = usuario;
	}
	  
	public String getNome() {
		return logado.getUsu_nome();
	}
	  
	public boolean isLogado() {
		return logado != null;
	}
	
	public void logout() {
		this.logado = null;
	}
}