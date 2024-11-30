package logica.controladores;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import logica.conceptos.Aerolinea;
import logica.conceptos.Categoria;
import logica.conceptos.Ciudad;
import logica.conceptos.Cliente;
import logica.conceptos.CompraPaquete;
import logica.conceptos.Paquete;
import logica.conceptos.Pasaje;
import logica.conceptos.ReservaVuelo;
import logica.conceptos.RutaDeVuelo;
import logica.conceptos.Usuario;
import logica.conceptos.Vuelo;
import logica.datatypes.*;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorVuelos;
import main.java.entidades.Reserva;
import main.java.entidades.RutaFinalizada;
import persistencia.DBConnection;




public class ControladorVuelos implements IControladorVuelos{

	@Override
	public Set<String> listarRutasDeVuelo(String nombre) throws DatoInvalido {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		if(nombre == null) throw new DatoInvalido("El nombre de la aerolinea no puede ser null");
		Aerolinea aero = man.getAerolinea(nombre);
		if(aero == null) throw new DatoInvalido("La aerolinea no existe");
		else {
			if(nombre == null || aero == null) return new HashSet<>();
			Map<String, RutaDeVuelo> rut = aero.getRutas();
			Set<String> res = rut.keySet();
			return res;
			
		}
	}
	@Override
	public Set<String> listarAllRutas(){
		ManejadorVuelo man = ManejadorVuelo.getInstancia();
		Map<String, RutaDeVuelo> rutas = man.getRutasDeVuelo();
		Set<String> res = new HashSet<>();
		for (Map.Entry<String, RutaDeVuelo> entrada : rutas.entrySet()) {
		   res.add(entrada.getValue().getNombre());
		}
		return res;
	}
	@Override 
	public DTRutasDeVuelo getRutaDeVuelo(String NombreRuta) throws InstanciaNoExiste, DatoInvalido {
		ManejadorVuelo man = ManejadorVuelo.getInstancia();
		RutaDeVuelo ruta = man.getRutaDeVuelo(NombreRuta);
		if (NombreRuta == null) throw new DatoInvalido("No puede ser un nombre nulo");
		if (ruta == null) throw new InstanciaNoExiste("No existe la ruta ingresada en el sistema");
		DTRutasDeVuelo DTruta = null; 

		if(ruta.getEstado().equals(Estado.Finalizada)) DTruta =getRutaFromDB(NombreRuta);
		if(DTruta == null) DTruta = ruta.getDTRuta();
		
		return DTruta;
		
	}
	private DTRutasDeVuelo getRutaFromDB(String nombreRuta) {
		RutaFinalizada rf = DBConnection.getInstancia().getRuta(nombreRuta);
		System.out.print(rf == null);
		if(rf == null)
			return null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
		HashMap<String,DTVuelo> vuelos = new HashMap<>();
		for(main.java.entidades.Vuelo v:rf.getVuelos()) {
			Set<DTReserva> reservas = new HashSet<>();
			for(Reserva r:v.getReservas()) {
				DTCliente cliente = new DTCliente(r.getCliente().getNickname());
				DTVuelo vuelo = new DTVuelo(v.getNombre());
				DTReserva dt = new DTReserva(r.getFecha_alta(),r.getTipoAsiento() != null && r.getTipoAsiento().equals("Turista") ? Asiento.Turista : Asiento.Ejecutivo, r.getCosto(), r.getCant_pasajes(), r.getEquipaje_extra(), new HashSet<DTPasajero>(), cliente, vuelo, null, null);
				reservas.add(dt);
			}
			DTVuelo dt = new DTVuelo(v.getNombre(), v.getFecha(), 0, 0, v.getMax_turista(), v.getMax_ejecutivo(), v.getFecha_alta(), reservas, 0, 0, "VU00.jpg", rf.getAerolinea().getNickname(), new DTRutasDeVuelo(rf.getNombre()));
			vuelos.put(v.getNombre(), dt);
		}
		DTRutasDeVuelo dt = new DTRutasDeVuelo(rf.getNombre(), 
												rf.getDescripcion(), 
												LocalTime.parse(rf.getHora_salida()), 
												rf.getFecha_alta(), 
												new DTCiudad("",rf.getCiudad_origen(),"","",""), 
												new DTCiudad("",rf.getCiudad_destino(),"","",""), 
												rf.getCosto_turista(), 
												rf.getCosto_ejecutivo(), 
												rf.getCosto_equipaje_extra(), 
												vuelos, 
												new HashSet<DTCategoria>(), 
												Estado.Finalizada, 
												rf.getAerolinea().getNickname(), 
												"RV00.png", 
												rf.getDescripcion_corta(), 
												null, 
												0);
		
		return dt;
	}

