package exceptions;

/**
 * Excepción para errores de validación de datos
 */
public class ValidationException extends Exception {
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Métodos factory para crear excepciones específicas con mensajes claros
    
    public static ValidationException nullValue(String fieldName) {
        return new ValidationException(
            String.format("El campo '%s' no puede ser nulo", fieldName)
        );
    }
    
    public static ValidationException emptyValue(String fieldName) {
        return new ValidationException(
            String.format("El campo '%s' no puede estar vacío", fieldName)
        );
    }
    
    public static ValidationException invalidId(int id) {
        return new ValidationException(
            String.format("ID inválido: %d. El ID debe ser mayor a 0", id)
        );
    }
    
    public static ValidationException invalidEmail(String email) {
        return new ValidationException(
            String.format("Email inválido: '%s'", email)
        );
    }
    
    public static ValidationException invalidAge(int age) {
        return new ValidationException(
            String.format("Edad inválida: %d. La edad debe estar entre 0 y 150", age)
        );
    }
    
    public static ValidationException customValidation(String fieldName, String reason) {
        return new ValidationException(
            String.format("Validación fallida para '%s': %s", fieldName, reason)
        );
    }
}