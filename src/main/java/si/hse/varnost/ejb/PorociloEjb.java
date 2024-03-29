package si.hse.varnost.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import si.hse.varnost.model.Porocilo;
import si.hse.varnost.model.Varnostnik;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class PorociloEjb {
	@PersistenceContext
	private EntityManager em;
	@Inject
	private Tools tools;
	
	public void create(Porocilo porocilo) {
		EntityManager em = tools.getEntityManager();
		try {
			porocilo.setSaveTime(new Date());
			em.merge(porocilo);
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Porocilo> findAll() {
		EntityManager em = tools.getEntityManager();

		List<Porocilo> results = new ArrayList<>();
		try {
			results = em.createQuery("SELECT v FROM Porocilo v ORDER BY v.id DESC").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri PorociloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}

	public void deletePorocilo(Porocilo p) {
		EntityManager em = tools.getEntityManager();
		try {
			em.remove(em.merge(p));
		} finally {
			em.close();
		}
		
	}
}
