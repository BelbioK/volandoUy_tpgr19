package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaVuelos {
	
	private Set<DTVuelo> vuelos;

	public DTListaVuelos() {
		
	}
	
	public Set<DTVuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(Set<DTVuelo> vuelos) {
		this.vuelos = vuelos;
	}
}