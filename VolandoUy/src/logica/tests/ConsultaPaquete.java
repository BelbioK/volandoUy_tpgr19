package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.datatypes.DTPaquete;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import persistencia.CargadorDatos;

public class ConsultaPaquete {
	@BeforeEach
	void setUp() {
		CargadorDatos.cargarDatos();
		Fabrica.getInstancia().getControladorPaquetes().listarDTPaquetes();
	}
	
	@Test
	public void consultarPaquete() throws InstanciaNoExiste, InstanciaRepetida, DatoInvalido {
		String nombre ="test";
		String descripcion = "paquete de test";
		int diasValidez = 10;
		int descuento = 56;
		Fabrica.getInstancia().getControladorPaquetes().ingresarPaquete(nombre, descripcion, diasValidez, descuento, new Date(),"");
		DTPaquete dtPaq = Fabrica.getInstancia().getControladorPaquetes().infoPaquete("test");
		assertTrue(dtPaq.getNombre().equals(nombre) && dtPaq.getDescripcion().equals(descripcion) && dtPaq.getDescuento() == descuento && dtPaq.getPeriodoValidez() == diasValidez);
	}
	@Test
	public void consultarPaqueteNoExiste() {
		assertThrows(InstanciaNoExiste.class, () -> {
			Fabrica.getInstancia().getControladorPaquetes().infoPaquete("No existe");
		});
	}
}
