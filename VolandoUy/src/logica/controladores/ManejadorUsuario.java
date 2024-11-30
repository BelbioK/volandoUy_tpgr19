package logica.controladores;

import java.util.HashMap;
import java.util.Map;

import logica.conceptos.*;

public class ManejadorUsuario {
    private Map<String, Usuario> usuarios;
    private Map<String, Aerolinea> aerolineas;
    private Map<String, Cliente> clientes;

    private static ManejadorUsuario instancia;

    private ManejadorUsuario() {
        usuarios = new HashMap<>();
        aerolineas = new HashMap<>();
        clientes = new HashMap<>();
    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) instancia = new ManejadorUsuario();
        return instancia;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public Map<String, Aerolinea> getAerolineas() {
        return aerolineas;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Cliente getCliente(String nickname) {
        return clientes.get(nickname);
    }

    public Aerolinea getAerolinea(String nickname) {
        return aerolineas.get(nickname);
    }

    public Usuario getUsuario(String nickname) {
        return usuarios.get(nickname);
    }
    
    public Usuario getUsuarioByEmail(String email) {
    	for(Usuario u:usuarios.values())
    		if(u.getEmail().equals(email))
    			return u;
    	
    	return null;
    }
    
    public void addUsuario(Usuario usu) {
    	usuarios.put(usu.getNickname(), usu);
    }
    
    public void addCliente(Cliente cli) {
        clientes.put(cli.getNickname(), cli);
    }

    public void addAerolinea(Aerolinea aero) {
        aerolineas.put(aero.getNickname(), aero);
    }
    
    public boolean verificarAerolinea(String nickname) {
    	return aerolineas.containsKey(nickname);
    }
}
