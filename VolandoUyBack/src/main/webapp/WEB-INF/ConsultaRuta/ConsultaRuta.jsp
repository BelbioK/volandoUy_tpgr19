<%@page import="publicar.DtRutasDeVuelo"%>
<%@page import="publicar.DtUsuario"%>
<%@page import="publicar.DtCategoria"%>
<%@page import="publicar.DtVuelo"%>

<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>

<!DOCTYPE>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/consulta-ruta.css">
	<link rel="stylesheet" href="styles/resource-list.css">
	<title>Vuelos :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<%DtRutasDeVuelo ruta = (DtRutasDeVuelo) request.getAttribute("ruta"); %>
    <%DtUsuario usr = (DtUsuario) session.getAttribute("usuario");%>
	<%SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");%>
	<%String puedeFinalizar = (String) request.getAttribute("puedeFinalizar"); %>
	<div id="root-consulta-ruta">
    <div id="info-section">
    
    
    
    	<div class="card">
                <div class="card-image">
                    <img src="./assets/imagenes/<%=ruta.getImagen()%>" alt="foto-perfil"/>
                </div>
                <div class="card-title"><%=ruta.getAerolinea() %></div>
                <div class="card-subtitle"> <span><%=ruta.getOrigen().getNombre() %> --></span> <span><%=ruta.getDestino().getNombre()%></span></div>
                <div class="card-content">
                   <%=ruta.getDescripcion() %>
                </div>
                <div class="card-additionalInfo">
	    			<% 
	    				for(DtCategoria c: ruta.getCategorias()){
	    			%>
	    					<span style="margin-right: 5px;background-color: var(--purple-primary);border-radius: 10px;color: #f4f4f9;padding: 5px;"><%=  c.getNombre() %></span>
	    			<%
	    				}
	    			%>
                        
                </div>
            </div>
    
    
    
       
        <div id="cards">
            <div class="mini-cards-superiores" >
                <div class="mini-card">
                    <span>Fecha</span>
                    <h4 id="card-fecha">
                    <%=formateo.format(ruta.getFechaAlta().toGregorianCalendar().getTime())%></h4>
                </div>
                <div class="mini-card">
                    <span>Hora</span>
                    <h4 id="card-hora"><%=ruta.getHoraString()%></h4>
                </div>
            </div>
            <div class="mini-cards-inferiores">
                <div class="mini-card">
                    <span>Equipaje</span>
                    <h4 id="card-cost-equi"><%=ruta.getCostoEquipaje() %></h4>
                </div>
                <div class="mini-card">
                    <span>Turista</span>
                    <h4 id="card-cost-tur"><%=ruta.getCostoTurista() %></h4>
                </div>
                <div class="mini-card">
                    <span>Ejecutivo</span>
                    <h4 id="card-cost-ejec"><%=ruta.getCostoEjecutivo() %></h4>
                </div>
            </div>
        </div>
        <div id="info-ciudades">
            <div id="info-origen" style="width: 80%">
                <h3 style="text-align: center;">Origen</h3>
                <div id="nombre-origen"><%=ruta.getOrigen().getNombre()%></div>
                <div id="pais-origen"><%=ruta.getOrigen().getPais()%></div>
                <div id="aeropuerto-origen"><%=ruta.getOrigen().getAeropuerto() %></div>
                <div id="sitioWeb-origen"><%=ruta.getOrigen().getPaginaWeb() %></div>
            </div>
            <div id="info-destino" style="width: 80%">
                <h3 style="text-align: center;">Destino</h3>
                <div id="nombre-destino"><%=ruta.getDestino().getNombre()%></div>
                <div id="pais-destino"><%=ruta.getDestino().getPais()%></div>
                <div id="aeropuerto-destino"><%=ruta.getDestino().getAeropuerto() %></div>
                <div id="sitioWeb-destino"><%=ruta.getDestino().getPaginaWeb() %></div>
            </div>
        </div>
        <div id="info-ciudades">
            <div id="info-origen">
                
            </div>
            <div id="info-destino">
                
            </div>
        </div>
    </div>
    <div id="vuelosBar">
    	<%  String color;
    		String colorTexto="black"; 
    	if(ruta.getEstado().toString().equals("CONFIRMADA")){ color="lightgreen";} else if(ruta.getEstado().toString().equals("INGRESADA")){ color="#FFEA70";} else if(ruta.getEstado().toString().equals("RECHAZADA")){ color="red";} else {color="black";colorTexto="red";}%>
	    <div style="display: flex; align-items: center;">
    <div id="EstadoCuadrado" style="padding: 1rem; border-radius: 15px; background-color: <%= color %>; flex-grow: 1; color:<%=colorTexto%>;">
        <%= ruta.getEstado() %>
    </div>
    <% 
    String botonesta = (String) request.getAttribute("puedeFinalizar");
    if(botonesta.equals("true")){%>
    <form action="/VolandoUyBack/rutas" method="get" style="margin-left: 0.5rem; display: flex; align-items: center;">
        <!-- Input oculto para enviar el nombre de la ruta -->
        <input type="hidden" name="finalizar" value="true">
        <input type="hidden" name="ruta" value="<%= ruta.getNombre() %>">
        
        <!-- Botón para enviar el formulario -->
        <button type="submit" style="background-color: #fd3d3d; color: white; border: none; border-radius: 15px; padding: 0.5rem 1rem; display: flex; align-items: center; justify-content: center; font-size: 1rem;cursor: pointer;">
            Finalizar
        </button>
    </form>
    <%} %>
</div>   
        
      	<%
    		String videoURL = ruta.getVideo();
    		if (videoURL != null && !videoURL.isEmpty()) {
        		if (videoURL.contains("watch?v=")) {
            		videoURL = videoURL.replace("watch?v=", "embed/");
        		} else if (videoURL.contains("youtu.be/")) {
                    videoURL = videoURL.replace("youtu.be/", "www.youtube.com/embed/");
                }
        		
		%>
    		<div>
        		<p style="margin-top: 1rem; font-size: 20px;">Video Promocional:</p>
        		<iframe width="560" height="315"
           	 		src="<%= videoURL %>"
            		title="YouTube video player" frameborder="0"
            		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            		allowfullscreen>
        		</iframe>
   			 </div>
			<%
    		}
			%>
			
        <p style="margin-top: 1rem; font-size: 20px;">Utilizada por los vuelos:</p>
          <%
        	Set<DtVuelo> vuelos = new HashSet<>(ruta.getVuelosSet());
        	for (DtVuelo vuelo : vuelos) {
        	%>
        	<a class="ancla-contenedor" href="/VolandoUyBack/vuelos?vuelo=<%=vuelo.getNombre() %>">
               <div class="resource-list">
			      <div id="link-<%=vuelo.getNombre() %>" class="resource-item">
			          <img src="./assets/imagenes/<%= vuelo.getImagen() %>" alt="imagen cliente" class="resource-image">
			             <span style="padding-left:1rem"><%= vuelo.getNombre() %></span>
			      </div>
	            </div>
          </a>
          <%} %>
    </div>
</div>
<script type="module" src="js/card.js"></script>
<script type="module" src="js/resource-list.js"></script>
</body>
