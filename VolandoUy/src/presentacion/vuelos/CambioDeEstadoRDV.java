package presentacion.vuelos;


import javax.swing.JInternalFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.controladores.Fabrica;
import logica.datatypes.DTRutasDeVuelo;
import logica.datatypes.Estado;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class CambioDeEstadoRDV extends JInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorUsuarios controladorUsuario = fabrica.getControladorUsuario();
	private IControladorVuelos controladorVuelos = fabrica.getControladorVuelos();
	
	private JComboBox<String> comboBox_aerolinea;
	private JComboBox<String> comboBox_Estado;
	/**
	 * Create the frame.
	 */
	public CambioDeEstadoRDV() {
		setTitle("Cambio de estado");
		setClosable(true);
		setBounds(100, 100, 293, 264);
		
		JLabel lblNewLabel = new JLabel("Seleccione la aerolínea creadora de la Ruta de Vuelo");
		
		comboBox_aerolinea = new JComboBox<String>();
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione la Ruta de Vuelo");
		
		JComboBox<String> comboBox_Ruta = new JComboBox<String>();
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione el estado a setear");
		
		comboBox_Estado = new JComboBox<String>();
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				String selectedItem = (String) comboBox_Estado.getSelectedItem();
				String selectedRuta = (String) comboBox_Ruta.getSelectedItem();
				if (comboBox_Estado.getSelectedItem()!=null) {		
					try {
						controladorVuelos.modificarEstado(selectedRuta, selectedItem.equals("Confirmada") ? Estado.Confirmada : Estado.Rechazada, null);
					} catch (InstanciaNoExiste e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Se ha modificado con exito el estado","Cambio de estado", JOptionPane.INFORMATION_MESSAGE);
				} else {JOptionPane.showMessageDialog(null, "Seleccione todos los campos correctamente", "Error", JOptionPane.ERROR_MESSAGE);}
				
			}
		});
		
		Set<String> options = controladorUsuario.listarAerolineas();
		String[] aerolineas = options.toArray(new String[0]);
		comboBox_aerolinea.setModel(new DefaultComboBoxModel<>(aerolineas));
		comboBox_aerolinea.setSelectedIndex(-1);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox_Estado, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox_Ruta, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox_aerolinea, Alignment.LEADING, 0, 233, Short.MAX_VALUE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(178, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(161, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_aerolinea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_Ruta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_Estado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

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
						rutas = controladorVuelos.getRutasStatusIngresada(aerolineaSeleccionada);
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
						Set<String> estados = new HashSet<>(Arrays.asList("Confirmada", "Rechazada"));
						String[] estadosString = estados.toArray(new String[0]);
						comboBox_Estado.setModel(new DefaultComboBoxModel<>(estadosString));
						comboBox_Estado.setSelectedItem(ruta.getEstado());
						
					} catch (InstanciaNoExiste | DatoInvalido e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}        
		    }
		});
		
	}
	public void actualizarCaso() {
		//aerolinea
		reloadCombo(comboBox_aerolinea, controladorUsuario.listarAerolineas());
		comboBox_aerolinea.setSelectedIndex(-1);
		comboBox_Estado.removeAllItems();
	}


	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
	    combo.removeAllItems();
	    for(String item:items)
	    	combo.addItem(item);
	}
}

