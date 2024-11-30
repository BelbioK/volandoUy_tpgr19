package presentacion.vuelos;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;
import logica.interfaces.IControladorVuelos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JSpinner;


@SuppressWarnings("serial")
public class AltaVuelo extends JInternalFrame{
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorVuelos controlVue = fabrica.getControladorVuelos();
	private IControladorUsuarios controlUsu = fabrica.getControladorUsuario();
	private JTextField txtnomVuelo;
	private JTextField textField_cantTuristas;
	private JTextField textField_cantEjecutivos;
	private JLabel txtnomRutaVuelo;
	private JSpinner spinnerDia;
	private JSpinner spinnerMes;
	private JSpinner spinnerAnio;
	private JSpinner spinnerHoras;
	private JSpinner spinnerMinutos;
	private JSpinner spinnerDiaAlta;
	private JSpinner spinnerMesAlta;
	private JSpinner spinnerAnioAlta;
	private JComboBox<String> comboBox_Aerolinea;
	private JComboBox<String> comboBox_RutasAerolinea;
    private Set<String> aerolineasSet = controlUsu.listarAerolineas();
    private Object aeroAnt;
    private Object aeroAhor;
    private Object rutaAnt;
    private Object rutaAhor;
	public AltaVuelo() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta Vuelo");
		setToolTipText("");
		setSize(560, 503);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos del Vuelo");
		lblNewLabel.setBounds(157, 157, 182, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(30, 185, 74, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre de la Ruta: ");
		lblNewLabel_1_1.setBounds(30, 223, 132, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		txtnomVuelo = new JTextField();
		txtnomVuelo.setHorizontalAlignment(SwingConstants.LEFT);
		txtnomVuelo.setColumns(10);
		txtnomVuelo.setBounds(151, 182, 357, 20);
		getContentPane().add(txtnomVuelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha del Vuelo:");
		lblNewLabel_1_1_1.setBounds(289, 330, 105, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		textField_cantTuristas = new JTextField();
		textField_cantTuristas.setColumns(10);
		textField_cantTuristas.setBounds(151, 271, 79, 20);
		getContentPane().add(textField_cantTuristas);
		
		textField_cantEjecutivos = new JTextField();
		textField_cantEjecutivos.setColumns(10);
		textField_cantEjecutivos.setBounds(422, 271, 86, 20);
		getContentPane().add(textField_cantEjecutivos);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Pasajeros Turistas:");
		lblNewLabel_1_1_1_1.setBounds(30, 274, 111, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Pasajeros Ejecutivos:");
		lblNewLabel_1_1_1_2.setBounds(289, 274, 136, 14);
		getContentPane().add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Duracion (HH:MM) :");
		lblNewLabel_1_1_1_3.setBounds(141, 387, 126, 14);
		getContentPane().add(lblNewLabel_1_1_1_3);
		
		JButton ButtonAceptar = new JButton("Aceptar");
		ButtonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
			    try {
			        RegistroVueloActionPerformed(exception);
			    } catch (InstanciaNoExiste e1) {
			        JOptionPane.showMessageDialog(AltaVuelo.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    } catch (DatoInvalido e1) {
					
					e1.printStackTrace();
				}
			}
		});
		ButtonAceptar.setBounds(141, 439, 89, 23);
		getContentPane().add(ButtonAceptar);
		
		JButton ButtonCancelar = new JButton("Cancelar");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				limpiarFormulario();
                setVisible(false);
			}
		});
		ButtonCancelar.setBounds(305, 439, 89, 23);
		getContentPane().add(ButtonCancelar);
		
		txtnomRutaVuelo = new JLabel();
		txtnomRutaVuelo.setHorizontalAlignment(SwingConstants.LEFT);
		txtnomRutaVuelo.setBounds(151, 220, 357, 20);
		getContentPane().add(txtnomRutaVuelo);
		//txtnomRutaVuelo.setColumns(10);
		txtnomRutaVuelo.setText("");
		
		JLabel lblNewLabel_2 = new JLabel("Aerolineas");
		lblNewLabel_2.setBounds(30, 46, 88, 14);
		getContentPane().add(lblNewLabel_2);
		
		String[] aeros;
        if (aerolineasSet == null) {
        	aeros = new String[]{""};
        } else {
        	aeros = aerolineasSet.toArray(new String[0]);
        }
		comboBox_Aerolinea = new JComboBox<String>(aeros);
		comboBox_Aerolinea.setBounds(151, 42, 357, 22);
		comboBox_Aerolinea.setSelectedIndex(-1);
		aeroAnt = comboBox_Aerolinea.getSelectedItem();
        aeroAhor = comboBox_Aerolinea.getSelectedItem();
		getContentPane().add(comboBox_Aerolinea);
		
		comboBox_Aerolinea.addActionListener(e -> { 
			aeroAhor = comboBox_Aerolinea.getSelectedItem();
			if(aeroAhor == null || aeroAhor.equals(aeroAnt)) {
				comboBox_RutasAerolinea.setSelectedIndex(-1);
				txtnomRutaVuelo.setText("");
			}else {
				Set<String> options;
				try {
					options = controlVue.getRutasStatusConfirmada(aeroAhor.toString());
					reloadCombo(comboBox_RutasAerolinea, options);
					comboBox_RutasAerolinea.setSelectedIndex(-1);
				} catch (DatoInvalido e1) {
					e1.printStackTrace();
				}
			}
			aeroAnt = aeroAhor;
		});
		
		JLabel lblNewLabel_3 = new JLabel("Seleccione una Aerolinea");
		lblNewLabel_3.setBounds(157, 17, 198, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Escoja una Ruta de Vuelo");
		lblNewLabel_4.setBounds(157, 73, 151, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Rutas De Vuelo");
		lblNewLabel_5.setBounds(31, 102, 104, 14);
		getContentPane().add(lblNewLabel_5);
		
		String[] rutas = new String[]{""};
		comboBox_RutasAerolinea = new JComboBox<String>(rutas);
		comboBox_RutasAerolinea.setSelectedIndex(-1);
		comboBox_RutasAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				rutaAhor = comboBox_RutasAerolinea.getSelectedItem();
				if(rutaAhor == null || rutaAhor.toString() == "") txtnomRutaVuelo.setText("");
				else if (rutaAhor.equals(rutaAnt));
				else txtnomRutaVuelo.setText(rutaAhor.toString());
				rutaAnt = rutaAhor;
			}
		});
		
