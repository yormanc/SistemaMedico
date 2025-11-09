package services.strategies;

import interfaces.services.IUserRegistrationStrategy;
import interfaces.repositories.IRepositoryUser;
import models.User;
import exceptions.RegistrationException;
import validators.UserValidator;

/**
 * Estrategia de registro para usuarios genéricos
 * Principio Open/Closed: Extensible sin modificar
 */
public class GenericUserRegistrationStrategy implements IUserRegistrationStrategy {
    
    private final IRepositoryUser userRepository;
    
    public GenericUserRegistrationStrategy(IRepositoryUser userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        this.userRepository = userRepository;
    }
    
    @Override
    public void validate(User user) throws RegistrationException {
        try {
            UserValidator.validateForRegistration(user);
        } catch (Exception e) {
            throw new RegistrationException("Validación fallida: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean register(User user) throws RegistrationException {
        try {
            validate(user);
            
            // Verificar que no exista
            try {
                User existing = userRepository.searchById(user.getCredentials().getId());
                if (existing != null) {
                    throw RegistrationException.userAlreadyExists(user.getCredentials().getId());
                }
            } catch (RuntimeException e) {
                // Usuario no existe, continuar
            }
            
            // Registrar
            userRepository.add(user);
            System.out.println("Usuario registrado: " + user.getFullName());
            return true;
            
        } catch (RegistrationException e) {
            System.err.println(" " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            throw RegistrationException.repositoryError("add user", e);
        }
    }
}
