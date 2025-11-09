package main;

public class Main {
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            // Usar el Look and Feel del sistema operativo
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.err.println("No se pudo configurar el Look and Feel: " + e.getMessage());
        }
        
        // Mostrar información del sistema
        System.out.println("════════════════════════════════════════════");
        System.out.println("   SISTEMA DE GESTIÓN MÉDICA");
        System.out.println("════════════════════════════════════════════");
        System.out.println("Iniciando aplicación...");
        System.out.println();
        
        // Crear y mostrar el formulario de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmLogin loginForm = new FrmLogin();
                loginForm.setVisible(true);
            }
        });
    }
}
