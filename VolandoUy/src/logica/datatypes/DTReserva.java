package logica.datatypes;

import java.util.Date;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTReserva{
	private Date fechaReserva;
	private Asiento tipoAsiento;
	private Integer costo;
	private Integer cantidadPasajes;
	private Integer equipajeExtra;
	private Set<DTPasajero> pasajes;
	private DTCliente cliente;
	private DTVuelo vuelo;
	private DTPaquete paquete;
	private Date fechaCheckIn;
	
	public DTReserva(Date fechaReserva, Asiento tipoAsiento, Integer costo, 
            Integer cantidadPasajes, Integer equipajeExtra, 
            Set<DTPasajero> pasajes, DTCliente cliente, DTVuelo vuelo,DTPaquete paquete,Date fechaCheckIn) {
		this.fechaReserva = fechaReserva;
		this.tipoAsiento = tipoAsiento;
		this.costo = costo;
		this.cantidadPasajes = cantidadPasajes;
		this.equipajeExtra = equipajeExtra;
		this.pasajes = pasajes;
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.paquete = paquete;
		this.fechaCheckIn = fechaCheckIn;
	}
	
	
	
	public DTCliente getCliente() {
		return cliente;
	}

	public DTVuelo getVuelo() {
		return vuelo;
	}
	
	public DTPaquete getPaquete() {
		return paquete;
	}

	public Date getFechaReserva() {
        return fechaReserva;
    }

    public Asiento getTipoAsiento() {
        return tipoAsiento;
    }

    public Integer getCosto() {
        return costo;
    }

    public Integer getCantidadPasajes() {
        return cantidadPasajes;
    }

    public Integer getEquipajeExtra() {
        return equipajeExtra;
    }

    public Set<DTPasajero> getPasajes() {
        return pasajes;
    }
    
    public String getNombreVuelo() {
    	return vuelo.getNombre();
    }
    public String getNickCliente() {
    	return cliente.getNickname();
    }
    public boolean isCheckIn() {
    	return fechaCheckIn != null;
    }
    public Date getFechaCheckIn() {
    	return fechaCheckIn;
    }
}