package logica.conceptos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.Asiento;
import logica.datatypes.DTCompraPaquete;
import logica.datatypes.DTCompraRuta;
import logica.datatypes.DTPaquete;




public class CompraPaquete {
	private Date fechaCompra;
	private Date vencimiento;
	private int costoAsociado;
	private Paquete paquete;
	private Cliente cliente;
	private Set<CompraRuta> rutas;
	public CompraPaquete(Paquete paquete, Cliente cliente, Date fechaCompra, Date vencimiento, int costoAsociado) {
		super();
		this.paquete = paquete;
		this.cliente = cliente;
		this.fechaCompra = fechaCompra;
		this.vencimiento = vencimiento;
		this.costoAsociado = costoAsociado;
		this.paquete.addCompra(this);
		this.cliente.addPaquete(paquete.getNombre(),this);
		this.rutas = new HashSet<CompraRuta>();
	}
	public String getNombreCliente() {
		return this.cliente.getNickname();
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public int getCostoAsociado() {
		return costoAsociado;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public void setCostoAsociado(int costoAsociado) {
		this.costoAsociado = costoAsociado;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void aplicarDescuento(String nombreRuta,Asiento asiento, int pasajes) {
		for(CompraRuta cr:rutas)
			if(cr.getNombreRuta().equals(nombreRuta) && cr.getAsiento().equals(asiento)  && cr.getCantidadRestante() <= pasajes) 
				cr.usarPaquete(pasajes);
	}
	public String getNombrePaquete() {
		return paquete == null ? "" : paquete.getNombre();
	}
	public DTCompraPaquete getDT() {
		
		Set<DTCompraRuta> compraRutas = new HashSet<DTCompraRuta>();
		for(CompraRuta cr:rutas)
			compraRutas.add(cr.getDT());
		
		return new DTCompraPaquete(paquete.getDT(),compraRutas);
	}
	public DTPaquete getPaqueteDT() {
		return paquete.getDT();
	}
	public void addRuta(CompraRuta ruta) {
		this.rutas.add(ruta);
	}
	public Set<CompraRuta> getRutas(){
		return rutas;
	}

	public boolean isActivo() {
		return this.vencimiento.getTime() > new Date().getTime();
	}
	public boolean aplicaAReserva(String ruta, Asiento asiento, int pasajes) {
		for(CompraRuta cr:rutas)
			if(cr.getNombreRuta().equals(ruta) && cr.getAsiento().equals(asiento)  && cr.getCantidadRestante() >= pasajes) 
				return true;
		
		return false;
	}
}
