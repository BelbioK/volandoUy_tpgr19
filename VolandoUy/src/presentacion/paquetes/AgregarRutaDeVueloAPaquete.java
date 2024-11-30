package presentacion.paquetes;


import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSpinner;

import logica.controladores.Fabrica;
import logica.datatypes.Asiento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaNoModificable;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorPaquetes;
import logica.interfaces.IControladorVuelos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class AgregarRutaDeVueloAPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorPaquetes controladorPaquetes = fabrica.getControladorPaquetes();
	private IControladorVuelos controladorVuelos = fabrica.getControladorVuelos();
	
	private JComboBox<String> comboBox_paquete = new JComboBox<String>();
	private JComboBox<String> comboBox_ruta = new JComboBox<String>();
	private JComboBox<String> comboBox_asiento = new JComboBox<String>();
	private JSpinner spinner_cantidad;

	public AgregarRutaDeVueloAPaquete() {
		setClosable(true);
		setTitle("Agregar Ruta de Vuelo a Paquete");
		setBounds(100, 100, 311, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Paquete:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		//comboBox_paquete;
		GridBagConstraints gbc_comboBox_paquete = new GridBagConstraints();
		gbc_comboBox_paquete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_paquete.gridwidth = 2;
		gbc_comboBox_paquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_paquete.gridx = 2;
		gbc_comboBox_paquete.gridy = 2;
		getContentPane().add(comboBox_paquete, gbc_comboBox_paquete);
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona la Ruta a agregar:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//JComboBox comboBox_ruta;
		GridBagConstraints gbc_comboBox_ruta = new GridBagConstraints();
		gbc_comboBox_ruta.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_ruta.gridwidth = 2;
		gbc_comboBox_ruta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_ruta.gridx = 2;
		gbc_comboBox_ruta.gridy = 4;
		getContentPane().add(comboBox_ruta, gbc_comboBox_ruta);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese cantidad de Ruta:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 6;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		spinner_cantidad = new JSpinner();
		GridBagConstraints gbc_spinner_cantidad = new GridBagConstraints();
		gbc_spinner_cantidad.anchor = GridBagConstraints.WEST;
		gbc_spinner_cantidad.gridwidth = 2;
		gbc_spinner_cantidad.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_cantidad.gridx = 3;
		gbc_spinner_cantidad.gridy = 6;
		getContentPane().add(spinner_cantidad, gbc_spinner_cantidad);
		
		JLabel lblNewLabel_3 = new JLabel("En el tipo de asiento seleccionado:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 7;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		comboBox_asiento = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_asiento = new GridBagConstraints();
		gbc_comboBox_asiento.gridwidth = 2;
		gbc_comboBox_asiento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_asiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_asiento.gridx = 2;
		gbc_comboBox_asiento.gridy = 8;
		getContentPane().add(comboBox_asiento, gbc_comboBox_asiento);
		
		
		JButton BotonAceptar = new JButton("Agregar");
		BotonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				agregarAPaquete();
			}
		});
		GridBagConstraints gbc_BotonAceptar = new GridBagConstraints();
		gbc_BotonAceptar.anchor = GridBagConstraints.EAST;
		gbc_BotonAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_BotonAceptar.gridx = 2;
		gbc_BotonAceptar.gridy = 11;
		getContentPane().add(BotonAceptar, gbc_BotonAceptar);
		
		JButton BotonCancelar = new JButton("Cancelar");
		BotonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_BotonCancelar = new GridBagConstraints();
		gbc_BotonCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_BotonCancelar.gridwidth = 2;
		gbc_BotonCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_BotonCancelar.gridx = 3;
		gbc_BotonCancelar.gridy = 11;
		getContentPane().add(BotonCancelar, gbc_BotonCancelar);

	}
	public void actualizarCaso() {
		Set<String> paq = controladorPaquetes.listarPaquetesSinCompras();
		if(!paq.isEmpty()) reloadCombo(comboBox_paquete, paq );
		Set<String> rutas = controladorVuelos.listarAllRutas();
		if(!rutas.isEmpty()) reloadCombo(comboBox_ruta, rutas);
		Set<String> asi = new HashSet<>();
		asi.add("Turista");
		asi.add("Ejecutivo");
		reloadCombo(comboBox_asiento, asi);
		this.comboBox_paquete.setSelectedIndex(-1);
		this.comboBox_ruta.setSelectedIndex(-1);
		this.comboBox_asiento.setSelectedIndex(-1);
	}


	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedIndex(-1);
	    combo.removeAllItems();
	    if(!items.isEmpty()) {
	    for(String item:items)
	    	combo.addItem(item);
	    }
	}
	
	private void agregarAPaquete() {
		if(verificacionDatos()) {
			String paquete = (String) comboBox_paquete.getSelectedItem();
			String ruta = (String) comboBox_ruta.getSelectedItem();
			String asiento = (String) comboBox_asiento.getSelectedItem();
			int cant = (Integer) spinner_cantidad.getValue();
			if (asiento.equals(Asiento.Turista.toString())) {
				try {
					controladorPaquetes.agregarRutaAPaquete(paquete, ruta, Asiento.Turista, cant);
					JOptionPane.showMessageDialog(this, "Ruta agregada exitosamente al paquete.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				} catch (InstanciaNoExiste | DatoInvalido | InstanciaNoModificable | InstanciaRepetida e ) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Ruta De Vuelo A Paquete",
		                    JOptionPane.ERROR_MESSAGE);
				}
			}else {//tipo de asiento es Ejecutivo
				try {
					controladorPaquetes.agregarRutaAPaquete(paquete, ruta, Asiento.Ejecutivo, cant);
					JOptionPane.showMessageDialog(this, "Ruta agregada exitosamente al paquete.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				} catch (InstanciaNoExiste | DatoInvalido | InstanciaNoModificable | InstanciaRepetida e ) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Ruta De Vuelo A Paquete",
		                    JOptionPane.ERROR_MESSAGE);
				}
			}
				
		}
	}
	
	 private boolean verificacionDatos() {
		 String paquete = (String) comboBox_paquete.getSelectedItem();
		 String ruta = (String) comboBox_ruta.getSelectedItem();
		 String asiento = (String) comboBox_asiento.getSelectedItem();
		 int cant = (Integer) spinner_cantidad.getValue();
		 if(paquete == null || paquete.isBlank() || asiento == null || asiento.isBlank() || ruta == null || ruta.isBlank()) {
			 JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Ruta De Vuelo A Paquete",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
		 }
		 if(cant <= 0 ) {
			 JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Agregar Ruta De Vuelo A Paquete", JOptionPane.ERROR_MESSAGE);
		        return false;
		 }

		return true;
	}
	 
	private void limpiarFormulario() {
		 this.comboBox_paquete.setSelectedIndex(-1);
		 this.comboBox_ruta.setSelectedIndex(-1);
		 this.comboBox_asiento.setSelectedIndex(-1);
		 spinner_cantidad.setValue(0);
	 }
	
}
