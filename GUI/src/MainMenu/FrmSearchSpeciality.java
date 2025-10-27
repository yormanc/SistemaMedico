import javax.swing.*;
import services.MedicSystemService;
import models.Speciality;
import repositories.SpecialityRepository;

/**
 * Formulario de búsqueda de especialidades
 * Patrón igual a FrmSearch de pacientes
 */
public class FrmSearchSpeciality extends javax.swing.JFrame {

    private final SpecialityRepository specialityRepo;
    private final MedicSystemService medicService;
    
    /**
     * Constructor por defecto
     */
    public FrmSearchSpeciality() {
        initComponents();
        this.medicService = new MedicSystemService();
        // ✅ Obtener el repositorio desde el servicio compartido
        try {
            this.specialityRepo = (SpecialityRepository) medicService.getClass()
                .getDeclaredField("specialityRepository").get(medicService);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener repositorio de especialidades", e);
        }
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmSearchSpeciality(MedicSystemService medicService) {
        initComponents();
        this.medicService = medicService;
        // ✅ Obtener el repositorio desde el servicio compartido
        try {
            this.specialityRepo = (SpecialityRepository) medicService.getClass()
                .getDeclaredField("specialityRepository").get(medicService);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener repositorio de especialidades", e);
        }
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jbtnBuscarModificar = new javax.swing.JButton();
        jbtnBuscarEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Especialidad");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("BUSCAR ESPECIALIDAD");

        jlblId.setText("   ID de Especialidad");
        jlblId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jbtnBuscarModificar.setText("Buscar y Modificar");
        jbtnBuscarModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarModificarActionPerformed(evt);
            }
        });

        jbtnBuscarEliminar.setText("Buscar y Eliminar");
        jbtnBuscarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarEliminarActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnBuscarModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnBuscarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
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
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnBuscarModificar)
                    .addComponent(jbtnBuscarEliminar))
                .addGap(26, 26, 26)
                .addComponent(jbtnCancelar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jlblTitulo.getAccessibleContext().setAccessibleParent(jlblTitulo);
        jlblId.getAccessibleContext().setAccessibleParent(jlblId);
        jtextId.getAccessibleContext().setAccessibleDescription("");
        jtextId.getAccessibleContext().setAccessibleParent(jlblId);
        jbtnBuscarModificar.getAccessibleContext().setAccessibleParent(jbtnBuscarModificar);
        jbtnCancelar.getAccessibleContext().setAccessibleParent(jbtnCancelar);
        jbtnBuscarEliminar.getAccessibleContext().setAccessibleParent(jbtnBuscarEliminar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar el campo
            String idInput = jtextId.getText().trim();
            
            // Validar que no esté vacío
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
            Speciality speciality = specialityRepo.searchById(id);
            
            if (speciality != null) {
                // Abrir FrmModifySpeciality con la especialidad encontrada
                FrmModifySpeciality frmModify = new FrmModifySpeciality(speciality, medicService);
                frmModify.setVisible(true);
                frmModify.setLocationRelativeTo(this); // Centrar respecto a esta ventana
                this.dispose(); // Cerrar FrmSearchSpeciality
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ninguna especialidad con el ID: " + id, 
                    "Especialidad No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll(); // Seleccionar el texto para facilitar corrección
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe contener solo números válidos (sin puntos ni comas)", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar la especialidad: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar el campo
            String idInput = jtextId.getText().trim();
            
            // Validar que no esté vacío
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
            Speciality speciality = specialityRepo.searchById(id);
            
            if (speciality != null) {
                // Abrir FrmDeleteSpeciality con la especialidad encontrada
                FrmDeleteSpeciality frmDelete = new FrmDeleteSpeciality(medicService, specialityRepo);
                frmDelete.setSpecialityToDelete(speciality); // Cargar la especialidad encontrada
                frmDelete.setVisible(true);
                frmDelete.setLocationRelativeTo(this); // Centrar respecto a esta ventana
                this.dispose(); // Cerrar FrmSearchSpeciality
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ninguna especialidad con el ID: " + id, 
                    "Especialidad No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe contener solo números válidos (sin puntos ni comas)", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar la especialidad: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(FrmSearchSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSearchSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSearchSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSearchSpeciality.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSearchSpeciality().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnBuscarEliminar;
    private javax.swing.JButton jbtnBuscarModificar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JLabel jlblId;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JTextField jtextId;
    // End of variables declaration
}