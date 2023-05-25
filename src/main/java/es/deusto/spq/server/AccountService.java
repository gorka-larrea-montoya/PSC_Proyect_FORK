package es.deusto.spq.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.pojo.CompraDTO;
import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.jdo.Compra;

import es.deusto.spq.pojo.AlquilerDTO;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.dao.AlquilerDAO;
import es.deusto.spq.server.dao.CompraDAO;
import es.deusto.spq.server.dao.LibroDAO;
import es.deusto.spq.server.dao.UserDAO;
import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Libro;
import es.deusto.spq.server.jdo.User;
import es.deusto.spq.pojo.UserData;

public class AccountService {
 
	private PersistenceManager pm = null;
	private Transaction tx = null;
	protected static final Logger logger = LogManager.getLogger();
	private static AccountService instance;

	public static AccountService getInstance() {
		if (instance == null) {
			instance = new AccountService();
		}
		return instance;
	}

	public AccountService() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}


	public boolean registerUser(UserData userData) {
		try {
			tx.begin();
			logger.info("Register : Checking whether the user already exits or not: '{}'", userData.getLogin());
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("Exception launched: {}", e.getMessage());
			}
			logger.info("User: {}", user);
			if (user != null) {
				logger.info("User already exists");
				return false;
			} else {
				logger.info("Creating user: {}", user);
				user = new User(userData.getLogin(), userData.getPassword());
				pm.makePersistent(user);
				logger.info("User created: {}", user);
			}
			tx.commit();
			return true;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public boolean loginUser(UserData userData) {
		boolean result = false;
		User user = null;
		logger.info("Login: Checking whether the user already exits or not: ", userData.getLogin());
		user = UserDAO.getInstance().find(userData.getLogin());
		
		if (user == null) {
			logger.info("The user does not exist");
		}else {
			logger.debug("AccountService - Login: obtained User: " +  user.getLogin()+" : " +user.getPassword() +"");
			if (userData.getPassword().equals(user.getPassword())) {
				logger.debug("Login: Passwords match: " + userData.getPassword() + " - " + user.getPassword());
				result = true;
			} else {
				logger.debug("Login: Passwords do not match: '" + userData.getPassword() + "' - '" + user.getPassword() + "'");
			}
		}
		return result;
	}
 
	public boolean registerCompra(CompraDTO compra) {
		Compra c = new Compra();
		boolean result = false;
		c.setUsuario(compra.getUsuario());
		c.setLibronombre(compra.getLibronombre());
		c.setLibrodesc(compra.getLibrodesc());
		c.setLibroprecio(compra.getLibroprecio());
		
		// Si va bien debería pasarse a true
		if(CompraDAO.getInstance().saveObject(c)) {
			LibroDAO.getInstance().deleteByName(compra.getLibronombre());
			result = true;
		}
				
		return result;
	}
 
	public boolean registerActualizarLibro(LibroDTO libro) {
		/* TODO
		Libro l = new Libro(libro.getNombre(),libro.getDescripccion(), libro.getPrecio(),
				libro.getTipo());
		l.setId(libro.getId());
		
		try {
			tx.begin();
			// logger.info("AÑADIENDO LIBRO: ",compra.getLibro().getId(),
			// compra.getLibro().getNombre(),compra.getUsuario());
			pm.makePersistent(l);
			logger.info("Purchase added: {}", l);
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("Exception thrown while adding purchase: {}", e.getMessage());
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		}*/ return false; //TODO quitar esto al arreglar
	}
}
