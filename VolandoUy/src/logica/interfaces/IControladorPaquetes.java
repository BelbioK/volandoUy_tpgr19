package logica.interfaces;

import java.util.Date;
import java.util.Set;

import logica.datatypes.*;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaNoModificable;
import excepciones.InstanciaRepetida;


public interface IControladorPaquetes {
	public abstract DTPaquete infoPaquete(String nombre) throws InstanciaNoExiste;
	public abstract void ingresarPaquete(String nombre, String descripcion, int diasValidez, int descuento, Date fechaALta, String imagen) throws InstanciaRepetida, DatoInvalido;
	public abstract Set<String> listarPaquetesNombres();
	public Set<String> listarPaquetesSinCompras();
	public abstract void agregarRutaAPaquete(String nombre, String nombreRuta, Asiento asiento, int cant) throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste, InstanciaRepetida;
	public abstract void altaCompraPaquete(String nombrePaquete, String nombreCliente, Date fechaCompra) throws InstanciaRepetida, InstanciaNoExiste;
	public abstract Set<DTPaquete> listarDTPaquetes();
	public abstract Set<DTPaquete> listarPaquetesDeVuelo(String vuelo) throws InstanciaNoExiste;
}
