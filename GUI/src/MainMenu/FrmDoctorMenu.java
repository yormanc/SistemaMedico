import javax.swing.*;
import java.awt.*;
import services.MedicSystemService;

/**
 * Menú de gestión de doctores
 */
public class FrmDoctorMenu extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    public FrmDoctorMenu(MedicSystemService medicService) {
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
        setTitle("Gestión de Doctores");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("GESTIÓN DE DOCTORES");

        jbtnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnAgregar.setText("Agregar Nuevo Doctor");
        jbtnAgregar.addActionListener(evt -> jbtnAgregarActionPerformed(evt));

        jbtnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnBuscar.setText("Buscar/Modificar/Eliminar");
        jbtnBuscar.addActionListener(evt -> jbtnBuscarActionPerformed(evt));

        jbtnListar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnListar.setText("Ver Todos los Doctores");
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
            // Se asume que FrmAddDoctor está en el paquete 'ui' o importado correctamente
            FrmAddDoctor frmAddDoctor = new FrmAddDoctor(medicService);
            frmAddDoctor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el formulario para agregar doctor: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }   

    // Fragmento de FrmDoctorMenu.java
// ...

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Abrir el formulario para buscar, modificar y eliminar doctor
            FrmManageDoctor frmManageDoctor = new FrmManageDoctor(medicService);
            frmManageDoctor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el módulo de Gestión de Doctores: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
// ...

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener el repositorio de doctores mediante reflexión
            repositories.DoctorRepository doctorRepo = 
                (repositories.DoctorRepository) medicService.getClass()
                    .getDeclaredField("doctorRepository").get(medicService);
            
            java.util.List<models.Doctor> doctors = doctorRepo.getAll();
            
            if (doctors == null || doctors.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No hay doctores registrados",
                    "Lista Vacía",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            JDialog dialog = new JDialog(this, "Lista de Doctores", true);
            dialog.setSize(700, 400);
            dialog.setLocationRelativeTo(this);
            
            String[] columnNames = {"ID", "Nombre", "Edad", "Email", "Especialidad"};
            Object[][] data = new Object[doctors.size()][5];
            
            for (int i = 0; i < doctors.size(); i++) {
                models.Doctor d = doctors.get(i);
                data[i][0] = d.getCredentials().getId();
                data[i][1] = d.getFullName();
                data[i][2] = d.getAge();
                data[i][3] = d.getEmail();
                data[i][4] = d.getSpeciality().getName();
            }
            
            JTable table = new JTable(data, columnNames);
            table.setEnabled(false);
            table.setRowHeight(25);
            JScrollPane scrollPane = new JScrollPane(table);
            
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            
            JLabel lblTotal = new JLabel("Total: " + doctors.size());
            lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(lblTotal, BorderLayout.SOUTH);
            
            dialog.add(panel);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al listar: " + e.getMessage(),
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
