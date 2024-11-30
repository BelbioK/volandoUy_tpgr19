package presentacion.vuelos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import logica.datatypes.Asiento;
import logica.datatypes.DTCliente;
import logica.datatypes.DTPasajero;
import logica.datatypes.DTVuelo;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;

public class ReservaVuelo extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String [] aerolineas;
	private JLabel aerolineaLbl;
	private JComboBox<String> aerolineaCbx;
	
	private JLabel rutaLbl;
	private JComboBox<String> rutaCbx;
	
	private JLabel vueloLbl;
	private JComboBox<String> vueloCbx;
	
	private String pasajeCliente=null;
	private String [] clientes;
	private JLabel clienteLbl;
	private JComboBox<String> clienteCbx;
	
	private JLabel asientoLbl;
	private JComboBox<String> asientoCbx;
	
	private JLabel equipajeLbl;
	private JSpinner equipajeSpn;
	
	private JLabel pasajerosLbl;
	private JList<String> listaPasajeros;
	private JScrollPane panelPasajeros;
	DefaultListModel<String> listaNombres = new DefaultListModel<>();
	
//	private JLabel fechaLbl;
//	private JDatePicker fechaDpk;
	List<DTPasajero> pasajeros = new ArrayList<DTPasajero>();
	private JLabel nombreLbl;
	private JTextField nombreTxf;
	
	private JLabel apellidoLbl;
	private JTextField apellidoTxf;
	
	private JButton btnAgregarPasajero;
	private JButton btnAceptar;
    private JButton btnCancelar;
    
    Fabrica fab = Fabrica.getInstancia();
    IControladorUsuarios contrUsu = fab.getControladorUsuario();
    IControladorVuelos contrVue = fab.getControladorVuelos();
    
	
	public ReservaVuelo() {
		//Basic config
    	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Reserva Vuelo");
        setBounds(10, 40, 650, 300);
        //grid configs
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 70, 120, 70, 120,70, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30,30,30,30,30,30,30};
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
        
      //Cliente
        clienteLbl = new JLabel("Cliente:");
        clienteLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_clienteLbl = new GridBagConstraints();
        gbc_clienteLbl.fill = GridBagConstraints.BOTH;
        gbc_clienteLbl.insets = new Insets(0, 0, 5, 5);
        gbc_clienteLbl.gridx = 0;
        gbc_clienteLbl.gridy = 0;
        getContentPane().add(clienteLbl, gbc_clienteLbl);
        
        clientes = new String[] {};
    	clienteCbx = new JComboBox<String>(clientes);
        GridBagConstraints gbc_clienteTxf = new GridBagConstraints();
        gbc_clienteTxf.gridwidth = 1;
        gbc_clienteTxf.fill = GridBagConstraints.BOTH;
        gbc_clienteTxf.insets = new Insets(0, 0, 5, 0);
        gbc_clienteTxf.gridx = 1;
        gbc_clienteTxf.gridy = 0;
        getContentPane().add(clienteCbx, gbc_clienteTxf);
        clienteCbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
            	String nickCliente = (String) clienteCbx.getSelectedItem();
            	try {
            		if(nickCliente == null || nickCliente.isBlank() || nickCliente.isEmpty()) {
            			if(pasajeCliente != null) {
    	            		pasajeros.remove(0);
    	            		listaNombres.remove(0);
    	            		pasajeCliente = null;
    					}
                		return;
            		}
					DTCliente usuario = (DTCliente) Fabrica.getInstancia().getControladorUsuario().mostrarInfoUsuarios(nickCliente);
					if(pasajeCliente != null) {
	            		pasajeros.remove(0);
	            		listaNombres.remove(0);
					}
					pasajeCliente = nickCliente;
					pasajeros.add(0, new DTPasajero(usuario.getNombre(),usuario.getApellido()));
			    	listaNombres.add(0, usuario.getNombre()+" "+usuario.getApellido());
				} catch (InstanciaNoExiste | DatoInvalido e1) {
					JOptionPane.showMessageDialog(ReservaVuelo.this, "Cliente no encontrado: "+nickCliente, "Reserva vuelo",
                            JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
      //Aerolinea
        aerolineaLbl = new JLabel("Aerolinea:");
        aerolineaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_aerolineaLbl = new GridBagConstraints();
        gbc_aerolineaLbl.fill = GridBagConstraints.BOTH;
        gbc_aerolineaLbl.insets = new Insets(0, 0, 5, 5);
        gbc_aerolineaLbl.gridx = 0;
        gbc_aerolineaLbl.gridy = 1;
        getContentPane().add(aerolineaLbl, gbc_aerolineaLbl);

    	aerolineas = new String[] {};
        aerolineaCbx = new JComboBox<String>(aerolineas);
        GridBagConstraints gbc_aerolineaTxf = new GridBagConstraints();
        gbc_aerolineaTxf.gridwidth = 1;
        gbc_aerolineaTxf.fill = GridBagConstraints.BOTH;
        gbc_aerolineaTxf.insets = new Insets(0, 0, 5, 0);
        gbc_aerolineaTxf.gridx = 1;
        gbc_aerolineaTxf.gridy = 1;
        getContentPane().add(aerolineaCbx, gbc_aerolineaTxf);
        aerolineaCbx.setSelectedIndex(-1);

        aerolineaCbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
                String selectedAerolinea = (String) aerolineaCbx.getSelectedItem();
                if(selectedAerolinea == null)
                	return;
                Set<String> nombresRutas;
				try {
					nombresRutas = contrVue.listarRutasDeVuelo(selectedAerolinea);
					reloadCombo(rutaCbx, nombresRutas);
	                rutaCbx.setSelectedIndex(-1);
	                new HashMap<String,DTVuelo>();
	                reloadCombo(vueloCbx,new String[]{});
	                vueloCbx.setSelectedIndex(-1);
				} catch (DatoInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                /*reloadCombo(rutaCbx, nombresRutas);
                rutaCbx.setSelectedIndex(-1);
                new HashMap<String,DTVuelo>();
                reloadCombo(vueloCbx,new String[]{});
                vueloCbx.setSelectedIndex(-1);*/
            }
        });
        
      //ruta
        rutaLbl = new JLabel("Ruta de vuelo:");
        rutaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	rutaCbx = new JComboBox<String>();
    	GridBagConstraints gbc_rutaLbl = new GridBagConstraints();
        gbc_rutaLbl.fill = GridBagConstraints.BOTH;
        gbc_rutaLbl.insets = new Insets(0, 0, 5, 5);
        gbc_rutaLbl.gridx = 0;
        gbc_rutaLbl.gridy = 2;
        GridBagConstraints gbc_rutaTxf = new GridBagConstraints();
        gbc_rutaTxf.gridwidth = 1;
        gbc_rutaTxf.fill = GridBagConstraints.BOTH;
        gbc_rutaTxf.insets = new Insets(0, 0, 5, 0);
        gbc_rutaTxf.gridx = 1;
        gbc_rutaTxf.gridy = 2;
        getContentPane().add(rutaLbl, gbc_rutaLbl);
        getContentPane().add(rutaCbx, gbc_rutaTxf);
        rutaCbx.setSelectedIndex(-1);

    	rutaCbx.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent exception) {
    			if (rutaCbx.getSelectedItem() != null) {
    				try {
						reloadCombo(vueloCbx,contrVue.listarVuelosDeRuta(rutaCbx.getSelectedItem().toString()));
					} catch (InstanciaNoExiste e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
    			}
                vueloCbx.setSelectedIndex(-1);
            }    
    	});
        
      //vuelo
        vueloLbl = new JLabel("Vuelo:");
        vueloLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	vueloCbx = new JComboBox<String>();
    	GridBagConstraints gbc_vueloLbl = new GridBagConstraints();
        gbc_vueloLbl.fill = GridBagConstraints.BOTH;
        gbc_vueloLbl.insets = new Insets(0, 0, 5, 5);
        gbc_vueloLbl.gridx = 0;
        gbc_vueloLbl.gridy = 3;

        GridBagConstraints gbc_vueloTxf = new GridBagConstraints();
        gbc_vueloTxf.gridwidth = 1;
        gbc_vueloTxf.fill = GridBagConstraints.BOTH;
        gbc_vueloTxf.insets = new Insets(0, 0, 5, 0);
        gbc_vueloTxf.gridx = 1;
        gbc_vueloTxf.gridy = 3;
        getContentPane().add(vueloLbl, gbc_vueloLbl);
        getContentPane().add(vueloCbx, gbc_vueloTxf);
    	vueloCbx.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent exception) {
    			
    		}
    	});
    	
    	 //asiento
    	asientoLbl = new JLabel("Asiento:");
        asientoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        asientoCbx = new JComboBox<String>(new String[] {"Turista","Ejecutivo"});
    	GridBagConstraints gbc_asientoLbl = new GridBagConstraints();
        gbc_asientoLbl.fill = GridBagConstraints.BOTH;
        gbc_asientoLbl.insets = new Insets(0, 0, 5, 5);
        gbc_asientoLbl.gridx = 0;
        gbc_asientoLbl.gridy = 4;

        GridBagConstraints gbc_asientoTxf = new GridBagConstraints();
        gbc_asientoTxf.gridwidth = 1;
        gbc_asientoTxf.fill = GridBagConstraints.BOTH;
        gbc_asientoTxf.insets = new Insets(0, 0, 5, 0);
        gbc_asientoTxf.gridx = 1;
        gbc_asientoTxf.gridy = 4;
        getContentPane().add(asientoLbl, gbc_asientoLbl);
        getContentPane().add(asientoCbx, gbc_asientoTxf);
        
      //equipaje
    	equipajeLbl = new JLabel("Equipaje extra:");
    	equipajeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	equipajeSpn = new JSpinner();
    	GridBagConstraints gbc_equipajeLbl = new GridBagConstraints();
        gbc_equipajeLbl.fill = GridBagConstraints.BOTH;
        gbc_equipajeLbl.insets = new Insets(0, 0, 5, 5);
        gbc_equipajeLbl.gridx = 0;
        gbc_equipajeLbl.gridy = 5;

        GridBagConstraints gbc_equipajeTxf = new GridBagConstraints();
        gbc_equipajeTxf.gridwidth = 1;
        gbc_equipajeTxf.fill = GridBagConstraints.BOTH;
        gbc_equipajeTxf.insets = new Insets(0, 0, 5, 0);
        gbc_equipajeTxf.gridx = 1;
        gbc_equipajeTxf.gridy = 5;
        getContentPane().add(equipajeLbl, gbc_equipajeLbl);
        getContentPane().add(equipajeSpn, gbc_equipajeTxf);
        
      //nombre
    	nombreLbl = new JLabel("Nombre:");
    	nombreLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	nombreTxf = new JTextField();
    	GridBagConstraints gbc_nombreLbl = new GridBagConstraints();
        gbc_nombreLbl.fill = GridBagConstraints.BOTH;
        gbc_nombreLbl.insets = new Insets(0, 0, 5, 5);
        gbc_nombreLbl.gridx = 2;
        gbc_nombreLbl.gridy = 0;

        GridBagConstraints gbc_nombreTxf = new GridBagConstraints();
        gbc_nombreTxf.gridwidth = 1;
        gbc_nombreTxf.fill = GridBagConstraints.BOTH;
        gbc_nombreTxf.insets = new Insets(0, 0, 5, 0);
        gbc_nombreTxf.gridx = 3;
        gbc_nombreTxf.gridy = 0;
        getContentPane().add(nombreLbl, gbc_nombreLbl);
        getContentPane().add(nombreTxf, gbc_nombreTxf);
        
      //apellido
        apellidoLbl = new JLabel("Apellido:");
        apellidoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	apellidoTxf = new JTextField();
    	GridBagConstraints gbc_apellidoLbl = new GridBagConstraints();
        gbc_apellidoLbl.fill = GridBagConstraints.BOTH;
        gbc_apellidoLbl.insets = new Insets(0, 0, 5, 5);
        gbc_apellidoLbl.gridx = 4;
        gbc_apellidoLbl.gridy = 0;

        GridBagConstraints gbc_apellidoTxf = new GridBagConstraints();
        gbc_apellidoTxf.gridwidth = 1;
        gbc_apellidoTxf.fill = GridBagConstraints.BOTH;
        gbc_apellidoTxf.insets = new Insets(0, 0, 5, 0);
        gbc_apellidoTxf.gridx = 5;
        gbc_apellidoTxf.gridy = 0;
        getContentPane().add(apellidoLbl, gbc_apellidoLbl);
        getContentPane().add(apellidoTxf, gbc_apellidoTxf);
        
        //pasajeros
        pasajerosLbl = new JLabel("Pasajeros:");
        pasajerosLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    	GridBagConstraints gbc_pasajerosLbl = new GridBagConstraints();
        gbc_pasajerosLbl.fill = GridBagConstraints.BOTH;
        gbc_pasajerosLbl.insets = new Insets(0, 10, 5, 5);
        gbc_pasajerosLbl.gridx = 2;
        gbc_pasajerosLbl.gridy = 2;
        getContentPane().add(pasajerosLbl, gbc_pasajerosLbl);
        
        listaPasajeros = new JList<String>(listaNombres);
        panelPasajeros = new JScrollPane(listaPasajeros);
        GridBagConstraints gbc_panelPasajeros = new GridBagConstraints();
        gbc_panelPasajeros.gridwidth = 4;
        gbc_panelPasajeros.gridheight = 3;
        gbc_panelPasajeros.fill = GridBagConstraints.BOTH;
        gbc_panelPasajeros.insets = new Insets(0, 10, 5, 0);
        gbc_panelPasajeros.gridx = 2;
        gbc_panelPasajeros.gridy = 3;
        getContentPane().add(panelPasajeros, gbc_panelPasajeros);
        
        btnAgregarPasajero = new JButton("Agregar pasajero");
        btnAgregarPasajero .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exception) {
            	agregarPasaje();
            }
        });
        GridBagConstraints gbc_btnAgregarPasajero = new GridBagConstraints();
        gbc_btnAgregarPasajero.gridwidth = 4;
        gbc_btnAgregarPasajero.fill = GridBagConstraints.BOTH;
        gbc_btnAgregarPasajero.insets = new Insets(0, 10, 0, 0);
        gbc_btnAgregarPasajero.gridx = 2;
        gbc_btnAgregarPasajero.gridy = 1;
        getContentPane().add(btnAgregarPasajero, gbc_btnAgregarPasajero);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
        		try {
					aceptar();
				} catch (InstanciaNoExiste | DatoInvalido | InstanciaRepetida e) {
					JOptionPane.showMessageDialog(ReservaVuelo.this, e.getMessage(), "Reserva vuelo",
                            JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        });
        
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.gridwidth = 2;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.insets = new Insets(0, 10, 0, 0);

        gbc_btnAceptar.gridx = 2;
        gbc_btnAceptar.gridy = 7;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exception) {
            	cancelar();
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.insets = new Insets(0, 10, 0, 0);
        gbc_btnCancelar.gridx = 4;
        gbc_btnCancelar.gridy = 7;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
        combo.removeAllItems();
        for(String item:items)
        	combo.addItem(item);
	}

	private void reloadCombo(JComboBox<String> combo, String[] items) {
		combo.setSelectedItem("");
        combo.removeAllItems();
        for(String item:items)
        	combo.addItem(item);
	}
	
    private void aceptar() throws InstanciaNoExiste, DatoInvalido, InstanciaRepetida {
        if (vueloCbx.getSelectedItem() == null && pasajeros.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "Datos faltantes", "agregar datos faltantes",
                    JOptionPane.ERROR_MESSAGE);
        } else {
        	Calendar calendar = Calendar.getInstance();
        	Date fechaActual = calendar.getTime();
        	IControladorVuelos contrVuelo = fab.getControladorVuelos();
        	String asientoSel = asientoCbx.getSelectedItem().toString();
        	Asiento asientoFin1;
        	if (asientoSel.equals("Ejecutivo")) {
        		asientoFin1 = Asiento.Ejecutivo;
        	} else {
        		asientoFin1 = Asiento.Turista;
        	}
        	Set<DTPasajero> res = new HashSet<>();
        	for(DTPasajero entry : pasajeros) {
        		res.add(entry);
        	}
        	contrVuelo.altaReserva(vueloCbx.getSelectedItem().toString(), 
        			clienteCbx.getSelectedItem().toString(), "",
        			asientoFin1,(int) equipajeSpn.getValue(),res, 
        			fechaActual);
        	borrarNombres();
        	pasajeros.clear();
        	listaPasajeros.removeAll();
        	listaNombres.removeAllElements();
        	JOptionPane.showMessageDialog(this, "La reserva se ha registrado con éxito", "Reserva de Vuelo",
                    JOptionPane.INFORMATION_MESSAGE);
        	cancelar();
        }
    }
    private void cancelar() {
    	//limpiarFormulario();
        setVisible(false);
        pasajeros.clear();
    	listaPasajeros.removeAll();
    	listaNombres.removeAllElements();
    	nombreTxf.setText("");
    	apellidoTxf.setText("");
    	pasajeCliente = null;
    }
    private void agregarPasaje() {
    	String nombre = nombreTxf.getText();
    	String apellido = apellidoTxf.getText();
    	if (nombre.isEmpty() || apellido.isEmpty()) {
    		JOptionPane.showMessageDialog(this, "Nombre y apellido obligatorios", "Agregar pasaje",
                    JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	pasajeros.add(new DTPasajero(nombre,apellido));
    	listaNombres.addElement(nombre + " " + apellido);
    	borrarNombres();
    }
    
    private void borrarNombres() {
    	nombreTxf.setText("");
    	apellidoTxf.setText("");
    }
    
    public void actualizarCaso() {
    	//clientes
    	reloadCombo(clienteCbx, contrUsu.listarClientes());
    	clienteCbx.setSelectedIndex(-1);
    	
		//aerolinea
    	reloadCombo(aerolineaCbx, contrUsu.listarAerolineas());
    	aerolineaCbx.setSelectedIndex(-1);
    	
    	
    	rutaCbx.setSelectedIndex(-1);
    	vueloCbx.setSelectedIndex(-1);
    	
    	equipajeSpn.setValue(0);
    	borrarNombres();
    	listaPasajeros.removeAll();
    	listaNombres.removeAllElements();
	}
    
    /*
    private void limpiarFormulario() {
		txtnomVuelo.setText("");;
		txtnomRutaVuelo.setText("");;
        txtFecha.setText("");
        textField_cantTuristas.setText("");
        textField_cantEjecutivos.setText("");
        txtFechaAlta.setText("");
        textField_Duracion.setText("");
        
    } 
    */
}

