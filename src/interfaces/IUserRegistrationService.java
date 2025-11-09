package interfaces;

import models.User;
import models.Doctor;
import models.Patient;
import models.Credentials;
import exceptions.RegistrationException;
import exceptions.ValidationException;

/**
 * Interface para registro de usuarios
 * Principio: Single Responsibility - Solo maneja registro
 */
public interface IUserRegistrationService {
    
    /**
     * Registra un usuario genérico
     */
    boolean registerUser(User user, Credentials credentials) 
            throws RegistrationException, ValidationException;
    
    /**
     * Registra un doctor
     */
    boolean registerDoctor(Doctor doctor, Credentials credentials) 
            throws RegistrationException, ValidationException;
    
    /**
     * Registra un paciente
     */
    boolean registerPatient(Patient patient, Credentials credentials) 
            throws RegistrationException, ValidationException;
}