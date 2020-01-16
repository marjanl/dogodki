package si.hse.varnost.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import si.hse.varnost.model.Izmenovodja;
import si.hse.varnost.model.Ostalo;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class OstaloEjb {
	@PersistenceContext
	private EntityManager em;
	@Inject
	private Tools tools;

	@SuppressWarnings("unchecked")
	public List<Ostalo> findAll() {
		EntityManager em = tools.getEntityManager();

		List<Ostalo> results = new ArrayList<Ostalo>();
		try {
			results = em.createQuery("SELECT v FROM Ostalo v").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri OstaloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}

	public Ostalo mergeOstalo(Ostalo selected) throws Exception {
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
	
	public void deleteOstalo(Ostalo v) throws Exception {
		EntityManager em = tools.getEntityManager();
		try {
			Ostalo ostalo = em.merge(v);
			em.remove(ostalo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}
}
