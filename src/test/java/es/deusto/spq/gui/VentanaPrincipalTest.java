package es.deusto.spq.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.client.gui.VentanaPrincipal;
import es.deusto.spq.client.gui.VentanaRegistro;


public class VentanaPrincipalTest {
	@Mock
	VentanaRegistro vr;
	
	@Test
	public void test() {
		try {
			VentanaPrincipal vr = new VentanaPrincipal("tipo");
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	

}