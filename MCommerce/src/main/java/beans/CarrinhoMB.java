package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.ItemDePedido;
import model.Pedido;
import model.Produto;
import services.PedidoService;
import services.ServiceDacException;

@Named
@SessionScoped
public class CarrinhoMB  extends AbstractBean{

	private static final long serialVersionUID = 2734303039770040365L;
	
	private Pedido pedido;
	
	@Inject
	private PedidoService pedidoService;

	@PostConstruct
	public void init() {
		
		if (pedido == null) {
			pedido = new Pedido();
		}
	}
	public void adicionarAoCarrinho(Produto produto) {

		ItemDePedido item = new ItemDePedido();
		item.setProduto(produto);
//		item.setQuantidade(qtd);
//		item.setTamanho(tam);
		
		pedido.getItens().add(item);
		
		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' adicionado ao carrinho");
	}
	public String comprar(Produto p) {
		adicionarAoCarrinho(p);
		
		return redirectCarrinho();
	}
	
	
	public void removerDoCarrinho(ItemDePedido i){
		List<ItemDePedido> itens = pedido.getItens();
		int indice = 0;
		for(ItemDePedido item: itens) {
			if(i.getProduto().getId().equals(i.getProduto().getId())) {
				pedido.getItens().remove(indice);
			}
			indice++;
		}
	}
	
	public String savePedido() {
		try {
				pedidoService.save(pedido);
			
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pedido '" + pedido.getId() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

}
