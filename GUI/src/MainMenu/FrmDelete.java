import models.Patient;
import services.MedicSystemService;

import javax.swing.*;

/**
 * Formulario para eliminar un paciente
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmDelete extends javax.swing.JFrame {

    private final MedicSystemService systemFacade;
    private final Patient currentPatient;

    public FrmDelete(MedicSystemService systemFacade, Patient patient) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient no puede ser nulo");
        }
        
        this.systemFacade = systemFacade;
        this.currentPatient = patient;
        initComponents();
        loadPatientData();
    }
    
    private void loadPatientData() {
        jtextNombreCompleto.setText(currentPatient.getFullName());
        jtextNombreCompleto.setEnabled(false);
        
        jtextNumeroDocumento.setText(String.valueOf(currentPatient.getCredentials().getId()));
        jtextNumeroDocumento.setEnabled(false);
        
        jtextEdad.setText(String.valueOf(currentPatient.getAge()));
        jtextEdad.setEnabled(false);
        
        jtextEmail.setText(currentPatient.getEmail());
        jtextEmail.setEnabled(false);
    }
    
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblEliminarPaciente = new javax.swing.JLabel();
        jlblNombreCompleto = new javax.swing.JLabel();
        jtextNombreCompleto = new javax.swing.JTextField();
        jlblEdad = new javax.swing.JLabel();
        jtextEdad = new javax.swing.JTextField();
        jlblEmail = new javax.swing.JLabel();
        jtextEmail = new javax.swing.JTextField();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Paciente");
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 350));

        jlblEliminarPaciente.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jlblEliminarPaciente.setText(" Eliminar Paciente");
        jlblEliminarPaciente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNombreCompleto.setText("  Nombre Completo");
        jlblNombreCompleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextNombreCompleto.setBackground(new java.awt.Color(240, 240, 240));

        jlblEdad.setText("            Edad");
        jlblEdad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextEdad.setBackground(new java.awt.Color(240, 240, 240));

        jlblEmail.setText("           Email");
        jlblEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextEmail.setBackground(new java.awt.Color(240, 240, 240));

        jlblNumeroDocumento.setText(" Numero Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextNumeroDocumento.setBackground(new java.awt.Color(240, 240, 240));

        jbtnEliminar.setText("Eliminar");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jlblEliminarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jlblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtextNombreCompleto)
                            .addComponent(jtextEdad)
                            .addComponent(jtextEmail)
                            .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jbtnEliminar)
                        .addGap(73, 73, 73)
                        .addComponent(jbtnCancelar)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlblEliminarPaciente)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombreCompleto)
                    .addComponent(jtextNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEdad)
                    .addComponent(jtextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEliminar)
                    .addComponent(jbtnCancelar))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de eliminar este paciente?\n\n" +
            "Nombre: " + currentPatient.getFullName() + "\n" +
            "Documento: " + currentPatient.getCredentials().getId() + "\n\n" +
            "Esta acción no se puede deshacer.",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = systemFacade.removePatient(currentPatient);
                
                if (success) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Paciente eliminado exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar el paciente.\n" +
                        "Puede que tenga citas asociadas.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JLabel jlblEdad;
    private javax.swing.JLabel jlblEliminarPaciente;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblNombreCompleto;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JTextField jtextEdad;
    private javax.swing.JTextField jtextEmail;
    private javax.swing.JTextField jtextNombreCompleto;
    private javax.swing.JTextField jtextNumeroDocumento;
}