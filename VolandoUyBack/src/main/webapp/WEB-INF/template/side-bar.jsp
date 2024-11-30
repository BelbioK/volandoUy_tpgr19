<%@page import="publicar.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="java.util.Set"%>

<div class="side-bar" id="side-bar" style="grid-area: sidebar; height: calc(100vh - 90px);">
    
     
    <div id="user-options" class="rounded-rectangle user-options">
    <% String servlet = request.getAttribute("servlet") == null ? "" : (String)request.getAttribute("servlet");  %>
    <% DtUsuario u = (DtUsuario)session.getAttribute("usuario"); %>
    <% if(u == null){ %>
        <a class='link <%= servlet.equals("/usuarios") ? "selected":"" %>' href='<%=request.getContextPath()%>/usuarios'>Usuarios</a>
        <a class='link <%= servlet.equals("/rutas") ? "selected":"" %>' href='<%=request.getContextPath()%>/rutas'>Rutas</a>
        <a class='link <%= servlet.equals("/vuelos") ? "selected":""%>' href='<%=request.getContextPath()%>/vuelos'>Vuelos</a>
        <a class='link <%= servlet.equals("/paquetes") ? "selected":"" %>' href='<%=request.getContextPath()%>/paquetes'>Paquetes</a>
	<% }else if(u.getTipo().equals(TipoUsuario.CLIENTE)){ %>
		<a class='link <%= servlet.equals("/usuarios") ? "selected":"" %>' href='<%=request.getContextPath()%>/usuarios'>Usuarios</a>
        <a class='link <%= servlet.equals("/rutas") ? "selected":"" %>' href='<%=request.getContextPath()%>/rutas'>Rutas</a>
        <a class='link <%= servlet.equals("/vuelos") ? "selected":"" %>' href='<%=request.getContextPath()%>/vuelos'>Vuelos</a>
        <a class='link <%= servlet.equals("/paquetes") ? "selected":"" %>' href='<%=request.getContextPath()%>/paquetes'>Paquetes</a>
		<a class='link <%= servlet.equals("/reservas") ? "selected":"" %>' href='<%=request.getContextPath()%>/reservas'>Reservas</a>
		<a class='link <%= servlet.equals("/reservar") ? "selected":"" %>' href='<%=request.getContextPath()%>/reservar'>Reservar vuelo</a>
		<a class='link <%= servlet.equals("/comprarPaquete") ? "selected":"" %>' href='<%=request.getContextPath()%>/comprarPaquete'>Comprar paquete</a>
	<%}else{ %>
	    <a class='link <%= servlet.equals("/usuarios") ? "selected":"" %>' href='<%=request.getContextPath()%>/usuarios'>Usuarios</a>
        <a class='link <%= servlet.equals("/rutas") ? "selected":"" %>' href='<%=request.getContextPath()%>/rutas'>Rutas</a>
        <a class='link <%= servlet.equals("/vuelos") ? "selected":"" %>' href='<%=request.getContextPath()%>/vuelos'>Vuelos</a>
        <a class='link <%= servlet.equals("/paquetes") ? "selected":"" %>' href='<%=request.getContextPath()%>/paquetes'>Paquetes</a>
		<a class='link <%= servlet.equals("/reservas") ? "selected":"" %>' href='<%=request.getContextPath()%>/reservas'>Reservas</a>
		<a class='link <%= servlet.equals("/agregarRuta") ? "selected":"" %>' href='<%=request.getContextPath()%>/agregarRuta'>Alta ruta</a>
		<a class='link <%= servlet.equals("/agregarVuelo") ? "selected":"" %>' href='<%=request.getContextPath()%>/agregarVuelo'>Agregar vuelo</a>
	<% } %>
    </div>
    <form id="filters" action="<%= request.getContextPath()+servlet %>" class="rounded-rectangle filters">
     <% if(request.getAttribute("busquedaFiltro") != null){ %>
    	<% Set<DtAerolinea> aerolineas = (Set<DtAerolinea>) request.getAttribute("aerolineasFiltros"); %>
    	<% String valor = request.getParameter("busquedaFiltro") == null ? "" : request.getParameter("busquedaFiltro"); %>
        <span>Busqueda</span>
        <input id='side-bar-busqueda' name="busquedaFiltro" value="<%= valor %>"/>
    <%} %>
    <% if(request.getAttribute("aerolineasFiltros") != null){ %>
    	<% Set<DtAerolinea> aerolineas = (Set<DtAerolinea>) request.getAttribute("aerolineasFiltros"); %>
    	<% String valor = request.getParameter("aerolineaFiltro") == null ? "" : request.getParameter("aerolineaFiltro"); %>
        <span>Aerolinea</span>
        <select class="filter-select" name="aerolineaFiltro" value="<%= valor %>">
        	<option value=""></option>
            <%for(DtAerolinea aerolinea:aerolineas){ %>
            	<option <%= aerolinea.getNickname().equals(valor) ? "selected" :"" %> value="<%=aerolinea.getNickname()%>"><%=aerolinea.getNombre() %></option>
            <%} %>
        </select>
    <%} %>
    <% if(request.getAttribute("rutasFiltros") != null){ %>
    	<% Set<DtRutasDeVuelo> rutas = (Set<DtRutasDeVuelo>) request.getAttribute("rutasFiltros"); %>
    	<% String valor = request.getParameter("rutaFiltro") == null ? "" : request.getParameter("rutaFiltro"); %>
        <span>Ruta de vuelo</span>
        <select class="filter-select" name="rutaFiltro" value="<%= valor %>">
        	<option value=""></option>
            <%for(DtRutasDeVuelo ruta:rutas){ %>
            	<option <%= ruta.getNombre().equals(valor) ? "selected" :"" %> value="<%=ruta.getNombre()%>"><%=ruta.getNombre() %></option>
            <%} %>
        </select>
    <%} %>
    <% if(request.getAttribute("vuelosFiltros") != null){ %>
    	<% Set<DtVuelo> vuelos = (Set<DtVuelo>) request.getAttribute("vuelosFiltros"); %>
    	<% String valor = request.getParameter("vueloFiltro") == null ? "" : request.getParameter("vueloFiltro"); %>
        <span>Vuelo</span>
        <select class="filter-select" name="vueloFiltro" value="<%= valor %>">
        	<option value=""></option>
            <%for(DtVuelo vuelo:vuelos){ %>
            	<option <%= vuelo.getNombre().equals(valor) ? "selected" :"" %> value="<%=vuelo.getNombre()%>"><%=vuelo.getNombre() %></option>
            <%} %>
        </select>
    <%} %>
     <% if(request.getAttribute("tipoUsuarioFiltros") != null){ %>
    <% String valor = request.getParameter("tipoUsuarioFiltro") == null ? "" : request.getParameter("tipoUsuarioFiltro"); %>
        <span>Tipo de usuario</span>
        <select class="filter-select" name="tipoUsuarioFiltro" value="<%= valor %>">
        	<option value=""></option>
            <option <%= valor.equals("cliente") ? "selected" :"" %> value="cliente">Cliente</option>
            <option <%= valor.equals("aerolinea") ? "selected" :"" %> value="aerolinea">Aerolinea</option>
        </select>
    <%} %>
    
    <% if(request.getAttribute("categoriasFiltros") != null){ %>
      	<% Set<String> categorias = (Set<String>) request.getAttribute("categoriasFiltros"); %>
        <span>Categorias</span>
	    <select multiple id="side-bar-categorias" name="categoriasFiltro" style="overflow-y: auto;">
	       <%for(String categoria:categorias){ %>
            	<option value="<%=categoria%>"><%=categoria%></option>
            <%} %>
	    </select>
    <%} %>
    
        <button type="submit" id="side-bar-aplicar-filtros" class='btn-aplicar'>Aplicar</button>                  
    </form>
</div>