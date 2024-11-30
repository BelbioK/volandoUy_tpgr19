package logica.interfaces;

import java.util.Set;

import logica.conceptos.Usuario;
import logica.datatypes.DTAerolinea;
import logica.datatypes.DTCliente;
import logica.datatypes.DTCompraRuta;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTReserva;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoDocumento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;

import java.util.Date;


public interface IControladorUsuarios {
	public abstract Set<String> listarAerolineas();
	public abstract Set<String> listarUsuarios();
	public abstract Set<String> listarClientes();
	public abstract DTUsuario mostrarInfoUsuarios(String nicknamne) throws InstanciaNoExiste, DatoInvalido;
	public abstract Set<DTUsuario> listarUsuariosDT();
	public abstract Set<DTRutasDeVuelo> listarRutas(String nickname);
	//public abstract Set<DTReserva> listarReservasCliente(String nickname);
	public abstract Set<DTPaquete> litarPaquetesCliente(String nickname);
	public abstract void ingresarDatosCliente(String nickname, String nombre, String correo, String apellido,
			Date fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, String numDocumento, 
			String password, String imagen, Date fechaAlta) throws InstanciaRepetida;
	public abstract void ingresarDatosAerolinea(String nickname, String nombre, String correo, String descripcion, 
			String link, String password, String imagen, Date fechaAlta) throws InstanciaRepetida;
	public abstract Set<DTReserva> listarReservasCliente(String nickname) throws InstanciaNoExiste;
	public abstract Usuario obtenerUsuario(String nickname); //TODO: no se deberia exponer la clase Usuario
	public abstract void modificarCliente(String nicknameOriginal, String password, String nombre, String apellido, Date fechaNacimiento, 
			String nacionalidad, TipoDocumento tipoDocumento, String numDocumento) throws DatoInvalido, InstanciaNoExiste;
	public abstract void modificarAerolinea(String nicknameOriginal, String password, String nombre, String descripcion, String link)throws DatoInvalido, InstanciaNoExiste;
	public abstract DTUsuario login(String key,String password) throws DatoInvalido;
	public abstract DTCliente loginMobile(String key, String password) throws DatoInvalido;
	public abstract Set<DTReserva> getReservasDeUsuario(String nickname) throws InstanciaNoExiste;
	public abstract Set<DTAerolinea> listarAerolineasDT();
	public abstract Set<DTCompraRuta> listarPaquetesCompradosParaVuelo(String nickname,String vuelo) throws InstanciaNoExiste;
	void comenzarSeguir(String principal, String aSeguir);
	void dejarDeSeguir(String principal, String dejarSeguir);
	Set<DTUsuario> getSeguidores(String nickname);
	Set<DTUsuario> getSeguidos(String nickname);
	boolean loSigo(String principal, String secundario);
	boolean esRutaPropia(String nickname, String nombreRuta);
}