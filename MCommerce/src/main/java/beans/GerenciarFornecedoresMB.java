package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import filters.FornecedorFilter;
import model.Fornecedor;
import services.FornecedorService;
import services.ServiceDacException;

@Named
@ViewScoped
public class GerenciarFornecedoresMB extends AbstractBean{

	private static final long serialVersionUID = -8692883126942160886L;

	private List<Fornecedor> fornecedores;

	private FornecedorFilter fornecedorFilter;

	@Inject
	private FornecedorService fornecedorService;
	
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			fornecedores = fornecedorService.findBy(null);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.fornecedorFilter = new FornecedorFilter();
		return null;
	}

	public String delete(Fornecedor fornecedor) {
		
		try {
			fornecedorService.delete(fornecedor);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Fornecedor '" + fornecedor.getNome() + "' deleted");
		
		return "index?faces-redirect=true";
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public FornecedorFilter getFornecedorFilter() {
		return fornecedorFilter;
	}

	public void setFornecedorFilter(FornecedorFilter fornecedorFilter) {
		this.fornecedorFilter = fornecedorFilter;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

}
