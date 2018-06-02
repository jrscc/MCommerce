package Model;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	private Date dataDoPedido;
	private List<ItemDePedido> itens;
	private double valorTotal;
	private Pagamento pagamento;
	
	public List<ItemDePedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}
	public double getValorTotal() {
		return valorTotal;
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
	
	
	

}
