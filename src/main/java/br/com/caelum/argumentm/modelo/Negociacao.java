package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.caelumn.argumentm.utils.Util;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(BigDecimal preco, int quantidade, Calendar data) {
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = Util.verificaDataNula(data);
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	//Retorna uma copia nova do objeto Calendar
	//Evitando multabilidade fora da classe
	public Calendar getData() {
		return (Calendar)this.data.clone();
	}
	
	//Volume de dinheiro transferido por negociacao
	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	
}
