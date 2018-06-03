package resources;

public enum Sexo {

	Masculino("Masculino"), Feminino("Feminino"),;
	
	private String nome;
	
	private Sexo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}	
	
}
