package beans;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<String> tamConvencionais;
	private List<String> tamTipo1;
	private List<String> tamTipo2;
	
	private String[] tamanhoNum1 = new String[] {"0","1","2","3","4","5","6","8","10","12","14","16"};

	private String[] tamanhoNum2 = new String[] {"28","30","32","34","36","38","40","42","44","46","48","50"};
	
	private String[] tamanhoConvencional = new String[] {"PP","P","M","G","GG","XGG"};
	
	@Inject
	private ProdutoService produtoService;

	@PostConstruct
	public void init() {
		
		if (produto == null) {
			produto = new Produto();
		}
	}
	public String saveProduto() {
		adicionarTamanhos();
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
	
	public List<String> getTamConvencionais() {
		return tamConvencionais;
	}
	public void setTamConvencionais(List<String> tamConvencionais) {
		this.tamConvencionais = tamConvencionais;
	}
	public List<String> getTamTipo1() {
		return tamTipo1;
	}
	public void setTamTipo1(List<String> tamTipo1) {
		this.tamTipo1 = tamTipo1;
	}
	public List<String> getTamTipo2() {
		return tamTipo2;
	}
	public void setTamTipo2(List<String> tamTipo2) {
		this.tamTipo2 = tamTipo2;
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
	public String[] getTamanhoNum1() {
		return tamanhoNum1;
	}
	public void setTamanhoNum1(String[] tamanhoNum1) {
		this.tamanhoNum1 = tamanhoNum1;
	}
	public String[] getTamanhoNum2() {
		return tamanhoNum2;
	}
	public void setTamanhoNum2(String[] tamanhoNum2) {
		this.tamanhoNum2 = tamanhoNum2;
	}
	public String[] getTamanhoConvencional() {
		return tamanhoConvencional;
	}
	public void setTamanhoConvencional(String[] tamanhoConvencional) {
		this.tamanhoConvencional = tamanhoConvencional;
	}
	public void adicionarTamanhos() {
		
		List<String> aux = new ArrayList<>(); 
		
		for(String s : tamConvencionais) {
			aux.add(s);
		}
		for(String s : tamTipo1) {
			aux.add(s);
		}
		for(String s : tamTipo2) {
			aux.add(s);
		}
		produto.setTamanhos(aux);
		
	}
}
