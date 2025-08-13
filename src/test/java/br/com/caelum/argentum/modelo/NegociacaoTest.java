package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

import org.junit.Assert;


public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(BigDecimal.valueOf(10), 5, c);
		
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		
		Negociacao n = new Negociacao(BigDecimal.valueOf(10), 5, null);
		
	}
	
	 @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoPrecoForNulo() {
	        new Negociacao(null, 10, Calendar.getInstance());
	    }

	    @Test(expected = NullPointerException.class)
	    public void deveLancarExcecaoQuandoDataForNula() {
	        new Negociacao(new BigDecimal("10.0"), 10, null);
	    }

	    @Test
	    public void deveCriarNegociacaoComParametrosValidos() {
	        BigDecimal preco = new BigDecimal("10.0");
	        int quantidade = 10;
	        Calendar data = Calendar.getInstance();

	        Negociacao negociacao = new Negociacao(preco, quantidade, data);

	        assertEquals(preco, negociacao.getPreco());
	        assertEquals(quantidade, negociacao.getQuantidade());
	        assertEquals(data, negociacao.getData());
	    }
	
		
}
