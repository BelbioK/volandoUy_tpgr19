package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaCompraRutas {
	
	private Set<DTCompraRuta> comprasRutas;

	public DTListaCompraRutas() {
		
	}
	
	public Set<DTCompraRuta> getCompraRutas() {
		return comprasRutas;
	}

	public void setAerolineas(Set<DTCompraRuta> compraRutas) {
		this.comprasRutas = compraRutas;
	}
}