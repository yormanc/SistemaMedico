import javax.swing.JOptionPane;
import services.MedicSystemFacade;
import models.Doctor;
import services.MedicSystemService;

/**
 * Formulario para buscar un médico
 * ACTUALIZADO para usar MedicSystemFacade
 */
public class FrmSearchDoctor extends javax.swing.JFrame {

    private final MedicSystemService systemFacade;

    public FrmSearchDoctor(MedicSystemService systemFacade) {
        if (systemFacade == null) {
            throw new IllegalArgumentException("MedicSystemFacade no puede ser nulo");
        }
        this.systemFacade = systemFacade;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblNumeroDocumento = new javax.swing.JLabel();
        jtextNumeroDocumento = new javax.swing.JTextField();
        jbtnBuscarModificar = new javax.swing.JButton();
        jbtnBuscarEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Medico");
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(350, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 250));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jlblTitulo.setText(" Buscar Médico");
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnBuscarModificar)
                    .addComponent(jlblNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtextNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscarEliminar))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnCancelar)
                .addGap(133, 133, 133))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
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
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jtextNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {
        jbtnBuscarModificarActionPerformed(evt);
    }

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idText = jtextNumeroDocumento.getText().trim();
            
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Doctor doctor = systemFacade.getDoctorRepository().searchById(id);

            if (doctor != null) {
                FrmModifyDoctor frmModify = new FrmModifyDoctor(systemFacade, doctor);
                frmModify.setVisible(true);
                frmModify.setLocationRelativeTo(this);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ningún médico con el documento: " + id, 
                    "Médico No Encontrado", 
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
                "Error al buscar el médico: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idText = jtextNumeroDocumento.getText().trim();
            
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un número de documento", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El número de documento debe ser positivo", 
                    "Documento Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Doctor doctor = systemFacade.getDoctorRepository().searchById(id);

            if (doctor != null) {
                FrmDeleteDoctor frmDeleteDoctor = new FrmDeleteDoctor(systemFacade, doctor);
                frmDeleteDoctor.setVisible(true);
                frmDeleteDoctor.setLocationRelativeTo(this);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró ningún médico con el documento: " + id, 
                    "Médico No Encontrado", 
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
                "Error al buscar el médico: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
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