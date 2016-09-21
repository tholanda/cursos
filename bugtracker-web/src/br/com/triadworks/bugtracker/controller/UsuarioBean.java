package br.com.triadworks.bugtracker.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@Scope("request")
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios;
	
	private UsuarioDao dao;
	private FacesUtils facesUtils;
	
	@Autowired
	public UsuarioBean(UsuarioDao dao, FacesUtils facesUtils){
		this.dao = dao;
		this.facesUtils = facesUtils;
	}
	
	public void adiciona(){
		dao.adiciona(usuario);
		usuario = new Usuario(); 
		facesUtils.adicionaMensagemDeSucesso("Usuário adicionado com sucesso!");
	}
	
	public void lista(){
		usuarios = dao.lista();
	}
	
	public void remove(Usuario usuario){
		usuarios = dao.lista();
		facesUtils.adicionaMensagemDeSucesso("Usuário removido com sucesso!");
	}
	
	public void altera(){
		dao.atualiza(this.usuario); 
		facesUtils.adicionaMensagemDeSucesso("Usuário atualizado com sucesso!");
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDao getDao() {
		return dao;
	}

	
}