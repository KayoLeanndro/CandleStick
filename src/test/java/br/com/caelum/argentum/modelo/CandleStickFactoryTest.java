package br.com.caelum.argentum.modelo;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.CandleStickFactory;
import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.Negociacao;

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

	@Test
	public void paraNegociacoesDeTresDiasGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(new BigDecimal("10.50"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("11.20"), 200, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("9.75"), 150, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("10.00"), 300, hoje);
		
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao5 = new Negociacao(new BigDecimal("10.50"), 100, amanha);
		Negociacao negociacao6 = new Negociacao(new BigDecimal("11.20"), 200, amanha);
		
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao7 = new Negociacao(new BigDecimal("9.75"), 150, depois);
		Negociacao negociacao8 = new Negociacao(new BigDecimal("10.00"), 300, depois);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5, negociacao6, negociacao7, negociacao8);
		
		CandleStickFactory candleStickFactory = new CandleStickFactory();

		List<Candlestick> candles = candleStickFactory.constroiCandles(negociacoes);
		
		Assert.assertEquals(3, candles.size());
		
		Assert.assertEquals(new BigDecimal("10.50"), candles.get(0).getAbertura());
		Assert.assertEquals(new BigDecimal("10.00"), candles.get(0).getFechamento());

		
		Assert.assertEquals(new BigDecimal("10.50"), candles.get(1).getAbertura());
		Assert.assertEquals(new BigDecimal("11.20"), candles.get(1).getFechamento());

		
		Assert.assertEquals(new BigDecimal("9.75"), candles.get(2).getAbertura());
		Assert.assertEquals(new BigDecimal("10.00"), candles.get(2).getFechamento());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoPermiteConstruirCandlesComNegociacoesForaDeOrdem() {
	    Calendar hoje = Calendar.getInstance();

	    Negociacao negociacao1 = new Negociacao(new BigDecimal("10.50"), 100, hoje);
	    Negociacao negociacao2 = new Negociacao(new BigDecimal("11.20"), 200, hoje);
	    Negociacao negociacao3 = new Negociacao(new BigDecimal("9.75"), 150, hoje);
	    Negociacao negociacao4 = new Negociacao(new BigDecimal("10.00"), 300, hoje);
	    
	    Calendar amanha = (Calendar) hoje.clone();
	    amanha.add(Calendar.DAY_OF_MONTH, 1);
	    
	    Negociacao negociacao5 = new Negociacao(new BigDecimal("10.50"), 100, amanha);
	    Negociacao negociacao6 = new Negociacao(new BigDecimal("11.20"), 200, amanha);
	    
	    Calendar depois = (Calendar) amanha.clone();
	    depois.add(Calendar.DAY_OF_MONTH, 1);
	    
	    Negociacao negociacao7 = new Negociacao(new BigDecimal("9.75"), 150, depois);
	    Negociacao negociacao8 = new Negociacao(new BigDecimal("10.00"), 300, depois);
	    
	    // Lista fora de ordem proposital
	    List<Negociacao> negociacoes = Arrays.asList(
	        negociacao5, negociacao6, // amanhã
	        negociacao1, negociacao2, negociacao3, negociacao4, // hoje
	        negociacao7, negociacao8 // depois de amanhã
	    );
	    
	    CandleStickFactory candleStickFactory = new CandleStickFactory();
	    
	    // Aqui esperamos que IllegalArgumentException seja lançada
	    candleStickFactory.constroiCandles(negociacoes);
	}

}
