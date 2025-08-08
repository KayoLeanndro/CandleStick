package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class CandleStickFactoryTest {
	
	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();
	
		Negociacao n1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		Negociacao n2 = new Negociacao(BigDecimal.valueOf(40.3), 100, hoje);
		Negociacao n3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
		Negociacao n4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4);
		
		CandleStickFactory candleStickFactory = new CandleStickFactory();
		
		Candlestick candle = candleStickFactory.constroiCandleParaData(hoje, negociacoes);

		Assert.assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura());
		Assert.assertEquals(BigDecimal.valueOf(42.3), candle.getFechamento());
		Assert.assertEquals(BigDecimal.valueOf(39.8), candle.getMinimo());
		Assert.assertEquals(BigDecimal.valueOf(42.3), candle.getMaximo());
		Assert.assertEquals(BigDecimal.valueOf(16290.0), candle.getVolume());
		
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		List<Negociacao> negociacoes = Arrays.asList(n1);
		
		CandleStickFactory candleStickFactory = new CandleStickFactory();
		
		Candlestick candle = candleStickFactory.constroiCandleParaData(hoje, negociacoes);

		Assert.assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura());
		Assert.assertEquals(BigDecimal.valueOf(40.5), candle.getFechamento());
		Assert.assertEquals(BigDecimal.valueOf(40.5), candle.getMinimo());
		Assert.assertEquals(BigDecimal.valueOf(40.5), candle.getMaximo());
		Assert.assertEquals(BigDecimal.valueOf(4050.0), candle.getVolume());
	}
	
	@Test
	public void apenasUmaGeracaoGeraCandle() {
Calendar hoje = Calendar.getInstance();
		
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandleStickFactory candleStickFactory = new CandleStickFactory();
		
		Candlestick candle = candleStickFactory.constroiCandleParaData(hoje, negociacoes);

		Assert.assertEquals(BigDecimal.valueOf(0), candle.getAbertura());
		Assert.assertEquals(BigDecimal.valueOf(0), candle.getFechamento());
		Assert.assertEquals(BigDecimal.valueOf(0), candle.getMinimo());
		Assert.assertEquals(BigDecimal.valueOf(0), candle.getMaximo());
		Assert.assertEquals(BigDecimal.valueOf(0), candle.getVolume());
	}
}
