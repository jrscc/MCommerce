package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Pedido;
import services.PedidoService;
import services.ServiceDacException;

@Named
@SessionScoped
public class CarrinhoMB  extends AbstractBean{

	private static final long serialVersionUID = 2734303039770040365L;
	
	private Pedido pedido = null;
	
	@Inject
	private PedidoService pedidoService;

	@PostConstruct
	public void init() {
		
		if (pedido == null) {
			pedido = new Pedido();
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
