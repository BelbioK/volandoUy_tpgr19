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
	<style>
	#side-bar-aplicar-filtros{
		display: none;
	}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-rutas">
    	<div style="width:100%;text-align: center;padding:1rem;">
	    	<span style="font-size:1.5rem;">Seleccione una ruta</span>
	    	<form action="<%= request.getContextPath()+"/reservar"%>">
   				<button style="width:150px;" class="buttonFacha" type="submit">Volver</button>
   			</form>
	    </div>
    	<%
    		Set<DtRutasDeVuelo> rutas = (Set<DtRutasDeVuelo>) request.getAttribute("rutas");
    		System.out.print("AAAAAA mi culo" + rutas.size());
    		List<DtRutasDeVuelo> rutasList = new ArrayList<>();
    		for(DtRutasDeVuelo r:rutas)
    			if(r.getEstado().toString().equals("CONFIRMADA"))
    				rutasList.add(r);

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
    		} else {
    	%>
    			<div>Esta aerolinea no tiene vuelos disponibles</div>
    	<% } %>
	</div>
</body>
</html>