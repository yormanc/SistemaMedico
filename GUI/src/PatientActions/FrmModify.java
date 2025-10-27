/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.JOptionPane;
import services.MedicSystemService;
import models.Patient;
import repositories.PatientRepository;

/**
 *
 * @author WINDOWS 11
 */
public class FrmModify extends javax.swing.JFrame {

    private final Patient currentPatient;
    private final MedicSystemService medicService;
    
    /**
     * Creates new form FrmModify
     */
    public FrmModify(Patient patient) {
        this.currentPatient = patient;
        this.medicService = new MedicSystemService();
        initComponents();
        loadPatientData();
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmModify(Patient patient, MedicSystemService medicService, PatientRepository patientRepo) {
        this.currentPatient = patient;
        this.medicService = medicService;
        initComponents();
        loadPatientData();
    }
    
    private void loadPatientData() {
        if (currentPatient == null) {
            JOptionPane.showMessageDialog(this, 
                "Error: No se proporcionó un paciente válido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        
        jtextNombreCompleto.setText(currentPatient.getFullName());
        jtextEdad.setText(String.valueOf(currentPatient.getAge()));
        jtextEmail.setText(currentPatient.getEmail());
        jtextNumeroDocumento.setText(String.valueOf(currentPatient.getCredentials().getId()));
        jtextNumeroDocumento.setEnabled(false); // El documento no se puede modificar
    }
    

    private void initComponents() {

        jpnlModifcar = new javax.swing.JPanel();
        jlblModificarUsuario = new javax.swing.JLabel();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jlblNombreCompleto = new javax.swing.JLabel();
        jtextNombreCompleto = new javax.swing.JTextField();
        jlblEdad = new javax.swing.JLabel();
        jtextEdad = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jtextEmail = new javax.swing.JTextField();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jbtnModificar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Paciente");
        setResizable(false);

        jlblModificarUsuario.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jlblModificarUsuario.setText(" MODIFICAR PACIENTE");
        jlblModificarUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText("   Número Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombreCompleto.setText("    Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEdad.setText("             Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail.setText("             Email");
        jlblEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextNumeroDocumento.setBackground(new java.awt.Color(240, 240, 240));

        jbtnModificar.setText("Modificar");
        jbtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlModifcarLayout = new javax.swing.GroupLayout(jpnlModifcar);
        jpnlModifcar.setLayout(jpnlModifcarLayout);
        jpnlModifcarLayout.setHorizontalGroup(
            jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlModifcarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(jpnlModifcarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addComponent(jlblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addComponent(jbtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jpnlModifcarLayout.setVerticalGroup(
            jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlModifcarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlblModificarUsuario)
                .addGap(18, 18, 18)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnModificar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jlblModificarUsuario.getAccessibleContext().setAccessibleParent(jlblModificarUsuario);
        jlblNumeroDocumento.getAccessibleContext().setAccessibleParent(jlblNumeroDocumento);
        jlblNombreCompleto.getAccessibleContext().setAccessibleParent(jlblNombreCompleto);
        jtextNombreCompleto.getAccessibleContext().setAccessibleName("");
        jtextNombreCompleto.getAccessibleContext().setAccessibleParent(jlblNombreCompleto);
        jlblEdad.getAccessibleContext().setAccessibleParent(jlblEdad);
        jtextEdad.getAccessibleContext().setAccessibleParent(jtextEdad);
        jlblEmail.getAccessibleContext().setAccessibleParent(jlblEmail);
        jtextNumeroDocumento.getAccessibleContext().setAccessibleParent(jtextNumeroDocumento);
        jbtnModificar.getAccessibleContext().setAccessibleParent(jbtnModificar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlModifcar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlModifcar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnlModifcar.getAccessibleContext().setAccessibleParent(jpnlModifcar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarActionPerformed
        try {
            // Obtener y validar campos
            String nameInput = jtextNombreCompleto.getText().trim();
            String ageInput = jtextEdad.getText().trim();
            String emailInput = jtextEmail.getText().trim();
            
            // Validar que no estén vacíos
            if (nameInput.isEmpty() || ageInput.isEmpty() || emailInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe llenar todos los campos", 
                    "Campos Vacíos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parsear la edad
            int age = Integer.parseInt(ageInput);
            
            // Validar que la edad sea positiva y realista
            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, 
                    "La edad debe ser un número entre 1 y 150", 
                    "Edad Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar formato básico de email
            if (!emailInput.contains("@") || !emailInput.contains(".")) {
                JOptionPane.showMessageDialog(this, 
                    "El email no tiene un formato válido", 
                    "Email Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que el nombre no contenga solo números
            if (nameInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, 
                    "El nombre no puede contener solo números", 
                    "Nombre Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verificar si hay cambios
            boolean hasChanges = !nameInput.equals(currentPatient.getFullName()) ||
                                age != currentPatient.getAge() ||
                                !emailInput.equals(currentPatient.getEmail());
            
            if (!hasChanges) {
                JOptionPane.showMessageDialog(this, 
                    "No se han realizado cambios en los datos del paciente", 
                    "Sin Cambios", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Crear paciente modificado con las credenciales originales
            Patient modifyPatient = new Patient(nameInput, age, emailInput, currentPatient.getCredentials());
            
            // Actualizar el paciente
            boolean isUpdated = medicService.updatePatient(modifyPatient);
            
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, 
                    "Datos del paciente actualizados exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar los datos del paciente", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La edad debe ser un número entero válido (sin puntos ni comas)", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado al actualizar el paciente: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbtnModificarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // Preguntar si desea descartar los cambios
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea cancelar?\nSe perderán los cambios no guardados.", 
            "Confirmar Cancelación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Nota: Para testing, necesitas crear un Patient válido con Credentials
                // Patient tempPatient = new Patient("Nombre de Prueba", 30, "email@prueba.com", credentials);
                // new FrmModify(tempPatient).setVisible(true);
                JOptionPane.showMessageDialog(null, 
                    "Este formulario requiere un objeto Patient válido.\n" +
                    "Debe ser llamado desde otra ventana con un paciente existente.", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblModificarUsuario;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JPanel jpnlModifcar;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
    // End of variables declaration//GEN-END:variables
}