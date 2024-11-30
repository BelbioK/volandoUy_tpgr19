package com.volandouyback.model.utils;

import java.util.regex.Pattern;
import publicar.TipoDocumento;

public class ValidationUtil {

    // Regex patterns for validation
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ú\\s]{1,50}$");
    private static final Pattern DOCUMENT_NUMBER_PATTERN = Pattern.compile("^[0-9]+$"); 
    private static final Pattern MAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"); 
    private static final Pattern LINK_PATTERN = Pattern.compile("^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(\\/[^\\s]*)?$"); 
    

    public static String validarParametrosCliente(String nombre, String password, 
                                            String apellido, String nacionalidad, 
                                            String fechaNacimiento, String tipoDocumento, 
                                            String nroDocumento) {

        StringBuilder validationErrors = new StringBuilder();


        if (nombre == null || nombre.trim().isEmpty() || !NAME_PATTERN.matcher(nombre).matches()) {
            validationErrors.append("Nombre invalido: Solo puede contener letras.\n");
        }

        if (password == null || password.trim().isEmpty()) {
            validationErrors.append("Contrasenia invalida: el campo contrasenia no puede ser vacio \n");
        }

        if (apellido == null || apellido.trim().isEmpty() || !NAME_PATTERN.matcher(apellido).matches()) {
            validationErrors.append("Apellido invalido: Solo puede contener letras \n");
        }

        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            validationErrors.append("Nacionalidad invalida: el campo nacionalidad no puede ser vacio.\n");
        }
        
        // (formato YYYY-MM-DD)
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty() || !isValidDate(fechaNacimiento)) {
            validationErrors.append("Fecha de nacimiento invalida.\n");
        }

        if (tipoDocumento == null || tipoDocumento.trim().isEmpty() || (!tipoDocumento.toUpperCase().equals(TipoDocumento.CEDULA.toString().toUpperCase()) && !tipoDocumento.toUpperCase().equals(TipoDocumento.PASAPORTE.toString().toUpperCase()))) { 	
        	validationErrors.append("Tipo de documento invalido.\n");
        }

        if (nroDocumento == null || nroDocumento.trim().isEmpty() || (tipoDocumento.toUpperCase().equals(TipoDocumento.CEDULA.toString().toUpperCase()) && !DOCUMENT_NUMBER_PATTERN.matcher(nroDocumento).matches())) {
            validationErrors.append("Numero de documento invalido\n");
        }

        return validationErrors.toString();
    } 
    public static String validarParametrosAerolinea(String nombre, String password, 
            String descripcion, String link) {
    	StringBuilder validationErrors = new StringBuilder();
    	if (nombre == null || nombre.trim().isEmpty() ) {
            validationErrors.append("Nombre invalido: el campo nombre no puede.\n");
        }

        if (password == null || password.trim().isEmpty()) {
            validationErrors.append("Contrasena invalida: la contrasena no debe ser vacia .\n");
        }

        if(descripcion == null || descripcion.trim().isEmpty()) {
        	validationErrors.append("Descipcion invalida: la descripcion no debe ser vacia.\n");
        }
        return validationErrors.toString();
    	
    }
    
    public static String validarParametrosAltaVuelo(String RutaVue, String nombre, String fecha, 
            String cantEje, String cantTuri, String duracionHoras, String duracionMin) {
    	StringBuilder validationErrors = new StringBuilder();
    	if (RutaVue == null || RutaVue.trim().isEmpty()) {
            validationErrors.append("Ruta invalida: Debe seleccionar una Ruta para el Vuelo.\n");
        }
    	if (nombre == null || nombre.trim().isEmpty()) {
            validationErrors.append("Nombre invalido: El vuelo debe tener un nombre.\n");
        }

        if (fecha == null || fecha.trim().isEmpty() || !isValidDate(fecha)) {
            validationErrors.append("Fecha de Vuelo invalida: la fecha debe ser valida .\n");
        }

        try {
            int eje = Integer.parseInt(cantEje);
            if (eje < 0 || eje > Integer.MAX_VALUE) {
                validationErrors.append("Cantidad de Ejecutivos invalida: debe ser un número válido y no superar el máximo permitido.\n");
            }
        } catch (NumberFormatException e) {
            validationErrors.append("Cantidad de Ejecutivos invalida: debe ser un número válido y no superar el máximo permitido.\n");
        }

        try {
            int turi = Integer.parseInt(cantTuri);
            if (turi < 0 || turi > Integer.MAX_VALUE) {
                validationErrors.append("Cantidad de Turistas invalida: el numero es demasiado grande, por favor cambielo o comuniquese con ayuda.\n");
            }
        } catch (NumberFormatException e) {
            validationErrors.append("Cantidad de Turistas invalida: el numero es demasiado grande, por favor cambielo o comuniquese con ayuda.\n");
        }

        try {
            int horas = Integer.parseInt(duracionHoras);
            if (horas < 0 || horas > Integer.MAX_VALUE) {
                validationErrors.append("Duración en Horas invalida: el numero es demasiado grande, por favor cambielo o comuniquese con ayuda.\n");
            }
        } catch (NumberFormatException e) {
            validationErrors.append("Duración en Horas invalida: el numero es demasiado grande, por favor cambielo o comuniquese con ayuda.\n");
        }

        try {
            int minutos = Integer.parseInt(duracionMin);
            if (minutos < 0 || minutos >= 60) { // Validación adicional para minutos
                validationErrors.append("Duración en Minutos invalida: debe estar entre 0 y 59.\n");
            }
        } catch (NumberFormatException e) {
            validationErrors.append("Duración en Minutos invalida: debe ser un número entero.\n");
        }
        return validationErrors.toString();
    	
    }
    public static String validarParametrosCompraPaquete(String nombrePaquete, String nombreCli) {
    	StringBuilder validationErrors = new StringBuilder();
    	if (nombrePaquete == null || nombrePaquete.trim().isEmpty()) {
            validationErrors.append("Nombre invalido: El nombre del paquete no es valido.\n");
        }

        if (nombreCli == null || nombreCli.trim().isEmpty()) {
            validationErrors.append("Nombre del cliente invalido: el nombre del cliente no debe ser vacio .\n");
        }

        return validationErrors.toString();
    	
    }
    public static String ingresarDatosAerolinea(String nickname, String nombre, String correo, String descripcion, String link, String password, String imagen) {
    	StringBuilder validationErrors = new StringBuilder();
    	if (nickname == null || nickname.trim().isEmpty()) {
            validationErrors.append("Nombre invalido: el campo nickname no puede ser vacio \n");
        }
    	
    	if (nombre == null || nombre.trim().isEmpty() || !NAME_PATTERN.matcher(nombre).matches()) {
            validationErrors.append("Nombre invalido: el campo nombre solo puede contener letras.\n");
        }

        if (password == null || password.trim().isEmpty()) {
            validationErrors.append("Contrasenia invalida: el campo contrasenia no puede ser vacio \n");
        }
        
        if (correo == null || correo.trim().isEmpty() || !MAIL_PATTERN.matcher(correo).matches()) {
            validationErrors.append("Correo invalido.\n");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
        	validationErrors.append("Descripcion invalida: el campo descripcion no puede ser vacio.\n");
        }
        if(link != null && !link.equals("") && !LINK_PATTERN.matcher("link").matches()) {
        	validationErrors.append("Link invalido: el campo link no es valido.\n");
        }
    	
    	
    	return validationErrors.toString();
    }
    
    public static String validacionAltaCliente(String nickname, String nombre, String correo, String apellido,
			String fechaNacimiento, String nacionalidad, String tipoDocumento, String numDocumento, 
			String password, String imagen, String fechaAlta) {
        
    	StringBuilder validationErrors = new StringBuilder();
    	
    	if (nickname == null || nickname.trim().isEmpty()) {
            validationErrors.append("Nombre invalido: el campo nickname no puede ser vacio \n");
        }
    	
    	if (nombre == null || nombre.trim().isEmpty() || !NAME_PATTERN.matcher(nombre).matches()) {
            validationErrors.append("Nombre invalido: el campo nombre solo puede contener letras.\n");
        }

        if (password == null || password.trim().isEmpty()) {
            validationErrors.append("Contrasenia invalida: el campo contrasenia no puede ser vacio \n");
        }

        if (apellido == null || apellido.trim().isEmpty() || !NAME_PATTERN.matcher(apellido).matches()) {
            validationErrors.append("Apellido invalido: el campo apellido solo puede contener letras \n");
        }

        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            validationErrors.append("Nacionalidad invalida: el campo nacionalidad no puede ser vacio.\n");
        }
        
        // (formato YYYY-MM-DD)
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty() || !isValidDate(fechaNacimiento)) {
            validationErrors.append("Fecha de nacimiento invalida.\n");
        }

        if (tipoDocumento == null || tipoDocumento.trim().isEmpty() || (!tipoDocumento.toUpperCase().equals(TipoDocumento.CEDULA.toString().toUpperCase()) && !tipoDocumento.toUpperCase().equals(TipoDocumento.PASAPORTE.toString().toUpperCase()))) { 	
        	validationErrors.append("Tipo de documento invalido.\n");
        }

        if (numDocumento == null || numDocumento.trim().isEmpty() || (tipoDocumento.toUpperCase().equals(TipoDocumento.CEDULA.toString().toUpperCase()) && !DOCUMENT_NUMBER_PATTERN.matcher(numDocumento).matches())) {
            validationErrors.append("Numero de documento invalido.\n");
        }
        return validationErrors.toString();
    }

    // Helper method to validate date format
    private static boolean isValidDate(String date) {
        String datePattern = "\\d{4}-\\d{2}-\\d{2}"; // YYYY-MM-DD
        return Pattern.matches(datePattern, date);
    }
    
    public class MensajesDeError {
        // Errores relacionados con la entrada del usuario
        public static final String CAMPO_REQUERIDO = "Este campo es obligatorio.";
        public static final String EMAIL_INVALIDO = "Por favor, introduce una dirección de correo electrónico válida.";
//        public static final String CONTRASENA_CORTA = "La contraseña debe tener al menos 8 caracteres.";
        public static final String CONTRASENAS_NO_COINCIDEN = "Las contraseñas no coinciden.";
        public static final String DOCUMENTO_INVALIDO = "Por favor, introduce un número de documento válido.";
        
        // Errores de autenticación
        public static final String CREDENCIALES_INVALIDAS = "Usuario o contraseña incorrectos.";
        public static final String ACCESO_NO_AUTORIZADO = "No tienes autorización para acceder a esta página.";
        public static final String USUARIO_NO_LOGUEADO = "Se necesita iniciar sesion para completar esta operacion";

        // Errores de red o servidor
        public static final String ERROR_SERVIDOR = "Ocurrió un error inesperado. Por favor, intenta de nuevo más tarde.";
        public static final String PAGINA_NO_ENCONTRADA = "La página que estás buscando no existe.";
        
        // Errores en el envío de formularios
        public static final String ENTRADA_DUPLICADA = "Ya existe un registro con el mismo valor.";
        public static final String INFORMACION_OBLIGATORIA_FALTANTE = "No se introdujo inforacion obligatoria en alguno de los campos";
        

        // Errores de validación personalizados
        public static final String FORMATO_FECHA_INVALIDO = "Por favor, introduce una fecha válida en el formato DD/MM/AAAA.";
        public static final String VALOR_FUERA_DE_RANGO = "El valor introducido está fuera del rango permitido.";
        public static final String RECURSO_NO_EXISTE = "No se encontro el recurso solicitado";
        public static final String ENTIDAD_YA_EXISTE = "No se encontro el recurso solicitado";

        // Constructor privado para evitar instanciación
        private MensajesDeError() {
            // Esta clase no está destinada a ser instanciada
        }
    }

}

