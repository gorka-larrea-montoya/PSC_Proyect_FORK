package es.deusto.spq.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.Alquiler;


public class AlquilerDAO extends DataAccessObjectBase implements IDataAccessObject<Alquiler>{
	private static AlquilerDAO instance;
	
	public static AlquilerDAO getInstance() {
		if (instance == null) {
			instance = new AlquilerDAO();
		}
		return instance;
	}
	@Override
	public boolean Save(Alquiler object) {
				
		logger.info("Saving Alquiler :" + object.getUsuario() +" : " +  object.getLibro() + "With DAO");
		return super.saveObject(object);
		
	}

	@Override
	public void delete(Alquiler object) {
		super.deleteObject(object);
		
	}

	@Override
	public List<Alquiler> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Alquiler> alquileres = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<Alquiler> extent = pm.getExtent(Alquiler.class, true);
			
			for (Alquiler category : extent) {
				alquileres.add(category);
			}
			tx.commit();
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Alquileres :" + e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();	
		}
		return alquileres;
	}

	public Alquiler find(String user, String libro) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Alquiler result = null;
		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Alquiler.class.getName() + " WHERE User == '" + user + " AND Libro == '" + libro + "'");
			query.setUnique(true);
			result = (Alquiler) query.execute();
			
			tx.commit();
		}catch(Exception e) {
			logger.error("Error querying an Alquiler : "+ e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();	
		}
		return result;
	}

	@Override
	public void update(Alquiler object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception e) {
			logger.error("Error updating object: " + e.getMessage());
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		pm.close();
	}
		
	@Override
	public Alquiler find(String param) {
		return null;
	}

}
