package services;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.FornecedorFilter;
import model.Fornecedor;
import persistence.FornecedorDAO;
import persistence.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class FornecedorService implements Serializable{

	private static final long serialVersionUID = -8442205398031128290L;
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@TransacionalCdi
	public void save(Fornecedor fornecedor) throws ServiceDacException {
		try {
			fornecedorDAO.save(fornecedor);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Fornecedor fornecedor, boolean passwordChanged) throws ServiceDacException {
		
		try {
			validarLogin(fornecedor);
			fornecedorDAO.update(fornecedor);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Fornecedor fornecedor) throws ServiceDacException {
		try {
			fornecedorDAO.delete(fornecedor);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Fornecedor getByID(int fornecedorId) throws ServiceDacException {
		try {
			return fornecedorDAO.getByID(fornecedorId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Fornecedor> getAll() throws ServiceDacException {
		try {
			return fornecedorDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Fornecedor> findBy(FornecedorFilter filter) throws ServiceDacException {
		try {
			return fornecedorDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void validarLogin(Fornecedor fornecedor) throws ServiceDacException {
		boolean jahExiste = fornecedorDAO.existeUsuarioComLogin(fornecedor);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + fornecedor.getLogin()); 
		}
	}
}


