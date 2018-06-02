package Model;

import java.util.Date;

import Resources.FormaPagamento;

public class Pagamento {
	
	private Date dataDoPagamento;
	private FormaPagamento formaPagamento;
	public Date getDataDoPagamento() {
		return dataDoPagamento;
	}
	public void setDataDoPagamento(Date dataDoPagamento) {
		this.dataDoPagamento = dataDoPagamento;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	

}
