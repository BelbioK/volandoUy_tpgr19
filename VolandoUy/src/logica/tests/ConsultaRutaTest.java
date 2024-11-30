package logica.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.datatypes.DTAerolinea;
import logica.datatypes.DTUsuario;
import logica.datatypes.Estado;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

public class ConsultaRutaTest {
	private Fabrica fab = Fabrica.getInstancia();
	private IControladorVuelos controladorVuelos = fab.getControladorVuelos();
	private IControladorUsuarios controladorUsuarios = fab.getControladorUsuario();
	
	@BeforeEach
	void setUp() throws DatoInvalido {
		CargadorDatos.cargarDatos();
		Set<DTAerolinea> aero = controladorUsuarios.listarAerolineasDT();
		for(DTAerolinea aerolinea: aero) {
			controladorVuelos.getRutasStatusIngresada(aerolinea.getNickname());
		}
		controladorVuelos.listarDTRutasDeVuelo();
	}
	
	@Test
	public void consultarRuta() {
			DTUsuario aero = null;
			try {
				aero = controladorUsuarios.mostrarInfoUsuarios("aerolineas");
			} catch (InstanciaNoExiste | DatoInvalido e) {
				fail("No debería lanzar excepción: " + e.getMessage());
			}
			try {
				controladorVuelos.listarRutasDeVuelo("aerolineas");
				controladorVuelos.getRutasStatusConfirmada("aerolineas");
			} catch (DatoInvalido e) {
				fail("No debería lanzar excepción: " + e.getMessage());
			}
			assertNotNull(aero, "El usuario no debería ser nulo después de una consulta exitosa");
	}
	@Test
	public void consultarAerolineaNoExiste() {
		assertThrows(InstanciaNoExiste.class, () -> {
            controladorUsuarios.mostrarInfoUsuarios("joacoPB");
        }, "Debería lanzarse una excepción de InstanciaNoExiste al intentar mostrar informacion de un Usuario Inexsitente");
	}
	@Test
	public void consultarRutaNoExiste() {
		assertThrows(DatoInvalido.class, () -> {
            controladorVuelos.listarRutasDeVuelo("joacoPiedrasBlancas");
        }, "Debería lanzarse una excepción de DatoInvalido al intentar listar rutas de un Usuario Inexsitente");
	}
	@Test
	public void listarVuelosDeRuta() {
		assertThrows(InstanciaNoExiste.class, () -> {
            controladorVuelos.listarVuelosDeRuta(null);
        }, "Debería lanzarse una excepción de InstanciaNoExiste al intentar listar vuelos de una ruta Inexsitente");
	}
	@Test
	public void listarRutasAerolineaNoExiste() {
		assertThrows(InstanciaNoExiste.class, () -> {
            controladorVuelos.getRutaDeVuelo("joacoooo");
        }, "Debería lanzarse una excepción de InstanciaNoExiste al intentar obtener ruta de vuelo de un ruta Inexsitente");
	}
	@Test
	public void listarRutasAerolinea() throws InstanciaNoExiste, DatoInvalido {
		Set<String> rutas = controladorVuelos.listarRutasDeVuelo("aerolineas");
		for(String ruta: rutas) {
			controladorVuelos.infoRuta(ruta);
		}
		assertNotNull(rutas, "El usuario no debería ser nulo después de una consulta exitosa");
	}
	@Test
	public void consultaRutaFinalizada() throws InstanciaNoExiste, DatoInvalido {
		CargadorDatos.cargarDatos();
		controladorVuelos.getRutaDeVuelo("ZL1501");
	}
	@Test
	public void tienePaquete() {
		assertTrue(controladorVuelos.tienePaquete("IB6012"),"Paquete no encontrado en ruta");
	}
	@Test
	public void vuelosPendientes() {
		assertTrue(controladorVuelos.vuelosPendientes("IB6012"),"Vuelos pendientes no encontrados");
	}
	@Test
	public void listarCategorias() {
		controladorVuelos.listarCategorias();
	}
	@Test
	public void finalizarRuta() throws InstanciaNoExiste {
		controladorVuelos.modificarEstado("CM283", Estado.Finalizada, new Date());
	}
	@Test
	public void finalizarRutaExtra() throws InstanciaNoExiste, DatoInvalido {
		String aero = "zfly";
		String ruta = "ZL1502";
		boolean sePuede;
		sePuede =  controladorUsuarios.esRutaPropia(aero, ruta);
		assertTrue(sePuede, "La ruta se puede cancelar");
	}
}
