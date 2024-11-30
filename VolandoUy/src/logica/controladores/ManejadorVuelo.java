package logica.controladores;

import java.util.HashMap;
import java.util.Map;

import logica.conceptos.*;

public class ManejadorVuelo {
    private Map<String, Categoria> categorias;
    private Map<String, Vuelo> vuelos;
    private Map<String, Ciudad> ciudades;
    private Map<String, RutaDeVuelo> rutasDeVuelo;

    private static ManejadorVuelo instancia;

    private ManejadorVuelo() {
        categorias = new HashMap<>();
        vuelos = new HashMap<>();
        ciudades = new HashMap<>();
        rutasDeVuelo = new HashMap<>();
    }

    public static ManejadorVuelo getInstancia() {
        if (instancia == null) instancia = new ManejadorVuelo();
        return instancia;
    }

    public Map<String, Categoria> getCategorias() {
        return categorias;
    }

    public Map<String, Vuelo> getVuelos() {
        return vuelos;
    }

    public Map<String, Ciudad> getCiudades() {
        return ciudades;
    }

    public Map<String, RutaDeVuelo> getRutasDeVuelo() {
        return rutasDeVuelo;
    }

    public Categoria getCategoria(String nombre) {
        return categorias.get(nombre);
    }

    public Vuelo getVuelo(String nombre) {
        return vuelos.get(nombre);
    }

    public Ciudad getCiudad(String nombre) {
        return ciudades.get(nombre);
    }

    public RutaDeVuelo getRutaDeVuelo(String nombre) {
        return rutasDeVuelo.get(nombre);
    }

    public void addCategoria(Categoria cat) {
       categorias.put(cat.getNombre(), cat);
    }

    public void addVuelo(Vuelo vue) {
        vuelos.put(vue.getNombre(), vue);
    }

    public void addCiudad(Ciudad city) {
        ciudades.put(city.getNombre(), city);
    }

    public void addRutaDeVuelo(RutaDeVuelo ruta) {
        rutasDeVuelo.put(ruta.getNombre(), ruta);
    }
    
    public boolean verificaCategoria(String nombre) {
    	return categorias.containsKey(nombre);
    }
    
    public boolean verificarRuta(String nombre) {
    	return rutasDeVuelo.containsKey(nombre);
    }
    
    public boolean verificarCiudad(String nombre) {
    	return ciudades.containsKey(nombre);
    }
}
