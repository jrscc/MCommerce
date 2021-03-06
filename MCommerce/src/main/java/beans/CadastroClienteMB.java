package beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cliente;
import model.Endereco;
import model.Group;
import services.ClienteService;
import services.ServiceDacException;

@Named
@ViewScoped
public class CadastroClienteMB extends AbstractBean{

	
	private static final long serialVersionUID = -5576985608803639014L;

	private Cliente cliente = null;
	
	@Inject
	private ClienteService clienteService;
	
	private String confirPassword;
	
	
	public String getConfirPassword() {
		return confirPassword;
	}

	public void setConfirPassword(String confirPassword) {
		this.confirPassword = confirPassword;
	}

	@PostConstruct
	public void init() {
		
		if (cliente == null) {
			cliente = new Cliente();
		}
		if (cliente.getEndereco() == null) {
			cliente.setEndereco(new Endereco());
		}
	}
	
	public String saveCliente() {
		try {
			if (isEdicaoDeUsuario()) {
				clienteService.update(cliente, false);
			} else {
				cliente.setGroup(Group.CLIENTE);
				clienteService.save(cliente);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + cliente.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}
	
	public void checarDisponibilidadeLogin() {
		try {
			clienteService.validarLogin(cliente);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", cliente.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public boolean isEdicaoDeUsuario() {
		return cliente != null && cliente.getId() != null;
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
