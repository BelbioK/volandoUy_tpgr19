package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaReservas {
	
	private Set<DTReserva> reservas;

	public DTListaReservas() {
		
	}
	
	public Set<DTReserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<DTReserva> reservas) {
		this.reservas = reservas;
	}
}