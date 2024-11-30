package presentacion.paquetes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPaqueteRuta;
import excepciones.InstanciaNoExiste;

@SuppressWarnings("serial")
public class ConsultaPaquete extends JInternalFrame {
	private String [] paquetes;
	private JLabel paqueteLbl;
	private JComboBox<String> paqueteCbx;
	
	private JLabel descripcionLbl;
	private JTextField descripcionTxf;
	
	private JLabel validezLbl;
	private JTextField validezTxf;
	
	private JLabel descuentoLbl;
	private JTextField descuentoTxf;
	
	private JLabel fechaAltaLbl;
	private JSpinner fechaAltaTxf;
	
	private JLabel rutasLbl;
	private JList<String> listaRutas;
	private JScrollPane panelRutas;
	DefaultListModel<String> modeloRutas = new DefaultListModel<>();
	
	public ConsultaPaquete() {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta paquete");
        setBounds(10, 40, 650, 250);
        
      //grid configs
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0,0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,0.0,Double.MIN_VALUE };
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
        
        paqueteCbx.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent exception) {
    			cargarDatosPaquete();
    		}
    	});
        
      //descripcion
        descripcionLbl = new JLabel("Descripcion:");
        descripcionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_descripcionLbl = new GridBagConstraints();
        gbc_descripcionLbl.fill = GridBagConstraints.BOTH;
        gbc_descripcionLbl.insets = new Insets(0, 0, 5, 5);
        gbc_descripcionLbl.gridx = 0;
        gbc_descripcionLbl.gridy = 1;
        getContentPane().add(descripcionLbl, gbc_descripcionLbl);
        
    	descripcionTxf = new JTextField();
    	descripcionTxf.setEnabled(false);
        GridBagConstraints gbc_descripcionTxf = new GridBagConstraints();
        gbc_descripcionTxf.gridwidth = 2;
        gbc_descripcionTxf.fill = GridBagConstraints.BOTH;
        gbc_descripcionTxf.insets = new Insets(0, 0, 5, 0);
        gbc_descripcionTxf.gridx = 1;
        gbc_descripcionTxf.gridy = 1;
        getContentPane().add(descripcionTxf, gbc_descripcionTxf);
        
        //validez
        validezLbl = new JLabel("Validez:");
        validezLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_validezLbl = new GridBagConstraints();
        gbc_validezLbl.fill = GridBagConstraints.BOTH;
        gbc_validezLbl.insets = new Insets(0, 0, 5, 5);
        gbc_validezLbl.gridx = 0;
        gbc_validezLbl.gridy = 2;
        getContentPane().add(validezLbl, gbc_validezLbl);
        
    	validezTxf = new JTextField();
    	validezTxf.setEnabled(false);
        GridBagConstraints gbc_validezTxf = new GridBagConstraints();
        gbc_validezTxf.gridwidth = 2;
        gbc_validezTxf.fill = GridBagConstraints.BOTH;
        gbc_validezTxf.insets = new Insets(0, 0, 5, 0);
        gbc_validezTxf.gridx = 1;
        gbc_validezTxf.gridy = 2;
        getContentPane().add(validezTxf, gbc_validezTxf);
        
        //descuento
        descuentoLbl = new JLabel("Descuento:");
        descuentoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_descuentoLbl = new GridBagConstraints();
        gbc_descuentoLbl.fill = GridBagConstraints.BOTH;
        gbc_descuentoLbl.insets = new Insets(0, 0, 5, 5);
        gbc_descuentoLbl.gridx = 0;
        gbc_descuentoLbl.gridy = 3;
        getContentPane().add(descuentoLbl, gbc_descuentoLbl);
        
    	descuentoTxf = new JTextField();
    	descuentoTxf.setEnabled(false);
        GridBagConstraints gbc_descuentoTxf = new GridBagConstraints();
        gbc_descuentoTxf.gridwidth = 2;
        gbc_descuentoTxf.fill = GridBagConstraints.BOTH;
        gbc_descuentoTxf.insets = new Insets(0, 0, 5, 0);
        gbc_descuentoTxf.gridx = 1;
        gbc_descuentoTxf.gridy = 3;
        getContentPane().add(descuentoTxf, gbc_descuentoTxf);
        
      //descuento
        fechaAltaLbl = new JLabel("Fecha alta:");
        fechaAltaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaAltaLbl = new GridBagConstraints();
        gbc_fechaAltaLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaAltaLbl.insets = new Insets(0, 0, 5, 5);
        gbc_fechaAltaLbl.gridx = 0;
        gbc_fechaAltaLbl.gridy = 4;
        getContentPane().add(fechaAltaLbl, gbc_fechaAltaLbl);
        
        SpinnerDateModel model = new SpinnerDateModel();
        fechaAltaTxf = new JSpinner(model);
        fechaAltaTxf.setEnabled(false);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaAltaTxf, "dd/MM/yyyy");
        fechaAltaTxf.setEditor(editor);
        fechaAltaTxf.setValue(new Date());
        GridBagConstraints gbc_fechaAltaTxf = new GridBagConstraints();
        gbc_fechaAltaTxf.gridwidth = 2;
        gbc_fechaAltaTxf.fill = GridBagConstraints.BOTH;
        gbc_fechaAltaTxf.insets = new Insets(0, 0, 5, 0);
        gbc_fechaAltaTxf.gridx = 1;
        gbc_fechaAltaTxf.gridy = 4;
        getContentPane().add(fechaAltaTxf, gbc_fechaAltaTxf);
        
      //rutas
        rutasLbl = new JLabel("Rutas:");
        rutasLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	GridBagConstraints gbc_rutasLbl = new GridBagConstraints();
    	gbc_rutasLbl.fill = GridBagConstraints.BOTH;
    	gbc_rutasLbl.insets = new Insets(0, 10, 5, 5);
    	gbc_rutasLbl.gridx = 3;
    	gbc_rutasLbl.gridy = 0;
        getContentPane().add(rutasLbl, gbc_rutasLbl);
        
        listaRutas = new JList<String>(modeloRutas);
        panelRutas = new JScrollPane(listaRutas);
        GridBagConstraints gbc_panelRutas = new GridBagConstraints();
        gbc_panelRutas.gridwidth = 4;
        gbc_panelRutas.gridheight = 4;
        gbc_panelRutas.fill = GridBagConstraints.BOTH;
        gbc_panelRutas.insets = new Insets(0, 10, 5, 0);
        gbc_panelRutas.gridx = 3;
        gbc_panelRutas.gridy = 1;
        getContentPane().add(panelRutas, gbc_panelRutas);
        
	}
	public void cargarDatos() {
		paquetes = Fabrica.getInstancia().getControladorPaquetes().listarPaquetesNombres().toArray(new String[]{});
		paqueteCbx.removeAllItems();
		for(String paquete:paquetes)
			paqueteCbx.addItem(paquete);
	}
	public void setPaquete(String paquete) {
		for(int i = 0;i<paqueteCbx.getItemCount();i++) 		
			if(((String)paqueteCbx.getItemAt(i)).equals(paquete)) {			
				paqueteCbx.setSelectedItem(i);
				break;
			}
	}
	
	private void cargarDatosPaquete() {
		String paquete = (String) paqueteCbx.getSelectedItem();
		if(paquete == null)
			return;
		
		try {
			DTPaquete dtp = Fabrica.getInstancia().getControladorPaquetes().infoPaquete(paquete);
			descripcionTxf.setText(dtp.getDescripcion());
			validezTxf.setText(dtp.getPeriodoValidez()+"");
			descuentoTxf.setText(dtp.getDescuento()+"");
			fechaAltaTxf.setValue(dtp.getFechaAlta());
			listaRutas.removeAll();
			modeloRutas.removeAllElements();
			for(DTPaqueteRuta pr:dtp.getRutas()) 
				modeloRutas.addElement(pr.toString());
		} catch (InstanciaNoExiste e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar paquete paquete",
                    JOptionPane.ERROR_MESSAGE);
		} 
	}
}
