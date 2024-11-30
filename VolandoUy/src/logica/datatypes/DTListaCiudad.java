package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaCiudad {
	
	private Set<DTCiudad> ciudades;

	public DTListaCiudad() {
		
	}
	
	public Set<DTCiudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(Set<DTCiudad> ciudades) {
		this.ciudades = ciudades;
	}
}