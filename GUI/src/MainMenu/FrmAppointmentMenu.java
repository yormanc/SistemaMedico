import javax.swing.*;

import enumerations.UserRole;

import java.awt.*;
import services.MedicSystemService;

/**
 * Menú de gestión de citas médicas
 */
public class FrmAppointmentMenu extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    public FrmAppointmentMenu(MedicSystemService medicService) {
        this.medicService = medicService;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelButtons = new javax.swing.JPanel();
        jbtnAgendar = new javax.swing.JButton();
        jbtnBuscar = new javax.swing.JButton();
        jbtnListar = new javax.swing.JButton();
        jbtnHistorialPaciente = new javax.swing.JButton();
        jbtnHistorialDoctor = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Citas Médicas");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));
        jPanelMain.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Panel de encabezado
        jPanelHeader.setBackground(new java.awt.Color(245, 245, 245));
        
        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("📅 GESTIÓN DE CITAS MÉDICAS");
        
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
        
        // Botón Agendar
        jbtnAgendar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnAgendar.setText("➕ Agendar Nueva Cita");
        jbtnAgendar.setBackground(new java.awt.Color(46, 204, 113));
        jbtnAgendar.setForeground(Color.WHITE);
        jbtnAgendar.setFocusPainted(false);
        jbtnAgendar.setBorderPainted(false);
        jbtnAgendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnAgendar.setToolTipText("Programar una nueva cita médica");
        jbtnAgendar.addActionListener(evt -> jbtnAgendarActionPerformed(evt));
        jbtnAgendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnAgendar.setBackground(new java.awt.Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnAgendar.setBackground(new java.awt.Color(46, 204, 113));
            }
        });

        // Botón Buscar
        jbtnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnBuscar.setText("🔍 Buscar/Modificar/Cancelar");
        jbtnBuscar.setBackground(new java.awt.Color(52, 152, 219));
        jbtnBuscar.setForeground(Color.WHITE);
        jbtnBuscar.setFocusPainted(false);
        jbtnBuscar.setBorderPainted(false);
        jbtnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnBuscar.setToolTipText("Buscar y gestionar citas existentes");
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
        jbtnListar.setText("📋 Ver Todas las Citas");
        jbtnListar.setBackground(new java.awt.Color(155, 89, 182));
        jbtnListar.setForeground(Color.WHITE);
        jbtnListar.setFocusPainted(false);
        jbtnListar.setBorderPainted(false);
        jbtnListar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnListar.setToolTipText("Mostrar listado completo de citas");
        jbtnListar.addActionListener(evt -> jbtnListarActionPerformed(evt));
        jbtnListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnListar.setBackground(new java.awt.Color(142, 68, 173));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnListar.setBackground(new java.awt.Color(155, 89, 182));
            }
        });

        // Botón Historial Paciente
        jbtnHistorialPaciente.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnHistorialPaciente.setText("👤 Historial de Paciente");
        jbtnHistorialPaciente.setBackground(new java.awt.Color(230, 126, 34));
        jbtnHistorialPaciente.setForeground(Color.WHITE);
        jbtnHistorialPaciente.setFocusPainted(false);
        jbtnHistorialPaciente.setBorderPainted(false);
        jbtnHistorialPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnHistorialPaciente.setToolTipText("Ver historial de citas de un paciente");
        jbtnHistorialPaciente.addActionListener(evt -> jbtnHistorialPacienteActionPerformed(evt));
        jbtnHistorialPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnHistorialPaciente.setBackground(new java.awt.Color(211, 84, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnHistorialPaciente.setBackground(new java.awt.Color(230, 126, 34));
            }
        });

        // Botón Historial Doctor
        jbtnHistorialDoctor.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jbtnHistorialDoctor.setText("⚕️ Historial de Doctor");
        jbtnHistorialDoctor.setBackground(new java.awt.Color(26, 188, 156));
        jbtnHistorialDoctor.setForeground(Color.WHITE);
        jbtnHistorialDoctor.setFocusPainted(false);
        jbtnHistorialDoctor.setBorderPainted(false);
        jbtnHistorialDoctor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnHistorialDoctor.setToolTipText("Ver historial de citas de un doctor");
        jbtnHistorialDoctor.addActionListener(evt -> jbtnHistorialDoctorActionPerformed(evt));
        jbtnHistorialDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnHistorialDoctor.setBackground(new java.awt.Color(22, 160, 133));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnHistorialDoctor.setBackground(new java.awt.Color(26, 188, 156));
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
            .addComponent(jbtnAgendar, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(jbtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnHistorialPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnHistorialDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addComponent(jbtnAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnHistorialPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jbtnHistorialDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jbtnAgendarActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir el nuevo formulario para agendar la cita
        try {
            FrmAddAppointment frmAddAppointment = new FrmAddAppointment(medicService);
            frmAddAppointment.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el formulario de Agendar Cita: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir el nuevo formulario para gestionar (buscar, modificar, eliminar) la cita
        try {
            FrmManageAppointments frmManage = new FrmManageAppointments(medicService);
            frmManage.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el módulo de Gestión de Citas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnListarActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir el nuevo formulario para listar todas las citas
        try {
            // Asegúrate de que FrmListAppointments está disponible en el mismo paquete o importado
            FrmListAppointments frmListAppointments = new FrmListAppointments(medicService);
            frmListAppointments.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el módulo de Listado de Citas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnHistorialPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Abrir el formulario de historial configurado para Pacientes
            FrmViewHistory frmHistory = new FrmViewHistory(medicService, UserRole.PATIENT);
            frmHistory.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el módulo de Historial de Pacientes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnHistorialDoctorActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Abrir el formulario de historial configurado para Doctores
            FrmViewHistory frmHistory = new FrmViewHistory(medicService, UserRole.DOCTOR);
            frmHistory.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el módulo de Historial de Doctores: " + e.getMessage(),
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
    private javax.swing.JButton jbtnAgendar;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnListar;
    private javax.swing.JButton jbtnHistorialPaciente;
    private javax.swing.JButton jbtnHistorialDoctor;
    private javax.swing.JButton jbtnVolver;
}
