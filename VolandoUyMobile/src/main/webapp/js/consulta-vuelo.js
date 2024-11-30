var personaActual = null;
var personaAnterior = null;

function mostrarDatos(nickname){
	personaAnterior = personaActual
	if(personaAnterior == null) personaAnterior = ".datos-" + nickname;
	personaActual = ".datos-" + nickname;
	
	$(personaAnterior).css("display", "none")
	$(personaActual).css("display", "block")
}