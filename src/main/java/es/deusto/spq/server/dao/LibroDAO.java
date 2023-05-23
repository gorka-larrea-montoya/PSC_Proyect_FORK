package es.deusto.spq.server.dao;

import java.util.ArrayList;
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
		Transaction tx = pm.currentTransaction();
		
		List<Libro> Libroes = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<Libro> extent = pm.getExtent(Libro.class, true);
			
			for (Libro category : extent) {
				Libroes.add(category);
			}
			tx.commit();
			
		}catch(Exception e) {
			logger.error("Error retrieving all the Libroes :" + e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();	
		}
		return Libroes;
	}

	public Libro find(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		//ESTO NO FUNCIONABA ASI QUE PUSE LAS LLAMADAS DE DEBUG PARA VER DONDE DEJABA DE FUNCIONAR
		// Y ENTONCES EMPIEZA A FUNCIONAR
		// NO TOCAR SIN BUEN MOTIVO
		Libro result = new Libro();
		try {
			tx.begin();
			
			//result = pm.getObjectById(Libro.class, nombre);
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre + "'");
			query.setUnique(true);
			result = (Libro) query.execute();
			logger.debug("LibroDAO : Searched for " + nombre + " and found " + result.toString() );
			tx.commit();
			logger.debug("LibroDAO : Right after commit:" + result.toString());
		}catch(Exception e) {
			logger.error("LibroDAO : Error querying an Libro : "+ e.getMessage());
		}finally {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Libro l = find(nombre);
		try {
			//TODO hacer una función para facilitar el alquilar y desalquilar libros
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		pm.close();
	}

}