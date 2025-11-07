/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.JOptionPane;
import Services.*;
/**
 *
 * @author WINDOWS 11
 */
public class FrmAddPatient extends javax.swing.JFrame {

    private MedicSystemService medicService;
    
    public FrmAddPatient() {
        initComponents();
        this.medicService = new MedicSystemService(); // Inicializar el servicio
    }
    
    // Constructor que acepta el servicio (útil para inyección de dependencias)
    public FrmAddPatient(MedicSystemService medicService) {
        initComponents();
        this.medicService = medicService;
    }

   
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jlblCrearPaciente = new javax.swing.JLabel();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jlblNombreCompleto = new javax.swing.JLabel();
        jtextNombreCompleto = new javax.swing.JTextField();
        jlblEdad = new javax.swing.JLabel();
        jtextEdad = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jtextEmail = new javax.swing.JTextField();
        jlblPassword = new javax.swing.JLabel();
        jpasswordField = new javax.swing.JPasswordField();
        jlblConfirmPassword = new javax.swing.JLabel();
        jpasswordConfirmField = new javax.swing.JPasswordField();
        jbtGuardar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Paciente");
        setResizable(false);

        jlblCrearPaciente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jlblCrearPaciente.setText(" CREAR PACIENTE");
        jlblCrearPaciente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText("   Número Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombreCompleto.setText("    Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEdad.setText("             Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail.setText("             Email");
        jlblEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblPassword.setText("         Contraseña");
        jlblPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblConfirmPassword.setText("   Confirmar Contraseña");
        jlblConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jbtGuardar.setText("Guardar");
        jbtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpasswordConfirmField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtGuardar)
                        .addGap(75, 75, 75)
                        .addComponent(jbtCancelar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlblCrearPaciente)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblPassword)
                    .addComponent(jpasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblConfirmPassword)
                    .addComponent(jpasswordConfirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtGuardar)
                    .addComponent(jbtCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jlblCrearPaciente.getAccessibleContext().setAccessibleParent(jlblCrearPaciente);
        jlblNumeroDocumento.getAccessibleContext().setAccessibleParent(jlblNumeroDocumento);
        jtextNumeroDocumento.getAccessibleContext().setAccessibleParent(jtextNumeroDocumento);
        jlblNombreCompleto.getAccessibleContext().setAccessibleParent(jlblNombreCompleto);
        jtextNombreCompleto.getAccessibleContext().setAccessibleParent(jtextNombreCompleto);
        jlblEdad.getAccessibleContext().setAccessibleParent(jlblEdad);
        jtextEdad.getAccessibleContext().setAccessibleName("");
        jtextEdad.getAccessibleContext().setAccessibleParent(jlblEdad);
        jlblEmail.getAccessibleContext().setAccessibleParent(jlblEmail);
        jtextEmail.getAccessibleContext().setAccessibleParent(jlblEmail);
        jlblPassword.getAccessibleContext().setAccessibleParent(jlblPassword);
        jpasswordField.getAccessibleContext().setAccessibleParent(jlblPassword);
        jlblConfirmPassword.getAccessibleContext().setAccessibleParent(jlblConfirmPassword);
        jpasswordConfirmField.getAccessibleContext().setAccessibleParent(jlblConfirmPassword);
        jbtGuardar.getAccessibleContext().setAccessibleParent(jbtGuardar);
        jbtCancelar.getAccessibleContext().setAccessibleParent(jbtCancelar);

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
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarActionPerformed
        try {
            // Obtener y validar campos antes de parsear
            String nameInput = jtextNombreCompleto.getText().trim();
            String idInput = jtextNumeroDocumento.getText().trim();
            String ageInput = jtextEdad.getText().trim();
            String emailInput = jtextEmail.getText().trim();
            String passwordInput = new String(jpasswordField.getPassword()).trim();
            String confirmPasswordInput = new String(jpasswordConfirmField.getPassword()).trim();
            
            // Validar que no estén vacíos ANTES de parsear
            if (nameInput.isEmpty() || idInput.isEmpty() || ageInput.isEmpty() || 
                emailInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
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
            
            // Ahora parsear los valores
            int id = Integer.parseInt(idInput);
            int age = Integer.parseInt(ageInput);
            
            // Validar que la edad sea positiva y realista
            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, 
                    "La edad debe ser un número entre 1 y 150", 
                    "Edad Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que el ID sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser un número positivo", 
                    "Documento Inválido", 
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
            
            // Validar longitud mínima de la contraseña
            if (passwordInput.length() < 6) {
                JOptionPane.showMessageDialog(this, 
                    "La contraseña debe tener al menos 6 caracteres", 
                    "Contraseña Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que las contraseñas coincidan
            if (!passwordInput.equals(confirmPasswordInput)) {
                JOptionPane.showMessageDialog(this, 
                    "Las contraseñas no coinciden", 
                    "Error de Contraseña", 
                    JOptionPane.WARNING_MESSAGE);
                // Limpiar campos de contraseña
                jpasswordField.setText("");
                jpasswordConfirmField.setText("");
                jpasswordField.requestFocus();
                return;
            }
            
            // Registrar el paciente con la contraseña ingresada
            // ORDEN CORRECTO según MedicSystemService: fullName, id, password, age, email
            boolean isAdded = medicService.registerPatient(nameInput, id, passwordInput, age, emailInput);
            
            if (!isAdded) {
                JOptionPane.showMessageDialog(this, 
                    "Error al agregar el paciente. Es posible que el documento ya esté registrado.", 
                    "Error al Registrar", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, 
                "Paciente agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después del éxito
            jtextNombreCompleto.setText("");
            jtextNumeroDocumento.setText("");
            jtextEdad.setText("");
            jtextEmail.setText("");
            jpasswordField.setText("");
            jpasswordConfirmField.setText("");
            
            // Enfocar el primer campo para facilitar el ingreso de otro paciente
            jtextNombreCompleto.requestFocus();
            
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El número de documento y la edad deben contener solo números válidos", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Para debug
        }
    }//GEN-LAST:event_jbtGuardarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAddPatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtGuardar;
    private javax.swing.JLabel jlblConfirmPassword;
    private javax.swing.JLabel jlblCrearPaciente;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JLabel jlblPassword;
    private javax.swing.JPasswordField jpasswordConfirmField;
    private javax.swing.JPasswordField jpasswordField;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
    // End of variables declaration//GEN-END:variables
}