	@Override
	public void altaVuelo(String nombre, Date fecha, Integer horasDur, Integer minutosDur, 
							Integer cantidadTuristas, Integer cantidadEjecutivo, Date fechaAlta, 
							String rutaDeVuelo, String imagen) throws  InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
		
		if(nombre.isBlank() || nombre == null || nombre.isEmpty() || fecha == null || cantidadTuristas == null  || cantidadEjecutivo == null || rutaDeVuelo.isBlank() ||
			rutaDeVuelo == null || rutaDeVuelo.isEmpty() || fechaAlta == null || cantidadTuristas < 0 || cantidadEjecutivo < 0 || (horasDur <= 0 && minutosDur <= 0)) {
			throw new DatoInvalido("No se rellenó algún campo");
		}
		ManejadorVuelo man = ManejadorVuelo.getInstancia();
		RutaDeVuelo rut = man.getRutaDeVuelo(rutaDeVuelo);
		if(rut == null || !man.verificarRuta(rut.getNombre())) {
			throw new InstanciaNoExiste("La ruta vuelo " + rutaDeVuelo + " no existe");
		}
		Vuelo exx = man.getVuelo(nombre);
		if (exx != null) {
			throw new InstanciaRepetida("El Vuelo" + nombre + "ya esta registrado");
		}
		Vuelo vue = new Vuelo(nombre, fecha, horasDur, minutosDur, cantidadTuristas, cantidadEjecutivo, cantidadTuristas, cantidadEjecutivo, fechaAlta, rut, imagen);
		vue.setRutaDeVuelo(rut);
		rut.agregarVuelo(vue);
		man.addVuelo(vue);
	}

	@Override
	public void altaCategoria(String nombre) throws InstanciaRepetida {
		ManejadorVuelo man = ManejadorVuelo.getInstancia();
		if (man.verificaCategoria(nombre)) {
			throw new InstanciaRepetida("Categoria" + nombre + "ya existe");
		} else {
			man.addCategoria(new Categoria(nombre));
		}
	}

	@Override
	public DTVuelo mostrarInfoVuelo(String nombre) throws InstanciaNoExiste {
		if (nombre == null) throw new InstanciaNoExiste("El nombre del Vuelo no existe");
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		Vuelo vuelo = manVuelo.getVuelo(nombre);
		if(vuelo == null) {
			throw new InstanciaNoExiste("El vuelo no existe");
		}
		DTVuelo dtvue = vuelo.getDT();
		return dtvue;
	}
	
	@Override
	public Set<String> listarVuelosDeRuta(String nombre) throws InstanciaNoExiste {
		if (nombre == null) throw new InstanciaNoExiste("No puede ser un nombre nulo");
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		if(!manVuelo.verificarRuta(nombre)) {
			throw new InstanciaNoExiste ("La ruta no existe");
		}
		if (manVuelo.getRutaDeVuelo(nombre) == null) throw new InstanciaNoExiste("No existe la ruta ingresada en el sistema");
		Map<String, Vuelo> conjunto = manVuelo.getVuelos();
		Set<String> res = new HashSet<>();
		for(Map.Entry<String, Vuelo> entry : conjunto.entrySet()) {
			String nombreAero = entry.getValue().getRuta().getNombre();
			if(nombre.equals(nombreAero)) {				
				res.add(entry.getValue().getNombre());
			}
		}
		return res;
	}
	
	@Override
	public Set<DTVuelo> listarAllDTVuelos(){
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		Map<String, Vuelo> conjunto = manVuelo.getVuelos();
		Set<DTVuelo> res = new HashSet<>();
		for(Map.Entry<String, Vuelo> entry : conjunto.entrySet()) {		
			res.add(entry.getValue().getDT());
		}
		return res;
	}

	@Override
	public Set<DTVuelo> listarVuelosDTDeRuta(String nombreRuta) throws InstanciaNoExiste {
		RutaDeVuelo ruta = ManejadorVuelo.getInstancia().getRutaDeVuelo(nombreRuta);
		if(ruta == null)
			throw new InstanciaNoExiste("La ruta no existe");
		
		return ruta.getDTsVuelos();
	}

	@Override
	public void altaReserva(String nombreVuelo, String nombreCliente, String nombrePaquete, Asiento asiento, int equipaje, Set<DTPasajero> pasajeros,
			Date fecha) throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida {
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstancia();		
		Vuelo vuelo = manVuelo.getVuelo(nombreVuelo);
		Cliente cliente = manUsuario.getCliente(nombreCliente);
		
		if(vuelo == null)
			throw new InstanciaNoExiste("El vuelo " + nombreVuelo + " no existe");
		if(cliente == null)
			throw new InstanciaNoExiste("El cliente " + nombreCliente + " no existe");
		
		CompraPaquete paquete = cliente.getPaquetes().get(nombrePaquete);
		if(nombrePaquete != null && !nombrePaquete.isEmpty() && paquete == null)
			throw new InstanciaNoExiste("El cliente " + nombreCliente + " no tiene el paquete "+nombrePaquete);
		if(cliente.tieneReserva(nombreVuelo)) 
			throw new InstanciaRepetida("El cliente "+nombreCliente+" ya tiene una reserva del vuelo "+nombreVuelo);
		if(pasajeros.size() == 0)
			throw new DatoInvalido("Tiene que haber al menos un pasajero");
		if(paquete != null && !paquete.aplicaAReserva(vuelo.getNombreRuta(),asiento,pasajeros.size()))
			throw new DatoInvalido("No quedan suficientes pasajes en la compra del paquete");
		if(vuelo.getAsientosRest(asiento) < pasajeros.size())
			throw new DatoInvalido("No quedan asientos disponibles para tantos pasajeros");
		
		int nroAsiento = vuelo.getAsientosMax(asiento) - vuelo.getAsientosRest(asiento) + 1;
		Set<Pasaje> pasajes = new HashSet<>();
		for(DTPasajero pass : pasajeros) {
			int asientonro = pass.getAsiento() > 0 ? pass.getAsiento() : nroAsiento;
			Pasaje pasaje = new Pasaje(pass.getNombre(),pass.getApellido(),asientonro);
			pasajes.add(pasaje);
			nroAsiento++;
		}
		
		new ReservaVuelo(cliente,vuelo,fecha,asiento,equipaje,pasajes,paquete != null ? paquete.getPaquete() : null);
	}
	
	@Override
	public void checkIn(String nombreVuelo, String nombreCliente) throws InstanciaNoExiste, InstanciaRepetida{
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstancia();		
		Vuelo vuelo = manVuelo.getVuelo(nombreVuelo);
		Cliente cliente = manUsuario.getCliente(nombreCliente);
		
		if(vuelo == null)
			throw new InstanciaNoExiste("El vuelo " + nombreVuelo + " no existe");
		if(cliente == null)
			throw new InstanciaNoExiste("El cliente " + nombreCliente + " no existe");
		if(!cliente.tieneReserva(nombreVuelo)) 
			throw new InstanciaNoExiste("El cliente "+nombreCliente+" no tiene una reserva del vuelo "+nombreVuelo);
		if(cliente.getReservas().get(nombreVuelo).isCheckIn())
			throw new InstanciaRepetida("El cliente "+nombreCliente+" ya realizó el check-in para el vuelo "+nombreVuelo);
		
		cliente.getReservas().get(nombreVuelo).checkIn();
	}

	public void altaCiudad(String nombre, String pais, String nombreAeropuerto, String descripcion, String paginaWeb, Date fechaAlta) throws InstanciaRepetida, DatoInvalido{
		if(nombre.isBlank() || nombre == null || nombre.isEmpty() || pais.isBlank() || pais == null || pais.isEmpty() || nombreAeropuerto.isBlank() ||
			nombreAeropuerto == null || nombreAeropuerto.isEmpty() || descripcion.isBlank() || descripcion == null || descripcion.isEmpty() || fechaAlta == null ) {
			throw new DatoInvalido("No se rellenó algún campo");
		}
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		if(!manVuelo.verificarCiudad(nombre)) {
			manVuelo.addCiudad(new Ciudad(nombre,pais,nombreAeropuerto,descripcion,paginaWeb,fechaAlta));
			return;
		}
		throw new InstanciaRepetida("La ciudad " + nombre + " ya existe");
	}
	
	public Set<String> listarCiudades(){
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		return manVuelo.getCiudades().keySet();
	};
	
	public void altaRutaVuelo(String aerolinea, String nombre, String descripcion, LocalTime hora, int costoTurista, 
			int costoEjecutivo, int costoEquipExtra, String ciudadOrigen, String ciudadDestino, Set<String> categorias,
			String descCorta, String imagen, String video) throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
		if(nombre.isBlank() || aerolinea == null || aerolinea.isEmpty() || nombre.isBlank() || nombre == null || nombre.isEmpty() || 
				descripcion.isBlank() || descripcion == null || descripcion.isEmpty()|| descripcion.isEmpty() || ciudadOrigen == null || 
				ciudadOrigen.isEmpty() || ciudadDestino == null || ciudadDestino.isEmpty() || ciudadDestino.isEmpty())
			throw new DatoInvalido("No se rellenó algún campo");
		
		if(costoTurista <= 0 || costoEjecutivo <= 0 || costoEquipExtra <= 0 ) {
			throw new DatoInvalido("Los costos deben ser un numero mayor a 0");
		}
		
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstancia();
		
		RutaDeVuelo rut = manVuelo.getRutaDeVuelo(nombre);
		if(rut != null)
			throw new InstanciaRepetida("La ruta vuelo con nombre:  " + nombre + " ya existe");
        Ciudad origen = manVuelo.getCiudad(ciudadOrigen);
        Ciudad destino = manVuelo.getCiudad(ciudadDestino);
        if(origen == null || destino == null)
        	throw new InstanciaNoExiste("No existe en el sistema el origen/destino ingresados.");
        Aerolinea aero = manUsuario.getAerolinea(aerolinea);
        Date fechaActual = new Date();
        Set<Categoria> categoriasRuta = new HashSet<>();
        Map<String, Categoria> setCategorias = manVuelo.getCategorias();
    	for (String key : categorias) {
    		if (setCategorias.containsKey(key)) {
    			categoriasRuta.add(setCategorias.get(key));
    		}
    	}
    	if((categoriasRuta.isEmpty() && !categorias.isEmpty() && !setCategorias.isEmpty() ) || categorias.size() != categoriasRuta.size()) {
    		throw new InstanciaNoExiste("La/s categorias ingresadas no existen");
    	}
        RutaDeVuelo ruta = new RutaDeVuelo(nombre, descripcion, hora, fechaActual, origen, destino, costoTurista, costoEjecutivo, costoEquipExtra, aero, categoriasRuta, descCorta, imagen, video);
        aero.agregarRutaDeVuelo(nombre, ruta);
        manVuelo.addRutaDeVuelo(ruta);
	}
	
	public void agregarVisita(String nombreRuta) throws InstanciaNoExiste {
		RutaDeVuelo ruta = ManejadorVuelo.getInstancia().getRutaDeVuelo(nombreRuta);
		if(ruta == null)
        	throw new InstanciaNoExiste("La ruta "+nombreRuta+" no existe");
		
		ruta.agregarVisita();
	}
	
	public Set<String> listarCategorias(){
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		return manVuelo.getCategorias().keySet();
		
	}
	
	public void modificarEstado(String nombreRuta, Estado estado, Date date) throws InstanciaNoExiste{
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		RutaDeVuelo ruta = manVuelo.getRutaDeVuelo(nombreRuta);
		if(ruta == null)
			throw new InstanciaNoExiste("No existe la ruta de nombre "+nombreRuta);
		ruta.setEstado(estado);
		if(estado.equals(Estado.Finalizada))
			finalizarRuta(ruta,date);
	}
	
	private void finalizarRuta(RutaDeVuelo ruta,Date date) {
		if(DBConnection.getInstancia().getRuta(ruta.getNombre()) != null)
			return;
		
		RutaFinalizada rf = ruta.getRutaFinalizada(date);
		DBConnection.getInstancia().persistRuta(rf);
	}
	
	public Set<String> getRutasStatusIngresada(String nombreAerolinea) throws DatoInvalido{
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		Set<String> res = new HashSet<>();
		Set<String> rutas = new HashSet<>();
		try {
			rutas = listarRutasDeVuelo(nombreAerolinea);
			for (String iterator : rutas) {
			    RutaDeVuelo ruta = manVuelo.getRutaDeVuelo(iterator);
			    if(ruta.getEstado().equals( Estado.Ingresada)) {
			    	res.add(ruta.getNombre());
			    }
			}
			return res;
		} catch (DatoInvalido e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Set<String> getRutasStatusConfirmada(String nombreAerolinea) throws DatoInvalido{
		ManejadorVuelo manVuelo = ManejadorVuelo.getInstancia();
		Set<String> res = new HashSet<>();
		Set<String> rutas = new HashSet<>();
		try {
			rutas = listarRutasDeVuelo(nombreAerolinea);
			for (String iterator : rutas) {
			    RutaDeVuelo ruta = manVuelo.getRutaDeVuelo(iterator);
			    if(ruta.getEstado().equals( Estado.Confirmada)) {
			    	res.add(ruta.getNombre());
			    }
			}
			return res;
		} catch (DatoInvalido e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Set<DTRutasDeVuelo> listarDTRutasDeVuelo() {
			Set<DTRutasDeVuelo> res = new HashSet<DTRutasDeVuelo>();
			ManejadorVuelo man = ManejadorVuelo.getInstancia();
			Map<String, RutaDeVuelo> rutas = man.getRutasDeVuelo();
			for (Map.Entry<String, RutaDeVuelo> entry : rutas.entrySet()) {
			    RutaDeVuelo ruta = entry.getValue(); 
			    res.add(ruta.getDTRuta());
			}
			return res;
	}
	
	@Override
	public DTReserva infoReserva(String vuelo, String cliente) throws InstanciaNoExiste {
		if(vuelo == null || cliente == null)
			throw new InstanciaNoExiste("Faltan datos");
		
		Cliente cli = ManejadorUsuario.getInstancia().getCliente(cliente);
		if(cli == null)
			throw new InstanciaNoExiste("El cliente no existe");
		
		ReservaVuelo res = cli.getReservas().get(vuelo);
		
		if(res == null)
			throw new InstanciaNoExiste("El cliente "+cliente+" no tiene una reserva para el vuelo "+vuelo);

		return res.getDT();
	}
	@Override
	public Set<DTRutasDeVuelo> listarRutasDeAerolinea(String aerolinea) throws InstanciaNoExiste {
		Set<DTRutasDeVuelo> rutas = new HashSet<>();
		Aerolinea aero = ManejadorUsuario.getInstancia().getAerolinea(aerolinea);
		if(aero == null)
			throw new InstanciaNoExiste("Aerolinea "+aerolinea+" no existe");
		for(RutaDeVuelo r: aero.getRutas().values())
			rutas.add(r.getDTRuta());
		
		return rutas;
	}
	@Override
	public DTRutasDeVuelo infoRuta(String rutaNombre) throws InstanciaNoExiste {
		RutaDeVuelo ruta = ManejadorVuelo.getInstancia().getRutaDeVuelo(rutaNombre);
		if(ruta == null)
			throw new InstanciaNoExiste("Ruta "+rutaNombre+" no existe");

		return ruta.getDTRuta();
	}
	@Override
	public boolean tienePaquete(String rutaDeVuelo) {
		ManejadorPaquete man = ManejadorPaquete.getInstancia();
		Map<String, Paquete> paquetes = man.getPaquetes();
		boolean aux = false;
		for(Paquete value: paquetes.values()) {
			aux  = (aux || value.rutaEstaEnPaquete(rutaDeVuelo));
		}
		return aux;
	}
	@Override 
	public boolean vuelosPendientes(String rutaDeVuelo) {
		RutaDeVuelo ruta = ManejadorVuelo.getInstancia().getRutaDeVuelo(rutaDeVuelo);
		Map<String, Vuelo> vuelos = ruta.getVuelos();
		boolean res = false;
		Date actual = new Date();
		for(Vuelo value: vuelos.values()) {
			res = res||value.getFecha().after(actual);
		}
		return res;
	}
	@Override
	public void hacerPDF(String usuario,String nombreVuelo,String nombreRuta, String direccion) throws Exception {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		DTRutasDeVuelo ruta = this.getRutaDeVuelo(nombreRuta);
		Cliente usu = (Cliente) man.getUsuario(usuario);
		Map<String,ReservaVuelo> reservas = usu.getReservas();
		ReservaVuelo reserva = reservas.get(nombreVuelo);
		DeviceRgb CelesteVolando = new DeviceRgb(167, 171, 221); 
		try {
		    PdfWriter writer = new PdfWriter(direccion);
		    PdfDocument pdf = new PdfDocument(writer);
		    Document document = new Document(pdf);

		    // ** Encabezado **
		    Paragraph titulo = new Paragraph("Volando.uy")
		            .setFontSize(30)
		            .setFontColor(CelesteVolando)
		            .setBold()
		            .setTextAlignment(TextAlignment.CENTER);
		    document.add(titulo);

		    LineSeparator separator = new LineSeparator(new SolidLine());
		    separator.setStrokeColor(CelesteVolando); // Asigna el color CelesteVolando al separador
		    document.add(separator);

		    // ** Detalles de la reserva **
		    Table detailsTable = new Table(4).useAllAvailableWidth();
		    detailsTable.addCell(createDetailCell("Cliente:", true));
		    detailsTable.addCell(createDetailCell(usu.getNickname(), false));
		    detailsTable.addCell(createDetailCell("Fecha:", true));
		    detailsTable.addCell(createDetailCell(reserva.getFechaReserva().toString(), false));

		    detailsTable.addCell(createDetailCell("Hora embarque:", true));
		    detailsTable.addCell(createDetailCell(reserva.getHoraEmbarque().toString(), false));
		    detailsTable.addCell(createDetailCell("Ruta:", true));
		    detailsTable.addCell(createDetailCell(ruta.getDescripcionCorta(), false));

		    detailsTable.addCell(createDetailCell("Origen:", true));
		    detailsTable.addCell(createDetailCell(ruta.getOrigen().getNombre(), false));
		    detailsTable.addCell(createDetailCell("Destino:", true));
		    detailsTable.addCell(createDetailCell(ruta.getDestino().getNombre(), false));

		    document.add(detailsTable);

		    // Espacio entre secciones
		    document.add(new Paragraph("\n"));

		    // ** Tabla de pasajeros **
		    Paragraph tableTitle = new Paragraph("Detalles de Pasajeros")
		            .setFontSize(16)
		            .setBold()
		            .setTextAlignment(TextAlignment.LEFT);
		    document.add(tableTitle);

		    Table passengerTable = new Table(3).useAllAvailableWidth();
		    passengerTable.addHeaderCell(createHeaderCell("Nombre"));
		    passengerTable.addHeaderCell(createHeaderCell("Apellido"));
		    passengerTable.addHeaderCell(createHeaderCell("Asiento"));

		    for (Pasaje pasajero : reserva.getPasajes()) {
		        passengerTable.addCell(createDetailCell(pasajero.getDT().getNombre(), false));
		        passengerTable.addCell(createDetailCell(pasajero.getDT().getApellido(), false));
		        String tipo = reserva.getTipoAsiento().toString();
		        passengerTable.addCell(createDetailCell(String.valueOf(pasajero.getDT().getAsiento()+"-"+tipo), false));
		    }
		    document.add(passengerTable);

		    // Cerrar el documento
		    document.close();
		    System.out.println("PDF generado correctamente en: " + direccion);

		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}
	@Override
	public Cell createDetailCell(String content, boolean isHeader) {
	    Cell cell = new Cell().add(new Paragraph(content));
	    cell.setFontSize(12).setPadding(5);
	    if (isHeader) {
	        cell.setBold().setFontColor(DeviceRgb.BLACK);
	    } else {
	        cell.setFontColor(DeviceRgb.BLACK);
	    }
	    cell.setBorder(Border.NO_BORDER);
	    return cell;
	}
	@Override
	public Cell createHeaderCell(String content) {
		DeviceRgb CelesteVolando = new DeviceRgb(167, 171, 221); 
	    return new Cell().add(new Paragraph(content))
	            .setBold()
	            .setFontSize(12)
	            .setBackgroundColor(CelesteVolando)
	            .setTextAlignment(TextAlignment.CENTER);
	}
	
}
