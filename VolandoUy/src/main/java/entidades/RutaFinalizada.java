package main.java.entidades;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Rutas_finalizadas")
public class RutaFinalizada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	private String descripcion_corta;
	private String descripcion;
	private String hora_salida;
	private int costo_turista;
	private int costo_ejecutivo;
	private int costo_equipaje_extra;
	private String ciudad_origen;
	private String ciudad_destino;
	private Date fecha_alta;
	private Date fecha_baja;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_aerolinea")
	private Aerolinea aerolinea;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Vuelo> vuelos = new ArrayList<>();

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion_corta() {
		return descripcion_corta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCosto_turista() {
		return costo_turista;
	}

	public int getCosto_ejecutivo() {
		return costo_ejecutivo;
	}

	public int getCosto_equipaje_extra() {
		return costo_equipaje_extra;
	}

	public String getCiudad_origen() {
		return ciudad_origen;
	}

	public String getCiudad_destino() {
		return ciudad_destino;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion_corta(String descripcion_corta) {
		this.descripcion_corta = descripcion_corta;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCosto_turista(int costo_turista) {
		this.costo_turista = costo_turista;
	}

	public void setCosto_ejecutivo(int costo_ejecutivo) {
		this.costo_ejecutivo = costo_ejecutivo;
	}

	public void setCosto_equipaje_extra(int costo_equipaje_extra) {
		this.costo_equipaje_extra = costo_equipaje_extra;
	}

	public void setCiudad_origen(String ciudad_origen) {
		this.ciudad_origen = ciudad_origen;
	}

	public void setCiudad_destino(String ciudad_destino) {
		this.ciudad_destino = ciudad_destino;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}

	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public String getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(String hora_salida) {
		this.hora_salida = hora_salida;
	}
	
	
}
