package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

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
	
	@Test(expected = NullPointerException.class)
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
	    
	    @Test
	    public void mesmoMilissegundosEhDoMesmoDia() {
	    	Calendar agora = Calendar.getInstance();
	    	Calendar mesmoMomento = (Calendar) agora.clone();
	    	
	    	Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, agora);
	    	Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	    }
	    
	    @Test
	    public void comHorariosDiferentesEhnoMesmoDia() {
	    	//Usando GregorianCalendar(ano,mes, dia, hora, minuto)
	    	Calendar manha = new GregorianCalendar(2011,10,20,8,30);
	    	Calendar tarde = new GregorianCalendar(2011,10,20,15,30);
	    	
	    	Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, manha);
	    	Assert.assertTrue(negociacao.isMesmoDia(tarde));
	    }
	    
	    @Test
	    public void mesmoDiaMasMesesDiferentesNaoSomadosDoMesmoDia() {
	    	//Usando GregorianCalendar(ano,mes, dia)
	    	Calendar junho = new GregorianCalendar(2010,7,20);
	    	Calendar setembro = new GregorianCalendar(2011,7,20);
	    	
	    	Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, junho);
	    	Assert.assertFalse(negociacao.isMesmoDia(setembro));
	    }
	    
	    @Test
	    public void mesmoDiamesmoMesesAnoDiferentesNaoSomadosDoMesmoDia() {
	    	//Usando GregorianCalendar(ano,mes, dia)
	    	Calendar doismileDez = new GregorianCalendar(2010,7,20);
	    	Calendar doismileOnze = new GregorianCalendar(2011,7,20);
	    	
	    	Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, doismileDez);
	    	Assert.assertFalse(negociacao.isMesmoDia(doismileOnze));
	    }
	
		
}
