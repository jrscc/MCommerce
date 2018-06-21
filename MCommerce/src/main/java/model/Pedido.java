package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date dataDoPedido;
	@Temporal(TemporalType.DATE)
	private Date dataDeEntrega;
	private double valorTotal;
	@OneToOne(cascade = {CascadeType.ALL})
	private Pagamento pagamento;
	private Boolean entregue;
	@OneToMany
	private List<ItemDePedido> itens = new ArrayList<ItemDePedido>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getEntregue() {
		return entregue;
	}
	public void setEntregue(Boolean entregue) {
		this.entregue = entregue;
	}
	public List<ItemDePedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}
	public double getValorTotal() {
		double total = 0;
		for(ItemDePedido item : itens) {
			total += item.getSubTotal();
		}
		
		return total;
	}
	public Date getDataDeEntrega() {
		return dataDeEntrega;
	}
	public void setDataDeEntrega(Date dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataDoPedido() {
		return dataDoPedido;
	}
	public void setDataDoPedido(Date dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entregue == null) ? 0 : entregue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Pedido other = (Pedido) obj;
		if (entregue == null) {
			if (other.entregue != null)
				return false;
		} else if (!entregue.equals(other.entregue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}
	
}
