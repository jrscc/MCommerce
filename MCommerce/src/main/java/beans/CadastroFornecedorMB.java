package beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Endereco;
import model.Fornecedor;
import services.FornecedorService;
import services.ServiceDacException;

@Named
@ViewScoped
public class CadastroFornecedorMB extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1141660357131033056L;
	
	private Fornecedor fornecedor = null;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@PostConstruct
	public void init() {
		
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		if (fornecedor.getEndereco() == null) {
			fornecedor.setEndereco(new Endereco());
		}
	}
	
	public String saveFornecedor() {
		try {
			if (isEdicaoDeUsuario()) {
				fornecedorService.update(fornecedor, false);
			} else {
				fornecedorService.save(fornecedor);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Fornecedor '" + fornecedor.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}
	
	public void checarDisponibilidadeLogin() {
		try {
			fornecedorService.validarLogin(fornecedor);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", fornecedor.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	public boolean isEdicaoDeUsuario() {
		return fornecedor != null && fornecedor.getId() != null;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}
	
}
