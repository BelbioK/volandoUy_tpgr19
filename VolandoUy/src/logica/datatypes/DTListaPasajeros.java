package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaPasajeros {
	
	private Set<DTPasajero> pasajeros;

	public DTListaPasajeros() {
		
	}
	
	public Set<DTPasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(Set<DTPasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
}