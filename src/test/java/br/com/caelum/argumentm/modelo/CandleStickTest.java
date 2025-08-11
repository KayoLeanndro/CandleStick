package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

public class CandleStickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMaiorQueMinimo() {
		new Candlestick(
			    new BigDecimal("10"),       // abertura
			    new BigDecimal("20"),       // fechamento
			    new BigDecimal("15"),       // minimo
			    new BigDecimal("10"),       // maximo
			    new BigDecimal("10000"),    // volume
			    Calendar.getInstance()
			);
	}
	
}
