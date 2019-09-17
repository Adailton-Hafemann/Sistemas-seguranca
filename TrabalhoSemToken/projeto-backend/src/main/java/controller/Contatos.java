package controller;

public class Contatos {
	
	private String nome;
	private String telefone;
	
	
	public Contatos(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Contatos() {
		//
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	

}
