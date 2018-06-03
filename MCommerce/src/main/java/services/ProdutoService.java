package services;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import filters.ProdutoFilter;
import model.Produto;
import persistence.PersistenciaDacException;
import persistence.ProdutoDAO;
import util.TransacionalCdi;

@ApplicationScoped
public class ProdutoService implements Serializable{

	private static final long serialVersionUID = -889263775140674322L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@TransacionalCdi
	public void save(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.save(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Produto produto, boolean passwordChanged) throws ServiceDacException {
		
		try {
			produtoDAO.update(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.delete(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Produto getByID(int produtoId) throws ServiceDacException {
		try {
			return produtoDAO.getByID(produtoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Produto> getAll() throws ServiceDacException {
		try {
			return produtoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException {
		try {
			return produtoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
