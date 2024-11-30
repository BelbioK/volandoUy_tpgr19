<%@page import="publicar.*"%>
<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="com.volandouyback.model.utils.*" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/consulta-paquete.css">
	<title>Paquetes :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    <% DtPaquete paquete = (DtPaquete)request.getAttribute("paquete"); %>
    <div id="root-consulta-paquete">
	    <div id="info-section">
	        <div class="imagen-nombre-desc">
	            <img id="imagen-paquete"  src="./assets/imagenes/<%= paquete.getImagen() %>">
	            <div>
	                <h2 class="name" id="nombre-paquete"><%= paquete.getNombre() %></h2>
	                <span class="desc" id="desc-paquete"><%= paquete.getDescripcion() %></span>
	            </div>
	        </div>
	        <div class="mini-cards-paquete">
	            <div class="mini-card">
	                <span>Dias de validez</span>
	                <h4 id="card-validez-paquete"><%= paquete.getPeriodoValidez() %></h4>
	            </div>
	            <div class="mini-card">
	                <span>Descuento</span>
	                <h4 id="card-descuento-paquete"><%= paquete.getDescuento() %></h4>
	            </div>
	            <div class="mini-card">
	                <span>Fecha de creacion</span>
	                <h4 id="fecha-creacion-paquete"><%= new SimpleDateFormat("dd/MM/yyyy").format(paquete.getFechaAlta().toGregorianCalendar().getTime()) %></h4>
	            </div>
	        </div>
	    </div>
	    <div id="links-section">
	        <p style="margin-top: 1rem; font-size: 20px;">Aplica en</p>
			<% for(DtPaqueteRuta dpr:paquete.getRutas()){ %>
		        <a class="ancla-contenedor" href="/VolandoUyBack/rutas?ruta=<%= dpr.getRuta() %>">
		            <div class="resource-list">
					    <div id="link-<%=dpr.getRuta() %>" class="resource-item">
						    <img src="./assets/imagenes/<%= dpr.getImagenRuta() %>" alt="imagen cliente" class="resource-image">
						    <span style="padding-left:1rem"><%= dpr.getRuta() %></span>
					    </div>
		            </div>
	            </a>
	        <% } %>
	    </div>
	</div>
</body>
</html>