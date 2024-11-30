package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraRuta {
	private int cantidadRestante;
	private String ruta;
	private Asiento asiento;
	private DTPaquete paquete;
	public DTCompraRuta(int cantidadRestante, String ruta, Asiento asiento, DTPaquete paquete) {
		super();
		this.cantidadRestante = cantidadRestante;
		this.ruta = ruta;
		this.asiento = asiento;
		this.paquete = paquete;
	}
	public int getCantidadRestante() {
		return cantidadRestante;
	}
	public String getRuta() {
		return ruta;
	}
	public Asiento getAsiento() {
		return asiento;
	}
	public DTPaquete getPaquete() {
		return paquete;
	}
	
	
}
