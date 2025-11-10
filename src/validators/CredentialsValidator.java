package validators;

import models.Credentials;
import exceptions.ValidationException;

/**
 * Validador para objetos Credentials
 */
public class CredentialsValidator {
    
    /**
     * Valida que las credenciales sean válidas
     */
    public static void validate(Credentials credentials) throws ValidationException {
        if (credentials == null) {
            throw ValidationException.nullValue("credenciales");
        }
        
        validateId(credentials.getId());
        validatePassword(credentials.getPassword());
        
        if (credentials.getRole() == null) {
            throw ValidationException.nullValue("rol del usuario");
        }
    }
    
    /**
     * Valida el ID de usuario
     */
    public static void validateId(int id) throws ValidationException {
        if (id <= 0) {
            throw ValidationException.invalidId(id);
        }
    }
    
    /**
     * Valida que la contraseña no sea nula o vacía
     */
    public static void validatePassword(String password) throws ValidationException {
        if (password == null || password.trim().isEmpty()) {
            throw ValidationException.emptyValue("contraseña");
        }
    }
}
