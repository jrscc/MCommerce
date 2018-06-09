package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import filters.TransportadoraFilter;
import model.Transportadora;
import services.ServiceDacException;
import services.TransportadoraService;

@Named
@ViewScoped
public class gerenciarTransportadorasMB extends AbstractBean {

	private static final long serialVersionUID = 1170796164463003172L;

	private List<Transportadora> transportadoras;

	private TransportadoraFilter transportadoraFilter;

	@Inject
	private TransportadoraService transportadoraService;
	
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			transportadoras = transportadoraService.findBy(null);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.transportadoraFilter = new TransportadoraFilter();
		return null;
	}

	public String delete(Transportadora transportadora) {
		try {
			transportadoraService.delete(transportadora);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Transportadora '" + transportadora.getNomeFantasia() + "' deleted");
		
		return "index?faces-redirect=true";
	}

	public List<Transportadora> getTransportadoras() {
		return transportadoras;
	}

	public void setTransportadoras(List<Transportadora> transportadoras) {
		this.transportadoras = transportadoras;
	}

	public TransportadoraFilter getTransportadoraFilter() {
		return transportadoraFilter;
	}

	public void setTransportadoraFilter(TransportadoraFilter transportadoraFilter) {
		this.transportadoraFilter = transportadoraFilter;
	}

	public TransportadoraService getTransportadoraService() {
		return transportadoraService;
	}

	public void setTransportadoraService(TransportadoraService transportadoraService) {
		this.transportadoraService = transportadoraService;
	}
}
