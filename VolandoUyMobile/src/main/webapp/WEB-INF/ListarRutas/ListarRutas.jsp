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
	<link rel="stylesheet" href="styles/listar-rutas.css">
	<title>Rutas :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-rutas">
    	<%
    		Set<DtRutasDeVuelo> rutas = (Set<DtRutasDeVuelo>) request.getAttribute("rutas");
    		List<DtRutasDeVuelo> rutasList = new ArrayList<>(rutas);

	    	// Ordenar la lista por nombre
	    	Collections.sort(rutasList, new Comparator<DtRutasDeVuelo>() {
	    	    @Override
	    	    public int compare(DtRutasDeVuelo r1, DtRutasDeVuelo r2) {
	    	        return r1.getNombre().compareTo(r2.getNombre());
	    	    }
	    	});

	    	//castear a Set
	    	rutas = new LinkedHashSet<>(rutasList);
	    	
    		String filterAero = null;
    		String filterCity = null;
    		int i = 0;
    		if (rutas != null && rutas.size() > 0){
    			for(DtRutasDeVuelo r: rutas) {
    				if(r.getEstado().toString().equals("CONFIRMADA")){

    	%>       	    	
	    	<a class="ancla-contenedor" href="?ruta=<%= r.getNombre() %>">
	            <div class="card" id='card-<%=i%>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%=r.getImagen()%>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%=r.getNombre()%></div>
	                <div class="card-subtitle"><%=r.getOrigen().getNombre() %> --> <%=r.getDestino().getNombre() %></div>
	                <div class="card-content"><%= r.getDescripcionCorta()%></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-usuTipo">
					        <% 
	    						for(DtCategoria c: r.getCategorias()){
			    			%>
			    					<span><%=  c.getNombre() %></span>
			    			<%
			    				}
			    			%>
			    		</slot>
	                </div>
	            </div>
            </a>
    	<% 		
    			i++;
    			}
    		}
    		} else {
    	%>
    			<div>No hay rutas registradas</div>
    	<% } %>
	</div>
</body>
</html>