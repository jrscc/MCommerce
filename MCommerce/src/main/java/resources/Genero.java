package resources;

public enum Genero {
	
	MASCULINO("Masculino"), 
	FEMININO("Feminino"),
	UNISSEX("Unissex");
	
	private String nome;
	
	private Genero(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}	

}
