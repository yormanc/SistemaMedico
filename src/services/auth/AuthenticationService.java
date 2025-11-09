package services.auth;

import interfaces.repositories.IRepositoryDoctor;
import interfaces.repositories.IRepositoryPatient;
import interfaces.repositories.IRepositoryUser;
import interfaces.services.IAuthenticationService;
import interfaces.services.IPasswordService;
import interfaces.services.ISessionManager;
import interfaces.services.IUserRegistrationService;
import models.User;
import models.Credentials;
import models.Doctor;
import models.Patient;
import validators.CredentialsValidator;
import exceptions.AuthenticationException;
import exceptions.PasswordException;
import exceptions.RegistrationException;
import exceptions.ValidationException;

/**
 * Servicio de autenticación REFACTORIZADO
 * Ahora cumple con todos los principios SOLID:
 * - S: Solo maneja login/logout (delega otras responsabilidades)
 * - O: Extensible mediante estrategias
 * - L: Todas las dependencias son obligatorias (no null)
 * - I: Interfaces pequeñas y segregadas
 * - D: Depende de abstracciones, no implementaciones
 */
public class AuthenticationService implements IAuthenticationService {

    private final IRepositoryUser userRepository;
    private final ISessionManager sessionManager;
    private final IPasswordService passwordService;
    private final IUserRegistrationService registrationService;

    /**
     * Constructor con todas las dependencias obligatorias
     * Principio: Dependency Inversion - Depende de interfaces
     * Principio: Liskov Substitution - No acepta nulls
     */
    public AuthenticationService(
        IRepositoryUser userRepository,
        IRepositoryDoctor doctorRepository,
        IRepositoryPatient patientRepository
    ) {
        // Validar que ninguna dependencia sea null (Liskov)
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        if (doctorRepository == null) {
            throw new IllegalArgumentException("El repositorio de doctores no puede ser nulo");
        }
        if (patientRepository == null) {
            throw new IllegalArgumentException("El repositorio de pacientes no puede ser nulo");
        }
        
        this.userRepository = userRepository;
        
        // Crear servicios especializados (Single Responsibility)
        this.sessionManager = new SessionManager();
        this.passwordService = new PasswordService(userRepository);
        this.registrationService = new UserRegistrationService(
            userRepository, 
            doctorRepository, 
            patientRepository
        );
    }

    /**
     * Login de usuario
     * Responsabilidad: Solo autenticar y establecer sesión
     */
    @Override
    public User login(int id, String password) {
        try {
            // Validar entrada
            CredentialsValidator.validateId(id);
            CredentialsValidator.validatePassword(password);
            
            // Buscar usuario
            User user = null;
            try {
                user = userRepository.searchById(id);
            } catch (Exception e) {
                System.err.println("Usuario no encontrado con ID: " + id);
                return null;
            }
            
            if (user == null) {
                System.err.println("Usuario no encontrado con ID: " + id);
                return null;
            }
            
            // Verificar contraseña
            if (!user.authenticate(password)) {
                System.err.println("Credenciales inválidas para ID: " + id);
                return null;
            }
            
            // Establecer sesión
            try {
                sessionManager.setCurrentUser(user);
                return user;
            } catch (AuthenticationException e) {
                System.err.println(" " + e.getMessage());
                return null;
            }
            
        } catch (ValidationException e) {
            System.err.println("Validación fallida: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error inesperado en login: " + e.getMessage());
            return null;
        }
    }

    /**
     * Logout de usuario
     * Responsabilidad: Solo cerrar sesión
     */
    @Override
    public boolean logout(int id) {
        try {
            // Validar entrada
            CredentialsValidator.validateId(id);
            
            // Verificar que sea el usuario actual
            if (!sessionManager.isCurrentUser(id)) {
                System.err.println("El ID no corresponde al usuario autenticado");
                return false;
            }
            
            // Cerrar sesión
            sessionManager.clearSession();
            return true;
            
        } catch (ValidationException e) {
            System.err.println(" " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error en logout: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registrar doctor
     * Delega a UserRegistrationService (Single Responsibility)
     */
    @Override
    public boolean registerDoctor(Doctor doctor, Credentials credentials) {
        try {
            return registrationService.registerDoctor(doctor, credentials);
        } catch (RegistrationException | ValidationException e) {
            System.err.println(" " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar doctor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registrar paciente
     * Delega a UserRegistrationService (Single Responsibility)
     */
    @Override
    public boolean registerPatient(Patient patient, Credentials credentials) {
        try {
            return registrationService.registerPatient(patient, credentials);
        } catch (RegistrationException | ValidationException e) {
            System.err.println(" " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registrar usuario genérico
     * Delega a UserRegistrationService (Single Responsibility)
     */
    @Override
    public boolean registerUser(User user, Credentials credentials) {
        try {
            return registrationService.registerUser(user, credentials);
        } catch (RegistrationException | ValidationException e) {
            System.err.println(" " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cambiar contraseña
     * Delega a PasswordService (Single Responsibility)
     */
    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        try {
            return passwordService.changePassword(userId, oldPassword, newPassword);
        } catch (PasswordException | AuthenticationException e) {
            System.err.println(" " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al cambiar contraseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el usuario actualmente autenticado
     * Delega a SessionManager
     */
    public User getCurrentUser() {
        return sessionManager.getCurrentUser();
    }

    /**
     * Verifica si hay un usuario autenticado
     * Delega a SessionManager
     */
    public boolean isAuthenticated() {
        return sessionManager.isAuthenticated();
    }
    
    /**
     * Obtiene el gestor de sesión (para uso avanzado)
     */
    public ISessionManager getSessionManager() {
        return sessionManager;
    }
    
    /**
     * Obtiene el servicio de contraseñas (para uso avanzado)
     */
    public IPasswordService getPasswordService() {
        return passwordService;
    }
    
    /**
     * Obtiene el servicio de registro (para uso avanzado)
     */
    public IUserRegistrationService getRegistrationService() {
        return registrationService;
    }
}