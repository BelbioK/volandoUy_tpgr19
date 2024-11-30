package logica.interfaces;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;

import logica.datatypes.*;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;

public interface IControladorVuelos {
	public abstract Set<String> listarRutasDeVuelo(String nombre) throws DatoInvalido;
	
	public abstract Set<String> listarAllRutas();
	
	public abstract DTRutasDeVuelo getRutaDeVuelo(String nombre) throws InstanciaNoExiste, DatoInvalido;
	public abstract void altaVuelo(String nombre, Date fecha, Integer horasDur, Integer minutosDur,
									Integer cantidadTuristas, Integer cantidadEjecutivo, 
									Date fechaAlta, String rutaDeVuelo, String imagen) 
											throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido;
	
	public abstract void altaCategoria(String nombre) throws InstanciaRepetida ;
	
	public abstract DTVuelo mostrarInfoVuelo(String nombre) throws InstanciaNoExiste;
	
	public abstract Set<String> listarVuelosDeRuta(String nombre) throws InstanciaNoExiste;
	
	public abstract Set<DTVuelo> listarVuelosDTDeRuta(String ruta) throws InstanciaNoExiste;
	
	public abstract Set<DTVuelo> listarAllDTVuelos();
	
	public abstract void altaReserva(String vuelo,String cliente, String paquete, Asiento asiento, int equipaje,
			Set<DTPasajero> pasajeros, Date fecha) throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida;
	
	public abstract void altaCiudad(String nombre, String pais, String nombreAeropuerto, String descripcion, 
			String paginaWeb, Date fechaAlta) throws InstanciaRepetida, DatoInvalido;
	
	public abstract Set<String> listarCiudades();
	
	public abstract void altaRutaVuelo(String aerolinea, String nombre, String descripcion, LocalTime hora, 
			int costoTurista, int costoEjecutivo, int costoEquipExtra, String ciudadOrigen, String ciudadDestino, 
			Set<String> categorias, String descCorta, String imagen, String video) throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido;
	
	public abstract void agregarVisita(String nombreRuta) throws InstanciaNoExiste;
	
	public abstract Set<String> listarCategorias();
	
	public abstract void modificarEstado(String selectedRuta, Estado selectedItem, Date date) throws InstanciaNoExiste;
	
	public abstract Set<String> getRutasStatusIngresada(String nombreAerolinea) throws DatoInvalido;
	
	public abstract Set<String> getRutasStatusConfirmada(String nombreAerolinea) throws DatoInvalido;

	public abstract Set<DTRutasDeVuelo> listarDTRutasDeVuelo();
	
	public abstract DTReserva infoReserva(String vuelo,String cliente) throws InstanciaNoExiste;
	
	public abstract Set<DTRutasDeVuelo> listarRutasDeAerolinea(String aerolinea) throws InstanciaNoExiste;
	
	public abstract DTRutasDeVuelo infoRuta(String ruta) throws InstanciaNoExiste;

	public abstract void checkIn(String nombreVuelo, String nombreCliente) throws InstanciaNoExiste, InstanciaRepetida;

	public boolean tienePaquete(String rutaDeVuelo);

	public boolean vuelosPendientes(String rutaDeVuelo);

	public void hacerPDF(String usuario,String nombreVuelo,String nombreRuta, String direccion) throws Exception;

	public Cell createDetailCell(String content, boolean isHeader);

	public Cell createHeaderCell(String content);

	
}
