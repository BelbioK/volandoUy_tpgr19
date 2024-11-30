package com.volandouyback.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicar.TipoUsuario;
import publicar.DtCliente;
import publicar.DtListaReservas;
import publicar.DtListaString;
import publicar.DtReserva;
import publicar.DtUsuario;
import publicar.DtVuelo;
import publicar.DatoInvalido_Exception;
import publicar.DtListaUsuarios;
import publicar.InstanciaNoExiste_Exception;
import publicar.Publicador;
import publicar.PublicadorService;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/usuarios")
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO el parametro "usuario" va a pasar a ser "key"
		request.setAttribute("servlet", "/usuarios");
		String nicknameUsuario = request.getAttribute("usuario") == null ? request.getParameter("usuario") : (String) request.getAttribute("usuario");
		PublicadorService service = new  PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
        
        String verifEmail = request.getParameter("verifEmail");
        String verifNickname = request.getParameter("verifNickname");
        
    	if(verifEmail != null) {
    		if(port.checkEmail(verifEmail)) {
    			response.setContentType("text/html;charset=UTF-8");
    			response.getWriter().write("DISPONIBLE");
    			response.setStatus(200);
    		}
    		else {
    			response.setContentType("text/html;charset=UTF-8");
    			response.getWriter().write("REPETIDO");
    			response.setStatus(200); //409 Conflict
    		}
    		return;
    	}
    	else if(verifNickname != null) {

    		if(port.checkNickname(verifNickname)) {
    			response.setContentType("text/html;charset=UTF-8");
    			response.getWriter().write("DISPONIBLE");
    			response.setStatus(200);
    		}
    		else {
    			response.setContentType("text/html;charset=UTF-8");
    	        response.getWriter().write("REPETIDO");
    	        response.setStatus(200); //409 Conflict
    		}
    		return;
    	}
        
        
        if(nicknameUsuario == null) {
        	setFiltros(request);
			Set<DtUsuario> usuarios = new HashSet<>();
			String tipo = request.getParameter("tipoUsuarioFiltro");
			boolean hasTipoFiltro = tipo != null && !tipo.isEmpty();
			TipoUsuario tipoDt = hasTipoFiltro ? tipo.equals("cliente") ? TipoUsuario.CLIENTE : tipo.equals("aerolinea") ? TipoUsuario.AEROLINEA : null : null;
			hasTipoFiltro = tipoDt != null;
			String busqueda = request.getParameter("busquedaFiltro");
			boolean hasBusquedaFiltro = busqueda != null && !busqueda.isEmpty();
			DtListaUsuarios listaUsu = port.listarUsuariosDT();
			Set<DtUsuario> listaUsuario = new HashSet<> (listaUsu.getUsuarios());
			for(DtUsuario u: listaUsuario) {//aqui creo que se tienen que ocupar los DataTypes con los Sets, preguntar
				if(
						(!hasBusquedaFiltro || u.getNickname().toLowerCase().contains(busqueda.toLowerCase())) &&	
						(!hasTipoFiltro || u.getTipo().equals(tipoDt))	
				  ) {
					usuarios.add(u);
				}
			}
			request.setAttribute("usuarios",usuarios);
			request.getRequestDispatcher("/WEB-INF/ListarUsuarios/ListarUsuarios.jsp").forward(request, response);		
			return;
        }else {
        	try {
        		DtUsuario infoUsuario = (DtUsuario) port.mostrarInfoUsuarios(nicknameUsuario);
        		request.setAttribute("usuarioParaFinalizar", infoUsuario);
        		HttpSession session = request.getSession(false);
        		DtUsuario usuarioLogueado = null;
        		if(session != null)
        			usuarioLogueado = (DtUsuario)session.getAttribute("usuario");
        		
        		if(usuarioLogueado!=null) {
        			boolean loSigo = port.loSigo(usuarioLogueado.getNickname(),nicknameUsuario);
        			if(loSigo)	request.setAttribute("loSigo", "si");
        			else		request.setAttribute("loSigo", "no");	
        		}else
        			request.setAttribute("loSigo", "no");
        		
        		DtListaUsuarios gauuu = port.getSeguidos(nicknameUsuario);
				List<DtUsuario> aux = gauuu.getUsuarios();
				request.setAttribute("seguidos", aux);
				DtListaUsuarios gau = port.getSeguidores(nicknameUsuario);
				List<DtUsuario> aux1 = gau.getUsuarios();
				request.setAttribute("seguidores", aux1);
        		
    			if(infoUsuario.getTipo() == TipoUsuario.CLIENTE) {
        			Set<DtVuelo> vuelosReservaUsuario = new HashSet<>();
        			for(DtReserva reserva : ((DtCliente)infoUsuario).getReservas()) {
        				vuelosReservaUsuario.add(port.mostrarInfoVuelo(reserva.getVuelo().getNombre()));
        			}
        			request.setAttribute("vuelosReservaUsuario",vuelosReservaUsuario);
        		}
        		request.setAttribute("usuario",infoUsuario);
        		request.getRequestDispatcher("/WEB-INF/ConsultaUsuario/ConsultaUsuario.jsp").forward(request, response);
        		return;
        	} catch (InstanciaNoExiste_Exception | DatoInvalido_Exception e) {
        		request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
        		request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
        		forward(request, response);
        		return;
        	} 
        }
        
	}
	
	private void setFiltros(HttpServletRequest request) {
		request.setAttribute("tipoUsuarioFiltros", "");
		String busqueda = request.getParameter("busquedaFiltro") == null ? "" : request.getParameter("busquedaFiltro");
		request.setAttribute("busquedaFiltro", busqueda);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorService service = new  PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usuario") == null) {
			//el usuario no esta logueado -> no puede modificar informacion de usuarios
			request.setAttribute("mensaje","Se necesita iniciar sesion para completar esta operacion");
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
			
		}else {//if(nickname == null || nickname.trim().isEmpty()) {
			DtUsuario usuarioLogueado = (DtUsuario)session.getAttribute("usuario");
			String nickname = request.getParameter("nickname"); 
			DtUsuario infoUsuarioTarget = null;
			String accion = request.getParameter("accion");
			
			if (accion != null && accion.equals("seguir")) {
				String principal = (String) request.getParameter("nickname1");
				String secundario = (String) request.getParameter("nickname2");
				boolean loSigo = port.loSigo(principal,secundario);//UNO DE LOSO DOS ES NULL
        		DtUsuario infoUsuario;
				try {
					infoUsuario = (DtUsuario) port.mostrarInfoUsuarios(secundario);
					if(!loSigo) {
						port.comenzarSeguir(principal, secundario);
						request.setAttribute("usuario", secundario);
						doGet(request,response);
		        		return;
					} else {
						port.dejarSeguir(principal, secundario);
						request.setAttribute("usuario", secundario);
						doGet(request,response);
		        		return;
					}
				} catch (DatoInvalido_Exception | InstanciaNoExiste_Exception e) {
					request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
	        		request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
	        		forward(request, response);
				}
			}
			
			
			try {
				infoUsuarioTarget = port.mostrarInfoUsuarios(nickname);								
			}catch (InstanciaNoExiste_Exception | DatoInvalido_Exception  e) {
				request.setAttribute("mensaje","El nickname del usuario a modificar no es valido");
				request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
				return;
				
			}
			
			if( !nickname.equals(usuarioLogueado.getNickname()) ) {
				request.setAttribute("mensaje","No se puede modificar un usuario que no sea el propio");
				request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
				return;
				
			}
			else {
				// Chequeo de seguridad exitoso
				String nombre = request.getParameter("nombre");
				String password = request.getParameter("password");
				if(infoUsuarioTarget.getTipo() == TipoUsuario.AEROLINEA) {
					String descripcion = request.getParameter("descripcion");
					String link = request.getParameter("link");
					String errores = ValidationUtil.validarParametrosAerolinea(nombre, password, descripcion, link);
					if(!errores.isEmpty()) {
						request.setAttribute("mensaje",errores);
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
					else {
						//Validacion existosa
						try {
							port.modificarAerolinea(nickname, password, nombre, descripcion, link);
							request.setAttribute("mensaje","La informacion del usuario "+ nickname +" fue actualizada correctamente");
							request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
							return;
						} catch (DatoInvalido_Exception | InstanciaNoExiste_Exception e) {
							request.setAttribute("mensaje",e.toString());
							request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
							return;
						}
					}
					
					
				}
				else { // Cliente
					String apellido = request.getParameter("apellido");
					String nacionalidad = request.getParameter("nacionalidad");
					String fechaNacimiento = request.getParameter("fechaNacimiento");
					String tipoDocumento = request.getParameter("tipoDocumento");
					String nroDocumento = request.getParameter("nroDocumento");	
					
					String errores = ValidationUtil.validarParametrosCliente(nombre,password,apellido,nacionalidad,fechaNacimiento,tipoDocumento,nroDocumento);
					if(!errores.isEmpty()) {
						System.out.print(errores);
						request.setAttribute("mensaje",errores);
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
					else {
						// Verificacion de datos exitosa
						//TODO: No sé que se está intentando hacer acá con el cambio de tipo
						Date fechaNacimientoParse = null;
						XMLGregorianCalendar fechaNacimientoXML = null;
						try {
							fechaNacimientoParse = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
							GregorianCalendar calendar = new GregorianCalendar();
							calendar.setTime(fechaNacimientoParse);
				            fechaNacimientoXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
						} catch (ParseException e) {
							request.setAttribute("mensaje",e.toString());
							request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
							return;
						} catch (Exception e) {
				            request.setAttribute("mensaje", "Error al convertir la fecha a XMLGregorianCalendar: " + e.getMessage());
				            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
				            return;
				        }

						publicar.TipoDocumento tipoDocumentoParse;
						if(tipoDocumento.toUpperCase().equals(publicar.TipoDocumento.CEDULA.toString().toUpperCase())) {
							tipoDocumentoParse = publicar.TipoDocumento.CEDULA;					
						}
						else{
							tipoDocumentoParse = publicar.TipoDocumento.PASAPORTE;
						}
						try {
							port.modificarCliente(nickname, password, nombre, apellido, fechaNacimientoXML, nacionalidad, tipoDocumentoParse, nroDocumento); //esta dando problemas por el tipo en la forma de la fecha, preguntar sobre que se debe hacer
						} catch (DatoInvalido_Exception | InstanciaNoExiste_Exception e) {
							request.setAttribute("mensaje",e.toString());
							request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
							return;
						}
						// Manda a la pagina de exito
						request.setAttribute("mensaje","La informacion del usuario "+ nickname +" fue actualizada correctamente");
						request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
						return;
					}
				}
			}	
		}
	}
}
