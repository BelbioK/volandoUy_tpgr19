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
	<title>Búsqueda :: Volando.uy</title>
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
    	<form style="width:100%;text-align: center;padding:1rem;">
    	<% String orden = request.getParameter("orden") == null ? "fechaDesc":request.getParameter("orden"); %>
	    	<span>Orden: </span>
	    	<select name="orden" onchange="this.form.submit()">
	    		<option <%= orden.equals("alfabeticoAZ") ? "selected" : "" %> value="alfabeticoAZ">Alfabético A-Z</option>
	    		<option <%= orden.equals("alfabeticoZA") ? "selected" : "" %> value="alfabeticoZA">Alfabético Z-A</option>
	    		<option <%= orden.equals("fechaDesc") ? "selected" : "" %> value="fechaDesc">Fecha descendiente</option>
	    		<option <%= orden.equals("fechaAsc") ? "selected" : "" %> value="fechaAsc">Fecha ascendiente</option>
	    	</select>
	    	<input style="display: none" name="busqueda"
					value="<%=request.getParameter("busqueda")%>" />
	    </form>
    	<%
    		int i = 0;
    		ArrayList<Object> list = (ArrayList<Object>)request.getAttribute("list");
    		if (list != null && list.size() > 0){
    			for(Object l: list) {
    				if(l.getClass().equals(DtRutasDeVuelo.class)){
    					DtRutasDeVuelo r = (DtRutasDeVuelo) l;
    				
    	%>       	    	
	    	<a class="ancla-contenedor" href="rutas?ruta=<%= r.getNombre() %>">
	            <div class="card" id='card-<%=i%>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%=r.getImagen()%>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%=r.getNombre()+" -- "+new SimpleDateFormat("yyyy-MM-dd").format(r.getFechaAlta().toGregorianCalendar().getTime())%></div>
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
    				}else if(l.getClass().equals(DtPaquete.class)){
    					DtPaquete paquete = (DtPaquete) l;
    					%>
    					<a class="ancla-contenedor" href="paquetes?paquete=<%= paquete.getNombre() %>">
				            <div class="card" id='card-<%=i%>'>
				                <div class="card-image">
				                    <img src="./assets/imagenes/<%= paquete.getImagen() %>" style="border-radius: 10px;" width="200px" height="200px" alt="">
				                </div>
				                <div class="card-title"><%= paquete.getNombre()+" -- "+new SimpleDateFormat("yyyy-MM-dd").format(paquete.getFechaAlta().toGregorianCalendar().getTime()) %></div>
				                <div class="subtitle">Por solo <%= paquete.getCostoAsociado()%> utilizalo hasta <%= paquete.getPeriodoValidez() %> días después de ser comprado</div>
				                <div class="card-content"><%= paquete.getDescripcion().replace("\"", "&quot;") %></div>
				                <div class="card-additionalInfo">
				                    <slot slot="additionalInfo" class="row-usuTipo">
								        <% for(DtPaqueteRuta dpr:paquete.getRutas()){ %>
								        <span><%=dpr.getRuta()%></span>
								    <%} %>
						    		</slot>
				                </div>
				            </div>
			            </a>
    				<% }
	    			i++;
	    		}
    		} else {
    	%>
    			<div>Esta aerolinea no tiene vuelos disponibles</div>
    			<form action="<%= request.getContextPath()+"/reservar"%>">
    				<button type="submit">Volver</button>
    			</form>
    	<% } %>
	</div>
</body>
</html>