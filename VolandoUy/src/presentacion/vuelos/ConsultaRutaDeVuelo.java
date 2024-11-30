package presentacion.vuelos;

import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.controladores.Fabrica;
import logica.datatypes.DTCategoria;
import logica.datatypes.DTRutasDeVuelo;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;

public class ConsultaRutaDeVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorUsuarios controladorUsuario = fabrica.getControladorUsuario();
	private IControladorVuelos controladorVuelos = fabrica.getControladorVuelos();
	private JTextField textField_nombre;
	private JTextField textField_descripcion;
	private JTextField textField_costTur;
	private JTextField textField_hora;
	private JTextField textField_costEqEx;
	private JTextField textField_costEj;
	private JTextField textField_destino;
	private JTextField textField_origen;
	private JTextField textField_cat;
	private JTextField textField_alta;
	private JComboBox<String> comboBox_aerolinea;
	private JComboBox<String> comboBox_Ruta;
	private ConsultaVuelo consultaVuelo;
	private JList<String> list;
	private String[] arrayVacio = {};
	
	private JDesktopPane desktopPane = new JDesktopPane();
	private JTextField textField_estado;
	private JTextField textField_Visitas;

	public ConsultaRutaDeVuelo() {
		setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consulta de Ruta de Vuelo");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 532, 391);
		
		setContentPane(desktopPane);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				setVisible(false);
				desSeleccionar();
				limpiarFormulario();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Consultar Vuelo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				//Abrir caso de uso de consulta Vuelos
				consultaVuelo = new ConsultaVuelo();
				desktopPane.add(consultaVuelo);
				consultaVuelo.actualizarCaso();
				consultaVuelo.setVisible(true);
			}
		});
		
		
		
		Set<String> options = controladorUsuario.listarAerolineas();
		

		//Set<String> options = Set.of("1", "2", "3");
		comboBox_aerolinea = new JComboBox<String>();
		String[] aerolineas = options.toArray(new String[0]);
		comboBox_aerolinea.setModel(new DefaultComboBoxModel<>(aerolineas));
		comboBox_aerolinea.setSelectedIndex(-1);
		
		
		
		//JComboBox comboBox = new JComboBox();
		//String[] opciones = {"Aerolinea 1", "Aerolinea 2", "Aerolinea 3"};
        //comboBox.setModel(new DefaultComboBoxModel<>(opciones));
		
		comboBox_Ruta = new JComboBox<String>();
		
		JLabel lblNewLabel = new JLabel("Ruta de Vuelo");
		
		textField_nombre = new JTextField();
		textField_nombre.setEditable(false);
		textField_nombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles ruta");
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		
		textField_descripcion = new JTextField();
		textField_descripcion.setEditable(false);
		textField_descripcion.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Descripcion");
		
		textField_costTur = new JTextField();
		textField_costTur.setEditable(false);
		textField_costTur.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("Costo Tur");
		
		textField_hora = new JTextField();
		textField_hora.setEditable(false);
		textField_hora.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Hora");
		
		textField_costEqEx = new JTextField();
		textField_costEqEx.setEditable(false);
		textField_costEqEx.setColumns(10);
		
		JLabel lblNewLabel_4_2 = new JLabel("Costo Eq Extra");
		
		textField_costEj = new JTextField();
		textField_costEj.setEditable(false);
		textField_costEj.setColumns(10);
		
		JLabel lblNewLabel_3_2 = new JLabel("Costo Ej");
		
		textField_destino = new JTextField();
		textField_destino.setEditable(false);
		textField_destino.setColumns(10);
		
		JLabel lblNewLabel_4_3 = new JLabel("Destino");
		
		textField_origen = new JTextField();
		textField_origen.setEditable(false);
		textField_origen.setColumns(10);
		
		JLabel lblNewLabel_3_3 = new JLabel("Origen");
		
		textField_cat = new JTextField();
		textField_cat.setEditable(false);
		textField_cat.setColumns(10);
		
		JLabel lblNewLabel_4_4 = new JLabel("Categoria");
		
		textField_alta = new JTextField();
		textField_alta.setEditable(false);
		textField_alta.setColumns(10);
		
		JLabel lblNewLabel_3_4 = new JLabel("Fecha alta");
		
		JLabel lblNewLabel_1_1 = new JLabel("Aerolínea");
		String[] nombres = {};
		list = new JList<>(nombres);
		
		JLabel lblNewLabel_1 = new JLabel("Vuelos de ruta:");
		
		JLabel lblNewLabel_5 = new JLabel("Estado de ruta");
		
		textField_estado = new JTextField();
		textField_estado.setEditable(false);
		textField_estado.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad de Visitas:");
		
		textField_Visitas = new JTextField();
		textField_Visitas.setEditable(false);
		textField_Visitas.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_aerolinea, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_Ruta, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_nombre, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_4, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_hora)
										.addComponent(textField_costEj)
										.addComponent(textField_origen)
										.addComponent(textField_alta)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_estado, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_descripcion, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
												.addComponent(lblNewLabel_4_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_costEqEx, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
												.addComponent(textField_costTur, 191, 191, Short.MAX_VALUE)
												.addComponent(lblNewLabel_4_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_destino, 191, 191, Short.MAX_VALUE)
												.addComponent(lblNewLabel_4_4, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_cat, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
												.addComponent(lblNewLabel_6)
												.addComponent(textField_Visitas, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addGap(61)
									.addComponent(btnNewButton)))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addGap(68))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_aerolinea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_3_1)
						.addComponent(lblNewLabel_4_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox_Ruta, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(153)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(31)
											.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_hora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_costTur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_2)
								.addComponent(lblNewLabel_4_2))
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_costEj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_costEqEx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_3)
								.addComponent(lblNewLabel_4_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_origen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_destino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_4)
								.addComponent(lblNewLabel_4_4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_alta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_cat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_estado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Visitas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(35))
		);
		getContentPane().setLayout(groupLayout);

		// Agregar ActionListener al comboBox de aerolíneas
				comboBox_aerolinea.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent exception) {
				        // Obtener la aerolínea seleccionada
				        String aerolineaSeleccionada = (String) comboBox_aerolinea.getSelectedItem();
				        
				        // Limpiar el comboBox de rutas
				        comboBox_Ruta.removeAllItems();
				        
				        Set<String> rutas;
				        if(aerolineaSeleccionada!=null) {
							try {
								rutas = controladorVuelos.listarRutasDeVuelo(aerolineaSeleccionada);
								String[] rutasString = rutas.toArray(new String[0]);
								comboBox_Ruta.setModel(new DefaultComboBoxModel<>(rutasString));
								comboBox_Ruta.setSelectedIndex(-1);
							} catch (DatoInvalido e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        }
				        /*String[] rutasString = rutas.toArray(new String[0]);
						comboBox_Ruta.setModel(new DefaultComboBoxModel<>(rutasString));
						comboBox_Ruta.setSelectedIndex(-1);*/
				    }
				});
				// Agregar ActionListener al comboBox de rutas
				comboBox_Ruta.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent exception) {
				        // Obtener la ruta seleccionada
				    	if (comboBox_Ruta.getSelectedIndex()!=-1) {
					        String rutaSeleccionada = (String) comboBox_Ruta.getSelectedItem();
					        DTRutasDeVuelo ruta;
							try {
								ruta = controladorVuelos.getRutaDeVuelo(rutaSeleccionada);
								// Obtener la información de la ruta

						        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
						        String fechaFormateada = formatoFecha.format(ruta.getFechaAlta());
						        
						        String horaFormateada = ruta.getHora().format(DateTimeFormatter.ofPattern("HH:mm"));
						        
						        String costoTurista = String.valueOf(ruta.getCostoTurista());
						        String costoEjecutivo = String.valueOf(ruta.getCostoEjecutivo());
						        String costoEqEx = String.valueOf(ruta.getCostoEquipaje());
						        String visitas = String.valueOf(ruta.getVisitas());
						        
						        Set<String> vuelos;
								try {
									if(ruta.getNombre()!=null) {
										vuelos = controladorVuelos.listarVuelosDeRuta(ruta.getNombre());
										DefaultListModel<String> listModel = new DefaultListModel<>();
									     // Agregar los vuelos al modelo
									        for (String vuelo : vuelos) {
									            listModel.addElement(vuelo);
									        }
					
									        // Establecer el modelo en la JList
									        list.setModel(listModel);
									        
									        textField_nombre.setText(ruta.getNombre());
									        textField_descripcion.setText(ruta.getDescripcion());
									        textField_hora.setText(horaFormateada);
									        textField_alta.setText(fechaFormateada);
									        textField_costTur.setText(costoTurista);
									        textField_costEj.setText(costoEjecutivo);
									        textField_costEqEx.setText(costoEqEx);
									        textField_destino.setText(ruta.getDestino().getResumen());
									        textField_origen.setText(ruta.getOrigen().getResumen());
									        textField_estado.setText(ruta.getEstado().name());
									        textField_Visitas.setText(visitas);									        
									        String categorias="";
									        for(DTCategoria cat: ruta.getCategoria())
									        	categorias+=cat.getNombre();
									        textField_cat.setText(categorias);
									}
								} catch (InstanciaNoExiste e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} catch (InstanciaNoExiste | DatoInvalido e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	}        
				    }
				});
		
	}
	private void limpiarFormulario() {
		textField_nombre.setText("");
        textField_descripcion.setText("");
        textField_hora.setText("");
        textField_alta.setText("");
        textField_costTur.setText("");
        textField_costEj.setText("");
        textField_costEqEx.setText("");
        textField_destino.setText("");
        textField_origen.setText("");
        textField_cat.setText("");
        textField_estado.setText("");
        textField_Visitas.setText("");
        list.setListData(arrayVacio);
        
	}
	private void desSeleccionar() {
		if(comboBox_aerolinea.getSelectedIndex()==-1) {
			comboBox_aerolinea.setSelectedIndex(-1);
		}
		if(comboBox_Ruta.getSelectedIndex()==-1) {
	    	comboBox_Ruta.setSelectedIndex(-1);
		}
	}
	public void actualizarCaso() {
		//aerolinea
		reloadCombo(comboBox_aerolinea, controladorUsuario.listarAerolineas());
		comboBox_aerolinea.setSelectedIndex(-1);
		System.out.print("consulta vuelo actualizado \n");
	}


	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
	    combo.removeAllItems();
	    for(String item:items)
	    	combo.addItem(item);
	}
}
