package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;
import com.volandouyback.model.utils.Helper;

import publicar.DtReserva;
import publicar.DtUsuario;
import publicar.InstanciaNoExiste_Exception;
import publicar.InstanciaRepetida_Exception;

import com.volandouyback.model.utils.ValidationUtil;

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
			} catch (InstanciaNoExiste_Exception e) {
				response.sendError(404);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						include(request,response);
			}
		} else {
			DtReserva res;
			try {
				res = puerto.infoReserva(vuelo,cliente);
			} catch(InstanciaNoExiste_Exception e) {
				request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						forward(request, response);
				return;
			}
			request.setAttribute("reserva", res);
			request.getRequestDispatcher("/WEB-INF/ConsultaReserva/ConsultaReserva.jsp").
					forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String vuelo = request.getParameter("vuelo");
		String cliente = request.getParameter("cliente");
		if(vuelo == null || cliente == null) {
			request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
			request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
					forward(request, response);
			return;
		}
		
		try {
			puerto.checkIn(vuelo, cliente);
			DtReserva res = puerto.infoReserva(vuelo,cliente);
			request.setAttribute("reserva", res);
			request.getRequestDispatcher("/WEB-INF/ConsultaReserva/ConsultaReserva.jsp").
					forward(request, response);
		} catch (InstanciaNoExiste_Exception | InstanciaRepetida_Exception e) {
			request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
            return;
		}
		
	}

}
