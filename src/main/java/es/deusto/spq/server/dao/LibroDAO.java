package es.deusto.spq.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.Libro;


public class LibroDAO extends DataAccessObjectBase implements IDataAccessObject<Libro>{
	private static LibroDAO instance;
	
	public static LibroDAO getInstance() {
		if (instance == null) {
			instance = new LibroDAO();
		}
		return instance;
	}
	@Override
	public boolean Save(Libro object) {
		
		logger.info("Saving Libro :" + object.getNombre() + " With DAO");
		return super.saveObject(object);
		
	}

	@Override
	public void delete(Libro object) {
		super.deleteObject(object);
		
	}

	@Override
	public List<Libro> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		List<Libro> Libroes = new ArrayList<>();
		
		try {
			
			Extent<Libro> extent = pm.getExtent(Libro.class, true);
			
			for (Libro category : extent) {
				Libroes.add(category);
			}
		
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Libroes :" + e.getMessage());
		}finally {
			pm.close();	
		}
		logger.debug("Sending List of books with size" + Libroes.size());
		logger.debug("Sending books test: " + Libroes.get(0).toString());
		return Libroes;
	}
	public List<Libro> findTipo(String tipo){
		logger.debug("called findTipo: " + tipo);
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		List<Libro> result = new ArrayList<>();
		try {
			
			Query<Libro> query = pm.newQuery(Libro.class);
			query.setFilter("tipo == '" + tipo + "'");
			result = (List<Libro>) query.execute();

			
			logger.debug("Sending List of books with size " + result.size());
			for (int i = 0; i < result.size(); i++) {
				logger.debug("Sending Book: " + result.get(i).getNombre());;
			}
			
			
			logger.debug("Sending books test post commit: " + result.get(3).toString());
		} catch(Exception e) {
			logger.error("Error : " + e.getMessage());
		} finally {
			pm.close();
		}
		logger.debug("Sending List of books with size" + result.size());

		for (int i = 0; i < result.size(); i++) {
			logger.debug("Sending Book: " + result.get(i).getNombre());;
		}
		
		return result;
	}

	public Libro find(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Libro result = new Libro();
		try {
			
			//result = pm.getObjectById(Libro.class, nombre);
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre + "'");
			query.setUnique(true);
			result = (Libro) query.execute();
			logger.debug("LibroDAO : Searched for " + nombre + " and found " + result.toString() );

			logger.debug("LibroDAO : Right after commit:" + result.toString());
		}catch(Exception e) {
			logger.error("LibroDAO : Error querying an Libro : "+ e.getMessage());
		}finally {

			logger.debug("Right before closing:" + result.toString());
			pm.close();	
		}
		logger.debug("Result :" + nombre + " and found " + result.toString() );
		return result;
	}

	@Override
	public void update(Libro object) {
	
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Libro l = find(object.getNombre());
		logger.debug("LibroDAO : Intentando actualizar libro: " + object.getNombre() + " - " + object.getTipo() + " - " + object.getPrecio());
	
		try {
			tx.begin();
					
			l.setDescripccion(object.getDescripccion());
			l.setPrecio(object.getPrecio());
			l.setTipo(object.getTipo());
//			pm.makePersistent(object);
			logger.debug("LibroDAO: update :  a punto de commit : " + l.toString())	;
			tx.commit();
			logger.debug("LibroDAO:  despues de commit : " + l.toString());
		} catch (Exception e) {
			logger.error("Error updating object: " + object.getNombre() + " : " + e.getMessage());
			e.printStackTrace();
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		pm.close();
		
	}
	public void alquilar(String nombre) {
		logger.debug("Alquilar: " + nombre);
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre + "'");
			query.setUnique(true);
			Libro l = (Libro) query.execute();
			logger.debug("LibroDAO : Searched for " + nombre + " and found " + l.toString() );
			
			l.setTipo("alquilado");
			pm.makePersistent(l);
			tx.commit();
		} catch (Exception e) {
			logger.error("Error updating object: " + nombre + " : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		pm.close();
	}
	public void deleteByName(String nombre) {
		logger.debug("Called deleteByName: " + nombre);
		Libro l = find(nombre);	
		delete(l);
	
		
	}

}