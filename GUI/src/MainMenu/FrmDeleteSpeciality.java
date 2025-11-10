import models.Speciality;
import services.MedicSystemService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para eliminar especialidad
 */
public class FrmDeleteSpeciality extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    private Speciality currentSpeciality = null;

    public FrmDeleteSpeciality() {
        this.medicService = new MedicSystemService();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public FrmDeleteSpeciality(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Método para cargar especialidad desde FrmSearchSpeciality
     */
    public void setSpecialityToDelete(Speciality speciality) {
        if (speciality == null) return;
        
        this.currentSpeciality = speciality;
        jtextId.setText(String.valueOf(speciality.getSpecialityId()));
        jtextId.setEnabled(false);
        
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(speciality.getSpecialityId()).append("\n");
        info.append("Nombre: ").append(speciality.getName()).append("\n");
        info.append("Descripción: ").append(speciality.getDescription()).append("\n");
        
        jtextInfo.setText(info.toString());
        jbtnEliminar.setEnabled(true);
        jbtnBuscar.setEnabled(false);
    }
    private void initComponents() {
        jPanelMain = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelSearch = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jbtnBuscar = new javax.swing.JButton();
        jPanelInfo = new javax.swing.JPanel();
        jlblInfoEspecialidad = new javax.swing.JLabel();
        jtextAreaInfo = new javax.swing.JScrollPane();
        jtextInfo = new javax.swing.JTextArea();
        jPanelButtons = new javax.swing.JPanel();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Especialidad");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 26));
        jlblTitulo.setForeground(new java.awt.Color(231, 76, 60));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("🗑️ ELIMINAR ESPECIALIDAD");

        // Panel de búsqueda
        jPanelSearch.setBackground(Color.WHITE);
        jPanelSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        jlblId.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jlblId.setForeground(new java.awt.Color(52, 73, 94));
        jlblId.setText("ID de Especialidad:");

        jtextId.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jtextId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        jbtnBuscar.setText("🔍 Buscar");
        jbtnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jbtnBuscar.setBackground(new java.awt.Color(52, 152, 219));
        jbtnBuscar.setForeground(Color.WHITE);
        jbtnBuscar.setFocusPainted(false);
        jbtnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnBuscar.addActionListener(evt -> jbtnBuscarActionPerformed(evt));

        javax.swing.GroupLayout jPanelSearchLayout = new javax.swing.GroupLayout(jPanelSearch);
        jPanelSearch.setLayout(jPanelSearchLayout);
        jPanelSearchLayout.setHorizontalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblId)
            .addGroup(jPanelSearchLayout.createSequentialGroup()
                .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jbtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelSearchLayout.setVerticalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchLayout.createSequentialGroup()
                .addComponent(jlblId)
                .addGap(8, 8, 8)
                .addGroup(jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        // Panel de información
        jPanelInfo.setBackground(Color.WHITE);
        jPanelInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        jlblInfoEspecialidad.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jlblInfoEspecialidad.setForeground(new java.awt.Color(52, 73, 94));
        jlblInfoEspecialidad.setText("Información de la Especialidad:");

        jtextInfo.setEditable(false);
        jtextInfo.setColumns(20);
        jtextInfo.setRows(5);
        jtextInfo.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jtextInfo.setBackground(new java.awt.Color(250, 250, 250));
        jtextInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jtextAreaInfo.setViewportView(jtextInfo);
        jtextAreaInfo.setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 1));

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblInfoEspecialidad)
            .addComponent(jtextAreaInfo)
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addComponent(jlblInfoEspecialidad)
                .addGap(8, 8, 8)
                .addComponent(jtextAreaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Panel de botones
        jPanelButtons.setBackground(new java.awt.Color(245, 245, 245));

        jbtnEliminar.setText("🗑️ Eliminar Especialidad");
        jbtnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnEliminar.setBackground(new java.awt.Color(231, 76, 60));
        jbtnEliminar.setForeground(Color.WHITE);
        jbtnEliminar.setFocusPainted(false);
        jbtnEliminar.setBorderPainted(false);
        jbtnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnEliminar.setEnabled(false);
        jbtnEliminar.addActionListener(evt -> jbtnEliminarActionPerformed(evt));
        jbtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (jbtnEliminar.isEnabled()) {
                    jbtnEliminar.setBackground(new java.awt.Color(192, 57, 43));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnEliminar.setBackground(new java.awt.Color(231, 76, 60));
            }
        });

        jbtnCancelar.setText("❌ Cancelar");
        jbtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14));
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
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addComponent(jbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        // Layout principal
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idInput = jtextId.getText().trim();
            
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Debe ingresar un ID de especialidad", 
                    "Campo Vacío", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idInput);
            
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ El ID debe ser un número positivo", 
                    "ID Inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            currentSpeciality = medicService.getSpecialityRepository().searchById(id);
            
            if (currentSpeciality == null) {
                JOptionPane.showMessageDialog(this, 
                    "❌ No se encontró especialidad con ID: " + id, 
                    "No Encontrada", 
                    JOptionPane.INFORMATION_MESSAGE);
                jtextInfo.setText("");
                jbtnEliminar.setEnabled(false);
                return;
            }
            
            StringBuilder info = new StringBuilder();
            info.append("ID: ").append(currentSpeciality.getSpecialityId()).append("\n");
            info.append("Nombre: ").append(currentSpeciality.getName()).append("\n");
            info.append("Descripción: ").append(currentSpeciality.getDescription()).append("\n");
            
            jtextInfo.setText(info.toString());
            jbtnEliminar.setEnabled(true);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ El ID debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
            jtextInfo.setText("");
            jbtnEliminar.setEnabled(false);
        }
    }

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (currentSpeciality == null) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ No hay especialidad seleccionada", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "⚠️ ¿Está seguro que desea eliminar esta especialidad?\n\n" + 
                "ID: " + currentSpeciality.getSpecialityId() + "\n" +
                "Nombre: " + currentSpeciality.getName() + "\n\n" +
                "Esta acción NO se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            boolean isDeleted = medicService.removeSpeciality(currentSpeciality);
            
            if (!isDeleted) {
                JOptionPane.showMessageDialog(this, 
                    "❌ No se pudo eliminar la especialidad.\n\n" +
                    "Posibles causas:\n" +
                    "• Tiene doctores asociados\n" +
                    "• Error en el sistema", 
                    "Error al Eliminar", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, 
                "✅ Especialidad eliminada exitosamente\n\n" +
                "ID: " + currentSpeciality.getSpecialityId() + "\n" +
                "Nombre: " + currentSpeciality.getName(), 
                "Eliminación Exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
            
            jtextId.setText("");
            jtextId.setEnabled(true);
            jtextInfo.setText("");
            jbtnEliminar.setEnabled(false);
            jbtnBuscar.setEnabled(true);
            currentSpeciality = null;
            jtextId.requestFocus();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblInfoEspecialidad;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextArea jtextInfo;
    private javax.swing.JScrollPane jtextAreaInfo;
    private javax.swing.JTextField jtextId;
}