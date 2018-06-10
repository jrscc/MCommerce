package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemDePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int quantidade;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Produto produto;
	private double subTotal;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> tamanhosSelecionados;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getTamanhos() {
		return tamanhosSelecionados;
	}
	public void setTamanhos(List<String> tamanhosSelecionados) {
		this.tamanhosSelecionados = tamanhosSelecionados;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + quantidade;
		long temp;
		temp = Double.doubleToLongBits(subTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tamanhosSelecionados == null) ? 0 : tamanhosSelecionados.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDePedido other = (ItemDePedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(subTotal) != Double.doubleToLongBits(other.subTotal))
			return false;
		if (tamanhosSelecionados == null) {
			if (other.tamanhosSelecionados != null)
				return false;
		} else if (!tamanhosSelecionados.equals(other.tamanhosSelecionados))
			return false;
		return true;
	}

}
