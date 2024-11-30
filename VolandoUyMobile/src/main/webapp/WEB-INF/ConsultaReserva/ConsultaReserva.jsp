<%@page import="publicar.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/consulta-reserva.css">
	<title>Vuelos :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    <% 
    	DtReserva reserva = (DtReserva) request.getAttribute("reserva");
    	DtCliente cliente = reserva.getCliente();
    	DtVuelo vuelo = reserva.getVuelo();
    	DtRutasDeVuelo ruta = vuelo.getRuta();
    	DtPaquete paquete = reserva.getPaquete();
    %>
    <div id="root-consulta-reserva">
    <div id="seccion-info">
        <div id="seccion-false-card">
            <img id="img-vuelo" src="./assets/imagenes/<%= vuelo.getImagen() %>"/>
            <div style="grid-area:info;display: flex; flex-direction: column; gap: 1rem; align-items:center">
                <h2 id="nombre-vuelo"><%= vuelo.getNombre() %></h2>
                <span id="nombre-ruta"><%= ruta.getNombre() %></span>
                <span id="nombre-aerolinea"><%= ruta.getAerolinea() %></span>
            </div>
            <% boolean didCheckIn = reserva.getFechaCheckIn() != null;
            	if(!didCheckIn){ %>
            <form <%= "method='POST' action=''" %> style="grid-area:button;padding:1rem;">
            	<button class="check-in-btn"><%= "Realizar " %>check-in</button>
            	<input style="display:none" name="cliente" value="<%=cliente.getNickname()%>"/>
            	<input style="display:none" name="vuelo" value="<%=vuelo.getNombre()%>"/>
            </form>
            <%}else{ %>
            	<span style="text-align:center;color:var(--purple-primary);">Consulte su check-in en nuestra web</span>
            <%} %>
        </div>
        <div id="seccion-info-general" style="display: grid; grid-template-columns: 1fr 1fr;">
            <div id="seccion-info-general-datos">
                <h4 class="info-reserva-unica">Fecha reserva</h4>
                <span id="fecha-reserva"><%=new SimpleDateFormat("dd/MM/yyyy").format(reserva.getFechaReserva().toGregorianCalendar().getTime()) %></span>
                <h4 class="info-reserva-unica">Tipo asiento</h4>
                <span id="tipo-asiento"><%= reserva.getTipoAsiento().equals(Asiento.TURISTA) ? "Turista" : "Ejecutivo" %></span>
                <h4 class="info-reserva-unica">Equipaje extra</h4>
                <span id="equipaje-extra"><%=reserva.getEquipajeExtra() %></span>
                <h4 class="info-reserva-unica">Costo</h4>
                <span id="costo"><%=reserva.getCosto() %></span>
                <%if(paquete != null){ %>
	                <h4 class="info-descuento">Descuento</h4>
	                <span id="descuento"><%=paquete.getDescuento()%>% (Se ahorraron <%= reserva.getCosto()*100/(100-paquete.getDescuento()) - reserva.getCosto() %>$)</span>
	            <%} %>
            </div>
            <div id="seccion-info-general-pasajes" style="display: flex; flex-direction: column; gap: 10px;">
                <h4 class="info-reserva-unica">Pasajes</h4>
                <% for(DtPasajero p:reserva.getPasajes()){ %>
			        <span><%=p.getNombre()%> <%=p.getApellido()%></span>
			     <% } %>
            </div>
        </div>
    </div>
    <div id="seccion-links">
        <p style="margin-top: 1rem; font-size: 20px;" id="p-usuario">Usuario</p>
        <div>
		    <div class="resource-list">
				<div id="link-<%=cliente.getNickname() %>" class="resource-item">
					<img src="./assets/imagenes/<%= cliente.getImagen() %>" alt="imagen cliente" class="resource-image">
					<span style="padding-left:1rem"><%= cliente.getNombre() + " " + cliente.getApellido() %></span>
				</div>
		    </div>
        </div>
        <p style="margin-top: 1rem; font-size: 20px;">Ruta de vuelo</p>
        <a class="ancla-contenedor" href="/VolandoUyMobile/rutas?ruta=<%= ruta.getNombre() %>">
		    <div class="resource-list">
				<div id="link-<%=ruta.getNombre() %>" class="resource-item">
					<img src="./assets/imagenes/<%= ruta.getImagen() %>" alt="imagen cliente" class="resource-image">
					<span style="padding-left:1rem"><%= ruta.getNombre() %></span>
				</div>
		    </div>
        </a>
        <p style="margin-top: 1rem; font-size: 20px;">Vuelo</p>
        <a class="ancla-contenedor" href="/VolandoUyMobile/vuelos?vuelo=<%= vuelo.getNombre() %>">
		    <div class="resource-list">
				<div id="link-<%=vuelo.getNombre() %>" class="resource-item">
					<img src="./assets/imagenes/<%= vuelo.getImagen() %>" alt="imagen cliente" class="resource-image">
					<span style="padding-left:1rem"><%= vuelo.getNombre() %></span>
				</div>
		    </div>
        </a>
        <p style="margin-top: 1rem; font-size: 20px;">Paquete</p>
        <% if(paquete != null){ %>
	        <div>
			    <div class="resource-list">
					<div id="link-<%="pquete" %>" class="resource-item">
						<img src="./assets/imagenes/<%= paquete.getImagen() %>" alt="imagen cliente" class="resource-image">
						<span style="padding-left:1rem"><%= paquete.getNombre() %></span>
					</div>
			    </div>
	        </div>
	    <% }else{ %>
        	<span id="no-hay-paquete">No se usaron paquete</span>
    	<%} %>
    </div>
</div>
</body>
</html>