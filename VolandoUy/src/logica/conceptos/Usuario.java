package logica.conceptos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.TipoUsuario;
import logica.datatypes.DTUsuario;

public abstract class Usuario {
	String nickname;
	String nombre;
	String password;
	String email;
	String imagen;
	Date fechaAlta;
	Map<String,Usuario> seguidos;
	Map<String,Usuario> seguidores;
	
	
	public Usuario(String nickname, String nombre, String email, String imagen, Date fechaAlta, String password) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.fechaAlta = fechaAlta;
		this.imagen = imagen == null ? "USC.jpg" : imagen;
		this.seguidores = new HashMap<String, Usuario>();
		this.seguidos = new HashMap<String, Usuario>();
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// Esto es lo menos seguro del mundo mundial
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
	public abstract  DTUsuario getDTUsuario();
	public TipoUsuario getTipo() {
        return this.getClass().getName().equals(Cliente.class.getName()) ? TipoUsuario.CLIENTE : TipoUsuario.AEROLINEA;
    }
	public Map<String, Usuario> getSeguidos() {
		return this.seguidos;
	}
	public void setSeguidos(Map<String, Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	public Map<String, Usuario> getSeguidores() {
		System.out.println(this.seguidores);
		System.out.println("getSeguidores en usuario");
		return this.seguidores;
	}
	public void setSeguidores(Map<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	public void seguir(Usuario usu) {
		//if(!this.seguidos.containsKey(usu.getNickname())) {
			this.seguidos.put(usu.getNickname(), usu);
		//}
	}
	public void dejarSeguir(Usuario usu) {
		if(this.seguidos.containsKey(usu.getNickname())) {
			this.seguidos.remove(usu.getNickname());
		}
	}
	public boolean loSigo(String nickname) {
		return this.seguidos.containsKey(nickname);
	}
	public void agregarASeguidores(Usuario usu) {
		this.seguidores.put(usu.getNickname(), usu);
	}
	public void borrarDeSeguidores(Usuario usu) {
		this.seguidores.remove(usu.getNickname());
	}
}
