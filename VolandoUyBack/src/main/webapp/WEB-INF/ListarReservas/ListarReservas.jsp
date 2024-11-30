<%@page import="publicar.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashSet"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/listar-vuelos.css">
	<title>Vuelos :: Volando.uy</title>
	<style>
	#side-bar-aplicar-filtros{
		display: none;
	}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-vuelos">
    	<%
    		Set<DtReserva> reservas = (Set<DtReserva>) request.getAttribute("reservas");
    		DtUsuario u = (DtUsuario)session.getAttribute("usuario");
    		TipoUsuario tu = u.getTipo();
    		
    		// Convertir el Set a una lista
    		List<DtReserva> resList = new ArrayList<>(reservas);

    		// Ordenar la lista por fecha (creciente)
    		Collections.sort(resList, new Comparator<DtReserva>() {
    		    @Override
    		    public int compare(DtReserva r1, DtReserva r2) {
    		        // Comparar las fechas de manera creciente
    		        return r1.getFechaReserva().compare(r2.getFechaReserva());
    		    }
    		});
    		//castear a Set
	    	reservas = new LinkedHashSet<>(resList);
    		
    		
    		if (reservas != null){
    			for(DtReserva reserva: reservas) {
    				
    	%>
    		<a class="ancla-contenedor" href="?vuelo=<%= reserva.getVuelo().getNombre() %>&cliente=<%= reserva.getCliente().getNickname() %>">
	            <div class="card" id='card-<%= reserva.getVuelo().getNombre() + reserva.getCliente().getNickname() %>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%= tu.equals(TipoUsuario.CLIENTE) ? reserva.getVuelo().getImagen() : reserva.getCliente().getImagen()  %>" alt="foto-perfil"/>
	                </div>
	                <div class="card-title"><%= tu.equals(TipoUsuario.CLIENTE) ? reserva.getVuelo().getNombre() : reserva.getCliente().getNickname() %></div>
	                <div class="card-subtitle">
		                <span class='tipoAsiento fecha'>
	                       <%= new SimpleDateFormat("dd/MM/yyyy").format(reserva.getVuelo().getFecha().toGregorianCalendar().getTime()) +" "+reserva.getVuelo().getRuta().getHoraString() %>
	                    </span>
	                    <span class='tipoAsiento <%= reserva.getTipoAsiento().equals(Asiento.TURISTA) ? "turista" : "ejecutivo" %>'>
	                        <%= reserva.getTipoAsiento().equals(Asiento.TURISTA) ? "Turista" : "Ejecutivo" %>
	                    </span>
	                </div>
	                <div class="card-content">
	                	Reserva al vuelo <%=reserva.getVuelo().getNombre() %> con ruta <%= reserva.getVuelo().getRuta().getNombre() %> y <%= reserva.getEquipajeExtra() %> equipajes extra
	                </div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-info">
	                    <% for(DtPasajero pasa:reserva.getPasajes()){ %>
					        <span><%=pasa.getNombre() + pasa.getApellido()%></span>
					     <% } %>
			    		</slot>
	                </div>
	            </div>
            </a>
    	<%
    			}
    		} else {
    	%>
    			<div>No hay reservas para este usuario</div>
    	<% } %>
	</div>
</body>
</html>