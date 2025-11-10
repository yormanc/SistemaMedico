

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import enums.UserRole;
import models.Appointment;
import models.Patient;
import models.Doctor;
import services.MedicSystemService;

/**
 * Formulario genérico para ver el historial de citas de un Paciente o un Doctor.
 */
public class FrmViewHistory extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private final UserRole targetRole;
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    // Componentes
    private JTable jTableHistory;
    private JTextField jtfTargetId;
    private JLabel jlblTarget;
    private JLabel jlblTargetName;
    private JButton jbtnSearch;

    /**
     * @param medicService El servicio principal del sistema.
     * @param targetRole Indica si se buscará el historial de un PATIENT o un DOCTOR.
     */
    public FrmViewHistory(MedicSystemService medicService, UserRole targetRole) {
        this.medicService = medicService;
        this.targetRole = targetRole;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Historial de Citas - " + targetRole.toString());
    }

    private void initComponents() {
        JPanel jPanelMain = new JPanel(new BorderLayout(10, 10));
        JPanel jPanelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel jPanelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelMain.setBackground(new Color(245, 245, 245));
        jPanelSearch.setBackground(new Color(245, 245, 245));
        jPanelInfo.setBackground(new Color(245, 245, 245));
        
        // Título dinámico
        String roleStr = targetRole == UserRole.PATIENT ? "Paciente" : "Doctor";
        String colorHex = targetRole == UserRole.PATIENT ? "#E67E22" : "#1ABC9C";

        JLabel jlblTitulo = new JLabel("⚕️ Historial de Citas por " + roleStr);
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(Color.decode(colorHex));
        jPanelMain.add(jlblTitulo, BorderLayout.NORTH);
        
        // --- Panel de Búsqueda ---
        jtfTargetId = new JTextField(10);
        jbtnSearch = new JButton("Buscar Historial");
        jbtnSearch.addActionListener(e -> jbtnSearchActionPerformed());
        
        jPanelSearch.add(new JLabel("Ingrese ID de " + roleStr + ":"));
        jPanelSearch.add(jtfTargetId);
        jPanelSearch.add(jbtnSearch);
        
        // --- Panel de Información del Usuario Encontrado ---
        jlblTarget = new JLabel(roleStr + " Encontrado:");
        jlblTarget.setFont(jlblTarget.getFont().deriveFont(Font.BOLD));
        jlblTargetName = new JLabel("---");
        
        jPanelInfo.add(jlblTarget);
        jPanelInfo.add(jlblTargetName);

        // Combinar búsqueda e información
        JPanel jPanelTop = new JPanel(new BorderLayout());
        jPanelTop.add(jPanelSearch, BorderLayout.NORTH);
        jPanelTop.add(jPanelInfo, BorderLayout.CENTER);
        jPanelTop.add(new JSeparator(), BorderLayout.SOUTH);
        jPanelMain.add(jPanelTop, BorderLayout.CENTER);
        
        // --- Tabla de Historial ---
        jTableHistory = new JTable();
        jTableHistory.setRowHeight(25);
        jTableHistory.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTableHistory.setDefaultEditor(Object.class, null); // Tabla no editable
        
        JScrollPane jScrollPaneTable = new JScrollPane(jTableHistory);
        jScrollPaneTable.setBorder(BorderFactory.createTitledBorder("Citas Históricas"));
        
        // Ajustar el layout principal para incluir la tabla
        JPanel jPanelContent = new JPanel(new BorderLayout(10, 10));
        jPanelContent.add(jPanelTop, BorderLayout.NORTH);
        jPanelContent.add(jScrollPaneTable, BorderLayout.CENTER);
        jPanelMain.add(jPanelContent, BorderLayout.CENTER);
        
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
        setSize(850, 550);
    }
    
    /**
     * Maneja la acción de búsqueda del historial.
     */
    private void jbtnSearchActionPerformed() {
        jlblTargetName.setText("---");
        int id;
        try {
            id = Integer.parseInt(jtfTargetId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Por favor, ingrese un ID válido (número entero).", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Appointment> history = null;
        String name = "No encontrado";

        if (targetRole == UserRole.PATIENT) {
            Patient patient = medicService.getPatientRepository().searchById(id);
            if (patient != null) {
                history = medicService.viewPatientHistory(patient);
                name = patient.getFullName();
            }
        } else if (targetRole == UserRole.DOCTOR) {
            Doctor doctor = medicService.getDoctorRepository().searchById(id);
            if (doctor != null) {
                history = medicService.viewDoctorHistory(doctor);
                name = doctor.getFullName();
            }
        }
        
        jlblTargetName.setText(name);
        
        if (history != null) {
            loadTable(history);
            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ℹ️ " + name + " no tiene citas registradas.", "Historial Vacío", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✅ Historial de citas cargado para " + name + ".", "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            loadTable(List.of()); // Vaciar la tabla
            JOptionPane.showMessageDialog(this, "❌ ID " + id + " de " + targetRole.toString() + " no encontrado.", "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga la lista de citas en la JTable.
     */
    private void loadTable(List<Appointment> appointments) {
        String[] columnNames = {"ID Cita", "Fecha y Hora", "Paciente", "Doctor", "Estado", "Diagnóstico/Motivo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Appointment appt : appointments) {
            Object[] rowData = new Object[] {
                appt.getAppointmentId(),
                appt.getDateTime().format(DATE_TIME_FORMATTER),
                appt.getPatient().getFullName(),
                appt.getDoctor().getFullName(),
                appt.getStatus().toString(),
                appt.getDiagnostic()
            };
            model.addRow(rowData);
        }
        
        jTableHistory.setModel(model);
        jTableHistory.getTableHeader().setReorderingAllowed(false);
        
        // Ajuste básico de columnas
        jTableHistory.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
    }
}
