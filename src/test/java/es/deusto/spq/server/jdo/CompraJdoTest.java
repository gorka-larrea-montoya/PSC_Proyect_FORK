package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.server.jdo.Compra;
import es.deusto.spq.server.jdo.Libro;


public class CompraJdoTest {

	@Mock
	private Libro libroMock;
	private String usuarioMock;

	private Compra compra;

	@Before
	public void setUp() {
		libroMock = mock(Libro.class);
		usuarioMock = "usuario1";
		compra = new Compra(libroMock, usuarioMock);
	}

	@Test
	public void testGetLibro() {
		assertEquals(libroMock, compra.getLibro());
	}

	@Test
	public void testSetLibro() {
		Libro libroMock2 = mock(Libro.class);
		compra.setLibro(libroMock2);
		assertEquals(libroMock2, compra.getLibro());
	}

	@Test
	public void testGetUsuario() {
		assertEquals(usuarioMock, compra.getUsuario());
	}

	@Test
	public void testSetUsuario() {
		String usuarioMock2 = "usuario2";
		compra.setUsuario(usuarioMock2);
		assertEquals(usuarioMock2, compra.getUsuario());
	}

//	@Test
//	public void testGetPrecio() {
//		assertEquals(precioMock, compra.getPrecio(), 0);
//	}
//
//	@Test
//	public void testSetPrecio() {
//		float precioMock2 = 20.0f;
//		compra.setPrecio(precioMock2);
//		assertEquals(precioMock2, compra.getPrecio(), 0);
//	}
}
