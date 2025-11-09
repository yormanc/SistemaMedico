package services.strategies;

import interfaces.services.IUserRegistrationStrategy;
import interfaces.repositories.IRepositoryUser;
import interfaces.repositories.IRepositoryPatient;
import models.User;
import models.Patient;
import exceptions.RegistrationException;
import validators.UserValidator;

/**
 * Estrategia de registro para pacientes
 * Principio Open/Closed: Extensible sin modificar
 */
public class PatientRegistrationStrategy implements IUserRegistrationStrategy {
    
    private final IRepositoryUser userRepository;
    private final IRepositoryPatient patientRepository;
    
    public PatientRegistrationStrategy(IRepositoryUser userRepository,
                                      IRepositoryPatient patientRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        if (patientRepository == null) {
            throw new IllegalArgumentException("El repositorio de pacientes no puede ser nulo");
        }
        
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }
    
    @Override
    public void validate(User user) throws RegistrationException {
        if (!(user instanceof Patient)) {
            throw RegistrationException.invalidUserData("El usuario debe ser de tipo Patient");
        }
        
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
            Patient patient = (Patient) user;
            
            // Verificar que no exista en ningún repositorio
            try {
                User existingUser = userRepository.searchById(patient.getCredentials().getId());
                if (existingUser != null) {
                    throw RegistrationException.userAlreadyExists(patient.getCredentials().getId());
                }
            } catch (RuntimeException e) {
                // Usuario no existe, continuar
            }
            
            try {
                Patient existingPatient = patientRepository.searchById(patient.getCredentials().getId());
                if (existingPatient != null) {
                    throw RegistrationException.userAlreadyExists(patient.getCredentials().getId());
                }
            } catch (RuntimeException e) {
                // Paciente no existe, continuar
            }
            
            // Registrar en ambos repositorios
            boolean addedToPatient = patientRepository.add(patient);
            if (!addedToPatient) {
                throw RegistrationException.repositoryError("add to patient repository", null);
            }
            
            boolean addedToUser = userRepository.add(patient);
            if (!addedToUser) {
                // Rollback: remover de patient repository
                patientRepository.remove(patient);
                throw RegistrationException.repositoryError("add to user repository", null);
            }
            
            System.out.println("Paciente registrado: " + patient.getFullName());
            return true;
            
        } catch (RegistrationException e) {
            System.err.println(" " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error al registrar paciente: " + e.getMessage());
            throw RegistrationException.repositoryError("register patient", e);
        }
    }
}