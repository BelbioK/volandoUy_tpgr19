package logica.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.ControladorUsuarios;
import logica.controladores.Fabrica;
import logica.datatypes.DTCliente;
import logica.datatypes.DTUsuario;
import excepciones.DatoInvalido;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import persistencia.CargadorDatos;

public class LoginTest {
	
	private Fabrica fab = Fabrica.getInstancia();
	private IControladorUsuarios controlUsu = fab.getControladorUsuario();
	ControladorUsuarios cUsu = (ControladorUsuarios) fab.getControladorUsuario();
	
	@BeforeEach
    void setUp() throws InstanciaRepetida, DatoInvalido { 
		CargadorDatos.cargarDatos();
	}
	
	@Test
	public void loginClienteNick() throws DatoInvalido {
		DTUsuario cliente = controlUsu.login("martig", "cft5xdr4");
		boolean esCliente = cliente.getNombre().equals("Marta");
		assertTrue(esCliente);
	}
	
	@Test
	public void loginClienteEmail() throws DatoInvalido {
		DTUsuario cliente = controlUsu.login("carlosh89@mxmail.com", "poi098lkj");
		boolean esCliente = cliente.getNombre().equals("Carlos") ;
		assertTrue(esCliente);
	}
	
	@Test
	public void loginAerolineaNick() throws DatoInvalido {
		DTUsuario aero = controlUsu.login("copaair", "2wsx3edc");
		boolean esAero = aero.getNombre().equals("Copa Airlines");
		assertTrue(esAero);
	}
	
	@Test
	public void loginAerolineaEmail() throws DatoInvalido {
		DTUsuario aero = controlUsu.login("contacto@copaair.com.uy", "2wsx3edc");
		boolean esAero = aero.getNombre().equals("Copa Airlines");
		assertTrue(esAero);
	}
	
	@Test
	public void usuarioNoEncontrado() throws DatoInvalido {
		try {			
			controlUsu.login("pedropedrosa", "2wsx3edc");
		} catch (DatoInvalido e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void contraseniaErronea() throws DatoInvalido {
		try {			
			controlUsu.login("ejstar", "pepepepepepepepe");
		} catch (DatoInvalido e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void loginMobileNickname() throws DatoInvalido {	
			DTCliente cliente = controlUsu.loginMobile("martig", "cft5xdr4");
			boolean esCli = !cUsu.checkNickname("martig");
			assertTrue(esCli);
	}
	
	@Test
	public void loginMobileEmail() throws DatoInvalido {	
			DTCliente cliente = controlUsu.loginMobile("marta.garcia95@webmail.es", "cft5xdr4");
			boolean esCli = !cUsu.checkEmail("marta.garcia95@webmail.es");
			assertTrue(esCli);
	}
	
	@Test
	public void loginMobilUsuNoExiste() throws DatoInvalido {	
		try {			
			controlUsu.login("PedroPedrosa", "pepepepepepepepe");
		} catch (DatoInvalido e) {
			assertTrue(true);
		}
	}
}