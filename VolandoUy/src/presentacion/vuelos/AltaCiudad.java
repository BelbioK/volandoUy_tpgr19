package presentacion.vuelos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import excepciones.DatoInvalido;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorVuelos;

/*

Aerolinea funciona como el nomrbe del aeropuerto (me da pereza cambair el nombre de la variable)

*/

public class AltaCiudad extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel aerolineaLbl;
	private JTextField aerolineaCbx;
	
	private JLabel paisLbl;
	private JTextField paisTxf;
	
	private JLabel ciudadLbl;
	private JTextField ciudadTxf;

	private JLabel descripcionLbl;
	private JTextField descripcionTxf;
	
	private JLabel webLbl;
	private JTextField webTxf;
	
	private JLabel fechaLbl;
	private JSpinner fechaSpn;
	
	private JButton btnAceptar;
    private JButton btnCancelar;
    
    
    Fabrica fab = Fabrica.getInstancia();
    IControladorVuelos contrVuelo = fab.getControladorVuelos();
    
    public AltaCiudad() {
    	//Basic config
    	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de ciudad");
        setBounds(10, 40, 380, 250);
        //grid configs
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
        
        //Aerolinea de ciudad
        aerolineaLbl = new JLabel("Aeropuerto:");
        aerolineaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_aerolineaLbl = new GridBagConstraints();
        gbc_aerolineaLbl.fill = GridBagConstraints.BOTH;
        gbc_aerolineaLbl.insets = new Insets(5, 0, 5, 5);
        gbc_aerolineaLbl.gridx = 0;
        gbc_aerolineaLbl.gridy = 0;
        getContentPane().add(aerolineaLbl, gbc_aerolineaLbl);

        aerolineaCbx = new JTextField();
        GridBagConstraints gbc_aerolineaTxf = new GridBagConstraints();
        gbc_aerolineaTxf.gridwidth = 2;
        gbc_aerolineaTxf.fill = GridBagConstraints.BOTH;
        gbc_aerolineaTxf.insets = new Insets(5, 0, 5, 0);
        gbc_aerolineaTxf.gridx = 1;
        gbc_aerolineaTxf.gridy = 0;
        getContentPane().add(aerolineaCbx, gbc_aerolineaTxf);
//        aerolineaCbx.setColumns(10);
        
        //Pais de ciudad
        paisLbl = new JLabel("Pais:");
        paisLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_paisLbl = new GridBagConstraints();
        gbc_paisLbl.fill = GridBagConstraints.BOTH;
        gbc_paisLbl.insets = new Insets(0, 0, 5, 5);
        gbc_paisLbl.gridx = 0;
        gbc_paisLbl.gridy = 1;
        getContentPane().add(paisLbl, gbc_paisLbl);

        paisTxf = new JTextField();
        GridBagConstraints gbc_paisTxf = new GridBagConstraints();
        gbc_paisTxf.gridwidth = 2;
        gbc_paisTxf.fill = GridBagConstraints.BOTH;
        gbc_paisTxf.insets = new Insets(0, 0, 5, 0);
        gbc_paisTxf.gridx = 1;
        gbc_paisTxf.gridy = 1;
        getContentPane().add(paisTxf, gbc_paisTxf);
        paisTxf.setColumns(10);
        
        //Ciudad de ciudad
        ciudadLbl = new JLabel("Ciudad:");
        ciudadLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_ciudadLbl = new GridBagConstraints();
        gbc_ciudadLbl.fill = GridBagConstraints.BOTH;
        gbc_ciudadLbl.insets = new Insets(0, 0, 5, 5);
        gbc_ciudadLbl.gridx = 0;
        gbc_ciudadLbl.gridy = 2;
        getContentPane().add(ciudadLbl, gbc_ciudadLbl);

        ciudadTxf = new JTextField();
        GridBagConstraints gbc_ciudadTxf = new GridBagConstraints();
        gbc_ciudadTxf.gridwidth = 2;
        gbc_ciudadTxf.fill = GridBagConstraints.BOTH;
        gbc_ciudadTxf.insets = new Insets(0, 0, 5, 0);
        gbc_ciudadTxf.gridx = 1;
        gbc_ciudadTxf.gridy = 2;
        getContentPane().add(ciudadTxf, gbc_ciudadTxf);
        ciudadTxf.setColumns(10);
        
        //Descripción
        descripcionLbl = new JLabel("Descripcion:");
        descripcionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_descripcionLbl = new GridBagConstraints();
        gbc_descripcionLbl.fill = GridBagConstraints.BOTH;
        gbc_descripcionLbl.insets = new Insets(0, 0, 5, 5);
        gbc_descripcionLbl.gridx = 0;
        gbc_descripcionLbl.gridy = 3;
        getContentPane().add(descripcionLbl, gbc_descripcionLbl);

        descripcionTxf = new JTextField();
        GridBagConstraints gbc_descripcionTxf = new GridBagConstraints();
        gbc_descripcionTxf.gridwidth = 2;
        gbc_descripcionTxf.fill = GridBagConstraints.BOTH;
        gbc_descripcionTxf.insets = new Insets(0, 0, 5, 0);
        gbc_descripcionTxf.gridx = 1;
        gbc_descripcionTxf.gridy = 3;
        getContentPane().add(descripcionTxf, gbc_descripcionTxf);
        descripcionTxf.setColumns(10);
        
        //web
        webLbl = new JLabel("Página web:");
        webLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_webLbl = new GridBagConstraints();
        gbc_webLbl.fill = GridBagConstraints.BOTH;
        gbc_webLbl.insets = new Insets(0, 0, 5, 5);
        gbc_webLbl.gridx = 0;
        gbc_webLbl.gridy = 4;
        getContentPane().add(webLbl, gbc_webLbl);

        webTxf = new JTextField();
        GridBagConstraints gbc_webTxf = new GridBagConstraints();
        gbc_webTxf.gridwidth = 2;
        gbc_webTxf.fill = GridBagConstraints.BOTH;
        gbc_webTxf.insets = new Insets(0, 0, 5, 0);
        gbc_webTxf.gridx = 1;
        gbc_webTxf.gridy = 4;
        getContentPane().add(webTxf, gbc_webTxf);
        webTxf.setColumns(10);
        
        //fecha
        fechaLbl = new JLabel("Fecha de alta:");
        fechaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaLbl = new GridBagConstraints();
        gbc_fechaLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaLbl.insets = new Insets(0, 0, 5, 5);
        gbc_fechaLbl.gridx = 0;
        gbc_fechaLbl.gridy = 5;
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
        gbc_fechaSpn.gridy = 5;
        getContentPane().add(fechaSpn, gbc_fechaSpn);
        webTxf.setColumns(10);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try {            		
            		aceptar();
            	} catch (InstanciaRepetida e) {
            		JOptionPane.showMessageDialog(AltaCiudad.this, "Nombre repetido", "Ciudad ya agregada",
                            JOptionPane.ERROR_MESSAGE);
            	} catch (DatoInvalido d) {
            		JOptionPane.showMessageDialog(AltaCiudad.this, "Datos invalidos", d.toString(),
                            JOptionPane.ERROR_MESSAGE);
            	}
            }
        });

        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 6;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exception) {
            	cancelar();
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 6;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }
    
    private void aceptar() throws InstanciaRepetida, DatoInvalido {    	
    	String nombreAeropuerto = aerolineaCbx.getText();
    	String nombreCiudad = ciudadTxf.getText();
    	String nombrePais = paisTxf.getText();
    	String descripcion = descripcionTxf.getText();
    	String web = webTxf.getText();
    	Date fecha = (Date) fechaSpn.getValue();
    	contrVuelo.altaCiudad(nombreCiudad, nombrePais, nombreAeropuerto, descripcion, web, fecha);
    	eliminarTodo();
    }
    
    private void eliminarTodo() {
    	aerolineaCbx.setText("");
    	ciudadTxf.setText("");
    	paisTxf.setText("");
    	descripcionTxf.setText("");
    	webTxf.setText("");
    	fechaSpn.setValue(new Date());
    }
    
    private void cancelar() {
    	eliminarTodo();
    	setVisible(false);
    }
}
