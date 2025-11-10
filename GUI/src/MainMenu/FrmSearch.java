import models.Patient;
import services.MedicSystemService;

import javax.swing.*;

/**
 * Formulario para buscar un paciente
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmSearch extends javax.swing.JFrame {

    private final MedicSystemService systemFacade;
    
    public FrmSearch(MedicSystemService systemFacade) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        this.systemFacade = systemFacade;
        initComponents();
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

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jlblTitulo.setText(" Buscar Usuario");
        jlblTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jlblNumeroDocumento.setText(" Numero Documento");
        jlblNumeroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtextNumeroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextNumeroDocumentoActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jbtnCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jbtnBuscarModificar)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnBuscarEliminar)
                            .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlblTitulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumeroDocumento)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnBuscarModificar)
                    .addComponent(jbtnBuscarEliminar))
                .addGap(18, 18, 18)
                .addComponent(jbtnCancelar)
                .addContainerGap(77, Short.MAX_VALUE))
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

    private void jtextNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {
        jbtnBuscarModificarActionPerformed(evt);
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextNumeroDocumento.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Patient patient = systemFacade.getPatientRepository().searchById(id);
            
            if (patient != null) {
                FrmModify frmModify = new FrmModify(systemFacade, patient);
                frmModify.setVisible(true);
                frmModify.setLocationRelativeTo(this);
                this.dispose();
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
                "El número de documento debe contener solo números válidos", 
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
    }
    
    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextNumeroDocumento.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Patient patient = systemFacade.getPatientRepository().searchById(id);
            
            if (patient != null) {
                FrmDelete frmDelete = new FrmDelete(systemFacade, patient);
                frmDelete.setVisible(true);
                frmDelete.setLocationRelativeTo(this);
                this.dispose();
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
                "El número de documento debe contener solo números válidos", 
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
    }

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnBuscarEliminar;
    private javax.swing.JButton jbtnBuscarModificar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JLabel jlblNumeroDocumento;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JTextField jtextNumeroDocumento;
}