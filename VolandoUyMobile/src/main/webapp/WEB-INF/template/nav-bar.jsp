<%@page import="publicar.DtUsuario"%>
<nav class="navbar">
	<button id="menu-btn" class="menu-btn">
		<img id="menu-btn-icon" style="margin-right:10px" src="./assets/borgir-open.svg" height="40px" width="40px"/>
	</button>
	<script>
		var menuOpened = false;
		const toggleMenu = () => {
			menuOpened = !menuOpened;
			if(menuOpened)
				$("#menu-btn-icon").attr('src',"./assets/borgir-close.svg")
			else
				$("#menu-btn-icon").attr('src',"./assets/borgir-open.svg")
			
			$("#side-bar").toggleClass("open")
		}
		$("#menu-btn").click(toggleMenu);
	</script>
    <a id="header-logo" class="navbar-logo" href="rutas"><img style="margin-right:10px" src="./assets/logo.svg" height="40px" width="40px"/>Volando<span>.uy</span></a>

    <div class="right-links">
        	<%
        		DtUsuario usuario = session.getAttribute("usuario") != null ? (DtUsuario)session.getAttribute("usuario") : new DtUsuario();
        	%>
        	<div id="profile" class="profile">
                 <img src="./assets/imagenes/<%= usuario.getImagen()%>" alt="Profile" class="profile-image">
                 <span class="profile-name"><%= usuario.getNickname()%></span>
                 <div id="profile-menu" class="profile-menu hide">
                     <span id="logout-btn"><a href="login?action=logout">Cerrar la sesion</a></span>
                 </div>
             </div>
    </div>
</nav>