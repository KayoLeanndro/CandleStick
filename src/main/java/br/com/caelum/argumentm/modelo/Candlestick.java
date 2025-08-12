package br.com.caelum.argumentm.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public final class Candlestick {

	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;

	public Candlestick(BigDecimal abertura, BigDecimal fechamento, BigDecimal minimo, BigDecimal maximo,
	        BigDecimal volume, Calendar data) {
	    
	    this.abertura = Objects.requireNonNull(abertura, "Abertura não pode ser nula");
	    this.fechamento = Objects.requireNonNull(fechamento, "Fechamento não pode ser nulo");
	    if (minimo.compareTo(maximo) > 0) {
	        throw new IllegalArgumentException("Preço minimo não pode ser maior que preço maximo");
	    }
	    this.minimo = Objects.requireNonNull(minimo, "Minimo não pode ser nulo");
	    this.maximo = Objects.requireNonNull(maximo, "Maximo não pode ser nulo");
	    this.volume = Objects.requireNonNull(volume, "Volume não pode ser nulo");
	    this.data = Objects.requireNonNull(data, "Data não pode ser nula");
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
		return abertura.compareTo(fechamento) <= 0;
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

		return "Candlestick [abertura = " + abertura + ", fechamento = " + fechamento + ", minimo = " + minimo
				+ ", maximo= " + maximo + ", volume = " + volume + ", data = " + data + "]";
	}
	
}
