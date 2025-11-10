package services.auth;

import interfaces.repositories.IRepositoryUser;
import interfaces.services.IPasswordService;
import models.User;
import exceptions.PasswordException;
import exceptions.AuthenticationException;
import validators.PasswordValidator;

/**
 * Servicio para gestión de contraseñas
 * Responsabilidad: Solo cambio de contraseñas
 */
public class PasswordService implements IPasswordService {
    
    private final IRepositoryUser userRepository;
    
    public PasswordService(IRepositoryUser userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) 
            throws PasswordException, AuthenticationException {
        
        try {
            // Validar contraseñas
            PasswordValidator.validatePassword(oldPassword);
            PasswordValidator.validateNewPassword(newPassword);
            PasswordValidator.validatePasswordsDifferent(oldPassword, newPassword);
            
            // Buscar usuario
            User user = null;
            try {
                user = userRepository.searchById(userId);
            } catch (Exception e) {
                throw AuthenticationException.userNotFound(userId);
            }
            
            if (user == null) {
                throw AuthenticationException.userNotFound(userId);
            }
            
            // Verificar contraseña actual
            if (!user.getCredentials().verifyPassword(oldPassword)) {
                throw PasswordException.incorrectCurrentPassword(userId);
            }
            
            // Cambiar contraseña
            user.getCredentials().setPassword(newPassword);
            
            // Actualizar en repositorio
            try {
                userRepository.update(user);
                System.out.println("✅ Contraseña actualizada para usuario ID: " + userId);
                return true;
                
            } catch (Exception e) {
                throw PasswordException.updateFailed(userId, e);
            }
            
        } catch (PasswordException | AuthenticationException e) {
            System.err.println("❌ " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al cambiar contraseña: " + e.getMessage());
            throw new PasswordException("Error inesperado al cambiar contraseña", e);
        }
    }
}
