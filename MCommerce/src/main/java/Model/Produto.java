package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import Resources.FaixaEtaria;
import Resources.Genero;
import Resources.Tamanho;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String nome;
	private String descricao;
	private double precoCusto;
	private double precoVenda;
	@Enumerated
	private Genero genero;
	@Enumerated
	private FaixaEtaria faixaEtaria;
//	@Enumerated
//	private List<Tamanho> tamanhos;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public FaixaEtaria getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(FaixaEtaria faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
//	public List<Tamanho> getTamanhos() {
//		return tamanhos;
//	}
//	public void setTamanhos(List<Tamanho> tamanhos) {
//		this.tamanhos = tamanhos;
//	}
	
}
