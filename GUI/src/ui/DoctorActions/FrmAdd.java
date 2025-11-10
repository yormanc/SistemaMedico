import javax.swing.JOptionPane;
import services.MedicSystemFacade;
import models.Speciality;
import java.util.List;

/**
 * Formulario para agregar un nuevo médico
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmAdd extends javax.swing.JFrame {
    private final MedicSystemFacade systemFacade;
    
    public FrmAdd(MedicSystemFacade systemFacade) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        this.systemFacade = systemFacade;
        initComponents();
        loadSpecialities();
    }

    private void loadSpecialities() {
        jcmbEspecialidad.removeAllItems();
        List<Speciality> specialities = systemFacade.getAllSpecialities();
        
        if (specialities.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay especialidades registradas en el sistema.\n" +
                "Por favor, registre especialidades primero.",
                "Sin Especialidades",
                JOptionPane.WARNING_MESSAGE);
            this.dispose();
            return;
        }
        
        for (Speciality s : specialities) {
            jcmbEspecialidad.addItem(s);
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblCrearMedico = new javax.swing.JLabel();
        jlblNombreCompleto = new javax.swing.JLabel();
        jtextNombreCompleto = new javax.swing.JTextField();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jlblContrasenaa = new javax.swing.JLabel();
        jtextContrasenna = new javax.swing.JTextField();
        jlblEdad = new javax.swing.JLabel();
        jtextEdad = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jtextEmail = new javax.swing.JTextField();
        jlblEmail1 = new javax.swing.JLabel();
        jcmbEspecialidad = new javax.swing.JComboBox<>();
        jbtGuardar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Medico");
        setPreferredSize(new java.awt.Dimension(400, 388));
        setResizable(false);

        jlblCrearMedico.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        jlblCrearMedico.setText("  CREAR MEDICO");
        jlblCrearMedico.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombreCompleto.setText("    Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText("   Número Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblContrasenaa.setText("        Contraseña");
        jlblContrasenaa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEdad.setText("             Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail.setText("             Email");
        jlblEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblEmail1.setText("           Especialidad");
        jlblEmail1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
                .addComponent(jlblCrearMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtextNumeroDocumento))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblContrasenaa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtextContrasenna))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcmbEspecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jlblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jbtGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtCancelar)
                .addGap(82, 82, 82))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jlblCrearMedico)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblContrasenaa)
                    .addComponent(jtextContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail1)
                    .addComponent(jcmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtGuardar)
                    .addComponent(jbtCancelar))
                .addContainerGap(43, Short.MAX_VALUE))
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

    private void jbtGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String name = jtextNombreCompleto.getText().trim();
            String idText = jtextNumeroDocumento.getText().trim();
            String password = jtextContrasenna.getText().trim();
            String ageText = jtextEdad.getText().trim();
            String email = jtextEmail.getText().trim();

            if (name.isEmpty() || idText.isEmpty() || password.isEmpty() || 
                ageText.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                return;
            }

            if (jcmbEspecialidad.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una especialidad");
                return;
            }

            int id = Integer.parseInt(idText);
            int age = Integer.parseInt(ageText);

            if (age <= 0 || age > 150) {
                JOptionPane.showMessageDialog(this, "La edad debe estar entre 1 y 150");
                return;
            }

            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "El número de documento debe ser positivo");
                return;
            }

            if (password.length() < 4) {
                JOptionPane.showMessageDialog(this, 
                    "La contraseña debe tener al menos 4 caracteres");
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Email inválido");
                return;
            }

            if (name.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, 
                    "El nombre no puede contener solo números");
                return;
            }

            Speciality speciality = (Speciality) jcmbEspecialidad.getSelectedItem();

            boolean success = systemFacade.registerDoctor(name, id, password, age, email, speciality);

            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Médico agregado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                jtextNombreCompleto.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al agregar el médico. El documento ya está registrado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Edad e ID deben ser números válidos",
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

    private void clearFields() {
        jtextNombreCompleto.setText("");
        jtextEdad.setText("");
        jtextEmail.setText("");
        jtextNumeroDocumento.setText("");
        jtextContrasenna.setText("");
        if (jcmbEspecialidad.getItemCount() > 0) {
            jcmbEspecialidad.setSelectedIndex(0);
        }
    }

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtGuardar;
    private javax.swing.JComboBox<Speciality> jcmbEspecialidad;
    private javax.swing.JLabel jlblContrasenaa;
    private javax.swing.JLabel jlblCrearMedico;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblEmail1;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JTextField jtextContrasenna;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
}