package br.com.triadworks.bugtracker.controller.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@ViewScoped
@ManagedBean
public class BugDataModel extends LazyDataModel<Bug> {

	@ManagedProperty("#{bugDao}")
	private BugDao dao;
	
	@PostConstruct
	public void init(){
		this.setRowCount(dao.contaTodos());
	}

	@Override
	public List<Bug> load(int inicio, int quantidade, String campoDeOrdenacao, SortOrder sentidoDeOrdenacao, Map<String,String> filtros){
		
		return dao.listaPaginada(inicio, quantidade);
	}
	
	public BugDao getDao() {
		return dao;
	}

	public void setDao(BugDao dao) {
		this.dao = dao;
	}	
}
