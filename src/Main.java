/**
 * Clase Principal del Sistema de Gestión Médica
 * Punto de entrada de la aplicación
 */
public class Main {

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("No se pudo configurar el Look and Feel: " + e.getMessage());
            // La aplicación continuará con el Look and Feel por defecto
        }

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