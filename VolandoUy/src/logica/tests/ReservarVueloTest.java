package logica.tests;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.conceptos.Cliente;
import logica.conceptos.ReservaVuelo;
import logica.controladores.Fabrica;
import logica.controladores.ManejadorUsuario;
import logica.controladores.ManejadorVuelo;
import logica.datatypes.Asiento;
import logica.datatypes.DTPasajero;
import logica.datatypes.TipoDocumento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import persistencia.CargadorDatos;

public class ReservarVueloTest {
	public ReservarVueloTest() {
	}
	@BeforeEach
	void setUp() {
        CargadorDatos.cargarDatos();
        try {
			Fabrica.getInstancia().getControladorUsuario().ingresarDatosCliente("prueba", "pedro", "corr@gm.com", "ape", new Date(), "Siberia", TipoDocumento.Pasaporte, "dsadas2312","","", new Date());
		} catch (InstanciaRepetida e) {
			e.getCause();
		}
    }
	
	@Test
	public void altaReserva() throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida {
		setUp();
        
		String vuelo = "AR1381124";
		String clienteNick = "prueba";
		Asiento asiento = Asiento.Ejecutivo;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		pasajeros.add(new DTPasajero("Pedro","Gonzales"));
		pasajeros.add(new DTPasajero("Pedro","Atagualpa"));
		pasajeros.add(new DTPasajero("Pedro","Pedro"));
		Date fecha = numbersToDate(8,6,2025);
		boolean valid = false;
		ManejadorVuelo.getInstancia();
		Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
		

		
		Cliente cliente = ManejadorUsuario.getInstancia().getCliente(clienteNick);
		ReservaVuelo reserva = null;
		for(ReservaVuelo r:cliente.getReservas().values()) {
			reserva = r;
		}
		valid = reserva.getVuelo().getNombre().equals(vuelo) 
				&& reserva.getCliente().getNickname().equals(clienteNick)
				&& reserva.getPasajes().size() == pasajeros.size()
				&& reserva.getFechaReserva().getTime() == fecha.getTime();
		assertTrue(valid,"Reserva agregada");
	}
	
	@Test
	public void altaReservaClienteNoEncontrado() {
		setUp();

		String vuelo = "AR1381124";
		String clienteNick = "";
		Asiento asiento = Asiento.Ejecutivo;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		pasajeros.add(new DTPasajero("Pedro","Gonzales"));
		Date fecha = numbersToDate(8,6,2025);
		assertThrows(InstanciaNoExiste.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
        });
	}
	@Test
	public void altaReservaVueloNoEncontrado() {
		setUp();
		String vuelo = "";
		String clienteNick = "prueba";
		Asiento asiento = Asiento.Ejecutivo;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		pasajeros.add(new DTPasajero("Pedro","Gonzales"));
		Date fecha = numbersToDate(8,6,2025);
		assertThrows(InstanciaNoExiste.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
        });
	}
	@Test
	public void altaReservaSinPasajes() {
		setUp();

		String vuelo = "AR1381124";
		String clienteNick = "prueba";
		Asiento asiento = Asiento.Ejecutivo;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		Date fecha = numbersToDate(8,6,2025);
		assertThrows(DatoInvalido.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
        });
	}
	@Test
	public void altaReservaMuchosPasajes() {
		setUp();

		String vuelo = "ZL15011419";
		String clienteNick = "prueba";
		Asiento asiento = Asiento.Turista;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		pasajeros.add(new DTPasajero("Pedro","Gonzales"));
		Date fecha = numbersToDate(8,6,2025);
		assertThrows(DatoInvalido.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
        });
	}
	
	@Test
	public void altaReservaConPaquete() throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida {
		setUp();

		String vuelo = "IB60125114";
		String clienteNick = "sofi89";
		Asiento asiento = Asiento.Ejecutivo;
		int equipaje = 4;
		Set<DTPasajero> pasajeros = new HashSet<DTPasajero>();
		pasajeros.add(new DTPasajero("Pedro","Gonzales"));
		pasajeros.add(new DTPasajero("Pedro","Gonzales2"));
		Date fecha = numbersToDate(8,6,2025);
		boolean valid = false;
		
		Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, clienteNick,"", asiento, equipaje, pasajeros, fecha);
		
		
		Cliente cliente = ManejadorUsuario.getInstancia().getCliente(clienteNick);
		ReservaVuelo reserva = null;
		
		System.out.println(cliente.getReservas().size());
		Set<String> reservas = cliente.getReservas().keySet();
		System.out.println(reservas);
		for (ReservaVuelo r : cliente.getReservas().values()) {
			if(r.getVuelo().getNombre().equals(vuelo)) {	
				r.getDTSinDTs();
				reserva = r;
				System.out.println(reserva);
		}
		valid = reserva.getVuelo().getNombre().equals(vuelo) 
				&& reserva.getCliente().getNickname().equals(clienteNick)
				&& reserva.getPasajes().size() == pasajeros.size()
				&& reserva.getFechaReserva().getTime() == fecha.getTime()
				&& reserva.getCosto() == equipaje*reserva.getVuelo().getRuta().getCostoEquipaje() + pasajeros.size()*reserva.getVuelo().getRuta().getCostoEjecutivo();
		Fabrica.getInstancia().getControladorVuelos().infoReserva(vuelo, clienteNick);
		assertTrue(valid,"Reserva agregada");
			}
	}
	
	@Test
	public void checkIn() {
		setUp();

		try {
			Fabrica.getInstancia().getControladorVuelos().checkIn("", "anarod87");
		} catch (InstanciaNoExiste e) {
			try {
				Fabrica.getInstancia().getControladorVuelos().checkIn("ZL15011350", "");
			} catch (InstanciaNoExiste e1) {
				try {
					Fabrica.getInstancia().getControladorVuelos().checkIn("anarod87", "AR1380939");
				} catch (InstanciaNoExiste e2) {
					try {
						Fabrica.getInstancia().getControladorVuelos().checkIn("anarod87", "ZL15011350");
					} catch (InstanciaNoExiste e3) {
						// TODO Auto-generated catch block
					} catch (InstanciaRepetida e3) {
						fail("No debería lanzar excepción: " + e.getMessage());
					}
				} catch (InstanciaRepetida e2) {
					// TODO Auto-generated catch block
					fail("No debería lanzar excepción: " + e.getMessage());
				}
				
			} catch (InstanciaRepetida e1) {
				fail("No debería lanzar excepción: " + e.getMessage());
			}

		} catch (InstanciaRepetida e) {
			fail("No debería lanzar excepción: " + e.getMessage());
		}
	}
	
	@Test
	public void hacerPDF() throws Exception {
		Fabrica.getInstancia().getControladorVuelos().hacerPDF("anarod87", "ZL15011350", "ZL1501", "test");
	}
	
	
	private Date numbersToDate(int dia, int mes, int anio) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH,mes-1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        Date fechaNueva = calendario.getTime();
        return fechaNueva;
    }
	@Test
	public void consultaReserva() {
		assertThrows(InstanciaNoExiste.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().infoReserva("anarod", null);
        }, "Debería lanzarse una excepción de InstanciaNoExiste");
		assertThrows(InstanciaNoExiste.class, () -> {
			Fabrica.getInstancia().getControladorVuelos().infoReserva("anarod87", "jajacapo");
        }, "Debería lanzarse una excepción de InstanciaNoExiste");
	}
}
