import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.Speciality;
import Services.MedicSystemService;

/**
 * Formulario para agregar un nuevo Doctor al sistema.
 */
public class FrmAddDoctor extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    // Componentes del formulario
    private JTextField jtfFullName;
    private JTextField jtfId;
    private JPasswordField jpfPassword;
    private JTextField jtfAge;
    private JTextField jtfEmail;
    private JComboBox<Speciality> jcbSpeciality;
    private JButton jbtnSave;
    private JButton jbtnCancel;

    public FrmAddDoctor(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Agregar Nuevo Doctor");
        loadSpecialities();
    }

    private void initComponents() {
        // Layout principal y paneles
        JPanel jPanelMain = new JPanel(new BorderLayout(15, 15));
        JPanel jPanelForm = new JPanel(new GridBagLayout());
        JPanel jPanelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        jPanelMain.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        jPanelMain.setBackground(new Color(245, 245, 245));
        jPanelForm.setBackground(new Color(245, 245, 245));
        jPanelButtons.setBackground(new Color(245, 245, 245));

        // Título
        JLabel jlblTitulo = new JLabel("➕ Registrar Nuevo Doctor");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new Color(0, 102, 204));
        
        // Configuración de GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // --- Campos del formulario ---
        jtfFullName = new JTextField(20);
        jtfId = new JTextField(20);
        jpfPassword = new JPasswordField(20);
        jtfAge = new JTextField(20);
        jtfEmail = new JTextField(20);
        jcbSpeciality = new JComboBox<>();

        // Helper para agregar filas al GridBagLayout
        int row = 0;
        row = addFormField(jPanelForm, gbc, row, "Nombre Completo:", jtfFullName);
        row = addFormField(jPanelForm, gbc, row, "ID/Cédula:", jtfId);
        row = addFormField(jPanelForm, gbc, row, "Contraseña:", jpfPassword);
        row = addFormField(jPanelForm, gbc, row, "Edad:", jtfAge);
        row = addFormField(jPanelForm, gbc, row, "Email:", jtfEmail);
        row = addFormField(jPanelForm, gbc, row, "Especialidad:", jcbSpeciality);

        // --- Botones ---
        jbtnSave = new JButton("Registrar Doctor");
        jbtnSave.setBackground(new Color(46, 204, 113));
        jbtnSave.setForeground(Color.WHITE);
        jbtnSave.setFocusPainted(false);
        jbtnSave.addActionListener(evt -> jbtnSaveActionPerformed());
        
        jbtnCancel = new JButton("Cancelar/Volver");
        jbtnCancel.setBackground(new Color(189, 195, 199));
        jbtnCancel.setForeground(new Color(52, 73, 94));
        jbtnCancel.setFocusPainted(false);
        jbtnCancel.addActionListener(evt -> dispose());
        
        jPanelButtons.add(jbtnCancel);
        jPanelButtons.add(jbtnSave);

        // Armar el Main Panel
        JPanel jPanelHeader = new JPanel(new BorderLayout());
        jPanelHeader.setBackground(new Color(245, 245, 245));
        jPanelHeader.add(jlblTitulo, BorderLayout.NORTH);
        jPanelHeader.add(new JSeparator(), BorderLayout.SOUTH);
        
        jPanelMain.add(jPanelHeader, BorderLayout.NORTH);
        jPanelMain.add(jPanelForm, BorderLayout.CENTER);
        jPanelMain.add(jPanelButtons, BorderLayout.SOUTH);

        // Configuración de la ventana
        setContentPane(jPanelMain);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }
    
    /** Helper para agregar un par etiqueta-campo al GridBagLayout */
    private int addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, Component field) {
        // Etiqueta
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(labelText), gbc);
        
        // Campo
        gbc.gridx = 1; gbc.gridy = row; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(field, gbc);
        return row + 1; // Retorna la siguiente fila
    }

    /**
     * Carga las especialidades disponibles en el JComboBox.
     */
    private void loadSpecialities() {
        try {
            // Usamos reflexión para obtener el SpecialityRepository, igual que en FrmDoctorMenu
            repositories.SpecialityRepository specialityRepo = 
                (repositories.SpecialityRepository) medicService.getClass()
                    .getDeclaredField("specialityRepository").get(medicService);
            
            List<Speciality> specialities = specialityRepo.getAll();
            jcbSpeciality.removeAllItems();

            if (specialities.isEmpty()) {
                jcbSpeciality.addItem(new Speciality(0, "NO DISPONIBLE", "No hay especialidades registradas"));
                jcbSpeciality.setEnabled(false);
                jbtnSave.setEnabled(false);
                JOptionPane.showMessageDialog(this, 
                    "Advertencia: No hay especialidades registradas. No se puede agregar un doctor.",
                    "Faltan Datos", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                specialities.forEach(jcbSpeciality::addItem);
                jcbSpeciality.setEnabled(true);
                jbtnSave.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar especialidades: " + e.getMessage(),
                "Error de Carga",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            jbtnSave.setEnabled(false);
        }
    }

    /**
     * Maneja el evento de guardar el nuevo doctor.
     */
    private void jbtnSaveActionPerformed() {
        // 1. Obtener y validar datos
        String fullName = jtfFullName.getText().trim();
        String idText = jtfId.getText().trim();
        String password = new String(jpfPassword.getPassword()).trim();
        String ageText = jtfAge.getText().trim();
        String email = jtfEmail.getText().trim();
        Speciality selectedSpeciality = (Speciality) jcbSpeciality.getSelectedItem();

        if (fullName.isEmpty() || idText.isEmpty() || password.isEmpty() || ageText.isEmpty() || email.isEmpty() || selectedSpeciality == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int id, age;
        try {
            id = Integer.parseInt(idText);
            age = Integer.parseInt(ageText);
            if (age <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y la Edad deben ser números enteros positivos válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Registrar el doctor
        boolean success = medicService.registerDoctor(fullName, id, password, age, email, selectedSpeciality);

        // 3. Mostrar resultado
        if (success) {
            JOptionPane.showMessageDialog(this, "Doctor '" + fullName + "' registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cierra la ventana tras el éxito
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el doctor. Es posible que el ID ya esté en uso.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }
}