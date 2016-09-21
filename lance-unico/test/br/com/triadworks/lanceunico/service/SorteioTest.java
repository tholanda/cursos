package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.builders.CriadorDePromocao;
import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;



public class SorteioTest {
	
	private Sorteio sorteio;
	private Cliente rafael;
	private Cliente rommel;
	private Cliente handerson;
	
	@Before
	public void setUp(){
		this.sorteio = new Sorteio();
		this.rafael = new Cliente("Rafael");
		this.rommel = new Cliente("Rommel");
		this.handerson = new Cliente("Handerson");
	}
	
	@Test
	public void deveSortearLancesEmOrdemCrescente(){
		//cenário
		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(handerson, 250.0)
			.comLance(rafael, 300.0)
			.comLance(rommel, 400.0)
			.cria();
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearLancesEmOrdemDecrescente(){
		//cenário
		//Promocao promocao = new Promocao("Máquina de Lavar");
		//promocao.registra(new Lance(rafael, 400.0));
		//promocao.registra(new Lance(rommel, 300.0));
		//promocao.registra(new Lance(handerson, 250.0));
		
		Promocao promocao = new CriadorDePromocao()
			.para("Máquina de Lavar")
			.comLance(rafael, 400.0)
			.comLance(rommel, 300.0)
			.comLance(handerson, 250.0)
			.cria();
		
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearQuandoHouverApenasUmLance(){
		//cenário
		//Promocao promocao = new Promocao("Forno Microondas");
		//promocao.registra(new Lance(rafael, 600.0));
		
		Promocao promocao = new CriadorDePromocao()
			.para("Forno Microondas")
			.comLance(rafael, 600.0)
			.cria();
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		assertEquals(600.0, sorteio.getMaiorLance(), 0.0001);
		assertEquals(600.0, sorteio.getMenorLance(), 0.0001);
	}
	
	
	@Test
	public void deveSortearLancesEmOrdemAleatoria(){
		//cenário
		//Promocao promocao = new Promocao("Casa");
		//promocao.registra(new Lance(rafael, 1050));
		//promocao.registra(new Lance(rommel, 2990.99));
		//promocao.registra(new Lance(handerson, 24.70));
		//promocao.registra(new Lance(rafael, 477.00));
		//promocao.registra(new Lance(handerson, 1.25));
		
		Promocao promocao = new CriadorDePromocao()
			.para("Casa")
			.comLance(rafael, 1050.0)
			.comLance(rommel, 2990.99)
			.comLance(handerson, 24.70)
			.comLance(rafael, 477.0)
			.comLance(handerson, 1.25)
			.cria();
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado = 2990.99;
		double menorEsperado = 1.25;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	
	@Test
	public void deveSortearOsTresMenoresLances(){
		//cenário
		//Promocao promocao = new Promocao("Show");
		//promocao.registra(new Lance(rafael, 300.0));
		//promocao.registra(new Lance(rommel, 100.0));
		//promocao.registra(new Lance(handerson, 20.0));
		//promocao.registra(new Lance(rafael, 440.0));
		//promocao.registra(new Lance(handerson, 1.25));
		
		Promocao promocao = new CriadorDePromocao()
		.para("Show")
		.comLance(rafael, 300.0)
		.comLance(rommel, 100.0)
		.comLance(handerson, 20.0)
		.comLance(rafael, 440.0)
		.comLance(handerson, 1.25)
		.cria();
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		List<Lance> menores = sorteio.getTresMenoresLances();
		
		assertEquals(3, menores.size());
		assertEquals(1.25, menores.get(0).getValor(), 0.00001);
		assertEquals(20.0, menores.get(1).getValor(), 0.00001);
		assertEquals(100.0, menores.get(2).getValor(), 0.00001);

	}
	
	@Test
	public void deveSortearTodosOsLancesQuandoHouverMenosDe3Lances(){
		//cenário
		//Promocao promocao = new Promocao("Show");
		//promocao.registra(new Lance(rafael, 300.0));
		//promocao.registra(new Lance(handerson, 440.00));
		
		Promocao promocao = new CriadorDePromocao()
			.para("Show")
			.comLance(rafael, 300.0)
			.comLance(handerson, 440.0)
			.cria();

		//ação
		sorteio.sorteia(promocao);
		
		//validação
		List<Lance> menores = sorteio.getTresMenoresLances();
		
		assertEquals(2, menores.size(), 0.00001);


	}
	
	@Test
	public void naoDeveSortearQuandoNaoHouverLances(){
		//cenário
		Promocao promocao = new Promocao("Show");
				
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		List<Lance> menores = sorteio.getTresMenoresLances();
		
		assertEquals(0, menores.size(), 0.00001);


	}

}