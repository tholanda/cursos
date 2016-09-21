package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.builders.CriadorDePromocao;
import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.modelo.Status;
import br.com.triadworks.lanceunico.util.JPAUtil;

public class PromocaoDaoTest {

	
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
	public void deveContarPromocoesEncerradas(){
		//cenario
		Promocao aberta = new CriadorDePromocao()
			.para("Notebook DELL")
			.comStatus(Status.ABERTA)
			.cria();
		
		Promocao encerrada = new CriadorDePromocao()
			.para("TV LED 32")
			.comStatus(Status.ENCERRADA)
			.cria();
		
		entityManager.persist(aberta);
		entityManager.persist(encerrada);
		
		//acao
		PromocaoDao dao = new PromocaoDao(entityManager);
		Long total = dao.totalDeEncerradas();
		
		//validacao
		Long totalEsperado = 1L;
		assertEquals(totalEsperado, total);
	}
	
	@Test
	public void deveRemoverUmaPromocao(){
		//cenario
		Promocao promocao = new CriadorDePromocao()
			.para("Netflix")
			.cria();
		
		entityManager.persist(promocao);
		
		//acao
		PromocaoDao dao = new PromocaoDao(entityManager);
		dao.remove(promocao);
		
		//validacao
		Promocao promocaoDoBanco = dao.carrega(promocao.getId());
		assertNull(promocaoDoBanco);
		
	}
	
	@Test
	public void deveRegistrarNovoLanceNaPromocao(){
		//cenario
		Cliente rafael = new Cliente("Rafael");
		
		Promocao promocao = new CriadorDePromocao()
				.para("Apple TV")
				.cria();
		
		entityManager.persist(rafael);
		entityManager.persist(promocao);
		
		//acao
		Integer id = promocao.getId();
		Lance lance = new Lance(rafael, 100.0);
		
		PromocaoDao dao = new PromocaoDao(entityManager);
		dao.registraLance(id, lance);
		
		//validacao
		Promocao promocaoDoBanco = dao.carrega(id);
		assertEquals(1, promocaoDoBanco.getLances().size());
	}

}
