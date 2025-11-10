import javax.swing.JOptionPane;
import models.Patient;
import services.MedicSystemFacade;

/**
 * Formulario para modificar un paciente
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmModify extends javax.swing.JFrame {

    private final Patient currentPatient;
    private final MedicSystemFacade systemFacade;
    
    public FrmModify(MedicSystemFacade systemFacade, Patient patient) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient no puede ser nulo");
        }
        
        this.currentPatient = patient;
        this.systemFacade = systemFacade;
        initComponents();
        loadPatientData();
    }
    
    private void loadPatientData() {
        jtextNombreCompleto.setText(currentPatient.getFullName());
        jtextEdad.setText(String.valueOf(currentPatient.getAge()));
        jtextEmail.setText(currentPatient.getEmail());
        jtextNumeroDocumento.setText(String.valueOf(currentPatient.getCredentials().getId()));
        jtextNumeroDocumento.setEnabled(false);
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
        jlblContrasenna = new javax.swing.JLabel();
        jtextContrasenna = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Paciente");
        setResizable(false);

        jlblModificarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jlblModificarUsuario.setText(" Modificar Usuario");

        jlblNumeroDocumento.setText(" Numero Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombreCompleto.setText(" Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEdad.setText("            Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail.setText("            Email");
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

        jlblContrasenna.setText("       Contraseña");
        jlblContrasenna.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jpnlModifcarLayout = new javax.swing.GroupLayout(jpnlModifcar);
        jpnlModifcar.setLayout(jpnlModifcarLayout);
        jpnlModifcarLayout.setHorizontalGroup(
            jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlModifcarLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(jlblModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(jpnlModifcarLayout.createSequentialGroup()
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jbtnModificar)
                        .addGap(68, 68, 68)
                        .addComponent(jbtnCancelar))
                    .addGroup(jpnlModifcarLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jlblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                            .addComponent(jlblContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtextNombreCompleto)
                            .addComponent(jtextContrasenna)
                            .addComponent(jtextNumeroDocumento)
                            .addComponent(jtextEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(jtextEmail))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlModifcarLayout.setVerticalGroup(
            jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlModifcarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jlblModificarUsuario)
                .addGap(18, 18, 18)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblContrasenna)
                    .addComponent(jtextContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jpnlModifcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnModificar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(69, Short.MAX_VALUE))
        );

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

        pack();
    }

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nameInput = jtextNombreCompleto.getText().trim();
            String ageInput = jtextEdad.getText().trim();
            String emailInput = jtextEmail.getText().trim();
            String password = jtextContrasenna.getText().trim();
            
            if (nameInput.isEmpty() || ageInput.isEmpty() || emailInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe llenar todos los campos obligatorios", 
                    "Campos Vacíos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int age = Integer.parseInt(ageInput);
            
            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, 
                    "La edad debe estar entre 1 y 150", 
                    "Edad Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!emailInput.contains("@") || !emailInput.contains(".")) {
                JOptionPane.showMessageDialog(this, 
                    "El email no tiene un formato válido", 
                    "Email Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (nameInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, 
                    "El nombre no puede contener solo números", 
                    "Nombre Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!password.isEmpty() && password.length() < 4) {
                JOptionPane.showMessageDialog(this, 
                    "La contraseña debe tener al menos 4 caracteres", 
                    "Contraseña Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean hasChanges = !nameInput.equals(currentPatient.getFullName()) ||
                                age != currentPatient.getAge() ||
                                !emailInput.equals(currentPatient.getEmail()) ||
                                !password.isEmpty();
            
            if (!hasChanges) {
                JOptionPane.showMessageDialog(this, 
                    "No se han realizado cambios", 
                    "Sin Cambios", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            currentPatient.setFullName(nameInput);
            currentPatient.setAge(age);
            currentPatient.setEmail(emailInput);
            
            if (!password.isEmpty()) {
                currentPatient.getCredentials().setPassword(password);
            }
            
            boolean isUpdated = systemFacade.updatePatient(currentPatient);
            
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, 
                    "Paciente actualizado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar el paciente", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La edad debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea cancelar?\nSe perderán los cambios no guardados.", 
            "Confirmar Cancelación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    // Variables declaration
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JLabel jlblContrasenna;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblModificarUsuario;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JPanel jpnlModifcar;
    private javax.swing.JTextField jtextContrasenna;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
}