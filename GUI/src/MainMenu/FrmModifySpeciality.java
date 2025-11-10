import models.Speciality;
import services.MedicSystemService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para modificar especialidad
 */
public class FrmModifySpeciality extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private final Speciality originalSpeciality;

    public FrmModifySpeciality(Speciality speciality, MedicSystemService medicService) {
        this.originalSpeciality = speciality;
        this.medicService = medicService;
        initComponents();
        loadSpecialityData();
        setLocationRelativeTo(null);
    }

    /**
     * Carga los datos de la especialidad en los campos
     */
    private void loadSpecialityData() {
        if (originalSpeciality != null) {
            jtextId.setText(String.valueOf(originalSpeciality.getSpecialityId()));
            jtextId.setEnabled(false);
            jtextNombre.setText(originalSpeciality.getName());
            jtextAreaDescripcion.setText(originalSpeciality.getDescription());
        }
    }

    private void initComponents() {
        jPanelMain = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelForm = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jlblNombre = new javax.swing.JLabel();
        jtextNombre = new javax.swing.JTextField();
        jlblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtextAreaDescripcion = new javax.swing.JTextArea();
        jPanelButtons = new javax.swing.JPanel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Especialidad");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // ========== PANEL HEADER ==========
        jPanelHeader.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 26));
        jlblTitulo.setForeground(new java.awt.Color(52, 152, 219));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("✏️ MODIFICAR ESPECIALIDAD");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
                jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1)
        );
        jPanelHeaderLayout.setVerticalGroup(
                jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // ========== PANEL FORM ==========
        jPanelForm.setBackground(Color.WHITE);
        jPanelForm.setBorder(BorderFactory.createCompoundBorder(
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
        jtextId.setEnabled(false);
        jtextId.setBackground(new java.awt.Color(230, 230, 230));
        jtextId.setToolTipText("El ID no se puede modificar");

        jlblNombre.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblNombre.setForeground(new java.awt.Color(52, 73, 94));
        jlblNombre.setText("Nombre de la Especialidad:");

        jtextNombre.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jtextNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        jtextNombre.setToolTipText("Modifique el nombre de la especialidad");

        jlblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jlblDescripcion.setForeground(new java.awt.Color(52, 73, 94));
        jlblDescripcion.setText("Descripción:");

        jtextAreaDescripcion.setColumns(20);
        jtextAreaDescripcion.setRows(5);
        jtextAreaDescripcion.setLineWrap(true);
        jtextAreaDescripcion.setWrapStyleWord(true);
        jtextAreaDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jtextAreaDescripcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        jtextAreaDescripcion.setToolTipText("Modifique la descripción de la especialidad");
        jScrollPane1.setViewportView(jtextAreaDescripcion);
        jScrollPane1.setBorder(null);

        // Layout del formulario
        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
                jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlblId)
                        .addComponent(jtextId)
                        .addComponent(jlblNombre)
                        .addComponent(jtextNombre)
                        .addComponent(jlblDescripcion)
                        .addComponent(jScrollPane1)
        );
        jPanelFormLayout.setVerticalGroup(
                jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jlblId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlblDescripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // ========== PANEL BUTTONS ==========
        jPanelButtons.setBackground(new java.awt.Color(245, 245, 245));

        jbtnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jbtnGuardar.setText("💾 Guardar Cambios");
        jbtnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        jbtnGuardar.setForeground(Color.WHITE);
        jbtnGuardar.setFocusPainted(false);
        jbtnGuardar.setBorderPainted(false);
        jbtnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnGuardar.setToolTipText("Guardar los cambios realizados");
        jbtnGuardar.addActionListener(evt -> jbtnGuardarActionPerformed(evt));
        jbtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnGuardar.setBackground(new java.awt.Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnGuardar.setBackground(new java.awt.Color(46, 204, 113));
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jbtnCancelar.setText("❌ Cancelar");
        jbtnCancelar.setBackground(new java.awt.Color(231, 76, 60));
        jbtnCancelar.setForeground(Color.WHITE);
        jbtnCancelar.setFocusPainted(false);
        jbtnCancelar.setBorderPainted(false);
        jbtnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnCancelar.setToolTipText("Cancelar sin guardar cambios");
        jbtnCancelar.addActionListener(evt -> dispose());
        jbtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(192, 57, 43));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnCancelar.setBackground(new java.awt.Color(231, 76, 60));
            }
        });

        // Layout de botones
        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
                jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelButtonsLayout.createSequentialGroup()
                                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelButtonsLayout.setVerticalGroup(
                jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        // ========== LAYOUT PRINCIPAL (AQUÍ ESTABA EL ERROR) ==========
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);

        jPanelMainLayout.setHorizontalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(10, Short.MAX_VALUE))
        );

        // ✅ AQUÍ FALTABA ESTE VERTICAL GROUP
        jPanelMainLayout.setVerticalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        // ========== LAYOUT DEL CONTENT PANE ==========
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

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombreInput = jtextNombre.getText().trim();
            String descripcionInput = jtextAreaDescripcion.getText().trim();

            if (nombreInput.isEmpty() || descripcionInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "⚠️ Todos los campos son obligatorios.",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nombreInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                        "❌ El nombre no puede contener solo números.",
                        "Nombre Inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nombreInput.length() < 3 || nombreInput.length() > 100) {
                JOptionPane.showMessageDialog(this,
                        "❌ El nombre debe tener entre 3 y 100 caracteres.",
                        "Longitud Inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (descripcionInput.length() < 10 || descripcionInput.length() > 500) {
                JOptionPane.showMessageDialog(this,
                        "❌ La descripción debe tener entre 10 y 500 caracteres.",
                        "Longitud Inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean hasChanges = !nombreInput.equals(originalSpeciality.getName()) ||
                    !descripcionInput.equals(originalSpeciality.getDescription());

            if (!hasChanges) {
                JOptionPane.showMessageDialog(this,
                        "ℹ️ No se detectaron cambios en los datos.",
                        "Sin Cambios",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            originalSpeciality.setName(nombreInput);
            originalSpeciality.setDescription(descripcionInput);

            boolean isUpdated = medicService.updateSpeciality(originalSpeciality);

            if (!isUpdated) {
                JOptionPane.showMessageDialog(this,
                        "❌ No se pudo actualizar la especialidad.",
                        "Error al Actualizar",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "✅ Especialidad actualizada exitosamente",
                    "Actualización Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "❌ Error inesperado: " + e.getMessage(),
                    "Error Crítico",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextField jtextId;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JTextField jtextNombre;
    private javax.swing.JLabel jlblDescripcion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtextAreaDescripcion;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnCancelar;
}