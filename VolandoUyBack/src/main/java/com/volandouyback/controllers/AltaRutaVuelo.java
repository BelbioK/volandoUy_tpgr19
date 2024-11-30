package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;


import publicar.TipoUsuario;
//import logica.controladores.Fabrica;
import publicar.DtUsuario;
import publicar.InstanciaNoExiste_Exception;
import publicar.InstanciaRepetida_Exception;
import publicar.Publicador;
import publicar.PublicadorService;
import publicar.DatoInvalido_Exception;
import publicar.DtListaString;

import com.volandouyback.model.utils.Helper;
//import logica.interfaces.IControladorVuelos;
import com.volandouyback.model.utils.ValidationUtil;

/**
 * Servlet implementation class AltaRutaVuelo
 */
@WebServlet("/agregarRuta")
public class AltaRutaVuelo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaRutaVuelo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usuario") == null) {
			// Usuario no logueado 
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.USUARIO_NO_LOGUEADO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		} 
		DtUsuario infoUsuario = ((DtUsuario)session.getAttribute("usuario"));
		if(infoUsuario.getTipo() != TipoUsuario.AEROLINEA) {
			// Usuario no logueado 
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.ACCESO_NO_AUTORIZADO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		
		
		//Todo okay
		//Fabrica fab = Fabrica.getInstancia();
		//IControladorVuelos iVuelos = fab.getControladorVuelos();
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
        
        //Request de verificacion de nombre
        String verifNombre = request.getParameter("verifNombre");
		if(verifNombre != null) {
    		try {
    			port.getRutaDeVuelo(verifNombre);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("REPETIDO");
				response.setStatus(200);
			} catch (InstanciaNoExiste_Exception e) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("DISPONIBLE");
				response.setStatus(200);
			} catch (DatoInvalido_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		return;
    	}
        
        
        
        DtListaString categoriasLista = port.listarCategorias();
        Set<String> categorias = new HashSet<>(categoriasLista.getItems());
        DtListaString ciudadesLista = port.listarCiudades();
        Set<String> ciudades = new HashSet<>(ciudadesLista.getItems());
		request.setAttribute("categorias", categorias);
		request.setAttribute("ciudades", ciudades);
		request.getRequestDispatcher("/WEB-INF/AltaRutaVuelo/AltaRutaVuelo.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usuario") == null) {
			// Usuario no logueado 
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.USUARIO_NO_LOGUEADO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		} 
		DtUsuario infoUsuario = ((DtUsuario)session.getAttribute("usuario"));
		if(infoUsuario.getTipo() != TipoUsuario.AEROLINEA) {
			// Usuario no logueado 
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.ACCESO_NO_AUTORIZADO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		//Chequeo de parametros obligatorios
		Enumeration<String> enumParams = request.getParameterNames();
		Set<String> requiredParams = new HashSet<String>(Arrays.asList("nickname","nombre","descripcion","descripcion_corta","hora","costo_turista","costo_ejecutivo","costo_equipaje_extra","ciudad_origen","ciudad_destino"));
		if(hasRequiredParams(requiredParams, enumParams)) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.INFORMACION_OBLIGATORIA_FALTANTE);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
		String nickname = infoUsuario.getNickname();
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String descripcion_corta = request.getParameter("descripcion_corta");
		String hora = request.getParameter("hora");
		String costo_turista = request.getParameter("costo_turista");
		String costo_ejecutivo = request.getParameter("costo_ejecutivo");
		String costo_equipaje_extra = request.getParameter("costo_equipaje_extra");
		String ciudad_origen = request.getParameter("ciudad_origen");
		String ciudad_destino = request.getParameter("ciudad_destino");
		// Opcionales		
		String video = request.getParameter("video");
		String[] categoriasSeleccionadas = request.getParameterValues("categoriasSeleccionadas");
		String imagen = request.getParameter("imagen");
		
		LocalTime horaParsed;
		try {
			horaParsed = LocalTime.parse(hora,DateTimeFormatter.ISO_LOCAL_TIME);			
		}
		catch (DateTimeParseException e) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.FORMATO_FECHA_INVALIDO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		int costo_turistaParsed = Integer.parseInt(costo_turista);
		int costo_ejecutivoParsed = Integer.parseInt(costo_ejecutivo);
		int costo_equipaje_extraParsed = Integer.parseInt(costo_equipaje_extra);
		Set<String> categoriasSeleccionadasParsed = new HashSet<String>();
		if(categoriasSeleccionadas != null && categoriasSeleccionadas.length > 0 ) {
			categoriasSeleccionadasParsed = new HashSet<String>(Arrays.asList(categoriasSeleccionadas));
		}
		String strCategorias ="";
		for(String cat : categoriasSeleccionadasParsed) 
			strCategorias = cat + ";";
		System.out.println("Las categorias de la ruta son: " + strCategorias);
		try {
			port.altaRutaVuelo(nickname, nombre, descripcion, hora , costo_turistaParsed , costo_ejecutivoParsed, costo_equipaje_extraParsed ,ciudad_origen ,ciudad_destino , strCategorias, descripcion_corta,imagen, video);
			request.setAttribute("mensaje","La informacion del usuario "+ nickname +" fue actualizada correctamente");
			request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
			return;			
		} catch (InstanciaRepetida_Exception | InstanciaNoExiste_Exception | DatoInvalido_Exception e) {
			request.setAttribute("mensaje",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
	}
	
	protected boolean hasRequiredParams(Set<String> requiredParams, Enumeration<String> enumParams) {
		Set<String> paramNames = new HashSet<String>();
		while (enumParams.hasMoreElements()) {
			String parameterName = (String) enumParams.nextElement();
			paramNames.add(parameterName);
		}
		for(String param : requiredParams) {
			if(!paramNames.contains(param)) {
				return false;
			}
		}
		return true;
	}
}
