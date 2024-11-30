package main.java.entidades;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Reservas")
public class Reserva {
	@Id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_vuelo")
	private Vuelo vuelo;
	
	@Id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	private String tipoAsiento;
	private int cant_pasajes;
	private int equipaje_extra;
	private int costo;
	private Date fecha_alta;
//	pasajeros no s[e bien qu[e quieren, tipo podemos agregar tablas?
	
	public Vuelo getVuelo() {
		return vuelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public String getTipoAsiento() {
		return tipoAsiento;
	}
	public int getCant_pasajes() {
		return cant_pasajes;
	}
	public int getEquipaje_extra() {
		return equipaje_extra;
	}
	public int getCosto() {
		return costo;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setTipoAsiento(String tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}
	public void setCant_pasajes(int cant_pasajes) {
		this.cant_pasajes = cant_pasajes;
	}
	public void setEquipaje_extra(int equipaje_extra) {
		this.equipaje_extra = equipaje_extra;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	
	
}
