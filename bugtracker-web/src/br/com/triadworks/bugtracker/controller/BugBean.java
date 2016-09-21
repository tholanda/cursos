package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@Scope("request")
public class BugBean {
	
	private Bug bug = new Bug();
	
	private BugDao dao;
	
	private List<Bug> bugs = new ArrayList<Bug>();
	
	private FacesUtils facesUtils;
	
	@Autowired
	public BugBean(BugDao dao, FacesUtils facesUtils){
		this.dao = dao;
		this.facesUtils = facesUtils;
	}
	
	@PostConstruct
	public void init(){
		this.bug.setResponsavel(new Usuario());
	}
	
	public void adiciona(){
		dao.salva(bug);
		facesUtils.adicionaMensagemDeSucesso("Bug adicionado com sucesso e assinado para "+ bug.getResponsavel().getNome() + ".");
		this.bug = new Bug();
	}
	
	public void lista() {
		this.bugs = dao.lista();
	}
	
	public void remove(Bug bug){
		dao.remove(bug);
		this.bugs = dao.lista();
		facesUtils.adicionaMensagemDeSucesso("Bug removido com sucesso!");
	}
	
	public void altera(){
		dao.atualiza(bug);
		facesUtils.adicionaMensagemDeSucesso("Bug atualizado com sucesso!");
		
	}
	
	public List<Status> getTodosOsStatus() {
			return Arrays.asList(Status.values());
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public BugDao getDao() {
		return dao;
	}


	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}
	
	
}
