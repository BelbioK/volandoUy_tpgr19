package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.conceptos.Cliente;
import logica.conceptos.CompraPaquete;
import logica.conceptos.Paquete;
import logica.controladores.Fabrica;
import logica.controladores.ManejadorPaquete;
import logica.controladores.ManejadorUsuario;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import persistencia.CargadorDatos;

public class CompraPaqueteTest {
	@BeforeEach
	void setUp() {
		CargadorDatos.cargarDatos();
	}
	
	@Test
	public void comprarPaquete() throws InstanciaRepetida, InstanciaNoExiste {
		setUp();
		String nombrePaquete = "Cruzar el Charco";
		String nombreCliente = "anarod87";
		Date fecha = numbersToDate(8,6,2024);
		Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(nombrePaquete, nombreCliente, fecha);
		
		Cliente cliente = ManejadorUsuario.getInstancia().getCliente(nombreCliente);
		Paquete paquete = ManejadorPaquete.getInstancia().getPaquete(nombrePaquete);
		
		CompraPaquete clicr=cliente.getPaquetes().get(nombrePaquete);
		CompraPaquete paqcr = paquete.getCompraCliente(nombreCliente);
		paqcr.aplicarDescuento(nombrePaquete, paqcr.getPaqueteDT().getTipoAsiento(), 4);;
		assertTrue(clicr.equals(paqcr));
	}
	@Test
	public void comprarPaqueteRepetido() {
		setUp();
		String nombrePaquete = "Cruzar el Charco";
		String nombreCliente = "sofi89";
		Date fecha = numbersToDate(8,6,2024);
		assertThrows(InstanciaRepetida.class, () -> {Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(nombrePaquete, nombreCliente, fecha);});
	}
	@Test
	public void comprarPaqueteNoCliente() {
		setUp();
		String nombrePaquete = "Cruzar el Charco";
		String nombreCliente = "No existe";
		Date fecha = numbersToDate(8,6,2024);
		assertThrows(InstanciaNoExiste.class, () -> {Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(nombrePaquete, nombreCliente, fecha);});
	}
	@Test
	public void comprarPaqueteNoPaquete() {
		setUp();
		String nombrePaquete = "No existe";
		String nombreCliente = "sofi89";
		Date fecha = numbersToDate(8,6,2024);
		assertThrows(InstanciaNoExiste.class, () -> {Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(nombrePaquete, nombreCliente, fecha);});
	}
	
	private Date numbersToDate(int dia, int mes, int anio) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH,mes-1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        Date fechaNueva = calendario.getTime();
        return fechaNueva;
    }
}
