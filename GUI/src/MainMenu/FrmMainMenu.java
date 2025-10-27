/*
 * Sistema de Gestión Médica
 * Menú Principal
 */
import javax.swing.*;
import java.awt.*;
import services.MedicSystemService;

/**
 * Formulario del Menú Principal que conecta todas las funcionalidades
 * del sistema de gestión médica
 * 
 * @author WINDOWS 11
 */
public class FrmMainMenu extends javax.swing.JFrame {

    private final MedicSystemService medicService;
    
    /**
     * Constructor por defecto
     */
    public FrmMainMenu() {
        initComponents();
        
        // El servicio maneja todo internamente a través de RepositoryManager
        this.medicService = new MedicSystemService();
        
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
        initComponents();
        this.medicService = medicService;
        
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
        jSeparator2 = new javax.swing.JSeparator();
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
        jbtnMenuPacientes.setText("  Menú de Pacientes");
        jbtnMenuPacientes.setFocusPainted(false);
        jbtnMenuPacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuPacientes.setIconTextGap(15);
        jbtnMenuPacientes.addActionListener(evt -> jbtnMenuPacientesActionPerformed(evt));

        jbtnMenuDoctores.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuDoctores.setIcon(createIcon("⚕️"));
        jbtnMenuDoctores.setText("  Menú de Doctores");
        jbtnMenuDoctores.setFocusPainted(false);
        jbtnMenuDoctores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuDoctores.setIconTextGap(15);
        jbtnMenuDoctores.addActionListener(evt -> jbtnMenuDoctoresActionPerformed(evt));

        jbtnMenuEspecialidades.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jbtnMenuEspecialidades.setIcon(createIcon("📋"));
        jbtnMenuEspecialidades.setText("  Menú de Especialidades");
        jbtnMenuEspecialidades.setFocusPainted(false);
        jbtnMenuEspecialidades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnMenuEspecialidades.setIconTextGap(15);
        jbtnMenuEspecialidades.addActionListener(evt -> jbtnMenuEspecialidadesActionPerformed(evt));

        jbtnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jbtnSalir.setForeground(new java.awt.Color(204, 0, 0));
        jbtnSalir.setText("Salir del Sistema");
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
                    .addComponent(jSeparator2)
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
                .addGap(25, 25, 25)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir del sistema?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("👋 Cerrando el sistema...");
            System.exit(0);
        }
    }

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
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnSalir;
    // End of variables declaration
}