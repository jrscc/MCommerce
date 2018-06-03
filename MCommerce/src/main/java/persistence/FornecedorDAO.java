package persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import filters.FornecedorFilter;
import model.Fornecedor;


public class FornecedorDAO  extends DAO{
	
	private static final long serialVersionUID = -8394883702700368658L;

	public void save(Fornecedor obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		}
	}

	public Fornecedor update(Fornecedor obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Fornecedor resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		}
		return resultado;
	}

	public void delete(Fornecedor obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Fornecedor.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}
	}

	public Fornecedor getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Fornecedor resultado = null;
		try {
			resultado = em.find(Fornecedor.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		}

		return resultado;
	}

	public List<Fornecedor> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Fornecedor> findBy(FornecedorFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<Fornecedor> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT u FROM User u WHERE 1 = 1 ";
			
//			// First name
//			if (notEmpty(filter.getFirstName())) {
//				jpql += "AND u.firstName LIKE :firstName ";
//			}
//
//			// Last name
//			if (notEmpty(filter.getLastName())) {
//				jpql += "AND u.lastName LIKE :lastName ";
//			}
//
//			// Birthday begin
//			if (notEmpty(filter.getBirthdayRangeBegin())) {
//				jpql += "AND u.birthday >= :birthdayRangeBegin ";
//			}
//
//			// Birthday end
//			if (notEmpty(filter.getBirthdayRangeEnd())) {
//				jpql += "AND u.birthday <= :birthdayRangeEnd ";
//			}
//
//			// Group
//			if (notEmpty(filter.getGroup())) {
//				jpql += "AND u.group = :group ";
//			}
//			
//			// Login
//			if (notEmpty(filter.getLogin())) {
//				jpql += "AND u.login = :login ";
//			}
//
			TypedQuery<Fornecedor> query = em.createQuery(jpql, Fornecedor.class);
//			
//			// First name
//			if (notEmpty(filter.getFirstName())) {
//				query.setParameter("firstName", "%" + filter.getFirstName() + "%");
//			}
//
//			// Last name
//			if (notEmpty(filter.getLastName())) {
//				query.setParameter("lastName", "%" + filter.getLastName() + "%");
//			}
//
//			// Birthday begin
//			if (notEmpty(filter.getBirthdayRangeBegin())) {
//				Date startOfDay = DateUtil.getStartOfDay(filter.getBirthdayRangeBegin());
//				query.setParameter("birthdayRangeBegin", startOfDay);
//			}
//
//			// Group
//			if (notEmpty(filter.getGroup())) {
//				query.setParameter("group", filter.getGroup());
//			}
//			
//			// Login
//			if (notEmpty(filter.getLogin())) {
//				query.setParameter("login", filter.getLogin());
//			}
			
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os usuários.", pe);
		}
		return resultado;
	}

	public boolean existeUsuarioComLogin(Fornecedor fornecedor) {
		if (fornecedor == null || !notEmpty(fornecedor.getLogin())) {
			return false;
		}
		
		// Usar estratégia de contabilizar quantos usuários existem com o dado login, e que não seja ele mesmo.
		// Existe algum usuário com o login caso a contagem seja diferente de zero.
		// Usar COUNT(*), já que cláusula EXISTS não pode ser usada no SELECT pela BNF do JPQL:
		// https://docs.oracle.com/html/E13946_01/ejb3_langref.html#ejb3_langref_bnf
		
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM User u WHERE u.login = :login ";
		if (fornecedor.getId() != null) {
			jpql += "AND u != :user ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", fornecedor.getLogin());
		if (fornecedor.getId() != null) {
			query.setParameter("user", fornecedor);
		}
		
		Long count = query.getSingleResult();
		return count > 0;
		
	}
	
	private boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
	
}