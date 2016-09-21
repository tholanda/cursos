package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Comentario;

@Repository
@Transactional
public class BugDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Bug> lista() {
		
		try {
			return manager.createQuery("select b from Bug b", Bug.class)
					.getResultList();
		} finally {
			manager.close();
		}
	}

	public void salva(Bug bug) {
			manager.persist(bug);
	}

	public void atualiza(Bug bug) {
		
		manager.merge(bug);
	}

	public void remove(Bug bug) {
		
		manager.remove(manager.merge(bug));
	}

	public Bug busca(Integer id) {
		
		try {
			return manager.find(Bug.class, id);
		} finally {
			manager.close();
		}
	}

	public List<Bug> listaPaginada(int inicio, int quantidade) {
		
		try {
			return manager
					.createQuery("select b from Bug b", Bug.class)
					.setFirstResult(inicio)
					.setMaxResults(quantidade)
					.getResultList();
		} finally {
			manager.close();
		}
	}

	public int contaTodos() {
		
		try {
			Long count = manager
					.createQuery("select count(b) from Bug b", Long.class)
					.getSingleResult();
			return count.intValue();
		} finally {
			manager.close();
		}
	}
	
	public List<Bug> getBugsDoUsuario(Integer id) {
		
		try {
			return manager
					.createQuery("select b from Bug b where b.responsavel.id = :id", Bug.class)
					.setParameter("id", id)
					.getResultList();
		} finally {
			manager.close();
		}
	}
	
	public Bug buscaComComentarios(Integer id) {
		
		try {
			Bug bug = manager.find(Bug.class, id);
			if (bug != null)
				bug.getComentarios().size();
			return bug;
		} finally {
			manager.close();
		}
	}
	
	public void comenta(Integer id, Comentario comentario) {

			Bug bug = this.busca(id);
			bug.comenta(comentario);

	}
	
	public void fecha(Integer id, Comentario comentario) {
		
			Bug bug = this.busca(id);
			bug.fecha(comentario);
	}
	
}
