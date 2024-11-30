<%@page import="publicar.*"%>
<%@page import="java.net.URL"%>
<%@page import="com.volandouyback.model.utils.Helper"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/consulta-vuelo.css">
	<link rel="stylesheet" href="styles/resource-list.css">
	<script type="module" src="js/consulta-vuelo.js"></script>
</head>
<body>

<%
//Variables

PublicadorService servicio = new PublicadorService(new URL(Helper.getProperty("")+"Publicar?wsdl"));
Publicador puerto = servicio.getPublicadorPort();

DtVuelo vuelo = (DtVuelo) request.getAttribute("vuelo");
DtRutasDeVuelo ruta = vuelo.getRuta();
DtUsuario aero = puerto.mostrarInfoUsuarios(vuelo.getAerolinea());
Set<DtReserva> reservas = new HashSet<>(vuelo.getReservas());
DtUsuario usuario = (DtUsuario)session.getAttribute("usuario");
SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
String minVue;
if(vuelo.getMinutosDur() < 10) {
	minVue = "0" + vuelo.getMinutosDur().toString();
} else {
	minVue = vuelo.getMinutosDur().toString();
}
%>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
   
    <div id="root-consulta-vuelo">
	    <div id="info-section">
	        <div class="imagen-nombre-desc">
	            <img id="imagen-vuelo" src="./assets/imagenes/<%= vuelo.getImagen() %>">
	            <div>
	                <div>
	                    <h2 class="name" id="nombre-vuelo"><%= vuelo.getNombre() %></h2>
	                    <span class="dur" id="dur-vuelo"> 
	                    	Duracion del Vuelo: <%= vuelo.getHorasDur() %>:<%= minVue %>
	                    </span>
	                    <br>
	                    <br>
	                    <span class="desc" id="desc-vuelo">
	                    	Fecha de alta: <%= formateo.format(vuelo.getFechaAlta().toGregorianCalendar().getTime())%>
	                    </span>
	                </div>
	                <div class="info-asientos-vuelo">
	                    <div class="mini-card" style="margin-right: 10px;">
	                        <span>Asientos Turista</span>
	                        <h4 id="info-asientos-turista">
	                        	<%= vuelo.getAsientosResTurista() %>/<%= vuelo.getAsientosMaxTurista() %>
	                        </h4>
	                    </div>
	                    <div class="mini-card">
	                        <span>Asientos Ejecutivo</span>
	                        <h4 id="info-asientos-ejecutivo">
	                        	<%= vuelo.getAsientosResEjecutivo() %>/<%= vuelo.getAsientosMaxEjecutivo() %>
	                        </h4>
	                    </div>
	                </div>
	            </div>
	            <div id="links-section">
	                <p style="margin-top: 1rem; font-size: 20px;">Aerolinea</p>
	                <a class="ancla-contenedor" href="/VolandoUyBack/usuarios?usuario=<%= aero.getNickname() %>">
		                <div class="resource-list">
	                		<div id="resource-aerolinea" class="resource-item">
	                        	<img src="./assets/imagenes/<%= aero.getImagen() %>" alt="imagen aerolinea" class="resource-image">
	                            <span style="padding-left:1rem"><%= aero.getNombre() %></span>
	                    	</div>
	            		</div>
            		</a>
	                <p style="margin-top: 1rem; font-size: 20px;">Ruta de vuelo</p>
	                <a class="ancla-contenedor" href="/VolandoUyBack/rutas?ruta=<%= ruta.getNombre() %>">
		                <div class="resource-list">
	                		<div id="resource-aerolinea" class="resource-item">
	                        	<img src="./assets/imagenes/<%= ruta.getImagen() %>" alt="imagen ruta de vuelo" class="resource-image">
	                            <span style="padding-left:1rem"><%= ruta.getNombre() %></span>
	                    	</div>
	            		</div>
            		</a>
	            </div>
	        </div>
	    </div>
	    <div id="info-aerolinea-prop">
	    <% if(usuario != null && usuario.getNickname().equals(aero.getNickname())) {%>
	    	<div id="reservas" class="info-reservas">
                <h2>Reservas</h2>
            	<div id="reservas-lista">
            	<% 	if(reservas != null && reservas.size() == 0) { 
            			%> <div id="reservas"><h2>No hay reservas para este vuelo</h2></div> <% 
            		} else if(reservas != null){
            		for (DtReserva reserva: reservas) {
            			String nickname = reserva.getCliente().getNickname();
            			DtUsuario persona = puerto.mostrarInfoUsuarios(nickname);
            	%>
	            	<div class="resource-list" onclick="mostrarDatos('<%=nickname %>')">
		                <div id="reserva-<%=nickname %>" class="resource-item">
		                	<img src="./assets/imagenes/<%= persona.getImagen() %>" alt="imagen cliente" class="resource-image">
		                	<span style="padding-left:1rem"><%= nickname %></span>
		            	</div>
		            </div>
		        <% }} %>
            	</div>
           	</div>
           	<div id="datos-reserva" class="info-reservas">
            	<h2>Datos de reserva</h2>
            	<div id="datos-reserva-lista">
            	<% 	if(reservas != null && reservas.size() == 0) { 
            			%> <div id="reservas"><h2>No hay reservas para este vuelo</h2></div> <% 
            		} else if(reservas != null){
            		for (DtReserva reserva: reservas) {
            			String nickname = reserva.getCliente().getNickname();
            			DtUsuario persona = puerto.mostrarInfoUsuarios(nickname);
            	%>
            	<div class="datos-<%=nickname %>" style="display: none;">
                        <div>
                            <h4 class="info-reserva-unica">Fecha reserva</h4>
                            <span> <%=formateo.format(reserva.getFechaReserva().toGregorianCalendar().getTime()) %></span>
                        </div>
                        <div>
                            <h4 class="info-reserva-unica">Tipo Asiento</h4>
                            <span> <%=reserva.getTipoAsiento().name() %></span>
                        </div>
                        <div>
                            <h4 class="info-reserva-unica">Equipaje extra</h4>
                            <span> <%=reserva.getEquipajeExtra() %></span>
                        </div>
                        <div>
                            <h4 class="info-reserva-unica">Costo</h4>
                            <span> $<%=reserva.getCosto() %></span>
                        </div>
                        <div>
                            <h4 class="info-reserva-unica">Consultar cliente</h4>
                            <a class="ancla-contenedor" href="/VolandoUyBack/usuarios?usuario=<%= nickname %>">
                            	<div class="resource-list">
			                		<div id="link-<%=nickname %>" class="resource-item">
			                        	<img src="./assets/imagenes/<%= persona.getImagen() %>" alt="imagen cliente" class="resource-image">
			                            <span style="padding-left:1rem"><%= nickname %></span>
			                    	</div>
	            				</div>
                            </a>
                        </div>
            	</div>
            	<% }} %>
            	</div>
            </div>
            <div id="pasajes" class="info-reservas">
                <h2>Pasajes</h2>
            	<div id="nombres-pasajeros"></div>
            	<% 	if(reservas != null && reservas.size() == 0) { 
            			%> <div id="reservas"><h2>No hay reservas para este vuelo</h2></div> <% 
            		} else if(reservas != null){
            		for (DtReserva reserva: reservas) {
            			String nickname = reserva.getCliente().getNickname();
            			DtUsuario persona = puerto.mostrarInfoUsuarios(nickname);
            	%>
            	<div class="datos-<%=nickname %>" style="display: none;">
            		<% 
            			Set<DtPasajero> pasajes = new HashSet<>(reserva.getPasajes());
            			for(DtPasajero pasaje: pasajes){
            		%>
            			<p style="margin: 5px;"><%=pasaje.getNombre()%> <%=pasaje.getApellido()%></p>
            		<% } %>
            	</div>
           			<% } %>
            	<% }} %>
          		</div>
	    
	    </div>
	</div>
	<script type="module" src="js/resource-list.js"></script>
	<script>
	var personaActual = null;
	var personaAnterior = null;

	function mostrarDatos(nickname){
		personaAnterior = personaActual
		if(personaAnterior == null) personaAnterior = ".datos-" + nickname;
		personaActual = ".datos-" + nickname;
		
		$(personaAnterior).css("display", "none")
		$(personaActual).css("display", "block")
	}
	</script>
</body>
</html>