package com.volandouyback.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicar.DtRutasDeVuelo;
import publicar.DtUsuario;
import publicar.TipoUsuario;
import publicar.DatoInvalido_Exception;
import publicar.DtListaRutas;
import publicar.DtListaString;
import publicar.InstanciaNoExiste_Exception;
import publicar.InstanciaRepetida_Exception;
import publicar.Publicador;
import publicar.PublicadorService;

import com.volandouyback.model.utils.Helper;
import com.volandouyback.model.utils.ValidationUtil;

@WebServlet("/agregarVuelo")
public class AltaVuelo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AltaVuelo() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AltaVuelo/AltaVuelo.jsp").forward(request, response);
    }
    
    private XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (Exception e) {
            e.printStackTrace();  // Imprime la traza de la excepción para ver detalles en el log
            return null;  // Retorna null en caso de fallo en la conversión
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            request.setAttribute("mensaje", ValidationUtil.MensajesDeError.USUARIO_NO_LOGUEADO);
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
            return;
        }
        DtUsuario infoUsuario = (DtUsuario) session.getAttribute("usuario");
        if (infoUsuario.getTipo() != TipoUsuario.AEROLINEA) {
            request.setAttribute("mensaje", ValidationUtil.MensajesDeError.ACCESO_NO_AUTORIZADO);
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
            return;
        }
        PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
        
        //Request de verificacion de nombre
        String verifNombre = request.getParameter("verifNombre");
		if(verifNombre != null) {
    		try {
    			port.mostrarInfoVuelo(verifNombre);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("REPETIDO");
				response.setStatus(200);
			} catch (InstanciaNoExiste_Exception e) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("DISPONIBLE");
				response.setStatus(200);
			}
    		return;
    	}
        
        try {
        	DtListaRutas listaRutas = port.listarRutasDeAerolinea(infoUsuario.getNickname());
        	Set<DtRutasDeVuelo> rutas = new HashSet<>(listaRutas.getRutas());
            request.setAttribute("rutas", rutas);
            request.getRequestDispatcher("/WEB-INF/AltaVuelo/AltaVuelo.jsp").forward(request, response);
        } catch (InstanciaNoExiste_Exception e) {
            request.setAttribute("mensaje", "Error al obtener las rutas de la aerolínea.");
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicadorService service = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
        Publicador port = service.getPublicadorPort();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            request.setAttribute("mensaje", ValidationUtil.MensajesDeError.USUARIO_NO_LOGUEADO);
            request.getRequestDispatcher("/WEB-INF/ErrorPage/ErrorPage.jsp").forward(request, response);
            return;
        }

        DtUsuario usuarioLogueado = (DtUsuario) session.getAttribute("usuario");
        String nickname = usuarioLogueado.getNickname();
        String RutaVue = request.getParameter("rutaVuelo");
        String nombreVue = request.getParameter("nombreVuelo");
        String fechaVue = request.getParameter("fechaVuelo");
        String cantEjecutivos = request.getParameter("asientosEjecutivos");
        String cantTuristas = request.getParameter("asientosTurista");
        String fotoVue = request.getParameter("fotoVuelo");
        String duracionHorasVue = request.getParameter("duracionVueloHoras");
        String duracionMinutosVue = request.getParameter("duracionVueloMinutos");
        String errores = ValidationUtil.validarParametrosAltaVuelo(RutaVue, nombreVue, fechaVue, cantEjecutivos, cantTuristas, duracionHorasVue, duracionMinutosVue);
        if (!errores.isEmpty()) {
            request.setAttribute("mensaje", errores);
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
            return;
        }
        if (fotoVue.trim().isEmpty()) {
        	fotoVue = "VU00.jpg";
	    }

        try {
        	DtListaString listaRutas = port.listarRutasDeVuelo(nickname);
            Set<String> rutas = new HashSet<>(listaRutas.getItems());//preguntar si esto funciona de esta manera o si se debe hacer algo para que arreglarlo
            if (!rutas.contains(RutaVue)) {
                request.setAttribute("mensaje", "La ruta NO pertenece a la aerolínea");
                request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaAlta = new Date();
            Date fechaVueParse = sdf.parse(fechaVue);
            
            XMLGregorianCalendar fechaAltaXml = convertDateToXMLGregorianCalendar(fechaAlta);
            XMLGregorianCalendar fechaVueXml = convertDateToXMLGregorianCalendar(fechaVueParse);

            Integer cantEje = Integer.parseInt(cantEjecutivos);
            Integer cantTuri = Integer.parseInt(cantTuristas);
            Integer duracionHoras = Integer.parseInt(duracionHorasVue);
            Integer duracionMinutos = Integer.parseInt(duracionMinutosVue);
            
            port.altaVuelo(nombreVue, fechaVueXml, duracionHoras, duracionMinutos, cantTuri, cantEje, fechaAltaXml, RutaVue, fotoVue);
            request.setAttribute("mensaje", "El vuelo " + nombreVue + " fue creado correctamente");
            request.getRequestDispatcher("/WEB-INF/PaginaExito/PaginaExito.jsp").forward(request, response);

        } catch (InstanciaRepetida_Exception e) {
            request.setAttribute("mensaje", "El nombre del vuelo '" + nombreVue + "' ya existe. Por favor, elija un nombre único.");
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
        } catch (DatoInvalido_Exception e) {
            request.setAttribute("mensaje", "Ocurrió un error al crear el vuelo. Verifique los datos ingresados.");
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
        } catch (InstanciaNoExiste_Exception e) {
            request.setAttribute("mensaje", "Error al verificar la ruta de la aerolínea.");
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("mensaje", "Formato de fecha incorrecto.");
            request.getRequestDispatcher("/WEB-INF/PaginaError/PaginaError.jsp").forward(request, response);
        } 
    }
}
