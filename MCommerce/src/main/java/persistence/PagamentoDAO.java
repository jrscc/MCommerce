package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import filters.PagamentoFilter;
import model.Pagamento;

public class PagamentoDAO extends DAO{

	private static final long serialVersionUID = 6472640455847893120L;
	
	public void save(Pagamento obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		}
	}

	public Pagamento update(Pagamento obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pagamento resultado = obj;
		try {
			resultado = em.merge(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		}
		return resultado;
	}

	public void delete(Pagamento obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Pagamento.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}
	}

	public Pagamento getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pagamento resultado = null;
		try {
			resultado = em.find(Pagamento.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		}

		return resultado;
	}

	public List<Pagamento> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Pagamento> findBy(PagamentoFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<Pagamento> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT p FROM Pagamento p WHERE 1 = 1 ";
			
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
			TypedQuery<Pagamento> query = em.createQuery(jpql, Pagamento.class);
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

	
//	private boolean notEmpty(Object obj) {
//		return obj != null;
//	}
//	
//	private boolean notEmpty(String obj) {
//		return obj != null && !obj.trim().isEmpty();
//	}

}
