package services;

import interfaces.IUserRegistrationService;
import interfaces.IUserRegistrationStrategy;
import interfaces.IRepositoryUser;
import interfaces.IRepositoryDoctor;
import interfaces.IRepositoryPatient;
import models.User;
import models.Doctor;
import models.Patient;
import models.Credentials;
import exceptions.RegistrationException;
import exceptions.ValidationException;
import validators.CredentialsValidator;

/**
 * Servicio de registro de usuarios
 * Responsabilidad: Coordinar el registro usando estrategias
 */
public class UserRegistrationService implements IUserRegistrationService {
    
    private final IUserRegistrationStrategy genericStrategy;
    private final IUserRegistrationStrategy doctorStrategy;
    private final IUserRegistrationStrategy patientStrategy;
    
    public UserRegistrationService(
            IRepositoryUser userRepository,
            IRepositoryDoctor doctorRepository,
            IRepositoryPatient patientRepository) {
        
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        if (doctorRepository == null) {
            throw new IllegalArgumentException("El repositorio de doctores no puede ser nulo");
        }
        if (patientRepository == null) {
            throw new IllegalArgumentException("El repositorio de pacientes no puede ser nulo");
        }
        
        // Crear estrategias
        this.genericStrategy = UserRegistrationStrategyFactory.createGenericStrategy(userRepository);
        this.doctorStrategy = UserRegistrationStrategyFactory.createDoctorStrategy(
            userRepository, doctorRepository
        );
        this.patientStrategy = UserRegistrationStrategyFactory.createPatientStrategy(
            userRepository, patientRepository
        );
    }
    
    @Override
    public boolean registerUser(User user, Credentials credentials) 
            throws RegistrationException, ValidationException {
        
        try {
            // Validar credenciales
            CredentialsValidator.validate(credentials);
            
            // Asegurar que el usuario tenga las credenciales
            if (user.getCredentials() == null) {
                user.setCredentials(credentials);
            }
            
            // Registrar usando estrategia genérica
            return genericStrategy.register(user);
            
        } catch (RegistrationException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistrationException("Error inesperado al registrar usuario: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean registerDoctor(Doctor doctor, Credentials credentials) 
            throws RegistrationException, ValidationException {
        
        try {
            // Validar credenciales
            CredentialsValidator.validate(credentials);
            
            // Asegurar que el doctor tenga las credenciales
            if (doctor.getCredentials() == null) {
                doctor.setCredentials(credentials);
            }
            
            // Registrar usando estrategia de doctor
            return doctorStrategy.register(doctor);
            
        } catch (RegistrationException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistrationException("Error inesperado al registrar doctor: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean registerPatient(Patient patient, Credentials credentials) 
            throws RegistrationException, ValidationException {
        
        try {
            // Validar credenciales
            CredentialsValidator.validate(credentials);
            
            // Asegurar que el paciente tenga las credenciales
            if (patient.getCredentials() == null) {
                patient.setCredentials(credentials);
            }
            
            // Registrar usando estrategia de paciente
            return patientStrategy.register(patient);
            
        } catch (RegistrationException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistrationException("Error inesperado al registrar paciente: " + e.getMessage(), e);
        }
    }
}