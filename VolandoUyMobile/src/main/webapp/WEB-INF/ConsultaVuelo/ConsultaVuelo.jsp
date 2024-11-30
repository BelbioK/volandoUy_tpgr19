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
	                <div style="text-align: center;">
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
	                <div>
		                <div class="resource-list">
	                		<div id="resource-aerolinea" class="resource-item">
	                        	<img src="./assets/imagenes/<%= aero.getImagen() %>" alt="imagen aerolinea" class="resource-image">
	                            <span style="padding-left:1rem"><%= aero.getNombre() %></span>
	                    	</div>
	            		</div>
            		</div>
	                <p style="margin-top: 1rem; font-size: 20px;">Ruta de vuelo</p>
	                <a class="ancla-contenedor" href="/VolandoUyMobile/rutas?ruta=<%= ruta.getNombre() %>">
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