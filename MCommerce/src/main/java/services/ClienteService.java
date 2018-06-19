package services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import filters.ClienteFilter;
import model.Cliente;
import persistence.ClienteDAO;
import persistence.PersistenciaDacException;
import util.TransacionalCdi;

@ApplicationScoped
public class ClienteService implements Serializable{

	private static final long serialVersionUID = 1107218611303294000L;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@TransacionalCdi
	public void save(Cliente cliente) throws ServiceDacException {
		try {
			// Verificar se login já existe
			validarLogin(cliente);
			calcularHashDaSenha(cliente);
			clienteDAO.save(cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void update(Cliente cliente, boolean passwordChanged) throws ServiceDacException {
		
		try {
			// Verificar se login já existe
			validarLogin(cliente);
			if (passwordChanged) {
				calcularHashDaSenha(cliente);
			}
			clienteDAO.update(cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@TransacionalCdi
	public void delete(Cliente cliente) throws ServiceDacException {
		try {
			clienteDAO.delete(cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Cliente getByID(int clienteId) throws ServiceDacException {
		try {
			return clienteDAO.getByID(clienteId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Cliente> getAll() throws ServiceDacException {
		try {
			return clienteDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Cliente> findBy(ClienteFilter filter) throws ServiceDacException {
		try {
			return clienteDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	
	private String calcularHashDaSenha(Cliente cliente) throws ServiceDacException {
		cliente.setPassword(hash(cliente.getPassword()));
		return cliente.getPassword();
	}

	
	public boolean senhaAtualConfere(String passwordAtualHash, String confirmacaoPasswordAtual) throws ServiceDacException {
		
		if (passwordAtualHash == null && confirmacaoPasswordAtual == null) {
			return true;
		}

		if (passwordAtualHash == null || confirmacaoPasswordAtual == null) {
			return false;
		}
		
		String confirmacaoPasswordAtualHash = hash(confirmacaoPasswordAtual);
		
		return passwordAtualHash.equals(confirmacaoPasswordAtualHash);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

	public void validarLogin(Cliente cliente) throws ServiceDacException {
		boolean jahExiste = clienteDAO.existeUsuarioComLogin(cliente);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + cliente.getLogin()); 
		}
	}
	
}
