package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import br.com.caelum.argentum.modelo.CandleBuilder;
import br.com.caelum.argentum.modelo.Candlestick;

public class CandleBuilderTest {

	@Test
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		Calendar data = Calendar.getInstance();

		BigDecimal abertura = new BigDecimal("10.5");
		BigDecimal fechamento = new BigDecimal("12.0");
		BigDecimal minimo = new BigDecimal("9.5");
		BigDecimal maximo = new BigDecimal("13.0");
		BigDecimal volume = new BigDecimal("1000");

		Candlestick candle = new CandleBuilder().comAbertura(abertura).comFechamento(fechamento).comMinimo(minimo)
				.comMaximo(maximo).comVolume(volume).comData(data).geraCandle();

		// Verificações básicas de não nulidade
		assertNotNull("Abertura não pode ser nula", candle.getAbertura());
		assertNotNull("Fechamento não pode ser nulo", candle.getFechamento());
		assertNotNull("Minimo não pode ser nulo", candle.getMinimo());
		assertNotNull("Maximo não pode ser nulo", candle.getMaximo());
		assertNotNull("Volume não pode ser nulo", candle.getVolume());
		assertNotNull("Data não pode ser nula", candle.getData());

		// Verificações de consistência (mínimo menor ou igual ao máximo)
		assertTrue("Mínimo deve ser menor ou igual ao máximo", candle.getMinimo().compareTo(candle.getMaximo()) <= 0);

		// Verificar se abertura está entre minimo e maximo
		assertTrue("Abertura deve estar entre minimo e maximo", candle.getAbertura().compareTo(candle.getMinimo()) >= 0
				&& candle.getAbertura().compareTo(candle.getMaximo()) <= 0);

		// Verificar se fechamento está entre minimo e maximo
		assertTrue("Fechamento deve estar entre minimo e maximo",
				candle.getFechamento().compareTo(candle.getMinimo()) >= 0
						&& candle.getFechamento().compareTo(candle.getMaximo()) <= 0);
	}
}
