package dto;

import java.util.ArrayList;
import java.util.List;

import controller.Contatos;

public class Pessoas {
	
	private List<String> nomes;
	
	public static List<Contatos> pessoasStaticas() {
		List<Contatos> no = new ArrayList<>();
		no.add(new Contatos("Fulano 1", "(99)9999-9999"));
		no.add(new Contatos("Fulano 2", "(99)9999-9999"));
		no.add(new Contatos("Fulano 3", "(99)9999-9999"));
		no.add(new Contatos("Fulano 4", "(99)9999-9999"));
		no.add(new Contatos("Fulano 5", "(99)9999-9999"));
		no.add(new Contatos("Fulano 6", "(99)9999-9999"));
		return no;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}
	
	

}
