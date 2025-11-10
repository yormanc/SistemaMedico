package exceptions;

/**
 * Excepción para errores en el registro de usuarios
 */
public class RegistrationException extends Exception {
    
    public RegistrationException(String message) {
        super(message);
    }
    
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Métodos factory para crear excepciones específicas con mensajes claros
    
    public static RegistrationException userAlreadyExists(int userId) {
        return new RegistrationException(
            String.format("Ya existe un usuario registrado con el ID: %d", userId)
        );
    }
    
    public static RegistrationException invalidUserData(String reason) {
        return new RegistrationException(
            String.format("Datos de usuario inválidos: %s", reason)
        );
    }
    
    public static RegistrationException nullUser() {
        return new RegistrationException(
            "El usuario no puede ser nulo"
        );
    }
    
    public static RegistrationException nullCredentials() {
        return new RegistrationException(
            "Las credenciales no pueden ser nulas"
        );
    }
    
    public static RegistrationException missingSpeciality() {
        return new RegistrationException(
            "El doctor debe tener una especialidad asignada"
        );
    }
    
    public static RegistrationException repositoryError(String operation, Throwable cause) {
        return new RegistrationException(
            String.format("Error al ejecutar la operación de repositorio '%s'", operation),
            cause
        );
    }
}
