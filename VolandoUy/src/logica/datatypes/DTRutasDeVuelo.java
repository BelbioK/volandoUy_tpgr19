package logica.datatypes;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTRutasDeVuelo {
	private String nombre;
	private String descripcion;
	private String descripcionCorta;
	private LocalTime hora;
	private String horaString;
	private Date fechaAlta;
	private DTCiudad origen;
	private DTCiudad destino;
	private int costoTurista;
	private int costoEjecutivo;
	private int costoEquipaje;
	private Map<String, DTVuelo> vuelos;
	private Set<DTVuelo> vuelosSet;
	private Set<DTCategoria> categorias;
	private Estado estado;
	private String aerolinea;
	private String imagen;
	private String video;
	private int visitas;
	
	public DTRutasDeVuelo(String nombre, String descripcion, LocalTime hora, Date fechaAlta, 
			DTCiudad origen, DTCiudad destino, int costoTurista, int costoEjecutivo, int costoEquipaje, 
			Map<String,DTVuelo> vuelos, Set<DTCategoria> categorias,Estado estado, String aerolinea, String imagen, String descripcionCorta, String video, int visitas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.horaString = hora.toString();
		this.fechaAlta = fechaAlta;
		this.origen = origen;
		this.destino = destino;
		this.costoTurista = costoTurista;
		this.costoEjecutivo = costoEjecutivo;
		this.costoEquipaje = costoEquipaje;
		this.vuelos = vuelos;
		this.categorias = categorias;
		this.estado = estado;
		this.aerolinea = aerolinea;
		this.imagen = imagen;
		this.descripcionCorta = descripcionCorta;
		this.video = video;
		this.visitas = visitas;
		this.setVuelosSet(vuelos.values()
                				.stream()
                				.collect(Collectors.toSet()));
	}
	public DTRutasDeVuelo(String nombre) {
		this.nombre = nombre;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public LocalTime getHora() {
		return hora;
	}
	public String getHoraString() {
		return horaString;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public DTCiudad getOrigen() {
		return origen;
	}
	public DTCiudad getDestino() {
		return destino;
	}
	public int getCostoTurista() {
		return costoTurista;
	}
	public int getCostoEjecutivo() {
		return costoEjecutivo;
	}
	public int getCostoEquipaje() {
		return costoEquipaje;
	}
	public Set<DTCategoria> getCategoria() {
		return categorias;
	}
	public Map<String, DTVuelo> getVuelos() {
		return vuelos;
	}
	public Estado getEstado() {
		return estado;
	}
	public String getImagen() {
		return imagen;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public Set<DTVuelo> getVuelosSet() {
		return vuelosSet;
	}
	public String getVideo() {
		return video;
	}
	public void setVuelosSet(Set<DTVuelo> vuelosSet) {
		this.vuelosSet = vuelosSet;
	}
	public int getVisitas() {
		return visitas;
	}
}	