package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaRutas {
	
	private Set<DTRutasDeVuelo> rutas;

	public DTListaRutas() {
		
	}
	
	public Set<DTRutasDeVuelo> getRutas() {
		return rutas;
	}

	public void setRutas(Set<DTRutasDeVuelo> rutas) {
		this.rutas = rutas;
	}
}