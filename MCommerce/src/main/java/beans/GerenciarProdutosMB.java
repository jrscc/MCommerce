package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import filters.ProdutoFilter;
import model.Produto;
import services.ProdutoService;
import services.ServiceDacException;

@Named
@ViewScoped
public class GerenciarProdutosMB extends AbstractBean{

	private static final long serialVersionUID = 2717018309819066400L;

	private List<Produto> produtos;

	private ProdutoFilter produtoFilter;

	@Inject
	private ProdutoService produtoService;
	
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			produtos = produtoService.findBy(null);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.produtoFilter = new ProdutoFilter();
		return null;
	}

	public String delete(Produto produto) {
		try {
			produtoService.delete(produto);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' deleted");
		
		return "index?faces-redirect=true";
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	
}
