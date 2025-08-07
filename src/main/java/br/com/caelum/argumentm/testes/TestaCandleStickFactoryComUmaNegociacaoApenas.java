package br.com.caelum.argumentm.testes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argumentm.modelo.CandleStickFactory;
import br.com.caelum.argumentm.modelo.Candlestick;
import br.com.caelum.argumentm.modelo.Negociacao;

public class TestaCandleStickFactoryComUmaNegociacaoApenas {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1);
		
		CandleStickFactory candleStickFactory = new CandleStickFactory();
		
		Candlestick candle = candleStickFactory.constroiCandleParaData(hoje, negociacoes);
		
		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());
		System.out.println(candle.toString());
	}
}
