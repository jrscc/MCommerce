package beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import services.ClienteService;

@Named
@ViewScoped
public class CaixaDeEntradaMB extends AbstractBean{

	private static final long serialVersionUID = -5319520520420803824L;
	
	private Cliente cliente;
	
	@Inject
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
		cliente = getUsuarioLogado();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	

	
}
