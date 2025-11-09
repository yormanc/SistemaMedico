package services.strategies;

import interfaces.services.IUserRegistrationStrategy;
import interfaces.repositories.IRepositoryUser;
import interfaces.repositories.IRepositoryDoctor;
import interfaces.repositories.IRepositoryPatient;

/**
 * Factory para crear estrategias de registro
 * Ahora solo crea las estrategias, no las contiene
 */
public class UserRegistrationStrategyFactory {
    
    /**
     * Crea una estrategia de registro genérica
     */
    public static IUserRegistrationStrategy createGenericStrategy(IRepositoryUser userRepository) {
        return new GenericUserRegistrationStrategy(userRepository);
    }
    
    /**
     * Crea una estrategia de registro para doctores
     */
    public static IUserRegistrationStrategy createDoctorStrategy(
            IRepositoryUser userRepository,
            IRepositoryDoctor doctorRepository) {
        return new DoctorRegistrationStrategy(userRepository, doctorRepository);
    }
    
    /**
     * Crea una estrategia de registro para pacientes
     */
    public static IUserRegistrationStrategy createPatientStrategy(
            IRepositoryUser userRepository,
            IRepositoryPatient patientRepository) {
        return new PatientRegistrationStrategy(userRepository, patientRepository);
    }
}