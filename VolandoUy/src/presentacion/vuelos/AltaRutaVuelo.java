package presentacion.vuelos;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;

import logica.controladores.Fabrica;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class AltaRutaVuelo extends JInternalFrame{
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorVuelos controlVue = fabrica.getControladorVuelos();
	private JLabel nombreRutaLabel;
	private JTextField nombreRutaField;
	private JLabel descripcionLabel;
	private JTextField descripcionField;
	private JLabel aerolineaLabel;
	private JComboBox<String> commboBoxAerolinea;
	private JLabel lblHora;
	private JLabel costoTuristLabel;
	private JTextField costoTuristField;
	private JLabel costoEjecLabel;
	private JTextField costoEjecField;
	private JLabel costoEquipExtraLabel;
	private JTextField costoEquipajeExtraField;
	private JLabel ciudadOrigLabel;
	private JComboBox<String> comboboxCiudadOrig;
	private JLabel ciudadDestLabel;
	private JComboBox<String> comboboxCiudadDest;
	private IControladorUsuarios cUsuario;
	IControladorVuelos cVuelos;
	private JButton aceptarButton;
	private JButton cancelarButton;
	private JPanel panel;
	private JSpinner horaSpinner;
	private JSpinner minutoSpinner;
	private JLabel lblCategorias;
	private JPanel panel_1;
	private JComboBox<String> comboboxCategorias;
	private JButton categoriaButton;
	private Set<String> categoriasSeleccionadas;
	
	public AltaRutaVuelo(){
		setTitle("Alta de ruta de vuelo");
		setSize(650, 500);
		getContentPane().setLayout(new GridLayout(11, 2, 0, 0));
		
		aerolineaLabel = new JLabel("Aerolineas");
		getContentPane().add(aerolineaLabel);
		
		commboBoxAerolinea = new JComboBox<String>();
		getContentPane().add(commboBoxAerolinea);
		
		nombreRutaLabel = new JLabel("Nombre");
		getContentPane().add(nombreRutaLabel);
		
		nombreRutaField = new JTextField();
		getContentPane().add(nombreRutaField);
		nombreRutaField.setColumns(10);
		
		descripcionLabel = new JLabel("Descripcion");
		getContentPane().add(descripcionLabel);
		
		descripcionField = new JTextField();
		getContentPane().add(descripcionField);
		descripcionField.setColumns(10);
		
		lblHora = new JLabel("Hora");
		getContentPane().add(lblHora);
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		horaSpinner = new JSpinner();
		panel.add(horaSpinner);
		
		minutoSpinner = new JSpinner();
		panel.add(minutoSpinner);
		
		costoTuristLabel = new JLabel("Costo turista");
		getContentPane().add(costoTuristLabel);
		
		costoTuristField = new JTextField();
		costoTuristField.setColumns(10);
		getContentPane().add(costoTuristField);
		
		costoEjecLabel = new JLabel("Costo ejecutivo");
		getContentPane().add(costoEjecLabel);
		
		costoEjecField = new JTextField();
		costoEjecField.setColumns(10);
		getContentPane().add(costoEjecField);
		
		costoEquipExtraLabel = new JLabel("Costo equipaje extra (por unidad)");
		getContentPane().add(costoEquipExtraLabel);
		
		costoEquipajeExtraField = new JTextField();
		costoEquipajeExtraField.setColumns(10);
		getContentPane().add(costoEquipajeExtraField);
		
		ciudadOrigLabel = new JLabel("Ciudad origen");
		getContentPane().add(ciudadOrigLabel);
		
		comboboxCiudadOrig = new JComboBox<String>();
		getContentPane().add(comboboxCiudadOrig);
		
		ciudadDestLabel = new JLabel("Ciudad destino");
		getContentPane().add(ciudadDestLabel);
		
		comboboxCiudadDest = new JComboBox<String>();
		getContentPane().add(comboboxCiudadDest);
		
		lblCategorias = new JLabel("Categorias");
		getContentPane().add(lblCategorias);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{158, 158, 0};
		gbl_panel_1.rowHeights = new int[]{42, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		comboboxCategorias = new JComboBox<String>();
		GridBagConstraints gbc_comboboxCategorias = new GridBagConstraints();
		gbc_comboboxCategorias.fill = GridBagConstraints.BOTH;
		gbc_comboboxCategorias.insets = new Insets(0, 0, 0, 5);
		gbc_comboboxCategorias.gridx = 0;
		gbc_comboboxCategorias.gridy = 0;
		panel_1.add(comboboxCategorias, gbc_comboboxCategorias);
		
		categoriaButton = new JButton("Agregar categoria");
		GridBagConstraints gbc_agregarCategoriaButton = new GridBagConstraints();
		gbc_agregarCategoriaButton.fill = GridBagConstraints.BOTH;
		gbc_agregarCategoriaButton.gridx = 1;
		gbc_agregarCategoriaButton.gridy = 0;
		panel_1.add(categoriaButton, gbc_agregarCategoriaButton);
		
		aceptarButton = new JButton("Aceptar");
		getContentPane().add(aceptarButton);
		
		cancelarButton = new JButton("Cancelar");
		getContentPane().add(cancelarButton);
		
		
		
		
		categoriasSeleccionadas = new HashSet<>();
		
		Fabrica fab = Fabrica.getInstancia();
		cUsuario = fab.getControladorUsuario();
		cVuelos = fab.getControladorVuelos();
		
		//Carga de datos
		// Acciones de los botones
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
            	aceptar();
            }
        });
        

        categoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
            	selectCategory();
            	
            }
        });
        
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
            	setVisible(false);
            	resetFormulario();
            }
        });
        
	}
	
	
	private void aceptar() {
        String nombre = nombreRutaField.getText();
        String descripcion = descripcionField.getText();
        String hora = horaSpinner.getValue().toString();
        String minuto = minutoSpinner.getValue().toString();
        String costoTurista = costoTuristField.getText();
        String costoEjecutivo = costoEjecField.getText();
        String costoEquipExtra = costoEquipajeExtraField.getText();
        
        if (validacionDatos(nombre, descripcion, hora, minuto, costoTurista, costoEjecutivo, costoEquipExtra)){
        	int hor = Integer.parseInt(hora);
        	int min = Integer.parseInt(minuto);
        	LocalTime horaFormat = LocalTime.of(hor, min);
        	
        	String aerolinea = commboBoxAerolinea.getSelectedItem().toString();
        	String ciudadOrigen = comboboxCiudadOrig.getSelectedItem().toString();
        	String ciudadDestino = comboboxCiudadDest.getSelectedItem().toString();
        	
        	int costoTuristaInt = Integer.parseInt(costoTurista);
        	int costoTuristaEjecutivoInt = Integer.parseInt(costoEjecutivo);
        	int costoEquipExtraInt = Integer.parseInt(costoEquipExtra);
        	try {
				controlVue.altaRutaVuelo(aerolinea, nombre, descripcion, horaFormat, costoTuristaInt, costoTuristaEjecutivoInt, costoEquipExtraInt, ciudadOrigen, ciudadDestino, categoriasSeleccionadas,"","","");
				JOptionPane.showMessageDialog(this,"Ruta de vuelo "+ nombre + " fue agregada correctamente.");
			} catch (InstanciaRepetida | InstanciaNoExiste | DatoInvalido e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Ruta de Vuelo", JOptionPane.ERROR_MESSAGE);
				return;
			}
        	categoriasSeleccionadas.clear();
        	resetFormulario();
        }
	}
	
	
	private boolean validacionDatos(String nombre, String descripcion, String hora, String minuto, String costoTurista, String costoEjecutivo, String costoEquipExtra) {
		
		String msg = "";
		if(commboBoxAerolinea.getSelectedIndex() == -1) msg += "Aerolinea, ";
		if(comboboxCiudadOrig.getSelectedIndex() == -1) msg += "Ciudad Origen, ";
		if(comboboxCiudadDest.getSelectedIndex() == -1) msg += "Ciudad Destino, ";
        if (nombre.isEmpty()) msg += "Nombre, ";
        if (descripcion.isEmpty()) msg += "Descripcion, ";
        if (costoTurista.isEmpty()) msg += "Costo turista, ";
        if (costoEjecutivo.isEmpty()) msg += "Costo ejecutivo, ";
        if (costoEquipExtra.isEmpty()) msg += "Costo equipaje extra, ";
        

        if (!msg.isEmpty()) {
            msg = "Los siguientes campos son obligatorios: " + msg;
            msg = msg.substring(0, msg.length() - 2); // Saca coma y espacio final
            JOptionPane.showMessageDialog(this, "Los campos " +  msg  + " son obligatorios.");
            return false;
        }
        
        String ciudadDestino = comboboxCiudadDest.getSelectedItem().toString();
        String ciudadOrigen = comboboxCiudadOrig.getSelectedItem().toString();
        if(ciudadDestino == ciudadOrigen) {
    		JOptionPane.showMessageDialog(this, "El origen y el destino deben ser diferentes.");
    		return false;
    	}
        
        else{
            return validNum(costoTurista, "Costo turista") && validNum(costoEjecutivo, "Costo ejecutivo") && validNum(costoEquipExtra, "Costo equipaje extra") && validHora(hora, minuto);
        }
    }
    private boolean validNum(String texto, String campo) {
		try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, campo + "debe ser un numero", "Error de Validacion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
	}
    private boolean validHora(String hora_s, String minuto_s) {
    	try {
            int hora = Integer.parseInt(hora_s);
            int minuto = Integer.parseInt(minuto_s);
            if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59 ) {
                JOptionPane.showMessageDialog(this, "Hora debe ser una hora valida");
                return false;
            } 
            LocalTime.of(hora, minuto);
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Fecha invalida.");
            return false;
        }
    }

	
	private void selectCategory() {
		if(comboboxCategorias.getSelectedIndex() == -1) {
    		JOptionPane.showMessageDialog(this, "Categoria no seleccionada");
    		return;
    	}
		categoriasSeleccionadas.add(comboboxCategorias.getSelectedItem().toString());
    	comboboxCategorias.setSelectedIndex(-1);
	}
	
	public void actualizarCaso() {
        reloadCombo(commboBoxAerolinea, cUsuario.listarAerolineas());
        reloadCombo(comboboxCiudadOrig, cVuelos.listarCiudades());
        reloadCombo(comboboxCiudadDest, cVuelos.listarCiudades());
        reloadCombo(comboboxCategorias, cVuelos.listarCategorias());
        resetFormulario();
        System.out.print("consulta usuario actualizado \n");
    }

	 private void reloadCombo(JComboBox<String> combo, Set<String> items) {
	 	combo.setSelectedItem("");
	    combo.removeAllItems();
	    for(String item:items) {
	        combo.addItem(item);
	    }
	    combo.setSelectedIndex(-1);
	 }
	 
	  private void resetFormulario() {
		  	commboBoxAerolinea.setSelectedIndex(-1);
		  	nombreRutaField.setText("");
	        descripcionField.setText("");
	        horaSpinner.setValue(0);
	        minutoSpinner.setValue(0);
	        costoTuristField.setText("");
	        costoEjecField.setText("");
	        costoEquipajeExtraField.setText("");
	        comboboxCiudadOrig.setSelectedIndex(-1);
	        comboboxCiudadDest.setSelectedIndex(-1);
	    }
}