package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.PagamentoFilter;
import model.Pagamento;
import persistence.PagamentoDAO;
import persistence.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class PagamentoService implements Serializable{

	private static final long serialVersionUID = -7050345715519206209L;
	
	@Inject
	private PagamentoDAO pagamentoDAO;
	
	@TransacionalCdi
	public void save(Pagamento pagamento) throws ServiceDacException {
		try {
			pagamentoDAO.save(pagamento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Pagamento pagamento) throws ServiceDacException {
		
		try {
			pagamentoDAO.update(pagamento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Pagamento pagamento) throws ServiceDacException {
		try {
			pagamentoDAO.delete(pagamento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Pagamento getByID(int pagamentoId) throws ServiceDacException {
		try {
			return pagamentoDAO.getByID(pagamentoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pagamento> getAll() throws ServiceDacException {
		try {
			return pagamentoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pagamento> findBy(PagamentoFilter filter) throws ServiceDacException {
		try {
			return pagamentoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
