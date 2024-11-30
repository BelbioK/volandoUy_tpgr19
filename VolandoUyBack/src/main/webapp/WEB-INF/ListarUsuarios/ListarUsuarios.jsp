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
	<link rel="stylesheet" href="styles/listar-usuarios.css">
	<title> Usuarios :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-listar-usuarios">
    	<%
    		Set<DtUsuario> usuarios = (Set<DtUsuario>) request.getAttribute("usuarios");
    		List<DtUsuario> usuList = new ArrayList<>(usuarios);
	    	Collections.sort(usuList, new Comparator<DtUsuario>() {
	    	    @Override
	    	    public int compare(DtUsuario u1, DtUsuario u2) {
	    	        return u1.getNombre().compareTo(u2.getNombre());
	    	    }
	    	});

	    	usuarios = new LinkedHashSet<>(usuList);
    		
    		int i = 0;
    		if (usuarios.size() > 0){
    			for(DtUsuario u: usuarios) {
    				
    	%>     
	    	<a class="ancla-contenedor" href="?usuario=<%= u.getNickname() %>">
	            <div class="card" id='card-<%=i%>'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%= u.getImagen() %>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%= u.getNickname() %></div>
	                <div class="card-content"><%= u.getTipo() == TipoUsuario.CLIENTE ? ((DtCliente) u).getNombre() + ' ' + ((DtCliente)u).getApellido(): u.getNombre()%></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-usuTipo">
					        <span><%= u.getTipo() %></span>
			    		</slot>
	                </div>
	            </div>
            </a>
    	<% 
    			i++;
    			}
    		} else {
    	%>
    			<div><h1> No hay usuarios registrados</h1></div>
    	<% } %>
	</div>
</body>
</html>