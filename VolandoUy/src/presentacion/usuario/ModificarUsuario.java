package presentacion.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.controladores.Fabrica;
import logica.datatypes.DTUsuario;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;

import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;

public class ModificarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboBox_modificar;
	private Fabrica fab = Fabrica.getInstancia();
	private IControladorUsuarios contUsuarios = fab.getControladorUsuario();
	
	private AltaUsuario altaUsuario;
	private JDesktopPane desktopPane = new JDesktopPane();



	/**
	 * Create the frame.
	 */
	public ModificarUsuario() {
		setClosable(true);
		setTitle("Modificar Usuario");
		setBounds(100, 100, 321, 163);
        setResizable(true);
		setContentPane(desktopPane);

		
		JLabel lblNewLabel = new JLabel("Selecciona el usuario que desea modificar:");
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				if(altaUsuario != null)
					altaUsuario.setVisible(false);
				setVisible(false);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		ModificarUsuario yoo = this;
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				if(comboBox_modificar.getSelectedIndex() != -1) {
					setBounds(50,10,500,600);
					altaUsuario = new AltaUsuario();
					altaUsuario.setBounds(0,0,450,550);
					altaUsuario.changeFormSetup(1);
					String sel = (String) comboBox_modificar.getSelectedItem();
					DTUsuario mostrar;
					try {
						mostrar = contUsuarios.mostrarInfoUsuarios(sel);
						altaUsuario.cargarDatosAModificarCliente(mostrar,yoo);
						desktopPane.add(altaUsuario);
						altaUsuario.setVisible(true);
					} catch (InstanciaNoExiste | DatoInvalido e1) {
						JOptionPane.showMessageDialog(ModificarUsuario.this, e1.getMessage(), "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					
				}
			}
		});
		comboBox_modificar = new JComboBox<String>();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(131, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(comboBox_modificar, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_modificar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	public void actualizarCaso() {
		//aerolinea
		Set<String> usuarios = contUsuarios.listarUsuarios();
		if (!usuarios.isEmpty()) {
			reloadCombo(comboBox_modificar,usuarios);
			comboBox_modificar.setSelectedIndex(-1);
			System.out.print("consulta vuelo actualizado \n");
		}
	}


	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
	    combo.removeAllItems();
	    for(String item:items)
	    	combo.addItem(item);
	}
}
