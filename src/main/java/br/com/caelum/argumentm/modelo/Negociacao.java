package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(BigDecimal preco, int quantidade, Calendar data) {
		super();
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return data;
	}
	
	//Volume de dinheiro transferido por negociacao
	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	
}
