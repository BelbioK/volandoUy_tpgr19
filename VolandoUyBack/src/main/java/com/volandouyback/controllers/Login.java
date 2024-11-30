package com.volandouyback.controllers;

import java.io.IOException;
import java.net.URL;

import com.volandouyback.model.utils.Helper;

import publicar.DatoInvalido_Exception;
import publicar.DtUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public Login() {
		super();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("servlet", "/login");
		String nickname = request.getParameter("nickname");
		DtUsuario sesion = (DtUsuario)request.getSession().getAttribute("usuario");
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		
		if(sesion != null) {
			//Ya estoy logueado
			String action = (String) request.getParameter("action");
			if(action != null && action.equals("logout")) {
				request.getSession(false).invalidate();
			}
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		if(nickname != null && sesion == null) { //Intento de login
			String password = request.getParameter("password");
			try {
				DtUsuario usuario = puerto.login(nickname,password);
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuario);
				response.sendRedirect(request.getContextPath()+"/home");
				return;
			} catch (DatoInvalido_Exception e) {
				System.out.print(e.getMessage());
				request.setAttribute("errorLogin", e.getMessage());
			}
		}
		request.getRequestDispatcher("/WEB-INF/LogIn/LogIn.jsp").
		forward(request, response);
			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
}
