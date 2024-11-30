package logica.controladores;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.conceptos.Cliente;
import logica.conceptos.CompraPaquete;
import logica.conceptos.CompraRuta;
import logica.conceptos.Paquete;
import logica.conceptos.PaqueteRuta;
import logica.conceptos.RutaDeVuelo;
import logica.conceptos.Vuelo;
import logica.datatypes.Asiento;
import logica.datatypes.DTPaquete;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaNoModificable;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorPaquetes;


public class ControladorPaquetes implements IControladorPaquetes {
	public void ingresarPaquete(String nombre, String descripcion, int diasValidez, int descuento, 
			Date fechaAlta, String imagen) throws InstanciaRepetida, DatoInvalido {
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		if(nombre.isBlank()|| nombre.isEmpty()||nombre == null||descripcion.isBlank()||descripcion.isEmpty()||descripcion == null||diasValidez < 1|| descuento < 0 || descuento > 100 || fechaAlta == null) {
			throw new DatoInvalido("Algún dato es inválido");
		}
		if(man.getPaquete(nombre) != null) {
			throw new InstanciaRepetida("El paquete " + nombre + " ya existe en el sistema.");
		}
		
		Paquete paq = new Paquete(nombre, descripcion, diasValidez, descuento, fechaAlta, imagen);
		man.addPaquete(paq);
	}
	public Set<String> listarPaquetesNombres(){
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		Map<String,Paquete> paquetes = man.getPaquetes();
		Set<String> res = new HashSet<>();
		for (Map.Entry<String, Paquete> entrada : paquetes.entrySet()) {
		    res.add(entrada.getValue().getNombre());
		}
		return res;
	}
	
	public void agregarRutaAPaquete(String nombre, String nombreRuta, Asiento asiento, int cant) throws InstanciaNoModificable, DatoInvalido, InstanciaNoExiste, InstanciaRepetida {
		if (nombre == null || nombre.isBlank() || nombreRuta == null || nombreRuta.isBlank() || cant < 1 || asiento == null) {
			throw new DatoInvalido("Algún dato es inválido");
		}
		ManejadorPaquete manejadorPaquete = ManejadorPaquete.getInstancia();
		ManejadorVuelo manejadorVuelo = ManejadorVuelo.getInstancia();
		Paquete paq = manejadorPaquete.getPaquete(nombre);
		if(paq == null) {
			throw new InstanciaNoExiste("El Paquete no existe");
		}
		if (paq.getComprado()) {
			throw new InstanciaNoModificable("El paquete " + nombre + " ya ha sido comprado.");
		}
		if(paq.tieneRuta(nombreRuta,asiento)) {
			throw new InstanciaRepetida("El paquete ya tiene esa ruta y asiento");
		}
		RutaDeVuelo ruta = manejadorVuelo.getRutaDeVuelo(nombreRuta);
		if(ruta == null) {
			throw new InstanciaNoExiste("El Paquete no existe");
		}
		PaqueteRuta paqRut = new PaqueteRuta(cant,asiento,paq,ruta);
		paq.addPaqueteRuta(paqRut);
	}
	
	public void altaCompraPaquete(String nombrePaquete, String nombreCliente, Date fechaCompra) throws InstanciaRepetida, InstanciaNoExiste {
		ManejadorPaquete manejadorPaquete = ManejadorPaquete.getInstancia();
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstancia();
		
		Paquete paquete = manejadorPaquete.getPaquete(nombrePaquete);
		Cliente cliente = manejadorUsuario.getCliente(nombreCliente);
		if(paquete == null)
			throw new InstanciaNoExiste("Paquete "+nombrePaquete+" no existe");
		if(cliente == null)
			throw new InstanciaNoExiste("Cliente "+nombreCliente+" no existe");
		if(cliente.getPaquetes().containsKey(nombrePaquete) && cliente.getPaquetes().get(nombrePaquete).isActivo())
			throw new InstanciaRepetida("El cliente ya tiene una compra valida de este paquete");
		
		Calendar calendar =	Calendar.getInstance();
		calendar.setTime(fechaCompra);
		calendar.add(Calendar.DAY_OF_MONTH,paquete.getPeriodoValidez());
		Date fechaVencimiento = calendar.getTime();
		CompraPaquete compra = new CompraPaquete(paquete,cliente,fechaCompra,fechaVencimiento,paquete.getCostoAsociado());
		
		for(PaqueteRuta pr:paquete.getRutas()) {
			CompraRuta compraRuta = new CompraRuta(pr.getCantidad(),pr.getTipoAsiento(),compra,pr.getRuta());
			compra.addRuta(compraRuta);
		}
		
	}
	@Override
	public DTPaquete infoPaquete(String nombre) throws InstanciaNoExiste {
		Paquete paq = ManejadorPaquete.getInstancia().getPaquete(nombre);
		if(paq == null)
			throw new InstanciaNoExiste("No existe paquete "+ nombre);
		
		return paq.getDT();
	}
	
	public Set<String> listarPaquetesSinCompras() { //lista los paquetes que no han sido comprados por ningun cliente
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		Map<String,Paquete> paquetes = man.getPaquetes();
		Set<String> res = new HashSet<>();
		for (Map.Entry<String, Paquete> entrada : paquetes.entrySet()) {
			if(entrada.getValue().getCompras().size() == 0) {
				res.add(entrada.getValue().getNombre());
			}
		}
		return res;
	}
	@Override
	public Set<DTPaquete> listarDTPaquetes() {
		Set<DTPaquete> paquetesDT = new HashSet<>();
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		Map<String,Paquete> paquetes = man.getPaquetes();
		for(Paquete p:paquetes.values())
			paquetesDT.add(p.getDT());
		
		return paquetesDT;
	}
	@Override
	public Set<DTPaquete> listarPaquetesDeVuelo(String vuelo) throws InstanciaNoExiste {
		Set<DTPaquete> paquetesDT = new HashSet<>();
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		Map<String,Paquete> paquetes = man.getPaquetes();
		Vuelo vue = ManejadorVuelo.getInstancia().getVuelo(vuelo);
		if(vue == null)
			throw new InstanciaNoExiste("No existe el vuelo "+ vuelo);
		String ruta = vue.getNombreRuta();
		for(Paquete p:paquetes.values()) {
			if(p.tieneRuta(ruta, Asiento.Ejecutivo) || p.tieneRuta(ruta, Asiento.Turista))
				paquetesDT.add(p.getDT());
		}
		return paquetesDT;
	}
}
