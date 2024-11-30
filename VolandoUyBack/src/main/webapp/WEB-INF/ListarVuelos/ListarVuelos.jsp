<%@page import="publicar.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/listar-vuelos.css">
	<title>Vuelos :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-vuelos">
    	<%  Set<DtVuelo> vuelos = (Set<DtVuelo>) request.getAttribute("vuelos");
    	
	    	List<DtVuelo> vueList = new ArrayList<>(vuelos);
			// Ordenar la lista por nombre
	    	Collections.sort(vueList, new Comparator<DtVuelo>() {
	    	    @Override
	    	    public int compare(DtVuelo v1, DtVuelo v2) {
	    	        return v1.getNombre().compareTo(v2.getNombre());
	    	    }
	    	});
	
	    	//castear a Set
	    	vuelos = new LinkedHashSet<>(vueList);
	    	
    		if (vuelos != null){
    			for(DtVuelo vuelo: vuelos) {
    	%>
    		<a class="ancla-contenedor" href="?vuelo=<%= vuelo.getNombre() %>">
	            <div class="card" id='card-<%= vuelo.getNombre() %>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%= vuelo.getImagen() %>" alt="foto-perfil"/>
	                </div>
	                <div class="card-title"><%= vuelo.getNombre() %></div>
	                <div class="card-subtitle">Fecha del vuelo: <%= new SimpleDateFormat("dd/MM/yyyy").format(vuelo.getFecha().toGregorianCalendar().getTime()) %></div>
	                <div class="card-content"></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-info">
					        <span><%=vuelo.getAerolinea()%></span>
					        <span><%=vuelo.getRuta().getNombre()%></span>
			    		</slot>
	                </div>
	            </div>
            </a>
    	<%
    			}
    		} else {
    	%>
    			<div>No hay vuelos registrados</div>
    	<% } %>
	</div>
</body>
</html>