import javax.swing.*;
import java.awt.*;
import java.util.List; // AGREGADO: import necesario
import models.Speciality; // AGREGADO: import necesario
import Services.MedicSystemService;

/**
 * Menú de gestión de especialidades
 */
public class FrmSpecialityMenu extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    public FrmSpecialityMenu(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
    }

    // ... (resto del código initComponents igual) ...

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // CORREGIDO: usar viewAllSpecialities() del servicio
            List<Speciality> specialities = medicService.viewAllSpecialities();
            
            if (specialities == null || specialities.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No hay especialidades registradas en el sistema.\n\n" +
                    "Por favor, agregue especialidades primero.",
                    "Lista Vacía",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Crear diálogo para mostrar la lista
            JDialog dialog = new JDialog(this, "Lista de Especialidades Médicas", true);
            dialog.setSize(750, 450);
            dialog.setLocationRelativeTo(this);
            
            // Crear tabla con los datos
            String[] columnNames = {"ID", "Nombre", "Descripción"};
            Object[][] data = new Object[specialities.size()][3];
            
            for (int i = 0; i < specialities.size(); i++) {
                Speciality s = specialities.get(i);
                data[i][0] = s.getSpecialityId();
                data[i][1] = s.getName();
                data[i][2] = s.getDescription();
            }
            
            JTable table = new JTable(data, columnNames);
            table.setEnabled(false);
            table.setRowHeight(30);
            table.setFont(new java.awt.Font("Segoe UI", 0, 13));
            table.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 14));
            table.getTableHeader().setBackground(new java.awt.Color(0, 102, 204));
            table.getTableHeader().setForeground(Color.WHITE);
            table.setSelectionBackground(new java.awt.Color(230, 240, 255));
            
            // Ajustar ancho de columnas
            table.getColumnModel().getColumn(0).setPreferredWidth(60);
            table.getColumnModel().getColumn(1).setPreferredWidth(180);
            table.getColumnModel().getColumn(2).setPreferredWidth(400);
            
            // Centrar el contenido de la columna ID
            javax.swing.table.DefaultTableCellRenderer centerRenderer = 
                new javax.swing.table.DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);
            panel.add(scrollPane, BorderLayout.CENTER);
            
            // Panel inferior con información
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            bottomPanel.setBackground(new java.awt.Color(240, 248, 255));
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            
            JLabel lblTotal = new JLabel("Total de especialidades registradas: " + specialities.size());
            lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 15));
            lblTotal.setForeground(new java.awt.Color(0, 102, 204));
            bottomPanel.add(lblTotal);
            
            panel.add(bottomPanel, BorderLayout.SOUTH);
            
            dialog.add(panel);
            dialog.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al listar especialidades:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Variables de instancia
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JLabel jlblT;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnAgregar;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnVolver;
}