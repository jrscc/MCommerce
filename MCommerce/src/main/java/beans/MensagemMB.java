package beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import services.ClienteService;
import services.ServiceDacException;

@Named
@ViewScoped
public class MensagemMB extends AbstractBean{

	private static final long serialVersionUID = 2975193071951104420L;
	
	private List<Cliente> clientes;
	
	private Cliente cliente = new Cliente();
	
	private String titulo;
	
	private String mensagem;
	
	@Inject
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
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

	public String saveCliente() {
		String mensagemEnviada = titulo + " - " + mensagem;
		cliente.getMensagens().add(mensagemEnviada);
		System.out.println("=--------Click");
		try {
				clienteService.update(cliente, false);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + cliente.getNome() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}
	
	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
