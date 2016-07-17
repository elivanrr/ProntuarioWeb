package br.med.nader.DAO;



import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.EntityManagerImpl;

import com.mysql.jdbc.Connection;


public class DAO {
	
	EntityManagerFactory emf;
	

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public DAO() {
		emf = Persistence.createEntityManagerFactory("sistema");
	}

	public Session getSession() {
		Session session = null;

		if (getEntityManager().getDelegate() instanceof EntityManagerImpl) {
			EntityManagerImpl entityManagerImpl = (EntityManagerImpl) getEntityManager()
					.getDelegate();
			return entityManagerImpl.getSession();
		} else {
			return (Session) getEntityManager().getDelegate();
		}
	}
	public static Connection getConnection() throws HibernateException, SQLException{
		return (Connection) new AnnotationConfiguration().configure().buildSettings().getConnectionProvider()
		.getConnection();
		}
}