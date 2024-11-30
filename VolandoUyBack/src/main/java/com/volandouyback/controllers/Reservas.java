package com.volandouyback.controllers;

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
import java.util.HashSet;
import java.util.Set;
import java.net.URL;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil.MensajesDeError;

import publicar.DtListaReservas;
import publicar.DtReserva;
import publicar.DtUsuario;
import publicar.InstanciaNoExiste_Exception;

@WebServlet("/reservas")
public class Reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Reservas() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("servlet", "/reservas");
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		
				String PDF = (String) request.getParameter("quieropdf");
				if(PDF!=null)
				if(PDF.equals("si")) {
			//		// Ruta al archivo PDF (puede ser generado dinámicamente o uno existente)
					request.setAttribute("quieropdf", "");
					//PRUEBO HACERLO CON EL SESSION SINO YA PUSE EL INPUT EN CONSULTAR RUTA
					HttpSession session = request.getSession();
					DtUsuario usuarioSesion = (DtUsuario) session.getAttribute("usuario");
					
					DtListaReservas listarReservas;
					try {
						String nombreVuelo = (String) request.getParameter("nombreVuelo");
						
						String pdfDirectoryPath = getServletContext().getRealPath("/resources/pdfs");
						File dir = new File(pdfDirectoryPath);
						if (!dir.exists()) {
						    dir.mkdirs();
						}
						String pdfFilePath = pdfDirectoryPath + File.separator + "archivo.pdf";
						String rutaNombre = (String) request.getParameter("nombreRuta");
					
						puerto.hacerPDF(usuarioSesion.getNickname(),nombreVuelo,rutaNombre,pdfFilePath);
					    
					    File file = new File(pdfFilePath);
					    // Verificar si el archivo existe
					    if (!file.exists()) {
				        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado");
					        return;
					    }
				
					    // Configuración de la respuesta HTTP
					    response.setContentType("application/pdf"); // Tipo de contenido
					    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
					    response.setContentLengthLong(file.length());
				
					    // Leer y enviar el archivo al cliente
					    try (FileInputStream fis = new FileInputStream(file);
					         OutputStream os = response.getOutputStream()) {
					        byte[] buffer = new byte[1024];
					        int bytesRead;
					        while ((bytesRead = fis.read(buffer)) != -1) {
					            os.write(buffer, 0, bytesRead);
					        }
					        return;
					    }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					}
		
		
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String vuelo = request.getParameter("vuelo");
		String cliente = request.getParameter("cliente");
		if(vuelo == null && cliente == null) {
			Set<DtReserva> reservas;
			try {
				reservas = new HashSet<>(puerto.getReservasDeUsuario(usuario.getNickname()).getReservas());
				request.setAttribute("reservas", reservas);
				request.getRequestDispatcher("/WEB-INF/ListarReservas/ListarReservas.jsp").
						forward(request, response);
						return;
			} catch (InstanciaNoExiste_Exception e) {
				response.sendError(404);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						include(request,response);
				return;
			}
		} else {
			DtReserva res;
			try {
				res = puerto.infoReserva(vuelo,cliente);
			} catch(InstanciaNoExiste_Exception e) {
				request.setAttribute("mensaje",MensajesDeError.RECURSO_NO_EXISTE);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						forward(request, response);
				return;
			}
			request.setAttribute("reserva", res);
			request.getRequestDispatcher("/WEB-INF/ConsultaReserva/ConsultaReserva.jsp").
					forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
