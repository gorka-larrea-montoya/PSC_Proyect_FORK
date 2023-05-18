package es.deusto.spq.client;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.gui.AnadirLibroOptionPane;
import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.server.LudoFunAccountService;

public class ClientController {
	public static ClientController instance;
	protected static final Logger logger = LogManager.getLogger();
	
	
	
	public static ClientController getInstance() {
		if (instance == null) {
			instance = new ClientController();
		}
		return instance;
	}
	
	public ClientController() {
	}
	
	public boolean adminSaveBook(LibroDTO Lib) {
		return ExampleClient.getInstance().anadirLibro(Lib);
	}


	public boolean validarLibro(String nombre,String desc, String precio, String tipo) {
		boolean result = true;
		if (nombre.trim() == "") {
			logger.error("Error al introducir libro: el nombre esta vacio");
			result = false;
		}
		if (desc.trim() == "") {
			logger.error("Error al introducir libro: la descripcion esta vacia");
			result = false;
		}
		try {
			if (Float.parseFloat(precio.trim()) < 0.01) {
				result = false;
				logger.error("Error al introducir libro: el precio es demasiado bajo");
			}
		} catch (Exception e) {
			logger.error("Error al introducir libro: el precio no es un numero");
			result = false;
		}
		
		return result;
	}
	public boolean registrarUsuario(String user, String pass) {
		return ExampleClient.getInstance().registerUser(user, pass);
	}

	public boolean alquilarLibros(ArrayList<LibroDTO> result) {
		return ExampleClient.getInstance().alquilarLibros(result, null);		
	}
}
