/*
 * Sistema de Gestión Médica
 * Menú Principal
 */
import javax.swing.*;
import java.awt.*;
import services.MedicSystemService;
// IMPORTACIÓN ASUMIDA PARA LA VENTANA DE LOGIN
// Debes asegurarte de que esta clase exista en tu proyecto
// import ui.FrmLogin; 

/**
 * Formulario del Menú Principal que conecta todas las funcionalidades
 * del sistema de gestión médica
 * * @author WINDOWS 11
 */
public class FrmMainMenu extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    /**
     * Constructor por defecto
     */
    public FrmMainMenu() {
        // Inicializa el servicio primero
        this.medicService = new MedicSystemService();
        
        initComponents();
        
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        
        // Personalizar el aspecto
        customizeAppearance();
        
        System.out.println("✅ FrmMainMenu inicializado correctamente");
    }
    
    /**
     * Constructor con inyección de dependencias (para testing)
     */
    public FrmMainMenu(MedicSystemService medicService) {
        this.medicService = medicService;
        
        initComponents();
        
        setLocationRelativeTo(null);
        customizeAppearance();
    }
    
    /**
     * Personaliza la apariencia de la ventana
     */
    private void customizeAppearance() {
        setTitle("Sistema de Gestión Médica");
    }

   
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblSubtitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblInstrucciones = new javax.swing.JLabel();
        jbtnMenuPacientes = new javax.swing.JButton();
        jbtnMenuDoctores = new javax.swing.JButton();
        jbtnMenuEspecialidades = new javax.swing.JButton();
        jbtnMenuCitas = new javax.swing.JButton(); 
        jSeparator2 = new javax.swing.JSeparator();
        jbtnLogout = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton(); 

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión Médica");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(245, 245, 245));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("SISTEMA DE GESTIÓN MÉDICA");

        jlblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jlblSubtitulo.setForeground(new java.awt.Color(102, 102, 102));
        jlblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSubtitulo.setText("Administración Integral");

        jlblInstrucciones.setFont(new java.awt.Font("Segoe UI", 2, 12));
        jlblInstrucciones.setForeground(new java.awt.Color(102, 102, 102));
        jlblInstrucciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInstrucciones.setText("Seleccione el módulo que desea gestionar");

        jbtnMenuPacientes.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuPacientes.setIcon(createIcon("👤"));
        jbtnMenuPacientes.setText("  Menú de Pacientes");
        jbtnMenuPacientes.setFocusPainted(false);
        jbtnMenuPacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuPacientes.setIconTextGap(15);
        jbtnMenuPacientes.addActionListener(evt -> jbtnMenuPacientesActionPerformed(evt));

        jbtnMenuDoctores.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuDoctores.setIcon(createIcon("⚕️"));
        jbtnMenuDoctores.setText("  Menú de Doctores");
        jbtnMenuDoctores.setFocusPainted(false);
        jbtnMenuDoctores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuDoctores.setIconTextGap(15);
        jbtnMenuDoctores.addActionListener(evt -> jbtnMenuDoctoresActionPerformed(evt));

        jbtnMenuEspecialidades.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuEspecialidades.setIcon(createIcon("📋"));
        jbtnMenuEspecialidades.setText("  Menú de Especialidades");
        jbtnMenuEspecialidades.setFocusPainted(false);
        jbtnMenuEspecialidades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuEspecialidades.setIconTextGap(15);
        jbtnMenuEspecialidades.addActionListener(evt -> jbtnMenuEspecialidadesActionPerformed(evt));

        // Configuración del botón de Citas
        jbtnMenuCitas.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuCitas.setIcon(createIcon("📅"));
        jbtnMenuCitas.setText("  Menú de Citas");
        jbtnMenuCitas.setFocusPainted(false);
        jbtnMenuCitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuCitas.setIconTextGap(15);
        jbtnMenuCitas.addActionListener(evt -> jbtnMenuCitasActionPerformed(evt));

        // Configuración del nuevo botón de Cerrar Sesión (Logout)
        jbtnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnLogout.setForeground(new java.awt.Color(231, 76, 60)); // Color rojo suave
        jbtnLogout.setText("🚪 Cerrar Sesión (Logout)");
        jbtnLogout.setFocusPainted(false);
        jbtnLogout.addActionListener(evt -> jbtnLogoutActionPerformed(evt));
        
        jbtnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnSalir.setForeground(new java.awt.Color(204, 0, 0));
        jbtnSalir.setText("Salir del Sistema (Cerrar App)");
        jbtnSalir.setFocusPainted(false);
        jbtnSalir.addActionListener(evt -> jbtnSalirActionPerformed(evt));

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jlblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jlblInstrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnMenuPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnMenuDoctores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnMenuEspecialidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnMenuCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jbtnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jlblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblSubtitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblInstrucciones)
                .addGap(25, 25, 25)
                .addComponent(jbtnMenuPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnMenuDoctores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnMenuEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnMenuCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    /**
     * Crea un icono simple con emoji
     */
    private Icon createIcon(String emoji) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
                g.drawString(emoji, x, y + 15);
            }
            
            @Override
            public int getIconWidth() {
                return 24;
            }
            
            @Override
            public int getIconHeight() {
                return 24;
            }
        };
    }

    private void jbtnMenuPacientesActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmPatientMenu frmPatientMenu = new FrmPatientMenu(medicService);
            frmPatientMenu.setVisible(true);
            frmPatientMenu.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el menú de pacientes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnMenuDoctoresActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmDoctorMenu frmDoctorMenu = new FrmDoctorMenu(medicService);
            frmDoctorMenu.setVisible(true);
            frmDoctorMenu.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el menú de doctores: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnMenuEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmSpecialityMenu frmSpecialityMenu = new FrmSpecialityMenu(medicService);
            frmSpecialityMenu.setVisible(true);
            frmSpecialityMenu.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el menú de especialidades: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jbtnMenuCitasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FrmAppointmentMenu frmAppointmentMenu = new FrmAppointmentMenu(medicService);
            frmAppointmentMenu.setVisible(true);
            frmAppointmentMenu.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el menú de citas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Maneja el evento de cerrar sesión:
     * 1. Confirma la acción.
     * 2. Llama al servicio de logout.
     * 3. Muestra la ventana de login (simulada).
     * 4. Cierra la ventana principal.
     */
    private void jbtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea cerrar la sesión actual y volver al login?",
            "Confirmar Cierre de Sesión",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // 1. Cierra la sesión en el servicio
            if (medicService.authenticatedUser != null) {
                int userId = medicService.authenticatedUser.getCredentials().getId();
                medicService.logoutUser(userId);
                System.out.println("👋 Sesión cerrada para usuario ID: " + userId);
            } else {
                 System.out.println("👋 No había sesión activa que cerrar.");
            }
            
            // 2. Muestra la ventana de Login (Simulación)
            // *** REEMPLAZA ESTO CON LA INSTANCIACIÓN REAL DE TU VENTANA DE LOGIN ***
            try {
                // Asumimos que tienes una clase FrmLogin
                // FrmLogin frmLogin = new FrmLogin(medicService); 
                // frmLogin.setVisible(true);
                // frmLogin.setLocationRelativeTo(null);
                
                JOptionPane.showMessageDialog(this,
                    "Sesión cerrada. Volviendo a la pantalla de Login.",
                    "Cierre de Sesión Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this,
                    "Error al intentar cargar la pantalla de Login: " + e.getMessage(),
                    "Error Fatal",
                    JOptionPane.ERROR_MESSAGE);
            }
            // *************************************************************************
            
            // 3. Cierra la ventana principal
            this.dispose(); 
        }
    }
    
    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir completamente del sistema?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("👋 Cerrando la aplicación...");
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblSubtitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblInstrucciones;
    private javax.swing.JButton jbtnMenuPacientes;
    private javax.swing.JButton jbtnMenuDoctores;
    private javax.swing.JButton jbtnMenuEspecialidades;
    private javax.swing.JButton jbtnMenuCitas;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnLogout;
    private javax.swing.JButton jbtnSalir;
    // End of variables declaration
}