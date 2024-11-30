package presentacion.paquetes;


import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.controladores.Fabrica;
import excepciones.DatoInvalido;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorPaquetes;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CrearPaqueteDeRutaDeVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorPaquetes controladorPaquetes = fabrica.getControladorPaquetes();
	
	private JTextField textField_nombre;
	private JTextField textField_descripcion;
	private JSpinner spinner_validez;
	private JSpinner spinner_fecha;
	private JSpinner spinner_descuento;

	/**
	 * Create the frame.
	 */
	public CrearPaqueteDeRutaDeVuelo() {
		setClosable(true);
		setTitle("Crear Paquete de Rutas de Vuelo");
		setBounds(100, 100, 302, 262);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		
		JLabel lblNewLabel_2 = new JLabel("Días de validez:");
		
		JLabel lblNewLabel_3 = new JLabel("Descuento:");
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de Alta:");
		
		textField_nombre = new JTextField();
		textField_nombre.setColumns(10);
		
		textField_descripcion = new JTextField();
		textField_descripcion.setColumns(10);
		
		spinner_validez = new JSpinner();

		SpinnerDateModel model = new SpinnerDateModel();
		spinner_fecha = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner_fecha, "dd/MM/yyyy");
        spinner_fecha.setEditor(editor);
        spinner_fecha.setValue(new Date());
		
		JButton btnNewButton = new JButton("Alta Paquete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				crearPaquete();
				limpiarFormulario();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		
		spinner_descuento = new JSpinner();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(spinner_descuento, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(spinner_validez, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(textField_nombre, 147, 147, Short.MAX_VALUE)
								.addComponent(textField_descripcion, 147, 147, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(spinner_fecha, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addGap(233))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(textField_descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(spinner_validez, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(spinner_descuento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(textField_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(spinner_fecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		

	}
	private void crearPaquete() {
		
		
		String nombre = textField_nombre.getText();
		String descripcion = textField_descripcion.getText();
		int descuento = (int) spinner_descuento.getValue();
		Date fecha = (Date) spinner_fecha.getValue();
		int validez = (int) spinner_validez.getValue();
		
		try {
			if (!validarStrings(nombre)) throw new DatoInvalido("Nombre obligatorio");
			if (!validarStrings(descripcion)) throw new DatoInvalido("Descripcion obligatoria");
			if (descuento<=0 || descuento>=100) throw new DatoInvalido("Descuento debe estar entre 1 y 99");
			
			controladorPaquetes.ingresarPaquete(nombre, descripcion, validez , descuento, fecha, "");
        	JOptionPane.showMessageDialog(this, "El paquete fue dado de alta con éxito");
        	this.setVisible(false);

		} catch (InstanciaRepetida e) {
			JOptionPane.showMessageDialog(this, "Ya existe "+ nombre +" en el sistema" );
		} catch (DatoInvalido e) {
			JOptionPane.showMessageDialog(this, "Algún dato es invalido" );
		}
		
		
	}
	private boolean validarStrings(String val) {
		if (val.isEmpty()||val.isBlank()) {
        	JOptionPane.showMessageDialog(this, "Todos los campos  son obligatorios.");
        	return false;
		}
		return true;
	}
	private void limpiarFormulario() {
		textField_nombre.setText("");
		textField_descripcion.setText("");
		spinner_validez.setValue(0);
		spinner_descuento.setValue(0);
		spinner_fecha.setValue(new Date());
		spinner_validez.setValue(0);
	}
}
