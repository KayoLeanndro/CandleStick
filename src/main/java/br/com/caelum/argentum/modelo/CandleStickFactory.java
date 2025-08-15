package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes){
		List<Candlestick> candles = new ArrayList<Candlestick>();
		List<Negociacao> negociacoesDodia = new ArrayList<Negociacao>();
		
		//Caso a lista de negociacoes esteja vazia, retorna a lista de candles vazia
		if(negociacoes.isEmpty()) {
			return candles;
		}
		
		Calendar dataAtual = negociacoes.get(0).getData();
		
		for(Negociacao negociacao : negociacoes) {
			
			if(negociacao.getData().before(dataAtual)) {
				throw new IllegalArgumentException("Negociações em ordem errada");
			}
			
			//Se a negociação não for do mesmo dia(dia atual)
			if(!negociacao.isMesmoDia(dataAtual)) {
				//Adiciona as negocicoes que eram do mesmo dia no candle e comeca o processo da criacao do novo
				Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDodia);
				candles.add(candleDoDia);
				
				//zera as negociacoes do dia para que seja criado outro candle
				negociacoesDodia = new ArrayList<>();
				dataAtual = negociacao.getData();
				
			}
			//Adiciona a negociacao do diaAtual
			negociacoesDodia.add(negociacao);
		}
		
		//Cria o candle quando verifica que o dia nao mudou de uma negociacao para outra
		Candlestick candleFinal = constroiCandleParaData(dataAtual, negociacoesDodia);
		candles.add(candleFinal);
		
		return candles;
	} 
	
	
	
}
