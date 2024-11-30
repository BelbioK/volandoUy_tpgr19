package logica.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.datatypes.DTRutasDeVuelo;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

public class AltaRutaVueloTest {
	private Fabrica fab = Fabrica.getInstancia();
    private IControladorVuelos controlVuelos = fab.getControladorVuelos();
	
    @BeforeEach
    void setUp() { 
		CargadorDatos.cargarDatos();
	}
    
    @Test
    public void agregarRutaVueloExitoso() {
        String aerolinea = "aireuropa";
        String nombre = "NuevaRuta1";
        String descripcion = "Tiempo de vuelo aproximado de 12 horas con comidas incluidas";
        LocalTime hora = LocalTime.of(12, 20); // Hora 12:20
        int costoTurista = 450;
        int costoEjecutivo = 950;
        int costoEquipExtra = 50;
        String ciudadOrigen = "Montevideo";
        String ciudadDestino = "Madrid";
        Set<String> categorias = new HashSet<>();
        categorias.add("Internacionales");
        categorias.add("Europa");

        try {
            controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","", "");
            DTRutasDeVuelo ruta = controlVuelos.getRutaDeVuelo(nombre);
            assertNotNull(ruta, "La ruta de vuelo debería haberse agregado correctamente.");
        } catch (InstanciaRepetida | InstanciaNoExiste | DatoInvalido e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void agregarRutaVueloRepetida() {
        String aerolinea = "aireuropa";
        String nombre = "NuevaRuta2";
        String descripcion = "Tiempo de vuelo aproximado de 12 horas con comidas incluidas";
        LocalTime hora = LocalTime.of(12, 20);
        int costoTurista = 450;
        int costoEjecutivo = 950;
        int costoEquipExtra = 50;
        String ciudadOrigen = "Montevideo";
        String ciudadDestino = "Madrid";
        Set<String> categorias = new HashSet<>();
        categorias.add("Internacionales");
        categorias.add("Europa");

        // Primera alta exitosa
        try {
            controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","", "");
        } catch (Exception e) {
            fail("La primera alta no debería fallar: " + e.getMessage());
        }

        // Intentar alta con el mismo nombre
        assertThrows(InstanciaRepetida.class, () -> {
            controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","","");
        }, "Se esperaba InstanciaRepetida al intentar agregar una ruta con el mismo nombre.");
    }

    
	@Test
	public void categoriaInvalida() throws InstanciaNoExiste{
		LocalTime hora = LocalTime.of(1, 1);
		Set<String> setCategoria = new HashSet<>();
		setCategoria.add("CategoriaInvalida");
		assertThrows(InstanciaNoExiste.class, ()->{
			controlVuelos.altaRutaVuelo("aero", "nombre", "descripcion", hora, 1, 1, 1, "Origen", "Destino", setCategoria,"","","");
		},  "Se esperaba que se lanzara InstanciaNoExiste al intentar pasar categorias que no estan en el sistema.");
	}
	
	@Test
	public void camposObligatorios() throws InstanciaRepetida{
		String aerolinea = "";
        String nombre = "";
        String descripcion = "";
        LocalTime hora = LocalTime.of(12, 20);
        int costoTurista = 0;
        int costoEjecutivo = 0;
        int costoEquipExtra = 0;
        String ciudadOrigen = ""; // Ciudad origen inexistente
        String ciudadDestino = "";
        Set<String> categorias = new HashSet<>();
        categorias.add("");
		assertThrows(DatoInvalido.class, ()->{
			controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","","");
	    }, "Se esperaba que se lanzara InstanciaRepetida al intentar dar de alta una ruta con nombre repetido.");
	}
	
	 @Test
    public void agregarRutaConCiudadInexistente() {
        String aerolinea = "aireuropa";
        String nombre = "NuevaRuta3";
        String descripcion = "Tiempo de vuelo aproximado de 12 horas con comidas incluidas";
        LocalTime hora = LocalTime.of(12, 20);
        int costoTurista = 450;
        int costoEjecutivo = 950;
        int costoEquipExtra = 50;
        String ciudadOrigen = "CiudadInexistente"; // Ciudad origen inexistente
        String ciudadDestino = "Madrid";
        Set<String> categorias = new HashSet<>();
        categorias.add("Internacionales");
        categorias.add("Europa");
 
        assertThrows(InstanciaNoExiste.class, () -> {
            controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","","");
        }, "Se esperaba InstanciaNoExiste al ingresar una ciudad de origen que no existe.");
    }

	
	@Test
    public void agregarRutaConDatosInvalidos() {
        String aerolinea = "Aerolinea";
        String nombre = "";
        String descripcion = "Ruta con datos inválidos";
        LocalTime hora = LocalTime.of(14, 0);
        int costoTurista = -100;
        int costoEjecutivo = 500;
        int costoEquipExtra = 50;
        String ciudadOrigen = "CiudadA";
        String ciudadDestino = "CiudadB";
        Set<String> categorias = new HashSet<>();

        assertThrows(DatoInvalido.class, () -> {
            controlVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categorias,"","","");
        }, "Se esperaba IllegalArgumentException al ingresar datos inválidos.");
    }
}
