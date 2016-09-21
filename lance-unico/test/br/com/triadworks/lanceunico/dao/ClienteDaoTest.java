package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.util.JPAUtil;

public class ClienteDaoTest {
	
	private EntityManager entityManager;
	
	@Before
	public void setup(){
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
	}
	
	@After
	public void tearDown(){
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	@Test
	public void deveEncontrarClientePorEmail() {
		
		/* nao se deve usar mock no Dao
		//cenário
		EntityManager entityManager = mock(EntityManager.class);
		Query query = mock(Query.class);
		
		String jpql = "select c from Cliente c where x.email = :email";
		Cliente cliente = new Cliente("Principe do Oceano", "principe@oceano.com");
		
		when(entityManager.createQuery(jpql)).thenReturn(query);
		when(query.setParameter("email", "principe@oceano.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(cliente);
		
		//ação
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe@oceano.com");
		
		//validação
		assertEquals(cliente.getNome(), clienteDoBanco.getNome());
		assertEquals(cliente.getEmail(), clienteDoBanco.getEmail());
		*/
		
		//cenario
		//EntityManager entityManager = new JPAUtil().getEntityManager();
		//entityManager.getTransaction().begin();
		
		Cliente principe = new Cliente("Principe do Oceano", "principe@oceano.com");
		entityManager.persist(principe);
		
		//acao
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe@oceano.com");
		
		//validacao
		assertEquals(principe.getNome(), clienteDoBanco.getNome());
		assertEquals(principe.getEmail(), clienteDoBanco.getEmail());
		
		//entityManager.getTransaction().commit();
		//entityManager.close();
	}
	
	
	@Test
	public void naoDeveEncontrarClientePorEmail() {
		
		//cenario
		//EntityManager entityManager = new JPAUtil().getEntityManager();
		//entityManager.getTransaction().begin();
		
		//acao
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe@oceano.com");
		
		//validacao
		assertNull(clienteDoBanco);
		
		//entityManager.getTransaction().commit();
		//entityManager.close();
	}

}
