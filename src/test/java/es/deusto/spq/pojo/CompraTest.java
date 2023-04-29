package es.deusto.spq.pojo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CompraTest {

    @Mock
    private Libro libroMock;

    private Compra compra;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compra = new Compra(libroMock, "usuario", 10.0f);
    }

    @Test
    public void testGetLibro() {
        assertEquals(libroMock, compra.getLibro());
    }

    @Test
    public void testSetLibro() {
        Libro nuevoLibro = new Libro("Libro1","Desc1",10,"Tipe1");
        compra.setLibro(nuevoLibro);
        assertEquals(nuevoLibro, compra.getLibro());
    }

    @Test
    public void testGetUsuario() {
        assertEquals("usuario", compra.getUsuario());
    }

    @Test
    public void testSetUsuario() {
        compra.setUsuario("otro_usuario");
        assertEquals("otro_usuario", compra.getUsuario());
    }

    @Test
    public void testGetPrecio() {
        assertEquals(10.0f, compra.getPrecio(), 0.0f);
    }

    @Test
    public void testSetPrecio() {
        compra.setPrecio(20.0f);
        assertEquals(20.0f, compra.getPrecio(), 0.0f);
    }
}