package es.deusto.spq.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Compra;

public class CompraDAO extends DataAccessObjectBase implements IDataAccessObject<Compra>  {

	private static CompraDAO instance;
	public static CompraDAO getInstance() {
		if (instance == null) {
			instance = new CompraDAO();
		}
		return instance;
	}
	@Override
	public boolean Save(Compra object) {
		logger.info("Saving Compra :" + object.getUsuario() +" : " +  object.getLibronombre() + "With DAO");
		return super.saveObject(object);	
	}

	@Override
	public void delete(Compra object) {
		logger.info("Deleting Compra :" + object.getUsuario() + " : " + object.getLibronombre());
		super.deleteObject(object);
		
	}

	@Override
	public List<Compra> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Compra> compras = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<Compra> extent = pm.getExtent(Compra.class,true);
			
			for (Compra category : extent) {
				compras.add(category);
			}
			tx.commit();
		} catch (Exception e) {
			logger.error("Error retrieving all the Compras :" + e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return compras;
	}

	public Compra find(String user, String titulo) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Compra result = null;
		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Compra.class.getName() + " WHERE User == '" + user + " AND Libro == '" + titulo + "'");
			query.setUnique(true);
			result = (Compra) query.execute();
			
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
	public void update(Compra object) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		logger.debug("CompraDAO : Intentando actualizar compra: " + object.getUsuario() + " - " + object.getLibronombre() );
	}
	@Override
	public Compra find(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
