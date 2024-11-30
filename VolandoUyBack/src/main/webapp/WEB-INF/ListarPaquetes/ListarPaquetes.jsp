<%@page import="publicar.*"%>
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
	<link rel="stylesheet" href="styles/listar-paquetes.css">
	<title>Paquetes :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-paquetes">
    	<%
    		Set<DtPaquete> paquetes = (Set<DtPaquete>) request.getAttribute("paquetes");
    	
	    	List<DtPaquete> paqList = new ArrayList<>(paquetes);
			// Ordenar la lista por nombre
	    	Collections.sort(paqList, new Comparator<DtPaquete>() {
	    	    @Override
	    	    public int compare(DtPaquete p1, DtPaquete p2) {
	    	        return p1.getNombre().compareTo(p2.getNombre());
	    	    }
	    	});
	
	    	//castear a Set
	    	paquetes = new LinkedHashSet<>(paqList);
    	
    		int i = 0;
    		if (paquetes != null){
    			for(DtPaquete paquete: paquetes) {
    	%>
			<a class="ancla-contenedor" href="?paquete=<%= paquete.getNombre() %>">
	            <div class="card" id='card-<%=i%>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%= paquete.getImagen() %>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%= paquete.getNombre() %></div>
	                <div class="subtitle">Por solo <%= paquete.getCostoAsociado()%> utilizalo hasta <%= paquete.getPeriodoValidez() %> dias despues de ser comprado.</div>
	                <div class="card-content"><%= paquete.getDescripcion().replace("\"", "&quot;") %></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-categorias">
					        <% for(DtPaqueteRuta dpr:paquete.getRutas()){ %>
					        <span><%=dpr.getRuta()%></span>
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
    			<div>No hay Paquetes registrados</div>
    	<% } %>
	</div>
</body>
</html>