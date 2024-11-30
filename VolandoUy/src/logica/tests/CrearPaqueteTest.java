package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.conceptos.PaqueteRuta;
import logica.controladores.Fabrica;
import logica.controladores.ManejadorPaquete;
import logica.datatypes.Asiento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaNoModificable;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorPaquetes;
import logica.interfaces.IControladorUsuarios;
import persistencia.CargadorDatos;

class CrearPaqueteTest {
	
	private Fabrica fab = Fabrica.getInstancia();
	private IControladorPaquetes controlPaq = fab.getControladorPaquetes();
	private ManejadorPaquete manPaq = ManejadorPaquete.getInstancia();
	private IControladorUsuarios controlUsu = fab.getControladorUsuario();
	
	@BeforeEach
    void setUp() throws InstanciaRepetida, DatoInvalido { 
		CargadorDatos.cargarDatos();
		controlPaq.listarPaquetesNombres();
		
		String nomPaq = "Paquete Prueba";
		if(!manPaq.verificarPaquete(nomPaq)) {
			controlPaq.ingresarPaquete(nomPaq, "Prueba", 20, 5, numbersToDate("1","1","1900"), "");
		}
		
	}
	
	@Test
	public void crearPaquete() throws InstanciaRepetida, DatoInvalido{
		String nombre = "New York";
		Date fecha = numbersToDate("11", "9", "2001");
		String descripcion = "No te pierdas una experiencia explosiva";
		Integer vali = 120;
		Integer descuento = 50;
		Integer costo = 300;
		controlPaq.ingresarPaquete(nombre, descripcion, vali, descuento, fecha, "");
		Set<String> paq = controlPaq.listarPaquetesNombres();
		boolean paqueteEncontrado = paq.stream().anyMatch(p -> p.equals(nombre));
		assertTrue(paqueteEncontrado, "El vuelo debería haber sido agregado");
	}
	
	@Test
	public void crearPaqueteSinNombre(){
		String nombre = "";
		Date fecha = numbersToDate("11", "9", "2001");
		String descripcion = "No te pierdas una experiencia explosiva";
		Integer vali = 120;
		Integer descuento = 50;
		Integer costo = 300;
		assertThrows(DatoInvalido.class, () -> {
            controlPaq.ingresarPaquete(nombre, descripcion, vali, descuento, fecha, "");
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar un paquete sin nombre.");

	}
	
	@Test
	public void crearPaqueteDiasValidezInvalido(){
		String nombre = "New York";
		Date fecha = numbersToDate("11", "9", "2001");
		String descripcion = "No te pierdas una experiencia explosiva";
		Integer vali = -1;
		Integer descuento = 50;
		Integer costo = 300;
		assertThrows(DatoInvalido.class, () -> {
            controlPaq.ingresarPaquete(nombre, descripcion, vali, descuento, fecha,  "");
        }, "Se esperaba que se lanzara DatoInvalido al intentar agregar una paquete con validez menor a 0.");
	}
	
	@Test
	public void crearPaqueteRepetido() throws InstanciaRepetida, DatoInvalido{
		String nombre = "Cruzar el Charco";
		Date fecha = numbersToDate("11", "9", "2001");
		String descripcion = "No te pierdas una experiencia explosiva";
		Integer vali = 120;
		Integer descuento = 50;
		Integer costo = 300;
		assertThrows(InstanciaRepetida.class, () -> {
            controlPaq.ingresarPaquete(nombre, descripcion, vali, descuento, fecha, "");
        }, "Se esperaba que se lanzara InstanciaRepetida al intentar crear un paquete con un mismo nombre.");
	}
	
	@Test
	public void agregarRutaPaquete() throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste, InstanciaRepetida {
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		Integer cant = 2;
		Asiento asiento = Asiento.Ejecutivo;
		controlPaq.agregarRutaAPaquete("Paquete Prueba", rutaSelect, asiento, cant);
		Set<PaqueteRuta> ruts = manPaq.getPaquete("Paquete Prueba").getRutas();
		boolean rutaEncontrada = ruts.stream().anyMatch(ruta -> ruta.getNombreRuta().equals(rutaSelect));
		assertTrue(rutaEncontrada, "La ruta debería haber sido agregado");
	}	
	@Test
	public void agregarRutaPaqueteRepetida() throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste, InstanciaRepetida {
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		Integer cant = 2;
		Asiento asiento = Asiento.Turista;
		controlPaq.agregarRutaAPaquete("Paquete Prueba", rutaSelect, asiento, cant);
		assertThrows(InstanciaRepetida.class, () -> {
			controlPaq.agregarRutaAPaquete("Paquete Prueba", rutaSelect, asiento, cant);
		});
	}	
	@Test
	public void agregarRutaPaqueteComprado() throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste, InstanciaRepetida {
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		String Cliente = "csexto";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		String Vue = "ZL15011350";
		controlUsu.listarPaquetesCompradosParaVuelo(Cliente, Vue);
		Integer cant = 2;
		Asiento asiento = Asiento.Turista;
		assertThrows(InstanciaNoModificable.class, () -> {
			controlPaq.agregarRutaAPaquete("Cruzar el Charco", rutaSelect, asiento, cant);
		});
	}	
	
	@Test
	public void agregarRutaPaquetePaqueteNoExiste() throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste{
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		Integer cant = 2;
		Asiento asiento = Asiento.Ejecutivo;
		assertThrows(InstanciaNoExiste.class, () -> {
		controlPaq.agregarRutaAPaquete("Paquete No Existe", rutaSelect, asiento, cant);
		}, "Debería lanzarse una excepción de InstanciaNoExiste al intentar agregar una ruta a un paquete inexsistente");
	}
	
	@Test
	public void agregarRutaPaqueteRutaNoExiste() throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste{
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "Ruta No Existe";
		Integer cant = 2;
		Asiento asiento = Asiento.Ejecutivo;
		assertThrows(InstanciaNoExiste.class, () -> {
			controlPaq.agregarRutaAPaquete("Paquete Prueba", rutaSelect, asiento, cant);
			}, "Debería lanzarse una excepción de InstanciaNoExiste al intentar agregar una ruta inexsistente a un paquete");
	}
	
	@Test
	public void agregarRutaPaquetenombreInvalido(){
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		Integer cant = 2;
		Asiento asiento = Asiento.Ejecutivo;
		assertThrows(DatoInvalido.class, () -> {
			controlPaq.agregarRutaAPaquete("", rutaSelect, asiento, cant);
			}, "Debería lanzarse una excepción de DatoInvalido al intentar agregar una ruta con nombre invalido");
	}
	
	@Test
	public void agregarRutaPaqueteCantidadInvalida(){
		controlPaq.listarPaquetesSinCompras();
		controlUsu.listarAerolineas();
		String aeroSelect = "copaair";
		controlUsu.listarRutas(aeroSelect);
		String rutaSelect = "CM284";
		Integer cant = -32;
		Asiento asiento = Asiento.Ejecutivo;
		assertThrows(DatoInvalido.class, () -> {
			controlPaq.agregarRutaAPaquete("Paquete Prueba", rutaSelect, asiento, cant);
			}, "Debería lanzarse una excepción de DatoInvalido al intentar agregar una ruta con nombre invalido");
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
