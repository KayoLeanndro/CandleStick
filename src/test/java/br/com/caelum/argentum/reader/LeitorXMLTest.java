package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmInstancia() {
		String xmlDeTeste = 
				"<list>" +
					"<negociacao>" +
						"<preco>43.5</preco>" +
						"<quantidade>1000</quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
				"</list>";
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1, negociacoes.size());
		Negociacao negociacao = negociacoes.get(0);
		Assert.assertEquals(new BigDecimal("43.5"), negociacao.getPreco());
		Assert.assertEquals(1000, negociacao.getQuantidade());
	}
	
	@Test(expected = NullPointerException.class) 
	public void carregaXmlSemQuantidadeDeveLancarExcecao() {
		String xmlDeTeste = 
				"<list>" +
					"<negociacao>" +
						"<preco>43.5</preco>" +
						"<quantidade></quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
				"</list>";
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
	}
	
	@Test(expected = NullPointerException.class) 
	public void carregaXmlSemPrecoDeveLancarExcecao() {
		String xmlDeTeste = 
				"<list>" +
					"<negociacao>" +
						"<preco></preco>" +
						"<quantidade>1000</quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
				"</list>";
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
	}
	
	@Test
	public void carregaXmlComTresNegociacoes() {
		String xmlDeTeste = 
				"<list>" +
					"<negociacao>" +
						"<preco>43.5</preco>" +
						"<quantidade>1000</quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
					"<negociacao>" +
						"<preco>43.5</preco>" +
						"<quantidade>1000</quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
					"<negociacao>" +
						"<preco>43.5</preco>" +
						"<quantidade>1000</quantidade>" +
						"<data><time>1322233344455</time>"+
						"</data>" +
					"</negociacao>" +
				"</list>";
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		Assert.assertEquals(3, negociacoes.size());
		
	}
	
}
