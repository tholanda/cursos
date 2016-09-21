package br.com.triadworks.bugtracker.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class FacesUtils {
	
	private FacesContext facesContext;
	
	
	public FacesUtils(){
		
		this.facesContext = FacesContext.getCurrentInstance();
	}
	
	public void adicionaMensagemDeErro(String mensagem) {
		
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
		
	}
	
	public void adicionaMensagemDeSucesso(String mensagem) {
		
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
		
	}

}
