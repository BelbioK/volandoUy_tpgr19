<%@page import="publicar.*"%>
<%@page import="excepciones.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="publicar.TipoDocumento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.volandouyback.model.utils.Helper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="styles/consulta-usuario.css">
<link rel="stylesheet" href="styles/resource-list.css">
<script src="js/error-popup.js"></script>

<meta charset="UTF-8">
<title>Alta de Ruta</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<%
	DtUsuario usr = (DtUsuario) request.getAttribute("usuario");
	DtUsuario usuarioSesion = (DtUsuario) session.getAttribute("usuario");
	String loSigo = (String) request.getAttribute("loSigo");
	Set<DtVuelo> vuelosReservaUsuario = (Set<DtVuelo>) request.getAttribute("vuelosReservaUsuario");
	Boolean esPerfilPropio = false;
	if (usuarioSesion != null) {
		esPerfilPropio = usr.getNickname().equals(usuarioSesion.getNickname());
	}
	String usuTipo = usr.getTipo().toString();
	%>
	<div id="root-consulta-usuario">
		<div class="wrapper-consulta-usuario">
			<div id="datos-usuario">
				<div id="usuario-header">
					<img id="imagen-usuario" src="assets/imagenes/<%=usr.getImagen()%>"
						style="border-radius: 10px;" width="200px" height="200px" alt="">
					<div>
						<%if(usuarioSesion!=null && !esPerfilPropio){ ////PONER PATA QUE SE CAMBIE EL COLOR CUANDO TOCO DEJAR DE SEGUIR
						if (loSigo.equals("no")){%>
							<form action="usuarios" method="POST">
								<input type="hidden" id="accion" name="accion" value="seguir">
								<input type="hidden" id="nickname1" name="nickname1" value="<%= usuarioSesion.getNickname()%>">
								<input type="hidden" id="nickname2" name="nickname2" value="<%= usr.getNickname()%>">
								<button id="text-button" class="text-button" onclick="toggleSeguir()" style="display: flex;justify-content: center;padding: 1rem;border-radius: 30px;background-color:var(--purple-primary);">Seguir</button>
							</form>
						<% 		} else{ %>
							<form action="usuarios" method="POST">
								<input type="hidden" id="accion" name="accion" value="seguir">
								<input type="hidden" id="nickname1" name="nickname1" value="<%= usuarioSesion.getNickname()%>">
								<input type="hidden" id="nickname2" name="nickname2" value="<%= usr.getNickname()%>">
								<button id="text-button" class="text-button" onclick="toggleSeguir()" style="display: flex;justify-content: center;padding: 1rem;border-radius: 30px;background-color:var(--purple-primary);">Dejar de Seguir</button>
							</form>
						<%}
						}%>
						<h2 class="nick" id="usuario-text-nickname">
							<%=usr.getNickname()%></h2>
						<span class="name" id="usuario-text-nombre"> <%=usr.getNombre()%>
						</span>
					</div>
				</div>

				<form id="usuario-info" onsubmit="validation(event) " method="POST"
					action="usuarios">
					<div class="info-field">
					
						<label class='rotulo'>Nickname:</label> <input
							id="usuario-nickname" type="text" name="nickname"
							value="<%=usr.getNickname()%>" disabled /> <input type="hidden"
							name="nickname" value="<%=usr.getNickname()%>" />
					</div>
					<div class="info-field">
						<label class="rotulo">Nombre:</label> <input id="usuario-nombre"
							type="text" name="nombre" value="<%=usr.getNombre()%>"
							<%=esPerfilPropio ? "" : "disabled"%> />
					</div>
					<div class="info-field">
						<label class="rotulo">Correo electronico:</label> <input
							id="usuario-correo" type="text" name="mail"
							value="<%=usr.getEmail()%>" disabled />
					</div>
					<div class="info-field">
						<label class="rotulo">Fecha de alta:</label> <input
							id="usuario-fechaAlta" type="text" name="fechaAlta"
							value="<%= new SimpleDateFormat("yyyy-MM-dd").format(usr.getFechaAlta().toGregorianCalendar().getTime()) %>"
							disabled />
					</div>
					<%
					if (esPerfilPropio) {
					%>
					<div class="info-field">
						<label class="rotulo">Contrasenia:</label>
						<div id="usuario-contrasenia-container">
							<input id="usuario-contrasenia" type="password" name="password"
								value="<%=usr.getPassword()%>" />
							<button type="button" id="usuario-form-toggle-contrasenia">Mostrar</button>
						</div>
					</div>
					<%
					}
					%>


					<%
					if (usr.getTipo().toString().equals("AEROLINEA")) {
					%>
					<div class="info-field">
						<label class='rotulo'>Descripcion:</label> 
						<textarea id="usuario-descripcion" name="descripcion" rows="4" cols="75"
    					<%=esPerfilPropio ? "" : "disabled"%> required><%=((DtAerolinea) usr).getDescripcion()%></textarea>
					</div>
					<div class="info-field">
						<label class='rotulo'>Sitio Web:</label> <input id="usuario-link"
							type="text" name="link"
							value="<%=((DtAerolinea) usr).getLink()%>"
							<%=esPerfilPropio ? "" : "disabled"%> />
					</div>
					<%
					} else {
					%>
					<div class="info-field">
						<label class='rotulo'>Apellido:</label> <input
							id="usuario-apellido" type="text" name="apellido"
							value="<%=((DtCliente) usr).getApellido()%>"
							<%=esPerfilPropio ? "" : "disabled"%> />
					</div>
					<div class="info-field">
						<label class='rotulo'>Nacionalidad:</label> <input
							id="usuario-nacionalidad" type="text" name="nacionalidad"
							value="<%=((DtCliente) usr).getNacionalidad()%>"
							<%=esPerfilPropio ? "" : "disabled"%> />
					</div>
					<div class="info-field">
						<label class="rotulo">Fecha de nacimiento:</label> <input
							id="usuario-fechaNacimiento" type="date" name="fechaNacimiento"
							value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((DtCliente) usr).getFechaNacimiento().toGregorianCalendar().getTime())%>"
							<%=esPerfilPropio ? "" : "disabled"%> />
					</div>
					<%
					if (esPerfilPropio) {
					%>
					<div>
						<label class="rotulo">Tipo de documento:</label> <select
							id="usuario-tipoDocumento" name="tipoDocumento">
							<option
								<%=((DtCliente) usr).getTipoDocumento() == TipoDocumento.CEDULA ? "selected" : ""%>
								value="Cedula">Cedula</option>
							<option
								<%=((DtCliente) usr).getTipoDocumento() == TipoDocumento.PASAPORTE ? "selected" : ""%>
								value="Pasaporte">Pasaporte</option>
						</select>
					</div>
					<div class="info-field">
						<label class='rotulo'>Numero de Documento:</label> <input
							id="usuario-numeroDocumento" type="text" name="nroDocumento"
							value="<%=((DtCliente) usr).getNroDocumento()%>" />
					</div>
					<%
					}
					%>
					<%
					}
					if (esPerfilPropio) {
					%>
					<button id="usuario-editar-bttn">Editar</button>
					<%
					}
					%>
				</form>
			</div>
			
			<%
			if (usr.getTipo() == TipoUsuario.CLIENTE && esPerfilPropio) {
			%>

			<div id="usuario-paquetes">
				<h3>Paquetes adquiridos</h3>
				<%
				Set<DtCompraPaquete> paquetes = new HashSet<>(((DtCliente) usr).getPaquetesSet());
				if(paquetes.size() == 0 ){
					%>
					<p>No hay paquetes para mostrar</p>
					<%
				}else{
					for (DtCompraPaquete paq : paquetes) {
					DtPaquete paqInfo = paq.getPaquete();
					%>
					<a class="ancla-contenedor"
						href="/VolandoUyBack/paquetes?paquete=<%=paqInfo.getNombre()%>">
						<div class="resource-list">
							<div id="resource-aerolinea" class="resource-item">
								<img src="./assets/imagenes/<%=paqInfo.getImagen()%>"
									alt="imagen ruta de vuelo" class="resource-image">
								<span style="padding-left: 1rem"><%=paqInfo.getNombre()%></span>
							</div>
						</div>
					</a>
					<%
					}
				}
				%>
			</div>
			<div>
				<div id="usuario-reservas">
					<h3>Reservas de vuelo</h3>
					<%
					Set<DtReserva> reservas = new HashSet<DtReserva>(((DtCliente) usr).getReservas());
					if(reservas.size() == 0 ){
						%>
						<p>No hay reservas para mostrar</p>
						<%
					}else{
						for (DtVuelo vuel : vuelosReservaUsuario) {
						%>
						<a class="ancla-contenedor"
							href="/VolandoUyBack/vuelos?vuelo=<%=vuel.getNombre()%>">
							<div class="resource-list">
								<div id="resource-aerolinea" class="resource-item">
									<img src="./assets/imagenes/<%=vuel.getImagen()%>"
										alt="imagen ruta de vuelo" class="resource-image"> <span
										style="padding-left: 1rem"><%=vuel.getNombre()%></span>
								</div>
							</div>
						</a>
						<%
						}
					}
					%>
				</div>
				<div>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; cursor: pointer; color: #333; padding: 5px 0;">Seguidos</summary>
					    <ul style="list-style-type: none; padding: 0; margin: 10px 0;">
						      <%
						      List<DtUsuario> aux1 = (List<DtUsuario>) request.getAttribute("seguidos");
					        	if(aux1!=null){
						        for (DtUsuario elemento : aux1) {
						            %><li>
						            	<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>	
						              </li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; color: #333; padding: 5px 0;">Seguidores</summary>
					    <ul style="list-style-type: none; margin: 10px 0;">
					        <%
					        List<DtUsuario> aux = (List<DtUsuario>) request.getAttribute("seguidores");
					        	if(aux!=null){
						        for (DtUsuario elemento : aux) {
						            %><li>
						            	<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>
						            </li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
				</div>
			</div>
			<%
			} else if (usr.getTipo() == TipoUsuario.CLIENTE && !esPerfilPropio){
			%>	<div>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; cursor: pointer; color: #333; padding: 5px 0;">Seguidos</summary>
					    <ul style="list-style-type: none; padding: 0; margin: 10px 0;">
						      <%
						      List<DtUsuario> aux1 = (List<DtUsuario>) request.getAttribute("seguidos");
					        	if(aux1!=null){
						        for (DtUsuario elemento : aux1) {
						            %><li>
						            	<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>	
						              </li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; color: #333; padding: 5px 0;">Seguidores</summary>
					    <ul style="list-style-type: none; margin: 10px 0;">
					        <%
					        List<DtUsuario> aux = (List<DtUsuario>) request.getAttribute("seguidores");
					        	if(aux!=null){
						        for (DtUsuario elemento : aux) {
						            %><li>
						            	<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>
						            </li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
				</div>		
			<%}
			if (usr.getTipo() == TipoUsuario.AEROLINEA) {
			%>
			<div style="display:flex;flex-direction:column;">
				<div id="aerolinea-rutas">
					<h3>Rutas generales</h3>
					<%
					Set<DtRutasDeVuelo> rutas = new HashSet<DtRutasDeVuelo>(((DtAerolinea) usr).getRutasSet());
					if(rutas.size() == 0 ){
						%>
						<p>No hay rutas para mostrar</p>
						<%
					}else{
						for (DtRutasDeVuelo ruta : rutas) {
							if (ruta.getEstado() == Estado.CONFIRMADA || esPerfilPropio) {
						%>
						<a class="ancla-contenedor"
							href="/VolandoUyBack/rutas?ruta=<%=ruta.getNombre()%>">
							<div class="resource-list">
								<div id="resource-aerolinea" class="resource-item">
									<img src="./assets/imagenes/<%=ruta.getImagen()%>"
										alt="imagen ruta de vuelo" class="resource-image"> <span
										style="padding-left: 1rem"><%=ruta.getNombre()%></span>
								</div>
							</div>
						</a>
						<%
							}
						}
					}
					%>
				</div>
				
				<div>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; color: #333; padding: 5px 0;">Seguidos</summary>
					    <ul style="list-style-type: none; margin: 10px 0;">
						      <%
						      List<DtUsuario> aux = (List<DtUsuario>) request.getAttribute("seguidos");
					        	if(aux!=null){
						        for (DtUsuario elemento : aux) {
						            %><li>
						            	<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>
						            </li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
					<details style="border: 1px solid #ddd; padding: 10px; border-radius: 15px; background-color: #e6e6e6; margin: 10px 0;">
					    <summary style="font-weight: bold; font-size: 1.1em; cursor: pointer; color: #333; padding: 5px 0;">Seguidores</summary>
					    <ul style="list-style-type: none; padding: 0; margin: 10px 0;">
					        <%
						      aux = (List<DtUsuario>) request.getAttribute("seguidores");
					        	if(aux!=null){
						        for (DtUsuario elemento : aux) {
						            %><li>
										<a class="ancla-contenedor"
											href="/VolandoUyBack/usuarios?usuario=<%=elemento.getNickname()%>">
											<div class="resource-list">
												<div id="resource-seguidos" class="resource-item">
													<img src="./assets/imagenes/<%=elemento.getImagen()%>"
													alt="imagen usuario" class="resource-image"> <span
													style="padding-left: 1rem"><%=elemento.getNombre()%></span>
												</div>
											</div>
										</a>
									</li> 
						        <%}
					        }
					        %>
					    </ul>
					</details>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<jsp:include page="/WEB-INF/template/error-popup.jsp" />
	<script>
	const currentDate = new Date();
	
	$('#usuario-form-toggle-contrasenia').click((e)=>{
        const contrasenia = $('#usuario-contrasenia')
        if(contrasenia.attr('type') == 'password'){
            e.target.innerHTML = `Ocultar`
            contrasenia.attr('type', 'text')
        }else{
            e.target.innerHTML = `Mostrar`
            contrasenia.attr('type', 'password')
        }
    })
    
    
	 const userFields = [
         //{key: 'nickname', id: 'usuario-nickname',  validation: (nickname)=> nickname.length > 0},
         {key: 'contrasenia', id: 'usuario-contrasenia', validation: (contrasenia)=> contrasenia.length > 0, mensajeError:"El campo Contraseña no puede estar vacio"},
         {key: 'nombre', id: 'usuario-nombre', validation: (nombre)=> nombre.length > 0, mensajeError:"El campo Nombre no puede estar vacio"},
         //{key: 'correo', id: 'usuario-correo', validation: (correo)=> correo.length > 0 && correo.includes('@') && /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(correo)},
         //{key: 'fechaAlta', id: 'usuario-fechaAlta', validation: (fechaAlta)=> fechaAlta.length > 0}
     ]
     
     const clientFields = [
         {key: 'apellido', id: 'usuario-apellido', validation: (apellido)=> apellido.length > 0, mensajeError:"El campo Apellido no puede estar vacio"},
         {key: 'fechaNacimiento', id: 'usuario-fechaNacimiento', validation: verfFechaNacimiento, mensajeError:"El campo Fecha de nacimiento no es valido"},
         {key: 'nacionalidad', id: 'usuario-nacionalidad', validation: (nacionalidad)=> nacionalidad.length > 0, mensajeError:"El campo Nacionalidad no puede estar vacio"},
		 {key: 'tipoDocumento', id: 'usuario-tipoDocumento', validation: (tipoDocumento)=> tipoDocumento.length > 0, mensajeError:"El campo Tipo documento no puede estar vacio"},
         {key: 'numeroDocumento', id: 'usuario-numeroDocumento', validation: (numeroDocumento)=> numeroDocumento.length > 0, mensajeError:"El campo Numero de documento no puede estar vacio"}
     ]
     
     const aerolineaFields = [
         {key: 'link',id: 'usuario-link', validation: ()=> true , mensajeError:""},
         {key: 'descripcion', id: 'usuario-descripcion', validation: (descripcion)=> descripcion.length > 0, mensajeError:"El campo descripcion no puede estar vacio"},
     ]
	
	<%if (usr.getTipo() == TipoUsuario.AEROLINEA) {%>
		const fields = [...aerolineaFields,...userFields]
	<%} else {%>
		const fields = [...clientFields,...userFields]
	<%}%>
	
	function validation(event){
		let {fieldErrorIds, formIsValid, errorMessages} = validarFormulario(fields)
		console.log(fieldErrorIds, formIsValid)
		let additionalVerif = true;
		let tipoDocumento = $("#usuario-tipoDocumento").val() 
		let numDocumento = $("#usuario-numeroDocumento").val()
		if(tipoDocumento == "Cedula" && !validarCedula(numDocumento)){
			formIsValid = false
			errorMessages.push("La cedula ingresado no es valida.")
		}
		else if(tipoDocumento == "Pasaporte" && !validarPasaporte(numDocumento)){
			formIsValid = false
			errorMessages.push("El pasaporte ingresado no es valido.")
		}
		
		if(formIsValid && aditionalVerif){
			return true
		}
		else{
			event.preventDefault()
			setMessage(errorMessages)
			return false;
		}
	}

    function validarFormulario(fields){
            let formIsValid = true
            let fieldErrorIds = []
            let errorMessages = []
            //let inputValues = getInputValues(fields.map(field => ({id: field.id, valFunc: field.validation, errMsg: field.mensajeError})))
            let inputValues = fields.map(field => ({id: field.id, valFunc: field.validation, value: $('#'+field.id).val(), errMsg: field.mensajeError}))
            inputValues.forEach(({id,value,valFunc,errMsg}) => {
                if(!valFunc(value)){
                    fieldErrorIds.push(id)
                    errorMessages.push(errMsg)
                    $('#'+id).addClass('invalid-value')
                    formIsValid = false
                }
                else{
					$('#'+id).removeClass('invalid-value')
                }
            })
            return {fieldErrorIds, formIsValid, errorMessages}
	}
    
    function getInputValues(ids){
        let data = []
        ids.forEach(({ id, valFunc }) => {
            data.push({ id: id, value: $('#' + id).val(), valFunc: valFunc });
        });
        return data
    }
    
    function verfFechaNacimiento(fechaIngresada){
		try{
			if(fechaIngresada == "") return false
			const fechaIngresadaParsed = new Date(fechaIngresada);
			const fechaAhora = new Date()
			if(fechaIngresadaParsed >= fechaAhora){
				return false;
			}
		}
		catch{
			return false
		}
		return true;
	}
    
    
    function validarCedula(cedula) {
    	// Eliminar espacios y caracteres no numï¿½ricos
    	cedula = cedula.replace(/\D/g, '');
    	// Verificar que la cï¿½dula tenga 8 dï¿½gitos
    	if (cedula.length !== 8) {
    		return false;
    	}
    	// Convertir la cï¿½dula en un array de nï¿½meros
    	let numeros= cedula.split('').map(Number);	

    	// Calcular el dï¿½gito de control
    	const digitoControl = numeros.pop(); // El ï¿½ltimo dï¿½gito es el dï¿½gito de control

    	// Multiplicadores para el cï¿½lculo del dï¿½gito de control
    	const multiplicadores = [2, 9, 8, 7, 6, 3, 4];
    	let suma = 0;
    	for (let i = 0; i < 7; i++) {
    		suma += numeros[i] * multiplicadores[i];
    	}

    	// Calcular el dï¿½gito de control
    	const resto = suma % 10;
    	const digitoCalculado = (resto === 0) ? 0 : 10 - resto;

    	// Comparar el dï¿½gito calculado con el dï¿½gito de control
    	return digitoCalculado === digitoControl;
    }
    
    function validarPasaporte(pasaporte) {
    	// Eliminar espacios y caracteres no vï¿½lidos
    	pasaporte = pasaporte.trim();

    	// Verificar que el pasaporte tenga entre 6 y 9 caracteres y contenga solo letras y nï¿½meros
    	const regex = /^[A-Z0-9]{6,9}$/;
    	return regex.test(pasaporte);
    }
    function toggleSeguir(){//TENGO QUE PONER PATA QU3 CUANDO TOCO DEJAR DE SEGUIR Y ME SIUGE APAREZCA EL SEGUIR TAMBIEN
    	const textButton = document.getElementById("text-button");
    	if (textButton.textContent ===("Seguir")){
    		textButton.textContent = "Dejar de Seguir";
    		//textButton.style.backgroundColor = #4CAF50;
    	}
    	else { if (textButton.textContent ===("Dejar de Seguir")){
    		textButton.textContent = "Seguir";
    		}
    	else {
    		textButton.textContent = "Dejar de Seguir";
    		}
    	}
    }

	</script>
</body>
<style>
    /* Estilo del contenedor <details> */
    details {
        border: 1px solid #ddd;
        padding: 10px;
        border-radius: 5px;
        background-color: #f9f9f9;
        margin: 10px 0;
        transition: all 0.3s ease; /* Transiciï¿½n suave para el borde */
    }

    /* Estilo del <summary> */
    summary {
        font-weight: bold;
        font-size: 1.1em;
        cursor: pointer;
        color: #333;
        padding: 5px 0;
    }

    /* Estilo del <ul> y animaciï¿½n */
    details[open] ul {
        animation: slideDown 0.5s ease forwards; /* Activar animaciï¿½n al abrir */
    }

    details ul {
        list-style-type: none;
        padding: 0;
        margin: 10px 0;
        max-height: 0;
        overflow: hidden;
        transition: max-height 0.5s ease; /* Transiciï¿½n para que el despliegue sea suave */
    }

    /* Aumenta la altura mï¿½xima del <ul> solo cuando <details> estï¿½ abierto */
    details[open] ul {
        max-height: 500px; /* Altura mï¿½xima suficiente para mostrar el contenido */
    }

    /* Estilo de los elementos de la lista <li> */
    details li {
        background-color: #e6e6e6;
        margin-bottom: 5px;
        border-radius: 3px;
    }

    /* Animaciï¿½n de deslizamiento */
    @keyframes slideDown {
        from {
            opacity: 0;
            transform: translateY(-10px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>
</html>
