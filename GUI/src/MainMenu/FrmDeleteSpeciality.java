import javax.swing.*;
import Services.MedicSystemService;
import models.Speciality;
import repositories.SpecialityRepository;

/**
 * Formulario para eliminar especialidad
 * Patrón igual a FrmDelete de pacientes
 */
public class FrmDeleteSpeciality extends javax.swing.JFrame {

    private MedicSystemService medicService;
    private SpecialityRepository specialityRepository;
    private Speciality currentSpeciality = null;

    /**
     * Constructor por defecto
     */
    public FrmDeleteSpeciality() {
        initComponents();
        this.medicService = new MedicSystemService();
        try {
            this.specialityRepository = (SpecialityRepository) medicService.getClass()
                .getDeclaredField("specialityRepository").get(medicService);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener repositorio", e);
        }
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmDeleteSpeciality(MedicSystemService medicService, SpecialityRepository specialityRepository) {
        initComponents();
        this.medicService = medicService;
        this.specialityRepository = specialityRepository;
    }

    /**
     * Método para cargar la especialidad a eliminar (llamado desde FrmSearchSpeciality)
     */
    public void setSpecialityToDelete(Speciality speciality) {
        if (speciality == null) {
            return;
        }
        
        this.currentSpeciality = speciality;
        
        // Llenar el campo de ID
        jtextId.setText(String.valueOf(speciality.getSpecialityId()));
        jtextId.setEnabled(false);
        
        // Mostrar información de la especialidad automáticamente
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(speciality.getSpecialityId()).append("\n");
        info.append("Nombre: ").append(speciality.getName()).append("\n");
        info.append("Descripción: ").append(speciality.getDescription()).append("\n");
        
        jtextInfo.setText(info.toString());
        jbtnEliminar.setEnabled(true);
        jbtnBuscar.setEnabled(false); // Deshabilitar el botón buscar ya que ya se tiene la especialidad
    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jbtnBuscar = new javax.swing.JButton();
        jlblInfoEspecialidad = new javax.swing.JLabel();
        jtextAreaInfo = new javax.swing.JScrollPane();
        jtextInfo = new javax.swing.JTextArea();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Especialidad");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jlblTitulo.setForeground(new java.awt.Color(204, 0, 0));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("ELIMINAR ESPECIALIDAD");
        jlblTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblId.setText("   ID de Especialidad");
        jlblId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jlblInfoEspecialidad.setText("Información de la Especialidad:");

        jtextInfo.setEditable(false);
        jtextInfo.setColumns(20);
        jtextInfo.setRows(5);
        jtextInfo.setBackground(new java.awt.Color(240, 240, 240));
        jtextAreaInfo.setViewportView(jtextInfo);

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.setForeground(new java.awt.Color(204, 0, 0));
        jbtnEliminar.setEnabled(false);
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnBuscar))
                    .addComponent(jlblInfoEspecialidad)
                    .addComponent(jtextAreaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscar))
                .addGap(26, 26, 26)
                .addComponent(jlblInfoEspecialidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextAreaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEliminar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jlblTitulo.getAccessibleContext().setAccessibleDescription("");
        jlblTitulo.getAccessibleContext().setAccessibleParent(jlblTitulo);

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

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            // Validar que el campo no esté vacío
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un ID de especialidad", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parsear el ID
            int id = Integer.parseInt(idInput);
            
            // Validar que sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El ID debe ser un número positivo", 
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar la especialidad
            currentSpeciality = specialityRepository.searchById(id);
            
            if (currentSpeciality == null) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ninguna especialidad con el ID: " + id, 
                    "Especialidad No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextInfo.setText("");
                jbtnEliminar.setEnabled(false);
                return;
            }
            
            // Mostrar información de la especialidad
            StringBuilder info = new StringBuilder();
            info.append("ID: ").append(currentSpeciality.getSpecialityId()).append("\n");
            info.append("Nombre: ").append(currentSpeciality.getName()).append("\n");
            info.append("Descripción: ").append(currentSpeciality.getDescription()).append("\n");
            
            jtextInfo.setText(info.toString());
            jbtnEliminar.setEnabled(true);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe contener solo números válidos", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextInfo.setText("");
            jbtnEliminar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar la especialidad: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            jtextInfo.setText("");
            jbtnEliminar.setEnabled(false);
        }
    }

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (currentSpeciality == null) {
                JOptionPane.showMessageDialog(this, 
                    "No hay ninguna especialidad seleccionada", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Confirmar la eliminación
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro que desea eliminar la especialidad:\n" + 
                currentSpeciality.getName() + " (ID: " + currentSpeciality.getSpecialityId() + ")?\n\n" +
                "Esta acción no se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            // ✅ USAR EL MÉTODO DE MedicSystemService
            boolean isDeleted = medicService.removeSpeciality(currentSpeciality);
            
            if (!isDeleted) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar la especialidad. Puede que tenga doctores asociados.", 
                    "Error al Eliminar", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, 
                "Especialidad eliminada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar el formulario
            jtextId.setText("");
            jtextInfo.setText("");
            jbtnEliminar.setEnabled(false);
            currentSpeciality = null;
            jtextId.requestFocus();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmDeleteSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDeleteSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDeleteSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDeleteSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDeleteSpeciality().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblInfoEspecialidad;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextArea jtextInfo;
    private javax.swing.JScrollPane jtextAreaInfo;
    private javax.swing.JTextField jtextId;
    // End of variables declaration
}
