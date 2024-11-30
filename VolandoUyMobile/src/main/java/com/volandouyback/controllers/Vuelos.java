package com.volandouyback.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;

import publicar.DtAerolinea;
import publicar.DtListaAerolineas;
import publicar.DtListaVuelos;
import publicar.DtRutasDeVuelo;
import publicar.DtUsuario;
import publicar.DtVuelo;
import publicar.InstanciaNoExiste_Exception;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/vuelos")
public class Vuelos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Vuelos() {
		super();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("servlet", "/vuelos");
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		setFiltros(request);
		publicar.PublicadorService servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		publicar.Publicador puerto = servicio.getPublicadorPort();
		String vuelo = request.getParameter("vuelo");
		
		if(vuelo == null) {
			DtListaVuelos dtv = puerto.listarAllDTVuelos();
			Set<DtVuelo> vuelos = new HashSet<>(dtv.getVuelos());
			Set<DtVuelo> vuelosFiltrados = new HashSet<DtVuelo>();
			
			String ruta = request.getParameter("rutaFiltro");
			boolean hasRutaFiltro = ruta != null && !ruta.isEmpty();
			
			String aerolinea = request.getParameter("aerolineaFiltro");
			boolean hasAerolineaFiltro = aerolinea != null && !aerolinea.isEmpty();
			
			String busqueda = request.getParameter("busquedaFiltro");
			boolean hasBusquedaFiltro = busqueda != null && !busqueda.isEmpty();
			
			for(DtVuelo v:vuelos) 
				if( (!hasBusquedaFiltro || v.getNombre().toLowerCase().contains(busqueda.toLowerCase())) && 
						(!hasRutaFiltro || v.getRuta().getNombre().toLowerCase().equals(ruta.toLowerCase())) &&
						(!hasAerolineaFiltro || v.getAerolinea().toLowerCase().equals(aerolinea.toLowerCase()))
				  )
					vuelosFiltrados.add(v);
			
			request.setAttribute("vuelos", vuelosFiltrados);
			request.getRequestDispatcher("/WEB-INF/ListarVuelos/ListarVuelos.jsp").
					forward(request, response);
		} else {
			publicar.DtVuelo vue;
			try {
				vue = puerto.mostrarInfoVuelo(vuelo);
			} catch(InstanciaNoExiste_Exception e) {
				request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						forward(request, response);
				return;
			}
			request.setAttribute("vuelo", vue);
			request.getRequestDispatcher("/WEB-INF/ConsultaVuelo/ConsultaVuelo.jsp").
					forward(request, response);
		}
	}
	
	private void setFiltros(HttpServletRequest request) {
		publicar.PublicadorService servicio = null;
		try {
			servicio = new publicar.PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		publicar.Publicador puerto = servicio.getPublicadorPort();
		publicar.DtListaRutas rutas = puerto.listarDTRutasDeVuelo();
		Set<DtRutasDeVuelo> rutasFiltros = new HashSet<>(rutas.getRutas());
		request.setAttribute("rutasFiltros", rutasFiltros);
		DtListaAerolineas dta = puerto.listarAerolineasDT();
		Set<DtAerolinea> aerolineasFiltros = new HashSet<>(dta.getAerolineas());
		request.setAttribute("aerolineasFiltros", aerolineasFiltros);
		String busqueda = request.getParameter("busquedaFiltro") == null ? "" : request.getParameter("busquedaFiltro");
		request.setAttribute("busquedaFiltro", busqueda);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);	
	}
}