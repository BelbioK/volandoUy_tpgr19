package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaPaqueteRutas {
	
	private Set<DTPaqueteRuta> paquetesRuta;

	public DTListaPaqueteRutas() {
		
	}
	
	public Set<DTPaqueteRuta> getPaquetesRuta() {
		return paquetesRuta;
	}

	public void setPaquetesRuta(Set<DTPaqueteRuta> paquetesRuta) {
		this.paquetesRuta = paquetesRuta;
	}
}