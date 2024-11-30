package logica.conceptos;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.DTAerolinea;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.DTUsuario;
import persistencia.DBConnection;

public class Aerolinea extends Usuario{
	String descripcion;
	String link;
	Map<String, RutaDeVuelo> rutas;
	
	public Aerolinea(String nickname, String nombre, String email, String descripcion, String link,
			String password, String imagen, Date fechaAlta) {
		super(nickname, nombre, email, imagen, fechaAlta, password);
		this.descripcion = descripcion; 
		this.link = link;
		Map<String, RutaDeVuelo> ruts = new HashMap<>();
		this.rutas = ruts;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getLink() {
		return link;
	}
	public Map<String, RutaDeVuelo> getRutas(){
		return rutas;
	}
	public void agregarRutaDeVuelo(String nombreRuta, RutaDeVuelo ruta) {
		rutas.put(nombreRuta, ruta);
	}
	public DTAerolinea getDTAerolinea() {
		Map<String,DTRutasDeVuelo> dtrutas = new HashMap<>();
		for(RutaDeVuelo rut: this.rutas.values()) {
			dtrutas.put(rut.getNombre(),rut.getDTRuta());
		}
		return new DTAerolinea(this.nombre, this.nickname, this.email, this.descripcion, this.link, dtrutas, this.imagen, this.fechaAlta, this.password);
	}
	public void setLink(String link) {
		this.link = link;
	}
	public DTUsuario getDTUsuario() {
        return getDTAerolinea();
    }
	public main.java.entidades.Aerolinea getDBAerolinea() {
		 main.java.entidades.Aerolinea a = DBConnection.getInstancia().getAerolinea(getNickname());
		 if(a != null)
			 return a;
		 
		 a = new main.java.entidades.Aerolinea();
		 a.setDescripcion(this.getDescripcion());
		 a.setEmail(this.getEmail());
		 a.setNickname(this.getNickname());
		 a.setNombre(this.getNombre());
		 a.setSitio_web(this.getLink());
		 a.setTipo_usuario("aerolinea");
		 
		 return a;
	}
}
