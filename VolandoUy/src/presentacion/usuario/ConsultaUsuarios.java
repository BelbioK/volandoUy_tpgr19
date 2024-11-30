 package presentacion.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import logica.controladores.Fabrica;
import logica.datatypes.DTAerolinea;
import logica.datatypes.DTCliente;
import logica.datatypes.DTCompraPaquete;
import logica.datatypes.DTReserva;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.DTUsuario;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import presentacion.paquetes.ConsultaPaquete;
import presentacion.vuelos.ConsultaRutaDeVuelo;
import presentacion.vuelos.ConsultaVuelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultaUsuarios extends JInternalFrame{
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorUsuarios controlUsu = fabrica.getControladorUsuario();
	private JTextField textFieldNombreCli;
	private JTextField textFieldNicknameCli;
	private JTextField textFieldEmailCli;
	private JTextField textFieldApellidoCli;
	private JTextField textFieldNumDocumentoCli;
	private JTextField textFieldTipoDocumentoCli;
	private JTextField textFieldNombreAero;
	private JTextField textFieldNicknameAero;
	private JTextField textFieldEmailAero;
	private JTextField textFieldDescripcionAero;
	private JTextField textFieldLinkAero;
	private JComboBox<String> comboBoxUsuarios;
	private JComboBox<String> comboBoxPaquetes;
	private JComboBox<String> comboBoxReservasCli;
	private JComboBox<String> comboBoxRutasAero;
	private JInternalFrame internalFrameInfoCliente;
    private JInternalFrame internalFrameInfoAerolinea_1;
    private Set<String> usuariosSet = controlUsu.listarUsuarios();
    private Object usuSel;
    private ConsultaRutaDeVuelo consuRuta;
    private ConsultaVuelo consuVue;
    private ConsultaPaquete consuPaquete;
    private JDesktopPane desktopPane = new JDesktopPane();
    private JTextField textFieldFechaNacimiento;
    private JTextField textField_contrasenia_aero;
    private JTextField textField_contrasenia_cli;
	public ConsultaUsuarios() {
		setMaximizable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consulta Usuarios");
		setSize(1012, 650);
		getContentPane().setLayout(null);
		setContentPane(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Usuarios Registrados:");
		lblNewLabel.setBounds(23, 28, 127, 14);
		getContentPane().add(lblNewLabel);
		
		 String[] usus;
	        if (usuariosSet == null) {
	        	usus = new String[]{""};
	        } else {
	        	usus = usuariosSet.toArray(new String[0]);
	        }
		comboBoxUsuarios = new JComboBox<String>(usus);
		comboBoxUsuarios.setBounds(220, 24, 200, 22);
		comboBoxUsuarios.setSelectedIndex(-1);
		getContentPane().add(comboBoxUsuarios);
		
		comboBoxUsuarios.addActionListener(e -> { //se selecciona a algun usuario
			usuSel = comboBoxUsuarios.getSelectedItem();
			String usuarioSelect = (String) comboBoxUsuarios.getSelectedItem();
			if(usuSel != null && (!usuSel.equals(""))) {
				DTUsuario usu;
				try {
					usu = controlUsu.mostrarInfoUsuarios(usuarioSelect);
				} catch (InstanciaNoExiste | DatoInvalido e1) {
					e1.printStackTrace();
					return;
				}
				if(usu instanceof DTCliente) {
					DTCliente cliSelect = (DTCliente) usu;
					Map<String, DTCompraPaquete> aux = cliSelect.getPaquetes();
					if(!aux.isEmpty()) {
						Set<String> paqs = aux.keySet();
						reloadCombo(comboBoxPaquetes, paqs);
					}else {
						this.comboBoxPaquetes.removeAllItems();
					}
					Set<DTReserva> aux2 = cliSelect.getReservas();
					Set<String> reser = new HashSet<>();
					if(!aux2.isEmpty()) {
						for(DTReserva dato : aux2) {
							String reservaString = dato.getNombreVuelo();
							reser.add(reservaString);
						}
						reloadCombo(comboBoxReservasCli, reser);
					}else {
						this.comboBoxReservasCli.removeAllItems();
					}
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaNacimiento = cliSelect.getFechaNacimiento();
					String fechaFormateada = formatoFecha.format(fechaNacimiento);
					textFieldFechaNacimiento.setText(fechaFormateada);
					textFieldNombreCli.setText(cliSelect.getNombre());
					textField_contrasenia_cli.setText(cliSelect.getPassword());
					textFieldNicknameCli.setText(cliSelect.getNickname());
					textFieldEmailCli.setText(cliSelect.getMail());
					textFieldApellidoCli.setText(cliSelect.getApellido());
					textFieldNumDocumentoCli.setText(String.valueOf(cliSelect.getNroDocumento()));
					textFieldTipoDocumentoCli.setText(cliSelect.getTipoDocumento().toString());
					this.internalFrameInfoCliente.setVisible(true);
				}else {
					DTAerolinea aeroSelect = (DTAerolinea) usu;
					comboBoxRutasAero.removeAllItems();
					textFieldNombreAero.setText(aeroSelect.getNombre());
					textFieldNicknameAero.setText(aeroSelect.getNickname());
					textField_contrasenia_aero.setText(aeroSelect.getPassword());
					textFieldEmailAero.setText(aeroSelect.getMail());
					textFieldDescripcionAero.setText(aeroSelect.getDescripcion());
					String aeroLink = aeroSelect.getLink();
					if (aeroLink == null || aeroLink.isEmpty()) { 
						textFieldLinkAero.setText("No hay link disponible de la aerolinea");
					}else {
						textFieldLinkAero.setText(aeroSelect.getLink());						
					}
					this.internalFrameInfoAerolinea_1.setVisible(true);
					Map<String, DTRutasDeVuelo> ruts = aeroSelect.getRutas();
					if(!ruts.isEmpty()) {
						Set<String> rutsString = ruts.keySet();
						reloadCombo(comboBoxRutasAero, rutsString);
					}else {
						this.comboBoxRutasAero.removeAllItems();
					}
				}
				setSize(650, 600);
			}
		});
		
		JButton BotonCancelarConsulta = new JButton("Cancelar");
		BotonCancelarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				limpiarFormulario();
                setVisible(false);
			}
		});
		BotonCancelarConsulta.setBounds(181, 68, 85, 21);
		getContentPane().add(BotonCancelarConsulta);
		
		internalFrameInfoCliente = new JInternalFrame("Informacion Cliente");
		internalFrameInfoCliente.setBounds(45, 11, 521, 453);
		desktopPane.add(internalFrameInfoCliente);
		internalFrameInfoCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrameInfoCliente.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(20, 11, 75, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_Nickname = new JLabel("Nickname:");
		lblNewLabel_Nickname.setBounds(20, 36, 75, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_Nickname);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(20, 61, 60, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apellido:");
		lblNewLabel_4.setBounds(20, 86, 75, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo De Documento:");
		lblNewLabel_6.setBounds(20, 192, 120, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Num. Documento:");
		lblNewLabel_7.setBounds(20, 167, 120, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_7);
		
		textFieldNombreCli = new JTextField();
		textFieldNombreCli.setEditable(false);
		textFieldNombreCli.setBounds(150, 8, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldNombreCli);
		textFieldNombreCli.setColumns(10);
		
		textFieldNicknameCli = new JTextField();
		textFieldNicknameCli.setEditable(false);
		textFieldNicknameCli.setBounds(150, 33, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldNicknameCli);
		textFieldNicknameCli.setColumns(10);
		
		textFieldEmailCli = new JTextField();
		textFieldEmailCli.setEditable(false);
		textFieldEmailCli.setBounds(150, 58, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldEmailCli);
		textFieldEmailCli.setColumns(10);
		
		textFieldApellidoCli = new JTextField();
		textFieldApellidoCli.setEditable(false);
		textFieldApellidoCli.setBounds(150, 83, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldApellidoCli);
		textFieldApellidoCli.setColumns(10);
		
		textFieldNumDocumentoCli = new JTextField();
		textFieldNumDocumentoCli.setEditable(false);
		textFieldNumDocumentoCli.setBounds(150, 164, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldNumDocumentoCli);
		textFieldNumDocumentoCli.setColumns(10);
		
		textFieldTipoDocumentoCli = new JTextField();
		textFieldTipoDocumentoCli.setEditable(false);
		textFieldTipoDocumentoCli.setBounds(150, 189, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldTipoDocumentoCli);
		textFieldTipoDocumentoCli.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Paquetes:");
		lblNewLabel_5.setBounds(20, 232, 76, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_5);
		
		comboBoxPaquetes = new JComboBox<String>();
		comboBoxPaquetes.setBounds(150, 228, 199, 22);
		comboBoxPaquetes.setSelectedIndex(-1);
		internalFrameInfoCliente.getContentPane().add(comboBoxPaquetes);
		
		JButton BotonCancelarInfoCliente = new JButton("Cancelar");
		BotonCancelarInfoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				limpiarFormulario();
				internalFrameInfoCliente.setVisible(false);
				setSize(525, 150);
			}
		});
		
		BotonCancelarInfoCliente.setBounds(150, 376, 89, 23);
		internalFrameInfoCliente.getContentPane().add(BotonCancelarInfoCliente);
		
		JButton BotonConsultaPaquete = new JButton("Consulta Paquete"); 
		BotonConsultaPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				consuPaquete = new ConsultaPaquete();
				desktopPane.add(consuPaquete);
				int xxx = (desktopPane.getWidth() - consuPaquete.getWidth()) / 2;
			    int yyy = (desktopPane.getHeight() - consuPaquete.getHeight()) / 2;
			    consuPaquete.setLocation(xxx, yyy);
			    consuPaquete.cargarDatos();
			    consuPaquete.setVisible(true);
			}
		});
		
		BotonConsultaPaquete.setBounds(150, 261, 160, 23);
		internalFrameInfoCliente.getContentPane().add(BotonConsultaPaquete);
		
		JLabel lblNewLabel_13 = new JLabel("Reservas de Vuelo:");
		lblNewLabel_13.setBounds(20, 313, 114, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_13);
		
		comboBoxReservasCli = new JComboBox<String>();
		comboBoxReservasCli.setBounds(150, 309, 199, 22);
		comboBoxReservasCli.setSelectedIndex(-1);
		internalFrameInfoCliente.getContentPane().add(comboBoxReservasCli);
		
		JButton BotonConsultaVueloCliente = new JButton("Consulta Vuelo"); 
		BotonConsultaVueloCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				consuVue = new ConsultaVuelo();
				desktopPane.add(consuVue);
				int xxx = (desktopPane.getWidth() - consuVue.getWidth()) / 2;
			    int yyy = (desktopPane.getHeight() - consuVue.getHeight()) / 2;
			    consuVue.setLocation(xxx, yyy);
				consuVue.actualizarCaso();
				consuVue.setVisible(true);
			}
		});
		
		BotonConsultaVueloCliente.setBounds(150, 342, 158, 23);
		internalFrameInfoCliente.getContentPane().add(BotonConsultaVueloCliente);
		
		JLabel lblNewLabel_14 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_14.setBounds(20, 142, 133, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_14);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setBounds(150, 139, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Contrasenia:");
		lblNewLabel_16.setBounds(20, 117, 75, 14);
		internalFrameInfoCliente.getContentPane().add(lblNewLabel_16);
		
		textField_contrasenia_cli = new JTextField();
		textField_contrasenia_cli.setEditable(false);
		textField_contrasenia_cli.setBounds(150, 114, 199, 20);
		internalFrameInfoCliente.getContentPane().add(textField_contrasenia_cli);
		textField_contrasenia_cli.setColumns(10);
		
		internalFrameInfoAerolinea_1 = new JInternalFrame("Informacion Aerolinea");
		internalFrameInfoAerolinea_1.setBounds(83, 53, 450, 272);
		desktopPane.add(internalFrameInfoAerolinea_1);
		internalFrameInfoAerolinea_1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrameInfoAerolinea_1.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(33, 12, 63, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_8 = new JLabel("Nickname:");
		lblNewLabel_8.setBounds(33, 36, 73, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Email:");
		lblNewLabel_9.setBounds(33, 61, 53, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Descripcion:");
		lblNewLabel_10.setBounds(33, 117, 84, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Link:");
		lblNewLabel_11.setBounds(33, 142, 53, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_11);
		
		textFieldNombreAero = new JTextField();
		textFieldNombreAero.setEditable(false);
		textFieldNombreAero.setBounds(127, 8, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textFieldNombreAero);
		textFieldNombreAero.setColumns(10);
		
		textFieldNicknameAero = new JTextField();
		textFieldNicknameAero.setEditable(false);
		textFieldNicknameAero.setBounds(127, 33, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textFieldNicknameAero);
		textFieldNicknameAero.setColumns(10);
		
		textFieldEmailAero = new JTextField();
		textFieldEmailAero.setEditable(false);
		textFieldEmailAero.setBounds(127, 58, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textFieldEmailAero);
		textFieldEmailAero.setColumns(10);
		
		textFieldDescripcionAero = new JTextField();
		textFieldDescripcionAero.setEditable(false);
		textFieldDescripcionAero.setBounds(127, 114, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textFieldDescripcionAero);
		textFieldDescripcionAero.setColumns(10);
		
		textFieldLinkAero = new JTextField();
		textFieldLinkAero.setEditable(false);
		textFieldLinkAero.setBounds(127, 139, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textFieldLinkAero);
		textFieldLinkAero.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Rutas de Vuelo:");
		lblNewLabel_12.setBounds(33, 174, 95, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_12);									
		
		JButton BotonConsultarRuta = new JButton("Consultar Ruta"); //al momento de oprimir el boton se debe cerrar todo y abrir la pestaña del caso de uso Consulta de Ruta
		BotonConsultarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				consuRuta = new ConsultaRutaDeVuelo();
				desktopPane.add(consuRuta);
				int xxx = (desktopPane.getWidth() - consuRuta.getWidth()) / 2;
			    int yyy = (desktopPane.getHeight() - consuRuta.getHeight()) / 2;
			    consuRuta.setLocation(xxx, yyy);
				consuRuta.actualizarCaso(); // Actualizar información si es necesario
				consuRuta.setVisible(true);
			}
		});
		
		BotonConsultarRuta.setBounds(127, 209, 144, 23);
		internalFrameInfoAerolinea_1.getContentPane().add(BotonConsultarRuta);
		
		comboBoxRutasAero = new JComboBox<String>();
		comboBoxRutasAero.setBounds(127, 170, 255, 22);
		comboBoxRutasAero.setSelectedIndex(-1);
		internalFrameInfoAerolinea_1.getContentPane().add(comboBoxRutasAero);
		
		JButton BotonCancelarInfoAerolinea = new JButton("Cancelar");
		BotonCancelarInfoAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				limpiarFormulario();
				internalFrameInfoAerolinea_1.setVisible(false);
				setSize(525, 150);
			}
		});
		
		BotonCancelarInfoAerolinea.setBounds(292, 209, 89, 23);
		internalFrameInfoAerolinea_1.getContentPane().add(BotonCancelarInfoAerolinea);
		
		JLabel lblNewLabel_15 = new JLabel("Contrasenia:");
		lblNewLabel_15.setBounds(33, 86, 84, 14);
		internalFrameInfoAerolinea_1.getContentPane().add(lblNewLabel_15);
		
		textField_contrasenia_aero = new JTextField();
		textField_contrasenia_aero.setEditable(false);
		textField_contrasenia_aero.setBounds(127, 83, 255, 20);
		internalFrameInfoAerolinea_1.getContentPane().add(textField_contrasenia_aero);
		textField_contrasenia_aero.setColumns(10);
		internalFrameInfoAerolinea_1.setVisible(true);
		internalFrameInfoCliente.setVisible(true);
		
		ocultarinicio();
		limpiarFormulario();
	}
	 
	 private void limpiarFormulario() {
		 textFieldNicknameCli.setText("");
		 textFieldEmailCli.setText("");
		 textFieldApellidoCli.setText("");
		 textFieldNumDocumentoCli.setText("");
		 textFieldTipoDocumentoCli.setText("");
		 textFieldNombreAero.setText("");
		 textFieldNicknameAero.setText("");
		 textFieldEmailAero.setText("");
		 textFieldDescripcionAero.setText("");
		 textFieldLinkAero.setText("");
		 textField_contrasenia_aero.setText("");
		 textField_contrasenia_cli.setText("");
	   }
	 
	 public void actualizarCaso() {
			reloadCombo(comboBoxUsuarios, controlUsu.listarUsuarios());
			setSize(525, 150);
		}
	 
	 private void reloadCombo(JComboBox<String> combo, Set<String> items) {
			combo.removeAllItems();
			for(String item:items) {
				combo.addItem(item);
			}	
			combo.setSelectedIndex(-1);
	 }
	 
	 public void ocultarinicio() {
		 this.internalFrameInfoCliente.setVisible(false);
		 this.internalFrameInfoAerolinea_1.setVisible(false);
	 }
}
