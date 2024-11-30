package logica.controladores;

import java.util.HashMap;
import java.util.Map;

import logica.conceptos.Paquete;

public class ManejadorPaquete {
    private Map<String, Paquete> paquetes;

    private static ManejadorPaquete instancia;

    private ManejadorPaquete() {
        paquetes = new HashMap<>();
    }

    public static ManejadorPaquete getInstancia() {
        if (instancia == null) instancia = new ManejadorPaquete();
        return instancia;
    }

    public Map<String, Paquete> getPaquetes() {
        return paquetes;
    }

    public Paquete getPaquete(String nickname) {
        return paquetes.get(nickname);
    }

    public void addPaquete(Paquete paq) {
        paquetes.put(paq.getNombre(), paq);
    }
    
    public boolean verificarPaquete(String nickname) {
    	return paquetes.containsKey(nickname);
    }
}
