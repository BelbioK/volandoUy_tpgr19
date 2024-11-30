package logica.conceptos;

import logica.datatypes.DTPasajero;

public class Pasaje{
	private String nombre;
	private String apellido;
	private int asiento;
	
	public Pasaje(String nombre, String apellido,int asiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.asiento = asiento;
	}
	
	public Pasaje(DTPasajero pass) {
		this.nombre = pass.getNombre();
		this.apellido = pass.getApellido();
	}
	
	public DTPasajero getDT() {
		DTPasajero nuevo = new DTPasajero(nombre, apellido,asiento);
		return nuevo;
	}
}