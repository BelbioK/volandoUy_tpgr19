package logica.datatypes;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTAerolinea extends DTUsuario{
	private String descripcion;
	private String link;
	private Map<String, DTRutasDeVuelo> rutas;
	private Set<DTRutasDeVuelo> rutasSet;
	
	public DTAerolinea(String nombre, String nickname, String email, String descripcion, String link, Map<String, DTRutasDeVuelo> rutas, String imagen, Date fechaAlta, String password) {
		super(nombre, nickname, email, imagen, fechaAlta, password);
		this.descripcion = descripcion;
		this.link = link;
		this.rutas = rutas;
		this.setRutasSet(rutas.values()
								.stream()
								.collect(Collectors.toSet()));
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getLink() {
		return link;
	}	
	public Map<String, DTRutasDeVuelo> getRutas(){
		return rutas;
	}
	public Set<DTRutasDeVuelo> getRutasSet() {
		return rutasSet;
	}
	public void setRutasSet(Set<DTRutasDeVuelo> rutasSet) {
		this.rutasSet = rutasSet;
	}
}
