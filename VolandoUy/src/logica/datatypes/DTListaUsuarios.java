package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaUsuarios {
	
	private Set<DTUsuario> usuarios;

	public DTListaUsuarios() {
		
	}
	
	public Set<DTUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<DTUsuario> usuarios) {
		this.usuarios = usuarios;
	}
}