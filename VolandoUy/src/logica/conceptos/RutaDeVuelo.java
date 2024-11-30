package logica.conceptos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTCategoria;
import logica.datatypes.DTCiudad;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.DTVuelo;
import logica.datatypes.Estado;
import main.java.entidades.Reserva;
import main.java.entidades.RutaFinalizada;


public class RutaDeVuelo{
	private String nombre;
	private String descripcion;
	private LocalTime hora;
	private Date fechaAlta;
	private Ciudad origen;
	private Ciudad destino;
	private int costoTurista;
	private int costoEjecutivo;
	private int costoEquipaje;
	private Estado estado;
	private Aerolinea aerolinea;
	private Set<Categoria> categorias;
	private Map<String,Vuelo> vuelos = new HashMap<>();
	private String descripcionCorta;
	private String imagen;
	private String video;
	private int visitas;
	
	public RutaDeVuelo(String nombre, String descripcion, LocalTime hora, Date fechaAlta, Ciudad origen, Ciudad destino, int costoTurista,
			int costoEjecutivo, int costoEquipaje, Aerolinea aerolinea, Set<Categoria> categorias, String descCorta, String imagen, String video) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.fechaAlta = fechaAlta;
		this.origen = origen;
		this.destino = destino;
		this.costoTurista = costoTurista;
		this.costoEjecutivo = costoEjecutivo;
		this.costoEquipaje = costoEquipaje;
		this.aerolinea = aerolinea;
		this.categorias = categorias;
		
		this.estado = Estado.Ingresada;
		
		this.descripcionCorta = descCorta;
		this.imagen = imagen == null || imagen.trim().isBlank() || imagen.isEmpty() ? "RV00.png" : imagen;
		
