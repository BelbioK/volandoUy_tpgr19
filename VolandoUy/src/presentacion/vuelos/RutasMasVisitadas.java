package presentacion.vuelos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.controladores.Fabrica;
import logica.datatypes.DTRutasDeVuelo;
import logica.interfaces.IControladorVuelos;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class RutasMasVisitadas extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Fabrica fabrica = Fabrica.getInstancia();
	private IControladorVuelos controlVue = fabrica.getControladorVuelos();
	
	public RutasMasVisitadas() {
		getContentPane().setEnabled(false);
        setClosable(true);
        setTitle("Rutas Mas Visitadas");
        setResizable(true);
        setMaximizable(true);
        setBounds(10, 10, 850, 216);
        getContentPane().setLayout(null);
        
        cargarDatosTabla();
        
        JLabel lblNewLabel = new JLabel("Rutas de Vuelo Mas Visitadas:");
        lblNewLabel.setBounds(36, 11, 236, 14);
        getContentPane().add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Cerrar");
        btnNewButton.setBounds(365, 158, 89, 23);
        btnNewButton.addActionListener(e -> {
            limpiarTabla();
            dispose();
        });
        getContentPane().add(btnNewButton);
        
        JLabel lblNewLabel_1 = new JLabel("Posicion:");
        lblNewLabel_1.setBounds(27, 42, 67, 14);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Ruta:");
        lblNewLabel_2.setBounds(172, 42, 46, 14);
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Aerolinea:");
        lblNewLabel_3.setBounds(297, 42, 73, 14);
        getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Origen:");
        lblNewLabel_4.setBounds(431, 42, 56, 14);
        getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Destino:");
        lblNewLabel_5.setBounds(562, 42, 46, 14);
        getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Visitas:");
        lblNewLabel_6.setBounds(704, 42, 46, 14);
        getContentPane().add(lblNewLabel_6);
    }
	
	public void cargarDatosTabla() {
        Set<DTRutasDeVuelo> rutasSet = controlVue.listarDTRutasDeVuelo();
        List<DTRutasDeVuelo> rutas = new ArrayList<>(rutasSet);
        rutas.sort(Comparator.comparingInt(DTRutasDeVuelo::getVisitas).reversed());

        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, 
                    new String[] {"Posición", "Nombre de Ruta", "Aerolínea", "Origen", "Destino", "Cantidad de Visitas"});
        
        int posicion = 1;
        for (int i = 0; i < Math.min(5, rutas.size()); i++) {
            DTRutasDeVuelo ruta = rutas.get(i);
            model.addRow(new Object[] {
                posicion++,
                ruta.getNombre(),
                ruta.getAerolinea(),
                ruta.getOrigen().getNombre(),
                ruta.getDestino().getNombre(),
                ruta.getVisitas() 
            });
        }
        
        if (table != null) {
            getContentPane().remove(table);
        }
        
        table = new JTable(model);
        table.setRowSelectionAllowed(false);
        table.setEnabled(false);
        table.setBounds(27, 67, 797, 80); 
        getContentPane().add(table);
        getContentPane().revalidate(); 
        getContentPane().repaint();
    }

    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 
    }
}
	