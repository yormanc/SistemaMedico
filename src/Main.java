/**
 * Clase Principal del Sistema de Gestión Médica
 * Punto de entrada de la aplicación
 */
public class Main {

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            // Opción 1: Usar Nimbus (moderno y multiplataforma)
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Opción 2 (alternativa): Usar el Look and Feel del sistema operativo
            // javax.swing.UIManager.setLookAndFeel(
            //     javax.swing.UIManager.getSystemLookAndFeelClassName()
            // );

        } catch (Exception e) {
            System.err.println(" No se pudo configurar el Look and Feel: " + e.getMessage());
            // La aplicación continuará con el Look and Feel por defecto
        }

        // Mostrar información del sistema en consola
        System.out.println("════════════════════════════════════════════");
        System.out.println("   SISTEMA DE GESTIÓN MÉDICA");
        System.out.println("════════════════════════════════════════════");
        System.out.println(" Fecha de inicio: " + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        ));
        System.out.println(" Iniciando aplicación...");
        System.out.println();

        // Crear y mostrar el formulario de login en el Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Crear e instanciar el formulario de login
                    FrmLogin loginForm = new FrmLogin();
                    loginForm.setVisible(true);

                    System.out.println(" Formulario de login iniciado correctamente");
                    System.out.println();

                } catch (Exception e) {
                    System.err.println(" Error al iniciar el formulario de login:");
                    e.printStackTrace();

                    // Mostrar mensaje de error al usuario
                    javax.swing.JOptionPane.showMessageDialog(
                            null,
                            "Error crítico al iniciar la aplicación:\n" + e.getMessage(),
                            "Error de Inicio",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );

                    // Cerrar la aplicación si hay error crítico
                    System.exit(1);
                }
            }
        });
    }
}