package interfaces;

import models.User;
import exceptions.RegistrationException;

/**
 * Strategy pattern para registro de diferentes tipos de usuarios
 * Principio: Open/Closed - Extensible sin modificar código existente
 */
public interface IUserRegistrationStrategy {
    
    /**
     * Registra un usuario en los repositorios correspondientes
     * 
     * @param user Usuario a registrar
     * @return true si el registro fue exitoso
     * @throws RegistrationException si hay error en el registro
     */
    boolean register(User user) throws RegistrationException;
    
    /**
     * Valida que el usuario sea válido para este tipo de registro
     */
    void validate(User user) throws RegistrationException;
}
