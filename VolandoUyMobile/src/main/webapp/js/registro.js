

const clientFieldIds = [
	'nombreCliente','apellido','tipo-documento','documento','fecha-nacimiento','nacionalidad',
]
const aerolineaFieldIds =['nombreAerolinea','link','descripcion']

window.addEventListener('load', (event) => {
	// Evento switch cliente -> aerolinea
	$('#boton-cliente').on('click', (event) => {
		aerolineaFieldIds.forEach((id)=>{
			$("#"+id).attr('disabled','disabled')
		})
		clientFieldIds.forEach((id)=>{
			$("#"+id).removeAttr('disabled')
		})
		$("#full-info-cliente").css("display", "block")
		$("#full-info-aerolinea").css("display", "none")
		$("#boton-cliente").css("background-color", "var(--purple-hover)")
		$("#boton-aerolinea").css("background-color", "var(--purple-primary)")
		tipoUsuario = "Cliente"
		$("#tipo_usuario").val(tipoUsuario);
	});
	
	// Evento switch aerolinea -> cliente
	$('#boton-aerolinea').on('click', (event) => {
		clientFieldIds.forEach((id)=>{
		$("#"+id).attr('disabled','disabled')
		})
		aerolineaFieldIds.forEach((id)=>{
			$("#"+id).removeAttr('disabled')
		})
		$("#full-info-cliente").css("display", "none")
		$("#full-info-aerolinea").css("display", "block")
		$("#boton-cliente").css("background-color", "var(--purple-primary)")
		$("#boton-aerolinea").css("background-color", "var(--purple-hover)")
		tipoUsuario = "Aerolinea"
		$("#tipo_usuario").val(tipoUsuario);
	});
});

function verificacion(event){
	let errores = []
	if(tipoUsuario == 'Cliente'){
		errores = verificar_cliente_totalidad_y_enviar()
	}
	else if(tipoUsuario == 'Aerolinea'){
		errores = verificar_aerolinea_totalidad_y_enviar()
	}
	if(errores.length > 0){
		setMessage(errores)
		event.preventDefault()
	}
	else{
		console.log("Usuario creado")
	}
}



const verificar_cliente_totalidad_y_enviar =  () => {
	let mensajes = verificar_nick_email_contrasenia(true)
	let nombre = $("#nombreCliente").val()
	let apellido = $("#apellido").val()
	let tipoDoc = $("#tipoDoc").val()
	let documento = $("#documento").val()
	let nacionalidad = $("#nacionalidad").val()
	let fechaNac = $("#fecha-nacimiento").val()
	const fechaActual = new Date()
	let fechaNacParsed = ""
	try{
		fechaNacParsed = new Date(fechaNac)
	}catch(err){
		mensajes.push("El formato de la fecha no es valido")
	}
	
	if (!nombre) {
		mensajes.push("Falta ingresar un nombre")
	}
	if (!apellido) {
		mensajes.push("Falta ingresar un apellido")
	}
	if (!documento) {
		mensajes.push("Falta ingresar un documento")
	}
	if (!fechaNac) {
		mensajes.push("Falta ingresar una fecha de nacimiento")
	}
	if (!nacionalidad) {
		mensajes.push("Falta ingresar la nacionalidad del cliente")
	}
	if (fechaNac && fechaNacParsed > fechaActual) {
		mensajes.push("La fecha de nacimiento no puede ser mayor a la fecha actual")
	}
	if (tipoDoc == "Cedula" && !validarCedula(documento)) {
		mensajes.push("El formato de la cedula no es correcto")
	}
	if (tipoDoc == "Pasaporte" && !validarPasaporte(documento)) {
		mensajes.push("El formato del pasaporte no es correcto")
	}
	return mensajes
}



const verificar_aerolinea_totalidad_y_enviar = (event) => {
	let mensajes = verificar_nick_email_contrasenia(true)
	let nombre = $("#nombreAerolinea").val()
	let link = $("#link").val()
	let desc = $("#descripcion").val()
	const regex = /^(https?:\/\/)?(www\.)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}(\/[^\s]*)?$/;
	const fechaActual = new Date()
	if (!nombre) {
		mensajes.push("Falta ingresar un nombre")
	}
	if (!desc) {
		mensajes.push("Falta ingresar una descripción")
	}
	if (link && !regex.test(link)) {
		mensajes.push("El link no coincide con el formato")
	}
	return mensajes;
}



function validarCedula(cedula) {
	// Eliminar espacios y caracteres no numéricos
	cedula = cedula.replace(/\D/g, '');
	// Verificar que la cédula tenga 8 dígitos
	if (cedula.length !== 8) {
		return false;
	}
	// Convertir la cédula en un array de números
	let numeros= cedula.split('').map(Number);	

	// Calcular el dígito de control
	const digitoControl = numeros.pop(); // El último dígito es el dígito de control

	// Multiplicadores para el cálculo del dígito de control
	const multiplicadores = [2, 9, 8, 7, 6, 3, 4];
	let suma = 0;
	for (let i = 0; i < 7; i++) {
		suma += numeros[i] * multiplicadores[i];
	}

	// Calcular el dígito de control
	const resto = suma % 10;
	const digitoCalculado = (resto === 0) ? 0 : 10 - resto;

	// Comparar el dígito calculado con el dígito de control
	return digitoCalculado === digitoControl;
}

function validarPasaporte(pasaporte) {
	// Eliminar espacios y caracteres no válidos
	pasaporte = pasaporte.trim();

	// Verificar que el pasaporte tenga entre 6 y 9 caracteres y contenga solo letras y números
	const regex = /^[A-Z0-9]{6,9}$/;
	return regex.test(pasaporte);
}

function verificar_nick_email_contrasenia(abierto = false) {
	const nick = $('#nickname').val();
	const email = $('#email').val();
	const contrasena = $('#contrasenia').val()
	const contrasena2 = $('#contrasenia2').val()
	let mensajes = []
	if (!nick) {
		mensajes.push("Falta ingresar nickname")
	}
	if (!email) {
		mensajes.push("Falta ingresar email")
	}
	if (!contrasena) {
		mensajes.push("Falta ingresar una contraseña")
	}
	const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
	if (!regexEmail.test(email)) {
		mensajes.push("El correo no es válido")
	}
	if (!(contrasena == contrasena2)) {
		mensajes.push("Las contraseñas no coinciden")
	}
	if (!abierto) $("#full-info").html("")
	return mensajes
}



