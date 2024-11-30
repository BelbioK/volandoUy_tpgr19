package logica.controladores;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.conceptos.*;
import logica.datatypes.*;
import excepciones.*;
import logica.interfaces.IControladorUsuarios;

public class ControladorUsuarios implements IControladorUsuarios{

	@Override
	public Set<String> listarAerolineas() {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
		Map<String, Aerolinea> conjunto = manUsu.getAerolineas();
		Set<String> res = new HashSet<>();
		if (conjunto != null) {
			for(Map.Entry<String, Aerolinea> entry : conjunto.entrySet()) {
				res.add(entry.getValue().getNickname());
			}
		}
		return res;
	}
	
	public Set<String> listarUsuarios(){
        ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
        Map<String, Usuario> conjunto = manUsu.getUsuarios();
        Set<String> res = new HashSet<>();
        for(Map.Entry<String, Usuario> entry : conjunto.entrySet()) {
            res.add(entry.getValue().getNickname());
        }
        return res;
    }
	
	public boolean checkEmail(String email ){
		ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
        Map<String, Usuario> conjUsuarios = manUsu.getUsuarios();
		for(Map.Entry<String,Usuario> usu : conjUsuarios.entrySet()) {
			if( usu.getValue().getEmail().equals(email)) {
				return false;			
			}
		}
		return true;
    }
	
	public boolean checkNickname(String nickname ){
		ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
        Map<String, Usuario> conjUsuarios = manUsu.getUsuarios();
		for(Map.Entry<String,Usuario> usu : conjUsuarios.entrySet()) {
			if( usu.getValue().getNickname().equals(nickname)) {
				return false;		
			}
		}
		return true;
    }
	
	public Set<String> listarClientes() {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
		Map<String, Cliente> clientes = manUsu.getClientes();
		return clientes.keySet();
	}
	
	public Set<DTUsuario> listarUsuariosDT(){
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Set<DTUsuario> res = new HashSet<>();//inicializar el set en vacio
		Map<String, Usuario> aux = man.getUsuarios();
		for(Map.Entry<String, Usuario> entry : aux.entrySet()) {
			Usuario usu = entry.getValue();
			DTUsuario dtpaq = usu.getDTUsuario();
			res.add(dtpaq);
		}
		return res;
	}
	
	public DTUsuario mostrarInfoUsuarios(String nickname) throws InstanciaNoExiste, DatoInvalido{
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		if(nickname == null || nickname == "" || nickname.isBlank() || nickname.isEmpty()) {
			throw new DatoInvalido("Nickname esta vacio");
		}
		Usuario usu = man.getUsuario(nickname);
		if (usu != null) {
			if (usu instanceof Cliente) {
				Cliente cli = (Cliente) usu;
				return cli.getDTUsuario();
			}else { //osea es Aerolinea
				Aerolinea aero = (Aerolinea) usu;
				return aero.getDTUsuario();
			}
		}else {
			throw new InstanciaNoExiste("El Usuario con Nickname: " + nickname + "NO esta esta registrado");
		}
	}
	
