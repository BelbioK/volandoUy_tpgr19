<%@page import="publicar.*"%>
<%@page import="excepciones.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashSet"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="styles/reservar.css">
	<title>Vuelos :: Volando.uy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/nav-bar.jsp" />
    <jsp:include page="/WEB-INF/template/side-bar.jsp" />
    
    <div id="root-reservar">
    	<% 
    		DtVuelo vuelo = (DtVuelo) request.getAttribute("vuelo");
    		DtRutasDeVuelo ruta = vuelo.getRuta();
    		DtUsuario usuario= (DtUsuario)session.getAttribute("usuario");
    		Set<DtCompraRuta> paquetes = (Set<DtCompraRuta>) request.getAttribute("paquetes");
    		String tipoAsientoParam = request.getParameter("tipoAsiento") != null ? request.getParameter("tipoAsiento") : "";
    		String tipoPagoParam = request.getParameter("tipoPago") != null ? request.getParameter("tipoPago") : "";
    	%>
  		<script>
 	  		const recalcularCosto = ()=>{	
	  	        const equipajeExtra = $('#input-equipaje').val() || 1;
	  	        const costoPasaje = $('#select-asiento').val() == 'turista' ? <%=ruta.getCostoTurista() %> : <%=ruta.getCostoEjecutivo() %>;
	  	        const pasajes = $('.pasajes-row.custom').length+1;
	  	        console.log(pasajes);
	  	        const descuento = $(".paquete-aplicable.selected").length ? $(".paquete-aplicable.selected").get(0).getAttribute("descuento") || 0 : 0; 
	  	    
	  	        let costo = costoPasaje*pasajes + equipajeExtra*<%=ruta.getCostoEquipaje()%>;
	  	        console.log(costo);
	  	        costo = costo*(100-descuento)/100;
	
	  	        $("#costo-total").html(costo+"$")
	
	  	    }
  		</script>
         
        <div id="seccion-vuelo-info" style="padding:1rem;">
        	<div style="width:100%;text-align: center;padding:1rem;">
		    	<span style="font-size:1.5rem;">Complete la informacion</span>
		    	<form action="<%= request.getContextPath()+"/reservar"%>">
		    	    <input style="display:none" name="ruta" value="<%=ruta.getNombre()%>"/>
	   				<button style="width:150px;" class="buttonFacha" type="submit">Volver</button>
	   			</form>
		    </div>
            <div>
                <span>Informacion general</span>
            </div>
            	<div class="ancla-contenedor">
		           <div class="card" id='card-vuelo'>
	                <div class="card-image">
	                    <img src="./assets/imagenes/<%=ruta.getImagen()%>" style="border-radius: 10px;" width="200px" height="200px" alt="">
	                </div>
	                <div class="card-title"><%=ruta.getNombre()%></div>
	                <div class="card-subtitle"><%=ruta.getOrigen().getNombre() %> --> <%=ruta.getDestino().getNombre() %></div>
	                <div class="card-content"><%=ruta.getDescripcionCorta()%></div>
	                <div class="card-additionalInfo">
	                    <slot slot="additionalInfo" class="row-usuTipo">
					        <% 
	    						for(DtCategoria c: ruta.getCategorias()){
			    			%>
			    					<span><%=  c.getNombre() %></span>
			    			<%
			    				}
			    			%>
			    		</slot>
	                </div>
	            </div>
		         </div>
            <div>
                <span>Fecha</span>
            </div>
            <div style="display: grid;grid-template-columns: 1fr 1fr 1fr; justify-items: center;align-items: center; margin:1rem 0 1rem 0;">
                <div class="mini-card">
                    <span>Fecha</span>
                    <h4 id="card-fecha"><%=  new SimpleDateFormat("dd/MM/yyyy").format(vuelo.getFecha().toGregorianCalendar().getTime()) %></h4>
                </div>
                <div class="mini-card">
                    <span>Hora</span>
                    <h4 id="card-hora"><%= ruta.getHoraString() %></h4>
                </div>
                <div class="mini-card">
                    <span>Duracion</span>
                    <h4 id="card-hora"><%= vuelo.getHorasDur() %>:<%= vuelo.getMinutosDur() %></h4>
                </div>
            </div>
            <div>
                <span>Costos</span>
            </div>
            <div style="display: grid;grid-template-columns: 1fr 1fr 1fr;justify-items: center;align-items: center;">
                <div class="mini-card">
                    <span>Equipaje</span>
                    <h4 id="card-costo-equipaje"><%=ruta.getCostoEquipaje() %></h4>
                </div>
                <div class="mini-card">
                    <span>Turista</span>
                    <h4 id="card-costo-turista"><%=ruta.getCostoTurista() %></h4>
                </div>
                <div class="mini-card">
                    <span>Ejecutivo</span>
                    <h4 id="card-costo-ejecutivo"><%=ruta.getCostoEjecutivo() %></h4>
                </div>
            </div> 
        </div>
	
		<form class="seccion-form-reserva" method="post">
			<input style="display:none;" value="<%=vuelo.getNombre()%>" name="vuelo"/>
	        <div style="display: grid; grid-template-columns: 4fr 1fr; justify-content: left; height: 66px;">
	            <div class="label-select">
	                <label>Asiento</label>
	                <select id="select-asiento" name="tipoAsiento">
	                    <option value="turista">Turista</option>
	                    <option value="ejecutivo">Ejecutivo</option>
	                </select>
	            </div>
	            <div class="label-input" style="width: 90%;">
	                <label>Equipaje extra</label>
	                <input type="number" id="input-equipaje" name="equipajeExtra" min="1" max="1000" value="1" step="1">
	            </div>
	        </div>
	        <div style="margin-top: 1rem;">
	            <label >
	                Pasajes
	            </label>
	        </div>
	        <script>
		        $('#select-asiento').change(function(){
		        	const otherTipo = $(this).val() == 'turista' ? 'ejecutivo' : 'turista';
		        	 $('.paquetes-aplicables').addClass($(this).val());
		        	 $('.paquetes-aplicables').removeClass(otherTipo);
		             $('.paquete-aplicable.'+otherTipo+'.selected').removeClass("selected");
		             recalcularCosto();
		        });
		        $('#input-equipaje').change(()=> recalcularCosto());
	        </script>
	        <div id="form-pasajes" class="form-pasajes">
	            <div class="pasajes-row">
	                <span>Nombre</span>
	                <span>Apellido</span>
	            </div>
	            <div class="pasajes-row">
	                <input disabled id="nombreCliente" name="nombreCliente" value="<%=usuario.getNombre()%>"/>
	                <input disabled id="apellidoCliente" name="apellidoCliente" value="<%=((DtCliente)usuario).getApellido()%>"/>
	            </div>
	            
	            <div class="add-row">
	                <button id="agregarPasaje" type="button" class="icon-btn add">+</button>
	            </div>
	            <script>
	            	var totalPasajes = 0;
		            $("#agregarPasaje").click(()=> {
		                $('#form-pasajes div:last').before(`
		                    <div class="pasajes-row custom" id="row_`+totalPasajes+`">
		                        <input id='nombre-pasaje-`+totalPasajes+`' name="nombrePasaje`+totalPasajes+`"/>
		                        <input id='apellido-pasaje-`+totalPasajes+`' name="apellidoPasaje`+totalPasajes+`"/>
		                        <button id="borrarPasaje_`+totalPasajes+`" type="button" class="icon-btn cross">x</button>
		                    </div>
		                `);
		                let index = totalPasajes
		                $('#borrarPasaje_'+totalPasajes).click(()=>{
		                    $('#row_'+index).remove();
			                recalcularCosto();
		                })
		                totalPasajes++;
		                recalcularCosto();
		            })
	            </script>
	        </div>
	        <div class="tipo-pago" id="seccion-tipo-pago">
	            <label>Tipo de pago</label>
	            <br/>
	            <input id="general-radio" type="radio" name="tipoPago" value="general" checked="checked">
	            <label for="general-radio">General</label>
	            <br/>
	            <input id="paquete-radio" type="radio" name="tipoPago" value="paquete">
	            <label for="paquete-radio">Con paquete</label>
	            <script>
		            $('#general-radio').change(async function() {
		                $('.paquetes-aplicables').addClass("esconder");
		                $('.paquete-aplicable.selected').removeClass("selected");
		                recalcularCosto();
		            })
		            $('#paquete-radio').change(async function() {
		                $('.paquetes-aplicables').removeClass("esconder");
		                recalcularCosto();
		            })
	            </script>
	            <br/>
	            <div class="paquetes-aplicables <%= tipoAsientoParam.equals("ejecutivo") ? "ejecutivo" : "turista" %> <%= tipoPagoParam.equals("paquete") ? "" : "esconder" %>">
					<input style="display:none;" value="" id="input-paquete" name="paquete"/>
	                <% 	int i = 0;
	                	for(DtCompraRuta p:paquetes){ %>
	                	<div id='paquete-aplicable-<%=i %>' nombre="<%= p.getPaquete().getNombre() %>" descuento="<%= p.getPaquete().getDescuento() %>" class="paquete-aplicable <%=p.getAsiento().equals(Asiento.TURISTA) ? "turista" : "ejecutivo"%>">
			                <img src="./assets/imagenes/<%=p.getPaquete().getImagen()%>">
			                <span><%=p.getPaquete().getNombre() %>, <%=p.getCantidadRestante() %> pasajes</span>
			            </div>
	                <% i++;} %>
	                <% if(paquetes.size() == 0){ %>
	                		<div class="no-hay">No hay paquetes disponibles</div>
	                <% } %>
	                <script>
	                	for(let i=0;i<<%=i%>;i++){
			                $('#paquete-aplicable-'+i).click(function(){
			                	$("#input-paquete").val($("#input-paquete").val() == $(this).attr("nombre") ? '' : $(this).attr("nombre"));
			                    $(this).toggleClass("selected");
			                    for(let j=0;j<<%=i%>;j++)
			                    	j!=i ? $('#paquete-aplicable-'+j).removeClass("selected"):''
			                    			
			                    recalcularCosto();
			                });
	                	}
	                </script>
	                <style>
	                	.no-hay{
	                		color:  rgb(247, 95, 95);
	                	}
	                	.paquetes-aplicables.esconder .paquete-aplicable,.no-hay{
	                		display:none;
	                	}
	                	.paquetes-aplicables.turista .paquete-aplicable.ejecutivo{
	                		display:none;
	                	}
	                	.paquetes-aplicables.ejecutivo .paquete-aplicable.turista{
	                		display:none;
	                	}
	                </style>
	            </div>
	        </div>
	        <div style="display: grid; align-items: center; justify-items: center; margin-top: auto;"> 
	            <div>Costo: <span id="costo-total"><%= ruta.getCostoTurista()+ruta.getCostoEquipaje() %>$</span></div>
	            <button id="confirmar" type="submit" class="boton-facha">Confirmar</button>
	        </div>
	    </form>
	</div>
</body>
</html>