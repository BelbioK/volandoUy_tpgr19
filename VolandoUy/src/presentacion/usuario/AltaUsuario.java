package presentacion.usuario;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logica.controladores.Fabrica;
import logica.datatypes.DTAerolinea;
import logica.datatypes.DTCliente;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoDocumento;
import excepciones.DatoInvalido;
import excepciones.InstanciaNoExiste;
import excepciones.InstanciaRepetida;
import logica.interfaces.IControladorUsuarios;

import java.util.Date;

import java.util.Set;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JSpinner;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

public class AltaUsuario extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> userTypeComboBox;
    private JLabel userTypeLabel;
    private JLabel nicknameLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel lastNameLabel;
    private JLabel nationalityLabel;
    private JLabel birthdayLabel;
    private JLabel documentTypeLabel;
    private JLabel documentNumberLabel;
    private JLabel airlineDescriptionLabel;
    private JLabel websiteLinkLabel;
    private JTextField nicknameField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField lastNameField;
    private JTextField nationalityField;
    private JComboBox<TipoDocumento> documentTypeField;
    private JTextField documentNumberField;
    private JTextArea airlineDescriptionArea;
    private JTextField websiteLinkField;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panel;
    private JSpinner dayField;
    private JSpinner monthField;
    private JSpinner yearField;
    private JButton modificarButton;
    
    public AltaUsuario() {
        // Configuración de la ventana
        setTitle("Alta de Usuario");
        setSize(400, 634);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{192, 192, 0};
        gridBagLayout.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 44, 38, 38, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
                
        // Componentes del formulario
        userTypeLabel = new JLabel("Tipo de Usuario:");
        GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
        gbc_userTypeLabel.fill = GridBagConstraints.BOTH;
        gbc_userTypeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_userTypeLabel.gridx = 0;
        gbc_userTypeLabel.gridy = 0;
        getContentPane().add(userTypeLabel, gbc_userTypeLabel);
        userTypeComboBox = new JComboBox<>(new String[]{"Cliente", "Aerolinea"});
        GridBagConstraints gbc_userTypeComboBox = new GridBagConstraints();
        gbc_userTypeComboBox.fill = GridBagConstraints.BOTH;
        gbc_userTypeComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_userTypeComboBox.gridx = 1;
        gbc_userTypeComboBox.gridy = 0;
        getContentPane().add(userTypeComboBox, gbc_userTypeComboBox);
        
        // Mostrar u ocultar campos según el tipo de usuario seleccionado
        userTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
                toggleFields();
                resetFormulario();
            }
        });
                        
        nicknameLabel = new JLabel("Nickname:");
        GridBagConstraints gbc_nicknameLabel = new GridBagConstraints();
        gbc_nicknameLabel.fill = GridBagConstraints.BOTH;
        gbc_nicknameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_nicknameLabel.gridx = 0;
        gbc_nicknameLabel.gridy = 1;
        getContentPane().add(nicknameLabel, gbc_nicknameLabel);
        nicknameField = new JTextField();
        GridBagConstraints gbc_nicknameField = new GridBagConstraints();
        gbc_nicknameField.fill = GridBagConstraints.BOTH;
        gbc_nicknameField.insets = new Insets(0, 0, 5, 0);
        gbc_nicknameField.gridx = 1;
        gbc_nicknameField.gridy = 1;
        getContentPane().add(nicknameField, gbc_nicknameField);

        nameLabel = new JLabel("Nombre:");
        GridBagConstraints gbc_nameLabel = new GridBagConstraints();
        gbc_nameLabel.fill = GridBagConstraints.BOTH;
        gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_nameLabel.gridx = 0;
        gbc_nameLabel.gridy = 2;
        getContentPane().add(nameLabel, gbc_nameLabel);
        nameField = new JTextField();
        GridBagConstraints gbc_nameField = new GridBagConstraints();
        gbc_nameField.fill = GridBagConstraints.BOTH;
        gbc_nameField.insets = new Insets(0, 0, 5, 0);
        gbc_nameField.gridx = 1;
        gbc_nameField.gridy = 2;
        getContentPane().add(nameField, gbc_nameField);
        
        lblNewLabel_contrasenia = new JLabel("Contraseña:");
        lblNewLabel_contrasenia.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblNewLabel_contrasenia = new GridBagConstraints();
        gbc_lblNewLabel_contrasenia.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_contrasenia.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_contrasenia.gridx = 0;
        gbc_lblNewLabel_contrasenia.gridy = 3;
        getContentPane().add(lblNewLabel_contrasenia, gbc_lblNewLabel_contrasenia);
        
        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.fill = GridBagConstraints.BOTH;
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 3;
        getContentPane().add(passwordField, gbc_passwordField);
        
        lblNewLabel_contrasenia_confir = new JLabel("Confirme la contraseña:");
        lblNewLabel_contrasenia_confir.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblNewLabel_contrasenia_confir = new GridBagConstraints();
        gbc_lblNewLabel_contrasenia_confir.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_contrasenia_confir.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_contrasenia_confir.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_contrasenia_confir.gridx = 0;
        gbc_lblNewLabel_contrasenia_confir.gridy = 4;
        getContentPane().add(lblNewLabel_contrasenia_confir, gbc_lblNewLabel_contrasenia_confir);
                
                passwordField_confirm = new JPasswordField();
                GridBagConstraints gbc_passwordField_confirm = new GridBagConstraints();
                gbc_passwordField_confirm.insets = new Insets(0, 0, 5, 0);
                gbc_passwordField_confirm.fill = GridBagConstraints.BOTH;
                gbc_passwordField_confirm.gridx = 1;
                gbc_passwordField_confirm.gridy = 4;
                getContentPane().add(passwordField_confirm, gbc_passwordField_confirm);
                
                birthdayLabel = new JLabel("Fecha de Nacimiento:");
                GridBagConstraints gbc_birthdayLabel = new GridBagConstraints();
                gbc_birthdayLabel.fill = GridBagConstraints.BOTH;
                gbc_birthdayLabel.insets = new Insets(0, 0, 5, 5);
                gbc_birthdayLabel.gridx = 0;
                gbc_birthdayLabel.gridy = 5;
                getContentPane().add(birthdayLabel, gbc_birthdayLabel);
                
                panel = new JPanel();
                GridBagConstraints gbc_panel = new GridBagConstraints();
                gbc_panel.fill = GridBagConstraints.BOTH;
                gbc_panel.insets = new Insets(0, 0, 5, 0);
                gbc_panel.gridx = 1;
                gbc_panel.gridy = 5;
                getContentPane().add(panel, gbc_panel);
                panel.setLayout(new GridLayout(0, 3, 0, 0));
                
                dayField = new JSpinner();
                panel.add(dayField);
                
                monthField = new JSpinner();
                panel.add(monthField);
                
                yearField = new JSpinner();
                panel.add(yearField);
        
                emailLabel = new JLabel("Correo Electrónico:");
                GridBagConstraints gbc_emailLabel = new GridBagConstraints();
                gbc_emailLabel.fill = GridBagConstraints.BOTH;
                gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
                gbc_emailLabel.gridx = 0;
                gbc_emailLabel.gridy = 6;
                getContentPane().add(emailLabel, gbc_emailLabel);
        emailField = new JTextField();
        GridBagConstraints gbc_emailField = new GridBagConstraints();
        gbc_emailField.fill = GridBagConstraints.BOTH;
        gbc_emailField.insets = new Insets(0, 0, 5, 0);
        gbc_emailField.gridx = 1;
        gbc_emailField.gridy = 6;
        getContentPane().add(emailField, gbc_emailField);
        
        // Campos específicos para Cliente
        lastNameLabel = new JLabel("Apellido:");
        GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
        gbc_lastNameLabel.fill = GridBagConstraints.BOTH;
        gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lastNameLabel.gridx = 0;
        gbc_lastNameLabel.gridy = 7;
        getContentPane().add(lastNameLabel, gbc_lastNameLabel);
        lastNameField = new JTextField();
        GridBagConstraints gbc_lastNameField = new GridBagConstraints();
        gbc_lastNameField.fill = GridBagConstraints.BOTH;
        gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
        gbc_lastNameField.gridx = 1;
        gbc_lastNameField.gridy = 7;
        getContentPane().add(lastNameField, gbc_lastNameField);
        
        nationalityLabel = new JLabel("Nacionalidad:");
        nationalityLabel.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_nationalityLabel = new GridBagConstraints();
        gbc_nationalityLabel.fill = GridBagConstraints.BOTH;
        gbc_nationalityLabel.insets = new Insets(0, 0, 5, 5);
        gbc_nationalityLabel.gridx = 0;
        gbc_nationalityLabel.gridy = 8;
        getContentPane().add(nationalityLabel, gbc_nationalityLabel);
        nationalityField = new JTextField();
        GridBagConstraints gbc_nationalityField = new GridBagConstraints();
        gbc_nationalityField.fill = GridBagConstraints.BOTH;
        gbc_nationalityField.insets = new Insets(0, 0, 5, 0);
        gbc_nationalityField.gridx = 1;
        gbc_nationalityField.gridy = 8;
        getContentPane().add(nationalityField, gbc_nationalityField);
                        
        documentTypeLabel = new JLabel("Tipo de Documento:");
        GridBagConstraints gbc_documentTypeLabel = new GridBagConstraints();
        gbc_documentTypeLabel.fill = GridBagConstraints.BOTH;
        gbc_documentTypeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_documentTypeLabel.gridx = 0;
        gbc_documentTypeLabel.gridy = 9;
        getContentPane().add(documentTypeLabel, gbc_documentTypeLabel);
                        documentTypeField = new JComboBox<>(TipoDocumento.values());
                        GridBagConstraints gbc_documentTypeField = new GridBagConstraints();
                        gbc_documentTypeField.fill = GridBagConstraints.BOTH;
                        gbc_documentTypeField.insets = new Insets(0, 0, 5, 0);
                        gbc_documentTypeField.gridx = 1;
                        gbc_documentTypeField.gridy = 9;
                        getContentPane().add(documentTypeField, gbc_documentTypeField);
                
                        documentNumberLabel = new JLabel("Número de Documento:");
                        GridBagConstraints gbc_documentNumberLabel = new GridBagConstraints();
                        gbc_documentNumberLabel.fill = GridBagConstraints.BOTH;
                        gbc_documentNumberLabel.insets = new Insets(0, 0, 5, 5);
                        gbc_documentNumberLabel.gridx = 0;
                        gbc_documentNumberLabel.gridy = 10;
                        getContentPane().add(documentNumberLabel, gbc_documentNumberLabel);
                documentNumberField = new JTextField();
                GridBagConstraints gbc_documentNumberField = new GridBagConstraints();
                gbc_documentNumberField.fill = GridBagConstraints.BOTH;
                gbc_documentNumberField.insets = new Insets(0, 0, 5, 0);
                gbc_documentNumberField.gridx = 1;
                gbc_documentNumberField.gridy = 10;
                getContentPane().add(documentNumberField, gbc_documentNumberField);
        
                // Campos específicos para Aerolínea
                airlineDescriptionLabel = new JLabel("Descripción de la Aerolinea:");
                GridBagConstraints gbc_airlineDescriptionLabel = new GridBagConstraints();
                gbc_airlineDescriptionLabel.fill = GridBagConstraints.BOTH;
                gbc_airlineDescriptionLabel.insets = new Insets(0, 0, 5, 5);
                gbc_airlineDescriptionLabel.gridx = 0;
                gbc_airlineDescriptionLabel.gridy = 11;
                getContentPane().add(airlineDescriptionLabel, gbc_airlineDescriptionLabel);
        airlineDescriptionArea = new JTextArea();
        airlineDescriptionArea.setLineWrap(true);
        GridBagConstraints gbc_airlineDescriptionArea = new GridBagConstraints();
        gbc_airlineDescriptionArea.fill = GridBagConstraints.BOTH;
        gbc_airlineDescriptionArea.insets = new Insets(0, 0, 5, 0);
        gbc_airlineDescriptionArea.gridx = 1;
        gbc_airlineDescriptionArea.gridy = 11;
        getContentPane().add(airlineDescriptionArea, gbc_airlineDescriptionArea);
        
        websiteLinkLabel = new JLabel("Link a Sitio Web:");
        GridBagConstraints gbc_websiteLinkLabel = new GridBagConstraints();
        gbc_websiteLinkLabel.fill = GridBagConstraints.BOTH;
        gbc_websiteLinkLabel.insets = new Insets(0, 0, 5, 5);
        gbc_websiteLinkLabel.gridx = 0;
        gbc_websiteLinkLabel.gridy = 12;
        getContentPane().add(websiteLinkLabel, gbc_websiteLinkLabel);
        websiteLinkField = new JTextField();
        GridBagConstraints gbc_websiteLinkField = new GridBagConstraints();
        gbc_websiteLinkField.fill = GridBagConstraints.BOTH;
        gbc_websiteLinkField.insets = new Insets(0, 0, 5, 0);
        gbc_websiteLinkField.gridx = 1;
        gbc_websiteLinkField.gridy = 12;
        getContentPane().add(websiteLinkField, gbc_websiteLinkField);
        
        // Botones
        submitButton = new JButton("Dar de Alta");
        
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.fill = GridBagConstraints.BOTH;
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 0;
        gbc_submitButton.gridy = 13;
        getContentPane().add(submitButton, gbc_submitButton);
        
        // Acciones de los botones
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
                createUser();
            }
        });
        cancelButton = new JButton("Cancelar");
        GridBagConstraints gbc_cancelButton = new GridBagConstraints();
        gbc_cancelButton.insets = new Insets(0, 0, 5, 0);
        gbc_cancelButton.fill = GridBagConstraints.BOTH;
        gbc_cancelButton.gridx = 1;
        gbc_cancelButton.gridy = 13;
        getContentPane().add(cancelButton, gbc_cancelButton);
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exception) {
                dispose(); // Cierra la ventana
            }
        });
        
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent exception) {
                        dispose(); // Cierra la ventana
                    }
                });
        
        modificarButton = new JButton("Modificar Usuario");
        modificarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent exception) {
        		modificarDatosUsuario(nicknameField.getText());
        	}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 14;
        getContentPane().add(modificarButton, gbc_btnNewButton);

        // Inicialmente ocultar los campos específicos
        toggleFields();
        
        setVisible(true);
    }

    private void toggleFields() {
        boolean isClient = userTypeComboBox.getSelectedItem().equals("Cliente");
        lastNameLabel.setVisible(isClient);
        nationalityLabel.setVisible(isClient);
        documentTypeLabel.setVisible(isClient);
        documentNumberLabel.setVisible(isClient);
        lastNameField.setVisible(isClient);
        nationalityField.setVisible(isClient);
        birthdayLabel.setVisible(isClient);
        documentTypeField.setVisible(isClient);
        documentNumberField.setVisible(isClient);
        dayField.setVisible(isClient);
        monthField.setVisible(isClient);
        yearField.setVisible(isClient);

        airlineDescriptionLabel.setVisible(!isClient);
        websiteLinkLabel.setVisible(!isClient);
        airlineDescriptionArea.setVisible(!isClient);
        websiteLinkField.setVisible(!isClient);
    }

    private void createUser() {
    	Fabrica fab = Fabrica.getInstancia();
    	IControladorUsuarios iCUsuario = fab.getControladorUsuario();
    	
        // Datos comunes para Usuario
        String tipoUsuario = userTypeComboBox.getSelectedItem().toString();
        String nickname = nicknameField.getText();
        String nombre = nameField.getText();
        String email = emailField.getText();
        char[] contrasenia_char = passwordField.getPassword();
        String contrasenia = new String(contrasenia_char);
        char[] contrasenia_confirm_char = passwordField_confirm.getPassword();
        String contrasenia_confirm = new String(contrasenia_confirm_char);
        // Datos adicionales para Cliente
        String apellido = lastNameField.getText();
        String diaString = dayField.getValue().toString();
        String mesString = monthField.getValue().toString();
        String anioString = yearField.getValue().toString();
        String nacionalidad = nationalityField.getText();
        String tipoDocumentoString = documentTypeField.getSelectedItem().toString();
        String numeroDocumentoString = documentNumberField.getText();
        // Datos adicionales para Aerolínea
        String descripcion = airlineDescriptionArea.getText();
        String link = websiteLinkField.getText();
        
        Arrays.fill(contrasenia_confirm_char, '\0');
        Arrays.fill(contrasenia_char, '\0');

        // Chequeos de campos obligatorios
        String msg = "";
        
        if (nickname.isEmpty()) msg += "Nickname, ";
        if (nombre.isEmpty()) msg += "Nombre, ";
        if (email.isEmpty()) msg += "Correo electrónico, ";
        if (contrasenia.isEmpty()) msg += "Contrasenia";
        if (contrasenia_confirm.isEmpty()) msg += "Confirmacion de Contrasenia";

        if (tipoUsuario.equals("Cliente")) {
            if (apellido.isEmpty()) msg += "Apellido, ";
            if (diaString == "0" && mesString == "0" && anioString == "0") msg += "Fecha de nacimiento, ";
            if( tipoDocumentoString.isEmpty()) msg += "Tipo de documento, ";
            if (numeroDocumentoString.isEmpty()) msg += "Número de documento, ";
            
            // Chequeo integridad
            if(!validTipoDocumento(tipoDocumentoString, "Tipo de documento")) return;
            if(!validFechaNacimiento(diaString, mesString, anioString, "Fecha de nacimiento")) return;
            if(!validNumDocumento(numeroDocumentoString, tipoDocumentoString)) return;
            if(!validContrasenia(contrasenia, contrasenia_confirm)) return;
            if(!validEmail(email)) return;
            
            Date fechaNacimiento = numbersToDate( diaString, mesString, anioString);
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(tipoDocumentoString);

            if(!msg.isEmpty()) {
            	msg = msg.substring(0, msg.length() - 2); // Saca coma y espacio final
            	// Mensaje de error:
            	JOptionPane.showMessageDialog(this, "Los campos " +  msg  + " son obligatorios.");
            	return;
            }
            else {
            	try {
					iCUsuario.ingresarDatosCliente(nickname, nombre, email, apellido, fechaNacimiento, nacionalidad, tipoDocumento, numeroDocumentoString, contrasenia, contrasenia_confirm ,new Date());
				} catch (InstanciaRepetida e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Vuelo", JOptionPane.ERROR_MESSAGE);
					return;
				}
            	resetFormulario();
            }
        }
        
        else if(tipoUsuario.equals("Aerolinea")) {
        	if (descripcion.isEmpty()) msg += "Descripcion, ";
        	if(!validEmail(email)) return;
        	if(!msg.isEmpty()) {
            	msg = msg.substring(0, msg.length() - 2); // Saca coma y espacio final
            	JOptionPane.showMessageDialog(this, "Los campos " +  msg  + " son obligatorios.");
            	return;
            }
            else {
            	try {
					iCUsuario.ingresarDatosAerolinea(nickname, nombre, email, descripcion, link,"","",new Date());
				} catch (InstanciaRepetida e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Usuario", JOptionPane.ERROR_MESSAGE);
					return;
				}
            	resetFormulario();
            }
        }
        
        JOptionPane.showMessageDialog(this, "Usuario '" + nickname + "' creado con éxito como " + tipoUsuario + ".");
        debugPrintUsuarios(nickname);

    }
    
    @SuppressWarnings("unused")
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
    
    private boolean validTipoDocumento(String texto, String campo) {
        try {
            TipoDocumento.valueOf(texto);
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, campo + "debe ser un tipo de documento valido", "Error de Validacion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean validNumDocumento(String texto, String tipo) {
    	
    	if( tipo == TipoDocumento.Cedula.toString()) {
    		if(texto.length() != 8) {
    			JOptionPane.showMessageDialog(this, "La cedula de identidad debe tener 8 digitos", "Error de Validacion",
                        JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
    		try {
                int num = Integer.parseInt(texto);
                if(num < 0) {
                    JOptionPane.showMessageDialog(this, "El numero de documento debe ser un numero positivo", "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El numero de documento debe ser un numero", "Error de Validacion",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
    	}
    	else if( tipo == TipoDocumento.Cedula.toString()) {
    		if(texto.length() != 9) {
    			JOptionPane.showMessageDialog(this, "El pasaporte debe tener 9 digitos", "Error de Validacion",
                        JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean validFechaNacimiento(String dia_s, String mes_s, String anio_s, String caso) {
        try {
            int dia = Integer.parseInt(dia_s);
            int mes = Integer.parseInt(mes_s);
            int anio = Integer.parseInt(anio_s);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 0 ||  anio > Year.now().getValue() ) {
                JOptionPane.showMessageDialog(this, caso + " debe ser una fecha valida");
                return false;
            } else {
                Calendar calendario = Calendar.getInstance();
                calendario.set(Calendar.YEAR, anio);
                calendario.set(Calendar.MONTH,mes-1);
                calendario.set(Calendar.DAY_OF_MONTH, dia);
                return true;
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Fecha invalida.");
            return false;
        }
    }
  
    
    private boolean validEmail(String mail){
        if(mail.contains("@") && mail.contains(".")){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(this, "El correo electronico debe tener un @ y un .", "Error de Validacion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean validContrasenia(String contra, String contra_confirm) {
    	if(contra.equals(contra_confirm)) {
    		return true;
    	}else {
    		return false;
    	}
    }


    private Date numbersToDate(String dia_s, String mes_s, String anio_s) {
        int dia = Integer.parseInt(dia_s);
        int mes = Integer.parseInt(mes_s);
        int anio = Integer.parseInt(anio_s);
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH,mes-1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        Date fechaNacimiento = calendario.getTime();
        return fechaNacimiento;
    }
    
    private void resetFormulario() {
    	nicknameField.setText("");
        nameField.setText("");
        emailField.setText("");
        lastNameField.setText("");
        nationalityField.setText("");
        passwordField.setText("");
        passwordField_confirm.setText("");
        documentNumberField.setText("");
        airlineDescriptionArea.setText("");
        websiteLinkField.setText("");
        dayField.setValue(0);
        monthField.setValue(0);
        yearField.setValue(0);
    }
    
    private void debugPrintUsuarios(String nickname){
    	Fabrica fab = Fabrica.getInstancia();
    	IControladorUsuarios iCUsuario = fab.getControladorUsuario();
    	try {
    		DTUsuario dtUsuario = iCUsuario.mostrarInfoUsuarios(nickname);
    		if(dtUsuario instanceof DTCliente ) {
    			DTCliente dtCliente = (DTCliente) dtUsuario;
    		}
    		else if(dtUsuario instanceof DTAerolinea) {
    			DTAerolinea dtAerolinea = (DTAerolinea) dtUsuario;
    		}
    	}catch (InstanciaNoExiste | DatoInvalido exception){
    		System.out.println("No se agrego bien");
    	}
    	
    	
    	Set<String> listaDeUsuarios = iCUsuario.listarUsuarios();
    	for( String usuario : listaDeUsuarios) {
    		DTUsuario infoUsuario;
    		try {
    			infoUsuario = iCUsuario.mostrarInfoUsuarios(usuario);
    		} catch (InstanciaNoExiste | DatoInvalido exception) {
    			exception.printStackTrace();
    		}
    	}
    	
    }
    
    @SuppressWarnings("unused")
	private void actualizarCaso() {
    	changeFormSetup(0);
    }
    
    // Modificar usuario
    
    public void changeFormSetup(int modo) {
    	//Modo alta usuario
    	if(modo == 0) {
    		nicknameField.setEditable(true);
    		emailField.setEditable(true);
    		submitButton.setVisible(true);
    		modificarButton.setVisible(false);
    		
    	}
    	//Modo modificar usuario
    	else if(modo == 1){
    		nicknameField.setEditable(false);
    		emailField.setEditable(false);
    		submitButton.setVisible(false);
    		modificarButton.setVisible(true);
    	}
    }
    
    ModificarUsuario miputopadre = null;
    private JLabel lblNewLabel_contrasenia_confir;
    private JLabel lblNewLabel_contrasenia;
    private JPasswordField passwordField;
    private JPasswordField passwordField_confirm;
    public void cargarDatosAModificarCliente(DTUsuario usuario, ModificarUsuario miputopadre) {
    		this.miputopadre = miputopadre;
    		this.submitButton.setVisible(false);
    		this.userTypeComboBox.setEnabled(false);
    		if (usuario instanceof DTCliente) {
    			DTCliente usu = (DTCliente) usuario;
	    		nicknameField.setText(usu.getNickname());
	    		nameField.setText(usu.getNombre());
	    		emailField.setText(usu.getMail());
	    		lastNameField.setText(((DTCliente) usu).getApellido());
	    	    nationalityField.setText(usu.getNacionalidad());;
	    	    documentNumberField.setText(usu.getNroDocumento());
	    	    
	    	    Date fechaNacimiento = usu.getFechaNacimiento();
	    	    Calendar calendar = Calendar.getInstance();
	    	    calendar.setTime(fechaNacimiento);
	    	    int dia = calendar.get(Calendar.DAY_OF_MONTH);
	    	    int mes = calendar.get(Calendar.MONTH);
	    	    int anio = calendar.get(Calendar.YEAR);
	    	    
	    	    
	    	    dayField.setValue(dia);
	    	    monthField.setValue(mes);
	    	    yearField.setValue(anio);
	    	} else {
	    		DTAerolinea usu = (DTAerolinea) usuario;
	    		userTypeComboBox.setSelectedItem("Aerolinea");
	    		toggleFields();
	    		nicknameField.setText(usu.getNickname());
	    		nameField.setText(usu.getNombre());
	    		emailField.setText(usu.getMail());
	    		airlineDescriptionArea.setText(usu.getDescripcion());
	    		websiteLinkField.setText(usu.getLink());
	    	}
    		
    }
    
    
    public void modificarDatosUsuario(String nicknameOriginal) {
    	Fabrica fab = Fabrica.getInstancia();
    	IControladorUsuarios contUsuario = fab.getControladorUsuario();
    	// Datos comunes para Usuario
        String tipoUsuario = userTypeComboBox.getSelectedItem().toString();
        String nickname = nicknameField.getText();
        String nombre = nameField.getText();
        String email = emailField.getText();
        // Datos adicionales para Cliente
        String apellido = lastNameField.getText();
        String diaString = dayField.getValue().toString();
        String mesString = monthField.getValue().toString();
        String anioString = yearField.getValue().toString();
        String nacionalidad = nationalityField.getText();
        String tipoDocumentoString = documentTypeField.getSelectedItem().toString();
        String numeroDocumentoString = documentNumberField.getText();
        // Datos adicionales para Aerolínea
        String descripcion = airlineDescriptionArea.getText();
        String link = websiteLinkField.getText();

        // Chequeos de campos obligatorios
        String msg = "";
        if (nombre.isEmpty()) msg += "Nombre, ";
        if(tipoUsuario.equals("Cliente")) {
            if (apellido.isEmpty()) msg += "Apellido, ";
            if (diaString == "0" && mesString == "0" && anioString == "0") msg += "Fecha de nacimiento, ";
            if(tipoDocumentoString.isEmpty()) msg += "Tipo de documento, ";
            if (numeroDocumentoString.isEmpty()) msg += "Número de documento, ";
            
            // Chequeo integridad
            if(!validTipoDocumento(tipoDocumentoString, "Tipo de documento")) return;
            if(!validFechaNacimiento(diaString, mesString, anioString, "Fecha de nacimiento")) return;
            if(!validNumDocumento(numeroDocumentoString, tipoDocumentoString)) return;
            if(!validEmail(email)) return;
            
            Date fechaNacimiento = numbersToDate( diaString, mesString, anioString);            	
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(tipoDocumentoString);

            if(!msg.isEmpty()) {
            	msg = msg.substring(0, msg.length() - 2); // Saca coma y espacio final
            	// Mensaje de error:
            	JOptionPane.showMessageDialog(this, "Los campos " +  msg  + " son obligatorios.");
            	return;
            } else {
				try {
					contUsuario.modificarCliente(nicknameOriginal,"", nombre, apellido, fechaNacimiento, nacionalidad, tipoDocumento, numeroDocumentoString);
				} catch (DatoInvalido | InstanciaNoExiste e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Usuario", JOptionPane.ERROR_MESSAGE);
				}
            }
        }
        else if(tipoUsuario.equals("Aerolinea")) {
        	if (descripcion.isEmpty()) msg += "Descripcion, ";
        	if(!validEmail(email)) return;
        	if(!msg.isEmpty()) {
            	msg = msg.substring(0, msg.length() - 2); // Saca coma y espacio final
            	JOptionPane.showMessageDialog(this, "Los campos " +  msg  + " son obligatorios.");
            	return;
            }
            else {
            	try {
            		contUsuario.modificarAerolinea(nicknameOriginal, nombre, descripcion, link,"");
				} catch (DatoInvalido | InstanciaNoExiste e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Usuario", JOptionPane.ERROR_MESSAGE);

				}
            	resetFormulario();
            }
        }
            
            JOptionPane.showMessageDialog(this, "Usuario '" + nickname + "' fue modificado correctamente.");
            setVisible(false);
            if(miputopadre != null) 
            	miputopadre.setVisible(false);
            
        	resetFormulario();
    }
        
    	
    
    
}

