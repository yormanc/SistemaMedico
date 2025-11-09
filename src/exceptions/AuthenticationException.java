package exceptions;

/**
 * Excepción base para errores de autenticación
 */
public class AuthenticationException extends Exception {
    
    public AuthenticationException(String message) {
        super(message);
    }
    
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    public static AuthenticationException invalidCredentials(int userId) {
        return new AuthenticationException(
            String.format("Credenciales inválidas para el usuario con ID: %d", userId)
        );
    }
    
    public static AuthenticationException userNotFound(int userId) {
        return new AuthenticationException(
            String.format("No se encontró un usuario con ID: %d", userId)
        );
    }
    
    public static AuthenticationException userAlreadyAuthenticated(String userName) {
        return new AuthenticationException(
            String.format("El usuario '%s' ya tiene una sesión activa", userName)
        );
    }
    
    public static AuthenticationException noActiveSession() {
        return new AuthenticationException(
            "No hay ninguna sesión activa en este momento"
        );
    }
    
    public static AuthenticationException sessionMismatch(int requestedId, int currentId) {
        return new AuthenticationException(
            String.format("El ID solicitado (%d) no coincide con la sesión actual (%d)", 
                requestedId, currentId)
        );
    }
}