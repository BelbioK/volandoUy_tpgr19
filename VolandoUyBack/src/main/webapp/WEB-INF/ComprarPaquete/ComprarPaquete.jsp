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
		<link rel="stylesheet" href="styles/comprar-paquete.css">
		<meta charset="UTF-8">
		<title>Comprar Paquete</title>
		<style>
			#side-bar-aplicar-filtros{
			display: none;
			}
		</style>
	</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<div id="root-listar-vuelos">
		<form id="formCompraPaquete" method="POST" action="comprarPaquete">
            <input type="hidden" id="paqueteSeleccionado" name="paqueteSeleccionado" value="">
		
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
	    		if (paquetes.size() != 0){
	    			for(DtPaquete paquete: paquetes) {
	    	%>
				<a class="ancla-contenedor" onclick="seleccionarPaquete(<%=i%>, '<%= paquete.getNombre() %>')">
		            <div class="card" id='card-<%=i%>'>
		                <div class="card-image">
		                    <img src="./assets/imagenes/<%= paquete.getImagen() %>" style="border-radius: 10px;" width="200px" height="200px" alt="">
		                </div>
		                <div class="card-title"><%= paquete.getNombre() %></div>
		                <div class="subtitle"><%= paquete.getDescuento() %>&#37; de descuento y el precio del paquete es de USD <%= paquete.getCostoAsociado() %></div>
		                <div class="card-content"><%= paquete.getDescripcion() %></div>
		                <div class="card-additionalInfo">
		                    <slot slot="additionalInfo" class="row-categorias">
						        <% for(DtPaqueteRuta dpr:paquete.getRutas()){ %>
						        <span><%=dpr.getRuta()%>, trae <%= dpr.getCantidad()%> pasajes </span>
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
	    	<button id="btn-comprar" disabled>Comprar paquete</button>
		</form>
	</div>
	<script>
	
    function seleccionarPaquete(index, nombrePaquete) {
        // Desmarcar cualquier tarjeta previamente seleccionada
        const tarjetas = document.querySelectorAll('.card');
        tarjetas.forEach(tarjeta => {
            tarjeta.classList.remove('selected');
        });

        // Marcar la tarjeta seleccionada
        const tarjetaSeleccionada = document.getElementById('card-' + index);
        tarjetaSeleccionada.classList.add('selected');

        // Actualizar el campo oculto con el paquete seleccionado
        document.getElementById('paqueteSeleccionado').value = nombrePaquete;

        // Habilitar el bot�n de comprar
        document.getElementById('btn-comprar').disabled = false;
    }
</script>
</body>
</html>