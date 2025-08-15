package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import br.com.caelum.argentum.utils.Util;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	 public Negociacao(BigDecimal preco, int quantidade, Calendar data) {
	        this.preco = Objects.requireNonNull(preco, "Preço não pode ser nulo");
	        this.quantidade = Objects.requireNonNull(quantidade, "Quantidade não pode ser nula");
	        this.data = Util.verificaDataNula(data);
	    }

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	// Retorna uma copia nova do objeto Calendar
	// Evitando multabilidade fora da classe
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	// Volume de dinheiro transferido por negociacao
	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	public boolean isMesmoDia(Calendar outraData) {
		return data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
						&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}

}
