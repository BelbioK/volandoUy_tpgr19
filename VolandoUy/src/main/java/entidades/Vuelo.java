package main.java.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Vuelos")
public class Vuelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private Date fecha;
//	private ... duracion TODO:definir tipo de duracion
	private int max_turista;
	private int max_ejecutivo;
	private Date fecha_alta;
	@ManyToOne
	@JoinColumn(name="id_ruta")
	private RutaFinalizada ruta;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public int getMax_turista() {
		return max_turista;
	}
	public int getMax_ejecutivo() {
		return max_ejecutivo;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public RutaFinalizada getRuta() {
		return ruta;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setMax_turista(int max_turista) {
		this.max_turista = max_turista;
	}
	public void setMax_ejecutivo(int max_ejecutivo) {
		this.max_ejecutivo = max_ejecutivo;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public void setRuta(RutaFinalizada ruta) {
		this.ruta = ruta;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	public void addReserva(Reserva r) {
		reservas.add(r);
	}
}
