package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import main.java.entidades.Cliente;
import persistencia.CargadorDatos;
import presentacion.paquetes.AgregarRutaDeVueloAPaquete;
import presentacion.paquetes.ComprarPaquete;
import presentacion.paquetes.ConsultaPaquete;
import presentacion.paquetes.CrearPaqueteDeRutaDeVuelo;
import presentacion.usuario.AltaUsuario;
import presentacion.usuario.ConsultaUsuarios;
import presentacion.usuario.ModificarUsuario;
import presentacion.vuelos.AltaCiudad;
import presentacion.vuelos.AltaRutaVuelo;
import presentacion.vuelos.AltaVuelo;
import presentacion.vuelos.CambioDeEstadoRDV;
import presentacion.vuelos.ConsultaRutaDeVuelo;
import presentacion.vuelos.ConsultaVuelo;
import presentacion.vuelos.ReservaVuelo;
import presentacion.vuelos.RutasMasVisitadas;
import publicar.Publicador;


public class Main {
	
	private JFrame application;
	
	private AltaVuelo altaVuelo;
	private AltaUsuario altaUsuario;
	private ModificarUsuario modificarUsuario;
	private AltaCiudad altaCiudad;
	private ReservaVuelo reservaVuelo;
	private ConsultaVuelo consultaVuelo;
	private ConsultaUsuarios consultaUsuarios;
	private ConsultaRutaDeVuelo consultaRutaDeVuelo;
	private CrearPaqueteDeRutaDeVuelo crearPaquete;
	private AgregarRutaDeVueloAPaquete agregarRutaDeVueloAPaquete;
	private AltaRutaVuelo altaRutaVuelo;
	private ComprarPaquete comprarPaquete;
	private ConsultaPaquete consultaPaquete;
	private CambioDeEstadoRDV cambioDeEstadoRDV;
	private RutasMasVisitadas RutasMasVisitadas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.application.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	public Main() {
		
		Publicador p = new Publicador();
        p.publicar();
		
		initMainFrame();
		initTabsUsuarios();
		initTabsVuelos();
		initTabsPaquetes();
		
		application.getContentPane().setLayout(null);
		application.getContentPane().add(altaUsuario);
		application.getContentPane().add(modificarUsuario);
        application.getContentPane().add(altaCiudad);
        application.getContentPane().add(altaVuelo);
		application.getContentPane().add(reservaVuelo);
		application.getContentPane().add(consultaRutaDeVuelo);
		application.getContentPane().add(consultaVuelo);
		application.getContentPane().add(consultaUsuarios);
		application.getContentPane().add(crearPaquete);
		application.getContentPane().add(agregarRutaDeVueloAPaquete);
		application.getContentPane().add(comprarPaquete);
		application.getContentPane().add(consultaPaquete);
		application.getContentPane().add(altaRutaVuelo);
		application.getContentPane().add(cambioDeEstadoRDV);
		application.getContentPane().add(RutasMasVisitadas);
		
	}
	
	private void initMainFrame() {
		application = new JFrame();
		application.setTitle("Volando.uy");
		application.setBounds(100, 100, 950, 669);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Crear menu: 
		 * Sistema(cerrar) 
		 * Usuarios(Alta,Consulta,Modificar) 
		 * Vuelos(Alta ruta, alta vuelo, alta ciudad, consulta ruta, consulta vuelo, reserva vuelo) 
		 * Paquetes(Crear paquete, agregar ruta a paquete, comprar paquete)
		*/
		
		JMenuBar menu = new JMenuBar();
		application.setJMenuBar(menu);

		getSeccionSistema(menu);
		getSeccionUsuarios(menu);
		getSeccionVuelos(menu);
		getSeccionPaquetes(menu);
	}
	
	private void initTabsUsuarios() {
		altaUsuario = new AltaUsuario();
		altaUsuario.setVisible(false);
		consultaUsuarios = new ConsultaUsuarios();
		consultaUsuarios.setVisible(false);
		modificarUsuario = new ModificarUsuario();
		modificarUsuario.setVisible(false);
	}
	private void initTabsVuelos() {
		altaCiudad = new AltaCiudad();
		altaCiudad.setVisible(false);
		altaVuelo = new AltaVuelo();
		altaVuelo.setVisible(false);
		reservaVuelo = new ReservaVuelo();
		reservaVuelo.setVisible(false);
		consultaVuelo = new ConsultaVuelo();
		consultaVuelo.setVisible(false);
		consultaVuelo.setMain(this);
		consultaRutaDeVuelo = new ConsultaRutaDeVuelo();
		consultaRutaDeVuelo.setVisible(false);
		altaRutaVuelo = new AltaRutaVuelo();
		altaRutaVuelo.setVisible(false);
		cambioDeEstadoRDV = new CambioDeEstadoRDV();
		cambioDeEstadoRDV.setVisible(false);
		RutasMasVisitadas = new RutasMasVisitadas();
		RutasMasVisitadas.setVisible(false);
	}
	private void initTabsPaquetes(){
		crearPaquete = new CrearPaqueteDeRutaDeVuelo();
		crearPaquete.setVisible(false);
		agregarRutaDeVueloAPaquete = new AgregarRutaDeVueloAPaquete();
		agregarRutaDeVueloAPaquete.setVisible(false); 
		comprarPaquete = new ComprarPaquete();
		comprarPaquete.setVisible(false);
		consultaPaquete = new ConsultaPaquete();
		consultaPaquete.setVisible(false);
	}
	
