package es.deusto.spq.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.gui.AnadirLibroOptionPane;
import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.pojo.UserData;



public class ClientController {
	public static ClientController instance;
	private String user;
	private String password;
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
	public boolean loginUsuario(String nombre, String pass) {
		UserData data = new UserData(nombre,pass);
		boolean result = ExampleClient.getInstance().loginUser(data);
		if (result) {
			this.setUser(nombre);
			this.setPass(pass);
		}
		return result;

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

	public String getUser() {
		return user;
	}

	public  void setUser(String user) {
		this.user = user;
	}

	public  String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}

	public List<LibroDTO> getBooksAlquilerUsuario() {
		return ExampleClient.getInstance().getBooksAlquilerUsuario(user);
	}

	public List<LibroDTO> getBooksCompraUsuario() {
		return ExampleClient.getInstance().getBooksCompraUsuario(user);
	}
	
}
