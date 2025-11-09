package services.auth;

import models.User;
import exceptions.AuthenticationException;
import interfaces.services.ISessionManager;

/**
 * Gestor de sesión de usuario
 * Responsabilidad: Mantener y gestionar la sesión activa
 */
public class SessionManager implements ISessionManager {
    
    private User currentUser;
    
    public SessionManager() {
        this.currentUser = null;
    }
    
    @Override
    public void setCurrentUser(User user) throws AuthenticationException {
        try {
            if (user == null) {
                throw new Exception("El usuario no puede ser nulo");
            }
            
            if (this.currentUser != null) {
                throw AuthenticationException.userAlreadyAuthenticated(
                    this.currentUser.getFullName()
                );
            }
            
            this.currentUser = user;
            System.out.println("✅ Sesión iniciada: " + user.getFullName());
            
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationException("Error al establecer la sesión: " + e.getMessage(), e);
        }
    }
    
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    
    @Override
    public boolean isAuthenticated() {
        return currentUser != null;
    }
    
    @Override
    public void clearSession() {
        if (currentUser != null) {
            System.out.println("✅ Sesión cerrada: " + currentUser.getFullName());
            currentUser = null;
        }
    }
    
    @Override
    public boolean isCurrentUser(int userId) {
        return currentUser != null && 
               currentUser.getCredentials().getId() == userId;
    }
}
