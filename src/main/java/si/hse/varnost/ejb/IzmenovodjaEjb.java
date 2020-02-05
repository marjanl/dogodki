package si.hse.varnost.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import si.hse.varnost.model.Izmenovodja;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class IzmenovodjaEjb {
	
	@PersistenceContext
	private EntityManager em;
	@Inject
	private Tools tools;

	@SuppressWarnings("unchecked")
	public List<Izmenovodja> findAll() {
		EntityManager em = tools.getEntityManager();

		List<Izmenovodja> results = new ArrayList<Izmenovodja>();
		try {
			results = em.createQuery("SELECT v FROM Izmenovodja v WHERE v.deleted=FALSE").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri IzmenovodjaEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}

	public Izmenovodja mergeIzmenovodja(Izmenovodja selected) throws Exception {
		EntityManager em = tools.getEntityManager();
		try {
			return em.merge(selected);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}

	}
	
	public void deleteIzmenovodja(Izmenovodja v) throws Exception {
		EntityManager em = tools.getEntityManager();
		try {
			v.setDeleted(true);
			em.merge(v);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}
	
}
