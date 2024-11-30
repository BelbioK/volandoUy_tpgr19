<%@ page import="publicar.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>

<!DOCTYPE html>
<html>
	<head>
    	<jsp:include page="/WEB-INF/template/head.jsp" />
		<link rel="stylesheet" href="styles/alta-vuelo.css">
		<title>Alta Vuelo :: Volando.uy</title>
		<style>
	#side-bar-aplicar-filtros{
		display: none;
	}
	</style>
	</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
	<jsp:include page="/WEB-INF/template/side-bar.jsp" />
	<%
		
		DtAerolinea aero = (DtAerolinea) session.getAttribute("usuario");
	    Set<DtRutasDeVuelo> rutas = (Set<DtRutasDeVuelo>) request.getAttribute("rutas");
	    SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
	%>
    <div id="root-alta-vuelo">
        <div class="label-select">
            <form id="formAltaVuelo" method="POST" action="agregarVuelo">
                <div class="label-select">
                    <label>Ruta del vuelo:</label>
                    <select id="selectRutaVuelo" name="rutaVuelo" required>
                        <option value="" disabled selected>Seleccione una ruta</option>
                        <% 
                            for (DtRutasDeVuelo ruta : rutas) {
                            	if(ruta.getEstado().equals(Estado.CONFIRMADA)){
                            	String rutaNombre = ruta.getNombre();
                            		
                        %>
                        	<option value="<%= rutaNombre %>"><%= rutaNombre %></option>
                        <% 
                            	}
                         	}
                        %>
                    </select><br><br>
                </div>

                <div class="label-select">
                    <label for="nombreVuelo">Nombre del vuelo (unico):</label>
                    <input type="text" id="nombreVuelo" name="nombreVuelo" required><br><br>
       	            <span id="message-verif-nombreVuelo"></span>
                </div>

                <div class="label-select">
                    <label for="fechaVuelo">Fecha del vuelo:</label>
                    <input type="date" id="fechaVuelo" name="fechaVuelo" required><br><br>
                </div>

                <div class="label-select">
                    <label for="asientosEjecutivos">Cantidad maxima de asientos ejecutivos:</label>
                    <input type="number" id="asientosEjecutivos" name="asientosEjecutivos" min="0" required><br><br>
                </div>

                <div class="label-select">
                    <label for="asientosTurista">Cantidad maxima de asientos turistas:</label>
                    <input type="number" id="asientosTurista" name="asientosTurista" min="0" required><br><br>
                </div>

                <div class="label-select">
                    <label for="fotoVuelo">Subir foto del vuelo (opcional):</label>
                    <input type="file" style="height: 35px; padding: 8px;" id="fotoVuelo" name="fotoVuelo" accept="image/*"><br><br>
                </div>

                <div class="full-width" style="height: 1rem;">
                    <label>Duracion del vuelo:</label><br>
                </div>

                <div class="label-select">
                    <label>Horas:</label>
                    <input type="number" id="duracionHoras" name="duracionVueloHoras" min="0" required><br><br>
                </div>

                <div class="label-select">
                    <label>Minutos:</label>
                    <input type="number" id="duracionMinutos" name="duracionVueloMinutos" min="0" max="59" required><br><br>
                </div>

                <div class="full-width" style="display: grid; align-items: center; justify-items: center;">
                    <button id="Registrar" class="boton" type="submit">Registrar Vuelo</button>
                </div>
                <div id="errorContainer">
    				<div class="modal-content">
        				<span id="closeError" class="close">&times;</span>
        				<p id="errorMessage"></p>
    				</div>
				</div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
  	//Ajax valid globals	
    let nombreRepetido = false
	    const mostrarError = (mensaje) => {
	        const errorContainer = document.getElementById('errorContainer');
	        document.getElementById('errorMessage').innerText = mensaje;
	        errorContainer.style.display = "block";
	    };
	
	    const cerrarError = () => {
	        const errorContainer = document.getElementById('errorContainer');
	        errorContainer.style.display = "none";
	    };
	
	    document.getElementById('closeError').onclick = cerrarError;
	
	    window.onclick = (event) => {
	        const errorContainer = document.getElementById('errorContainer');
	        if (event.target === errorContainer) {
	            cerrarError();
	        }
	    };
	
	    const validarAsientos = () => {
	        const cantTuri = Number(document.getElementById('asientosTurista').value);
	        const cantEje = Number(document.getElementById('asientosEjecutivos').value);
	        return !(cantEje === 0 && cantTuri === 0);
	    }
	    
	    const validarCantidad = () => {
	        const cantTuri = Number(document.getElementById('asientosTurista').value);
	        const cantEje = Number(document.getElementById('asientosEjecutivos').value);
	        const durHoras = Number(document.getElementById('duracionHoras').value);
	        const maxInt = Number.MAX_SAFE_INTEGER;
	        if (cantTuri > maxInt || cantEje > maxInt || durHoras > maxInt){
				return false;
	        }
	      	return true;
	    }
	
	    const validarDuracion = () => {
	        const durHoras = Number(document.getElementById('duracionHoras').value);
	        const durMinutos = Number(document.getElementById('duracionMinutos').value);
	        return !(durHoras === 0 && durMinutos === 0);
	    }
	    
	    function verfFechaVuelo(fecha){
			const fechaVue  = document.getElementById('fechaVuelo').value;
	        try{
	            if(fechaVue == "") return false
	            const fechaVueParsed = new Date(fechaVue);
	            const fechaAhora = new Date()
	            if(fechaVueParsed <= fechaAhora){
	                return false;
	            }
	        }
	        catch{
	            return false
	        }
	        return true;
	    }
	    
	    document.getElementById('formAltaVuelo').addEventListener('submit', (event) => {
	        let mensajeError = '';
	        if (!validarAsientos()) {
	            mensajeError += 'La cantidad de asientos del vuelo debe ser mayor a 0.\n';
	        }
	        if (!validarDuracion()) {
	            mensajeError += 'La duraci�n del vuelo debe ser mayor a 0.\n';
	        }
	        if(!verfFechaVuelo()){
	        	 mensajeError += 'La fecha del vuelo debe ser mayor a la fecha de alta.\n';
			}
	        if(!validacionCantidad()){
				mensajeError += 'Alguna de las cantidades es mayor a la recomendada, por favor cambiela.\n'
	        }
	        if(nombreRepetido){
	        	mensajeError += 'Nombre no disponible\n'
	        }
	        if (mensajeError !== '') {
	            event.preventDefault(); 
	            mostrarError(mensajeError); 
	        }
	    });
	    
	    
	    $(document).ready(function() {
	        $("#nombreVuelo").keyup(function(event) {
				if($("#nombreVuelo").val() != ""){
		            $.ajax({
						url: "agregarVuelo?verifNombre=" + encodeURIComponent($("#nombreVuelo").val()), 
		            	success: function(result){
							    console.log(result);
								if(result === "REPETIDO"){
									$("#nombreVuelo").removeClass( 'successInput');
									$("#nombreVuelo").addClass( 'errorInput');
									$("#message-verif-nombreVuelo").show()
									$("#message-verif-nombreVuelo").text('Nombre no disponible.')
								    nombreRepetido = true;							
								}
								else if(result == "DISPONIBLE"){
								    $("#nombreVuelo").removeClass( 'errorInput');
								    $("#nombreVuelo").addClass( 'successInput');
								    $("#message-verif-nombreVuelo").show()
									$("#message-verif-nombreVuelo").text('Nombre disponible.')
								    nombreRepetido = false;	
								}
							},
						error: function(result){
							console.log("ERROR")
							$("#message-verif-nombreVuelo").hide()
						}
		
		    		});
					
				}
			   else{
				   $("#nombreVuelo").removeClass( 'errorInput');
				   $("#nombreVuelo").removeClass( 'successInput');
				   $("#message-verif-nombreVuelo").hide();
			   }
	        });
	    });  
    </script>
</body>
</html>
