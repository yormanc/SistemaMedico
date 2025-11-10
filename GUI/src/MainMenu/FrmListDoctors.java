import models.Doctor;
import services.MedicSystemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Formulario para listar todos los doctores registrados en el sistema.
 */
public class FrmListDoctors extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private JTable jTableDoctors;

    public FrmListDoctors(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Listado Completo de Doctores");
        loadDoctorsTable(); // Cargar los datos al iniciar la ventana
    }

    private void initComponents() {
        JPanel jPanelMain = new JPanel(new BorderLayout(10, 10));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelMain.setBackground(new Color(245, 245, 245));
        
        // Título
        JLabel jlblTitulo = new JLabel(" Listado de Todos los Doctores");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new Color(0, 102, 204)); // Color azul acorde al menú
        jPanelMain.add(jlblTitulo, BorderLayout.NORTH);
        
        // Tabla
        jTableDoctors = new JTable();
        jTableDoctors.setRowHeight(25);
        jTableDoctors.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTableDoctors.setDefaultEditor(Object.class, null); // Hacer la tabla no editable
        
        JScrollPane jScrollPaneTable = new JScrollPane(jTableDoctors);
        jScrollPaneTable.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        jPanelMain.add(jScrollPaneTable, BorderLayout.CENTER);
        
        // Botón
        JButton jbtnClose = new JButton("Cerrar");
        jbtnClose.addActionListener(e -> dispose());
        jbtnClose.setBackground(new Color(189, 195, 199));
        jbtnClose.setForeground(new Color(52, 73, 94));
        
        JPanel jPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jPanelButton.setBackground(new Color(245, 245, 245));
        jPanelButton.add(jbtnClose);
        
        jPanelMain.add(jPanelButton, BorderLayout.SOUTH);

        setContentPane(jPanelMain);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(850, 500); 
    }

    /**
     * Recupera todos los doctores y los carga en la JTable.
     */
    private void loadDoctorsTable() {
        // Nombres de las columnas
        String[] columnNames = {"ID", "Nombre", "Edad", "Email", "Especialidad"};
        
        // Obtener datos del servicio usando el repositorio público
        List<Doctor> doctors = medicService.getDoctorRepository().getAll(); 

        // Preparar datos para JTable
        Object[][] data = new Object[doctors.size()][columnNames.length];
        
        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            data[i][0] = d.getCredentials().getId();
            data[i][1] = d.getFullName();
            data[i][2] = d.getAge();
            data[i][3] = d.getEmail();
            // Asume que getSpeciality().getName() devuelve el nombre de la especialidad
            data[i][4] = d.getSpeciality().getName(); 
        }
        
        // Crear el modelo de tabla y asignarlo
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTableDoctors.setModel(model);
        jTableDoctors.getTableHeader().setReorderingAllowed(false);
        
        // Ajuste de ancho de columnas para mejor visualización
        jTableDoctors.getColumnModel().getColumn(0).setPreferredWidth(50); 
        jTableDoctors.getColumnModel().getColumn(2).setPreferredWidth(50);
        
        if (doctors.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Actualmente no hay doctores registrados en el sistema.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
