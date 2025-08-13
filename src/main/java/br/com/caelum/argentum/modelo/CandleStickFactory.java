package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class CandleStickFactory {

	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {

		if (negociacoes == null || negociacoes.isEmpty()) {
			return new CandleBuilder().comAbertura(BigDecimal.ZERO).comFechamento(BigDecimal.ZERO)
					.comMinimo(BigDecimal.ZERO).comMaximo(BigDecimal.ZERO).comVolume(BigDecimal.ZERO).comData(data)
					.geraCandle();
		}

		BigDecimal maximo = negociacoes.get(0).getPreco();
		BigDecimal minimo = negociacoes.get(0).getPreco();
		BigDecimal volume = BigDecimal.ZERO;

		for (Negociacao negociacao : negociacoes) {
			volume = volume.add(negociacao.getVolume());

			if (negociacao.getPreco().compareTo(maximo) > 0) {
				maximo = negociacao.getPreco();
			} else if (negociacao.getPreco().compareTo(minimo) < 0) {
				minimo = negociacao.getPreco();
			}

		}

		BigDecimal abertura = negociacoes.get(0).getPreco();
		BigDecimal fechamento = negociacoes.get(negociacoes.size() - 1).getPreco();

		return new CandleBuilder().comAbertura(abertura).comFechamento(fechamento).comMinimo(minimo).comMaximo(maximo)
				.comVolume(volume).comData(data).geraCandle();

	}

}
