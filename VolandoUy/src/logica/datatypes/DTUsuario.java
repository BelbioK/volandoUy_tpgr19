package logica.datatypes;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuario{
	private String nombre;
	private String nickname;
	private String email;
	private String imagen;
	private Date fechaAlta;
	private String password;
	private TipoUsuario tipo;
	
	public DTUsuario(String nickname) {
		this.nickname = nickname;
		this.imagen = "USC.jpg";
	}
	
	public DTUsuario(String nombre, String nickname, String email, String imagen, Date fechaAlta, String password) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.email = email;
		this.imagen = imagen;
		this.fechaAlta = fechaAlta;
		this.password = password; 
		this.tipo = this.getClass().getName().equals(DTCliente.class.getName()) ? TipoUsuario.CLIENTE : TipoUsuario.AEROLINEA;
	}
	
	public String getImagen() {
		return imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNickname() {
		return nickname;
	}	
	
	public String getMail() {
		return email;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public TipoUsuario getTipo() {
        return this.tipo;
    }
	public String getPassword() {
		return password;
	}
}
