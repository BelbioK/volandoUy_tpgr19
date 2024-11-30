<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success Page</title>
    <link rel="stylesheet" href="styles/messagePages.css">
    <style>
		body{
/* 			background-color: var(--green-primary); */
		}
		.button{
			background-color: var(--green-primary);
		}
		.button:hover{
			background-color: var(--green-hover);
		}
		.icon{
			filter: invert(94%) sepia(9%) saturate(1037%) hue-rotate(59deg) brightness(93%) contrast(89%);
			
		}
    </style>
</head>
<body>
<% 
    
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje == null) {
    	mensaje = "La operacion fue realizada correctamente!";
    }
%>
	
    <body>
      <div class="card">
	      <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
	 		<img class="icon" alt="✓" src="assets/success-icon.svg">
	      </div>
	      <h1>Exito!</h1> 
	      <p class="message"><%=mensaje%></p>
	      
	      	<a class="button" href="home"> Home </a>
      </div>
    </body>

</body>
</html>