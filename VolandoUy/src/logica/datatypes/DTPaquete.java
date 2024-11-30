package logica.datatypes;

import java.util.Date;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
	private String nombre;
	private String descripcion;
	private Asiento tipoAsiento;
	private int periodoValidez;
	private int descuento;
	private int costoAsociado;
	private Boolean comprado;
	private Set<DTPaqueteRuta> rutas;
	private Date fechaAlta;
	private String imagen;
	public DTPaquete(String nombre, String descripcion, Asiento tipoAsiento, int periodoValidez, int descuento, int costoAsociado, Boolean comprado,Set<DTPaqueteRuta> rutasdt, Date fechaAlta,String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoAsiento = tipoAsiento;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.comprado = comprado;
		this.rutas = rutasdt;
		this.fechaAlta = fechaAlta;
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Asiento getTipoAsiento() {
		return tipoAsiento;
	}
	public int getPeriodoValidez() {
		return periodoValidez;
	}
	public int getDescuento() {
		return descuento;
	}
	public int getCostoAsociado() {
		return costoAsociado;
	}
	public Boolean getComprado() {
		return comprado;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public Set<DTPaqueteRuta> getRutas(){
		return rutas;
	}
	public String getImagen() {
		return imagen;
	}
}