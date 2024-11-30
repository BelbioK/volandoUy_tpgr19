package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicar.DtListaPaquetes;
import publicar.DtListaString;
import publicar.DtPaquete;
import publicar.DtUsuario;
import publicar.InstanciaNoExiste_Exception;
import publicar.Publicador;
import publicar.PublicadorService;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

/**
 * Servlet implementation class CompraPaquete
 */
@WebServlet("/comprarPaquete")
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompraPaquete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fabrica fab = Fabrica.getInstancia();
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		Publicador port = service.getPublicadorPort();
		DtListaPaquetes paquetes = port.listarDTPaquetes();// luego de esto se devuelve el costo mal calculado
		Set<DtPaquete> paquetesSet = new HashSet<>(paquetes.getPaquetes());

		request.setAttribute("paquetes", paquetesSet);
		request.getRequestDispatcher("/WEB-INF/ComprarPaquete/ComprarPaquete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		Publicador port = service.getPublicadorPort();
		// Fabrica fab = Fabrica.getInstancia();
		// IControladorPaquetes iCPaquetes = fab.getControladorPaquetes();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario") == null) {
			request.setAttribute("mensaje", ValidationUtil.MensajesDeError.ACCESO_NO_AUTORIZADO);
			request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
		} else {
			DtUsuario usuarioLogueado = (DtUsuario) session.getAttribute("usuario");
			String nickname = usuarioLogueado.getNickname();
			String paqueteCompra = request.getParameter("paqueteSeleccionado");
			Date fechaCompra = new Date();

			String errores = ValidationUtil.validarParametrosCompraPaquete(paqueteCompra, nickname);
			if (!errores.isEmpty()) {
				request.setAttribute("mensaje", errores);
				request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
			} else {
				try {
					DtListaString paqs = port.listarPaquetesNombres();
					if (!paqs.getItems().contains(paqueteCompra)) {
						request.setAttribute("mensaje", "El paquete NO existe");
						request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
					} else {
						try {
							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(fechaCompra);

							// Convert GregorianCalendar to XMLGregorianCalendar
							DatatypeFactory df = DatatypeFactory.newInstance();
							XMLGregorianCalendar xmlGc = df.newXMLGregorianCalendar(gc);

							port.altaCompraPaquete(paqueteCompra, nickname, xmlGc);
							
							//port.altaCompraPaquete(paqueteCompra, nickname, Helper.convertDateToXMLGregorianCalendar(fechaCompra));
						} catch (InstanciaNoExiste_Exception e) {
							System.out.println("El usuario ya compro el paquete PELOTUDITO");
							request.setAttribute("mensaje", "El paquete ya fue comprado por '" + nickname);
							request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request,
									response);
							return;
						} catch (Exception e) {
							request.setAttribute("mesaje", "Ocurrió un error al comprar el paquete.");
							request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request,
									response);
							return;
						}
						request.setAttribute("mensaje", "El paquete " + paqueteCompra + " fue comprado correctamente");
						request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);
						return;
					}
				} catch (Exception e) {
					request.setAttribute("mensaje", "Error al verificar la ruta de la aerolínea");
					request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
					return;
				}
			}
		}
	}

}
