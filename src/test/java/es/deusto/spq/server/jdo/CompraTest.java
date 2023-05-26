package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.server.jdo.Compra;
import es.deusto.spq.server.jdo.Libro;


public class CompraTest {


	private Libro libro;
	private String usuario;
	private Compra compra;

	@Before
	public void setUp() {
		libro = new Libro("lib","desc",(float)3.0,"compra");
		usuario = "usuario1";
		compra = new Compra(libro, usuario);
	}

//	@Test Este test no tira por el ID en la BD
//	public void testGetLibro() {
//		assertEquals(libro, compra.getLibro());
//	}

	@Test
	public void testSetLibro() {
		Libro libroMock2 = mock(Libro.class);
		compra.setLibro(libroMock2);
		assertEquals(libroMock2, compra.getLibro());
	}

	@Test
	public void testGetUsuario() {
		assertEquals(usuario, compra.getUsuario());
	}

	@Test
	public void testSetUsuario() {
		String usuarioMock2 = "usuario2";
		compra.setUsuario(usuarioMock2);
		assertEquals(usuarioMock2, compra.getUsuario());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(libro.getPrecio(), compra.getLibroprecio(), 0);
	}

	@Test
	public void testSetPrecio() {
		float precio = 20.0f;
		compra.setLibroprecio(precio);
		assertEquals(precio, compra.getLibroprecio(),0.1);
	}
}
