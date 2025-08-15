package br.com.caelum.argentum.reader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;

public class GeradorAleatorioDeXML {
	public static void main(String[] args) throws IOException {
		//Gera uma data com o calendar no momento da instancia
		Calendar data = Calendar.getInstance();
		//Cria um objeto random com uma semente fixa de 123 gerando uma sequencia de numeros aleatorios 
		//que nao irao mudar caso o codigo seja compilado novamente
		Random random = new Random(123);

		List<Negociacao> negociacoes = new ArrayList<Negociacao>();

		double valor = 40;
		int quantidade = 1000;

		//Cria 30 dias de negociaçoes
		for (int dias = 0; dias < 30; dias++) {
			//Quantidade de negociações que podem acontecer no dia (1 a 19)
			int quantidadeNegociacoesDoDia = random.nextInt(20);

			//itera sobre as negociações do dia
			for (int negociacao = 0; negociacao < quantidadeNegociacoesDoDia; negociacao++) {
				//faz o preço da negociação variar só alguns centavos a cada nova negociação.
				valor += (random.nextInt(200) - 100) / 100.0;
				//Evita que o valor seja menor que 5.0
				if (valor < 5.0) {
					valor = 5.0;
				}
				
				//cria variações na quantidade de negociações e no volume
				quantidade += 1000 - random.nextInt(500);
				
				Negociacao n = new Negociacao(null, quantidadeNegociacoesDoDia, data);
				negociacoes.add(n);
			}
			//Aumenta o dia das negociacoes
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}

		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		stream.setMode(XStream.NO_REFERENCES);
		PrintStream out = new PrintStream(new PrintStream("negociacao.xml"));
		out.println(stream.toXML(negociacoes));

	}
}
