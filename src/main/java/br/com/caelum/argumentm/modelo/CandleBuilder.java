package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public class CandleBuilder {
	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private Calendar data;
	
	private boolean aberturaSet = false;
    private boolean fechamentoSet = false;
    private boolean minimoSet = false;
    private boolean maximoSet = false;
    private boolean volumeSet = false;
    private boolean dataSet = false;
	
	public CandleBuilder comAbertura(BigDecimal abertura) {
		this.abertura = abertura;
		aberturaSet = true;
		return this;
	}

	public CandleBuilder comFechamento(BigDecimal fechamento) {
		this.fechamento = fechamento;
		fechamentoSet = true;
		return this;
	}

	public CandleBuilder comMinimo(BigDecimal minimo) {
		this.minimo = minimo;
		minimoSet = true;
		return this;
	}

	public CandleBuilder comMaximo(BigDecimal maximo) {
		this.maximo = maximo;
		maximoSet = true;
		return this;
	}

	public CandleBuilder comVolume(BigDecimal volume) {
		this.volume = volume;
		volumeSet = true;
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		this.data = data;
		dataSet = true;
		return this;
	}

	public Candlestick geraCandle() {
		if (!aberturaSet) throw new IllegalStateException("Abertura não foi setada");
        if (!fechamentoSet) throw new IllegalStateException("Fechamento não foi setado");
        if (!minimoSet) throw new IllegalStateException("Minimo não foi setado");
        if (!maximoSet) throw new IllegalStateException("Maximo não foi setado");
        if (!volumeSet) throw new IllegalStateException("Volume não foi setado");
        if (!dataSet) throw new IllegalStateException("Data não foi setada");
        
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
