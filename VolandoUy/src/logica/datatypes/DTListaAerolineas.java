package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaAerolineas {
	
	private Set<DTAerolinea> aerolineas;

	public DTListaAerolineas() {
		
	}
	
	public Set<DTAerolinea> getAerolineas() {
		return aerolineas;
	}

	public void setAerolineas(Set<DTAerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
}