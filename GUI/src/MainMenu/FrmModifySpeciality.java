import javax.swing.*;
import services.MedicSystemService;
import models.Speciality;

/**
 * Formulario para modificar especialidad
 * USA MedicSystemService.updateSpeciality()
 */
public class FrmModifySpeciality extends javax.swing.JFrame {

    private final Speciality currentSpeciality;
    private final MedicSystemService medicService;
    
    /**
     * Constructor principal
     */
    public FrmModifySpeciality(Speciality speciality, MedicSystemService medicService) {
        this.currentSpeciality = speciality;
        this.medicService = medicService;
        initComponents();
        loadSpecialityData();
        setLocationRelativeTo(null);
    }
    
    private void loadSpecialityData() {
        if (currentSpeciality == null) {
            JOptionPane.showMessageDialog(this,
                "Error: No se proporcionó una especialidad válida",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        
        jtextId.setText(String.valueOf(currentSpeciality.getSpecialityId()));
        jtextId.setEnabled(false);
        jtextNombre.setText(currentSpeciality.getName());
        jtextAreaDescripcion.setText(currentSpeciality.getDescription());
    }


    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jlblNombre = new javax.swing.JLabel();
        jtextNombre = new javax.swing.JTextField();
        jlblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtextAreaDescripcion = new javax.swing.JTextArea();
        jbtnModificar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Especialidad");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("MODIFICAR ESPECIALIDAD");
        jlblTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblId.setText("   ID de Especialidad");
        jlblId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextId.setBackground(new java.awt.Color(240, 240, 240));

        jlblNombre.setText("   Nombre");
        jlblNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblDescripcion.setText("   Descripción");
        jlblDescripcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextAreaDescripcion.setColumns(20);
        jtextAreaDescripcion.setRows(5);
        jtextAreaDescripcion.setLineWrap(true);
        jtextAreaDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jtextAreaDescripcion);

        jbtnModificar.setText("Modificar");
        jbtnModificar.addActionListener(evt -> jbtnModificarActionPerformed(evt));

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(evt -> jbtnCancelarActionPerformed(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlblTitulo)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblId)
                    .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre)
                    .addComponent(jtextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblDescripcion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnModificar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar campos
            String nombreInput = jtextNombre.getText().trim();
            String descripcionInput = jtextAreaDescripcion.getText().trim();

            // Validar que no estén vacíos
            if (nombreInput.isEmpty() || descripcionInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Debe llenar todos los campos",
                    "Campos Vacíos",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar que el nombre no contenga solo números
            if (nombreInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                    "El nombre no puede contener solo números",
                    "Nombre Inválido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar longitud mínima del nombre
            if (nombreInput.length() < 3) {
                JOptionPane.showMessageDialog(this,
                    "El nombre debe tener al menos 3 caracteres",
                    "Nombre Inválido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar longitud mínima de la descripción
            if (descripcionInput.length() < 10) {
                JOptionPane.showMessageDialog(this,
                    "La descripción debe tener al menos 10 caracteres",
                    "Descripción Inválida",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Verificar si hay cambios
            boolean hasChanges = !nombreInput.equals(currentSpeciality.getName()) ||
                                !descripcionInput.equals(currentSpeciality.getDescription());

            if (!hasChanges) {
                JOptionPane.showMessageDialog(this,
                    "No se han realizado cambios en los datos de la especialidad",
                    "Sin Cambios",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Crear especialidad modificada manteniendo el mismo ID
            Speciality modifiedSpeciality = new Speciality(
                currentSpeciality.getSpecialityId(),
                nombreInput,
                descripcionInput
            );

            // ✅ USAR EL MÉTODO DE MedicSystemService
            boolean isUpdated = medicService.updateSpeciality(modifiedSpeciality);

            if (isUpdated) {
                JOptionPane.showMessageDialog(this,
                    "Datos de la especialidad actualizados exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al actualizar los datos de la especialidad",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error inesperado al actualizar la especialidad: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // Preguntar si desea descartar los cambios
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea cancelar?\nSe perderán los cambios no guardados.",
            "Confirmar Cancelación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextField jtextId;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JTextField jtextNombre;
    private javax.swing.JLabel jlblDescripcion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtextAreaDescripcion;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JButton jbtnCancelar;
}
