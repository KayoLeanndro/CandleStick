package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Candlestick;

public class CandleStickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMaiorQueMinimo() {
		new Candlestick(new BigDecimal("10"), // abertura
				new BigDecimal("20"), // fechamento
				new BigDecimal("15"), // minimo
				new BigDecimal("10"), // maximo
				new BigDecimal("10000"), // volume
				Calendar.getInstance());
	}

	@Test(expected = NullPointerException.class)
	public void naoCriaCandleStickComDataNula() {
		new Candlestick(new BigDecimal("10"), // abertura
				new BigDecimal("20"), // fechamento
				new BigDecimal("10"), // minimo
				new BigDecimal("20"), // maximo
				new BigDecimal("10000"), // volume
				null);
	}
	
	 @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoAberturaForNula() {
	        new Candlestick(null, new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("15"),
	                new BigDecimal("1000"), Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoFechamentoForNulo() {
	        new Candlestick(new BigDecimal("10"), null, new BigDecimal("5"), new BigDecimal("15"),
	                new BigDecimal("1000"), Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoMinimoForNulo() {
	        new Candlestick(new BigDecimal("10"), new BigDecimal("20"), null, new BigDecimal("15"),
	                new BigDecimal("1000"), Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoMaximoForNulo() {
	        new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("5"), null,
	                new BigDecimal("1000"), Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoVolumeForNulo() {
	        new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("5"), new BigDecimal("15"),
	                null, Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoDataForNula() {
	        new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("5"), new BigDecimal("15"),
	                new BigDecimal("1000"), null);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void deveLancarExcecaoQuandoMinimoMaiorQueMaximo() {
	        new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("30"), new BigDecimal("15"),
	                new BigDecimal("1000"), Calendar.getInstance());
	    }
	    
	    @Test
	    public void quandoAberturaIgualFechamentoEhAlta() {
	    	Candlestick c = new Candlestick(new BigDecimal("10"), // abertura
					new BigDecimal("10"), // fechamento
					new BigDecimal("10"), // minimo
					new BigDecimal("10"), // maximo
					new BigDecimal("10000"), // volume
					Calendar.getInstance());
	    	
	    	assertTrue(c.isAlta());
	    }
	    
	    @Test
	    public void quandoFechamentoMenorAberturaEhBaixa() {
	    	Candlestick c = new Candlestick(new BigDecimal("10"), // abertura
					new BigDecimal("5"), // fechamento
					new BigDecimal("10"), // minimo
					new BigDecimal("10"), // maximo
					new BigDecimal("10000"), // volume
					Calendar.getInstance());
	    	
	    	assertTrue(c.isBaixa());
	    }

}
