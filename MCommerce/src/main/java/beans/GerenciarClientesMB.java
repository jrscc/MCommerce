package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import filters.ClienteFilter;
import model.Cliente;
import services.ClienteService;
import services.ServiceDacException;

@Named
@ViewScoped
public class GerenciarClientesMB extends AbstractBean {

	private static final long serialVersionUID = 8430517551643680513L;

	private List<Cliente> clientes;

	private ClienteFilter clienteFilter;

	@Inject
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			clientes = clienteService.findBy(null);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.clienteFilter = new ClienteFilter();
		return null;
	}

	public String delete(Cliente cliente) {
		
		try {
			clienteService.delete(cliente);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Cliente '" + cliente.getNome() + "' deleted");
		
		return "index?faces-redirect=true";
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

}
