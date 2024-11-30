package logica.conceptos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.*;



public class Paquete{
	private String nombre;
	private String descripcion;
	private Asiento tipoAsiento;
	private int periodoValidez;
	private int descuento;
	private Date fechaAlta;
	private int costoAsociado;
	private Boolean comprado;
	private Set<PaqueteRuta> rutas;
	private Set<CompraPaquete> compras;
	private String imagen;
	
	public Paquete(String nombre, String descripcion,  int periodoValidez, int descuento, Date fechaAlta, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoAsiento = null;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.comprado = false;
		this.rutas = new HashSet<>();
		this.compras = new HashSet<>();
		this.costoAsociado = 0;
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public String getImagen() {
		return imagen;
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
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setTipoAsiento(Asiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}
	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public void setCostoAsociado(int costoAsociado) {
		this.costoAsociado = costoAsociado;
	}
	public void setComprado(Boolean comprado) {
		this.comprado = comprado;
	}
	public void addPaqueteRuta(PaqueteRuta paqRuta) {
		if(this.comprado)
			return;
		rutas.add(paqRuta);
		this.costoAsociado+=(int) Math.round(((double)(100-this.descuento)/100)*paqRuta.getCosto());	
	}
	public void addCompra(CompraPaquete compra) {
		this.compras.add(compra);
		if(this.compras.size() == 1)
			this.comprado = true;
	}
	public DTPaquete getDT() {
		Set<DTPaqueteRuta> rutasdt = new HashSet<>();
		for (PaqueteRuta ruta : rutas) {
		   rutasdt.add(ruta.getDT());
		}
		return new DTPaquete(nombre, descripcion, tipoAsiento, periodoValidez, descuento, costoAsociado, comprado, rutasdt, fechaAlta, imagen);
	}
	
	public Set<PaqueteRuta> getRutas(){
		return rutas;
	}
	
	public Set<CompraPaquete> getCompras(){
		return compras;
	}
		
	public CompraPaquete getCompraCliente(String cliente) {
		for(CompraPaquete cr:compras)
			if(cr.getNombreCliente().equals(cliente))
				return cr;
		
		return null;
	}
	
	public boolean tieneRuta(String ruta, Asiento asiento) {
		return rutas.stream().anyMatch(r -> r.getNombreRuta().equals(ruta) && r.getTipoAsiento().equals(asiento));
	}
	public boolean rutaEstaEnPaquete(String ruta) {
		return rutas.stream().anyMatch(r -> r.getNombreRuta().equals(ruta));
	}
}