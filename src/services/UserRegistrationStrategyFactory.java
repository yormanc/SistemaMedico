package services;

import interfaces.IUserRegistrationStrategy;
import interfaces.IRepositoryUser;
import interfaces.IRepositoryDoctor;
import interfaces.IRepositoryPatient;
import models.User;
import models.Doctor;
import models.Patient;
import exceptions.RegistrationException;
import validators.UserValidator;

/**
 * Estrategias de registro para diferentes tipos de usuarios
 * Principio Open/Closed: Nuevos tipos se agregan sin modificar existentes
 */

// ============ ESTRATEGIA PARA USUARIOS GENÉRICOS ============
class GenericUserRegistrationStrategy implements IUserRegistrationStrategy {
    
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
            System.out.println("✅ Usuario registrado: " + user.getFullName());
            return true;
            
        } catch (RegistrationException e) {
            System.err.println("❌ " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            throw RegistrationException.repositoryError("add user", e);
        }
    }
}

// ============ ESTRATEGIA PARA DOCTORES ============
class DoctorRegistrationStrategy implements IUserRegistrationStrategy {
    
    private final IRepositoryUser userRepository;
    private final IRepositoryDoctor doctorRepository;
    
    public DoctorRegistrationStrategy(
            IRepositoryUser userRepository,
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
            
            System.out.println("✅ Doctor registrado: " + doctor.getFullName() + 
                             " (Especialidad: " + doctor.getSpeciality().getName() + ")");
            return true;
            
        } catch (RegistrationException e) {
            System.err.println("❌ " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar doctor: " + e.getMessage());
            throw RegistrationException.repositoryError("register doctor", e);
        }
    }
}

// ============ ESTRATEGIA PARA PACIENTES ============
class PatientRegistrationStrategy implements IUserRegistrationStrategy {
    
    private final IRepositoryUser userRepository;
    private final IRepositoryPatient patientRepository;
    
    public PatientRegistrationStrategy(
            IRepositoryUser userRepository,
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
            
            System.out.println("✅ Paciente registrado: " + patient.getFullName());
            return true;
            
        } catch (RegistrationException e) {
            System.err.println("❌ " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar paciente: " + e.getMessage());
            throw RegistrationException.repositoryError("register patient", e);
        }
    }
}

// ============ FACTORY PARA CREAR ESTRATEGIAS ============
public class UserRegistrationStrategyFactory {
    
    public static IUserRegistrationStrategy createGenericStrategy(IRepositoryUser userRepository) {
        return new GenericUserRegistrationStrategy(userRepository);
    }
    
    public static IUserRegistrationStrategy createDoctorStrategy(
            IRepositoryUser userRepository,
            IRepositoryDoctor doctorRepository) {
        return new DoctorRegistrationStrategy(userRepository, doctorRepository);
    }
    
    public static IUserRegistrationStrategy createPatientStrategy(
            IRepositoryUser userRepository,
            IRepositoryPatient patientRepository) {
        return new PatientRegistrationStrategy(userRepository, patientRepository);
    }
}