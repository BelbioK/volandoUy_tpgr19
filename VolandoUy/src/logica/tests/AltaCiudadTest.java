package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import excepciones.DatoInvalido;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

class AltaCiudadTest {
    private Fabrica fab = Fabrica.getInstancia();
    private IControladorVuelos controlVue = fab.getControladorVuelos();

    @BeforeEach
    void setUp() {
        CargadorDatos.cargarDatos();
    }
    
    @Test
    public void nuevaCiudadConWeb() throws InstanciaRepetida, DatoInvalido {
        Date fecha = numbersToDate(21, 2, 2003);
        String nombre = "Verona";
        controlVue.altaCiudad(nombre, "Italia", "Bentegodi", "Romeo y Julieta", "verona.com.it", fecha);
        Set<String> ciudades = controlVue.listarCiudades();
        boolean ciudadEncontrada = ciudades.stream().anyMatch(v -> v.equals(nombre));
        assertTrue(ciudadEncontrada, "La ciudad fue agregada");
    }
    
    @Test
    public void nuevaCiudadSinWeb() throws InstanciaRepetida, DatoInvalido {
            Date fecha = numbersToDate(21, 2, 2003);
            String nombre = "La plata";
            controlVue.altaCiudad(nombre, "Argentina", "Bentegodi", "Romeo y Julieta", null, fecha);
            Set<String> ciudades = controlVue.listarCiudades();
            boolean ciudadEncontrada = ciudades.stream().anyMatch(v -> v.equals(nombre));
            assertTrue(ciudadEncontrada, "La ciudad fue agregada");
            
            Date fecha2 = numbersToDate(21, 2, 2003);
            String nombre2 = "CDM";
            controlVue.altaCiudad(nombre2, "Mexico", "Bentegodi", "Romeo y Julieta", "", fecha2);
            Set<String> ciudades2 = controlVue.listarCiudades();
            boolean ciudadEncontrada2 = ciudades2.stream().anyMatch(v -> v.equals(nombre));
            assertTrue(ciudadEncontrada2, "La ciudad fue agregada");
    }

    @Test
    void agregarCiudadRepetida() {
        Date fecha = numbersToDate(21, 2, 2003);
        String nombre = "Montevideo";
        assertThrows(InstanciaRepetida.class, () -> {
            controlVue.altaCiudad(nombre, "Uruguay", "Estadio Centenario", "Fútbol", "montevideo.com.uy", fecha);
        }, "Se esperaba que se lanzara InstanciaRepetida al intentar agregar una ciudad existente.");
    }
    
    
    @Test
    void agregarCiudadSinNombre() {
        Date fecha = numbersToDate(21, 2, 2003);
        String nombre = "";
        assertThrows(DatoInvalido.class, () -> {
            controlVue.altaCiudad(nombre, "Uruguay", "Estadio Centenario", "Fútbol", "montevideo.com.uy", fecha);
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar una ciudad sin nombre.");
    }
    
    @Test
    void agregarCiudadSinDescripcion() {
    	Date fecha = numbersToDate(21, 2, 2003);
        assertThrows(NullPointerException.class, () -> {
            controlVue.altaCiudad("Valencia", "Uruguay", "Estadio Centenario", null, "montevideo.com.uy", fecha);
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar una ciudad sin nombre.");
    }

    private Date numbersToDate(int dia, int mes, int anio) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH, mes - 1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }
}
