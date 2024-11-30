package logica.datatypes;

import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTBusquedaRutaYPaquete {
	Set<DTRutasDeVuelo> rutas = new HashSet<>();
	Set<DTPaquete> paquete = new HashSet<>();
	public DTBusquedaRutaYPaquete() {
		
	}
	public Set<DTRutasDeVuelo> getRutas() {
		return rutas;
	}
	public Set<DTPaquete> getPaquete() {
		return paquete;
	}
	public void setRutas(Set<DTRutasDeVuelo> rutas) {
		this.rutas = rutas;
	}
	public void setPaquete(Set<DTPaquete> paquete) {
		this.paquete = paquete;
	}
	
	
}
