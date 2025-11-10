import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import models.Doctor;
import models.Speciality;
import services.MedicSystemService;

/**
 * Formulario para buscar, modificar y eliminar Doctores.
 */
public class FrmManageDoctor extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private Doctor currentDoctor = null;

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
        setFieldsEditable(false);
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
        JLabel jlblTitulo = new JLabel("🔍 Gestionar Doctor (Buscar, Modificar, Eliminar)");
        jlblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jlblTitulo.setForeground(new Color(52, 152, 219));

        // --- Panel de Búsqueda (Norte) ---
        jtfSearchId = new JTextField(10);
        jtfSearchId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jbtnSearch = new JButton("🔎 Buscar por ID");
        jbtnSearch.addActionListener(e -> jbtnSearchActionPerformed());
        jbtnSearch.setBackground(new Color(52, 152, 219));
        jbtnSearch.setForeground(Color.WHITE);
        jbtnSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jbtnSearch.setFocusPainted(false);
        jbtnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIdDoctor = new JLabel("ID Doctor:");
        lblIdDoctor.setFont(new Font("Segoe UI", Font.BOLD, 14));

        jPanelSearch.add(lblIdDoctor);
        jPanelSearch.add(jtfSearchId);
        jPanelSearch.add(jbtnSearch);

        // Contenedor principal para título y búsqueda
        JPanel jPanelTop = new JPanel(new BorderLayout(10, 10));
        jPanelTop.setBackground(new Color(245, 245, 245));
        jPanelTop.add(jlblTitulo, BorderLayout.NORTH);
        jPanelTop.add(new JSeparator(), BorderLayout.CENTER);
        jPanelTop.add(jPanelSearch, BorderLayout.SOUTH);

        // --- Panel de Detalles (Centro) ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes de detalle
        jtfFullName = new JTextField(25);
        jtfFullName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jtfAge = new JTextField(25);
        jtfAge.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jtfEmail = new JTextField(25);
        jtfEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jcbSpeciality = new JComboBox<>();
        jcbSpeciality.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jcbSpeciality.setPreferredSize(new Dimension(300, 30));

        // ✅ SOLUCIÓN: Configurar el renderer para mostrar el nombre
        jcbSpeciality.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Speciality) {
                    Speciality speciality = (Speciality) value;
                    setText(speciality.getName());
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));
                } else if (value == null) {
                    setText("-- Seleccione una especialidad --");
                    setFont(new Font("Segoe UI", Font.ITALIC, 13));
                    setForeground(Color.GRAY);
                }

                return this;
            }
        });

        // Helper para agregar filas al GridBagLayout
        int row = 0;
        row = addFormField(jPanelForm, gbc, row, "Nombre Completo:", jtfFullName);
        row = addFormField(jPanelForm, gbc, row, "Edad:", jtfAge);
        row = addFormField(jPanelForm, gbc, row, "Email:", jtfEmail);
        row = addFormField(jPanelForm, gbc, row, "Especialidad:", jcbSpeciality);

        // --- Botones de Acción (Sur) ---
        jbtnModify = new JButton("💾 Modificar Datos");
        jbtnModify.addActionListener(e -> jbtnModifyActionPerformed());
        jbtnModify.setBackground(new Color(46, 204, 113));
        jbtnModify.setForeground(Color.WHITE);
        jbtnModify.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jbtnModify.setFocusPainted(false);
        jbtnModify.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnModify.setPreferredSize(new Dimension(170, 40));

        jbtnDelete = new JButton("🗑️ Eliminar Doctor");
        jbtnDelete.addActionListener(e -> jbtnDeleteActionPerformed());
        jbtnDelete.setBackground(new Color(231, 76, 60));
        jbtnDelete.setForeground(Color.WHITE);
        jbtnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jbtnDelete.setFocusPainted(false);
        jbtnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnDelete.setPreferredSize(new Dimension(170, 40));

        jbtnCancel = new JButton("⬅️ Volver");
        jbtnCancel.addActionListener(e -> dispose());
        jbtnCancel.setBackground(new Color(189, 195, 199));
        jbtnCancel.setForeground(new Color(52, 73, 94));
        jbtnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jbtnCancel.setFocusPainted(false);
        jbtnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnCancel.setPreferredSize(new Dimension(120, 40));

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
        setMinimumSize(new Dimension(650, 450));
    }

    /** Helper para agregar un par etiqueta-campo al GridBagLayout */
    private int addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, Component field) {
        // Etiqueta
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(52, 73, 94));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        // Campo
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
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

        loadSpecialities(doctor.getSpeciality());

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
            ArrayList<Speciality> specialities = medicService.getSpecialityRepository().getAll();

            if (specialities == null || specialities.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "⚠️ No hay especialidades registradas en el sistema.\n\n" +
                                "Por favor, agregue especialidades primero.",
                        "Sin Especialidades",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Speciality s : specialities) {
                jcbSpeciality.addItem(s);
                // Selecciona la especialidad actual del doctor
                if (selectedSpeciality != null &&
                        s.getSpecialityId() == selectedSpeciality.getSpecialityId()) {
                    jcbSpeciality.setSelectedItem(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "❌ Error al cargar especialidades:\n\n" + e.getMessage(),
                    "Error de Carga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- MANEJADORES DE ACCIONES ---

    private void jbtnSearchActionPerformed() {
        clearFields();
        setFieldsEditable(false);

        String idText = jtfSearchId.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Por favor, ingrese un ID de doctor.",
                    "Campo Vacío",
                    JOptionPane.WARNING_MESSAGE);
            jtfSearchId.requestFocus();
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ El ID debe ser un número entero válido.\n\n" +
                            "Ejemplo: 123",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
            jtfSearchId.requestFocus();
            jtfSearchId.selectAll();
            return;
        }

        Doctor foundDoctor = medicService.getDoctorRepository().searchById(id);

        if (foundDoctor != null) {
            loadDoctorData(foundDoctor);
            JOptionPane.showMessageDialog(this,
                    "✅ Doctor encontrado exitosamente.\n\n" +
                            "ID: " + foundDoctor.getCredentials().getId() + "\n" +
                            "Nombre: " + foundDoctor.getFullName(),
                    "Búsqueda Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "❌ No se encontró ningún doctor con ID: " + id + "\n\n" +
                            "Verifique el ID e intente nuevamente.",
                    "Doctor No Encontrado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnModifyActionPerformed() {
        if (currentDoctor == null) return;

        // 1. Obtener y validar nuevos datos
        String fullName = jtfFullName.getText().trim();
        String ageText = jtfAge.getText().trim();
        String email = jtfEmail.getText().trim();
        Speciality newSpeciality = (Speciality) jcbSpeciality.getSelectedItem();

        // Validación: Campos vacíos
        if (fullName.isEmpty() || ageText.isEmpty() || email.isEmpty() || newSpeciality == null) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Todos los campos son obligatorios.\n\n" +
                            "Por favor, complete:\n" +
                            "• Nombre completo\n" +
                            "• Edad\n" +
                            "• Email\n" +
                            "• Especialidad",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación: Edad
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 120) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ La edad debe ser un número entre 1 y 120.\n\n" +
                            "Valor ingresado: " + ageText,
                    "Edad Inválida",
                    JOptionPane.ERROR_MESSAGE);
            jtfAge.requestFocus();
            jtfAge.selectAll();
            return;
        }

        // Validación: Email
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this,
                    "❌ El formato del email es inválido.\n\n" +
                            "Ejemplo válido: doctor@hospital.com",
                    "Email Inválido",
                    JOptionPane.ERROR_MESSAGE);
            jtfEmail.requestFocus();
            jtfEmail.selectAll();
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
            JOptionPane.showMessageDialog(this,
                    "✅ Doctor modificado exitosamente.\n\n" +
                            "ID: " + currentDoctor.getCredentials().getId() + "\n" +
                            "Nombre: " + fullName + "\n" +
                            "Edad: " + age + "\n" +
                            "Email: " + email + "\n" +
                            "Especialidad: " + newSpeciality.getName(),
                    "Modificación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            loadDoctorData(currentDoctor);
        } else {
            JOptionPane.showMessageDialog(this,
                    "❌ No se pudo modificar el doctor.\n\n" +
                            "Por favor, intente nuevamente.",
                    "Error de Sistema",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbtnDeleteActionPerformed() {
        if (currentDoctor == null) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "⚠️ ¿Está seguro que desea eliminar este doctor?\n\n" +
                        "ID: " + currentDoctor.getCredentials().getId() + "\n" +
                        "Nombre: " + currentDoctor.getFullName() + "\n" +
                        "Especialidad: " + currentDoctor.getSpeciality().getName() + "\n\n" +
                        "⚠️ Esta acción NO se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = medicService.removeDoctor(currentDoctor);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "✅ Doctor eliminado exitosamente.\n\n" +
                                "ID: " + currentDoctor.getCredentials().getId() + "\n" +
                                "Nombre: " + currentDoctor.getFullName(),
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                setFieldsEditable(false);
                jtfSearchId.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ No se pudo eliminar el doctor.\n\n" +
                                "Posibles causas:\n" +
                                "• Tiene citas programadas\n" +
                                "• Tiene dependencias activas\n\n" +
                                "Elimine las dependencias primero.",
                        "Error al Eliminar",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}