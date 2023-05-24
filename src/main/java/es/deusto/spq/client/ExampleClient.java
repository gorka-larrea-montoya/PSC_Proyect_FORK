package es.deusto.spq.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.GenericType;

import javax.ws.rs.client.Client;

import es.deusto.spq.client.gui.VentanaLoginN;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.pojo.CompraDTO;
import es.deusto.spq.pojo.AlquilerDTO;
import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.pojo.UserData;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleClient {

	protected static final Logger logger = LogManager.getLogger();
	private static String hostname;
	private static String port;
//	private static final String USER = "dipina";
//	private static final String PASSWORD = "dipina";

	private Client client;
	WebTarget webTarget;
	public static ExampleClient instance;
	
	

	public ExampleClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
	}
	public static ExampleClient getInstance() {
		if (instance == null) {
			logger.error("Error: No hay Instance de ExampleClient");
		}
		return instance;
	}

	public boolean registerUser(String login, String password) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly registered");
			return true;
		}
	}
	public boolean loginUser(String name, String password) {
		WebTarget loginUserWebTarget = webTarget.path("login");
		Invocation.Builder invocationBuilder = loginUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		UserData userData = new UserData();
		userData.setLogin(name);
		userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly logged");
			return true;
		}
	}
	
	public boolean anadirLibro(LibroDTO lib) {
		WebTarget booksWebTarget = webTarget.path("anadirLibro");
	    Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);
	    
	    Response response = invocationBuilder.post(Entity.entity(lib, MediaType.APPLICATION_JSON));
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	    	logger.error("Error anadiendo el libro. Code : {}", response.getStatus());
	    	return false;
	    } else {
	    	logger.info("Libros anadidos correctamente");
	    	return true;
	    }
	}
	
	public List<LibroDTO> getBooksAlquiler() {
	    WebTarget booksWebTarget = webTarget.path("librosAlquiler");
	    Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);

	    Response response = invocationBuilder.get();
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
	        return null;
	    } else {
	        List<LibroDTO> books = response.readEntity(new GenericType<List<LibroDTO>>() {});
	        logger.debug("Received List of books with size: " + books.size());
	        logger.debug("Received books test: " + books.get(0).toString());
	        return books;
	    }
	}
	public List<LibroDTO> getAllBooks() {
	    WebTarget booksWebTarget = webTarget.path("getBooks");
	    Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);

	    Response response = invocationBuilder.get();
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
	        return null;
	    } else {
	        List<LibroDTO> books = response.readEntity(new GenericType<List<LibroDTO>>() {});
	        logger.debug("Received List of books with size: " + books.size());
	        logger.debug("Received books test: " + books.get(0).toString());
	        return books;
	    }
	}

	public List<LibroDTO> getBooksCompra() {
	    WebTarget booksWebTarget = webTarget.path("librosCompra");
	    Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);

	    Response response = invocationBuilder.get();
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
	        return null;
	    } else {
	        List<LibroDTO> books = response.readEntity(new GenericType<List<LibroDTO>>() {});
	        logger.debug("Received List of books with size: " + books.size());
	        try {
	        	for (int i = 0; i < books.size(); i++) {
	        		logger.debug("Received books test: " + books.get(i).toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
	 
	        return books;
	    }
	}
	/**
	 * Metodo para cger los libros que un cliente ha comprado
	 * @param usuario
	 * @return
	 */
	public List<LibroDTO> getBooksCompraUsuario(String usuario) {
		WebTarget booksWebTarget = webTarget.path("librosCompraU");
		Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			List<LibroDTO> books = response.readEntity(new GenericType<List<LibroDTO>>() {
			});
			logger.debug("Received List of books with size: " + books.size());
			return books;
		}
	}
	
	public List<LibroDTO> getBooksUsuarioUsuario(String usuario) {
		WebTarget booksWebTarget = webTarget.path("librosAlquiladosUsuario");
		Invocation.Builder invocationBuilder = booksWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			List<LibroDTO> books = response.readEntity(new GenericType<List<LibroDTO>>() {
			});
			logger.info("Books correctly obtained");
			return books;
		}
	}

	public static void main(String[] args) {
		
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		hostname = args[0];
		port = args[1];
		instance = new ExampleClient(hostname, port);
		
		VentanaLoginN vent = new VentanaLoginN();
		
//		exampleClient.registerUser(USER, PASSWORD);
//		exampleClient.sayMessage(USER, PASSWORD, "This is a test!...");
	}
	public boolean alquilarLibros(ArrayList<LibroDTO> libros,String usuario) {
		WebTarget registerUserWebTarget = webTarget.path("alquilarLibros");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		//convierte un array de libros x usuario en un array de Alquiler.
		
		ArrayList<AlquilerDTO> alquileres = new ArrayList<AlquilerDTO>();
		if(!libros.isEmpty()) {
			for (LibroDTO libro : libros) {
				alquileres.add(new AlquilerDTO(libro.getNombre(),usuario,new Date().toString()));
			}
			Response response = invocationBuilder.post(Entity.entity(alquileres, MediaType.APPLICATION_JSON));
			if (response.getStatus() != Status.OK.getStatusCode()) {
				logger.error("Error connecting with the server. Code: {}", response.getStatus());
				return false;
			} else {
				logger.info("Libros Alquilados Correctamente");
				return true;
			}
		}else {
			return false;
		}
		
		
		
//		for (int i = 0; i < libros.size(); i++) {
//			logger.info("Intentando mandar: [" + libros.get(i)+"] "+ libros.get(i).getNombre());
//		} 
//		if (response.getStatus() != Status.OK.getStatusCode()) {
//			logger.error("Error connecting with the server. Code: {}", response.getStatus());
//		} else {
//			logger.info("Libros Alquilados Correctamente");
		}
		

	public boolean comprarLibro(long id, String titulo, String descrip, float precio, String tipo, String usuario) {
	
		WebTarget registerUserWebTarget = webTarget.path("ComprarLibro");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		LibroDTO l=new LibroDTO(titulo,descrip,precio,tipo);
		l.setId(id);
		CompraDTO compra=new CompraDTO(l,usuario);
		Response response = invocationBuilder.post(Entity.entity(compra, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly logged");
			return true;
		}
	}
	
	
	
	public boolean actualizarLibroComprado(long id, String titulo, String descrip, float precio, String tipo, String usuario) {
		WebTarget registerUserWebTarget = webTarget.path("ActualizarLibroComprado");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		LibroDTO l=new LibroDTO(titulo,descrip,precio,tipo);
		l.setId(id);
		Response response = invocationBuilder.post(Entity.entity(l, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly logged");
			return true;
		}
		
	}
		
}