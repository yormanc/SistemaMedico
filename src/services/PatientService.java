package services;

import interfaces.services.IPatientService;
import repositories.PatientRepository;
import repositories.UserRepository;
import models.Patient;
import models.Credentials;
import enums.UserRole;
import repositories.RepositoryManager;
import java.util.List;

/**
 * Servicio para gestión de pacientes
 * Responsabilidad: Solo operaciones CRUD de pacientes
 */
public class PatientService implements IPatientService {
    
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        if (patientRepository == null) {
            throw new IllegalArgumentException("El repositorio de pacientes no puede ser nulo");
        }
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean registerPatient(String fullName, int id, String password, 
                                   int age, String email) {
        try {
            // Validaciones básicas
            if (fullName == null || fullName.trim().isEmpty()) {
                System.err.println("El nombre no puede estar vacío");
                return false;
            }
            
            if (email == null || email.trim().isEmpty()) {
                System.err.println("El email no puede estar vacío");
                return false;
            }
            
            // Crear credenciales y paciente
            Credentials credentials = new Credentials(id, password, UserRole.PATIENT);
            Patient patient = new Patient(fullName, age, email, credentials);
            
            // Agregar a repositorio de pacientes
            boolean addedToPatient = patientRepository.add(patient);
            if (!addedToPatient) {
                System.err.println("No se pudo agregar al repositorio de pacientes");
                return false;
            }
            
            // Agregar a repositorio de usuarios
            boolean addedToUser = userRepository.add(patient);
            if (!addedToUser) {
                // Rollback: remover de repositorio de pacientes
                patientRepository.remove(patient);
                System.err.println("El ID ya existe en el sistema de usuarios");
                return false;
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Paciente registrado: " + fullName + " (ID: " + id + ")");
            System.out.println("Total de pacientes: " + patientRepository.getAll().size());
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al registrar paciente: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updatePatient(Patient patient) {
        try {
            if (patient == null) {
                System.err.println("El paciente no puede ser nulo");
                return false;
            }
            
            // Actualizar en repositorio de pacientes
            boolean updatedInPatient = patientRepository.update(patient);
            if (!updatedInPatient) {
                System.err.println("No se pudo actualizar en repositorio de pacientes");
                return false;
            }
            
            // Actualizar en repositorio de usuarios
            boolean updatedInUser = userRepository.update(patient);
            if (!updatedInUser) {
                System.err.println("Advertencia: No se actualizó en repositorio de usuarios");
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Paciente actualizado: " + patient.getFullName());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deletePatient(Patient patient) {
        try {
            if (patient == null) {
                System.err.println("El paciente no puede ser nulo");
                return false;
            }
            
            // Eliminar de repositorio de pacientes
            boolean removedFromPatient = patientRepository.remove(patient);
            if (!removedFromPatient) {
                System.err.println("No se pudo eliminar del repositorio de pacientes");
                return false;
            }
            
            // Eliminar de repositorio de usuarios
            boolean removedFromUser = userRepository.remove(patient);
            if (!removedFromUser) {
                System.err.println("Advertencia: No se eliminó del repositorio de usuarios");
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Paciente eliminado: " + patient.getFullName());
            System.out.println("Total de pacientes: " + patientRepository.getAll().size());
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Patient getPatientById(int patientId) {
        try {
            return patientRepository.searchById(patientId);
        } catch (Exception e) {
            System.err.println("Error al buscar paciente: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Patient> getAllPatients() {
        try {
            return patientRepository.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener pacientes: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public boolean patientExists(int patientId) {
        try {
            Patient patient = patientRepository.searchById(patientId);
            return patient != null;
        } catch (Exception e) {
            return false;
        }
    }
}
