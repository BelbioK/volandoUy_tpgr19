package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicar.DtUsuario;

import java.io.IOException;

/**
 * Servlet implementation class HolaServlet
 */
@WebServlet("/home")
public class Home extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
	HttpSession session = request.getSession();
	DtUsuario usuario = (DtUsuario) session.getAttribute("usuario");
	if(usuario == null) {
		response.sendRedirect(request.getContextPath()+"/login");
		return;
	}
	
	response.sendRedirect(request.getContextPath()+"/rutas");
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}

}