package si.hse.varnost.ejb;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@ApplicationScoped
public class Tools {
	
	@Resource
	private DataSource dataSource;
	
	//@PersistenceUnit(name="pu_hse")
	private EntityManagerFactory entityManagerFactory;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("pu_hse");
		}
		
		return entityManagerFactory.createEntityManager();
	}

}
