package es.deusto.spq.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.client.gui.VentanaLoginN;
import es.deusto.spq.client.gui.VentanaMenuN;
import es.deusto.spq.pojo.UserData;


public class VentanaMenuNTest {
	@Mock
	UserData u;
	@Mock
	VentanaMenuN vm;
	
	@Before
	public void setUp() {
		u = new UserData();
		u.setLogin("user");
		u.setPassword("pass");
		}
	@Test
	public void test() {
		try {
			VentanaMenuN vm = new VentanaMenuN();
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	

}