		comboBox_RutasAerolinea.setBounds(151, 98, 357, 22);
		getContentPane().add(comboBox_RutasAerolinea);
		
		spinnerDia = new JSpinner();
		spinnerDia.setBounds(382, 320, 43, 35);
		getContentPane().add(spinnerDia);
		
		spinnerMes = new JSpinner();
		spinnerMes.setBounds(425, 320, 43, 35);
		getContentPane().add(spinnerMes);
		
		spinnerAnio = new JSpinner();
		spinnerAnio.setBounds(467, 320, 54, 35);
		getContentPane().add(spinnerAnio);
		
		spinnerHoras = new JSpinner();
		spinnerHoras.setToolTipText("");
		spinnerHoras.setBounds(268, 377, 44, 35);
		getContentPane().add(spinnerHoras);
		
		spinnerMinutos = new JSpinner();
		spinnerMinutos.setBounds(312, 377, 43, 35);
		getContentPane().add(spinnerMinutos);
		
		JLabel lblNewLabel_6 = new JLabel("Dia");
		lblNewLabel_6.setBounds(391, 305, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Mes");
		lblNewLabel_7.setBounds(432, 305, 43, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Año");
		lblNewLabel_8.setBounds(478, 305, 43, 14);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha Alta:");
		lblNewLabel_9.setBounds(30, 330, 88, 14);
		getContentPane().add(lblNewLabel_9);
		
		spinnerDiaAlta = new JSpinner();
		spinnerDiaAlta.setBounds(108, 320, 43, 35);
		getContentPane().add(spinnerDiaAlta);
		
		spinnerMesAlta = new JSpinner();
		spinnerMesAlta.setBounds(151, 320, 43, 35);
		getContentPane().add(spinnerMesAlta);
		
		spinnerAnioAlta = new JSpinner();
		spinnerAnioAlta.setBounds(195, 320, 54, 35);
		getContentPane().add(spinnerAnioAlta);
		
		JLabel lblNewLabel_6_1 = new JLabel("Dia");
		lblNewLabel_6_1.setBounds(116, 305, 46, 14);
		getContentPane().add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("Mes");
		lblNewLabel_7_1.setBounds(157, 305, 43, 14);
		getContentPane().add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Año");
		lblNewLabel_8_1.setBounds(206, 305, 43, 14);
		getContentPane().add(lblNewLabel_8_1);
		
		//limpiarFormulario();
		//setVisible(true);
	}
	
	protected void RegistroVueloActionPerformed(ActionEvent exception) throws InstanciaNoExiste, DatoInvalido { 
		String NombreU = txtnomVuelo.getText();
        String RutaU = txtnomRutaVuelo.getText();
        String cantTuristasU = textField_cantTuristas.getText();
        String cantEjecutivosU = textField_cantEjecutivos.getText();
        String diaString = spinnerDia.getValue().toString();
        String mesString = spinnerMes.getValue().toString();
        String anioString = spinnerAnio.getValue().toString();
        String horasString = spinnerHoras.getValue().toString();
        String minutosString = spinnerMinutos.getValue().toString();
        String diaAltaString = spinnerDiaAlta.getValue().toString();
        String mesAltaString = spinnerMesAlta.getValue().toString();
        String anioAltaString = spinnerAnioAlta.getValue().toString();
		if (verificarDatos(NombreU, RutaU, cantTuristasU, cantEjecutivosU, diaString, mesString, anioString, horasString, minutosString, diaAltaString, mesAltaString, anioAltaString, horasString, minutosString)) {
			Integer cantTuristas = Integer.parseInt(cantTuristasU);
			Integer cantEjecutivos = Integer.parseInt(cantEjecutivosU);
			int hora = Integer.parseInt(horasString);
        	int minutos = Integer.parseInt(minutosString);
			if((cantTuristas == 0) && (cantEjecutivos == 0)) {
				JOptionPane.showMessageDialog(this, "La cantidad total de asientos en el vuelo debe ser mayor a 0", "Error de Validación",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
			if(cantTuristas < 0 || cantEjecutivos < 0) {
				JOptionPane.showMessageDialog(this, "La cantidad asientos debe ser mayor a 0", "Error de Validación",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
			Date fecha = numbersToDate(diaString, mesString, anioString);
	        Date fechaAlta = numbersToDate(diaAltaString, mesAltaString, anioAltaString);
			if (fecha.before(fechaAlta)) {
				JOptionPane.showMessageDialog(this, "La fecha del vuelo debe ser posterior a la fecha de alta", "Error de Validación",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
			try {
				controlVue.altaVuelo(NombreU, fecha, hora, minutos,cantTuristas, cantEjecutivos, fechaAlta, RutaU,"");
				JOptionPane.showMessageDialog(this, "El Vuelo se ha creado con éxito", "Alta de Vuelo",
                        JOptionPane.INFORMATION_MESSAGE);
			}catch (InstanciaRepetida ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta de Vuelo", JOptionPane.ERROR_MESSAGE);
			}
			limpiarFormulario();
		}
	}
	
	private boolean verificarDatos(String nombre, String ruta, String cantTuri, String cantEje, String dia, String mes, String anio, String hora, String minutos, String diaAlta, String mesAlta, String anioAlta, String horasDur,String minutosDur) {
		String NombreU = nombre;
        String RutaU = ruta;
        String cantTuristasU = cantTuri;
        String cantEjecutivosU = cantEje;
        String horasString = hora;
        String minutosString = minutos;
        String diaString = dia;
        String mesString = mes;
        String anioString = anio;
        String diaAltaString = diaAlta;
        String mesAltaString = mesAlta;
        String anioAltaString = anioAlta;
        if (NombreU.isEmpty() || NombreU.isBlank() || RutaU.isEmpty() || RutaU.isBlank()|| cantTuristasU.isEmpty() || cantTuristasU.isBlank()|| cantEjecutivosU.isEmpty() || cantEjecutivosU.isBlank() || horasString.isEmpty() || horasString.isBlank() || minutosString.isEmpty() || minutosString.isBlank() || diaString.isEmpty() || diaString.isBlank() || mesString.isEmpty() || mesString.isBlank() || anioString.isEmpty() || anioString.isBlank() || diaAltaString.isEmpty() || diaAltaString.isBlank() || mesAltaString.isEmpty() || mesAltaString.isBlank() ||  anioAltaString.isEmpty() || anioAltaString.isBlank()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Vuelo",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!validNum(cantTuristasU, "Cantidad de Turistas")) {
        	return false;
        }
        
        if (!validNum(cantEjecutivosU, "Cantidad de Ejecutivos")) {
        	return false;
        }
        
        if (!validDur(horasString, minutosString, "La duracion")) {
        	return false;
        }
        
        if(!validDate(diaString, mesString, anioString, "La fecha")) {
        	return false;
        }
        
        if(!validDate(diaAltaString, mesAltaString, anioAltaString, "La fecha de Alta")) {
        	return false;
        }
        return true;
	}
	
	private boolean validNum(String texto, String campo) {
		try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, campo + " debe ser un numero", "Error de Validacion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
	}
	
	private boolean validDate(String dia, String mes, String anio, String campo) {
		try {
			int diaU = Integer.parseInt(dia);
            int mesU = Integer.parseInt(mes);
            int anioU = Integer.parseInt(anio);
            if (diaU <= 0 || diaU > 31) {
                JOptionPane.showMessageDialog(this, campo + " debe tener un día válido entre 1 y 31", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (mesU <= 0 || mesU > 12) {
                JOptionPane.showMessageDialog(this, campo + " debe tener un mes válido entre 1 y 12", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (anioU <= 0) {
                JOptionPane.showMessageDialog(this, campo + " debe tener un año válido mayor que 0", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return false;
			}else {
				Calendar calendario = Calendar.getInstance();
				calendario.setLenient(false);
                calendario.set(Calendar.YEAR, anioU);
                calendario.set(Calendar.MONTH,mesU-1);
                calendario.set(Calendar.DAY_OF_MONTH, diaU);
                calendario.getTime();
                return true;
			}
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, campo + " debe ser una fecha válida y contener solo números", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, campo + " debe ser una fecha válida", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}
	
	private boolean validDur(String hora, String minutos, String campo) {
		try {
			int horaU = Integer.parseInt(hora);
			int minutosU = Integer.parseInt(minutos);
			if ((horaU == 0 && minutosU == 0) || minutosU >= 60 || horaU < 0 || minutosU < 0) {
				JOptionPane.showMessageDialog(this, campo + " debe ser una entrada valida");
                return false;
			}
            return true;
		}catch(Exception e){
            JOptionPane.showMessageDialog(this, "Hora invalida.");
            return false;
        }
	}
	
	private void limpiarFormulario() {
		txtnomVuelo.setText("");;
		txtnomRutaVuelo.setText("");;
        textField_cantTuristas.setText("");
        textField_cantEjecutivos.setText("");
        spinnerHoras.setValue(0);
        spinnerMinutos.setValue(0);
        spinnerDia.setValue(0);
        spinnerMes.setValue(0);
        spinnerAnio.setValue(0);
        spinnerDiaAlta.setValue(0);
        spinnerMesAlta.setValue(0);
        spinnerAnioAlta.setValue(0);
        this.comboBox_Aerolinea.setSelectedIndex(-1);
        this.comboBox_RutasAerolinea.setSelectedIndex(-1);
        this.comboBox_RutasAerolinea.removeAllItems();
    } 
	 
	public void actualizarCaso() {
		//aerolinea
		reloadCombo(comboBox_Aerolinea, controlUsu.listarAerolineas());
		comboBox_RutasAerolinea.removeAllItems();
		System.out.print("alta vuelo actualizado \n");
	}

	private void reloadCombo(JComboBox<String> combo, Set<String> items) {
		combo.setSelectedItem("");
		combo.removeAllItems();
		for(String item:items) {
			combo.addItem(item);
		}	
		combo.setSelectedIndex(-1);
	}
	
	private Date numbersToDate(String dia_s, String mes_s, String anio_s) {
        int dia = Integer.parseInt(dia_s);
        int mes = Integer.parseInt(mes_s);
        int anio = Integer.parseInt(anio_s);
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH,mes-1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        Date fechaNueva = calendario.getTime();
        return fechaNueva;
    }
}