package logica.controladores;

import logica.interfaces.IControladorPaquetes;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;
import persistencia.CargadorDatos;

public class Fabrica {
	private static Fabrica instancia;
	private Fabrica() {
		
	}
	public static Fabrica getInstancia() {
		if(instancia == null) {
			instancia = new Fabrica();
			CargadorDatos.cargarDatos();
		}
		
		return instancia;
	}
	public IControladorUsuarios getControladorUsuario() {
		return new ControladorUsuarios();
	}
	public IControladorPaquetes getControladorPaquetes() {
		return new ControladorPaquetes();
	}
	public IControladorVuelos getControladorVuelos() {
		return new ControladorVuelos();
	}
}
