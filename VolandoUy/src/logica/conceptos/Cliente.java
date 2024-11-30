package logica.conceptos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTCliente;
import logica.datatypes.DTCompraPaquete;
import logica.datatypes.DTReserva;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoDocumento;




public class Cliente extends Usuario{
	String apellido;
	Date fechaNacimiento;
	String nacionalidad;
	TipoDocumento tipoDocumento;
	String nroDocumento;
	Map<String,ReservaVuelo> reservas;
	Map<String, CompraPaquete> paquetes;
	
	public Cliente(String nickname, String nombre, String email, String apellido, Date fechaNacimiento,
			String nacionalidad, TipoDocumento tipoDocumento, String nroDocumento, String password, String imagen, Date fechaAlta) {
		super(nickname, nombre, email, imagen, fechaAlta,password);
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.reservas = new HashMap<>();
		this.paquetes = new HashMap<>();
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public DTUsuario getDTUsuario() {
		Set<DTReserva> dtreservas = new HashSet<>();
		Map<String,DTCompraPaquete> dtpaquete = new HashMap<>();

		for(ReservaVuelo r:this.reservas.values())
			dtreservas.add(r.getDT());
		for(CompraPaquete paquete: this.paquetes.values())
			dtpaquete.put(paquete.getNombrePaquete(),paquete.getDT());
		return new DTCliente(this.nombre, this.nickname, this.email, this.apellido, this.fechaNacimiento, this.nacionalidad, this.tipoDocumento, this.nroDocumento, dtreservas, dtpaquete, this.imagen, this.fechaAlta, this.password);
	}
	public DTUsuario getDTUsuarioSinReservas() {
		Set<DTReserva> dtreservas = new HashSet<>();
		Map<String,DTCompraPaquete> dtpaquete = new HashMap<>();
		return new DTCliente(this.nombre, this.nickname, this.email, this.apellido, this.fechaNacimiento, this.nacionalidad, this.tipoDocumento, this.nroDocumento, dtreservas, dtpaquete, this.imagen, this.fechaAlta, this.password);
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public Map<String,ReservaVuelo> getReservas(){
		return reservas;
	}
	public Map<String, CompraPaquete> getPaquetes(){
		return paquetes;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public void addReserva(ReservaVuelo reserva) {
		this.reservas.put(reserva.getNombreVuelo(),reserva);
	}
	public boolean tieneReserva(String vuelo) {
		return this.reservas.containsKey(vuelo);
	}
	public void addPaquete(String paquete, CompraPaquete compra) {
		this.paquetes.put(paquete, compra);
	}
	public main.java.entidades.Cliente getDBCliente() {
		 main.java.entidades.Cliente a = new  main.java.entidades.Cliente();
		 
		 a.setFecha_nacimiento(fechaNacimiento);
		 a.setApellido(apellido);
		 a.setEmail(this.getEmail());
		 a.setNickname(this.getNickname());
		 a.setNombre(this.getNombre());
		 a.setNum_documento(nroDocumento);
		 a.setTipo_documento(tipoDocumento.toString());
		 a.setTipo_usuario("cliente");
		 a.setNacionalidad(nacionalidad);
		 
		 return a;
	}
}
