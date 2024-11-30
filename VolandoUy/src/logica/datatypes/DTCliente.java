package logica.datatypes;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCliente extends DTUsuario{
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private TipoDocumento tipoDocumento;
	private String nroDocumento;
	private Set<DTReserva> reservas;
	private Map<String, DTCompraPaquete> paquetes;
	private Set<DTCompraPaquete> paquetesSet;
	
	public DTCliente(String nickname) {
		super(nickname);
	}
	public DTCliente(String nombre, String nickname, String email, String apellido, Date fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, String nroDocumento, Set<DTReserva> reservas, Map<String, DTCompraPaquete> paquetes, String imagen, Date fechaAlta, String password) {
		super(nombre, nickname, email, imagen,fechaAlta, password);
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.reservas = reservas;
		this.paquetes = paquetes;
		this.setPaquetesSet(paquetes.values()
                					.stream()
                					.collect(Collectors.toSet()));
	}
	
	public String getApellido() {
		return apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;	
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public Set<DTReserva> getReservas(){
		return reservas;
	}
	public Map<String, DTCompraPaquete> getPaquetes(){
		return paquetes;
	}
	public Set<DTCompraPaquete> getPaquetesSet() {
		return paquetesSet;
	}
	public void setPaquetesSet(Set<DTCompraPaquete> paquetesSet) {
		this.paquetesSet = paquetesSet;
	}
}
