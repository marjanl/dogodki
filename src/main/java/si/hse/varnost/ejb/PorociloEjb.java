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
	
	public void create(Porocilo porocilo) throws Exception {
		EntityManager em = tools.getEntityManager();
		try {
			porocilo.setSaveTime(new Date());
			em.merge(porocilo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Porocilo> findAll() {
		EntityManager em = tools.getEntityManager();

		List<Porocilo> results = new ArrayList<>();
		try {
			results = em.createQuery("SELECT v FROM Porocilo v").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri PorociloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}
}
