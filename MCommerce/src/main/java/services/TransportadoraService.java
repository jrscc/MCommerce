package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.TransportadoraFilter;
import model.Transportadora;
import persistence.PersistenciaDacException;
import persistence.TransportadoraDAO;
import util.TransacionalCdi;

@ApplicationScoped
public class TransportadoraService implements Serializable{

	private static final long serialVersionUID = -3457932937664473225L;

	@Inject
	private TransportadoraDAO transportadoraDAO;
	
	@TransacionalCdi
	public void save(Transportadora transportadora) throws ServiceDacException {
		try {
			transportadoraDAO.save(transportadora);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Transportadora transportadora) throws ServiceDacException {
		
		try {
			transportadoraDAO.update(transportadora);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Transportadora transportadora) throws ServiceDacException {
		try {
			transportadoraDAO.delete(transportadora);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Transportadora getByID(int transportadoraId) throws ServiceDacException {
		try {
			return transportadoraDAO.getByID(transportadoraId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Transportadora> getAll() throws ServiceDacException {
		try {
			return transportadoraDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Transportadora> findBy(TransportadoraFilter filter) throws ServiceDacException {
		try {
			return transportadoraDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public TransportadoraDAO getTransportadoraDAO() {
		return transportadoraDAO;
	}

	public void setTransportadoraDAO(TransportadoraDAO transportadoraDAO) {
		this.transportadoraDAO = transportadoraDAO;
	}

	
}
