package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import br.com.caelumn.argumentm.utils.Util;

public final class Candlestick {
	
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;
	
	public Candlestick(BigDecimal abertura, BigDecimal fechamento, BigDecimal minimo, BigDecimal maximo, BigDecimal volume, Calendar data) {
		super();
		this.abertura = abertura;
		this.fechamento = fechamento;
		if(minimo.compareTo(maximo) > 0) {
			throw new IllegalArgumentException("Preço minimo não pode ser maior que preço maximo");
		}
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = Util.verificaDataNula(data);
	}
	
	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	public boolean isAlta() {
	    if (abertura == null || fechamento == null) {
	        return false;
	    }
	    return abertura.compareTo(fechamento) < 0;
	}
	
	public boolean isBaixa() {
	    if (abertura == null || fechamento == null) {
	        return false;
	    }
	    return abertura.compareTo(fechamento) > 0;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String data = simpleDateFormat.format(this.data.getTime());
		
		return "Candlestick [abertura = " + abertura + ", fechamento = " + fechamento + ", minimo = " + minimo + ", maximo= "
				+ maximo + ", volume = " + volume + ", data = " + data + "]";
	}
	
	
	
}
