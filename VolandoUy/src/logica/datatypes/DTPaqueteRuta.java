package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaqueteRuta {
	private int cantidad;
	private Asiento tipoAsiento;
	private String ruta;
	private String imagenRuta;
	public DTPaqueteRuta(int cantidad, Asiento tipoAsiento, String ruta, String imagen) {
		super();
		this.cantidad = cantidad;
		this.tipoAsiento = tipoAsiento;
		this.ruta = ruta;
		this.imagenRuta = imagen;
	}
	public int getCantidad() {
		return cantidad;
	}
	public Asiento getTipoAsiento() {
		return tipoAsiento;
	}
	public String getRuta() {
		return ruta;
	}	
	public String getImagenRuta() {
		return imagenRuta;
	}
	public String toString() {
		return ruta+" "+tipoAsiento+" "+cantidad;
	}
}
