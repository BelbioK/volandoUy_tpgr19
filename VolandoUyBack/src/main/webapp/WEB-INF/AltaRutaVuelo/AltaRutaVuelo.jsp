<%@page import="publicar.*"%>
<%@page import="excepciones.*"%>
<%@page import="publicar.TipoUsuario"%>
<%@page import="publicar.TipoDocumento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%--@page errorPage="/WEB-INF/NoExiste/NoExiste.jsp"--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="styles/alta-ruta-vuelo.css">
<link rel="stylesheet" href="styles/resource-list.css">
<meta charset="UTF-8">
<title>Perfil</title>
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
	Set<String> categorias = (Set<String>) request.getAttribute("categorias");
	Set<String> ciudades = (Set<String>) request.getAttribute("ciudades");
	%>
	
	<div id="root-alta-ruta-vuelo">
	    <!-- <h1>Registro de Ruta de Vuelo ✈️</h1> -->
	    <!-- <form id="alta-ruta-form" onsubmit="event.preventDefault(); return customValidation(event) " method="POST" action="usuarios"> -->
	    <form id="alta-ruta-form" onsubmit="validation(event)" method="POST" action="agregarRuta">
	        <!-- Nombre único -->
	        <div>
	            <label for="nombre">Nombre (unico):</label>
	            <input type="text" id="nombre" name="nombre" required>
	            <span id="message-verif-nombre"></span>
	        </div>
	
	        <!-- Descripción corta -->
	        <div>
	            <label for="descripcion_corta">Descripcion corta:</label>
	            <input type="text" id="descripcion_corta" name="descripcion_corta" maxlength="100" required>
	        </div>
	
	        <!-- Descripción (full width) -->
	        <div class="full-width">
	            <label for="descripcion">Descripcion:</label>
	            <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
	        </div>
	
	        <!-- Hora -->
	        <div>
	            <label for="hora">Hora:</label>
	            <input type="time" id="hora" name="hora" required>
	        </div>
	
	        <!-- Costo turista -->
	        <div>
	            <label for="costo_turista">Costo clase turista:</label>
	            <input type="number" id="costo_turista" name="costo_turista" step="0.01" min="0" required>
	        </div>
	
	        <!-- Costo ejecutivo -->
	        <div>
	            <label for="costo_ejecutivo">Costo clase ejecutiva:</label>
	            <input type="number" id="costo_ejecutivo" name="costo_ejecutivo" step="0.01" min="0" required>
	        </div>
	
	        <!-- Costo unidad equipaje extra -->
	        <div>
	            <label for="costo_equipaje_extra">Costo unidad equipaje extra:</label>
	            <input type="number" id="costo_equipaje_extra" name="costo_equipaje_extra" step="0.01" min="0" required>
	        </div>
	
	        <!-- Ciudad origen -->
	        
	        <div>
	            <label for="ciudad_origen">Ciudad de origen:</label>
	            <select id="ciudad_origen" name="ciudad_origen" required>
	            <% 
	            for(String ciudad : ciudades){
	            	%>
	            	 <option value="<%=ciudad %>"><%=ciudad %></option>
	            	<%
	            }
	            %>
	            </select>
	        </div>
	
	        <!-- Ciudad destino -->
	        <div>
	            <label for="ciudad_destino">Ciudad de destino:</label>
	            <select id="ciudad_destino" name="ciudad_destino" required>
	            <% 
	            for(String ciudad : ciudades){
	            	%>
	            	 <option value="<%=ciudad %>"><%=ciudad %></option>
	            	<%
	            }
	            %>
	            </select>
	        </div>
	
	        <!-- Categorías -->
	        <div>
	            <label for="categorias">Categorias:</label>
	            <select id="categoriasSelector" name="categorias">
	            <%
	            for(String cat : categorias){
	            	%>
	            	 <option value="<%=cat %>"><%=cat %></option>
	            	<%
	            }
	            %>
	            </select>
	            <select id="categoriasSelector_categoriasParaEnviar" hidden="hidden" name="categoriasSeleccionadas" multiple>
	        
	       	 	</select>
	            <button type="button" id="agregarCategoria">Agregar Categoria</button>
	        </div>
	
	        <div>
	            <label for="categorias_seleccionadas">Categorias seleccionadas:</label>
	            <ul id="categorias_seleccionadas"></ul>
	        </div>
	        <!-- Video opcional -->
			<div>
	            <label for="video">URL del video (opcional):</label>
	            <input type="text" id="video" name="video">
	        </div>
	        
	        <!-- Imagen opcional (full width) -->
	        <div class>
	            <label for="imagen">Subir imagen (opcional):</label>
	            <input type="file" id="imagen" name="imagen">
	        </div>
	
	        <!-- Enviar (submit button) -->
	        <div class="submit-btn">
	            <button id="submitBtt" type="submit">Registrar Ruta</button>
	        </div>
	    </form>
	    <jsp:include page="/WEB-INF/template/error-popup.jsp" />
	</div>

	<script>
	//Ajax valid globals
	let nombreRepetido = false;
	
    const categoriasSeleccionadas = new Set();
     
    const fields = [ 
            { "id": "nombre", validation: (v)=> v.length > 0, mensajeError: "El campo Nombre no puede estar vacio" },
            { "id": "descripcion_corta", validation: (v)=> v.length > 0, mensajeError: "El campo Descripcion corta no puede estar vacio" },
            { "id": "hora", validation: (v)=>v != "", mensajeError: "Debe seleccionarse una hora" }, 
            { "id": "costo_turista", validation: (v)=>v != "" && parseInt(v) > 0, mensajeError: "Debe seleccionarse una costo para turista valido" },
            { "id": "costo_ejecutivo", validation: (v)=>v != "" && parseInt(v) > 0 , mensajeError: "Debe seleccionarse una costo para ejecutivo valido" },
            { "id": "costo_equipaje_extra", validation: (v)=>v != "" && parseInt(v) > 0, mensajeError: "Debe ingresar una costo para equipaje extra valido" },
            { "id": "ciudad_origen", validation: (v)=>v != "", mensajeError: "Debe seleccionarse ciudad origen" },
            { "id": "ciudad_destino", validation: (v)=>v != "", mensajeError: "Debe seleccionarse ciudad destino" },
        ]

    $('#root-alta-ruta-vuelo #agregarCategoria').on('click', async ()=>{
        const categoria = $('#root-alta-ruta-vuelo #categoriasSelector').val();
        if(!categoria){
            console.log('Debe seleccionar una categoria')
            setMessage('Debe seleccionar una categoria')
            return;
        }
        else if(categoriasSeleccionadas.has(categoria)){
            setMessage('La categoria ya fue seleccionada')
            return;
        }
        else{
            categoriasSeleccionadas.add(categoria);
            console.log(categoria)
            //$('#root-alta-ruta-vuelo #categoriasSelector').val('');
            const categoryElement = `
                <li id="item_categoria_`+ categoria +`" class="category-item" >
                    <div class="category-item-name">
                        <span>`+ categoria +`</span>
                    </div>
                    <div class="category-item-btn">
                        <button type="button" id="btn_cerrar_categoria_`+ categoria +`">X</button>
                    </div>
                </li>
            `
            $('#root-alta-ruta-vuelo #categorias_seleccionadas').append(categoryElement);
            $(`#root-alta-ruta-vuelo #categorias_seleccionadas #btn_cerrar_categoria_`+categoria).click((event)=>{
                //event.preventDefault();
            categoriasSeleccionadas.delete(categoria)
            $(`#root-alta-ruta-vuelo #categorias_seleccionadas #item_categoria_`+categoria).remove();
            })
        }
    })


    function validation(event){
		//console.log($('#root-alta-ruta-vuelo #categoriasSelector').val())
		const {fieldErrorIds, formIsValid, errorMessages} = validarFormulario(fields, getInputValues)
		console.log(fieldErrorIds, formIsValid)
        const additVal = additionalValidation()
        const URLValid = ValidationURL()
		if(formIsValid && additVal && URLValid && !nombreRepetido){
			preSendForm(categoriasSeleccionadas)
			return true
		}
		else{
			event.preventDefault()
            if(!additVal){
                errorMessages.push('La ciudad de origen y destino deben ser diferentes')
			}
			if(!URLValid){
                errorMessages.push('El link es invalido')
			}
			if(nombreRepetido){
				errorMessages.push('Nombre no disponible')
			}
			setMessage(errorMessages)
			return false;
		}
	}

    function additionalValidation(){
		return $('#ciudad_destino').val() != $('#ciudad_origen').val()
    }
    
    function ValidationURL(){
		const videoURL = $('#video').val()
		if (videoURL === "") return true;
        const regex = /^(https?:\/\/)?(www\.)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}(\/[^\s]*)?$/
		return regex.test(videoURL)
	}
   
	//Re descubri esta funcion, que feliz me hace
	//Att. el mero mero
    function validarFormulario(fields, getInputValues = null){
            let formIsValid = true
            let fieldErrorIds = []
            let errorMessages = []
            let inputValues = []
            if(getInputValues == null){
                inputValues = fields.map(field => ({id: field.id, valFunc: field.validation, value: $('#'+field.id).val(), errMsg: field.mensajeError}))
            }
            else{
                inputValues = getInputValues(fields.map(field => ({id: field.id, valFunc: field.validation, errMsg: field.mensajeError})))
            }
            inputValues.forEach(({id,value,valFunc,errMsg}) => {
                if(!valFunc(value)){
                    fieldErrorIds.push(id)
                    errorMessages.push(errMsg)
                    $('#'+id).addClass('errorInput')
                    formIsValid = false
                }
                else{
					$('#'+id).removeClass('errorInput')
                }
            })
            return {fieldErrorIds, formIsValid, errorMessages}
	}
    

    function getInputValues(fields){
        let data = []
        fields.forEach(({ id, valFunc, errMsg }) => {
            data.push({ id: id, value: $('#' + id).val(), valFunc: valFunc, errMsg:errMsg});
        });
        data.push({id:'categoriasSelector', value:Array.from(categoriasSeleccionadas), valFunc: ()=>true, errMsg:""})
        return data
    }
    
    function preSendForm(categoriasSeleccionadas){
    	categoriasSeleccionadas.forEach((categoria)=>{
	    	$("#root-alta-ruta-vuelo #alta-ruta-form #categoriasSelector" ).attr("disabled","disabled")
    		$("#root-alta-ruta-vuelo #alta-ruta-form #categoriasSelector_categoriasParaEnviar").append('<option selected name=categorias[] value='+categoria+'>')
    	})
    }

    
    
    $(document).ready(function() {
        $("#nombre").keyup(function(event) {
			if($("#nombre").val() != ""){
	            $.ajax({
					url: "agregarRuta?verifNombre=" + encodeURIComponent($("#nombre").val()), 
	            	success: function(result){
						    console.log(result);
							if(result === "REPETIDO"){
								$("#nombre").removeClass( 'successInput');
								$("#nombre").addClass( 'errorInput');
								$("#message-verif-nombre").show()
								$("#message-verif-nombre").text('Nombre no disponible.')
							    nombreRepetido = true;							
							}
							else if(result == "DISPONIBLE"){
							    $("#nombre").removeClass( 'errorInput');
							    $("#nombre").addClass( 'successInput');
							    $("#message-verif-nombre").show()
								$("#message-verif-nombre").text('Nombre disponible.')
							    nombreRepetido = false;	
							}
						},
					error: function(result){
						console.log("ERROR")
						$("#message-verif-nombre").hide()
					}
	
	    		});
				
			}
		   else{
			   $("#nombre").removeClass( 'errorInput');
			   $("#nombre").removeClass( 'successInput');
			   $("#message-verif-nombre").hide();
		   }
        });
    });  
	</script>		
</body>
</html>
