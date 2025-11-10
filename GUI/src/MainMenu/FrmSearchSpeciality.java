import models.Speciality;
import services.MedicSystemService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario de búsqueda de especialidades
 */
public class FrmSearchSpeciality extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    public FrmSearchSpeciality() {
        this.medicService = new MedicSystemService();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public FrmSearchSpeciality(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelSearch = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jPanelButtons = new javax.swing.JPanel();
        jbtnBuscarModificar = new javax.swing.JButton();
        jbtnBuscarEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Especialidad");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 26));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText(" BUSCAR ESPECIALIDAD");

        // Panel de búsqueda
        jPanelSearch.setBackground(Color.WHITE);
        jPanelSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        jlblId.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblId.setForeground(new java.awt.Color(52, 73, 94));
        jlblId.setText("ID de Especialidad:");

        jtextId.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jtextId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        jtextId.setToolTipText("Ingrese el ID de la especialidad a buscar");

        javax.swing.GroupLayout jPanelSearchLayout = new javax.swing.GroupLayout(jPanelSearch);
        jPanelSearch.setLayout(jPanelSearchLayout);
        jPanelSearchLayout.setHorizontalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblId)
            .addComponent(jtextId, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        jPanelSearchLayout.setVerticalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchLayout.createSequentialGroup()
                .addComponent(jlblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Panel de botones
        jPanelButtons.setBackground(new java.awt.Color(245, 245, 245));

        jbtnBuscarModificar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnBuscarModificar.setText(" Buscar y Modificar");
        jbtnBuscarModificar.setBackground(new java.awt.Color(52, 152, 219));
        jbtnBuscarModificar.setForeground(Color.WHITE);
        jbtnBuscarModificar.setFocusPainted(false);
        jbtnBuscarModificar.setBorderPainted(false);
        jbtnBuscarModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnBuscarModificar.setToolTipText("Buscar especialidad para modificar");
        jbtnBuscarModificar.addActionListener(evt -> jbtnBuscarModificarActionPerformed(evt));
        jbtnBuscarModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnBuscarModificar.setBackground(new java.awt.Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnBuscarModificar.setBackground(new java.awt.Color(52, 152, 219));
            }
        });

        jbtnBuscarEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnBuscarEliminar.setText(" Buscar y Eliminar");
        jbtnBuscarEliminar.setBackground(new java.awt.Color(231, 76, 60));
        jbtnBuscarEliminar.setForeground(Color.WHITE);
        jbtnBuscarEliminar.setFocusPainted(false);
        jbtnBuscarEliminar.setBorderPainted(false);
        jbtnBuscarEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnBuscarEliminar.setToolTipText("Buscar especialidad para eliminar");
        jbtnBuscarEliminar.addActionListener(evt -> jbtnBuscarEliminarActionPerformed(evt));
        jbtnBuscarEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnBuscarEliminar.setBackground(new java.awt.Color(192, 57, 43));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnBuscarEliminar.setBackground(new java.awt.Color(231, 76, 60));
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnCancelar.setText(" Cancelar");
        jbtnCancelar.setBackground(new java.awt.Color(189, 195, 199));
        jbtnCancelar.setForeground(new java.awt.Color(52, 73, 94));
        jbtnCancelar.setFocusPainted(false);
        jbtnCancelar.setBorderPainted(false);
        jbtnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnCancelar.addActionListener(evt -> dispose());
        jbtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(149, 165, 166));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(189, 195, 199));
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnBuscarModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
            .addComponent(jbtnBuscarEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addComponent(jbtnBuscarModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jbtnBuscarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Layout principal
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jbtnBuscarModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    " Debe ingresar un ID de especialidad",
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    " El ID debe ser un número positivo",
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            // BUSCAR USANDO MÉTODO PÚBLICO DEL SERVICIO
            Speciality speciality = medicService.getSpecialityRepository().searchById(id);
            
            if (speciality != null) {
                FrmModifySpeciality frmModify = new FrmModifySpeciality(speciality, medicService);
                frmModify.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    " No se encontró especialidad con ID: " + id,
                    "No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                " El ID debe ser un número válido",
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                " Error al buscar: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void jbtnBuscarEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    " Debe ingresar un ID de especialidad",
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El ID debe ser un número positivo",
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            //  BUSCAR USANDO MÉTODO PÚBLICO DEL SERVICIO
            Speciality speciality = medicService.getSpecialityRepository().searchById(id);
            
            if (speciality != null) {
                FrmDeleteSpeciality frmDelete = new FrmDeleteSpeciality();
                frmDelete.setSpecialityToDelete(speciality);
                frmDelete.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    " No se encontró especialidad con ID: " + id,
                    "No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                " El ID debe ser un número válido",
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextId.selectAll();
            jtextId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                " Error al buscar: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnBuscarEliminar;
    private javax.swing.JButton jbtnBuscarModificar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JLabel jlblId;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JTextField jtextId;
}