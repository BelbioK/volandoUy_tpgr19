package logica.datatypes;

import java.util.Date;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTVuelo {
	private String nombre;
	private Date fecha;
	private Integer horasDur;
	private Integer minutosDur;
	private int asientosMaxTurista;
	private int asientosMaxEjecutivo;
	private int asientosResTurista;
	private int asientosResEjecutivo;
	private Date fechaAlta;
	private Set<DTReserva> reservas;
	private String imagen;
	private String aerolinea;
	private DTRutasDeVuelo ruta;
	private String horaString;
	private String minutoString;
	
	public DTVuelo(String nombre) {
		this.nombre = nombre;
		this.imagen = "VU00.jpg";
	}
	public DTVuelo(String nombre, Date fecha, Integer horasDur, Integer minutosDur, int asientosMaxTurista, 
			int asientosMaxEjecutivo, Date fechaAlta, Set<DTReserva> reservas, int asientosResTurista, 
			int asientosResEjecutivo, String imagen, String aero, DTRutasDeVuelo ruta) {
		this.nombre = nombre;

		this.fecha = fecha;
		this.horasDur = horasDur;
		this.minutosDur = minutosDur;
		this.asientosMaxTurista = asientosMaxTurista;
		this.asientosMaxEjecutivo = asientosMaxEjecutivo;
		this.fechaAlta = fechaAlta;
		this.reservas = reservas;
		this.asientosResEjecutivo = asientosResEjecutivo;
		this.asientosResTurista = asientosResTurista;
		this.imagen = imagen;
		this.ruta = ruta;
		this.aerolinea = aero;
		this.horaString = horasDur.toString();
		this.minutoString = minutosDur.toString();
	}
	
	public String getNombre() {
		return nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getDuracionStringDT() {
		return String.format("%02d:%02d", this.horasDur, this.minutosDur);	
	}
	public int getAsientosMaxTurista() {
		return asientosMaxTurista;
	}
	public int getAsientosMaxEjecutivo() {
		return asientosMaxEjecutivo;
	}
	public int getAsientosRestantesTurista() {
		return asientosResTurista;
	}
	public int getAsientosRestantesEjecutivo() {
		return asientosResEjecutivo;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public Set<DTReserva> getReservas(){
		return reservas;
	}
	public String getImagen() {
		return imagen;
	}
	public String getNickAerolinea() {
		return aerolinea;
	}
	public DTRutasDeVuelo getRuta() {
		return ruta;
	}
	public String getNombreRuta() {
		return ruta.getNombre();
	}
	
	
}
