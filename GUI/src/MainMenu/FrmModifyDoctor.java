import models.Doctor;
import models.Speciality;
import services.MedicSystemFacade;

import javax.swing.*;
import java.util.List;

/**
 * Formulario para modificar un médico
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmModifyDoctor extends javax.swing.JFrame {
    private final MedicSystemFacade systemFacade;
    private final Doctor currentDoctor;
    
    public FrmModifyDoctor(MedicSystemFacade systemFacade, Doctor doctor) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no puede ser nulo");
        }
        
        this.systemFacade = systemFacade;
        this.currentDoctor = doctor;
        initComponents();
        loadSpecialities();
        loadDoctorData();
    }
    
    private void loadSpecialities() {
        jcmbEspecialidad.removeAllItems();
        List<Speciality> specialities = systemFacade.getAllSpecialities();
        
        for (Speciality s : specialities) {
            jcmbEspecialidad.addItem(s);
        }
    }
    
    private void loadDoctorData() {
        jtextNombreCompleto.setText(currentDoctor.getFullName());
        jtextEdad.setText(String.valueOf(currentDoctor.getAge()));
        jtextEmail.setText(currentDoctor.getEmail());
        jtextNumeroDocumento.setText(String.valueOf(currentDoctor.getCredentials().getId()));
        jtextNumeroDocumento.setEnabled(false);
        
        // Seleccionar la especialidad actual
        for (int i = 0; i < jcmbEspecialidad.getItemCount(); i++) {
            Speciality s = jcmbEspecialidad.getItemAt(i);
            if (s.getSpecialityId() == currentDoctor.getSpeciality().getSpecialityId()) {
                jcmbEspecialidad.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void initComponents() {
        jpnlModificar = new javax.swing.JPanel();
        jlblModificarUsuario = new javax.swing.JLabel();
        jlblNombreCompleto = new javax.swing.JLabel();
        jtextNombreCompleto = new javax.swing.JTextField();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jlblContrasenna = new javax.swing.JLabel();
        jtextContrasenna = new javax.swing.JTextField();
        jlblEdad = new javax.swing.JLabel();
        jtextEdad = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jtextEmail = new javax.swing.JTextField();
        jlblEspecialidad = new javax.swing.JLabel();
        jcmbEspecialidad = new javax.swing.JComboBox<>();
        jbtnModificar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Medico");
        setResizable(false);

        jlblModificarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jlblModificarUsuario.setText(" Modificar Medico");

        jlblNombreCompleto.setText(" Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText(" Numero Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblContrasenna.setText("       Contraseña");
        jlblContrasenna.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEdad.setText("            Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail.setText("            Email");
        jlblEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEspecialidad.setText("     Especialidad");
        jlblEspecialidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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

        javax.swing.GroupLayout jpnlModificarLayout = new javax.swing.GroupLayout(jpnlModificar);
        jpnlModificar.setLayout(jpnlModificarLayout);
        jpnlModificarLayout.setHorizontalGroup(
            jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlModificarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlModificarLayout.createSequentialGroup()
                        .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtextNumeroDocumento))
                    .addGroup(jpnlModificarLayout.createSequentialGroup()
                        .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtextNombreCompleto))
                    .addGroup(jpnlModificarLayout.createSequentialGroup()
                        .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlblEspecialidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jlblEdad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblContrasenna, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtextContrasenna)
                            .addComponent(jtextEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jtextEmail)
                            .addComponent(jcmbEspecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
            .addGroup(jpnlModificarLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jlblModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnlModificarLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jbtnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCancelar)
                .addGap(79, 79, 79))
        );
        jpnlModificarLayout.setVerticalGroup(
            jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlModificarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jlblModificarUsuario)
                .addGap(18, 18, 18)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblContrasenna)
                    .addComponent(jtextContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEspecialidad)
                    .addComponent(jcmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnModificar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String name = jtextNombreCompleto.getText().trim();
            String ageText = jtextEdad.getText().trim();
            String email = jtextEmail.getText().trim();
            String password = jtextContrasenna.getText().trim();

            if (name.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos obligatorios");
                return;
            }

            int age = Integer.parseInt(ageText);

            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, "La edad debe estar entre 1 y 150");
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Email inválido");
                return;
            }

            if (!password.isEmpty() && password.length() < 4) {
                JOptionPane.showMessageDialog(this, 
                    "La contraseña debe tener al menos 4 caracteres");
                return;
            }

            Speciality speciality = (Speciality) jcmbEspecialidad.getSelectedItem();
            if (speciality == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una especialidad");
                return;
            }

            currentDoctor.setFullName(name);
            currentDoctor.setAge(age);
            currentDoctor.setEmail(email);
            currentDoctor.setSpecialty(speciality);
            
            if (!password.isEmpty()) {
                currentDoctor.getCredentials().setPassword(password);
            }

            boolean updated = systemFacade.updateDoctor(currentDoctor);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Médico actualizado correctamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el médico");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    // Variables declaration
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JComboBox<Speciality> jcmbEspecialidad;
    private javax.swing.JLabel jlblContrasenna;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblEspecialidad;
    private javax.swing.JLabel jlblModificarUsuario;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JPanel jpnlModificar;
    private javax.swing.JTextField jtextContrasenna;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
}