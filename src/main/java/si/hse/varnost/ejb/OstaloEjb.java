package si.hse.varnost.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import si.hse.varnost.model.Ostalo;
import si.hse.varnost.model.Vrsta;

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
			results = em.createQuery("SELECT v FROM Ostalo v WHERE v.deleted=FALSE").getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri OstaloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ostalo> findByVrsta(Vrsta vrsta) {
		EntityManager em = tools.getEntityManager();

		List<Ostalo> results = new ArrayList<Ostalo>();
		try {
			results = em.createQuery("SELECT v FROM Ostalo v WHERE v.vrsta=:vrsta AND v.deleted=FALSE").setParameter("vrsta", vrsta).getResultList();
		} catch (Exception e) {
			System.err.println("Exception pri OstaloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return results;
	}
	
	@SuppressWarnings("unchecked")
	public Ostalo findByOpis(String opis) {
		EntityManager em = tools.getEntityManager();

		Ostalo result =  null;
		try {
			result = (Ostalo) em.createQuery("SELECT v FROM Ostalo v WHERE v.opis = :opis AND v.deleted=FALSE").setParameter("opis", opis).getSingleResult();
		} catch (Exception e) {
			System.err.println("Exception pri OstaloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Ostalo findById(int id) {
		EntityManager em = tools.getEntityManager();

		Ostalo result =  null;
		try {
			result = (Ostalo) em.createQuery("SELECT v FROM Ostalo v WHERE v.id = :id AND v.deleted=FALSE").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			System.err.println("Exception pri OstaloEjb:" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

		return result;
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
