package publicar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.controladores.ControladorPaquetes;
import logica.controladores.ControladorUsuarios;
import logica.controladores.ControladorVuelos;
import logica.controladores.Fabrica;
import logica.datatypes.Asiento;
import logica.datatypes.DTBusquedaRutaYPaquete;
import logica.datatypes.DTCliente;
import logica.datatypes.DTListaAerolineas;
import logica.datatypes.DTListaCompraRutas;
import logica.datatypes.DTListaPaquetes;
import logica.datatypes.DTListaReservas;
import logica.datatypes.DTListaRutas;
import logica.datatypes.DTListaString;
import logica.datatypes.DTListaVuelos;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPasajero;
import logica.datatypes.DTReserva;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.DTListaUsuarios;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTVuelo;
import logica.datatypes.Estado;
import logica.datatypes.TipoDocumento;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
	
	private Endpoint endpoint = null;
	private Fabrica fab;
	private ControladorVuelos cVuelos; //yo sé que esto es la peor práctica posible pero
	private ControladorUsuarios cUsuarios; // ya no quiero escribir más getInstnacia()
	private ControladorPaquetes cPaquetes;
    
	//Constructor
    public Publicador(){
    	fab = Fabrica.getInstancia();
    	cVuelos = (ControladorVuelos) fab.getControladorVuelos(); 
    	cUsuarios = (ControladorUsuarios) fab.getControladorUsuario();
    	cPaquetes = (ControladorPaquetes) fab.getControladorPaquetes();
    }
	
    @WebMethod(exclude = true)
    public void publicar(){
    	String url = getProperty("publicador.url"); 
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }
    
    private String getUsername() {
    	String username = System.getenv("USER");
        if (username == null) {
            username = System.getenv("LOGNAME");
        }

        if (username != null) {
            return username;
        } else {
            return "sebastiano.benato";
        }
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    private String getProperty(String key) {
    	String user = getUsername();
    	String filePath = "/ens/home01/"+user.charAt(0)+"/"+user+"/.VolandoUy/.properties";
    	System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (IOException e) {
        	System.out.println("No se encontró el archivo");
        }
        return "http://localhost:9128/Publicador?wsdl";
    }
    
    //AltaRutaDeVuelo
    @WebMethod
    public DTListaString listarCategorias(){	
		DTListaString aux1 = new DTListaString();
		aux1.setArray(cVuelos.listarCategorias());
		return aux1;
	}
    @WebMethod
    public DTListaString listarCiudades(){
    	DTListaString aux = new DTListaString();
    	aux.setArray(cVuelos.listarCiudades());
    	return aux;
    }
    
    
    //AltaVuelo servlet
    @WebMethod
    public DTListaString listarRutasDeVuelo(String nickname) throws DatoInvalido{
		DTListaString aux = new DTListaString();
    	aux.setArray(cVuelos.listarRutasDeVuelo(nickname));
	    return aux;
		
    	
    }
    @WebMethod public void altaVuelo(String nombre, Date fecha, Integer horasDur, Integer minutosDur, 
			Integer cantidadTuristas, Integer cantidadEjecutivo, Date fechaAlta, 
			String rutaDeVuelo, String imagen) throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
    	cVuelos.altaVuelo(nombre, fecha, horasDur, minutosDur, cantidadTuristas, cantidadEjecutivo, fechaAlta, rutaDeVuelo, imagen);
    }

    @WebMethod
    public DTListaVuelos listarAllDTVuelos() {
    	DTListaVuelos aux = new DTListaVuelos();
    	aux.setVuelos(cVuelos.listarAllDTVuelos());
    	return aux;
    }
 
    public DTListaRutas listarDTRutasDeVuelo() {
    	DTListaRutas aux = new DTListaRutas();
    	aux.setRutas(cVuelos.listarDTRutasDeVuelo());
    	return aux;
    }
    
    public DTListaAerolineas listarAerolineasDT() {
    	DTListaAerolineas aux = new DTListaAerolineas();
    	aux.setAerolineas(cUsuarios.listarAerolineasDT());
    	return aux;
    }
    
    //CompraPaquete
    public void altaCompraPaquete(String nombrePaquete, String nombreCliente, Date fechaCompra) throws InstanciaRepetida, InstanciaNoExiste {
    	cPaquetes.altaCompraPaquete(nombrePaquete, nombreCliente, fechaCompra);
    }
    @WebMethod 
    public DTListaPaquetes listarDTPaquetes(){
    	DTListaPaquetes aux = new DTListaPaquetes();
    	aux.setPaquetes(cPaquetes.listarDTPaquetes());
    	return aux;
    }
    @WebMethod 
    public DTListaString listarPaquetesNombres(){
    	DTListaString aux = new DTListaString();
    	aux.setArray(cPaquetes.listarPaquetesNombres());
    	return aux;
    }
    @WebMethod
    public DTListaUsuarios listarUsuariosDT() {
    	DTListaUsuarios aux = new DTListaUsuarios();
    	aux.setUsuarios(cUsuarios.listarUsuariosDT());
    	return aux;
    }
    @WebMethod
    public DTListaString listarUsuarios() {
    	
    	DTListaString aux = new DTListaString();
    	aux.setArray(cUsuarios.listarUsuarios());
    	return aux;
    }
    
    @WebMethod
    public DTPaquete infoPaquete(String nombre) throws InstanciaNoExiste {
    	return cPaquetes.infoPaquete(nombre);
    }
    //Registro
    @WebMethod
    public void ingresarDatosAerolinea(String nickname, String nombre, String correo, String descripcion, String link,
			String password, String imagen, Date fechaAlta) throws InstanciaRepetida{
    	cUsuarios.ingresarDatosAerolinea(nickname, nombre, correo, descripcion, link, password, imagen, fechaAlta);
    }
    @WebMethod
    public void ingresarDatosCliente(String nickname, String nombre, String correo, String apellido, Date fechaNacimiento, 
			String nacionalidad, TipoDocumento tipoDocumento, String numDocumento, String password, String imagen, Date fechaAlta) throws InstanciaRepetida{
    	cUsuarios.ingresarDatosCliente(nickname, nombre, correo, apellido, fechaNacimiento, nacionalidad, tipoDocumento, numDocumento, password, imagen, fechaAlta);
    }
    
    //ReservaVuelo
    @WebMethod
    public DTListaCompraRutas listarPaquetesCompradosParaVuelo(String nickname, String vuelo) throws InstanciaNoExiste{
    	DTListaCompraRutas aux = new DTListaCompraRutas();
    	aux.setAerolineas(cUsuarios.listarPaquetesCompradosParaVuelo(nickname, vuelo));
    	return aux;
    }
    @WebMethod
    public DTListaRutas listarRutasDeAerolinea(String aerolinea) throws InstanciaNoExiste {
    	DTListaRutas aux = new DTListaRutas();
    	aux.setRutas(cVuelos.listarRutasDeAerolinea(aerolinea));
    	return aux;
    }
    //Reserva
    @WebMethod
    public DTListaReservas getReservasDeUsuario(String nickname) throws InstanciaNoExiste{
    	DTListaReservas aux = new DTListaReservas();
    	aux.setReservas(cUsuarios.getReservasDeUsuario(nickname));
    	return aux;
    }
    @WebMethod
    public DTReserva infoReserva(String vuelo, String cliente) throws InstanciaNoExiste{
    	return cVuelos.infoReserva(vuelo, cliente);
    }
    
    //Rutas
    @WebMethod
    public DTRutasDeVuelo getRutaDeVuelo(String nombreRuta) throws InstanciaNoExiste, DatoInvalido {
    	return cVuelos.getRutaDeVuelo(nombreRuta);
    }
    
    @WebMethod
    public void altaRutaVuelo(String aerolinea, String nombre, String descripcion, String hora, int costoTurista, 
			int costoEjecutivo, int costoEquipExtra, String ciudadOrigen, String ciudadDestino, String categorias,
			String descCorta, String imagen, String video) throws InstanciaRepetida, InstanciaNoExiste, DatoInvalido {
    	Set<String> categoriasSet = new HashSet<>();
    	if(!categorias.isBlank() || !categorias.isEmpty() || !categorias.equals("")){
    		String[] categoriasArr = categorias.split(";");
    		for (String cat : categoriasArr) {
    			categoriasSet.add(cat);
    		}    		
    	}
        LocalTime horaParsed = LocalTime.parse(hora);
        cVuelos.altaRutaVuelo(aerolinea, nombre, descripcion, horaParsed, costoTurista, costoEjecutivo, costoEquipExtra, ciudadOrigen, ciudadDestino, categoriasSet, descCorta, imagen, video);
    }
    
    @WebMethod
    public DTListaVuelos listarVuelosDTDeRuta(String ruta) {
    	DTListaVuelos aux = new DTListaVuelos();
    	try {
    		aux.setVuelos(cVuelos.listarVuelosDTDeRuta(ruta));
    	} catch (InstanciaNoExiste e) {
    		
    	}
    	return aux;
    }
    
    //Usuarios
    @WebMethod
    public DTUsuario mostrarInfoUsuarios(String nickname) throws InstanciaNoExiste, DatoInvalido{
    	return cUsuarios.mostrarInfoUsuarios(nickname);
    }
    @WebMethod
    public DTVuelo mostrarInfoVuelo(String nombre) throws InstanciaNoExiste{
    	return cVuelos.mostrarInfoVuelo(nombre);
    }
    @WebMethod
    public void modificarCliente (String nicknameOriginal, String password, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, String numDocumento) throws InstanciaNoExiste, DatoInvalido {
    	cUsuarios.modificarCliente(nicknameOriginal, password, nombre, apellido, fechaNacimiento, nacionalidad, tipoDocumento, numDocumento);
    }
    @WebMethod
    public void modificarAerolinea(String nicknameOriginal, String password, String nombre, String descripcion, String link) throws InstanciaNoExiste, DatoInvalido{
    	cUsuarios.modificarAerolinea(nicknameOriginal, password, nombre, descripcion, link);
    }
    
    @WebMethod
	public boolean checkNickname(String nickname ){
    	return cUsuarios.checkNickname(nickname);
    }
    @WebMethod
    public boolean checkEmail(String email ){
    	return cUsuarios.checkEmail(email);
    }

    
    @WebMethod
    public void altaReserva(String vuelo, String cliente, String paquete, Asiento asiento, int equipajeExtra, String pasajes, Date fecha) throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida {
    	Set<DTPasajero> pasajesSet = new HashSet<DTPasajero>();
        String[] nameArray = pasajes.split(";");
        String nombre;
        String apellido;
        for (String name : nameArray) {
            nombre = name.split(" ")[0];
            apellido = name.split(" ")[1];
            pasajesSet.add(new DTPasajero(nombre, apellido));
        }

    	cVuelos.altaReserva(vuelo, cliente, paquete, asiento, equipajeExtra, pasajesSet, fecha);
    	
    }
    @WebMethod
    public void checkIn(String nombreVuelo, String nombreCliente) throws InstanciaNoExiste, InstanciaRepetida {
    	cVuelos.checkIn(nombreVuelo, nombreCliente);
    }
    
    //Login
    @WebMethod
    public DTUsuario login(String nickname, String password) throws DatoInvalido{
    	return cUsuarios.login(nickname, password);
    }
  //LoginMobile
    @WebMethod
    public DTCliente loginMobile(String nickname, String password) throws DatoInvalido{
    	return cUsuarios.loginMobile(nickname, password);
    }
    
    @WebMethod
    public void comenzarSeguir(String principal, String secundario) {
    	cUsuarios.comenzarSeguir(principal, secundario);
    }
    @WebMethod
    public void dejarSeguir(String principal, String secundario) {
    	cUsuarios.dejarDeSeguir(principal, secundario);
    }
    @WebMethod 
    public boolean loSigo(String principal, String secundario) {
    	return cUsuarios.loSigo(principal, secundario);
    }
    @WebMethod
    public DTListaUsuarios getSeguidores(String nickname) {
    	DTListaUsuarios aux = new DTListaUsuarios();
    	aux.setUsuarios(cUsuarios.getSeguidores(nickname));
    	return aux;    	
    }
    @WebMethod
    public DTListaUsuarios getSeguidos(String nickname) {
    	DTListaUsuarios aux = new DTListaUsuarios();
    	aux.setUsuarios(cUsuarios.getSeguidos(nickname));
    	return aux; 
    }
    @WebMethod
    public boolean tienePaquete(String rutaDeVuelo) {
    	return cVuelos.tienePaquete(rutaDeVuelo);
    }
    @WebMethod 
    public boolean vuelosPendientes(String rutaDeVuelo){
    	return cVuelos.vuelosPendientes(rutaDeVuelo);
    }
    @WebMethod
    public void modificarEstado(String ruta, Estado estado) throws InstanciaNoExiste {
    	cVuelos.modificarEstado(ruta, estado, null);
    }
    @WebMethod
    public boolean esRutaPropia(String nickname, String nombreRuta) {
    	return cUsuarios.esRutaPropia(nickname, nombreRuta);
    }
    @WebMethod
    public void AgregarVisitaRuta(String nombreRuta) throws InstanciaNoExiste {
    	cVuelos.agregarVisita(nombreRuta);
    }
    @WebMethod 
    public DTBusquedaRutaYPaquete busquedaGeneral(String busqueda) {
    	DTBusquedaRutaYPaquete b = new DTBusquedaRutaYPaquete();
    	
    	HashSet<DTRutasDeVuelo> rutas = new HashSet<>();
    	HashSet<DTPaquete> paquetes = new HashSet<>();
    	
    	for(DTRutasDeVuelo r:cVuelos.listarDTRutasDeVuelo())
    		if(aplicaBusqueda(r.getNombre(),busqueda) || aplicaBusqueda(r.getDescripcion(),busqueda) || aplicaBusqueda(r.getDescripcionCorta(),busqueda))
    			rutas.add(r);
    	for(DTPaquete p:cPaquetes.listarDTPaquetes())
    		if(aplicaBusqueda(p.getNombre(),busqueda) || aplicaBusqueda(p.getDescripcion(),busqueda))
    			paquetes.add(p);
    	
    	b.setPaquete(paquetes);
    	b.setRutas(rutas);
    	
    	return b;
    }
    public boolean aplicaBusqueda(String s,String t) {
    	return s.toLowerCase().contains(t.toLowerCase());
    }
    public void hacerPDF(String usuario,String nombreVuelo,String nombreRuta, String direccion) {
    	System.out.println("en el publicador");
    	try {
			cVuelos.hacerPDF(usuario,nombreVuelo,nombreRuta, direccion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


