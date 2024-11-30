package logica.conceptos;

import java.util.Date;

public class Ciudad{
	private String pais;
	private String nombre;
	private String descripcion;
	private String paginaWeb;
	private String nombreAeropuerto;
	private Date fechaAlta;
	public Ciudad(String nombre, String pais, String nombreAeropuerto, String descripcion, String paginaWeb, Date fechaAlta) {
		this.pais = pais;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.paginaWeb = paginaWeb;
		this.nombreAeropuerto = nombreAeropuerto;
		this.fechaAlta = fechaAlta;
	}
	public String getPais() {
		return pais;
	}
	public String getNombre() {
		return nombre;
	}
	public String getNombreAeropuerto() {
		return nombreAeropuerto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public String getResumen() {
		return this.nombre + "," + this.pais;
	}
}