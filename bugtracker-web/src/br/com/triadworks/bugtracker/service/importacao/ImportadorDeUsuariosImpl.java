package br.com.triadworks.bugtracker.service.importacao;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@Service
public class ImportadorDeUsuariosImpl implements ImportadorDeUsuarios {
	
	private UsuarioParser parser;
	private UsuarioDao dao;
	
	@Autowired
	public ImportadorDeUsuariosImpl(UsuarioParser parser, UsuarioDao dao){
		
		this.parser = parser;
		this.dao = dao;
	}
	
	@Transactional
	public void importa(InputStream inputStream){
		Scanner scanner = null;
		try {
			scanner = new Scanner(inputStream);
			while(scanner.hasNextLine()){
				String linha = scanner.nextLine();
				Usuario usuario = parser.parse(linha);
				dao.adiciona(usuario);
			}
		} finally {
			if (scanner!=null){
				scanner.close();
			}
		}
	}

}
