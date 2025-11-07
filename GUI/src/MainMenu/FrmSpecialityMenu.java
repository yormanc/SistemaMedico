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
        initComponents();
        this.medicService = medicService;
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jbtnAgregar = new javax.swing.JButton();
        jbtnBuscar = new javax.swing.JButton();
        jbtnListar = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Especialidades");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("GESTIÓN DE ESPECIALIDADES");

        jbtnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnAgregar.setText("Agregar Nueva Especialidad");
        jbtnAgregar.addActionListener(evt -> jbtnAgregarActionPerformed(evt));

        jbtnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnBuscar.setText("Buscar/Modificar/Eliminar");
        jbtnBuscar.addActionListener(evt -> jbtnBuscarActionPerformed(evt));

        jbtnListar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnListar.setText("Ver Todas las Especialidades");
        jbtnListar.addActionListener(evt -> jbtnListarActionPerformed(evt));

        jbtnVolver.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(evt -> dispose());

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jbtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jlblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jbtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jbtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
            frm.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir formulario: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmSearchSpeciality frm = new FrmSearchSpeciality(medicService);
            frm.setVisible(true);
            frm.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir formulario: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener el repositorio de especialidades
            SpecialityRepository repo = (SpecialityRepository) medicService.getClass()
                .getDeclaredField("specialityRepository").get(medicService);
            
            java.util.List<models.Speciality> specialities = repo.getAll();
            
            if (specialities == null || specialities.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No hay especialidades registradas",
                    "Lista Vacía",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Crear diálogo para mostrar la lista
            JDialog dialog = new JDialog(this, "Lista de Especialidades", true);
            dialog.setSize(700, 400);
            dialog.setLocationRelativeTo(this);
            
            // Crear tabla con los datos
            String[] columnNames = {"ID", "Nombre", "Descripción"};
            Object[][] data = new Object[specialities.size()][3];
            
            for (int i = 0; i < specialities.size(); i++) {
                models.Speciality s = specialities.get(i);
                data[i][0] = s.getSpecialityId();
                data[i][1] = s.getName();
                data[i][2] = s.getDescription();
            }
            
            JTable table = new JTable(data, columnNames);
            table.setEnabled(false);
            table.setRowHeight(25);
            
            // Ajustar ancho de columnas
            table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
            table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nombre
            table.getColumnModel().getColumn(2).setPreferredWidth(350);  // Descripción
            
            JScrollPane scrollPane = new JScrollPane(table);
            
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            
            // Label con el total
            JLabel lblTotal = new JLabel("Total de especialidades: " + specialities.size());
            lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12));
            panel.add(lblTotal, BorderLayout.SOUTH);
            
            dialog.add(panel);
            dialog.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al listar especialidades: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private javax.swing.JPanel jPanelMain;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnAgregar;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnVolver;
}