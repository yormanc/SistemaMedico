import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.Doctor;
import models.Speciality;
import services.MedicSystemService;
import repositories.SpecialityRepository; // Necesario para cargar las especialidades


/**
 * Formulario para buscar, modificar y eliminar Doctores.
 */
public class FrmManageDoctor extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private Doctor currentDoctor = null; // Doctor actualmente cargado

    // Componentes de Búsqueda
    private JTextField jtfSearchId;
    private JButton jbtnSearch;

    // Componentes de Detalles y Modificación
    private JTextField jtfFullName;
    private JTextField jtfAge;
    private JTextField jtfEmail;
    private JComboBox<Speciality> jcbSpeciality;
    private JButton jbtnModify;
    private JButton jbtnDelete;
    private JButton jbtnCancel;

    public FrmManageDoctor(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestionar Doctor");
        setFieldsEditable(false); // Inicialmente, los campos están deshabilitados
    }

    private void initComponents() {
        // Layout principal y paneles
        JPanel jPanelMain = new JPanel(new BorderLayout(15, 15));
        JPanel jPanelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel jPanelForm = new JPanel(new GridBagLayout());
        JPanel jPanelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        jPanelMain.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        jPanelMain.setBackground(new Color(245, 245, 245));
        jPanelSearch.setBackground(new Color(245, 245, 245));
        jPanelForm.setBackground(new Color(245, 245, 245));
        jPanelButtons.setBackground(new Color(245, 245, 245));

        // Título
        JLabel jlblTitulo = new JLabel(" Gestionar Doctor (Buscar, Modificar, Eliminar)");
        jlblTitulo.setFont(new Font("Segoe UI", 1, 22));
        jlblTitulo.setForeground(new Color(52, 152, 219));
        
        // --- Panel de Búsqueda (Norte) ---
        jtfSearchId = new JTextField(10);
        jbtnSearch = new JButton("Buscar por ID");
        jbtnSearch.addActionListener(e -> jbtnSearchActionPerformed());
        jbtnSearch.setBackground(new Color(52, 152, 219));
        jbtnSearch.setForeground(Color.WHITE);
        
        jPanelSearch.add(new JLabel("ID Doctor:"));
        jPanelSearch.add(jtfSearchId);
        jPanelSearch.add(jbtnSearch);
        
        // Contenedor principal para título y búsqueda
        JPanel jPanelTop = new JPanel(new BorderLayout());
        jPanelTop.setBackground(new Color(245, 245, 245));
        jPanelTop.add(jlblTitulo, BorderLayout.NORTH);
        jPanelTop.add(new JSeparator(), BorderLayout.CENTER);
        jPanelTop.add(jPanelSearch, BorderLayout.SOUTH);
        
        // --- Panel de Detalles (Centro) ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Componentes de detalle
        jtfFullName = new JTextField(20);
        jtfAge = new JTextField(20);
        jtfEmail = new JTextField(20);
        jcbSpeciality = new JComboBox<>();
        
        // Helper para agregar filas al GridBagLayout
        int row = 0;
        row = addFormField(jPanelForm, gbc, row, "Nombre Completo:", jtfFullName);
        row = addFormField(jPanelForm, gbc, row, "Edad:", jtfAge);
        row = addFormField(jPanelForm, gbc, row, "Email:", jtfEmail);
        row = addFormField(jPanelForm, gbc, row, "Especialidad:", jcbSpeciality);
        
        // --- Botones de Acción (Sur) ---
        jbtnModify = new JButton(" Modificar Datos");
        jbtnModify.addActionListener(e -> jbtnModifyActionPerformed());
        jbtnModify.setBackground(new Color(46, 204, 113));
        jbtnModify.setForeground(Color.WHITE);
        
        jbtnDelete = new JButton("Eliminar Doctor");
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
        jPanelMain.add(jPanelTop, BorderLayout.NORTH);
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
        return row + 1;
    }

    /**
     * Habilita/Deshabilita los campos de modificación
     */
    private void setFieldsEditable(boolean editable) {
        jtfFullName.setEditable(editable);
        jtfAge.setEditable(editable);
        jtfEmail.setEditable(editable);
        jcbSpeciality.setEnabled(editable);
        jbtnModify.setEnabled(editable);
        jbtnDelete.setEnabled(editable);
    }
    
    /**
     * Carga los datos del doctor en los campos
     */
    private void loadDoctorData(Doctor doctor) {
        if (doctor == null) {
            clearFields();
            setFieldsEditable(false);
            return;
        }
        
        this.currentDoctor = doctor;
        jtfFullName.setText(doctor.getFullName());
        jtfAge.setText(String.valueOf(doctor.getAge()));
        jtfEmail.setText(doctor.getEmail());
        
        loadSpecialities(doctor.getSpeciality()); // Carga las especialidades seleccionando la del doctor
        
        setFieldsEditable(true);
    }

    /**
     * Limpia todos los campos de detalle
     */
    private void clearFields() {
        currentDoctor = null;
        jtfFullName.setText("");
        jtfAge.setText("");
        jtfEmail.setText("");
        jcbSpeciality.removeAllItems();
    }
    
    /**
     * Carga todas las especialidades disponibles y selecciona la especialidad actual del doctor.
     */
    private void loadSpecialities(Speciality selectedSpeciality) {
        jcbSpeciality.removeAllItems();
        try {
            // Usar reflexión para acceder al repositorio si no hay un getter público
            SpecialityRepository specialityRepo = 
                (SpecialityRepository) medicService.getClass()
                    .getDeclaredField("specialityRepository").get(medicService);
            
            List<Speciality> specialities = specialityRepo.getAll();
            
            for (Speciality s : specialities) {
                jcbSpeciality.addItem(s);
                // Selecciona la especialidad actual del doctor
                if (selectedSpeciality != null && s.getSpecialityId() == selectedSpeciality.getSpecialityId()) {
                    jcbSpeciality.setSelectedItem(s);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar especialidades: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- MANEJADORES DE ACCIONES ---

    private void jbtnSearchActionPerformed() {
        clearFields();
        setFieldsEditable(false);
        int id;
        try {
            id = Integer.parseInt(jtfSearchId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, " Por favor, ingrese un ID de doctor válido (número entero).", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Doctor foundDoctor = medicService.getDoctorRepository().searchById(id); 

        if (foundDoctor != null) {
            loadDoctorData(foundDoctor);
            JOptionPane.showMessageDialog(this, " Doctor encontrado.", "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, " Doctor con ID " + id + " no encontrado.", "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnModifyActionPerformed() {
        if (currentDoctor == null) return;

        // 1. Obtener y validar nuevos datos
        String fullName = jtfFullName.getText().trim();
        String ageText = jtfAge.getText().trim();
        String email = jtfEmail.getText().trim();
        Speciality newSpeciality = (Speciality) jcbSpeciality.getSelectedItem();

        if (fullName.isEmpty() || ageText.isEmpty() || email.isEmpty() || newSpeciality == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La Edad debe ser un número entero positivo válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 2. Actualizar el modelo
        currentDoctor.setFullName(fullName);
        currentDoctor.setAge(age);
        currentDoctor.setEmail(email);
        currentDoctor.setSpecialty(newSpeciality); 
        
        // 3. Llamar al servicio de actualización
        boolean success = medicService.updateDoctor(currentDoctor); 

        if (success) {
            JOptionPane.showMessageDialog(this, "Doctor " + currentDoctor.getCredentials().getId() + " modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Recargar datos para confirmar la actualización
            loadDoctorData(currentDoctor);
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el doctor en el sistema.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnDeleteActionPerformed() {
        if (currentDoctor == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, 
            "⚠️ ¿Está seguro que desea eliminar al doctor " + currentDoctor.getFullName() + " (ID: " + currentDoctor.getCredentials().getId() + ")? Esta acción es irreversible.", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = medicService.removeDoctor(currentDoctor);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Doctor eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                setFieldsEditable(false);
                jtfSearchId.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el doctor. Asegúrese de que no tenga dependencias activas (e.g., citas).", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
