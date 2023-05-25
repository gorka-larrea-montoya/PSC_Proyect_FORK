package es.deusto.spq.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.User;
import es.deusto.spq.pojo.AlquilerDTO;

import es.deusto.spq.pojo.UserData;
import es.deusto.spq.pojo.Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import es.deusto.spq.server.jdo.Libro;
import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Compra;
import es.deusto.spq.pojo.CompraDTO;
import es.deusto.spq.pojo.LibroDTO;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;


	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		}


	@POST
	@Path("/populateDB")
	public Response populateDB() {
		LudoFunBooksService.getInstance().populateDB();
		return Response.ok().build();
	}
	
	@POST
	@Path("/anadirLibro")
	public Response anadirLibro(Libro libro) {
		if (LudoFunBooksService.getInstance().addLibro(libro)) {
			return Response.ok().build();
		} else {
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/login")
	public Response loginUser(UserData userData) {
		if (LudoFunAccountService.getInstance().loginUser(userData)) {
			return Response.ok().build();
		}else {
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		if (LudoFunAccountService.getInstance().registerUser(userData)) {
			LudoFunBooksService.getInstance().populateDB();
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	@GET
	@Path("/getBooks")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getBooks() {
		logger.debug("Called getBooks");
		List<Libro> result = LudoFunBooksService.getInstance().getLibros();
		
		return Response.ok(result).build();
	}

	@GET
	@Path("/librosAlquiler")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksAlquiler() {
		logger.debug("Called getBooksAlquiler");
		List<Libro> books = LudoFunBooksService.getInstance().getLibrosAlquiler();
		logger.debug("Sending books test: " + books.get(0).toString());
		logger.debug("Sending List of books with size" + books.size());
		return Response.ok(books).build();
	}

	@GET
	@Path("/librosCompra")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksCompra() {
		logger.debug("Called getBooksCompra");
		
		List<Libro> result = LudoFunBooksService.getInstance().getLibrosCompra();
		logger.debug("Sending books test: " + result.get(0).toString());
		logger.debug("Sending List of books with size " + result.size());
		
		return Response.ok(result).build();
	}
	
	@POST
	@Path("/ComprarLibro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response compraLibros(CompraDTO c) {
		
		if (LudoFunAccountService.getInstance().registerCompra(c)) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	/**
	 * metodo coger libros que tiene un usuario especifico comprados
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	@Path("/librosCompraU")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksCompraUsuario(String usuario) {
		
	
		List<Libro> books = new ArrayList();
		
		//TODO reescribir todo esto
		// coger ids de los libros que hay en la tabla compra con ese usuario
		/*try {
			// System.out.println("ENTRA");
			Query query = pm.newQuery(Compra.class);
			//System.out.println("QUERY:" + query);
			query.setResult("bookKey");
			query.setFilter("usuario == '" + usuario + "'");
			//System.out.println("QUERY2:" + query);
			ids = (List<Long>) query.execute();

		} finally {

		}

		// buscar los libros que tiene esos ids
		try {
			List<Libro> libros = new ArrayList();
			for (int i = 0; i < ids.size(); i++) {

				Query query = pm.newQuery(Libro.class);
				//System.out.println("QUERY3:" + query);
				query.setFilter("id ==" + ids.get(i));
				//System.out.println("QUERY4:" + query);
				libros = (List<Libro>) query.execute();
				books.addAll(libros);
			}

		} finally {
			pm.close();
		}*/

		return Response.ok(books).build();
	}

	
	@GET
	@Path("/librosAlquiladosUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksAlquilarUsuario(String usuario) {
		List<Alquiler> result = LudoFunBooksService.getInstance().getAlquileresUsuario(usuario);
		
		return Response.ok(result).build();
	}

	
	
	/**
	 * Actualizar libro comprado a vendido
	 */
	@POST
	@Path("/ActualizarLibroComprado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ActualizarLibrosComprado(LibroDTO libro) {


		if (LudoFunAccountService.getInstance().registerActualizarLibro(libro)) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}

	}

	@POST
	@Path("alquilarLibros")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alquilarLibros(ArrayList<AlquilerDTO> alquileres) {
		logger.info("Recibidos alquileres:");
		for (AlquilerDTO alquiler : alquileres) {
			logger.info("Recibidos Alquileres: " + alquiler.getUsuario() + ": " + alquiler.getLibroNombre() + " - " + alquiler.getFecha_compra());
			LudoFunAccountService.getInstance().alquilarLibro(alquiler);
		}

		return Response.ok("//TODO ALQUILAR LIBROS").build();

	}

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hello world!").build();
	}
}