		this.video = video;
		this.visitas = 0;
		
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public String getImagen() {
		return imagen;
	}
	public LocalTime getHora() {
		return hora;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public Ciudad getOrigen() {
		return origen;
	}
	public Ciudad getDestino() {
		return destino;
	}
	public Estado getEstado() {
		return this.estado;
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
	public Set<Categoria> getCategoria(){
		return categorias;
	}
	public String getVideo() {
		return video;
	}
	public Map<String,Vuelo> getVuelos(){
		return this.vuelos;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setHora(int hora, int minuto) {
		this.hora = LocalTime.of(hora,minuto);
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setCostoTurista(int costoTurista) {
		this.costoTurista = costoTurista;
	}
	public void setCostoEjecutivo(int costoEjecutivo) {
		this.costoEjecutivo = costoEjecutivo;
	}
	public void setCostoEquipaje(int costoEquipaje) {
		this.costoEquipaje = costoEquipaje;
	}
	public void setCategoria(Set<Categoria> categorias){
		this.categorias = categorias;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public Set<DTVuelo> getDTsVuelos(){
		Set<DTVuelo> dtsVuelos = new HashSet<>();
		for(Vuelo v:vuelos.values())
			dtsVuelos.add(v.getDT());
		return dtsVuelos;
	}
	public DTRutasDeVuelo getDTRuta() {
		Map<String, DTVuelo> aux = new HashMap<>();
		 for(Map.Entry<String, Vuelo> entry : this.vuelos.entrySet()) {
			 Vuelo vue = entry.getValue();
			 DTVuelo dtvue = vue.getDT();
			 aux.put(vue.getNombre(), dtvue);
			
		}
		DTCiudad Origen = new DTCiudad(this.origen.getPais(),this.origen.getNombre(),this.origen.getDescripcion(),this.origen.getPaginaWeb(),this.origen.getNombreAeropuerto());
		DTCiudad Destino = new DTCiudad(this.destino.getPais(),this.destino.getNombre(),this.destino.getDescripcion(),this.destino.getPaginaWeb(),this.destino.getNombreAeropuerto());
		Set<DTCategoria> dtCat = new HashSet<>();
		for(Categoria cat : this.categorias) {
			dtCat.add( new DTCategoria (cat.getNombre()));
		}
		DTRutasDeVuelo res = new DTRutasDeVuelo(this.nombre, this.descripcion, this.hora, this.fechaAlta, Origen, Destino, this.costoTurista, this.costoEjecutivo, this.costoEquipaje, aux, dtCat,this.estado, this.aerolinea.getNickname(), this.imagen, this.descripcionCorta, this.video, this.visitas);
		return res;
	}
	public DTRutasDeVuelo getDTRutaSinVuelos() {
		DTCiudad Origen = new DTCiudad(this.origen.getPais(),this.origen.getNombre(),this.origen.getDescripcion(),this.origen.getPaginaWeb(),this.origen.getNombreAeropuerto());
		DTCiudad Destino = new DTCiudad(this.destino.getPais(),this.destino.getNombre(),this.destino.getDescripcion(),this.destino.getPaginaWeb(),this.destino.getNombreAeropuerto());
		Set<DTCategoria> dtCat = new HashSet<>();
		for(Categoria cat : this.categorias) {
			dtCat.add( new DTCategoria (cat.getNombre()));
		}
		DTRutasDeVuelo res = new DTRutasDeVuelo(this.nombre, this.descripcion, this.hora, this.fechaAlta, Origen, Destino, this.costoTurista, this.costoEjecutivo, this.costoEquipaje, new HashMap<>(), dtCat,this.estado, this.aerolinea.getNickname(), this.imagen, this.descripcionCorta, this.video, this.visitas);
		return res;
	}
	
	public void agregarVuelo(Vuelo vue) {
		this.vuelos.put(vue.getNombre(), vue);
	}
	
	public void agregarVisita() {
		visitas++;
	}
	public int getVisitas() {
		return visitas;
	}
	public RutaFinalizada getRutaFinalizada(Date date) {
		RutaFinalizada rf = new RutaFinalizada();
		
		rf.setCiudad_destino(this.getDestino().getNombre());
		rf.setCiudad_origen(this.getOrigen().getNombre());
		rf.setCosto_ejecutivo(this.getCostoEjecutivo());
		rf.setCosto_equipaje_extra(this.getCostoEquipaje());
		rf.setCosto_turista(this.getCostoTurista());
		rf.setFecha_alta(this.getFechaAlta());
		rf.setFecha_baja(date == null ? new Date() : null);
		rf.setNombre(this.getNombre());
		rf.setDescripcion(this.getDescripcion());
		rf.setDescripcion_corta(this.getDescripcionCorta());
		rf.setHora_salida(this.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
		
		main.java.entidades.Aerolinea a = this.getAerolinea().getDBAerolinea();
		
		rf.setAerolinea(a);
		
		ArrayList<main.java.entidades.Vuelo> vuelosdb = new ArrayList<>();
		HashMap<String,ArrayList<Reserva>> reservasdb = new HashMap<>();
		HashMap<String,main.java.entidades.Cliente> clientesdb = new HashMap<>();
		for(Vuelo v:vuelos.values()) {
			main.java.entidades.Vuelo vuelodb = v.getDBVuelo();
			for(ReservaVuelo r:v.getReservas()) {
				String cliente = r.getCliente().getNickname();
				Reserva reservadb = r.getDBReserva();
				ArrayList<Reserva> list = reservasdb.getOrDefault(cliente, new ArrayList<>());
				list.add(reservadb);
				clientesdb.put(cliente, r.getCliente().getDBCliente());
				reservasdb.put(cliente, list);
				
				vuelodb.addReserva(reservadb);
				reservadb.setVuelo(vuelodb);
			}
			vuelosdb.add(vuelodb);
		}
		for(main.java.entidades.Cliente c:clientesdb.values()) 
			for(Reserva r : reservasdb.getOrDefault(c.getNickname(),new ArrayList<>())) {
				r.setCliente(c);
				c.addReserva(r);
			}
		
		rf.setVuelos(vuelosdb);
		
		return rf;
	}
	
}
