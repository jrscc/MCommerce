package Beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Services.ClienteService;

@Named
@ViewScoped
public class IndexMB {

	@Inject
	private ClienteService clienteService;

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	
}
