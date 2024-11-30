package logica.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.conceptos.Aerolinea;
import logica.conceptos.Cliente;
import logica.conceptos.Usuario;
import logica.controladores.Fabrica;
import logica.datatypes.TipoDocumento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import persistencia.CargadorDatos;

public class ModificarUsuarioTest {
	private Fabrica fab = Fabrica.getInstancia();
    private IControladorUsuarios controlUsu = fab.getControladorUsuario();
	
    @BeforeEach
    void setUp() { 
		CargadorDatos.cargarDatos();
	}
	
	@Test
	public void modificarCliente(){
		Date fecha1 = numbersToDate(21, 2, 2003);
		String nick1 = "sofi89";
		TipoDocumento tipoDocumento = TipoDocumento.Cedula; 
		String numeroDocumento = "12345678";
		try {
			controlUsu.modificarCliente(nick1,"", "SOFIA", "TERRENCE", fecha1, "Uruguay", tipoDocumento, numeroDocumento);
		} catch (DatoInvalido | InstanciaNoExiste e) {
			fail("No debería lanzar excepción: " + e.getMessage());
		}
		Usuario usu = controlUsu.obtenerUsuario(nick1);
		if(usu instanceof Cliente) {
			Cliente cli = (Cliente) usu;
			if(cli.getNombre() != "SOFIA" || cli.getApellido() != "TERRENCE" || !cli.getFechaNacimiento().equals(fecha1) || cli.getNacionalidad() != "Uruguay" || cli.getTipoDocumento() != (tipoDocumento) || cli.getNroDocumento() != numeroDocumento ) {
				fail("Los datos modificados no coinciden");
			}
		}
		else {
			fail("No deberia cambiar el tipo de Usuario");
		}
	}
	
	@Test
	public void modificarAerolinea() {
		String nick1 = "zfly";
		try {
			controlUsu.modificarAerolinea(nick1,"", "NOMBRE", "DESCRIPCION", "LINK");
		} catch (DatoInvalido | InstanciaNoExiste e) {
			fail("No debería lanzar excepción: " + e.getMessage());
		}
		Usuario usu = controlUsu.obtenerUsuario(nick1);
		if(usu instanceof Aerolinea) {
			Aerolinea aero = (Aerolinea) usu;
			if(aero.getNombre() != "NOMBRE" ||  aero.getDescripcion() != "DESCRIPCION" || aero.getLink() != "LINK") {
				fail("Los datos modificados no coinciden");
			}
		}
		else {
			fail("No deberia cambiar el tipo de Usuario");
		}
	}
	
	@Test
	public void modificarClienteDatosInvalidos(){
		TipoDocumento tipoDocumento = TipoDocumento.Cedula;
		Date fecha = numbersToDate(21, 2, 2003);
		//Numero Documento invalido
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarCliente("zfly","", "test", "test", fecha, "test", tipoDocumento, "");
        }, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con un documento invelido.");
		// Nacionalidad invalida
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarCliente("zfly","", "test", "test", fecha, "", tipoDocumento, "12312323");
        }, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con una nacionalidad invalida.");
		//Apellido invalido
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarCliente("zfly","", "test", "", fecha, "test", tipoDocumento, "12312323");
        }, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con un apellido invalido.");
		//Nombre invalido
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarCliente("zfly","", "", "test", fecha, "test", tipoDocumento, "12312323");
        }, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con un nombre invalido.");
	}
	
	@Test
	public void modificarAerolineaDatosInvalidos(){
		String nick1 = "zfly";
		// Nombre invalido
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarAerolinea(nick1,"", "", "DESCRIPCION", "LINK");
		}, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con una nacionalidad invalido.");
		
		// Descripcion invalida
		assertThrows(DatoInvalido.class, () -> {
			controlUsu.modificarAerolinea(nick1,"", "NOMBRE", "", "LINK");
		}, "Se esperaba que se lanzara DatoInvalido al modificar un usuario con una nacionalidad invalido.");
	}
	
	private Date numbersToDate(int dia, int mes, int anio) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH, mes - 1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }
}