	private JMenu getSeccionSistema(JMenuBar menu) {
		JMenu seccion = new JMenu("Sistema");
		menu.add(seccion);
		
		JMenuItem datos = new JMenuItem("Cargar datos");
		datos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CargadorDatos.cargarDatos();
            }
        });
        seccion.add(datos);
        
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                application.setVisible(false);
                application.dispose();
            }
        });
        seccion.add(salir);
		return seccion;
	}
	private JMenu getSeccionUsuarios(JMenuBar menu) {
		JMenu seccion = new JMenu("Usuarios");
		menu.add(seccion);
		JMenuItem altaUsuarioItem = new JMenuItem("Alta de usuario");
		JMenuItem modificarUsuarioItem = new JMenuItem("Modificar usuario");
		JMenuItem consultaUsuarioItem = new JMenuItem("Consulta de usuario");

		altaUsuarioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				altaUsuario.setVisible(true);
				altaUsuario.changeFormSetup(0);
			}
		});
		modificarUsuarioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				//Inicializar y abrir form de modificar usuario
				modificarUsuario.actualizarCaso();
				modificarUsuario.setVisible(true);
			}
		});
		consultaUsuarioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				consultaUsuarios.setVisible(true);
				consultaUsuarios.actualizarCaso();
				consultaUsuarios.ocultarinicio();
			}
		});
		seccion.add(altaUsuarioItem);
		seccion.add(modificarUsuarioItem);
		seccion.add(consultaUsuarioItem);
		return seccion;
	}
	private JMenu getSeccionVuelos(JMenuBar menu) {
		JMenu seccion = new JMenu("Vuelos");
		menu.add(seccion);
		JMenuItem altaRutaItem = new JMenuItem("Alta de ruta de vuelo");
		JMenuItem altaVueloItem = new JMenuItem("Alta de vuelo");
		JMenuItem altaCiudadItem = new JMenuItem("Alta de ciudad");
		JMenuItem consultaRutaItem = new JMenuItem("Consultar ruta de vuelo");
		JMenuItem consultaVueloItem = new JMenuItem("Consultar vuelo");
		JMenuItem reservaVueloItem = new JMenuItem("Reservar vuelo");
		JMenuItem cambioEstadoItem = new JMenuItem("Modificar Estado de Ruta");
		JMenuItem rutasVisitadasItem = new JMenuItem("Rutas Mas Visitadas");
		

		altaRutaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				altaRutaVuelo.setVisible(true);
				altaRutaVuelo.actualizarCaso();
				//Inicializar y abrir form de alta
			}
		});
		altaVueloItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				altaVuelo.setVisible(true);
				altaVuelo.actualizarCaso();
			}
		});
		altaCiudadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				altaCiudad.setVisible(true);
			}
		});
		consultaRutaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				//Inicializar y abrir consulta
				consultaRutaDeVuelo.actualizarCaso();
				consultaRutaDeVuelo.setVisible(true);
			}
		});
		consultaVueloItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				consultaVuelo.setVisible(true);
				consultaVuelo.actualizarCaso();
			}
		});
		reservaVueloItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				reservaVuelo.setVisible(true);
				reservaVuelo.actualizarCaso();
			}
		});
		cambioEstadoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				cambioDeEstadoRDV.actualizarCaso();
				cambioDeEstadoRDV.setVisible(true);
				//Inicializar y abrir form de alta
			}
		});
		rutasVisitadasItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				RutasMasVisitadas.setVisible(true);
				RutasMasVisitadas.cargarDatosTabla();
			}
		});
		seccion.add(altaRutaItem);
		seccion.add(altaVueloItem);
		seccion.add(altaCiudadItem);
		seccion.add(consultaRutaItem);
		seccion.add(consultaVueloItem);
		seccion.add(reservaVueloItem);
		seccion.add(cambioEstadoItem);
		seccion.add(rutasVisitadasItem);
		return seccion;
	}
	private JMenu getSeccionPaquetes(JMenuBar menu) {
		JMenu seccion = new JMenu("Paquetes");
		menu.add(seccion);
		JMenuItem altaPaqueteItem = new JMenuItem("Alta de paquete");
		JMenuItem agregarRutaItem = new JMenuItem("Agregar ruta a paquete");
		JMenuItem consultaPaqueteItem = new JMenuItem("Consultar paquete");
		JMenuItem comprarPaqueteItem = new JMenuItem("Comprar paquete");

		altaPaqueteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				//Inicializar y abrir form de alta 
				crearPaquete.setVisible(true);
			}
		});
		agregarRutaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actiona) {
				//Inicializar y abrir form de agregar
				agregarRutaDeVueloAPaquete.actualizarCaso();
				agregarRutaDeVueloAPaquete.setVisible(true);
			}
		});
		consultaPaqueteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				//Inicializar y abrir consulta 
				consultaPaquete.cargarDatos();
				consultaPaquete.setVisible(true);
			}
		});
		comprarPaqueteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				comprarPaquete.cargarDatos();
				comprarPaquete.setVisible(true);
			}
		});
		seccion.add(altaPaqueteItem);
		seccion.add(agregarRutaItem);
		seccion.add(consultaPaqueteItem);
		seccion.add(comprarPaqueteItem);
		return seccion;
	}
	public boolean mainAbierto() {
		return application.isVisible();
	}
	public void abrirConsultaRuta() {
		consultaRutaDeVuelo.actualizarCaso();
		consultaRutaDeVuelo.setVisible(true);
	}
}