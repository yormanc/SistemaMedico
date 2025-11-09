package interfaces;

import models.User;
import exceptions.AuthenticationException;

/**
 * Interface para gestionar la sesión del usuario actual
 * Principio: Single Responsibility - Solo gestiona sesiones
 */
public interface ISessionManager {
    
    /**
     * Establece el usuario actualmente autenticado
     */
    void setCurrentUser(User user) throws AuthenticationException;
    
    /**
     * Obtiene el usuario actualmente autenticado
     */
    User getCurrentUser();
    
    /**
     * Verifica si hay un usuario autenticado
     */
    boolean isAuthenticated();
    
    /**
     * Cierra la sesión actual
     */
    void clearSession();
    
    /**
     * Verifica si el ID proporcionado corresponde al usuario actual
     */
    boolean isCurrentUser(int userId);
}
