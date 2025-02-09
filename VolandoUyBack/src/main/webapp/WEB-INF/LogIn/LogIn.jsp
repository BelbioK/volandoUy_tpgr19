<%@page import="publicar.*"%>
<%@page import="excepciones.*" %>
<%@page import="logica.controladores.*" %>
<%@page import="logica.interfaces.*" %>
<%@page import="logica.conceptos.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="styles/log-in.css">
<script src="js/login.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<div style="grid-area: content; height: calc(100vh - 90px);">
		<div id="login-root">
			<h2 style="text-align: center;">Bienvenido</h2>
			<form id="loginForm" class="login-form" onsubmit="validation(event)"
				method="post" action="login">
				<!-- Username Input -->
				<div>
					<label for="nickname">Nickname o email:</label> <input type="text"
						id="nickname" name="nickname"
						placeholder="Ingrese su nickname o email" required>
				</div>

				<!-- Password Input -->
				<div>
					<label for="password">Contraseņa:</label> <input type="password"
						id="password" name="password" placeholder="Ingrese su contraseņa"
						required>
				</div>

				<!-- Submit Button -->
				<div>
					<button type="submit" id="boton-login">Login</button>
				</div>
			</form>

			<p id="registrar">
				Aun no tienes cuenta? <a id="registro" href="registro" style="color: #00BFFF; text-decoration: underline;">Registrar</a>
			</p>
		</div>
	</div>
	<%
	String error = (String) request.getAttribute("errorLogin");
	System.out.print(error);
	if (error != null) {
	%>
	<jsp:include page="/WEB-INF/template/error-popup.jsp" />
	<input id="mensaje-error" style="display: none" value="<%=error%>"
		type="hidden" disabled></input>
	<%
	}
	%>

	<script>
    const fields = [
    	{ key: 'nickname', id: 'nickname', validation: (nickname) => nickname.length > 0, mensajeError: 'El campo nickname/email no puede ser vacio' },
    	{ key: 'password', id: 'password', validation: (password) => password.length > 0, mensajeError: 'El campo contrasena no puede ser vacio' },
    ]

    function validation(event) {
    	//console.log($('#root-alta-ruta-vuelo #categoriasSelector').val())
    	const { fieldErrorIds, formIsValid, errorMessages } = validarFormulario(fields)
    	console.log(fieldErrorIds, formIsValid)
    	const additVal = additionalValidation()
    	if (formIsValid && additVal) {
    		return true
    	}
    	else {
    		event.preventDefault()
    		if (!additVal)
    			errorMessages.push('La ciudad de origen y destino deben ser diferentes')
    		setMessage(errorMessages)
    		return false;
    	}
    }

    function additionalValidation() {
    	return true;
    }

    function validarFormulario(fields, getInput = null) {
    	let formIsValid = true
    	let fieldErrorIds = []
    	let errorMessages = []
    	let inputValues = []
    	if (getInput == null) {
    		inputValues = fields.map(field => ({ id: field.id, valFunc: field.validation, value: $('#' + field.id).val(), errMsg: field.mensajeError }))
    	}
    	else {
    		inputValues = getInput(fields.map(field => ({ id: field.id, valFunc: field.validation, errMsg: field.mensajeError })))
    	}
    	inputValues.forEach(({ id, value, valFunc, errMsg }) => {
    		if (!valFunc(value)) {
    			fieldErrorIds.push(id)
    			errorMessages.push(errMsg)
    			$('#' + id).addClass('invalid-value')
    			formIsValid = false
    		}
    		else {
    			$('#' + id).removeClass('invalid-value')
    		}
    	})
    	return { fieldErrorIds, formIsValid, errorMessages }
    }

    // Popup handle
    const popupElem = $("#error-popup-wrapper")
    if(popupElem.length > 0){
    	setMessage($("#mensaje-error").val())
		console.log($("#mensaje-error").val())
    }
  

    </script>
</body>
</html>