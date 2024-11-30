package presentacion.paquetes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;

@SuppressWarnings("serial")
public class ComprarPaquete extends JInternalFrame{
	private String [] paquetes;
	private JLabel paqueteLbl;
	private JComboBox<String> paqueteCbx;
	
	private String [] clientes;
	private JLabel clienteLbl;
	private JComboBox<String> clienteCbx;
	
	private JLabel fechaLbl;
	private JSpinner fechaSpn;
	
	private JButton btnAceptar;
    private JButton btnCancelar;
	
	public ComprarPaquete(){
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Comprar paquete");
        setBounds(10, 40, 380, 250);
        
      //grid configs
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
        
      //paquete
        paqueteLbl = new JLabel("Paquete:");
        paqueteLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_paqueteLbl = new GridBagConstraints();
        gbc_paqueteLbl.fill = GridBagConstraints.BOTH;
        gbc_paqueteLbl.insets = new Insets(0, 0, 5, 5);
        gbc_paqueteLbl.gridx = 0;
        gbc_paqueteLbl.gridy = 0;
        getContentPane().add(paqueteLbl, gbc_paqueteLbl);
        
        paquetes = new String[] {};
    	paqueteCbx = new JComboBox<String>(paquetes);
        GridBagConstraints gbc_paqueteTxf = new GridBagConstraints();
        gbc_paqueteTxf.gridwidth = 2;
        gbc_paqueteTxf.fill = GridBagConstraints.BOTH;
        gbc_paqueteTxf.insets = new Insets(0, 0, 5, 0);
        gbc_paqueteTxf.gridx = 1;
        gbc_paqueteTxf.gridy = 0;
        getContentPane().add(paqueteCbx, gbc_paqueteTxf);
        
      //cliente        
        clienteLbl = new JLabel("Cliente:");
        clienteLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_clienteLbl = new GridBagConstraints();
        gbc_clienteLbl.fill = GridBagConstraints.BOTH;
        gbc_clienteLbl.insets = new Insets(0, 0, 5, 5);
        gbc_clienteLbl.gridx = 0;
        gbc_clienteLbl.gridy = 1;
        getContentPane().add(clienteLbl, gbc_clienteLbl);
        
        clientes = new String[] {};
    	clienteCbx = new JComboBox<String>(clientes);
        GridBagConstraints gbc_clienteTxf = new GridBagConstraints();
        gbc_clienteTxf.gridwidth = 2;
        gbc_clienteTxf.fill = GridBagConstraints.BOTH;
        gbc_clienteTxf.insets = new Insets(0, 0, 5, 0);
        gbc_clienteTxf.gridx = 1;
        gbc_clienteTxf.gridy = 1;
        getContentPane().add(clienteCbx, gbc_clienteTxf);
        
      //fecha
        fechaLbl = new JLabel("Fecha de compra:");
        fechaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaLbl = new GridBagConstraints();
        gbc_fechaLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaLbl.insets = new Insets(0, 0, 5, 5);
        gbc_fechaLbl.gridx = 0;
        gbc_fechaLbl.gridy = 2;
        getContentPane().add(fechaLbl, gbc_fechaLbl);
        
        SpinnerDateModel model = new SpinnerDateModel();
        fechaSpn = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaSpn, "dd/MM/yyyy");
        fechaSpn.setEditor(editor);
        fechaSpn.setValue(new Date());
        GridBagConstraints gbc_fechaSpn = new GridBagConstraints();
        gbc_fechaSpn.gridwidth = 2;
        gbc_fechaSpn.fill = GridBagConstraints.BOTH;
        gbc_fechaSpn.insets = new Insets(0, 0, 5, 0);
        gbc_fechaSpn.gridx = 1;
        gbc_fechaSpn.gridy = 2;
        getContentPane().add(fechaSpn, gbc_fechaSpn);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try {            		
            		aceptar();
            	} catch (InstanciaNoExiste e) {
            		JOptionPane.showMessageDialog(ComprarPaquete.this, e.getMessage(), "Comprar paquete",
                            JOptionPane.ERROR_MESSAGE);
            	} catch (InstanciaRepetida e) {
            		JOptionPane.showMessageDialog(ComprarPaquete.this, e.getMessage(), "Comprar paquete",
                            JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.gridwidth = 1;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.insets = new Insets(0, 10, 0, 0);

        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 3;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exception) {
            	cerrar();
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridwidth = 1;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.insets = new Insets(0, 10, 0, 0);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 3;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	public void cargarDatos() {
		clientes = Fabrica.getInstancia().getControladorUsuario().listarClientes().toArray(clientes);
		paquetes = Fabrica.getInstancia().getControladorPaquetes().listarPaquetesNombres().toArray(paquetes);
		paqueteCbx.removeAllItems();
		for(String paquete:paquetes)
			paqueteCbx.addItem(paquete);
		clienteCbx.removeAllItems();
		for(String cliente:clientes)
			clienteCbx.addItem(cliente);
		paqueteCbx.setSelectedIndex(-1);
		clienteCbx.setSelectedIndex(-1);
	}
	
	public void cerrar() {
		cargarDatos();
		setVisible(false);
	}
	
	private void aceptar() throws InstanciaRepetida, InstanciaNoExiste {
		String nombrePaquete = (String) paqueteCbx.getSelectedItem();
		nombrePaquete = nombrePaquete == null ? "":nombrePaquete;
		String nombreCliente = (String) clienteCbx.getSelectedItem();
		nombreCliente = nombreCliente == null ? "":nombreCliente;
		Date fecha = (Date) fechaSpn.getValue();
		Fabrica.getInstancia().getControladorPaquetes().altaCompraPaquete(nombrePaquete, nombreCliente, fecha);
		
		JOptionPane.showMessageDialog(this, "Compra de paquete fue dada de alta con éxito");
		cerrar();
	}
}
