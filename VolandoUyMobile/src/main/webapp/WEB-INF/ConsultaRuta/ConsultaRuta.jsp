<%@page import="publicar.*"%>
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
	<%SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");%>
	<div id="root-consulta-ruta">
    <div id="info-section">
    
    
    
    	<div class="info-ruta">
                <img src="./assets/imagenes/<%=ruta.getImagen()%>" alt="foto-perfil"/>
                <div class="ruta-general-info">
                	<div style="align-self:center;">
                		<h2 style="display:inline"><%=ruta.getAerolinea() %></h2> 
	                	<%  String color;          
				    	if(ruta.getEstado().toString().equals("CONFIRMADA")){ color="lightgreen";} else if(ruta.getEstado().toString().equals("INGRESADA")){ color="#FFEA70";} else{ color="red";}%>
				        <span style="margin-left:1rem;padding:3px;border-radius: 15px;background-color:<%=color%>;">
							<%=ruta.getEstado() %>
				        </span>
                	</div>
                	<div style="align-self:center;"> <span><%=ruta.getOrigen().getNombre() %> --></span> <span><%=ruta.getDestino().getNombre()%></span></div>
	                <div>
	                   <%=ruta.getDescripcion() %>
	                </div>
	                <div>
		    			<% 
		    				for(DtCategoria c: ruta.getCategorias()){
		    			%>
		    					<span style="margin-right: 5px;background-color: var(--purple-primary);border-radius: 10px;color: #f4f4f9;padding: 5px;"><%=  c.getNombre() %></span>
		    			<%
		    				}
		    			%>
	                        
	                </div>
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
                    <h4 id="card-hora"><%= ruta.getHoraString() %></h4>
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
        		<iframe 
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
        	<a class="ancla-contenedor" href="/VolandoUyMobile/vuelos?vuelo=<%=vuelo.getNombre() %>">
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
