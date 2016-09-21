package br.com.triadworks.bugtracker.service.importacao;

import org.springframework.stereotype.Component;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Component
public class UsuarioParser {

	public Usuario parse(String linha){
		
		String[] valores = linha.split(";");
		
		Usuario usuario = new Usuario();
		usuario.setNome(valores[0]);
		usuario.setLogin(valores[1]);
		usuario.setSenha(valores[2]);
		
		return usuario;
		
		
	}
	
}
