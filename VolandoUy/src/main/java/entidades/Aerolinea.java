package main.java.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Aerolineas")
public class Aerolinea extends Usuario{
	private String descripcion;
	private String sitio_web;
	@OneToMany(cascade = CascadeType.PERSIST)
	List<RutaFinalizada> rutasFinalizadas = new ArrayList<>();
	
	public String getDescripcion() {
		return descripcion;
	}
	public String getSitio_web() {
		return sitio_web;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setSitio_web(String sitio_web) {
		this.sitio_web = sitio_web;
	}
	public void addRuta(RutaFinalizada rf) {
		rutasFinalizadas.add(rf);
	}
}
