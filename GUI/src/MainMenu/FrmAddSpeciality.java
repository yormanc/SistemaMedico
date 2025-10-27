import javax.swing.*;
import java.awt.*;
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
        jPanelMain = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelForm = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jlblNombre = new javax.swing.JLabel();
        jtextNombre = new javax.swing.JTextField();
        jlblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtextAreaDescripcion = new javax.swing.JTextArea();
        jPanelButtons = new javax.swing.JPanel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Especialidad Médica");
        setResizable(false);

        // Panel principal
        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Panel de encabezado
        jPanelHeader.setBackground(new java.awt.Color(245, 245, 245));
        
        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 26));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("➕ AGREGAR ESPECIALIDAD");
        
        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Panel del formulario
        jPanelForm.setBackground(Color.WHITE);
        jPanelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Labels con estilo mejorado
        jlblId.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblId.setForeground(new java.awt.Color(52, 73, 94));
        jlblId.setText("ID de Especialidad:");

        jtextId.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jtextId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        jtextId.setToolTipText("Ingrese un número único para identificar la especialidad");

        jlblNombre.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblNombre.setForeground(new java.awt.Color(52, 73, 94));
        jlblNombre.setText("Nombre de la Especialidad:");

        jtextNombre.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jtextNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        jtextNombre.setToolTipText("Ej: Cardiología, Pediatría, Dermatología");

        jlblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblDescripcion.setForeground(new java.awt.Color(52, 73, 94));
        jlblDescripcion.setText("Descripción:");

        jtextAreaDescripcion.setColumns(20);
        jtextAreaDescripcion.setRows(5);
        jtextAreaDescripcion.setLineWrap(true);
        jtextAreaDescripcion.setWrapStyleWord(true);
        jtextAreaDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jtextAreaDescripcion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        jtextAreaDescripcion.setToolTipText("Describa brevemente el área de esta especialidad médica");
        jScrollPane1.setViewportView(jtextAreaDescripcion);
        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblId)
            .addComponent(jtextId)
            .addComponent(jlblNombre)
            .addComponent(jtextNombre)
            .addComponent(jlblDescripcion)
            .addComponent(jScrollPane1)
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addComponent(jlblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Panel de botones
        jPanelButtons.setBackground(new java.awt.Color(245, 245, 245));

        jbtnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jbtnGuardar.setText("💾 Guardar");
        jbtnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        jbtnGuardar.setForeground(Color.WHITE);
        jbtnGuardar.setFocusPainted(false);
        jbtnGuardar.setBorderPainted(false);
        jbtnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnGuardar.setToolTipText("Guardar la especialidad en el sistema");
        jbtnGuardar.addActionListener(evt -> jbtnGuardarActionPerformed(evt));
        jbtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnGuardar.setBackground(new java.awt.Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnGuardar.setBackground(new java.awt.Color(46, 204, 113));
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jbtnCancelar.setText("❌ Cancelar");
        jbtnCancelar.setBackground(new java.awt.Color(231, 76, 60));
        jbtnCancelar.setForeground(Color.WHITE);
        jbtnCancelar.setFocusPainted(false);
        jbtnCancelar.setBorderPainted(false);
        jbtnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnCancelar.setToolTipText("Cancelar y cerrar el formulario");
        jbtnCancelar.addActionListener(evt -> dispose());
        jbtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(192, 57, 43));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(231, 76, 60));
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Layout principal
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar campos antes de parsear
            String idInput = jtextId.getText().trim();
            String nombreInput = jtextNombre.getText().trim();
            String descripcionInput = jtextAreaDescripcion.getText().trim();

            // ✅ Validación 1: Campos vacíos
            if (idInput.isEmpty() || nombreInput.isEmpty() || descripcionInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "⚠️ Todos los campos son obligatorios.\n\n" +
                    "Por favor, complete:\n" +
                    "• ID de Especialidad\n" +
                    "• Nombre\n" +
                    "• Descripción",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
                
                // Enfocar el primer campo vacío
                if (idInput.isEmpty()) {
                    jtextId.requestFocus();
                } else if (nombreInput.isEmpty()) {
                    jtextNombre.requestFocus();
                } else {
                    jtextAreaDescripcion.requestFocus();
                }
                return;
            }
            
            // ✅ Validación 2: Servicio inicializado
            if (medicService == null) {
                JOptionPane.showMessageDialog(this,
                    "❌ Error crítico: El servicio no está inicializado.\n\n" +
                    "Por favor, reinicie la aplicación.",
                    "Error de Sistema",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Validación 3: ID numérico válido
            int id;
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                    "❌ El ID debe ser un número entero válido.\n\n" +
                    "Ejemplos válidos: 1, 2, 100, 999",
                    "ID Inválido",
                    JOptionPane.ERROR_MESSAGE);
                jtextId.requestFocus();
                jtextId.selectAll();
                return;
            }

            // ✅ Validación 4: ID positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                    "❌ El ID debe ser un número positivo mayor a cero.\n\n" +
                    "ID ingresado: " + id,
                    "ID Inválido",
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                jtextId.selectAll();
                return;
            }

            // ✅ Validación 5: Nombre no solo números
            if (nombreInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                    "❌ El nombre de la especialidad no puede contener solo números.\n\n" +
                    "Ingrese un nombre descriptivo como:\n" +
                    "• Cardiología\n" +
                    "• Pediatría\n" +
                    "• Dermatología",
                    "Nombre Inválido",
                    JOptionPane.WARNING_MESSAGE);
                jtextNombre.requestFocus();
                jtextNombre.selectAll();
                return;
            }
            
            // ✅ Validación 6: Longitud mínima del nombre
            if (nombreInput.length() < 3) {
                JOptionPane.showMessageDialog(this,
                    "❌ El nombre debe tener al menos 3 caracteres.\n\n" +
                    "Caracteres actuales: " + nombreInput.length(),
                    "Nombre Muy Corto",
                    JOptionPane.WARNING_MESSAGE);
                jtextNombre.requestFocus();
                jtextNombre.selectAll();
                return;
            }
            
            // ✅ Validación 7: Longitud máxima del nombre
            if (nombreInput.length() > 100) {
                JOptionPane.showMessageDialog(this,
                    "❌ El nombre es demasiado largo (máximo 100 caracteres).\n\n" +
                    "Caracteres actuales: " + nombreInput.length(),
                    "Nombre Muy Largo",
                    JOptionPane.WARNING_MESSAGE);
                jtextNombre.requestFocus();
                return;
            }
            
            // ✅ Validación 8: Longitud mínima de la descripción
            if (descripcionInput.length() < 10) {
                JOptionPane.showMessageDialog(this,
                    "❌ La descripción debe tener al menos 10 caracteres.\n\n" +
                    "Caracteres actuales: " + descripcionInput.length(),
                    "Descripción Muy Corta",
                    JOptionPane.WARNING_MESSAGE);
                jtextAreaDescripcion.requestFocus();
                return;
            }
            
            // ✅ Validación 9: Longitud máxima de la descripción
            if (descripcionInput.length() > 500) {
                JOptionPane.showMessageDialog(this,
                    "❌ La descripción es demasiado larga (máximo 500 caracteres).\n\n" +
                    "Caracteres actuales: " + descripcionInput.length(),
                    "Descripción Muy Larga",
                    JOptionPane.WARNING_MESSAGE);
                jtextAreaDescripcion.requestFocus();
                return;
            }

            // ✅ GUARDAR EN EL SISTEMA
            System.out.println("\n=== INTENTANDO GUARDAR ESPECIALIDAD ===");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombreInput);
            System.out.println("Descripción: " + descripcionInput);
            
            boolean isAdded = medicService.addSpeciality(id, nombreInput, descripcionInput);

            if (!isAdded) {
                System.out.println("❌ NO SE PUDO AGREGAR LA ESPECIALIDAD");
                JOptionPane.showMessageDialog(this,
                    "❌ No se pudo agregar la especialidad.\n\n" +
                    "Posibles causas:\n" +
                    "• El ID " + id + " ya está registrado\n" +
                    "• Error en la base de datos\n\n" +
                    "Por favor, intente con un ID diferente.",
                    "Error al Registrar",
                    JOptionPane.ERROR_MESSAGE);
                jtextId.requestFocus();
                jtextId.selectAll();
                return;
            }

            // ✅ ÉXITO
            System.out.println("✅ ESPECIALIDAD AGREGADA EXITOSAMENTE");
            JOptionPane.showMessageDialog(this,
                "✅ Especialidad agregada exitosamente\n\n" +
                "ID: " + id + "\n" +
                "Nombre: " + nombreInput + "\n\n" +
                "La especialidad ha sido registrada en el sistema.",
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después del éxito
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "❌ El ID debe contener solo números válidos.\n\n" +
                "Error: " + e.getMessage(),
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
            jtextId.requestFocus();
            jtextId.selectAll();
        } catch (Exception e) {
            System.err.println("❌ ERROR INESPERADO: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "❌ Error inesperado al guardar:\n\n" + 
                e.getMessage() + "\n\n" +
                "Por favor, contacte al administrador del sistema.",
                "Error Crítico",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        jtextId.setText("");
        jtextNombre.setText("");
        jtextAreaDescripcion.setText("");
        jtextId.requestFocus();
    }

    // Variables de instancia
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JSeparator jSeparator1;
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