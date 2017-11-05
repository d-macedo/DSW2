package br.edu.ifsp.ingresso.component;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;

@RequestScoped
public class DeveSerGerente implements CustomBrutauthRule {
	
	private UsuarioSession user;
	
	@Inject
	public DeveSerGerente(UsuarioSession user) {
		this.user = user;
	}

	public boolean isAllowed(){
		if (user.getTipoUsuario() == "G") {
			return true;
		}
		return false;
	}
	
	

}
