package logica.conceptos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.Asiento;
import logica.datatypes.DTReserva;
import logica.datatypes.DTVuelo;
import main.java.entidades.Reserva;

public class Vuelo{
	private String nombre;
	private Date fecha;
	private Integer horasDur;
	private Integer minutosDur;
	private int asientosMaxTurista;
	private int asientosMaxEjecutiv;
	private int asientosRestTurista;
	private int asientosRestEjecutivo;
	private Date fechaAlta;
	private RutaDeVuelo ruta;
	private Set<ReservaVuelo> reservas;
	private String imagen;
	
	public Vuelo(String nombre, Date fecha, Integer horasDur, Integer minutosDur, int asientosMaxTurista, int asientosMaxEjecutiv,
			int asientosRestTurista, int asientosRestEjecutivo, Date fechaAlta, RutaDeVuelo ruta, String imagen) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.horasDur = horasDur;
		this.minutosDur = minutosDur;
		this.asientosMaxTurista = asientosMaxTurista;
		this.asientosMaxEjecutiv = asientosMaxEjecutiv;
		this.asientosRestTurista = asientosRestTurista;
		this.asientosRestEjecutivo = asientosRestEjecutivo;
		this.fechaAlta = fechaAlta;
		this.ruta = ruta;
		this.reservas = new HashSet<>();
		this.imagen = imagen == null ? "VU00.jpg" : imagen;
	}
	public RutaDeVuelo getRuta() {
		return ruta;
	}
	public String getNombre() {
		return nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getDuracionString() {
		return String.format("%02d:%02d", this.horasDur, this.minutosDur);
	}
	public int getAsientosMaxTurista() {
		return asientosMaxTurista;
	}
	public int getAsientosMaxEjecutiv() {
		return asientosMaxEjecutiv;
	}
	public int getAsientosRestTurista() {
		return asientosRestTurista;
	}
	public int getAsientosRestEjecutivo() {
		return asientosRestEjecutivo;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public RutaDeVuelo getRutaDeVeulo() {
		return ruta;
	}
	public String getNombreRuta() {
		return this.ruta != null ? this.ruta.getNombre() : "";
	}
	public int getCostoAsiento(Asiento asiento) {
		return asiento.equals(Asiento.Ejecutivo) ? this.ruta.getCostoEjecutivo() : this.ruta.getCostoTurista();
	}
	public int getCostoEquipaje() {
		return ruta.getCostoEquipaje();
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setDuracion(Integer hora, Integer minutos) {
		this.horasDur = hora;
		this.minutosDur = minutos;
	}
	public void setAsientosMaxTurista(int asientosMaxTurista) {
		this.asientosMaxTurista = asientosMaxTurista;
	}
	public void setAsientosMaxEjecutiv(int asientosMaxEjecutiv) {
		this.asientosMaxEjecutiv = asientosMaxEjecutiv;
	}
	public void setAsientosRestTurista(int asientosRestTurista) {
		this.asientosRestTurista = asientosRestTurista;
	}
	public void setAsientosRestEjecutivo(int asientosRestEjecutivo) {
		this.asientosRestEjecutivo = asientosRestEjecutivo;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public void setRutaDeVuelo(RutaDeVuelo rut) {
		this.ruta = rut;
	}
	public void addReserva(ReservaVuelo reserva,Asiento asiento,int pasajeros) {
		this.reservas.add(reserva);
		if(asiento.equals(Asiento.Ejecutivo))
			this.asientosRestEjecutivo-=pasajeros;
		else
			this.asientosRestTurista-=pasajeros;
	}
	public int getAsientosMax(Asiento asiento) {
		return asiento.equals(Asiento.Ejecutivo) ? this.asientosMaxEjecutiv : this.asientosMaxTurista;
	}
	public int getAsientosRest(Asiento asiento) {
		return asiento.equals(Asiento.Ejecutivo) ? this.asientosRestEjecutivo : this.asientosRestTurista;
	}
	public Set<ReservaVuelo> getReservas(){
		return reservas;
	}
	public DTVuelo getDT() {
		Set<DTReserva> dtr = new HashSet<>();
		if(reservas != null) {
			for (ReservaVuelo res : reservas) {
				dtr.add(res.getDT());
			}
		}
		String aerolinea = this.ruta.getAerolinea().getNickname();

		DTVuelo nuevo = new DTVuelo(nombre, fecha, horasDur, minutosDur, asientosMaxTurista, 
				asientosMaxEjecutiv, fechaAlta, dtr, asientosRestTurista, asientosRestEjecutivo, 
				imagen, aerolinea, this.ruta.getDTRutaSinVuelos());
		return nuevo;
	}
	public DTVuelo getDTSinReservas() {
		Set<DTReserva> dtr = new HashSet<>();
		String aerolinea = this.ruta.getAerolinea().getNickname();

		DTVuelo nuevo = new DTVuelo(nombre, fecha, horasDur, minutosDur, asientosMaxTurista, 
				asientosMaxEjecutiv, fechaAlta, dtr, asientosRestTurista, asientosRestEjecutivo, 
				imagen, aerolinea, this.ruta.getDTRutaSinVuelos());
		return nuevo;
	}
	public main.java.entidades.Vuelo getDBVuelo() {
		main.java.entidades.Vuelo v = new main.java.entidades.Vuelo();
		
		v.setFecha(fecha);
		v.setFecha_alta(fechaAlta);
		v.setMax_ejecutivo(asientosMaxEjecutiv);
		v.setMax_turista(asientosMaxTurista);
		v.setNombre(nombre);
		
		return v;
	}
}
