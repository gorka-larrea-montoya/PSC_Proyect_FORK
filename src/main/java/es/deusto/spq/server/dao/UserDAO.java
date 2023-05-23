package es.deusto.spq.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.User;


public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User>{
	private static UserDAO instance;
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	@Override
	public boolean Save(User object) {
		logger.info("Saving User :" + object.getLogin() + " With DAO");
		return super.saveObject(object);
		
	}

	@Override
	public void delete(User object) {
		super.deleteObject(object);
		
	}

	@Override
	public List<User> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<User> Useres = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<User> extent = pm.getExtent(User.class, true);
			
			for (User category : extent) {
				Useres.add(category);
			}
			tx.commit();
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Useres :" + e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();	
		}
		return Useres;
	}

	public User find(String login) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		User result = null;
		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == '" + login + "'");
			query.setUnique(true);
			result = (User) query.execute();
			logger.debug("1:Searched for  " + login + " and found " + result.toString() );
			tx.commit();
			logger.debug("2:Searched for " + login + " and found " + result.toString() );
		}catch(Exception e) {
			logger.error("Error querying an User : "+ e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();	
		}
		logger.debug("3: Searched for " + login + " and found " + result.toString() );
		return result;
	}

	@Override
	public void update(User object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception e) {
			logger.error("Error updating object: " + e.getStackTrace());

		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		pm.close();
	}

}