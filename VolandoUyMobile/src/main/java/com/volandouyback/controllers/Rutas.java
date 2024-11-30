package com.volandouyback.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;

//import logica.controladores.Fabrica;
import publicar.DatoInvalido_Exception;
import publicar.DtAerolinea;
import publicar.DtCategoria;
import publicar.DtListaAerolineas;
import publicar.DtListaRutas;
import publicar.DtListaString;
import publicar.DtRutasDeVuelo;
import publicar.DtUsuario;
import publicar.InstanciaNoExiste_Exception;
import publicar.Publicador;
import publicar.PublicadorService;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/rutas")
public class Rutas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Rutas() {
		super();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setAttribute("servlet", "/rutas");
		setFiltros(request);
		String nombreRuta = request.getParameter("ruta");
		//Fabrica fab = Fabrica.getInstancia();
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
		if(nombreRuta == null) {
			DtListaRutas rutas = port.listarDTRutasDeVuelo();
			Set<DtRutasDeVuelo> rutasFiltradas = new HashSet<DtRutasDeVuelo>();
			
			String aerolinea = request.getParameter("aerolineaFiltro");
			boolean hasAerolineaFiltro = aerolinea != null && !aerolinea.isEmpty();
			
			String busqueda = request.getParameter("busquedaFiltro");
			boolean hasBusquedaFiltro = busqueda != null && !busqueda.isEmpty();
			
			String[] categorias = request.getParameter("categoriasFiltro") != null && !request.getParameter("categoriasFiltro").isEmpty() 
									? request.getParameterValues("categoriasFiltro") : new String[0];
			
			for(DtRutasDeVuelo r:rutas.getRutas()) {
				
				boolean validCategorias = true;
				for(String cat:categorias) {
					boolean tieneCategoria = false;
					for(DtCategoria catDT:r.getCategorias())
						tieneCategoria |= catDT.getNombre().equals(cat);
					
					if(!tieneCategoria) {
						validCategorias = false;
						break;
					}
				}
				
				if( (!hasBusquedaFiltro || r.getNombre().toLowerCase().contains(busqueda.toLowerCase())) && 
						(!hasAerolineaFiltro || r.getAerolinea().toLowerCase().equals(aerolinea.toLowerCase())) &&
						(categorias.length == 0 || validCategorias)
				  )
					rutasFiltradas.add(r);
			}
			request.setAttribute("rutas", rutasFiltradas);
			request.getRequestDispatcher("/WEB-INF/ListarRutas/ListarRutas.jsp").
					forward(request, response);
		} else {
			DtRutasDeVuelo ruta;
			try {
				ruta = port.getRutaDeVuelo(nombreRuta);
			} catch(InstanciaNoExiste_Exception e) {
				request.setAttribute("mensaje",ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						forward(request, response);
				return;
			} catch(DatoInvalido_Exception e) {
				response.sendError(404);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						include(request,response);
				return;
				}
			request.setAttribute("ruta", ruta);
			request.getRequestDispatcher("/WEB-INF/ConsultaRuta/ConsultaRuta.jsp").
					forward(request, response);
		}
	}
	
	private void setFiltros(HttpServletRequest request) {
		PublicadorService service = null;
		try {
			service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Publicador port = service.getPublicadorPort();
        DtListaAerolineas aerolineasFiltros = port.listarAerolineasDT();
        Set<DtAerolinea> aerolineasFiltrosSet = new HashSet<>(aerolineasFiltros.getAerolineas());
		request.setAttribute("aerolineasFiltros", aerolineasFiltrosSet);
		DtListaString categoriasFiltros = port.listarCategorias();
		Set<String> categoriasFiltrosSet = new HashSet<>(categoriasFiltros.getItems());
		request.setAttribute("categoriasFiltros", categoriasFiltrosSet);
		String busqueda = request.getParameter("busquedaFiltro") == null ? "" : request.getParameter("busquedaFiltro");
		request.setAttribute("busquedaFiltro", busqueda);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);	
	}
}