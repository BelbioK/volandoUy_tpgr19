package presentacion.vuelos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logica.controladores.Fabrica;
import logica.datatypes.DTPasajero;
import logica.datatypes.DTReserva;
import logica.datatypes.DTVuelo;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;
import presentacion.Main;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ConsultaVuelo extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JLabel aerolineaLbl;
	private JComboBox<String> aerolineaCbx;
	
	private JLabel rutaLbl;
	private JComboBox<String> rutaCbx;
	
	private JLabel vueloLbl;
	private JComboBox<String> vueloCbx;
	
	private JLabel fechaVueloLbl;
	private JLabel fechaVueloRes;
	
	private JLabel duraLbl;
	private JLabel duraRes;
	
	private JLabel asiMaxTurLbl;
	private JLabel asiMaxTurRes;
	private JLabel asiResTurLbl;
	private JLabel asiResTurRes;
	private JLabel asiMaxEjLbl;
	private JLabel asiMaxEjRes;
	private JLabel asiResEjLbl;
	private JLabel asiResEjRes;
	
	private JLabel fechaLbl;
	private JLabel fechaRes;
	
	private JLabel reservasLbl;
	private JLabel nombresLbl;
	private JList<String> reservasList;
	private JList<String> nombresList;
	private JScrollPane reservasScr;
	private JScrollPane nombresScr;
	private JLabel fechaRLbl;
	private JLabel tipoAsientoLbl;
	private JLabel cantEquipajeLbl;
	private JLabel costoLbl;
	private JLabel fechaRRes;
	private JLabel tipoAsientoRes;
	private JLabel cantEquipajeRes;
	private JLabel costoRes;
	
	private JButton btnRuta;
	private JButton btnAceptar;
    
    private Object aeroAnt;
    private Object aeroAhor;
    private Object rutaAnt;
    private Object rutaAhor;
    private Object vueloAnt;
    private Object vueloAhor;
    
    private Fabrica fabrica = Fabrica.getInstancia();
    private IControladorVuelos contVuelo = fabrica.getControladorVuelos();
    private IControladorUsuarios contUsuario = fabrica.getControladorUsuario();
    
    private Set<String> aerolineasSet = contUsuario.listarAerolineas();
    private String[] arrayVacio = {};
    
    private Set<DTReserva> reservasSet;    
    private JDesktopPane desktopPane = new JDesktopPane();
    
    SimpleDateFormat horas = new SimpleDateFormat("HH:mm");
    SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
    
    Main main = null;
    
    public ConsultaVuelo() {
    	//Basic config
    	setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de vuelo");
        setBounds(10, 40, 410, 180); // 500 o 180
        setContentPane(desktopPane);
        //grid configs
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 102, 120, 110, 0 };
        gridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20};
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.2, 0.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);
        
        
      //Aerolinea de vuelo
        aerolineaLbl = new JLabel("Aerolinea:");
        aerolineaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_aerolineaLbl = new GridBagConstraints();
        gbc_aerolineaLbl.fill = GridBagConstraints.BOTH;
        gbc_aerolineaLbl.insets = new Insets(10, 0, 5, 5);
        gbc_aerolineaLbl.gridx = 0;
        gbc_aerolineaLbl.gridy = 0;
        getContentPane().add(aerolineaLbl, gbc_aerolineaLbl);
        
        
        String[] aeros;
        if (aerolineasSet == null) {
        	aeros = new String[]{""};
        } else {
        	aeros = aerolineasSet.toArray(new String[0]);
        }
        aerolineaCbx = new JComboBox<String>(aeros);
        aerolineaCbx.setSelectedIndex(-1);
        aeroAnt = aerolineaCbx.getSelectedItem();
        aeroAhor = aerolineaCbx.getSelectedItem();
        GridBagConstraints gbc_aerolineaTxf = new GridBagConstraints();
        gbc_aerolineaTxf.gridwidth = 2;
        gbc_aerolineaTxf.fill = GridBagConstraints.BOTH;
        gbc_aerolineaTxf.insets = new Insets(10, 0, 5, 0);
        gbc_aerolineaTxf.gridx = 1;
        gbc_aerolineaTxf.gridy = 0;
        getContentPane().add(aerolineaCbx, gbc_aerolineaTxf);
        
      //ruta de aerolinea
        rutaLbl = new JLabel("Ruta de vuelo:");
        rutaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_rutaLbl = new GridBagConstraints();
        gbc_rutaLbl.fill = GridBagConstraints.BOTH;
        gbc_rutaLbl.insets = new Insets(5, 0, 5, 5);
        gbc_rutaLbl.gridx = 0;
        gbc_rutaLbl.gridy = 1;
        getContentPane().add(rutaLbl, gbc_rutaLbl);

        String[] rutas = new String[]{""};
        rutaCbx = new JComboBox<String>(rutas);
        rutaCbx.setSelectedIndex(-1);
        rutaAnt = rutaCbx.getSelectedItem();
        rutaAhor = rutaCbx.getSelectedItem();
        GridBagConstraints gbc_rutaTxf = new GridBagConstraints();
        gbc_rutaTxf.gridwidth = 2;
        gbc_rutaTxf.fill = GridBagConstraints.BOTH;
        gbc_rutaTxf.insets = new Insets(5, 0, 5, 0);
        gbc_rutaTxf.gridx = 1;
        gbc_rutaTxf.gridy = 1;
        getContentPane().add(rutaCbx, gbc_rutaTxf);
        
      //Vuelos de Ruta de aerolinea
        vueloLbl = new JLabel("Vuelo:");
        vueloLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_vueloLbl = new GridBagConstraints();
        gbc_vueloLbl.fill = GridBagConstraints.BOTH;
        gbc_vueloLbl.insets = new Insets(5, 0, 5, 5);
        gbc_vueloLbl.gridx = 0;
        gbc_vueloLbl.gridy = 2;
        getContentPane().add(vueloLbl, gbc_vueloLbl);

        String[] vuelos = {};
        vueloCbx = new JComboBox<String>(vuelos);
        vueloCbx.setSelectedIndex(-1);
        GridBagConstraints gbc_vueloTxf = new GridBagConstraints();
        gbc_vueloTxf.gridwidth = 2;
        gbc_vueloTxf.fill = GridBagConstraints.BOTH;
        gbc_vueloTxf.insets = new Insets(5, 0, 5, 0);
        gbc_vueloTxf.gridx = 1;
        gbc_vueloTxf.gridy = 2;
        getContentPane().add(vueloCbx, gbc_vueloTxf);
        
        
        //botones
        btnAceptar = new JButton("Aceptar");
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(5, 0, 0, 5);
        gbc_btnAceptar.gridx = 2;
        gbc_btnAceptar.gridy = 3;
        gbc_btnAceptar.weightx = 1.0;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnRuta = new JButton("Datos ruta");
        GridBagConstraints gbc_btnRuta = new GridBagConstraints();
        gbc_btnRuta.fill = GridBagConstraints.BOTH;
        gbc_btnRuta.insets = new Insets(5, 0, 0, 5);
        gbc_btnRuta.gridx = 2;
        gbc_btnRuta.gridy = 3;
        gbc_btnRuta.weightx = 1.0;
        getContentPane().add(btnRuta, gbc_btnRuta);
        
        //FechaVuelo
        fechaVueloLbl = new JLabel("Fecha:");
        fechaVueloLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaVueloLbl = new GridBagConstraints();
        gbc_fechaVueloLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaVueloLbl.insets = new Insets(5, 0, 5, 5);
        gbc_fechaVueloLbl.gridx = 0;
        gbc_fechaVueloLbl.gridy = 3;
        getContentPane().add(fechaVueloLbl, gbc_fechaVueloLbl);
        
        fechaVueloRes = new JLabel("-");
        fechaVueloRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaVueloRes = new GridBagConstraints();
        gbc_fechaVueloRes.anchor = GridBagConstraints.WEST;
        gbc_fechaVueloRes.fill = GridBagConstraints.VERTICAL;
        gbc_fechaVueloRes.insets = new Insets(5, 0, 5, 5);
        gbc_fechaVueloRes.gridx = 1;
        gbc_fechaVueloRes.gridy = 3;
        getContentPane().add(fechaVueloRes, gbc_fechaVueloRes);
        
        //Duración
        duraLbl = new JLabel("Duración:");
        duraLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_duraLbl = new GridBagConstraints();
        gbc_duraLbl.fill = GridBagConstraints.BOTH;
        gbc_duraLbl.insets = new Insets(5, 0, 5, 5);
        gbc_duraLbl.gridx = 0;
        gbc_duraLbl.gridy = 4;
        getContentPane().add(duraLbl, gbc_duraLbl);
        
        duraRes = new JLabel("-");
        duraRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_duraRes = new GridBagConstraints();
        gbc_duraRes.anchor = GridBagConstraints.WEST;
        gbc_duraRes.fill = GridBagConstraints.VERTICAL;
        gbc_duraRes.insets = new Insets(5, 0, 5, 5);
        gbc_duraRes.gridx = 1;
        gbc_duraRes.gridy = 4;
        getContentPane().add(duraRes, gbc_duraRes);
        
        //Asiento Max Turista
        asiMaxTurLbl = new JLabel("Asientos Turista:");
        asiMaxTurLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiMaxTurLbl = new GridBagConstraints();
        gbc_asiMaxTurLbl.fill = GridBagConstraints.BOTH;
        gbc_asiMaxTurLbl.insets = new Insets(5, 0, 5, 5);
        gbc_asiMaxTurLbl.gridx = 0;
        gbc_asiMaxTurLbl.gridy = 5;
        getContentPane().add(asiMaxTurLbl, gbc_asiMaxTurLbl);
        
        asiMaxTurRes = new JLabel("-");
        asiMaxTurRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiMaxTurRes = new GridBagConstraints();
        gbc_asiMaxTurRes.anchor = GridBagConstraints.WEST;
        gbc_asiMaxTurRes.fill = GridBagConstraints.VERTICAL;
        gbc_asiMaxTurRes.insets = new Insets(5, 0, 5, 5);
        gbc_asiMaxTurRes.gridx = 1;
        gbc_asiMaxTurRes.gridy = 5;
        getContentPane().add(asiMaxTurRes, gbc_asiMaxTurRes);
        
        //asientos restantes
        asiResTurLbl = new JLabel("Restantes:");
        asiResTurLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiResTurLbl = new GridBagConstraints();
        gbc_asiResTurLbl.fill = GridBagConstraints.BOTH;
        gbc_asiResTurLbl.insets = new Insets(5, 0, 5, 5);
        gbc_asiResTurLbl.gridx = 1;
        gbc_asiResTurLbl.gridy = 5;
        getContentPane().add(asiResTurLbl, gbc_asiResTurLbl);
        
        asiResTurRes = new JLabel("-");
        asiResTurRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiResTurRes = new GridBagConstraints();
        gbc_asiResTurRes.anchor = GridBagConstraints.WEST;
        gbc_asiResTurRes.fill = GridBagConstraints.VERTICAL;
        gbc_asiResTurRes.insets = new Insets(5, 0, 5, 5);
        gbc_asiResTurRes.gridx = 2;
        gbc_asiResTurRes.gridy = 5;
        getContentPane().add(asiResTurRes, gbc_asiResTurRes);
        
        //Asiento Max Ejecutivo
        asiMaxEjLbl = new JLabel("Asientos Ejecutivo:");
        asiMaxEjLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiMaxEjLbl = new GridBagConstraints();
        gbc_asiMaxEjLbl.fill = GridBagConstraints.BOTH;
        gbc_asiMaxEjLbl.insets = new Insets(5, 0, 5, 5);
        gbc_asiMaxEjLbl.gridx = 0;
        gbc_asiMaxEjLbl.gridy = 6;
        getContentPane().add(asiMaxEjLbl, gbc_asiMaxEjLbl);
        
        asiMaxEjRes = new JLabel("-");
        asiMaxEjRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiMaxEjRes = new GridBagConstraints();
        gbc_asiMaxEjRes.anchor = GridBagConstraints.WEST;
        gbc_asiMaxEjRes.fill = GridBagConstraints.VERTICAL;
        gbc_asiMaxEjRes.insets = new Insets(5, 0, 5, 5);
        gbc_asiMaxEjRes.gridx = 1;
        gbc_asiMaxEjRes.gridy = 6;
        getContentPane().add(asiMaxEjRes, gbc_asiMaxEjRes);
        
        //Asientos Ejecutivo Restantes
        asiResEjLbl = new JLabel("Restantes:");
        asiResEjLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiResEjLbl = new GridBagConstraints();
        gbc_asiResEjLbl.fill = GridBagConstraints.BOTH;
        gbc_asiResEjLbl.insets = new Insets(5, 0, 5, 5);
        gbc_asiResEjLbl.gridx = 1;
        gbc_asiResEjLbl.gridy = 6;
        getContentPane().add(asiResEjLbl, gbc_asiResEjLbl);
        
        asiResEjRes = new JLabel("-");
        asiResEjRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_asiResEjRes = new GridBagConstraints();
        gbc_asiResEjRes.anchor = GridBagConstraints.WEST;
        gbc_asiResEjRes.fill = GridBagConstraints.VERTICAL;
        gbc_asiResEjRes.insets = new Insets(5, 0, 5, 5);
        gbc_asiResEjRes.gridx = 2;
        gbc_asiResEjRes.gridy = 6;
        getContentPane().add(asiResEjRes, gbc_asiResEjRes);
        
        //Fecha de alta
        fechaLbl = new JLabel("Fecha Alta:");
        fechaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaLbl = new GridBagConstraints();
        gbc_fechaLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaLbl.insets = new Insets(5, 0, 5, 5);
        gbc_fechaLbl.gridx = 0;
        gbc_fechaLbl.gridy = 7;
        getContentPane().add(fechaLbl, gbc_fechaLbl);
        
        fechaRes = new JLabel("-");
        fechaRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaRes = new GridBagConstraints();
        gbc_fechaRes.gridwidth = 2;
        gbc_fechaRes.anchor = GridBagConstraints.WEST;
        gbc_fechaRes.fill = GridBagConstraints.VERTICAL;
        gbc_fechaRes.insets = new Insets(5, 0, 5, 5);
        gbc_fechaRes.gridx = 1;
        gbc_fechaRes.gridy = 7;
        getContentPane().add(fechaRes, gbc_fechaRes);
        
        //reservas
        reservasLbl = new JLabel("Reservas:");
        reservasLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_reservasLbl = new GridBagConstraints();
        gbc_reservasLbl.fill = GridBagConstraints.VERTICAL;
        gbc_reservasLbl.insets = new Insets(5, 0, 5, 5);
        gbc_reservasLbl.gridx = 1;
        gbc_reservasLbl.gridy = 8;
        getContentPane().add(reservasLbl, gbc_reservasLbl);

        String[] reservas = {};
        reservasList = new JList<>(reservas);
        reservasScr = new JScrollPane(reservasList);
        reservasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservasScr.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints gbc_reservasTxf = new GridBagConstraints();
        gbc_reservasTxf.fill = GridBagConstraints.BOTH;
        gbc_reservasTxf.insets = new Insets(5, 0, 5, 0);
        gbc_reservasTxf.gridx = 1;
        gbc_reservasTxf.gridy = 9;
        getContentPane().add(reservasScr, gbc_reservasTxf);
        
        //Nombres Clientes
        nombresLbl = new JLabel("Pasajes:");
        nombresLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_nombresLbl = new GridBagConstraints();
        gbc_nombresLbl.fill = GridBagConstraints.VERTICAL;
        gbc_nombresLbl.insets = new Insets(5, 0, 5, 5);
        gbc_nombresLbl.gridx = 2;
        gbc_nombresLbl.gridy = 8;
        getContentPane().add(nombresLbl, gbc_nombresLbl);

        String[] nombres = {};
        nombresList = new JList<>(nombres);
        nombresScr = new JScrollPane(nombresList);
        nombresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nombresScr.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints gbc_nombresTxf = new GridBagConstraints();
        gbc_nombresTxf.fill = GridBagConstraints.BOTH;
        gbc_nombresTxf.insets = new Insets(5, 0, 5, 0);
        gbc_nombresTxf.gridx = 2;
        gbc_nombresTxf.gridy = 9;
        getContentPane().add(nombresScr, gbc_nombresTxf);
        
        
        //reservas datos
        
        //Fecha
        fechaRLbl = new JLabel("Fecha Reserva:");
        fechaRLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaRLbl = new GridBagConstraints();
        gbc_fechaRLbl.fill = GridBagConstraints.BOTH;
        gbc_fechaRLbl.insets = new Insets(5, 0, 5, 5);
        gbc_fechaRLbl.gridx = 0;
        gbc_fechaRLbl.gridy = 10;
        getContentPane().add(fechaRLbl, gbc_fechaRLbl);
        
        fechaRRes = new JLabel("-");
        fechaRRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_fechaRRes = new GridBagConstraints();
        gbc_fechaRRes.anchor = GridBagConstraints.WEST;
        gbc_fechaRRes.fill = GridBagConstraints.VERTICAL;
        gbc_fechaRRes.insets = new Insets(5, 0, 5, 5);
        gbc_fechaRRes.gridx = 1;
        gbc_fechaRRes.gridy = 10;
        getContentPane().add(fechaRRes, gbc_fechaRRes);
        
        //TipoAsiento
        tipoAsientoLbl = new JLabel("Tipo Asiento:");
        tipoAsientoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_tipoAsientoLbl = new GridBagConstraints();
        gbc_tipoAsientoLbl.fill = GridBagConstraints.BOTH;
        gbc_tipoAsientoLbl.insets = new Insets(5, 0, 5, 5);
        gbc_tipoAsientoLbl.gridx = 0;
        gbc_tipoAsientoLbl.gridy = 11;
        getContentPane().add(tipoAsientoLbl, gbc_tipoAsientoLbl);
        
        tipoAsientoRes = new JLabel("-");
        tipoAsientoRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_tipoAsientoRes = new GridBagConstraints();
        gbc_tipoAsientoRes.anchor = GridBagConstraints.WEST;
        gbc_tipoAsientoRes.fill = GridBagConstraints.VERTICAL;
        gbc_tipoAsientoRes.insets = new Insets(5, 0, 5, 5);
        gbc_tipoAsientoRes.gridx = 1;
        gbc_tipoAsientoRes.gridy = 11;
        getContentPane().add(tipoAsientoRes, gbc_tipoAsientoRes);
        
        //Costo
        costoLbl = new JLabel("Costo:");
        costoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_costoLbl = new GridBagConstraints();
        gbc_costoLbl.fill = GridBagConstraints.BOTH;
        gbc_costoLbl.insets = new Insets(5, 0, 5, 5);
        gbc_costoLbl.gridx = 1;
        gbc_costoLbl.gridy = 12;
        getContentPane().add(costoLbl, gbc_costoLbl);
        
        costoRes = new JLabel("-");
        costoRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_costoRes = new GridBagConstraints();
        gbc_costoRes.anchor = GridBagConstraints.WEST;
        gbc_costoRes.fill = GridBagConstraints.VERTICAL;
        gbc_costoRes.insets = new Insets(5, 0, 5, 5);
        gbc_costoRes.gridx = 2;
        gbc_costoRes.gridy = 12;
        getContentPane().add(costoRes, gbc_costoRes);
        
        //Equipaje Extra
        cantEquipajeLbl = new JLabel("Equipaje Extra:");
        cantEquipajeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_cantEquipajeLbl = new GridBagConstraints();
        gbc_cantEquipajeLbl.fill = GridBagConstraints.BOTH;
        gbc_cantEquipajeLbl.insets = new Insets(5, 0, 5, 5);
        gbc_cantEquipajeLbl.gridx = 0;
        gbc_cantEquipajeLbl.gridy = 12;
        getContentPane().add(cantEquipajeLbl, gbc_cantEquipajeLbl);
        
        cantEquipajeRes = new JLabel("-");
        cantEquipajeRes.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_cantEquipajeRes = new GridBagConstraints();
        gbc_cantEquipajeRes.anchor = GridBagConstraints.WEST;
        gbc_cantEquipajeRes.fill = GridBagConstraints.VERTICAL;
        gbc_cantEquipajeRes.insets = new Insets(5, 0, 5, 5);
        gbc_cantEquipajeRes.gridx = 1;
        gbc_cantEquipajeRes.gridy = 12;
        getContentPane().add(cantEquipajeRes, gbc_cantEquipajeRes);
        
        
        //set true algunas cosas
        rutaCbx.setVisible(true);
        vueloCbx.setVisible(true); 
        mostrarInfo(false);
        btnAceptar.setEnabled(false);
    	
        btnRuta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		abrirConsultaRuta();
        	}
        });
        
        
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DTVuelo dtVuelo;
				try {
					dtVuelo = contVuelo.mostrarInfoVuelo(vueloCbx.getSelectedItem().toString());
				} catch (InstanciaNoExiste e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
        		setBounds(10, 40, 410, 500);
        		btnAceptar.setVisible(false);
        		
        		mostrarInfo(true);
            	
            	fechaVueloRes.setText(fechaHora.format(dtVuelo.getFecha()));
            	duraRes.setText(dtVuelo.getDuracionStringDT());
            	asiMaxTurRes.setText(dtVuelo.getAsientosMaxTurista()+"");
            	asiMaxEjRes.setText(dtVuelo.getAsientosMaxEjecutivo()+"");
            	asiResTurRes.setText(dtVuelo.getAsientosRestantesTurista()+"");
            	asiResEjRes.setText(dtVuelo.getAsientosRestantesEjecutivo()+"");
            	fechaRes.setText(dtVuelo.getFechaAlta()+"");
            	
            	List<String> idReservas = new ArrayList<String>();
            	reservasSet = dtVuelo.getReservas();
            	if(reservasSet != null) {
            		for (DTReserva reserva : reservasSet) idReservas.add(reserva.getNickCliente());
            	} else idReservas.add("No hay reservas");
            	reservasList.setListData(idReservas.toArray(new String[0]));
        	}
        });
        
        
     // ActionListener para el JComboBox de aerolíneas
        aerolineaCbx.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent exception) {
        		aeroAhor = aerolineaCbx.getSelectedItem();
        		if (aeroAhor == null || !aeroAhor.equals(aeroAnt) ||										//Si no hubo cambio o no se seleccionó nada y la siguiente está mostrada 
        				aerolineaCbx.getSelectedItem().equals("")) {					//Si se selecionó desde una lista vacía de aerolineas
        			if(aeroAhor!=null && !aerolineaCbx.getSelectedItem().equals("")) {
						try {
							Set<String>options = contVuelo.listarRutasDeVuelo(aeroAhor.toString());
	        				reloadCombo(rutaCbx, options);

						} catch (DatoInvalido e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        			}
        			
        			setBounds(10, 40, 410, 180);
        			btnAceptar.setVisible(true);
        			btnAceptar.setEnabled(false);
        			rutaCbx.setSelectedIndex(-1);
        			
        			mostrarInfo(false);
        		}
        		aeroAnt = aeroAhor;
        	}
        });
        
        // ActionListener para el JComboBox de aerolíneas
        
        rutaCbx.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent exception) {
        		rutaAhor = rutaCbx.getSelectedItem();
        		if ((rutaAhor ==  null || 
        				!rutaAhor.equals(rutaAnt) ||
        				rutaCbx.getSelectedItem().equals("")) &&
        				vueloCbx.isVisible()) {
        			if(rutaAhor!=null) {
        				Set<String> options;
						try {
							if(rutaAhor!="") {
								options = contVuelo.listarVuelosDeRuta(rutaAhor.toString());
		        				reloadCombo(vueloCbx, options);
							}

						} catch (InstanciaNoExiste e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        			}
        			        			
        			setBounds(10, 40, 410, 180);
        			vueloCbx.setSelectedIndex(-1);
        			btnAceptar.setVisible(true);
        			btnAceptar.setEnabled(false);
        			
        			mostrarInfo(false);
        		}
        		rutaAnt = rutaAhor;
        	}
        });
        
        vueloCbx.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent exception) {
        		vueloAhor = vueloCbx.getSelectedItem();
        		if (vueloAhor ==  null || 
        				!vueloAhor.equals(vueloAnt) ||
        				vueloCbx.getSelectedItem().equals("")) {
        			
        			setBounds(10, 40, 410, 180);
        			btnAceptar.setVisible(true);
        			btnAceptar.setEnabled(true);
        			mostrarInfo(false);
        		}
        		vueloAnt = vueloAhor;
        	}
        });
 
        reservasList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent exception) {
                // Verificar que la selección no sea un cambio intermedio
                if (!exception.getValueIsAdjusting()) {
                    String selectedValue = reservasList.getSelectedValue();
                    if (selectedValue == "No hay reservas" || reservasSet == null) return;
                    nombresList.removeAll();
                    DTReserva ess = null;
                    for(DTReserva res : reservasSet) {
                    	if (res.getNickCliente() == selectedValue) {
                    		ess = res;
                    		break;
                    	}
                    }
                    if (ess == null) return;
                    Set<DTPasajero> pasajes = ess.getPasajes();
                    List<String> nomPasajes = new ArrayList<>();
                	for (DTPasajero nom : pasajes) nomPasajes.add(nom.getNombreCompleto());
                	nombresList.setListData(nomPasajes.toArray(new String[0]));
                	fechaRRes.setText(ess.getFechaReserva().toString());
                	tipoAsientoRes.setText(ess.getTipoAsiento().toString());
                	cantEquipajeRes.setText(ess.getEquipajeExtra().toString());
                	costoRes.setText(ess.getCosto().toString());
                }
            }
        });
    }
    

	public void actualizarCaso() {
		//aerolinea
		reloadCombo(aerolineaCbx, contUsuario.listarAerolineas());
		aerolineaCbx.setSelectedIndex(-1);
		rutaCbx.removeAllItems();
	}
	
	
	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
        combo.removeAllItems();
        for(String item:items) {
        	combo.addItem(item);        	
        }
	}
	private void abrirConsultaRuta() {
		if(main != null)
			main.abrirConsultaRuta();
	}
	
	private void mostrarInfo(boolean bool) {
		btnRuta.setVisible(bool);
    	duraLbl.setVisible(bool);
		duraRes.setVisible(bool);
		fechaVueloLbl.setVisible(bool);
		fechaVueloRes.setVisible(bool);
		
		asiMaxTurLbl.setVisible(bool);
		asiMaxTurRes.setVisible(bool);
		asiResTurLbl.setVisible(bool);
		asiResTurRes.setVisible(bool);
		asiMaxEjLbl.setVisible(bool);
		asiMaxEjRes.setVisible(bool);
		asiResEjLbl.setVisible(bool);
		asiResEjRes.setVisible(bool);
		
		fechaLbl.setVisible(bool);
		fechaRes.setVisible(bool);
		
		reservasLbl.setVisible(bool);
		nombresLbl.setVisible(bool);
		reservasScr.setVisible(bool);
		nombresScr.setVisible(bool);
		fechaRLbl.setVisible(bool);
		tipoAsientoLbl.setVisible(bool);
		cantEquipajeLbl.setVisible(bool);
		costoLbl.setVisible(bool);
		fechaRRes.setVisible(bool);
		tipoAsientoRes.setVisible(bool);
		cantEquipajeRes.setVisible(bool);
		costoRes.setVisible(bool);
		fechaRRes.setText("");
    	tipoAsientoRes.setText("");
    	cantEquipajeRes.setText("");
    	costoRes.setText("");
    	nombresList.setListData(arrayVacio);
    }
	
	public void setMain(Main main) {
		this.main = main; 
	}
}