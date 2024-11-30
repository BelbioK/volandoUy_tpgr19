package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.controladores.ManejadorUsuario;
import logica.controladores.ManejadorVuelo;
import logica.datatypes.DTVuelo;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;


class AltaVueloTest {
	private IControladorVuelos controlVue;
	private IControladorUsuarios controlUsu;
	private ManejadorVuelo manVue = ManejadorVuelo.getInstancia();
	private ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
	
	@BeforeEach
	void setUp() throws Exception {
        Fabrica fabrica = Fabrica.getInstancia();
        controlVue = fabrica.getControladorVuelos();
        controlUsu = fabrica.getControladorUsuario();
        LocalTime horaRuta = LocalTime.of(3, 6);
        Set<String> cate = new HashSet<>();

        if(!manUsu.verificarAerolinea("AeroBicho")) {
        	controlUsu.ingresarDatosAerolinea("AeroBicho", "El GOAT", "vuevos@.", "Siuuuuuuu", "cr7.com","","",new Date());
        }
        if (!manVue.verificarRuta("CR7 GOAT")) {
            controlVue.altaRutaVuelo("AeroBicho", "CR7 GOAT", "Messi Ladron", horaRuta, 300, 500, 100, "Montevideo", "Buenos Aires", cate,"","","");
        }
	}
	
	@Test
	void nuevoVuelo() throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
		String nom = "Matenme pls";
    	Date fecha = numbersToDate("10", "6", "2025");
    	Date fechaAlta = numbersToDate("1", "1", "2024");
    	Integer hora = 1;
    	Integer min = 50;
    	Integer cantTuristas = 100; 
    	Integer cantEjecutivos = 150;
    	String rutaVuelo = "CR7 GOAT";
    	controlVue.altaVuelo(nom, fecha, hora, min, cantTuristas, cantEjecutivos, fechaAlta, rutaVuelo,"");
    	Set<DTVuelo> vue = controlVue.listarVuelosDTDeRuta(rutaVuelo);
    	boolean vueloEncontrado = vue.stream().anyMatch(v -> v.getNombre().equals(nom));
    	assertTrue(vueloEncontrado, "El vuelo debería haber sido agregado");
	}
	
	@Test
	void vueloExcepcionRepetida() throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
		String nombreV = "Siu2";
    	Date fechaV = numbersToDate("10", "6", "2025");
    	Date fechaAltaV = numbersToDate("1", "1", "2024");
    	Integer hora = 1;
    	Integer min = 50;
    	Integer cantTuristasV = 100; 
    	Integer cantEjecutivosV = 150;
    	String rutaVueloV = "CR7 GOAT";
    	controlVue.altaVuelo(nombreV, fechaV, hora, min, cantTuristasV, cantEjecutivosV, fechaAltaV, rutaVueloV,"");
    	assertThrows(InstanciaRepetida.class, () -> {
            controlVue.altaVuelo(nombreV, fechaV, hora, min, cantTuristasV, cantEjecutivosV, fechaAltaV, rutaVueloV,"");
        }, "Debería lanzarse una excepción de InstanciaRepetida al intentar agregar un vuelo con el mismo nombre");
	}
	
	@Test 
	void vueloExcepcionNoExiste() throws InstanciaRepetida, InstanciaNoExiste {
		String nombreV = "Siu3";
    	Date fechaV = numbersToDate("10", "6", "2025");
    	Date fechaAltaV = numbersToDate("1", "1", "2024");
    	Integer hora = 1;
    	Integer min = 50;
    	Integer cantTuristasV = 100; 
    	Integer cantEjecutivosV = 150;
    	String rutaVueloV = "Vuevos";
    	assertThrows(InstanciaNoExiste.class, () -> {
            controlVue.altaVuelo(nombreV, fechaV, hora, min, cantTuristasV, cantEjecutivosV, fechaAltaV, rutaVueloV,"");
        }, "Debería lanzarse una excepción de InstanciaNoExiste al intentar agregar un vuelo con ruta de vuelo sin Ruta de Vuelo existente");
	}
	
	@Test 
	void vueloNombreInvalido() throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido{
		String nombreV = "";
    	Date fechaV = numbersToDate("10", "6", "2025");
    	Date fechaAltaV = numbersToDate("1", "1", "2024");
    	Integer hora = 1;
    	Integer min = 50;
    	Integer cantTuristasV = 100; 
    	Integer cantEjecutivosV = 150;
    	String rutaVueloV = "CR7 GOAT";
    	assertThrows(DatoInvalido.class, () -> {
            controlVue.altaVuelo(nombreV, fechaV, hora, min, cantTuristasV, cantEjecutivosV, fechaAltaV, rutaVueloV,"");
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar una ciudad sin nombre.");
	}
	
	@Test
	void vuelocantidadInvalidos () {
		String nombreV = "Siu4";
    	Date fechaV = numbersToDate("10", "6", "2025");
    	Date fechaAltaV = numbersToDate("1", "1", "2024");
    	Integer hora = 1;
    	Integer min = 50;
    	Integer cantTuristasV = -500; 
    	Integer cantEjecutivosV = 150;
    	String rutaVueloV = "CR7 GOAT";
    	assertThrows(DatoInvalido.class, () -> {
            controlVue.altaVuelo(nombreV, fechaV, hora, min, cantTuristasV, cantEjecutivosV, fechaAltaV, rutaVueloV,"");
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar una ciudad sin nombre.");
	}
	
	
	private Date numbersToDate(String dia_s, String mes_s, String anio_s) {
        int dia = Integer.parseInt(dia_s);
        int mes = Integer.parseInt(mes_s);
        int anio = Integer.parseInt(anio_s);
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH,mes-1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        Date fechaNueva = calendario.getTime();
        return fechaNueva;
    }
}