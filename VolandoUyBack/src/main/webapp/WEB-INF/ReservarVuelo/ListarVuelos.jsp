<%@page import="publicar.*"%>
<%@page import="excepciones.*"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<link rel="stylesheet" href="styles/listar-vuelos.css">
<title>Reservar :: Volando.uy</title>
<style>
#side-bar-aplicar-filtros {
	display: none;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />

	<div id="root-listar-vuelos">
		<div style="width: 100%; text-align: center; padding: 1rem;">
			<span style="font-size: 1.5rem;">Seleccione un vuelo</span>
			<form action="<%=request.getContextPath() + "/reservar"%>">
				<input style="display: none" name="aerolinea"
					value="<%=request.getAttribute("aerolinea")%>" />
				<button style="width: 150px;" class="buttonFacha" type="submit">Volver</button>
			</form>
		</div>
		<%
		Set<DtVuelo> vuelos = (Set<DtVuelo>) request.getAttribute("vuelos");

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

		if (vuelos != null && vuelos.size() > 0) {
			for (DtVuelo vuelo : vuelos) {
		%>
		<a class="ancla-contenedor" href="?vuelo=<%=vuelo.getNombre()%>">
			<div class="card" id='card-<%=vuelo.getNombre()%>'>
				<div class="card-image">
					<img src="./assets/imagenes/<%=vuelo.getImagen()%>"
						alt="foto-perfil" />
				</div>
				<div class="card-title"><%=vuelo.getNombre()%></div>
				<div class="card-subtitle">
					Fecha del vuelo:
					<%=new SimpleDateFormat("dd/MM/yyyy").format(vuelo.getFecha().toGregorianCalendar().getTime())%></div>
				<div class="card-content"></div>
				<div class="card-additionalInfo"></div>
			</div>
		</a>
		<%
		}
		} else {
		%>
		<div>Esta ruta no tiene vuelos disponibles</div>
		<%
		}
		%>
	</div>
</body>
</html>