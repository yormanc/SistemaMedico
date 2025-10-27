import javax.swing.*;
import services.MedicSystemService;

/**
 * Formulario para agregar nueva especialidad
 * USA MedicSystemService para garantizar repositorios compartidos
 */
public class FrmAddSpeciality extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    /**
     * Constructor por defecto
     */
    public FrmAddSpeciality() {
        this.medicService = new MedicSystemService();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmAddSpeciality(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
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
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Especialidad");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("AGREGAR ESPECIALIDAD");
        jlblTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblId.setText("   ID de Especialidad");
        jlblId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombre.setText("   Nombre");
        jlblNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblDescripcion.setText("   Descripción");
        jlblDescripcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextAreaDescripcion.setColumns(20);
        jtextAreaDescripcion.setRows(5);
        jtextAreaDescripcion.setLineWrap(true);
        jtextAreaDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jtextAreaDescripcion);

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(evt -> jbtnGuardarActionPerformed(evt));

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(evt -> dispose());

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
                        .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jbtnGuardar)
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

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar campos antes de parsear
            String idInput = jtextId.getText().trim();
            String nombreInput = jtextNombre.getText().trim();
            String descripcionInput = jtextAreaDescripcion.getText().trim();

            // Validar que no estén vacíos ANTES de parsear
            if (idInput.isEmpty() || nombreInput.isEmpty() || descripcionInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Debe llenar todos los campos",
                    "Campos Vacíos",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que el servicio esté inicializado
            if (medicService == null) {
                JOptionPane.showMessageDialog(this,
                    "Error: El servicio no está inicializado",
                    "Error de Sistema",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ahora parsear el ID
            int id = Integer.parseInt(idInput);

            // Validar que el ID sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número positivo",
                    "ID Inválido",
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

            // ✅ USAR EL MÉTODO DE MedicSystemService
            boolean isAdded = medicService.addSpeciality(id, nombreInput, descripcionInput);

            if (!isAdded) {
                JOptionPane.showMessageDialog(this,
                    "Error al agregar la especialidad. Es posible que el ID ya esté registrado.",
                    "Error al Registrar",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                "Especialidad agregada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después del éxito
            jtextId.setText("");
            jtextNombre.setText("");
            jtextAreaDescripcion.setText("");
            
            // Enfocar el primer campo para facilitar el ingreso de otra especialidad
            jtextId.requestFocus();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "El ID debe contener solo números válidos",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error inesperado: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Para debug
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
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnCancelar;
}