	public Set<DTRutasDeVuelo> listarRutas(String nickname) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Aerolinea aero = man.getAerolinea(nickname);
		Set<DTRutasDeVuelo> res = new HashSet<>();//inicializar el set en vacio
		Map<String, RutaDeVuelo> aux = aero.getRutas();
		for(Map.Entry<String, RutaDeVuelo> entry : aux.entrySet()) {
			RutaDeVuelo rut = entry.getValue();
			DTRutasDeVuelo dtrut = rut.getDTRuta();
			res.add(dtrut);
		}
		return res;
	}

	public Set<DTPaquete> litarPaquetesCliente(String nickname) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Cliente cli = man.getCliente(nickname);
		Set<DTPaquete> res = new HashSet<>();//inicializar el set en vacio
		Map<String, CompraPaquete> aux = cli.getPaquetes();
		for(Map.Entry<String, CompraPaquete> entry : aux.entrySet()) {
			CompraPaquete compaq = entry.getValue();
			Paquete paq = compaq.getPaquete();
			DTPaquete dtpaq = paq.getDT();
			res.add(dtpaq);
		}
		return res;
	}

	public void ingresarDatosCliente(String nickname, String nombre, String correo, String apellido, Date fechaNacimiento, 
			String nacionalidad, TipoDocumento tipoDocumento, String numDocumento, String password, String imagen, Date fechaAlta) throws InstanciaRepetida {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		if(man.getUsuario(nickname) != null) {
			throw new InstanciaRepetida("El usuario " + nickname + " ya existe en el sistema.");
		}
		Map<String, Usuario> conjUsuarios = man.getUsuarios();
		for(Map.Entry<String,Usuario> usu : conjUsuarios.entrySet()) {
			if( usu.getValue().getEmail().equals(correo)) {
				throw new InstanciaRepetida("El correo " + correo + " ya esta en uso.");
			}
		}
		Cliente cli = new Cliente(nickname, nombre, correo, apellido, fechaNacimiento, nacionalidad, tipoDocumento, numDocumento, password, imagen, fechaAlta);
		man.addUsuario(cli);
		man.addCliente(cli);			
	}

	public void ingresarDatosAerolinea(String nickname, String nombre, String correo, String descripcion, String link,
			String password, String imagen, Date fechaAlta) throws InstanciaRepetida {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		if(man.getUsuario(nickname) != null) {
			throw new InstanciaRepetida("El usuario " + nickname + " ya existe en el sistema.");
		}
		Map<String, Usuario> conjUsuarios = man.getUsuarios();
		for(Map.Entry<String,Usuario> usu : conjUsuarios.entrySet()) {
			if(usu.getValue().getEmail().equals(correo)) {
				throw new InstanciaRepetida("El correo " + correo + " ya esta en uso.");
			}
		}
		Aerolinea aero = new Aerolinea(nickname, nombre, correo, descripcion, link, password, imagen, fechaAlta);
		man.addUsuario(aero);
		man.addAerolinea(aero);
	}	

	public Set<DTReserva> listarReservasCliente(String nickname) throws InstanciaNoExiste {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Cliente cli = man.getCliente(nickname);
		if(cli == null) {
			throw new InstanciaNoExiste("El Usuario con Nickname: " + nickname + "NO esta esta registrado");
		}
		Set<DTReserva> res = new HashSet<>();
		Map<String,ReservaVuelo> aux = cli.getReservas();
		for(ReservaVuelo rv : aux.values()) {
			DTReserva dtrv = rv.getDT();
			res.add(dtrv);
		}
		return res;
	}
	
	public Usuario obtenerUsuario(String nickname) {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstancia();
		Usuario res = manUsu.getUsuario(nickname);
		return res;
	}
	public void modificarCliente (String nicknameOriginal, String password, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, String numDocumento) throws InstanciaNoExiste, DatoInvalido {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Cliente clienteOriginal = man.getCliente(nicknameOriginal);
		if(man.getUsuario(nicknameOriginal) == null) {
			throw new InstanciaNoExiste("El usuario " + nicknameOriginal + " no existe en el sistema.");
		}
		
		if(apellido == "" || apellido.isBlank() || apellido.isEmpty() || nombre == "" || nombre.isBlank() || nombre.isEmpty() ||
			nacionalidad == "" || nacionalidad.isBlank() || nacionalidad.isEmpty() || numDocumento == "" || numDocumento.isBlank() || numDocumento.isEmpty()) {
			throw new DatoInvalido("");			
		}
		clienteOriginal.setNombre(nombre);
		clienteOriginal.setPassword(password);
		clienteOriginal.setApellido(apellido);
		clienteOriginal.setFechaNacimiento(fechaNacimiento);
		clienteOriginal.setNacionalidad(nacionalidad);
		clienteOriginal.setTipoDocumento(tipoDocumento);
		clienteOriginal.setNroDocumento(numDocumento);
	}


	public void modificarAerolinea(String nicknameOriginal, String password, String nombre, String descripcion, String link) throws InstanciaNoExiste, DatoInvalido {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Aerolinea clienteOriginal = man.getAerolinea(nicknameOriginal);
		if(man.getUsuario(nicknameOriginal) == null) {
			throw new InstanciaNoExiste("El usuario " + nicknameOriginal + " no existe en el sistema.");
		}
		if(descripcion == "" || descripcion.isBlank() || descripcion.isEmpty() || nombre == "" || nombre.isBlank() || nombre.isEmpty())
			throw new DatoInvalido("Faltan datos obligatorios");

		clienteOriginal.setNombre(nombre);
		clienteOriginal.setPassword(password);
		clienteOriginal.setDescripcion(descripcion);
		clienteOriginal.setLink(link);
		
	}

	@Override
	public DTUsuario login(String key, String password) throws DatoInvalido {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(key);
		if(usu == null)
			usu = man.getUsuarioByEmail(key);
		if(usu == null)
			throw new DatoInvalido("Usuario no registrado");
		if(!usu.validatePassword(password))
			throw new DatoInvalido("Contraseña inválida");

		return usu.getDTUsuario();
	}
	@Override
	public DTCliente loginMobile(String key, String password) throws DatoInvalido {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(key);
		if(usu == null)
			usu = man.getUsuarioByEmail(key);
		if(usu == null)
			throw new DatoInvalido("Usuario no registrado");
		if(!usu.validatePassword(password))
			throw new DatoInvalido("Contraseña inválida");
		if(usu.getTipo().equals(TipoUsuario.AEROLINEA))
			throw new DatoInvalido("Usuario no registrado");

		return (DTCliente)usu.getDTUsuario();
	}

	@Override
	public Set<DTReserva> getReservasDeUsuario(String nickname) throws InstanciaNoExiste {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(nickname);
		if(usu == null)
			throw new InstanciaNoExiste("No existe el usuario "+nickname);
		Set<DTReserva> rvs = new HashSet<>();
		if(usu.getTipo().equals(TipoUsuario.CLIENTE)) {
			for(ReservaVuelo rv: ((Cliente)usu).getReservas().values())
				rvs.add(rv.getDT());
		}else {
			for(RutaDeVuelo r:((Aerolinea)usu).getRutas().values()) {
				for(DTVuelo v:r.getDTsVuelos())
					rvs.addAll(v.getReservas());
			}
		}

		return rvs;
	}

	@Override
	public Set<DTAerolinea> listarAerolineasDT() {
		Set<DTAerolinea> aerolineas = new HashSet<DTAerolinea>();
		for(Aerolinea a:ManejadorUsuario.getInstancia().getAerolineas().values())
			aerolineas.add(a.getDTAerolinea());
		
		return aerolineas;
	}

	@Override
	public Set<DTCompraRuta> listarPaquetesCompradosParaVuelo(String nickname, String vuelo)
			throws InstanciaNoExiste {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Cliente usu = man.getCliente(nickname);
		if(usu == null)
			throw new InstanciaNoExiste("No existe el usuario "+nickname);
		Vuelo vue = ManejadorVuelo.getInstancia().getVuelo(vuelo);
		if(vue == null)
			throw new InstanciaNoExiste("No existe el vuelo "+vuelo);
		
		String ruta = vue.getNombreRuta();
		Set<DTCompraRuta> compras = new HashSet<DTCompraRuta>();
		
		for(CompraPaquete cp: usu.getPaquetes().values())
			for(CompraRuta cr:cp.getRutas()){
				if(cr.getNombreRuta().equals(ruta) && cp.getVencimiento().getTime() > new Date().getTime())
					compras.add(cr.getDT());
			}
		return compras;
	}
	@Override
	public void comenzarSeguir(String principal, String aSeguir) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario UsuPrincipal = man.getUsuario(principal);
		Usuario UsuASeguir = man.getUsuario(aSeguir);
		UsuPrincipal.seguir(UsuASeguir);
		UsuASeguir.agregarASeguidores(UsuPrincipal);
		
	}
	@Override
	public void dejarDeSeguir(String principal, String dejarSeguir) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario UsuPrincipal = man.getUsuario(principal);
		Usuario DejarDeSeguir = man.getUsuario(dejarSeguir);
		UsuPrincipal.dejarSeguir(DejarDeSeguir);
		DejarDeSeguir.borrarDeSeguidores(UsuPrincipal);
	}
	@Override 
	public Set<DTUsuario> getSeguidores(String nickname){
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(nickname);
		Map<String, Usuario> seguidores = usu.getSeguidores();
		Set<DTUsuario> aux = new HashSet<DTUsuario>();
		for (String key : seguidores.keySet()) {
			Usuario usuario = man.getUsuario(key);
		    aux.add(usuario.getDTUsuario());
		}
		return aux;
		
	}
	@Override 
	public Set<DTUsuario> getSeguidos(String nickname){
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(nickname);
		Map<String, Usuario> seguidos = usu.getSeguidos();
		Set<DTUsuario> aux = new HashSet<DTUsuario>();
		for (String key : seguidos.keySet()) {
		    Usuario usuario = man.getUsuario(key);
			aux.add(usuario.getDTUsuario());
		}
		return aux;
	}
	@Override
	public boolean loSigo(String principal, String secundario) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario usu = man.getUsuario(principal);
		return usu.loSigo(secundario);
	}
	@Override 
	public boolean esRutaPropia(String nickname, String nombreRuta) {
		ManejadorUsuario man = ManejadorUsuario.getInstancia();
		Usuario aerolinea = man.getUsuario(nickname);
		Map<String,RutaDeVuelo> rutas = ((Aerolinea) aerolinea).getRutas();
		boolean res= false;
		for (RutaDeVuelo value : rutas.values()) {
		    res = res || value.getNombre().equals(nombreRuta);
		}
		return res;
	}
	
}
