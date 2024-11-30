package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPasajero {
	private String nombre;
	private String apellido;
	private int asiento;
	
	public DTPasajero(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.asiento = 0;
	}
	
	public DTPasajero(String nombre, String apellido, int asiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.asiento = asiento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}	
	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}
	public int getAsiento() {
		return asiento;
	}
}
