package logica.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.datatypes.DTVuelo;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

public class ConsultaVuelosTest {
	private Fabrica fab = Fabrica.getInstancia();
    private IControladorVuelos controlVue = fab.getControladorVuelos();
	
	@BeforeEach
	void setUp() {
		CargadorDatos.cargarDatos();
		controlVue.listarAllDTVuelos();
	}
	
	@Test
	public void consultarVuelo() throws InstanciaNoExiste {
		String nombre = "IB6012272";
		DTVuelo dtVue = controlVue.mostrarInfoVuelo(nombre);
		String duracionEsperada = LocalTime.of(11, 31).toString();
		boolean verificacion = (dtVue.getNombre().equals(nombre) && 
				dtVue.getDuracionStringDT().equals(duracionEsperada) && 
				dtVue.getAsientosMaxEjecutivo() == 19 &&
				dtVue.getAsientosMaxTurista() == 269);
		assertTrue(verificacion, "Los datos del Datatype son correctos");
	}
	
	@Test
	public void consultarVueloNoExiste() throws InstanciaNoExiste {
		String nombre = "Vuelo No Existe";
		assertThrows(InstanciaNoExiste.class, () -> {
			controlVue.mostrarInfoVuelo(nombre);
		}, "Debería lanzarse una excepción de InstanciaRepetida al intentar agregar un vuelo con el mismo nombre");
	}
	
	@Test
	public void listarVuelosRutas() throws InstanciaNoExiste {
		controlVue.listarAllRutas();
		String rutaSelect = "IB6012";
		Set<String> res = controlVue.listarVuelosDeRuta(rutaSelect);
		Set<String> esperado = new HashSet<>();
		esperado.add("IB6012272");
		esperado.add("IB6012377");
		esperado.add("IB60124102");
		esperado.add("IB60125114");
		assertEquals(esperado, res);
	}
	
	@Test
	public void listarVuelosRutasNoExiste() {
		String nombre = "Ruta No Existe";
		assertThrows(InstanciaNoExiste.class, () -> {
			controlVue.listarVuelosDeRuta(nombre);
		}, "Debería lanzarse una excepción de InstanciaRepetida al intentar agregar un vuelo con el mismo nombre");
	}
	@Test
	public void consultarVueloPropio() throws InstanciaNoExiste {
		String nombre = "IB6012272";
		DTVuelo dtVue = controlVue.mostrarInfoVuelo(nombre);
		String duracionEsperada = LocalTime.of(11, 31).toString();
		boolean verificacion = (dtVue.getNombre().equals(nombre) && 
				dtVue.getDuracionStringDT().equals(duracionEsperada) && 
				dtVue.getAsientosMaxEjecutivo() == 19 &&
				dtVue.getAsientosMaxTurista() == 269);
		assertTrue(verificacion, "Los datos del Datatype son correctos");
	}
}
