<%@page import="publicar.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashSet"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/listar-usuarios.css">
	<title> Reservar :: Volando.uy</title>
	<style>
	#side-bar-aplicar-filtros{
		display: none;
	}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
   
    <div id="root-listar-usuarios">
	    <div style="width:100%;text-align: center;padding:1rem;">
	        	 <span style="font-size:1.5rem;">Seleccione una aerolinea</span>
	    </div>
    	<%
    	Object attribute = request.getAttribute("aerolineas");
    	Set<DtAerolinea> aerolineas = null;

    	if (attribute instanceof Set<?>) {
    	    aerolineas = (Set<DtAerolinea>) attribute;
    		
    		List<DtAerolinea> usuList = new ArrayList<>(aerolineas);
	    	Collections.sort(usuList, new Comparator<DtUsuario>() {
	    	    @Override
	    	    public int compare(DtUsuario u1, DtUsuario u2) {
	    	        return u1.getNombre().compareTo(u2.getNombre());
	    	    }
	    	});

	    	aerolineas = new LinkedHashSet<>(usuList);
    		
    		int i = 0;
    		if (aerolineas != null && aerolineas.size() > 0){
    			for(DtAerolinea u: aerolineas) {
    				
    	%>     
	    	<a class="ancla-contenedor" href="?aerolinea=<%= u.getNickname() %>">
	            <div class="card" id='card-<%=i%>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%= u.getImagen() %>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%= u.getNickname() %></div>
	                <div class="card-subtitle"><%= u.getNombre() %></div>
	                <div class="card-content"><%= u.getDescripcion() %></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-usuTipo">
					     	<% for(DtRutasDeVuelo r: new HashSet<>(u.getRutasSet())){ %>
					     		<span><%= r.getNombre() %></span>
					     	<%} %>
			    		</slot>
	                </div>
	            </div>
            </a>
    	<% 
    			i++;
    			}
    		} else {
    	%>
    			<div><h1> No hay aerolineas registradas</h1></div>
    	<% }
    	}%>
	</div>
</body>
</html>