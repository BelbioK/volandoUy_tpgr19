package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaPaquetes {
	
	private Set<DTPaquete> paquetes;

	public DTListaPaquetes() {
		
	}
	
	public Set<DTPaquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Set<DTPaquete> paquetes) {
		this.paquetes = paquetes;
	}
}