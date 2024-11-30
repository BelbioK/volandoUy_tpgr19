<%@page import="publicar.DtUsuario"%>
<nav class="navbar" style="grid-area:header; height: 90px;">
    <a id="header-logo" class="navbar-logo" href="home"><img style="margin-right:10px" src="./assets/logo.svg" height="40px" width="40px"/>Volando<span>.uy</span></a>

    <form class="search-container" action="home">
        <input type="text" id="searchInput" class="search-input" name="busqueda" placeholder="Ruta, Paquete">
        <button id="searchButton" class="search-button">
            <svg fill="#000000" height="20px" width="20px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 488.4 488.4" xml:space="preserve"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M0,203.25c0,112.1,91.2,203.2,203.2,203.2c51.6,0,98.8-19.4,134.7-51.2l129.5,129.5c2.4,2.4,5.5,3.6,8.7,3.6 s6.3-1.2,8.7-3.6c4.8-4.8,4.8-12.5,0-17.3l-129.6-129.5c31.8-35.9,51.2-83,51.2-134.7c0-112.1-91.2-203.2-203.2-203.2 S0,91.15,0,203.25z M381.9,203.25c0,98.5-80.2,178.7-178.7,178.7s-178.7-80.2-178.7-178.7s80.2-178.7,178.7-178.7 S381.9,104.65,381.9,203.25z"></path> </g> </g> </g></svg>
        </button>
    </form>

    <div class="right-links">
        <% if(session.getAttribute("usuario") == null){ %>
        	<div style="margin-right:5px; display:flex; flex-direction:column;">
        		<form action="login" method="post">
        			<input name="nickname" value="anarod87" style="display: none">
        			<input name="password" value="bgt5nhy6" style="display: none">
                	<button id="loginCliente" class="button login" type="submit" onclick="window.location.href='login';">Cliente</button>
        		</form>
        		<form action="login" method="post">
        			<input name="nickname" value="zfly" style="display: none">
        			<input name="password" value="r45tgvcf" style="display: none">
	                <button id="loginAerolinea" class="button login"  type="submit" onclick="window.location.href='login';">Aerolineas</button>
        		</form>
            </div>
            <div>
                <button id="log-in" class="button" onclick="window.location.href='login';">Iniciar Sesion</button>
                <button id="registrarme" class="button" onclick="window.location.href='registro';">Registrarme</button>
            </div>
        <%}else{ %>
        	<%
        	DtUsuario usuario = (DtUsuario)session.getAttribute("usuario");
        	%>
        
        	<div id="profile" class="profile">
                 <img src="./assets/imagenes/<%= usuario.getImagen()%>" alt="Profile" class="profile-image">
                 <span class="profile-name"><%= usuario.getNickname()%></span>
                 <div id="profile-menu" class="profile-menu hide">
                     <span id="profile-btn"><a href="usuarios?usuario=<%= usuario.getNickname() %>">Perfil</a></span>
                     <span id="logout-btn"><a href="login?action=logout">Cerrar la sesion</a></span>
                 </div>
             </div>
        <% } %>
    </div>
</nav>