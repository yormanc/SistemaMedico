package interfaces.services;

import exceptions.PasswordException;
import exceptions.AuthenticationException;

/**
 * Interface para gestionar contraseñas
 * Principio: Single Responsibility - Solo maneja contraseñas
 */
public interface IPasswordService {
    
    /**
     * Cambia la contraseña de un usuario
     * 
     * @param userId ID del usuario
     * @param oldPassword Contraseña actual
     * @param newPassword Nueva contraseña
     * @return true si el cambio fue exitoso
     * @throws PasswordException si hay error con las contraseñas
     * @throws AuthenticationException si el usuario no existe o la contraseña actual es incorrecta
     */
    boolean changePassword(int userId, String oldPassword, String newPassword) 
            throws PasswordException, AuthenticationException;
}