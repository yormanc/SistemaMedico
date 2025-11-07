

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import models.Appointment;
import Services.MedicSystemService;

/**
 * Formulario para listar todas las citas médicas registradas en el sistema.
 */
public class FrmListAppointments extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private JTable jTableAppointments;
    
    public FrmListAppointments(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Listado Completo de Citas");
        loadAppointmentsTable(); // Cargar los datos al iniciar la ventana
    }

    private void initComponents() {
        JPanel jPanelMain = new JPanel(new BorderLayout(10, 10));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelMain.setBackground(new Color(245, 245, 245));
        
        // Título
        JLabel jlblTitulo = new JLabel("📋 Listado de Todas las Citas");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new Color(155, 89, 182)); // Color púrpura
        jPanelMain.add(jlblTitulo, BorderLayout.NORTH);
        
        // Tabla
        jTableAppointments = new JTable();
        jTableAppointments.setRowHeight(25);
        jTableAppointments.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTableAppointments.setDefaultEditor(Object.class, null); // Hacer la tabla no editable
        
        JScrollPane jScrollPaneTable = new JScrollPane(jTableAppointments);
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
        setSize(950, 600); 
    }

    /**
     * Recupera todas las citas y las carga en la JTable.
     */
    private void loadAppointmentsTable() {
        // Nombres de las columnas
        String[] columnNames = {"ID", "Fecha y Hora", "Paciente", "Doctor", "Estado", "Diagnóstico/Motivo"};
        
        // Obtener datos del servicio (asumiendo que getAppointmentRepository().getAll() devuelve List<Appointment>)
        List<Appointment> appointments = medicService.getAppointmentRepository().getAll(); 

        // Preparar datos para JTable
        Object[][] data = new Object[appointments.size()][columnNames.length];
        
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appt = appointments.get(i);
            data[i][0] = appt.getAppointmentId();
            data[i][1] = appt.getDateTime().format(DATE_TIME_FORMATTER);
            data[i][2] = appt.getPatient().getFullName(); // Asume que existe getFullName()
            data[i][3] = appt.getDoctor().getFullName(); // Asume que existe getFullName()
            data[i][4] = appt.getStatus().toString();
            data[i][5] = appt.getDiagnostic();
        }
        
        // Crear el modelo de tabla y asignarlo
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTableAppointments.setModel(model);
        jTableAppointments.getTableHeader().setReorderingAllowed(false);
        
        // Ajuste de ancho de columnas para mejor visualización
        jTableAppointments.getColumnModel().getColumn(0).setPreferredWidth(30); 
        jTableAppointments.getColumnModel().getColumn(1).setPreferredWidth(120); 
        jTableAppointments.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableAppointments.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTableAppointments.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTableAppointments.getColumnModel().getColumn(5).setPreferredWidth(300);
        
        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Actualmente no hay citas registradas en el sistema.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
