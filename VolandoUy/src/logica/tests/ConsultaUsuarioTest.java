package logica.tests;




import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.datatypes.DTUsuario;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

public class ConsultaUsuarioTest {
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorUsuarios controlUsu = fabrica.getControladorUsuario();
	private IControladorVuelos controlVue = fabrica.getControladorVuelos();
	
	@BeforeEach
	void setUp() {
		CargadorDatos.cargarDatos();
		controlUsu.listarUsuarios();
		controlUsu.listarUsuariosDT();
	}
	
	@Test
	public void consultaCliente() {
		String Nickname = "sofi89";
		DTUsuario actualUser = null;
		try {
			actualUser = controlUsu.mostrarInfoUsuarios(Nickname);
			controlUsu.listarReservasCliente(Nickname);
			controlUsu.litarPaquetesCliente(Nickname);
			controlUsu.getSeguidores(Nickname);
			controlUsu.getSeguidos(Nickname);
			
		} catch (InstanciaNoExiste | DatoInvalido e) {
			 fail("No debería lanzar excepción: " + e.getMessage());
		}
		
		assertNotNull(actualUser, "El cliente no debería ser nulo después de una consulta exitosa");
	}
	
	@Test
	public void consultaAerolinea() {
		String Nickname = "aireuropa";
		DTUsuario actualUser = null;
		try {
			actualUser = controlUsu.mostrarInfoUsuarios(Nickname);
			controlUsu.listarRutas(Nickname);
			controlUsu.getSeguidores(Nickname);
			controlUsu.getSeguidos(Nickname);
		} catch (InstanciaNoExiste | DatoInvalido e) {
			 fail("No debería lanzar excepción: " + e.getMessage());
		}
		assertNotNull(actualUser, "La aerolinea no debería ser nulo después de una consulta exitosa");
	}
	
	@Test
	public void consultarUsuarioNoExiste() {
		String Nickname = "YoaquimBrom";
		assertThrows(InstanciaNoExiste.class, () -> {
            controlUsu.mostrarInfoUsuarios(Nickname);
        }, "Debería lanzarse una excepción de InstanciaNoExiste al intentar mostrar informacion de un Usuario Inexsitente");
	}
	
	@Test
	public void consultaClientePropio() {
		String Nickname = "sofi89";
		DTUsuario actualUser = null;
		try {
			actualUser = controlUsu.mostrarInfoUsuarios(Nickname);
			controlUsu.listarReservasCliente(Nickname);
			controlUsu.litarPaquetesCliente(Nickname);
			controlUsu.getReservasDeUsuario(Nickname);
			
		} catch (InstanciaNoExiste | DatoInvalido e) {
			 fail("No debería lanzar excepción: " + e.getMessage());
		}
		
		assertNotNull(actualUser, "El cliente no debería ser nulo después de una consulta exitosa");
	}
	 
	@Test
	public void consultaAerolineaPropio() {
		String Nickname = "aireuropa";
		DTUsuario actualUser = null;
		try {
			actualUser = controlUsu.mostrarInfoUsuarios(Nickname);
			controlUsu.listarRutas(Nickname);
			controlUsu.getReservasDeUsuario(Nickname);
			controlVue.listarRutasDeAerolinea(Nickname);
		} catch (InstanciaNoExiste | DatoInvalido e) {
			 fail("No debería lanzar excepción: " + e.getMessage());
		}
		assertNotNull(actualUser, "La aerolinea no debería ser nulo después de una consulta exitosa");
	}
	
	@Test
	public void seguirUsu() {
		String nicknameSeguidor = "aireuropa";
		String nicknameSeguido = "sofi89";
		controlUsu.loSigo(nicknameSeguidor, nicknameSeguido);
		controlUsu.comenzarSeguir(nicknameSeguidor, nicknameSeguido);
	}
	
	@Test
	public void dejarSeguirUsu() {
		String nicknameSeguidor = "aireuropa";
		String nicknameSeguido = "sofi89";
		controlUsu.loSigo(nicknameSeguidor, nicknameSeguido);
		controlUsu.dejarDeSeguir(nicknameSeguidor, nicknameSeguido);
	}
}
