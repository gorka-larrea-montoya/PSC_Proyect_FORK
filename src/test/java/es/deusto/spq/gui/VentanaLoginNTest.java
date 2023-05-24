package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.client.ExampleClient;
import es.deusto.spq.client.gui.VentanaLoginN;

public class VentanaLoginNTest {
	
	@Mock
	private ExampleClient exampleClient;
	
	private VentanaLoginN ventanaLogin;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		ventanaLogin = new VentanaLoginN();
	}
	
	@Test
	public void testIniciarSesion() {
		// Simulamos una entrada de usuario y contraseña
		ventanaLogin.txtUsuario.setText("usuario1");
		ventanaLogin.passContraseya.setText("contraseña1");
		
		// Simulamos un clic en el botón "Iniciar Sesión"
		ventanaLogin.btnIniciarSession.doClick();
		
		// Verificamos que se haya llamado al método correspondiente en el objeto ExampleClient
		verify(exampleClient).loginUser("usuario1", "contraseña1");
		
		// Verificamos que se haya cerrado la ventana de inicio de sesión después de iniciar sesión correctamente
		assertEquals(true, ventanaLogin.isVisible());
	}
	
	@Test
	public void testErrorIniciarSesion() {
		// Simulamos una entrada de usuario y contraseña incorrectos
		ventanaLogin.txtUsuario.setText("usuarioIncorrecto");
		ventanaLogin.passContraseya.setText("contraseñaIncorrecta");
		
		// Simulamos un clic en el botón "Iniciar Sesión"
		ventanaLogin.btnIniciarSession.doClick();
		
		// Verificamos que se haya mostrado el mensaje de error
		assertEquals(true, ventanaLogin.lblErrorContrasenya.isVisible());
		
		// Verificamos que la ventana de inicio de sesión siga abierta después de intentar iniciar sesión con datos incorrectos
		assertEquals(true, ventanaLogin.isVisible());
	}

}
