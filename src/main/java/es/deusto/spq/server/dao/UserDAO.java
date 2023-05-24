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
		List<User> Useres = new ArrayList<>();
		
		try {
			Extent<User> extent = pm.getExtent(User.class, true);
			
			for (User category : extent) {
				Useres.add(category);
			}
			
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Useres :" + e.getMessage());
		}finally {
			pm.close();	
		}
		return Useres;
	}

	public User find(String login) {
		PersistenceManager pm = pmf.getPersistenceManager();		
		User result = null;
		try {		
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == '" + login + "'");
			query.setUnique(true);
			result = (User) query.execute();

		}catch(Exception e) {
		}finally {
			pm.close();	
		}
		logger.debug("Searched for " + login + " and found " + result.toString() );
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