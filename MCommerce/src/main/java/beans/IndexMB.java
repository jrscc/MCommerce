package beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import services.ClienteService;

@Named
@ViewScoped
public class IndexMB extends AbstractBean{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3821387767052576385L;
	
	@Inject
	private ClienteService clienteService;

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	
}
