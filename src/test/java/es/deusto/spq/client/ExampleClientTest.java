package es.deusto.spq.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.AlquilerDTO;
import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.pojo.Usuario;


public class ExampleClientTest {

    private ExampleClient exampleClient;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;
    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Captor
    private ArgumentCaptor<Entity<UserData>> userDataEntityCaptor;
    
    @Mock
    AlquilerDTO alquiler;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exampleClient = new ExampleClient("", "");
        exampleClient.webTarget = webTarget;
    }

    @After
    public void tearDown() throws Exception {
        exampleClient = null;
    }

    @Test
    public void testGetBooksAlquiler() {
        List<LibroDTO> expectedBooks = new ArrayList<>();
        expectedBooks.add(new LibroDTO("Libro1","Desc1",10,"Tipe1"));
        expectedBooks.add(new LibroDTO("Libro2","Desc2",10,"Tipe2"));
        when(webTarget.path("librosAlquiler")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(response.readEntity(new GenericType<List<LibroDTO>>() {})).thenReturn(expectedBooks);

        List<LibroDTO> books = exampleClient.getBooksAlquiler();

        assertNotNull(books);
        assertEquals(expectedBooks.size(), books.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i).getNombre(), books.get(i).getNombre());
        }
    }
    @Test
    public void testGetBooksAlquilerError() {
        List<LibroDTO> expectedBooks = new ArrayList<>();
        expectedBooks.add(new LibroDTO("Libro1","Desc1",10,"Tipe1"));
        expectedBooks.add(new LibroDTO("Libro2","Desc2",10,"Tipe2"));
        when(webTarget.path("librosAlquiler")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.NOT_FOUND.getStatusCode());
        when(response.readEntity(new GenericType<List<LibroDTO>>() {})).thenReturn(expectedBooks);

        List<LibroDTO> books = exampleClient.getBooksAlquiler();

        assertNull(books);
    }

    @Test
    public void testGetBooksCompra() {
        List<LibroDTO> expectedBooks = new ArrayList<>();
        expectedBooks.add(new LibroDTO("Libro3","Desc3",10,"Tipe3"));
        expectedBooks.add(new LibroDTO("Libro4","Desc4",10,"Tipe4"));
        when(webTarget.path("librosCompra")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(response.readEntity(new GenericType<List<LibroDTO>>() {})).thenReturn(expectedBooks);

        List<LibroDTO> books = exampleClient.getBooksCompra();

        assertNotNull(books);
        assertEquals(expectedBooks.size(), books.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i).getNombre(), books.get(i).getNombre());
        }
    }
    @Test
    public void testGetBooksCompraEror() {
        List<LibroDTO> expectedBooks = new ArrayList<>();
        expectedBooks.add(new LibroDTO("Libro3","Desc3",10,"Tipe3"));
        expectedBooks.add(new LibroDTO("Libro4","Desc4",10,"Tipe4"));
        when(webTarget.path("librosCompra")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.NOT_ACCEPTABLE.getStatusCode());
        when(response.readEntity(new GenericType<List<LibroDTO>>() {})).thenReturn(expectedBooks);

        List<LibroDTO> books = exampleClient.getBooksCompra();

        assertNull(books);
    }

    @Test
    public void testAlquilarLibros() {
    	when(webTarget.path("alquilarLibros")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(exampleClient.alquilarLibros(new ArrayList<>(), "usu"));
        assertNotNull(alquiler);
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        
        LibroDTO libroMock = mock(LibroDTO.class);
        // Simular el comportamiento de la llamada libro.getNombre() en el objeto Mock
        when(libroMock.getNombre()).thenReturn("El Quijote");
        // Crear una lista de libros con el objeto Mock
        List<LibroDTO> libros = new ArrayList<>();
        libros.add(libroMock);
        // Llamar al método que crea una lista de alquileres a partir de la lista de libros
        List<AlquilerDTO> alquileres = new ArrayList<>();
        Usuario usuario = new Usuario("Juan", "Perez");
        for (LibroDTO libro : libros) {
            alquileres.add(new AlquilerDTO(libro.getNombre(), "usu", "fecha"));
        }
        // Verificar que se haya creado un único alquiler con el nombre del libro esperado
        assertEquals(1, alquileres.size());
        assertEquals("El Quijote", alquileres.get(0).getLibro());
    }
    
    @Test
    public void testAlquilarLibrosError() {
    	when(webTarget.path("alquilarLibros")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(exampleClient.alquilarLibros(new ArrayList<>(), "usu"));
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        
        LibroDTO libroMock = mock(LibroDTO.class);
        // Simular el comportamiento de la llamada libro.getNombre() en el objeto Mock
        when(libroMock.getNombre()).thenReturn("El Quijote");
        // Crear una lista de libros con el objeto Mock
        List<LibroDTO> libros = new ArrayList<>();
        libros.add(libroMock);
        // Llamar al método que crea una lista de alquileres a partir de la lista de libros
        List<AlquilerDTO> alquileres = new ArrayList<>();
        Usuario usuario = new Usuario("Juan", "Perez");
        for (LibroDTO libro : libros) {
            alquileres.add(new AlquilerDTO(libro.getNombre(), "usu", "fecha"));
        }
        // Verificar que se haya creado un único alquiler con el nombre del libro esperado
        assertNotEquals(2, alquileres.size());
        assertNotEquals("El Quijote q", alquileres.get(0).getLibro());
    }
    
    @Test
    public void testRegisterUser() {
        when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(exampleClient.registerUser("test-login", "passwd"));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
    
    @Test
    public void testNotRegisterUser() {
        when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(exampleClient.registerUser("test-login", "passwd"));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertNotEquals("test-login1", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertNotEquals("passwd1", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
	@Test
	public void testLogUser() {
		 when(webTarget.path("login")).thenReturn(webTarget);
		 
		 Response response = Response.ok().build();
		 when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
		 assertTrue(exampleClient.loginUser("test-login", "passwd"));
		 
		 verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
		 assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
		 assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
	}
	@Test
    public void testLogUserWithError() {
		when(webTarget.path("login")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(exampleClient.loginUser("test-login", "passwd"));
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
	@Test
    public void testcomprarLibroError() {
		when(webTarget.path("ComprarLibro")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(exampleClient.comprarLibro((long)1, "tit", "desc", (float) 10.5, "tipo", "usu"));
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
    }
	@Test
    public void testcomprarLibro() {
		when(webTarget.path("ComprarLibro")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(exampleClient.comprarLibro((long)1, "tit", "desc", (float) 10.5, "tipo", "usu"));
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
    }
}
