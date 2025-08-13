package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXML {
	
	public List<Negociacao> carrega(InputStream inputStream){
		XStream stream = new XStream(new DomDriver());
		//Cria uma liberacao na classe para que possa ser usada a desserializacao
		stream.allowTypes(new Class[] { br.com.caelum.argentum.modelo.Negociacao.class });
		stream.alias("negociacao", Negociacao.class);
		return (List<Negociacao>) stream.fromXML(inputStream);
		
	}
	
}
