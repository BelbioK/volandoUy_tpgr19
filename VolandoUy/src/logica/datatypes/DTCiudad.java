package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCiudad{
	private String pais;
	private String nombre;
	private String descripcion;
	private String paginaWeb;
	private String aeropuerto;
	public DTCiudad(String pais, String nombre, String descripcion, String paginaWeb, String aeropuerto) {
		this.pais = pais;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.paginaWeb = paginaWeb;
		this.aeropuerto = aeropuerto;
	}
	public String getPais() {
		return pais;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public String getResumen() {
		return this.nombre + "," + this.pais;
	}
	public String getAeropuerto() {
		return this.aeropuerto;
	}
}