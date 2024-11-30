package logica.conceptos;

import logica.datatypes.Asiento;
import logica.datatypes.DTCompraRuta;

public class CompraRuta {
	private int cantidadRestante;
	private Asiento asiento;
	private CompraPaquete compra;
	private RutaDeVuelo ruta;
	
	public CompraRuta(int cantidadRestante, Asiento asiento, CompraPaquete compra, RutaDeVuelo ruta) {
		super();
		this.cantidadRestante = cantidadRestante;
		this.compra = compra;
		this.ruta = ruta;
		this.asiento = asiento;
	}
	public int getCantidadRestante() {
		return cantidadRestante;
	}
	public CompraPaquete getCompra() {
		return compra;
	}
	public Asiento getAsiento() {
		return asiento;
	}
	public RutaDeVuelo getRuta() {
		return ruta;
	}
	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}
	public void setCompra(CompraPaquete compra) {
		this.compra = compra;
	}
	public void setRuta(RutaDeVuelo ruta) {
		this.ruta = ruta;
	}
	
	public void usarPaquete(int usado) {
		this.cantidadRestante-=usado;
	}
	public String getNombreRuta() {
		return ruta.getNombre();
	}
	public DTCompraRuta getDT() {
		return new DTCompraRuta(cantidadRestante, getNombreRuta(), asiento,compra.getPaqueteDT());
	}
}
