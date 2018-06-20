package beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import filters.ClienteFilter;
import model.Cliente;
import model.Group;
import model.Produto;
import resources.FaixaEtaria;
import resources.Genero;
import resources.UF;
import services.ClienteService;
import services.ServiceDacException;


public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7887186144461468149L;
	
	@Inject
	private ClienteService clienteService;

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe);

	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe);
	}

	private void reportarMensagem(boolean isErro, String detalhe) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}
		
		return userPrincipal.getName();
	}
	
	public Cliente getUsuarioLogado() {
		ClienteFilter filter = new ClienteFilter();
		filter.setLogin(getUserLogin());
		List<Cliente> clientes = null;
		try {
			clientes = clienteService.findBy(filter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o usuário logado!");
		}

		if (!clientes.isEmpty()) {
			return clientes.get(0);
		}
		
		return null;
	}
	public String redirectCarrinho() {
		return EnderecoPaginas.PAGINA_CARRINHO;
	}

	public UF[] getUf() {
		return UF.values();
	}
	
	public Genero[] getGenero() {
		return Genero.values();
	}
	
	public FaixaEtaria[] getFaixaEtaria() {
		return FaixaEtaria.values();
	}
	public Group[] getGroups() {
		return Group.values();
	}
}
