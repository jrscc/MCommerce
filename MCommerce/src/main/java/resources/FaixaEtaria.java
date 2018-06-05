package resources;

public enum FaixaEtaria {

	INFANTIL("Infantil"), 
	JUVENIL("Juvenil"), 
	ADULTO("Adulto");
	
	private String nome;
	
	private FaixaEtaria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}	
}
