package persistencia;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

import logica.conceptos.*;
import logica.controladores.*;
import logica.datatypes.*;
import excepciones.*;



public class CargadorDatos {
	private static boolean cargados = false;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static Map<String, Map<String, Object>> resourceByRef = new HashMap<String, Map<String, Object>>();
	static HashMap<String,Date> rutasFinalizadas = new HashMap<String,Date>();
	public static void cargarDatos() {
		if(cargados)
			return;
		
		loadUsuarios();
		loadSeguidores();
		loadCategorias();
		loadCiudades();
		loadRutas();
		loadVuelos();
		loadPaquetes();
		loadComprasPaquetes();
		loadReservas();
		
		for(String ruta:rutasFinalizadas.keySet())
			try {
				Fabrica.getInstancia().getControladorVuelos().modificarEstado(ruta, Estado.Finalizada, rutasFinalizadas.get(ruta));
			} catch (InstanciaNoExiste e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		cargados = true;
	}
	private static void loadUsuarios() {
//		Ref;Tipo;Nickname;Nombre;Correo
		List<String[]> usuarios = loadCsvFile("2024Usuarios.csv");
		Map<String,String[]> clientes = loadCsvFileAsHash("2024Usuarios-Clientes.csv");
		Map<String,String[]> aerolineas = loadCsvFileAsHash("2024Usuarios-Aerolineas.csv");
		Map<String, Object> clienteMap =new HashMap<String, Object>();
		Map<String, Object> aerolineaMap =new HashMap<String, Object>();
		resourceByRef.put("clientes",clienteMap);
		resourceByRef.put("aerolineas",aerolineaMap);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for(String[] usuario:usuarios) {
			try {
				String nickname = usuario[2];
				String nombre = usuario[3];
				String correo = usuario[4];
				String password = usuario[5];
				String imagen = usuario[6];
				Date fechaAlta = formatter.parse(usuario[7]);
				if(usuario[1].equals("C")) {
					String[] cliente = clientes.get(usuario[0]);
					String apellido = cliente[1];
					Date fechaNacimiento = dateFormat.parse(cliente[2]);
					String nacionalidad = cliente[3];
					TipoDocumento tipoDocumento = cliente[4].equals("Pasaporte") ? TipoDocumento.Pasaporte : TipoDocumento.Cedula;
					String numeroDocumento = cliente[5];
					
					Fabrica.getInstancia().getControladorUsuario().ingresarDatosCliente(nickname, nombre, correo, apellido, fechaNacimiento, 
							nacionalidad, tipoDocumento, numeroDocumento, password, imagen, fechaAlta);
					clienteMap.put(usuario[0], ManejadorUsuario.getInstancia().getCliente(nickname));
				}else {
					String[] aerolinea = aerolineas.get(usuario[0]);
					String descripcion = aerolinea[1];
					String web = aerolinea[2];
					
					Fabrica.getInstancia().getControladorUsuario().ingresarDatosAerolinea(nickname, nombre, correo, descripcion, web,
							password, imagen, fechaAlta);
					aerolineaMap.put(usuario[0], ManejadorUsuario.getInstancia().getAerolinea(nickname));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			    System.out.println("Error: " + e.getMessage());
			}
		}
	}
	private static void loadSeguidores() {
//		Ref;RefSeguidor;NickNameSeguidor;RefSeguido;NickNameSeguido
		List<String[]> seguidores = loadCsvFile("2024Seguidos.csv");
		for(String[] seguidor:seguidores) {
			String seguidorNick = seguidor[2];
			String seguidoNick = seguidor[4];
			System.out.print(seguidorNick+" "+seguidoNick);
			Fabrica.getInstancia().getControladorUsuario().comenzarSeguir(seguidorNick, seguidoNick);
		}
	}
	private static void loadCategorias() {
//		Ref;Nombre
		List<String[]> categorias = loadCsvFile("2024Categorias.csv");
		Map<String, Object> catMap =new HashMap<String, Object>();
		resourceByRef.put("categorias",catMap);
		for(String[] cat:categorias) {
			try {
				Fabrica.getInstancia().getControladorVuelos().altaCategoria(cat[1]);
				catMap.put(cat[0], ManejadorVuelo.getInstancia().getCategoria(cat[1]));
			} catch (InstanciaRepetida e) {}
		}
	}
	private static void loadCiudades() {
//		Ref;Nombre;Pais;Aeropuerto;Descripcion;SitioWeb;FechaAlta
		List<String[]> ciudades = loadCsvFile("2024Ciudades.csv");
		Map<String, Object> cMap = new HashMap<String, Object>();
		resourceByRef.put("ciudades",cMap);
		for(String[] ciudad:ciudades) {
			try {
				String nombre = ciudad[1];
				String pais = ciudad[2];
				String aeropuerto = ciudad[3];
				String descripcion = ciudad[4];
				String web = ciudad[5];
				Date fecha = dateFormat.parse(ciudad[6]);
				Fabrica.getInstancia().getControladorVuelos().altaCiudad(nombre,pais,aeropuerto,descripcion,web,fecha);
				cMap.put(ciudad[0], ManejadorVuelo.getInstancia().getCiudad(nombre));
			} catch (Exception e) {}
		}
	}
	private static void loadRutas() {
//		Ref;Aerolinea;Nombre;Descripcion;Hora;CostoTurista;CostoEjecutivo;CostoEquipaje;Origen;Destino;FechaAlta;Categoria
		List<String[]> rutas = loadCsvFile("2024RutasVuelos.csv");
		Map<String, Object> rutaMap = new HashMap<String, Object>();
		for(String[] ruta:rutas) {
			try {
				String aerolinea =((Aerolinea) resourceByRef.get("aerolineas").get(ruta[1])).getNickname();
				String nombre = ruta[2];
				String descripcion = ruta[3];
				LocalTime hora = LocalTime.parse(ruta[4], DateTimeFormatter.ofPattern("HH:mm"));
				int costoTurista = Integer.parseInt(ruta[5]);
				int costoEjecutivo = Integer.parseInt(ruta[6]);
				int costoEquipaje = Integer.parseInt(ruta[7]);
				String origen = ((Ciudad) resourceByRef.get("ciudades").get(ruta[8])).getNombre();
				String destino = ((Ciudad) resourceByRef.get("ciudades").get(ruta[9])).getNombre();
				Date fechaAlta = dateFormat.parse(ruta[10]);
				Set<String> categorias = new HashSet<String>();
				String[] categoriaRefs = ruta[11].split(",");
				for(String cat:categoriaRefs) 
					categorias.add(((Categoria) resourceByRef.get("categorias").get(cat.trim())).getNombre());
				String tipoEstado = ruta[12];
				String descCorta = ruta[13];
				String imagen = ruta[14].charAt(0) != '(' ? ruta[14] : null;
				String video = ruta[15].charAt(0) != '(' ? ruta[15] : null;
				if(!ruta[16].isEmpty())
					rutasFinalizadas.put(nombre, dateFormat.parse(ruta[16]));
				int visitas = Integer.parseInt(ruta[17]);
				
				Fabrica.getInstancia().getControladorVuelos().altaRutaVuelo(aerolinea, nombre, descripcion, hora, costoTurista, 
						costoEjecutivo, costoEquipaje, origen, destino, categorias, descCorta, imagen, video);
				
				for(int i = 0;i<visitas;i++)
					Fabrica.getInstancia().getControladorVuelos().agregarVisita(nombre);
				
				//no olvidarse de cambiarle el estado a la ruta
				RutaDeVuelo rutaActual = ManejadorVuelo.getInstancia().getRutaDeVuelo(nombre);
				rutaActual.setFechaAlta(fechaAlta);
				if (tipoEstado.equals("Confirmada")) {rutaActual.setEstado(Estado.Confirmada);}
				else if (tipoEstado.equals("Rechazada")) rutaActual.setEstado(Estado.Rechazada);
				
				rutaMap.put(ruta[0], rutaActual);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		resourceByRef.put("rutas",rutaMap);
	}
	private static void loadVuelos() {
//		Ref;Aerolinea;Ruta;Nombre;Fecha;Duracion;MaxTuristas;MaxEjecutivo;FechaAlta
		List<String[]> vuelos = loadCsvFile("2024Vuelos.csv");
		Map<String, Object> vueloMap = new HashMap<String, Object>();
		resourceByRef.put("vuelos",vueloMap);
		for(String[] vuelo:vuelos) {
			try {
				String imagen = vuelo[9].charAt(0) != '(' ? vuelo[9] : null;
				String ruta =((RutaDeVuelo) resourceByRef.get("rutas").get(vuelo[2])).getNombre();
				String nombre = vuelo[3];
				Date fecha = dateFormat.parse(vuelo[4]);
				LocalTime duracion = LocalTime.parse(vuelo[5], DateTimeFormatter.ofPattern("HH:mm"));
				int horasDur = duracion.getHour();
				int minutosDur = duracion.getMinute();
				int maxTuristas = Integer.parseInt(vuelo[6]);
				int maxEjecutivo = Integer.parseInt(vuelo[7]);
				Date fechaAlta = dateFormat.parse(vuelo[8]);
				Fabrica.getInstancia().getControladorVuelos().altaVuelo(nombre, fecha, horasDur, minutosDur, maxTuristas, maxEjecutivo, fechaAlta, ruta, imagen);
				vueloMap.put(vuelo[0], ManejadorVuelo.getInstancia().getVuelo(nombre));
			}catch(Exception e) {//me está tirando una excepción
				e.printStackTrace();
			}
		}
	}
	
	private static void loadPaquetes() {
//		Ref;Nombre;Descripción;Validez;Descuento;FechaAlta;Costo
		List<String[]> paquetes = loadCsvFile("2024Paquetes.csv");
		Map<String, Object> paqueteMap =new HashMap<String, Object>();
		Map<String,List<String[]>> paquetesRutas = getPaquetesRutas();
		
		resourceByRef.put("paquetes",paqueteMap);
		for(String[] paquete:paquetes) {
			try {
				String nombre = paquete[1];
				String descripcion = paquete[2];
				int validez = Integer.parseInt(paquete[3]);
				int descuento = Integer.parseInt(paquete[4]);
				Date fechaAlta = dateFormat.parse(paquete[5]);
				String imagen = paquete[7];
				
				Fabrica.getInstancia().getControladorPaquetes().ingresarPaquete(nombre, descripcion, validez, descuento, fechaAlta, imagen);
				
				for(String[] paqueteRuta:paquetesRutas.get(paquete[0])) {
					String aerolinea = ((Aerolinea) resourceByRef.get("aerolineas").get(paqueteRuta[2])).getNickname();
					String ruta =((RutaDeVuelo) resourceByRef.get("rutas").get(paqueteRuta[3])).getNombre();
					int cantidad = Integer.parseInt(paqueteRuta[4]);
					Asiento asiento = paqueteRuta[5].equals("Turista") ? Asiento.Turista : Asiento.Ejecutivo;
					
					Fabrica.getInstancia().getControladorPaquetes().agregarRutaAPaquete(nombre, ruta, asiento, cantidad);
				}
				paqueteMap.put(paquete[0], ManejadorPaquete.getInstancia().getPaquete(nombre));
			}catch(Exception e) {
				e.getCause();
			}
		}
	}
	private static Map<String, List<String[]>> getPaquetesRutas() {
		Map<String,List<String[]>> paquetesHash = new HashMap<String,List<String[]>>();
//		Ref;Paquete;Aerolinea;Ruta;Cantidad;TipoAsiento
		List<String[]> paquetes = loadCsvFile("2024PaquetesRutas.csv");
		for(String[] paquete:paquetes) {
			try {
				if(!paquetesHash.containsKey(paquete[1]))
					paquetesHash.put(paquete[1], new ArrayList<String[]>());
				
				paquetesHash.get(paquete[1]).add(paquete);
			}catch(Exception e) {}
		}
		
		return paquetesHash;
	}
	private static void loadComprasPaquetes() {
//		Ref;Paquete;Cliente;FechaCompra;Validez;Costo
		List<String[]> compras = loadCsvFile("2024ComprasPaquetes.csv");
		Map<String, Object> compraMap = new HashMap<String, Object>();
		resourceByRef.put("compras",compraMap);
		for(String[] compra:compras) {
			try {
				String paquete = ((Paquete) resourceByRef.get("paquetes").get(compra[1])).getNombre();
				String cliente =((Cliente) resourceByRef.get("clientes").get(compra[2])).getNickname();
				Date fechaValidez = dateFormat.parse(compra[3]);
				Date fecha = dateFormat.parse(compra[4]);
				int costo = Integer.parseInt(compra[5]);
				
				Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(paquete, cliente, fechaValidez);
//				compraMap.put(compras[0], ManejadorPaquete.getInstancia().getCompra());
			}catch(Exception e) {
				e.getCause();
			}
		}
	}
	private static void loadReservas() {
//		Ref;Aerolinea;Ruta;Vuelo;Cliente;TipoAsiento;CantPasajeros;EquipajeExtra;FechaReserva;Costo;TipoReserva
		List<String[]> reservas = loadCsvFile("2024Reservas.csv");
//		Ref;Reserva;HoraEmbarque;FechaCheckin
		List<String[]> checkIns = loadCsvFile("2024Checkin.csv");
		HashMap<String,String[]> checkInByRes = new HashMap<>();
		for(String[] ci:checkIns) 
			checkInByRes.put(ci[1], ci);
		
		Map<String,List<String[]>> pasajeros = getPasajeros();
		Map<String, Object> reservaMap =new HashMap<String, Object>();
		resourceByRef.put("reservas",reservaMap);
		for(String[] reserva:reservas) {
			try {
				String aerolinea =((Aerolinea) resourceByRef.get("aerolineas").get(reserva[1])).getNickname();
				String ruta =((RutaDeVuelo) resourceByRef.get("rutas").get(reserva[2])).getNombre();
				String vuelo =((Vuelo) resourceByRef.get("vuelos").get(reserva[3])).getNombre();
				String cliente =((Cliente) resourceByRef.get("clientes").get(reserva[4])).getNickname();
				String paquete = resourceByRef.get("paquetes").containsKey(reserva[10]) ? ((Paquete) resourceByRef.get("paquetes").get(reserva[10])).getNombre() : "" ; 
				Asiento asiento = reserva[5].equals("Turista") ? Asiento.Turista : Asiento.Ejecutivo;
				int equipaje = Integer.parseInt(reserva[7]);
				Date fecha = dateFormat.parse(reserva[8]);
				int costo = Integer.parseInt(reserva[9]);
				Set<DTPasajero> pasajes = new HashSet<DTPasajero>(); 
				for(String[] pasajero:pasajeros.get(reserva[0])) 
					pasajes.add(new DTPasajero(pasajero[2],pasajero[3],pasajero.length == 5 ? Integer.parseInt(pasajero[4]) : 0));
//				Date fechaEmbarque = 
				
				Fabrica.getInstancia().getControladorVuelos().altaReserva(vuelo, cliente, paquete, asiento, equipaje, pasajes, fecha);
				
				if(checkInByRes.containsKey(reserva[0])) {
					Fabrica.getInstancia().getControladorVuelos().checkIn(vuelo, cliente);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static Map<String, List<String[]>> getPasajeros(){
		Map<String,List<String[]>> pasajerosHash = new HashMap<String,List<String[]>>();
//		Ref;Reserva;Nombre;Apellido
		List<String[]> pasajeros = loadCsvFile("2024Pasajes.csv");
		for(String[] pasaje:pasajeros) {
			try {
				if(!pasajerosHash.containsKey(pasaje[1]))
					pasajerosHash.put(pasaje[1], new ArrayList<String[]>());
				
				pasajerosHash.get(pasaje[1]).add(pasaje);
			}catch(Exception e) {}
		}
		
		return pasajerosHash;
	}
	
	private static List<String[]> loadCsvFile(String fileName){
		List<String []> rows = new ArrayList<String[]>();
		
		try (InputStream iss = CargadorDatos.class.getClassLoader().getResourceAsStream(fileName)) {
			BufferedReader brr = new BufferedReader(new InputStreamReader(iss));
			brr.readLine(); //Leo la linea de titulos
            String line;
            while ((line = brr.readLine()) != null) {
                String[] values = line.split(";");
                rows.add(values);
            }
        } catch (Exception e) {
		} 
		
		return rows;
	}
	
	private static Map<String,String[]> loadCsvFileAsHash(String fileName){
		Map<String, String []> rows = new HashMap<String,String[]>();
		
		try (InputStream iss = CargadorDatos.class.getClassLoader().getResourceAsStream(fileName)) {
			BufferedReader brr = new BufferedReader(new InputStreamReader(iss));
			brr.readLine(); //Leo la linea de titulos
            String line;
            while ((line = brr.readLine()) != null) {
                String[] values = line.split(";");
                rows.put(values[0],values);
            }
        } catch (Exception e) {
		} 
		
		return rows;
	}
}