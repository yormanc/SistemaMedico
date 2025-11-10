package validators;

import exceptions.PasswordException;

/**
 * Validador para contraseñas
 */
public class PasswordValidator {
    
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 50;
    
    /**
     * Valida que una contraseña cumpla con los requisitos mínimos
     */
    public static void validatePassword(String password) throws PasswordException {
        if (password == null || password.isEmpty()) {
            throw PasswordException.nullPassword();
        }
        
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw PasswordException.weakPassword(
                String.format("debe tener al menos %d caracteres", MIN_PASSWORD_LENGTH)
            );
        }
        
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw PasswordException.weakPassword(
                String.format("no debe exceder %d caracteres", MAX_PASSWORD_LENGTH)
            );
        }
    }
    
    /**
     * Valida que dos contraseñas sean diferentes
     */
    public static void validatePasswordsDifferent(String oldPassword, String newPassword) 
            throws PasswordException {
        if (oldPassword.equals(newPassword)) {
            throw PasswordException.samePassword();
        }
    }
    
    /**
     * Valida una nueva contraseña (más estricta)
     */
    public static void validateNewPassword(String password) throws PasswordException {
        validatePassword(password);
        
        // Aquí podrías agregar validaciones adicionales:
        // - Debe contener al menos un número
        // - Debe contener al menos una letra mayúscula
        // - etc.
    }
}
