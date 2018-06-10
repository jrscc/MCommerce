package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.ItemDePedidoFilter;
import model.ItemDePedido;
import persistence.ItemDePedidoDAO;
import persistence.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class ItemDePedidoService implements Serializable{

	private static final long serialVersionUID = 6429500273982707448L;
	
	@Inject
	private ItemDePedidoDAO itemDePedidoDAO;
	
	@TransacionalCdi
	public void save(ItemDePedido itemDePedido) throws ServiceDacException {
		try {
			itemDePedidoDAO.save(itemDePedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(ItemDePedido itemDePedido) throws ServiceDacException {
		
		try {
			itemDePedidoDAO.update(itemDePedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(ItemDePedido itemDePedido) throws ServiceDacException {
		try {
			itemDePedidoDAO.delete(itemDePedido); 
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public ItemDePedido getByID(int itemDePedidoId) throws ServiceDacException {
		try {
			return itemDePedidoDAO.getByID(itemDePedidoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<ItemDePedido> getAll() throws ServiceDacException {
		try {
			return itemDePedidoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<ItemDePedido> findBy(ItemDePedidoFilter filter) throws ServiceDacException {
		try {
			return itemDePedidoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	

}
