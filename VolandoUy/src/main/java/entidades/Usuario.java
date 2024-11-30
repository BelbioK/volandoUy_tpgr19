package main.java.entidades;

import jakarta.annotation.Nonnull;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;

@Entity
@Table(name="Usuarios")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nickname;
	private String email;
	@Nonnull
	private String nombre;
	private String tipo_usuario;
	public int getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
	public String getEmail() {
		return email;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTipo_usuario() {
		return tipo_usuario;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
}
