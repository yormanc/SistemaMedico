package exceptions;

/**
 * Excepción para errores relacionados con contraseñas
 */
public class PasswordException extends Exception {
    
    public PasswordException(String message) {
        super(message);
    }
    
    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Métodos factory para crear excepciones específicas con mensajes claros
    
    public static PasswordException incorrectCurrentPassword(int userId) {
        return new PasswordException(
            String.format("La contraseña actual es incorrecta para el usuario ID: %d", userId)
        );
    }
    
    public static PasswordException weakPassword(String reason) {
        return new PasswordException(
            String.format("La nueva contraseña no cumple los requisitos: %s", reason)
        );
    }
    
    public static PasswordException samePassword() {
        return new PasswordException(
            "La nueva contraseña no puede ser igual a la contraseña actual"
        );
    }
    
    public static PasswordException nullPassword() {
        return new PasswordException(
            "La contraseña no puede ser nula o vacía"
        );
    }
    
    public static PasswordException updateFailed(int userId, Throwable cause) {
        return new PasswordException(
            String.format("Error al actualizar la contraseña del usuario ID: %d", userId),
            cause
        );
    }
}