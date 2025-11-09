import javax.swing.*;
import java.awt.*;
import Services.MedicSystemService;
import repositories.SpecialityRepository;

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

    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelButtons = new javax.swing.JPanel();
        jbtnAgregar = new javax.swing.JButton();
        jbtnBuscar = new javax.swing.JButton();
        jbtnListar = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Especialidades");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Panel de encabezado
        jPanelHeader.setBackground(new java.awt.Color(245, 245, 245));
        
        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("GESTIÓN DE ESPECIALIDADES");
        jlblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/medical.png"))); // Opcional
        
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
                .addComponent(jlblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Panel de botones
        jPanelButtons.setBackground(new java.awt.Color(245, 245, 245));
        
        // Botón Agregar
        jbtnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnAgregar.setText("➕ Agregar Nueva Especialidad");
        jbtnAgregar.setBackground(new java.awt.Color(46, 204, 113));
        jbtnAgregar.setForeground(Color.WHITE);
        jbtnAgregar.setFocusPainted(false);
        jbtnAgregar.setBorderPainted(false);
        jbtnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnAgregar.setToolTipText("Registrar una nueva especialidad médica");
        jbtnAgregar.addActionListener(evt -> jbtnAgregarActionPerformed(evt));
        jbtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnAgregar.setBackground(new java.awt.Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnAgregar.setBackground(new java.awt.Color(46, 204, 113));
            }
        });

        // Botón Buscar
        jbtnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnBuscar.setText("🔍 Buscar/Modificar/Eliminar");
        jbtnBuscar.setBackground(new java.awt.Color(52, 152, 219));
        jbtnBuscar.setForeground(Color.WHITE);
        jbtnBuscar.setFocusPainted(false);
        jbtnBuscar.setBorderPainted(false);
        jbtnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnBuscar.setToolTipText("Buscar y gestionar especialidades existentes");
        jbtnBuscar.addActionListener(evt -> jbtnBuscarActionPerformed(evt));
        jbtnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnBuscar.setBackground(new java.awt.Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnBuscar.setBackground(new java.awt.Color(52, 152, 219));
            }
        });

        // Botón Listar
        jbtnListar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnListar.setText("📋 Ver Todas las Especialidades");
        jbtnListar.setBackground(new java.awt.Color(155, 89, 182));
        jbtnListar.setForeground(Color.WHITE);
        jbtnListar.setFocusPainted(false);
        jbtnListar.setBorderPainted(false);
        jbtnListar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnListar.setToolTipText("Mostrar listado completo de especialidades");
        jbtnListar.addActionListener(evt -> jbtnListarActionPerformed(evt));
        jbtnListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnListar.setBackground(new java.awt.Color(142, 68, 173));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnListar.setBackground(new java.awt.Color(155, 89, 182));
            }
        });

        // Botón Volver
        jbtnVolver.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnVolver.setText("⬅ Volver al Menú Principal");
        jbtnVolver.setBackground(new java.awt.Color(189, 195, 199));
        jbtnVolver.setForeground(new java.awt.Color(52, 73, 94));
        jbtnVolver.setFocusPainted(false);
        jbtnVolver.setBorderPainted(false);
        jbtnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnVolver.setToolTipText("Regresar al menú anterior");
        jbtnVolver.addActionListener(evt -> dispose());
        jbtnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnVolver.setBackground(new java.awt.Color(149, 165, 166));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnVolver.setBackground(new java.awt.Color(189, 195, 199));
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(jbtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addComponent(jbtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jbtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Layout principal
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
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

    private void jbtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmAddSpeciality frm = new FrmAddSpeciality(medicService);
            frm.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el formulario: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmSearchSpeciality frm = new FrmSearchSpeciality(medicService);
            frm.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el formulario: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // ✅ USAR EL MÉTODO PÚBLICO DEL SERVICIO
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
            table.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
            table.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nombre
            table.getColumnModel().getColumn(2).setPreferredWidth(400);  // Descripción
            
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
            
            JLabel lblTotal = new JLabel("📊 Total de especialidades registradas: " + specialities.size());
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
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnAgregar;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnVolver;
    
    /**
     * Método main para testing independiente
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            MedicSystemService service = new MedicSystemService();
            new FrmSpecialityMenu(service).setVisible(true);
        });
    }
}