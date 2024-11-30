<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.volandouyback.model.utils.ValidationUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Page</title>
<link rel="stylesheet" href="styles/messagePages.css">
<style>
body {
	background-color: var(--red-primary);
}

.button {
	background-color: var(--red-primary);
}

.button:hover {
	background-color: var(--red-hover);
}

.icon {
	filter: invert(78%) sepia(52%) saturate(7259%) hue-rotate(324deg)
		brightness(99%) contrast(89%);
}

.titulo{
	color: var(--red-primary);
}

</style>
</head>
<body>
	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje == null) {
		mensaje = "Ocurrio un error al realizar la operacion!";
	}
	%>

<body>
	<div class="card-wrapper">
		<div class="card">
			<div
				style="border-radius: 200px; height: 200px; width: 200px; background: #F8FAF5; margin: 0 auto;">
				<img class="icon" alt="X" src="assets/error-icon.svg">
			</div>
			<h1 class="titulo">Error!</h1>
			<p class="message"><%=mensaje%></p>
	
			<a class="button" href="home"> Home </a>
		</div>
	</div>
</body>

</body>
</html>