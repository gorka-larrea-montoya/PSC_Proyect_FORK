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
				
		logger.info("Saving Alquiler :" + object.getUsuario() +" : " +  object.getLibronombre() + "With DAO");
		return super.saveObject(object);
		
	}

	@Override
	public void delete(Alquiler object) {
		super.deleteObject(object);
		
	}
	public List<Alquiler> findByUser(String user){
		logger.debug("called findByUser: "+user );
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		
		List<Alquiler> result = new ArrayList<>();
		try {
			
			Query<Alquiler> query = pm.newQuery(Alquiler.class);
			query.setFilter("usuario == '" + user + "'");
			List<Alquiler> execute = (List<Alquiler>) query.execute();
			result = execute;
			
			logger.debug("Sending List of Alquilers with size " + result.size());
			logger.debug("Sending Alquilers test: " + result.get(3).toString());
			
			
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
		}finally {
			pm.close();
		}
		return result;
	}
	
	
	@Override
	public List<Alquiler> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		List<Alquiler> alquileres = new ArrayList<>();
		
		try {
			Extent<Alquiler> extent = pm.getExtent(Alquiler.class, true);
			
			for (Alquiler category : extent) {
				alquileres.add(category);
			}
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Alquileres :" + e.getMessage());
		}finally {
			pm.close();	
		}
		return alquileres;
	}

	public Alquiler find(String user, String libro) {
		PersistenceManager pm = pmf.getPersistenceManager();		
		Alquiler result = null;
		try {			
			Query<?> query = pm.newQuery("SELECT FROM " + Alquiler.class.getName() + " WHERE User == '" + user + " AND Libro == '" + libro + "'");
			query.setUnique(true);
			result = (Alquiler) query.execute();
			

		}catch(Exception e) {
			logger.error("Error querying an Alquiler : "+ e.getMessage());
		}finally {
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
