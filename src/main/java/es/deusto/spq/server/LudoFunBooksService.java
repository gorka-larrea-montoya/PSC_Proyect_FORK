package es.deusto.spq.server;

import es.deusto.spq.server.jdo.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LudoFunBooksService {

	private PersistenceManager pm = null;
	private Transaction tx = null;
	protected static final Logger logger = LogManager.getLogger();
	private static LudoFunBooksService instance;

	public static LudoFunBooksService getInstance() {
		if (instance == null) {
			instance = new LudoFunBooksService();
		}
		return instance;
	}

	public LudoFunBooksService() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	public boolean alquilarLibros(List<Book> books , String nombre, String contrasenia) {
		
		
		return false;
	}
	

	public void populateDB() {
		ArrayList<Book> bks = new ArrayList<Book>();
		
		bks.add( new Book("El capital","Obra de Karl Marx y Engels en la que bla bla bla",(float) 10.0,"alquiler"));
		bks.add( new Book("Teo va al parque", "En esta aventura para los mas jovenes bla bla",(float)5.95,"compra"));
		bks.add( new Book("Harry Potter y la piedra","Clasico de la JK en la que un chaval bla bla",(float)9.95,"compra"));
		bks.add( new Book("La biblia","El libro sagrado del cristianismo que bla bla bla",(float) 13.95,"alquiler"));
//
//		Book b1 = new Book("Cien años de soledad",
//				"Obra de Gabriel García Márquez en la que narra la historia de la familia Buendía", (float) 9.5,
//				"compra");
//		Book b2 = new Book("El Quijote",
//				"Obra de Miguel de Cervantes que cuenta las aventuras de un caballero y su escudero", (float) 8.2,
//				"alquiler");
//		Book b3 = new Book("La Odisea",
//				"Obra de Homero que narra el regreso de Ulises a su hogar tras la Guerra de Troya", (float) 7.8,
//				"compra");
//		Book b4 = new Book("El gran Gatsby",
//				"Obra de F. Scott Fitzgerald que describe la vida de la alta sociedad estadounidense en la década de 1920",
//				(float) 9.0, "compra");
		bks.add( new Book("1984", "Obra de George Orwell que retrata una sociedad totalitaria y opresiva", (float) 8.5,
				"alquiler"));
		bks.add( new Book("La Divina Comedia",
				"Obra de Dante Alighieri que describe el viaje del protagonista a través del Infierno, el Purgatorio y el Paraíso",
				(float) 9.3, "compra"));
		bks.add( new Book("Matar a un ruiseñor",
				"Obra de Harper Lee que trata sobre la discriminación racial en la América de los años 30", (float) 8.8,
				"alquiler"));
		bks.add( new Book("Don Juan Tenorio", "Obra de José Zorrilla que relata la historia del famoso seductor",
				(float) 7.5, "compra"));
		bks.add( new Book("La naranja mecánica",
				"Obra de Anthony Burgess que describe la violencia y la psicología criminal", (float) 8.0, "alquiler"));
		bks.add( new Book("La metamorfosis",
				"Obra de Franz Kafka en la que el protagonista se transforma en un insecto gigante", (float) 7.2,
				"compra"));
		bks.add( new Book("El perfume",
				"Obra de Patrick Süskind que describe la obsesión de un asesino por el aroma de las mujeres",
				(float) 8.7, "compra"));
		bks.add( new Book("El retrato de Dorian Gray",
				"Obra de Oscar Wilde que cuenta la historia de un joven que desea la eterna juventud", (float) 9.1,
				"alquiler"));
		bks.add( new Book("El código Da Vinci",
				"Obra de Dan Brown que narra la búsqueda del Santo Grial por parte del profesor Robert Langdon",
				(float) 6.8, "compra"));
		bks.add( new Book("La isla del tesoro",
				"Obra de Robert Louis Stevenson que relata la búsqueda del tesoro del Capitán Flint", (float) 8.3,
				"alquiler"));
		bks.add( new Book("El señor de las moscas",
				"Obra de William Golding que describe la lucha por el poder entre un grupo de niños varados en una isla desierta",
				(float) 7.9, "compra"));
		bks.add( new Book("El nombre de la rosa",
				"Obra de Umberto Eco que cuenta la investigación de una serie de misteriosos asesinatos en una abadía medieval",
				(float) 8.9, "compra"));
		bks.add( new Book("La conjura de los necios",
				"Obra de John Kennedy Toole que narra las desventuras de Ignatius J. Reilly en Nueva Orleans",
				(float) 7.6, "alquiler"));
		bks.add( new Book("El laberinto de la soledad",
				"Obra de Octavio Paz que reflexiona sobre la identidad del mexicano", (float) 9.2, "compra"));
		bks.add( new Book("Moby Dick",
				"Obra de Herman Melville que relata la obsesión del capitán Ahab por capturar a la ballena blanca",
				(float) 8.4, "alquiler"));
		bks.add( new Book("Los Miserables",
				"Obra de Victor Hugo que describe la vida de Jean Valjean y su lucha por la redención", (float) 9.5,
				"compra"));
		bks.add( new Book("La casa de los espíritus",
				"Obra de Isabel Allende que relata la historia de tres generaciones de la familia Trueba en Chile",
				(float) 8.1, "alquiler"));
		bks.add( new Book("La historia interminable",
				"Obra de Michael Ende que narra la aventura de Bastian Balthazar Bux en el mundo de Fantasía",
				(float) 7.3, "compra"));
		bks.add( new Book("Crónicas marcianas",
				"Obra de Ray Bradbury que describe la colonización de Marte por parte de los humanos", (float) 8.7,
				"alquiler"));
		bks.add( new Book("El alquimista",
				"Obra de Paulo Coelho que narra el viaje de Santiago en busca de su tesoro personal", (float) 6.9,
				"compra"));
		bks.add( new Book("El club de la lucha",
				"Obra de Chuck Palahniuk que describe la formación de un grupo de hombres que se reúnen para pelear a puñetazos",
				(float) 8.6, "alquiler"));
		bks.add( new Book("Cien años de soledad",
				"Obra de Gabriel García Márquez que narra la historia de la familia Buendía en Macondo", (float) 9.8,
				"compra"));
//		Book b27 = new Book("1984",
//				"Obra de George Orwell que describe una sociedad totalitaria en la que se controla todo", (float) 9.3,
//				"alquiler");
//		Book b28 = new Book("La odisea",
//				"Obra de Homero que relata el viaje de Ulises de vuelta a casa tras la Guerra de Troya", (float) 8.5,
//				"compra");
		bks.add( new Book("El gran Gatsby",
				"Obra de F. Scott Fitzgerald que describe la vida de los ricos en los años 20 en Nueva York",
				(float) 7.9, "alquiler"));
		bks.add( new Book("El retrato de una dama",
				"Obra de Henry James que cuenta la historia de Isabel Archer y su lucha por la independencia",
				(float) 8.2, "compra"));

		logger.info("InitDB: Introduciendo libros");		
		for (Book book : bks) {
			try {
				tx.begin();
				pm.makePersistent(book);
				tx.commit();
			} catch (Exception e) {
				e.getMessage();
			}finally {
				if (tx.isActive()) {
					tx.rollback();
				}
			}
		}
		
	}
}
