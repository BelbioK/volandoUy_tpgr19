<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.volandouyback.model.utils.ValidationUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
<link rel="stylesheet" href="styles/messagePages.css">
<% 
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
%>
<style>
body {
	background-color: var(--purple-light);
}

.button {
	background-color: var(--purple-primary);
}

.button:hover {
	background-color: var(--purple-hover);
}

.icon {
	filter: invert(78%) sepia(3%) saturate(4681%) hue-rotate(199deg) brightness(90%) contrast(93%);
}

.titulo{
	color: var(--purple-primary);
}

</style>
</head>
<body>
	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje == null) {
		mensaje = ValidationUtil.MensajesDeError.RECURSO_NO_EXISTE;
	}
	%>

<body>
	<div class="card-wrapper">
		<div class="card">
			<div
				style="border-radius: 200px; height: 200px; width: 200px; background: #F8FAF5; margin: 0 auto;">
				<img class="icon" alt="X" src="assets/warning-icon.svg">
			</div>
			<h1 class="titulo">404</h1>
			<p class="message"><%=mensaje%></p>
	
			<a class="button" href="home"> Home </a>
		</div>
	</div>
</body>

</body>
</html>