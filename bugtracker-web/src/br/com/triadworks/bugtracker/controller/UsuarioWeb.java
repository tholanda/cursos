package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Component
@Scope("session")
public class UsuarioWeb implements Serializable {
	
	private Usuario usuario;
	
	public void loga(Usuario usuario){
		this.usuario = usuario;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public boolean isLogado(){
		return this.usuario != null;
	}
	
	public void desloga(){
		this.usuario = null;
	}

}
