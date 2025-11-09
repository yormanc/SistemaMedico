import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import models.Patient;
import models.Doctor;
import Services.MedicSystemService;

/**
 * Formulario para Agendar una Nueva Cita Médica
 */
public class FrmAddAppointment extends javax.swing.JFrame {

    private final MedicSystemService medicService;

    // Componentes de formulario
    private javax.swing.JComboBox<Patient> jcbPatient;
    private javax.swing.JComboBox<Doctor> jcbDoctor;
    private javax.swing.JTextField jtfDate;
    private javax.swing.JTextField jtfTime;
    private javax.swing.JTextArea jtaDiagnostic;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnCancel;

    public FrmAddAppointment(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        loadComboboxData(); // Carga pacientes y doctores al iniciar
        setTitle("Agendar Nueva Cita");
    }

    private void initComponents() {
        // Inicialización de componentes principales
        JPanel jPanelMain = new JPanel(new BorderLayout(10, 10));
        JPanel jPanelForm = new JPanel(new GridBagLayout());
        JPanel jPanelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // Estilo general
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanelMain.setBackground(new Color(245, 245, 245));
        jPanelForm.setBackground(new Color(245, 245, 245));
        jPanelButtons.setBackground(new Color(245, 245, 245));
        
        // Configuración de GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel jlblTitulo = new JLabel("➕ Agendar Nueva Cita");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new Color(46, 204, 113));
        
        // Campos del formulario
        jcbPatient = new JComboBox<>();
        jcbDoctor = new JComboBox<>();
        jtfDate = new JTextField(10);
        jtfTime = new JTextField(10);
        jtaDiagnostic = new JTextArea(4, 20);
        JScrollPane jspDiagnostic = new JScrollPane(jtaDiagnostic);
        
        // Establecer un formato de ejemplo
        jtfDate.setToolTipText("Formato: YYYY-MM-DD");
        jtfTime.setToolTipText("Formato: HH:MM (24h)");
        
        // Etiqueta y Campo Paciente
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        jPanelForm.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        jPanelForm.add(jcbPatient, gbc);
        
        // Etiqueta y Campo Doctor
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        jPanelForm.add(new JLabel("Doctor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        jPanelForm.add(jcbDoctor, gbc);
        
        // Etiqueta y Campo Fecha
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        jPanelForm.add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        jPanelForm.add(jtfDate, gbc);
        
        // Etiqueta y Campo Hora
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        jPanelForm.add(new JLabel("Hora (HH:MM):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        jPanelForm.add(jtfTime, gbc);
        
        // Etiqueta y Campo Diagnóstico/Motivo
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.NORTH;
        jPanelForm.add(new JLabel("Motivo/Diagnóstico:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.CENTER;
        jPanelForm.add(jspDiagnostic, gbc);
        
        // Botones
        jbtnSave = new JButton("Guardar Cita");
        jbtnSave.setBackground(new Color(46, 204, 113));
        jbtnSave.setForeground(Color.WHITE);
        jbtnSave.addActionListener(evt -> jbtnSaveActionPerformed());
        
        jbtnCancel = new JButton("Cancelar");
        jbtnCancel.setBackground(new Color(189, 195, 199));
        jbtnCancel.setForeground(new Color(52, 73, 94));
        jbtnCancel.addActionListener(evt -> dispose());
        
        jPanelButtons.add(jbtnCancel);
        jPanelButtons.add(jbtnSave);

        // Armar el Main Panel
        jPanelMain.add(jlblTitulo, BorderLayout.NORTH);
        jPanelMain.add(new JSeparator(), BorderLayout.CENTER);
        jPanelMain.add(jPanelForm, BorderLayout.CENTER);
        jPanelMain.add(jPanelButtons, BorderLayout.SOUTH);

        // Configuración de la ventana
        setContentPane(jPanelMain);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }
    
    /**
     * Carga los datos de Pacientes y Doctores en los JComboBox
     */
    private void loadComboboxData() {
        // Cargar Pacientes
        List<Patient> patients = medicService.getPatientRepository().getAll();
        jcbPatient.removeAllItems();
        if (patients.isEmpty()) {
            jcbPatient.addItem(new Patient("No hay Pacientes", 0, "", null));
            jcbPatient.setEnabled(false);
        } else {
            patients.forEach(jcbPatient::addItem);
        }
        
        // Cargar Doctores
        List<Doctor> doctors = medicService.getDoctorRepository().getAll();
        jcbDoctor.removeAllItems();
        if (doctors.isEmpty()) {
            jcbDoctor.addItem(new Doctor("No hay Doctores", 0, "", null, null));
            jcbDoctor.setEnabled(false);
        } else {
            doctors.forEach(jcbDoctor::addItem);
        }
    }

    /**
     * Maneja el evento de guardar la nueva cita
     */
    private void jbtnSaveActionPerformed() {
        Patient selectedPatient = (Patient) jcbPatient.getSelectedItem();
        Doctor selectedDoctor = (Doctor) jcbDoctor.getSelectedItem();
        String dateStr = jtfDate.getText().trim();
        String timeStr = jtfTime.getText().trim();
        String diagnostic = jtaDiagnostic.getText().trim();

        if (selectedPatient == null || selectedDoctor == null || dateStr.isEmpty() || timeStr.isEmpty() || diagnostic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Concatenar y parsear Fecha y Hora
            String dateTimeStr = dateStr + " " + timeStr;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            
            // Simulación de la utilidad DateTimeConverter. Este método debe existir en tu proyecto
            // LocalDateTime dateTime = DateTimeConverter.toLocalDateTime(dateStr, timeStr); 

            // Llamar al servicio para programar la cita
            boolean success = medicService.scheduleAppointment(dateTime, selectedPatient, selectedDoctor, diagnostic);

            if (success) {
                JOptionPane.showMessageDialog(this, " Cita agendada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, " Error al agendar la cita. Verifique la disponibilidad.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            }

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, 
                " Error de formato en Fecha/Hora.\nUse YYYY-MM-DD para la fecha y HH:MM para la hora.", 
                "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                " Ocurrió un error inesperado: " + e.getMessage(), 
                "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
