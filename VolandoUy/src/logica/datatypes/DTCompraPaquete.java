package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraPaquete {
	DTPaquete paquete;
	Set<DTCompraRuta> rutas;
	public DTCompraPaquete(DTPaquete paquete,Set<DTCompraRuta> compraRutas) {
		super();
		this.paquete = paquete;
		this.rutas = compraRutas;
	}
	public DTPaquete getPaquete() {
		return paquete;
	}
	public Set<DTCompraRuta> getRutas() {
		return rutas;
	}
}
