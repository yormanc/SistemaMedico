package services.strategies;

import interfaces.services.IUserRegistrationStrategy;
import interfaces.repositories.IRepositoryUser;
import interfaces.repositories.IRepositoryDoctor;
import models.User;
import models.Doctor;
import exceptions.RegistrationException;
import validators.UserValidator;

/**
 * Estrategia de registro para doctores
 * Principio Open/Closed: Extensible sin modificar
 */
public class DoctorRegistrationStrategy implements IUserRegistrationStrategy {
    
    private final IRepositoryUser userRepository;
    private final IRepositoryDoctor doctorRepository;
    
    public DoctorRegistrationStrategy(IRepositoryUser userRepository,
                                     IRepositoryDoctor doctorRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        if (doctorRepository == null) {
            throw new IllegalArgumentException("El repositorio de doctores no puede ser nulo");
        }
        
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }
    
    @Override
    public void validate(User user) throws RegistrationException {
        if (!(user instanceof Doctor)) {
            throw RegistrationException.invalidUserData("El usuario debe ser de tipo Doctor");
        }
        
        try {
            UserValidator.validateDoctorForRegistration((Doctor) user);
        } catch (Exception e) {
            throw new RegistrationException("Validación fallida: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean register(User user) throws RegistrationException {
        try {
            validate(user);
            Doctor doctor = (Doctor) user;
            
            // Verificar que no exista en ningún repositorio
            try {
                User existingUser = userRepository.searchById(doctor.getCredentials().getId());
                if (existingUser != null) {
                    throw RegistrationException.userAlreadyExists(doctor.getCredentials().getId());
                }
            } catch (RuntimeException e) {
                // Usuario no existe, continuar
            }
            
            try {
                Doctor existingDoctor = doctorRepository.searchById(doctor.getCredentials().getId());
                if (existingDoctor != null) {
                    throw RegistrationException.userAlreadyExists(doctor.getCredentials().getId());
                }
            } catch (RuntimeException e) {
                // Doctor no existe, continuar
            }
            
            // Registrar en ambos repositorios
            boolean addedToDoctor = doctorRepository.add(doctor);
            if (!addedToDoctor) {
                throw RegistrationException.repositoryError("add to doctor repository", null);
            }
            
            boolean addedToUser = userRepository.add(doctor);
            if (!addedToUser) {
                // Rollback: remover de doctor repository
                doctorRepository.remove(doctor);
                throw RegistrationException.repositoryError("add to user repository", null);
            }
            
            System.out.println("Doctor registrado: " + doctor.getFullName() + 
                             " (Especialidad: " + doctor.getSpeciality().getName() + ")");
            return true;
            
        } catch (RegistrationException e) {
            System.err.println(" " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error al registrar doctor: " + e.getMessage());
            throw RegistrationException.repositoryError("register doctor", e);
        }
    }
}