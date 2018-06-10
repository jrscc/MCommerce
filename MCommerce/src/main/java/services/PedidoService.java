package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.PedidoFilter;
import model.Pedido;
import persistence.PedidoDAO;
import persistence.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class PedidoService implements Serializable{

	private static final long serialVersionUID = -3153125198723605879L;
	
	@Inject
	private PedidoDAO pedidoDAO;
	
	@TransacionalCdi
	public void save(Pedido pedido) throws ServiceDacException {
		try {
			pedidoDAO.save(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Pedido pedido, boolean passwordChanged) throws ServiceDacException {
		
		try {
			pedidoDAO.update(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Pedido pedido) throws ServiceDacException {
		try {
			pedidoDAO.delete(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Pedido getByID(int pedidoId) throws ServiceDacException {
		try {
			return pedidoDAO.getByID(pedidoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> getAll() throws ServiceDacException {
		try {
			return pedidoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException {
		try {
			return pedidoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
