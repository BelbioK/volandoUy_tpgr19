package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publicar.DtBusquedaRutaYPaquete;
import publicar.DtPaquete;
import publicar.DtRutasDeVuelo;
import publicar.Publicador;
import publicar.PublicadorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.net.URL;

import javax.xml.datatype.XMLGregorianCalendar;

import com.volandouyback.model.utils.Helper;

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
		String busqueda = request.getParameter("busqueda");
		if(busqueda == null) {
			request.setAttribute("servlet", "/home");
			request.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").forward(request, response);
			return;
		}
		PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
		Publicador port = service.getPublicadorPort();
		
		DtBusquedaRutaYPaquete res = port.busquedaGeneral(busqueda);
		
		List<Object> list = new ArrayList<>();
		
		list.addAll(res.getPaquete());
		list.addAll(res.getRutas());
		
		String orden = request.getParameter("orden") == null ? "fechaDesc" : request.getParameter("orden");
		switch(orden) {
			case "alfabeticoAZ"->{
				list.sort(new CompararAlfabetico());
			}
			case "alfabeticoZA"->{
				list.sort(new CompararAlfabetico().reversed());
			}
			case "fechaAsc"->{
				list.sort(new CompararFecha());
			}
			default->{
				list.sort(new CompararFecha().reversed());
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("servlet", "/home");
		request.getRequestDispatcher("/WEB-INF/home/busqueda.jsp").forward(request, response);
		return;
	}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		doGet(request, response);
	}

	class CompararAlfabetico implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			String nombre1 = o1.getClass().equals(DtRutasDeVuelo.class) ? ((DtRutasDeVuelo) o1).getNombre() : ((DtPaquete)o1).getNombre();
			String nombre2 = o2.getClass().equals(DtRutasDeVuelo.class) ? ((DtRutasDeVuelo) o2).getNombre() : ((DtPaquete)o2).getNombre();
			return nombre1.compareTo(nombre2);
		}
	}
	class CompararFecha implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			XMLGregorianCalendar fecha1 = o1.getClass().equals(DtRutasDeVuelo.class) ? ((DtRutasDeVuelo) o1).getFechaAlta() : ((DtPaquete)o1).getFechaAlta();
			XMLGregorianCalendar fecha2 = o2.getClass().equals(DtRutasDeVuelo.class) ? ((DtRutasDeVuelo) o2).getFechaAlta() : ((DtPaquete)o2).getFechaAlta();

			return fecha1.compare(fecha2);
		}
	}
}
