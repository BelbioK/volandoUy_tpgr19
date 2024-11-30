package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicar.TipoUsuario;
import publicar.Asiento;
import publicar.DatoInvalido_Exception;
import publicar.DtAerolinea;
import publicar.DtCliente;
import publicar.DtCompraRuta;
import publicar.DtListaRutas;
import publicar.DtRutasDeVuelo;
import publicar.DtUsuario;
import publicar.DtVuelo;
import publicar.InstanciaNoExiste_Exception;
import publicar.InstanciaRepetida_Exception;
import publicar.Publicador;
import publicar.PublicadorService;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

/**
 * Servlet implementation class ReservarVuelo
 */
@WebServlet("/reservar")
public class ReservarVuelo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservarVuelo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setAttribute("servlet", "/reservar");
		String vuelo = request.getParameter("vuelo");
		if(vuelo != null && !vuelo.isEmpty()) {
			goToFinalForm(request,response,vuelo);
			return;
		}
		
		String ruta = request.getParameter("ruta");
		if(ruta != null && !ruta.isEmpty()) {
			goToListarVuelos(request,response,ruta);
			return;
		}
		
		String aerolinea = request.getParameter("aerolinea");
		if(aerolinea != null && !aerolinea.isEmpty()) {
			goToListarRutas(request,response,aerolinea);
			return;
		}
		
		goToListarAerolineas(request,response);
	}
	private void goToFinalForm(HttpServletRequest request, HttpServletResponse response,String vuelo) throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		try {
			DtVuelo vue = puerto.mostrarInfoVuelo(vuelo);
			request.setAttribute("vuelo", vue);
			
			String nickname = ((DtUsuario)request.getSession().getAttribute("usuario")).getNickname();
			Set<DtCompraRuta> paquetesAplicables = new HashSet<>(puerto.listarPaquetesCompradosParaVuelo(nickname,vuelo).getComprasRutas());
			request.setAttribute("paquetes", paquetesAplicables);
			
			request.getRequestDispatcher("/WEB-INF/ReservarVuelo/ReservarVuelo.jsp").
			forward(request, response);
		} catch (InstanciaNoExiste_Exception exception) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
			request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
					forward(request, response);
			return;
		}
	}
	private void goToListarVuelos(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		try {
			Set<DtVuelo> vuelos = new HashSet<>(puerto.listarVuelosDTDeRuta(ruta).getVuelos());
			request.setAttribute("vuelos", vuelos);
			request.setAttribute("ruta",ruta);
			request.setAttribute("aerolinea", puerto.getRutaDeVuelo(ruta).getAerolinea());
	
			request.getRequestDispatcher("/WEB-INF/ReservarVuelo/ListarVuelos.jsp").
			forward(request, response);
		} catch (InstanciaNoExiste_Exception | DatoInvalido_Exception exception) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
			request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
					forward(request, response);
			return;
		}
	}
	private void goToListarRutas(HttpServletRequest request, HttpServletResponse response, String aerolinea) throws ServletException, IOException {
		PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		Publicador puerto = servicio.getPublicadorPort();
		try {
			DtListaRutas rutas = puerto.listarRutasDeAerolinea(aerolinea);
			Set<DtRutasDeVuelo> rutasSet = new HashSet<>(rutas.getRutas());
			request.setAttribute("rutas", rutasSet);
			request.setAttribute("aerolinea", aerolinea);
			request.getRequestDispatcher("/WEB-INF/ReservarVuelo/ListarRutas.jsp").
			forward(request, response);
		} catch (InstanciaNoExiste_Exception exception) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
			request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
					forward(request, response);
			return;
		}
	}
	private void goToListarAerolineas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		Set<DtAerolinea> aerolineas = new HashSet<>(puerto.listarAerolineasDT().getAerolineas());
		request.setAttribute("aerolineas", aerolineas);

		request.getRequestDispatcher("/WEB-INF/ReservarVuelo/ListarAerolineas.jsp").
		forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		if(usuario.getTipo().equals(TipoUsuario.AEROLINEA)) 
			goToErrorPage(request,response,ValidationUtil.MensajesDeError.ACCESO_NO_AUTORIZADO);
		String vuelo = request.getParameter("vuelo");		
		DtCliente cliente = (DtCliente) usuario;
		String paquete = request.getParameter("paquete");
		Asiento asiento = request.getParameter("tipoAsiento").equals("turista") ? Asiento.TURISTA : Asiento.EJECUTIVO;
		int equipajeExtra = Integer.parseInt(request.getParameter("equipajeExtra"));
		String pasajes = cliente.getNombre() + " " + cliente.getApellido();
		for(String param:request.getParameterMap().keySet()) 
			if(param.contains("nombrePasaje")) {
				String index = param.substring(param.length()-1);
				String nombre = request.getParameter("nombrePasaje"+index);
				String apellido = request.getParameter("apellidoPasaje"+index);
				if(nombre.isBlank() || apellido.isBlank()) {
					goToErrorPage(request,response,"Faltó completar un nombre o apellido");
				}
				pasajes = pasajes + ";" + nombre + " " + apellido;
			}
		
        
		try {
			GregorianCalendar geoCal = new GregorianCalendar();
			geoCal.setTime(new Date());
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(geoCal);
			puerto.altaReserva(vuelo, cliente.getNickname(), paquete, asiento, equipajeExtra, pasajes, xmlGregorianCalendar);
			request.setAttribute("mensaje", "La reserva al vuelo "+vuelo+" fue realizada exitosamente");
			request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
			return;
		} catch (InstanciaNoExiste_Exception | DatoInvalido_Exception | InstanciaRepetida_Exception exception) {
			goToErrorPage(request,response,exception.getMessage());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void goToErrorPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
		request.setAttribute("mensaje",message);
		request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
		return;
	}

}
