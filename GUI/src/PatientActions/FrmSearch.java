/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.JOptionPane;
import Services.MedicSystemService;
import models.Patient;
import repositories.PatientRepository;

/**
 *
 * @author WINDOWS 11
 */
public class FrmSearch extends javax.swing.JFrame {

    private final PatientRepository patientRepo;
    private final MedicSystemService medicService;
    
    /**
     * Creates new form FrmSearch
     */
    public FrmSearch() {
        initComponents();
        this.patientRepo = new PatientRepository();
        this.medicService = new MedicSystemService();
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmSearch(PatientRepository patientRepo, MedicSystemService medicService) {
        initComponents();
        this.patientRepo = patientRepo;
        this.medicService = medicService;
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jbtnBuscarModificar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnBuscarEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Paciente");
        setResizable(false);

        jlblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jlblTitulo.setText(" BUSCAR PACIENTE");
        jlblTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText("   Número Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jbtnBuscarModificar.setText("Buscar y Modificar");
        jbtnBuscarModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarModificarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jbtnBuscarEliminar.setText("Buscar y Eliminar");
        jbtnBuscarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnBuscarModificar)
                    .addComponent(jbtnBuscarEliminar))
                .addGap(26, 26, 26)
                .addComponent(jbtnCancelar)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jlblTitulo.getAccessibleContext().setAccessibleParent(jlblTitulo);
        jlblNumeroDocumento.getAccessibleContext().setAccessibleParent(jlblNumeroDocumento);
        jtextNumeroDocumento.getAccessibleContext().setAccessibleDescription("");
        jtextNumeroDocumento.getAccessibleContext().setAccessibleParent(jlblNumeroDocumento);
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarModificarActionPerformed
        try {
            // Obtener y validar el campo
            String idInput = jtextNumeroDocumento.getText().trim();
            
            // Validar que no esté vacío
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parsear el ID
            int id = Integer.parseInt(idInput);
            
            // Validar que sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser un número positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar el paciente
            Patient patient = patientRepo.searchById(id);
            
            if (patient != null) {
                // Abrir FrmModify con el paciente encontrado
                FrmModify frmModify = new FrmModify(patient, medicService, patientRepo);
                frmModify.setVisible(true);
                frmModify.setLocationRelativeTo(this); // Centrar respecto a esta ventana
                this.dispose(); // Cerrar FrmSearch
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ningún paciente con el documento: " + id, 
                    "Paciente No Encontrado", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextNumeroDocumento.selectAll(); // Seleccionar el texto para facilitar corrección
                jtextNumeroDocumento.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El número de documento debe contener solo números válidos (sin puntos ni comas)", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextNumeroDocumento.selectAll();
            jtextNumeroDocumento.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar el paciente: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbtnBuscarModificarActionPerformed
    
    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarEliminarActionPerformed
        try {
            // Obtener y validar el campo
            String idInput = jtextNumeroDocumento.getText().trim();
            
            // Validar que no esté vacío
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parsear el ID
            int id = Integer.parseInt(idInput);
            
            // Validar que sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser un número positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar el paciente
            Patient patient = patientRepo.searchById(id);
            
            if (patient != null) {
                // Abrir FrmDelete con el paciente encontrado
                FrmDelete frmDelete = new FrmDelete(medicService, patientRepo);
                frmDelete.setPatientToDelete(patient); // Cargar el paciente encontrado
                frmDelete.setVisible(true);
                frmDelete.setLocationRelativeTo(this); // Centrar respecto a esta ventana
                this.dispose(); // Cerrar FrmSearch
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ningún paciente con el documento: " + id, 
                    "Paciente No Encontrado", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextNumeroDocumento.selectAll();
                jtextNumeroDocumento.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El número de documento debe contener solo números válidos (sin puntos ni comas)", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextNumeroDocumento.selectAll();
            jtextNumeroDocumento.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar el paciente: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbtnBuscarEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnBuscarEliminar;
    private javax.swing.JButton jbtnBuscarModificar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JTextField jtextNumeroDocumento;
    // End of variables declaration//GEN-END:variables
}