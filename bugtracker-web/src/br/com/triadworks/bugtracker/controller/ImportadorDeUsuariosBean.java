package br.com.triadworks.bugtracker.controller;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.service.importacao.ImportadorDeUsuarios;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@Scope("request")
public class ImportadorDeUsuariosBean {

	private Part arquivo;
	
	private ImportadorDeUsuarios importador;
	private FacesUtils facesUtils;
	
	@Autowired
	public ImportadorDeUsuariosBean(ImportadorDeUsuarios importador,
			FacesUtils facesUtils) {
		this.importador = importador;
		this.facesUtils = facesUtils;
	}
	
	public void importa(){
		try{
			importador.importa(arquivo.getInputStream());
			facesUtils.adicionaMensagemDeSucesso("Arquivo ok");
		}catch (Exception e){
			facesUtils.adicionaMensagemDeErro("Arquivo erro - " + e.getMessage());
		}
		
		
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
