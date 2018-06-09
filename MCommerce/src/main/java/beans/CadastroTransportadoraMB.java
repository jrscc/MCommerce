package beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Endereco;
import model.Transportadora;
import services.ServiceDacException;
import services.TransportadoraService;

@Named
@ViewScoped
public class CadastroTransportadoraMB extends AbstractBean {

	private static final long serialVersionUID = 8413567899047300882L;

	private Transportadora transportadora = null;
	
	@Inject
	private TransportadoraService transportadoraService;

	@PostConstruct
	public void init() {
		
		if (transportadora == null) {
			transportadora = new Transportadora();
		}
		if (transportadora.getEndereco() == null) {
			transportadora.setEndereco(new Endereco());
		}
	}
	
	public String saveTransportadora() {
		try {
			if (isEdicaoDeUsuario()) {
				transportadoraService.update(transportadora);
			} else {
				transportadoraService.save(transportadora);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Transportadora '" + transportadora.getNomeFantasia() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}
	
	public boolean isEdicaoDeUsuario() {
		return transportadora != null && transportadora.getId() != null;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public TransportadoraService getTransportadoraService() {
		return transportadoraService;
	}

	public void setTransportadoraService(TransportadoraService transportadoraService) {
		this.transportadoraService = transportadoraService;
	}
	
}
