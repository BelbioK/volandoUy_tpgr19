package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.conceptos.Usuario;
import logica.controladores.Fabrica;
import logica.datatypes.TipoDocumento;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import persistencia.CargadorDatos;

public class AltaUsuarioTest {
	private Fabrica fab = Fabrica.getInstancia();
    private IControladorUsuarios controlUsu = fab.getControladorUsuario();
	
    @BeforeEach
    void setUp() { 
		CargadorDatos.cargarDatos();
	}
	
	@Test
	public void crearCliente() throws InstanciaRepetida{
		Date fecha1 = numbersToDate(21, 2, 2003);
		String nick1 = "pepe";
		controlUsu.ingresarDatosCliente(nick1, "pepe", "@.", "Gento", fecha1, "Lituano", TipoDocumento.Cedula, "12331234","","",new Date());
		Set<String> nombres = controlUsu.listarClientes();
		Usuario usu = controlUsu.obtenerUsuario(nick1);
        boolean clienteEncontrado = nombres.stream().anyMatch(v -> v.equals(nick1)) && usu != null;
        assertTrue(clienteEncontrado, "El cliente fue agregado");
	}
	
	@Test
	public void crearAerolinea() throws InstanciaRepetida{
		String nick2 = "pepeAero";
		controlUsu.ingresarDatosAerolinea(nick2, "aero", "@.2", "water", "","","",new Date());
		Set<String> nombresAero = controlUsu.listarAerolineas();
		boolean aeroEncontrado = nombresAero.stream().anyMatch(v -> v.equals(nick2));
		assertTrue(aeroEncontrado, "La aerolinea fue agregada");
	}
	@Test
	public void crearUsuarioNickRepetido(){
		assertThrows(InstanciaRepetida.class, () -> {
			controlUsu.ingresarDatosAerolinea("latam", "aero", "@.2", "water", "","","",new Date());
        }, "Se esperaba que se lanzara InstanciaRepetida al intentar agregar una aerolinea existente.");
	}
	@Test
	public void crearUsuarioCorreoRepetido(){
		assertThrows(InstanciaRepetida.class, () -> {
			controlUsu.ingresarDatosAerolinea("latam2", "aero", "servicioalcliente@aerolineas.com.uy", "water", "","","",new Date());
        }, "Se esperaba que se lanzara InstanciaRepetida al intentar agregar una usuario con un mail ya existente en el sistema.");
	}
	
	private Date numbersToDate(int dia, int mes, int anio) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH, mes - 1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }
}
