package si.hse.varnost.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import si.hse.varnost.model.Varnostnik;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class VarnostnikEjb {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private Tools tools;

	@SuppressWarnings("unchecked")
	public List<Varnostnik> findAll() {
		EntityManager em = tools.getEntityManager();

		List<Varnostnik> results = new ArrayList<Varnostnik>();
		try {
			results = em.createQuery("SELECT v FROM Varnostnik v").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri VarnostnikEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}

	public Varnostnik mergeVarnostnik(Varnostnik selected) throws Exception {
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
	
	public void deleteVarnostnik(Varnostnik v) throws Exception {
		EntityManager em = tools.getEntityManager();
		try {
			Varnostnik varnostnik = em.merge(v);
			em.remove(varnostnik);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}
	
	
}
