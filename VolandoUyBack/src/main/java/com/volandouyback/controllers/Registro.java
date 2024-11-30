package com.volandouyback.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicar.InstanciaRepetida_Exception;
import publicar.TipoDocumento;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registro() {
		super();
	}
	
	private XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("servlet", "/registro");
//		String tipo = request.getParameter("tipo");
		HttpSession session = request.getSession(false);
		if (session != null  && session.getAttribute("usuario") != null) {
			System.out.print("Usuario logueado");
			request.setAttribute("mensaje", "Ya estas logueado, no puede registrarse nuevamente.");
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		else {
			// Invitado
			request.getRequestDispatcher("/WEB-INF/Registro/Registro.jsp").forward(request, response);
			return;
		}
		
		
		
//		if (tipo == null) {
//			request.getRequestDispatcher("/WEB-INF/Registro/Registro.jsp").forward(request, response);
//		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		HttpSession session = request.getSession(false);
		if (session != null  && session.getAttribute("usuario") != null) {
			request.setAttribute("mensaje", "Ya estas logueado, no puede registrarse nuevamente.");
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			return;
		}
		else {
			String tipo = request.getParameter("tipo");
			if(tipo == null || (!tipo.equals("Cliente") && !tipo.equals("Aerolinea"))) {
				request.setAttribute("mensaje", "El tipo de usuario no fue especificado.");
				request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
				return;
			}
			Enumeration<String> enumParams = request.getParameterNames();
			Set<String> requiredParams = null;
			// Parametros comunes
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String contrasenia = request.getParameter("contrasenia");
			String contrasenia_repetida = request.getParameter("contrasenia_repetida");
			String imagen = request.getParameter("imagen");
			
			if(imagen.trim().isEmpty()) {
				if(tipo.equals("Cliente")) {
					imagen = "USC.jpg";
				}
				else {
					imagen = "USA.jpg";					
				}
			}
			
			if(!contrasenia.equals(contrasenia_repetida)) {
				request.setAttribute("mensaje",ValidationUtil.MensajesDeError.CONTRASENAS_NO_COINCIDEN);
				request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
				return;
			}
			
			if(tipo.equals("Cliente")) {
				requiredParams = new HashSet<String>(Arrays.asList("nickname","email","contrasenia","contrasenia_repetida","nombre_cliente","apellido",
						"tipo_documento","numero_documento","fecha_nacimiento","nacionalidad"));
				if(!hasRequiredParams(requiredParams, enumParams)) {
					request.setAttribute("mensaje",ValidationUtil.MensajesDeError.INFORMACION_OBLIGATORIA_FALTANTE);
					request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
					return;
				}
				else {
					String apellido = request.getParameter("apellido");
					String nombre_cliente = request.getParameter("nombre_cliente");
					String tipo_documento = request.getParameter("tipo_documento");
					String numero_documento = request.getParameter("numero_documento");
					String fecha_nacimiento = request.getParameter("fecha_nacimiento");
					String nacionalidad = request.getParameter("nacionalidad");
					
					// Chequear que la fecha sea valida y parsearla
					Date fecha_nacimiento_parsed = null;
					try {
						fecha_nacimiento_parsed = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nacimiento);
					} catch (ParseException e) {
						request.setAttribute("mansaje","La fecha ingresada es invalida.");
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
					
					// Chequear que el tipo de documento sea valido y parsearlo
					TipoDocumento tipo_documento_parse = null;
					if(tipo_documento.toUpperCase().equals(TipoDocumento.CEDULA.toString().toUpperCase())) {
						tipo_documento_parse = TipoDocumento.CEDULA;
					}
					else if(tipo_documento.toUpperCase().equals(TipoDocumento.PASAPORTE.toString().toUpperCase())){
						tipo_documento_parse = TipoDocumento.PASAPORTE;
					}
					else {
						request.setAttribute("mansaje","El tipo de documento ingresado es invalido.");
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
					// Chequear si las contrasenias son iguales
					if(!contrasenia.equals(contrasenia_repetida)) {
						request.setAttribute("mansaje","El tipo de documento ingresado es invalido.");
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
					
					try {
						puerto.ingresarDatosCliente(nickname, nombre_cliente, email, apellido, convertDateToXMLGregorianCalendar(fecha_nacimiento_parsed), nacionalidad, tipo_documento_parse, numero_documento, contrasenia, imagen, convertDateToXMLGregorianCalendar(new Date()));
					} catch (Exception e) {
						request.setAttribute("mansaje", e.getMessage());
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
				}
			}
			else { // tipo.equals("Aerolinea")
				requiredParams = new HashSet<String>(Arrays.asList("nickname","email","contrasenia", "contrasenia_repetida","nombre_aerolinea","descripcion"));
				if(!hasRequiredParams(requiredParams, enumParams)) {
					request.setAttribute("mensaje",ValidationUtil.MensajesDeError.INFORMACION_OBLIGATORIA_FALTANTE);
					request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
					return;
				}
				else {
					String nombre_aerolinea = request.getParameter("nombre_aerolinea");
					String link = request.getParameter("link");
					String descripcion= request.getParameter("descripcion");
					
					String errores = ValidationUtil.ingresarDatosAerolinea( nickname,  nombre_aerolinea,  email,  descripcion,  link,  contrasenia,  imagen);
					System.out.print(errores);
					try {
						puerto.ingresarDatosAerolinea(nickname, nombre_aerolinea, email,  descripcion, link,  contrasenia, imagen, Helper.convertDateToXMLGregorianCalendar(new Date()));
					} catch (InstanciaRepetida_Exception e) {
						request.setAttribute("mensaje",e.getMessage());
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					} catch (Exception e) {
						request.setAttribute("mensaje",e.getMessage());
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
						return;
					}
				}
			}
			
			// Se dio de alta bien :D 
			request.setAttribute("mensaje","El usuario " + nickname +", se creo correctamente");
			request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
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