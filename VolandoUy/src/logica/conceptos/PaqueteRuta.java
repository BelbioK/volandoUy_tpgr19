package logica.conceptos;

import logica.datatypes.Asiento;
import logica.datatypes.DTPaqueteRuta;

public class PaqueteRuta {
	private int cantidad;
	private Asiento tipoAsiento;
	private Paquete paquete;
	private RutaDeVuelo ruta;
	
	public PaqueteRuta(int cantidad, Asiento tipoAsiento, Paquete paquete, RutaDeVuelo ruta) {
		super();
		this.cantidad = cantidad;
		this.tipoAsiento = tipoAsiento;
		this.paquete = paquete;
		this.ruta = ruta;
	}
	public int getCantidad() {
		return cantidad;
	}
	public Asiento getTipoAsiento() {
		return tipoAsiento;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public RutaDeVuelo getRuta() {
		return ruta;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setTipoAsiento(Asiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	public void setRuta(RutaDeVuelo ruta) {
		this.ruta = ruta;
	}
	public String getNombreRuta() {
		return ruta == null ? "" : ruta.getNombre();
	}
	public String getImagenRuta() {
		return ruta == null ? "USC" : ruta.getImagen();
	}
	public DTPaqueteRuta getDT() {
		return new DTPaqueteRuta(cantidad,tipoAsiento,getNombreRuta(),getImagenRuta());
	}
	public int getCosto() {
		return this.tipoAsiento.equals(Asiento.Ejecutivo) ? 
					this.cantidad*ruta.getCostoEjecutivo() 
				  : this.cantidad*ruta.getCostoTurista();
	}
}
