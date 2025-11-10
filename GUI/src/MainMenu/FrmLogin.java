/*
 * Sistema de Gestión de Pacientes
 * Formulario de Inicio de Sesión
 */

import enums.UserRole;
import models.User;
import services.MedicSystemService;

import javax.swing.*;

/**
 * Formulario de inicio de sesión del sistema
 * 
 * @author WINDOWS 11
 */
public class FrmLogin extends javax.swing.JFrame {

    private MedicSystemService medicService;
    
    /**
     * Constructor por defecto
     */
    public FrmLogin() {
        initComponents();
        this.medicService = new MedicSystemService();
        
        // Crear usuario administrador por defecto (hardcoded para testing)
        crearUsuarioAdminPorDefecto();
        
        // Centrar la ventana
        setLocationRelativeTo(null);
        
        // Configuraciones adicionales
        setupForm();
        
        System.out.println(" FrmLogin inicializado");
    }
    
    /**
     * Crea un usuario administrador por defecto en el sistema
     * ID: 1234
     * Contraseña: aaa123
     */
    private void crearUsuarioAdminPorDefecto() {
        try {
            boolean registered = medicService.registerAdmin(
                "Administrador del Sistema",  // Nombre completo
                1234,                          // ID
                "aaa123",                      // Contraseña
                35,                            // Edad
                "admin@sistemmedico.com"       // Email
            );
            
            if (registered) {
                System.out.println("════════════════════════════════════════════");
                System.out.println(" USUARIO ADMINISTRADOR CREADO");
                System.out.println("════════════════════════════════════════════");
                System.out.println("    ID: 1234");
                System.out.println("    Contraseña: aaa123");
                System.out.println("    Nombre: Administrador del Sistema");
                System.out.println("    Email: admin@sistemmedico.com");
                System.out.println("    Rol: ADMIN");
                System.out.println("════════════════════════════════════════════");
            } else {
                System.out.println("  Usuario administrador ya existe en el sistema");
            }
        } catch (Exception e) {
            System.err.println("Error al crear usuario administrador: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Constructor con inyección de dependencias
     */
    public FrmLogin(MedicSystemService medicService) {
        initComponents();
        this.medicService = medicService;
        setLocationRelativeTo(null);
        setupForm();
    }
    
    /**
     * Configuraciones adicionales del formulario
     */
    private void setupForm() {
        // Hacer que Enter en el campo de contraseña ejecute el login
        jpasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });
        
        // Enfocar el campo de ID al iniciar
        jtextId.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelLogin = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jlblSubtitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblId = new javax.swing.JLabel();
        jtextId = new javax.swing.JTextField();
        jlblPassword = new javax.swing.JLabel();
        jpasswordField = new javax.swing.JPasswordField();
        jcheckMostrarPassword = new javax.swing.JCheckBox();
        jbtnLogin = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jlblRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión - Sistema Médico");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(240, 245, 250));

        jPanelLogin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));

        jlblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jlblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("INICIAR SESIÓN");

        jlblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jlblSubtitulo.setForeground(new java.awt.Color(102, 102, 102));
        jlblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSubtitulo.setText("Sistema de Gestión de Pacientes");

        jlblId.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jlblId.setText("Número de Documento:");

        jtextId.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jtextId.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        jlblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jlblPassword.setText("Contraseña:");

        jpasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jpasswordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        jcheckMostrarPassword.setBackground(new java.awt.Color(255, 255, 255));
        jcheckMostrarPassword.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jcheckMostrarPassword.setText("Mostrar contraseña");
        jcheckMostrarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcheckMostrarPasswordActionPerformed(evt);
            }
        });

        jbtnLogin.setBackground(new java.awt.Color(0, 102, 204));
        jbtnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnLogin.setText("INGRESAR");
        jbtnLogin.setFocusPainted(false);
        jbtnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.setFocusPainted(false);
        jbtnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jlblRegistro.setFont(new java.awt.Font("Segoe UI", 2, 12));
        jlblRegistro.setForeground(new java.awt.Color(102, 102, 102));
        jlblRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRegistro.setText("¿No tienes cuenta? Contacta al administrador");

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jlblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jlblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtextId)
                    .addComponent(jlblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpasswordField)
                    .addComponent(jcheckMostrarPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jlblRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jlblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblSubtitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jlblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcheckMostrarPassword)
                .addGap(25, 25, 25)
                .addComponent(jbtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblRegistro)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>

    private void jbtnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener y validar los campos
            String idInput = jtextId.getText().trim();
            String passwordInput = new String(jpasswordField.getPassword()).trim();
            
            // Validar que no estén vacíos
            if (idInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Debe ingresar su número de documento",
                    "Campo Vacío",
                    JOptionPane.WARNING_MESSAGE);
                jtextId.requestFocus();
                return;
            }
            
            if (passwordInput.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Debe ingresar su contraseña",
                    "Campo Vacío",
                    JOptionPane.WARNING_MESSAGE);
                jpasswordField.requestFocus();
                return;
            }
            
            // Validar que el ID sea un número
            int id;
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                    "El número de documento debe ser un valor numérico válido",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            // Validar que sea positivo
            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El número de documento debe ser un número positivo",
                    "ID Inválido",
                    JOptionPane.WARNING_MESSAGE);
                jtextId.selectAll();
                jtextId.requestFocus();
                return;
            }
            
            // Intentar iniciar sesión
            medicService.loginUser(id, passwordInput);
            User authenticatedUser = medicService.authenticatedUser;
            
            if (authenticatedUser == null) {
                JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas.\nVerifique su documento y contraseña.",
                    "Error de Autenticación",
                    JOptionPane.ERROR_MESSAGE);
                jpasswordField.setText("");
                jpasswordField.requestFocus();
                return;
            }
            
            // Login exitoso - Determinar el rol y abrir la ventana correspondiente
            UserRole role = authenticatedUser.getCredentials().getRole();
            
            JOptionPane.showMessageDialog(this,
                "¡Bienvenido, " + authenticatedUser.getFullName() + "!\n" +
                "Rol: " + role,
                "Login Exitoso",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir la ventana correspondiente según el rol
            switch (role) {
                case ADMIN:
                    // Abrir menú de administrador (con todas las funcionalidades)
                    FrmMainMenu mainMenu = new FrmMainMenu(medicService);
                    mainMenu.setVisible(true);
                    this.dispose();
                    break;
                    
                case PATIENT:
                    // Abrir menú de paciente (funcionalidades limitadas)
                    JOptionPane.showMessageDialog(this,
                        "Módulo de paciente en desarrollo.\nPor ahora accediendo al menú principal.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                    FrmMainMenu patientMenu = new FrmMainMenu(medicService);
                    patientMenu.setVisible(true);
                    this.dispose();
                    break;
                    
                case DOCTOR:
                    // Abrir menú de doctor
                    JOptionPane.showMessageDialog(this,
                        "Módulo de doctor en desarrollo.\nPor ahora accediendo al menú principal.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                    FrmMainMenu doctorMenu = new FrmMainMenu(medicService);
                    doctorMenu.setVisible(true);
                    this.dispose();
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(this,
                        "Rol de usuario no reconocido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    break;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error inesperado al iniciar sesión: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void jcheckMostrarPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        // Alternar visibilidad de la contraseña
        if (jcheckMostrarPassword.isSelected()) {
            jpasswordField.setEchoChar((char) 0); // Mostrar texto
        } else {
            jpasswordField.setEchoChar('•'); // Ocultar con puntos
        }
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblSubtitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextField jtextId;
    private javax.swing.JLabel jlblPassword;
    private javax.swing.JPasswordField jpasswordField;
    private javax.swing.JCheckBox jcheckMostrarPassword;
    private javax.swing.JButton jbtnLogin;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlblRegistro;
    // End of variables declaration
}
