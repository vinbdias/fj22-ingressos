package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {
	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	
	@Before
	public void preparaSalaFilmeESessao() {
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		
		BigDecimal precoEsperado = new BigDecimal("32.50");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressoDeClientesDeBancos() {
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());		
	}
	
	@Test
	public void deveConcederDescontoDe50PorCentoParaIngressoDeEstudante() {
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());		
	}	
}
