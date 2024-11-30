package main.java.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Clientes")
public class Cliente extends Usuario{
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String tipo_documento;
	private String num_documento;
	@OneToMany(cascade = CascadeType.PERSIST)
	List<Reserva> reservas = new ArrayList<>();
	
	public String getApellido() {
		return apellido;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getTipo_documento() {
		return tipo_documento;
	}
	public String getNum_documento() {
		return num_documento;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento;
	}
	
	public void addReserva(Reserva r) {
		reservas.add(r);
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
}
