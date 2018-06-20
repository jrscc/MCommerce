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
public class IndexMB extends AbstractBean{


	private static final long serialVersionUID = 3821387767052576385L;
	
	private List<Produto> produtos;
	
	private Produto selecionado;
	
	private List<String> images;
	
	@Inject
	private ProdutoService produtoService;
    
    @PostConstruct
    public void init() {
    	filtrar();
        images = new ArrayList<String>();
            images.add("brasil.jpg");
            images.add("palmeiras.jpg");
            images.add("shimano.jpg");
            images.add("black.jpg");
            images.add("50off.png");
            images.add("real.jpg");
            images.add("barca.jpg");
            images.add("bermudaNike.jpg");
            
        
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
    
    public List<String> getImages() {
        return images;
    }

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Produto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Produto selecionado) {
		this.selecionado = selecionado;
	}
	
}
