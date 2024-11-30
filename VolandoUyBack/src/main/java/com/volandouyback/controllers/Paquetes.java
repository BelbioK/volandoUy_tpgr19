 package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

//import logica.controladores.Fabrica;
import publicar.DtPaquete;
import publicar.DtRutasDeVuelo;
import publicar.InstanciaNoExiste_Exception;
import publicar.Publicador;
import publicar.PublicadorService;
import publicar.DtListaPaquetes;
import publicar.DtListaRutas;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil.MensajesDeError;

/**
 * Servlet implementation class Paquetes
 */
@WebServlet("/paquetes")
public class Paquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paquetes() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("servlet", "/paquetes");
		setFiltros(request);
		String paquete = request.getParameter("paquete");
		//Fabrica fab = Fabrica.getInstancia();
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
		if(paquete == null) {
			DtListaPaquetes paquetes = port.listarDTPaquetes();//aca se rompe el precio
			Set<DtPaquete> paquetesFiltrados = new HashSet<>();
			
			String ruta = request.getParameter("rutaFiltro");
			boolean hasRutaFiltro = ruta != null && !ruta.isEmpty();
			
			String busqueda = request.getParameter("busquedaFiltro");
			boolean hasBusquedaFiltro = busqueda != null && !busqueda.isEmpty();
			
			for(DtPaquete p:paquetes.getPaquetes()) {
				if( (!hasBusquedaFiltro || p.getNombre().toLowerCase().contains(busqueda.toLowerCase())) && 
					(!hasRutaFiltro || p.getRutas().stream().anyMatch(r -> r.getRuta().toLowerCase().equals(ruta.toLowerCase())))
				  )
					paquetesFiltrados.add(p);
			}
			request.setAttribute("paquetes", paquetesFiltrados);
			request.getRequestDispatcher("/WEB-INF/ListarPaquetes/ListarPaquetes.jsp").
					forward(request, response);
		} else {
			DtPaquete paq;
			try {
				paq = port.infoPaquete(paquete);
			} catch(InstanciaNoExiste_Exception e) {
				request.setAttribute("mensaje",MensajesDeError.RECURSO_NO_EXISTE);
				request.getRequestDispatcher("/WEB-INF/NoExiste/NoExiste.jsp").
						forward(request, response);
				return;
			}
			request.setAttribute("paquete", paq);
			request.getRequestDispatcher("/WEB-INF/ConsultaPaquete/ConsultaPaquete.jsp").
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
        
        DtListaRutas rutasFiltros = port.listarDTRutasDeVuelo();
        Set<DtRutasDeVuelo> setRutasFiltro = new HashSet<>(rutasFiltros.getRutas());
		request.setAttribute("rutasFiltros", setRutasFiltro);
		String busqueda = request.getParameter("busquedaFiltro") == null ? "" : request.getParameter("busquedaFiltro");
		request.setAttribute("busquedaFiltro", busqueda);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
