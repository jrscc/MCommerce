package beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Produto;
import services.ProdutoService;
import services.ServiceDacException;

@Named
@ViewScoped
public class CadastroProdutoMB extends AbstractBean{

	private static final long serialVersionUID = -5889267824712098769L;
	
	private Produto produto;
	
	@Inject
	private ProdutoService produtoService;

	@PostConstruct
	public void init() {
		
		if (produto == null) {
			produto = new Produto();
		}
	}
	public String saveProduto() {
		try {
			if (isEdicaoDeUsuario()) {
				produtoService.update(produto, false);
			} else {
				produtoService.save(produto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}

	public boolean isEdicaoDeUsuario() {
		return produto != null && produto.getId() != null;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ProdutoService getProdutoService() {
		return produtoService;
	}
	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
}
