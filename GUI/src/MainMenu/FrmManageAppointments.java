import enums.AppointmentStatus;
import models.Appointment;
import services.MedicSystemService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formulario para Buscar, Modificar y Eliminar Citas
 */
public class FrmManageAppointments extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private Appointment currentAppointment = null; // Cita actualmente cargada
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Componentes del formulario
    private JTextField jtfAppointmentId;
    private JButton jbtnSearch;
    private JComboBox<AppointmentStatus> jcbStatus;
    private JTextArea jtaDiagnostic;
    private JLabel jlblPatientName;
    private JLabel jlblDoctorName;
    private JTextField jtfDateTime;
    private JButton jbtnModify;
    private JButton jbtnDelete;
    private JButton jbtnCancel;

    public FrmManageAppointments(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestionar Citas Médicas");
        setFieldsEditable(false); // Inicialmente, los campos están deshabilitados
    }

    private void initComponents() {
        // Layout principal y paneles
        JPanel jPanelMain = new JPanel(new BorderLayout(10, 10));
        JPanel jPanelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel jPanelDetails = new JPanel(new GridBagLayout());
        JPanel jPanelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelMain.setBackground(new Color(245, 245, 245));
        jPanelDetails.setBackground(new Color(245, 245, 245));
        jPanelButtons.setBackground(new Color(245, 245, 245));
        jPanelSearch.setBackground(new Color(245, 245, 245));

        // Título
        JLabel jlblTitulo = new JLabel("🔍 Modificar / Eliminar Cita");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new Color(52, 152, 219));
        
        // --- Panel de Búsqueda ---
        jtfAppointmentId = new JTextField(10);
        jbtnSearch = new JButton("Buscar Cita");
        jbtnSearch.addActionListener(e -> jbtnSearchActionPerformed());
        
        jPanelSearch.add(new JLabel("ID de Cita:"));
        jPanelSearch.add(jtfAppointmentId);
        jPanelSearch.add(jbtnSearch);
        
        // --- Panel de Detalles (GridBagLayout) ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // 1. Paciente
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        jPanelDetails.add(new JLabel("Paciente:"), gbc);
        jlblPatientName = new JLabel("---");
        jlblPatientName.setFont(jlblPatientName.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        jPanelDetails.add(jlblPatientName, gbc);

        // 2. Doctor
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        jPanelDetails.add(new JLabel("Doctor:"), gbc);
        jlblDoctorName = new JLabel("---");
        jlblDoctorName.setFont(jlblDoctorName.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        jPanelDetails.add(jlblDoctorName, gbc);
        
        // 3. Fecha y Hora
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        jPanelDetails.add(new JLabel("Fecha y Hora (YYYY-MM-DD HH:MM):"), gbc);
        jtfDateTime = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        jPanelDetails.add(jtfDateTime, gbc);

        // 4. Estado
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        jPanelDetails.add(new JLabel("Estado:"), gbc);
        jcbStatus = new JComboBox<>(AppointmentStatus.values());
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        jPanelDetails.add(jcbStatus, gbc);

        // 5. Diagnóstico/Motivo
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.NORTH;
        jPanelDetails.add(new JLabel("Motivo/Diagnóstico:"), gbc);
        jtaDiagnostic = new JTextArea(5, 20);
        JScrollPane jspDiagnostic = new JScrollPane(jtaDiagnostic);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.CENTER;
        jPanelDetails.add(jspDiagnostic, gbc);
        
        // --- Panel de Botones de Acción ---
        jbtnModify = new JButton("💾 Modificar Cita");
        jbtnModify.addActionListener(e -> jbtnModifyActionPerformed());
        jbtnModify.setBackground(new Color(46, 204, 113));
        jbtnModify.setForeground(Color.WHITE);

        jbtnDelete = new JButton("🗑️ Eliminar Cita");
        jbtnDelete.addActionListener(e -> jbtnDeleteActionPerformed());
        jbtnDelete.setBackground(new Color(231, 76, 60));
        jbtnDelete.setForeground(Color.WHITE);
        
        jbtnCancel = new JButton("Volver");
        jbtnCancel.addActionListener(e -> dispose());
        jbtnCancel.setBackground(new Color(189, 195, 199));
        jbtnCancel.setForeground(new Color(52, 73, 94));
        
        jPanelButtons.add(jbtnCancel);
        jPanelButtons.add(jbtnDelete);
        jPanelButtons.add(jbtnModify);
        
        // Armar el Main Panel
        jPanelMain.add(jlblTitulo, BorderLayout.NORTH);
        jPanelMain.add(jPanelSearch, BorderLayout.NORTH); // Reemplazamos el título por la búsqueda
        jPanelMain.add(new JSeparator(), BorderLayout.CENTER);
        jPanelMain.add(jPanelDetails, BorderLayout.CENTER);
        jPanelMain.add(jPanelButtons, BorderLayout.SOUTH);

        setContentPane(jPanelMain);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    /**
     * Habilita/Deshabilita los campos de modificación
     */
    private void setFieldsEditable(boolean editable) {
        jcbStatus.setEnabled(editable);
        jtaDiagnostic.setEditable(editable);
        jtfDateTime.setEditable(editable);
        jbtnModify.setEnabled(editable);
        jbtnDelete.setEnabled(editable);
    }
    
    /**
     * Carga los datos de la cita en los campos
     */
    private void loadAppointmentData(Appointment appointment) {
        if (appointment == null) {
            clearFields();
            setFieldsEditable(false);
            return;
        }
        
        this.currentAppointment = appointment;
        jlblPatientName.setText(appointment.getPatient().getFullName() + " (ID: " + appointment.getPatient().getCredentials().getId() + ")");
        jlblDoctorName.setText(appointment.getDoctor().getFullName() + " (ID: " + appointment.getDoctor().getCredentials().getId() + ")");
        jtfDateTime.setText(appointment.getDateTime().format(DATE_TIME_FORMATTER));
        jcbStatus.setSelectedItem(appointment.getStatus());
        jtaDiagnostic.setText(appointment.getDiagnostic());
        
        setFieldsEditable(true);
    }

    /**
     * Limpia todos los campos de detalle
     */
    private void clearFields() {
        currentAppointment = null;
        jlblPatientName.setText("---");
        jlblDoctorName.setText("---");
        jtfDateTime.setText("");
        jcbStatus.setSelectedIndex(0);
        jtaDiagnostic.setText("");
    }

    // --- MANEJADORES DE ACCIONES ---

    private void jbtnSearchActionPerformed() {
        try {
            int id = Integer.parseInt(jtfAppointmentId.getText().trim());
            // Se asume que AppoinmentRepository tiene un método findById(int id)
            Appointment foundAppointment = medicService.getAppointmentRepository().searchById(id); 

            if (foundAppointment != null) {
                loadAppointmentData(foundAppointment);
                JOptionPane.showMessageDialog(this, "✅ Cita encontrada.", "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                clearFields();
                JOptionPane.showMessageDialog(this, "❌ Cita con ID " + id + " no encontrada.", "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Por favor, ingrese un ID de cita válido (número entero).", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnModifyActionPerformed() {
        if (currentAppointment == null) {
            JOptionPane.showMessageDialog(this, "No hay una cita cargada para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 1. Obtener nuevos valores
            LocalDateTime newDateTime = LocalDateTime.parse(jtfDateTime.getText().trim(), DATE_TIME_FORMATTER);
            AppointmentStatus newStatus = (AppointmentStatus) jcbStatus.getSelectedItem();
            String newDiagnostic = jtaDiagnostic.getText().trim();
            
            // 2. Actualizar el modelo (solo los campos modificables)
            currentAppointment.setDateTime(newDateTime);
            currentAppointment.setStatus(newStatus);
            currentAppointment.setDiagnostic(newDiagnostic); 
            
            // 3. Llamar al servicio de actualización (se asume que update solo actualiza el registro en el repo)
            boolean success = medicService.updateAppointment(currentAppointment, newStatus); 

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Cita modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Opcional: Recargar los datos para confirmar
                loadAppointmentData(currentAppointment);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al modificar la cita en el sistema.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException e) {
             JOptionPane.showMessageDialog(this, "❌ Formato de Fecha/Hora inválido. Use YYYY-MM-DD HH:MM.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Ocurrió un error inesperado al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnDeleteActionPerformed() {
        if (currentAppointment == null) {
            JOptionPane.showMessageDialog(this, "No hay una cita cargada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "⚠️ ¿Está seguro que desea eliminar la cita ID " + currentAppointment.getAppointmentId() + "? Esta acción es irreversible.", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = medicService.removeAppointment(currentAppointment);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "🗑️ Cita eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                setFieldsEditable(false);
                jtfAppointmentId.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al eliminar la cita.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
