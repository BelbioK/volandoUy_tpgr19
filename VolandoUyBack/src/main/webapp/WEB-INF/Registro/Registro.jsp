<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="styles/registro.css">
<script type="text/javascript" src="js/registro.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<div style="grid-area: content; height: calc(100vh - 90px);">
		<div id="registro-root">
			<!-- <h2 style="text-align: center;">Registro</h2> -->
			<form id="registroForm" onsubmit="verificacion(event)" class="registro-form" action="registro" method="POST">
				<div id="datos-usuario">
					<div id="nick-email" class="coso-coso">
						<div class="inputs-form">
							<label class="input-text" for="nickname">Nickname:</label> <input
								type="text" id="nickname" name="nickname"
								placeholder="Ingrese su nickname" required>
								<span id="message-verif-nickname"></span>
						</div>

						<div class="inputs-form">
							<label class="input-text" for="email">Email:</label> <input
								type="text" id="email" name="email"
								placeholder="Ingrese su email" required>
								<span id="message-verif-email"></span>
						</div>
					</div>

					<div id="contrasena-contrasena" class="coso-coso">
						<div class="inputs-form">
							<label class="input-text" for="contrasenia">Contraseña:</label> <input
								type="password" id="contrasenia" name="contrasenia"
								placeholder="Ingrese su contraseña" required>
						</div>

						<div class="inputs-form">
							<label class="input-text" for="contrasenia_repetida">Repita la
								contraseña:</label> <input type="password" id="contrasenia2"
								name="contrasenia_repetida" placeholder="Repita la contraseña" required>
						</div>
					</div>

					<div>
						<div class="inputs-form">
							<label class="input-text" for="imagen">Subir imagen
								(opcional):</label> <input type="file" id="imagen" name="imagen">
						</div>
					</div>

				</div>


				<div id="tipo-usuario">
					<label>Elija su tipo de usuario</label>
					<div id="botones-tipo-usuario" class="coso-coso">
						<button type="button" id="boton-cliente">Cliente</button>
						<button type="button" id="boton-aerolinea">Aerolinea</button>
					</div>
				</div>


				<div id="full-info-cliente" style="display: none">
					<div id="nombre-apellido" class="coso-coso">
						<div class="inputs-form">
							<label class="input-text" for="nombre_cliente">Nombre:</label> <input
								type="text" id="nombreCliente" name="nombre_cliente"
								placeholder="Ingrese su nombre" required>
						</div>

						<div class="inputs-form">
							<label class="input-text" for="apellido">Apellido:</label> <input
								type="text" id="apellido" name="apellido"
								placeholder="Ingrese su apellido" required>
						</div>
					</div>
					<div id="documento-tipo" class="coso-coso">
						<div>
							<div class="inputs-form">
								<label class="input-text" for="tipo_documento">Tipo
									documento:</label> <select id="tipo-documento" name="tipo_documento" style="margin-top: 8px;">
									<option value="Cedula">Cedula</option>
									<option value="Pasaporte">Pasaporte</option>
								</select>
							</div>
						</div>
						<div class="inputs-form">
							<label class="input-text" for="numero_documento">Documento:</label> <input
								type="text" id="documento" name="numero_documento"
								placeholder="Ingrese su documento" required>
						</div>
					</div>
					<div id="fecha-boton" class="coso-coso">
						<div class="inputs-form">
							<label class="input-text" for="fecha_nacimiento">Fecha de
								nacimiento:</label> <input type="date" id="fecha-nacimiento"
								name="fecha_nacimiento"
								placeholder="Ingrese su fecha de nacimiento" required>
						</div>
						<div class="inputs-form">
							<label class="input-text" for="nacionalidad">Nacionalidad:</label>
							<input type="text" id="nacionalidad" name="nacionalidad"
								placeholder="Ingrese su nacionalidad" required>
						</div>
					</div>
					<div id="fecha-boton" style="margin-top: 18px; margin-right: 8px;">
						<button id="aceptarClienteBttn" type="submit">Ingresar Datos</button>
					</div>
				</div>

				<div id="full-info-aerolinea" style="display: none">
					<div id="nombre-link" class="coso-coso">
						<div class="inputs-form">
							<label class="input-text" for="nombre_aerolinea">Nombre:</label> <input
								type="text" id="nombreAerolinea" name="nombre_aerolinea"
								placeholder="Ingrese su nombre" required>
						</div>

						<div class="inputs-form">
							<label class="input-text" for="link">Link a pagina web
								(Opcional):</label> <input type="text" id="link" name="link"
								placeholder="Ingrese su link">
						</div>
					</div>
					<div id="descripcion-form">
						<div>
							<div class="inputs-form">
								<label class="input-text" for="descripcion">Descripcion:</label>
								<textarea id="descripcion" name="descripcion" rows="4" cols="70"
									placeholder="Ingrese su descripcion" required></textarea>
							</div>
						</div>
					</div>
					<div id="fecha-boton" style="margin-top: 18px; margin-right: 8px;">
						<button id="aceptarAerolineaBttn" type="submit">Ingresar Datos</button>
					</div>
				</div>

				<p id="registrar">
					¿Ya tienes una cuenta? <a id="login" href="login">Iniciar
						Sesion</a>
				</p>
				<input id="tipo_usuario" type="hidden" name="tipo"/>
			</form>
		</div>
		<jsp:include page="/WEB-INF/template/error-popup.jsp" />
	</div>
</body>

</html>