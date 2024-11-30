package logica.conceptos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.*;
import main.java.entidades.Reserva;



public class ReservaVuelo{
	private Date fechaReserva;
	private Asiento tipoAsiento;
	private Integer costo;
	private Integer equipajeExtra;
	private Set<Pasaje> pasajes;
	private Cliente cliente;
	private Vuelo vuelo;
	private Paquete paquete;
	
	private LocalTime horaEmbarque;
	private Date fechaCheckIn;
	
	public ReservaVuelo(Cliente cliente,Vuelo vuelo, Date fecha, Asiento asiento,int equipaje, Set<Pasaje> pasajes,Paquete paquete) {
		int countPasajeros = pasajes.size();
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.cliente.addReserva(this);
		this.vuelo.addReserva(this,asiento,countPasajeros);
		this.fechaReserva = fecha;
		this.tipoAsiento = asiento;
		this.equipajeExtra = equipaje;
		this.pasajes = pasajes;
		this.fechaCheckIn = null;
		
		this.paquete = paquete;
		int descuento = paquete == null ? 0 : paquete.getDescuento();
		
		this.costo = (countPasajeros*this.vuelo.getCostoAsiento(asiento) + equipaje*this.vuelo.getCostoEquipaje())*(100-descuento)/100;
		
		// Calcular horaEmbarque (media hora antes de la hora en fechaReserva)
	    LocalDateTime reservaDateTime = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    LocalTime embarqueTime = reservaDateTime.toLocalTime().minusMinutes(30);
	    this.horaEmbarque = embarqueTime;
	}
	
	public DTReserva getDT() {
		Set<DTPasajero> pas = new HashSet<>();
		for (Pasaje p : pasajes) pas.add(p.getDT());
		DTReserva nuevo = new DTReserva(fechaReserva, tipoAsiento, costo, pasajes.size(), equipajeExtra, pas,(DTCliente)cliente.getDTUsuarioSinReservas(), vuelo.getDTSinReservas(),paquete == null ? null : paquete.getDT(),fechaCheckIn);
		return nuevo;
	}
	public DTReserva getDTSinDTs() {
		Set<DTPasajero> pas = new HashSet<>();
		for (Pasaje p : pasajes) pas.add(p.getDT());
		DTReserva nuevo = new DTReserva(fechaReserva, 
								tipoAsiento, 
								costo, 
								pasajes.size(), 
								equipajeExtra, 
								pas,
								new DTCliente(cliente.getNickname()), 
								new DTVuelo(vuelo.getNombre()),
								paquete == null ? null : paquete.getDT(),fechaCheckIn);
		return nuevo;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public Asiento getTipoAsiento() {
		return tipoAsiento;
	}

	public Integer getCosto() {
		return costo;
	}

	public Integer getCantidadPasajes() {
		return pasajes.size();
	}

	public Integer getEquipajeExtra() {
		return equipajeExtra;
	}

	public Set<Pasaje> getPasajes() {
		return pasajes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}
	public LocalTime getHoraEmbarque() {
		return this.horaEmbarque;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public void setTipoAsiento(Asiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public void setEquipajeExtra(Integer equipajeExtra) {
		this.equipajeExtra = equipajeExtra;
	}

	public void setPasajes(Set<Pasaje> pasajes) {
		this.pasajes = pasajes;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
	public String getNombreVuelo() {
		return vuelo.getNombre();
	}
	
	public boolean isCheckIn() {
		return fechaCheckIn != null;
	}
	public void checkIn() {
		if(this.fechaCheckIn == null)
			this.fechaCheckIn = new Date();
	}
	public Reserva getDBReserva() {
		Reserva r = new Reserva();
		
		r.setCant_pasajes(pasajes.size());
		r.setCosto(costo);
		r.setFecha_alta(fechaReserva);
		r.setEquipaje_extra(equipajeExtra);
		r.setTipoAsiento(tipoAsiento.toString());
				
		return r;
	}
}